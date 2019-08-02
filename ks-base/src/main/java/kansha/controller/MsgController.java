package kansha.controller;

import com.github.qcloudsms.SmsSingleSenderResult;
import kansha.emuns.CommonEmun;
import kansha.entitys.ResultEntity;
import kansha.service.EmailService;
import kansha.service.RedisService;
import kansha.service.TencentMsgService;
import kansha.utils.RandomNumberUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

/**
 * @program: ks-parent
 * @description:
 * @author: pzy
 * @create: 2019-08-01 16:44
 **/
@RestController
@Slf4j
public class MsgController {

    private static final String PHONE_NUMBER_REG = "^1\\d{10}$";
    private static final String CHECK_CODE = "^[0-9a-zA-Z]+$";
    private static final String RULE_EMAIL = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";

    @Autowired
    TencentMsgService tencentMsgUtils;

    @Autowired
    EmailService emailService;

    @Autowired
    RedisService redisService;
    /**
     *  腾讯接口 发送验证码
     * @param tel
     * @return
     */
    @RequestMapping("sendTelCheckCode")
    public ResultEntity sendTelCheckCode(@RequestParam String tel ){
        log.info("【url:/sendTelCheckCode】 【tel:"+tel+"】");
        String code = RandomNumberUtil.getRandomNumber6();
        ResultEntity result = new ResultEntity();
        if(StringUtils.isEmpty(tel) || StringUtils.isEmpty(code) || !tel.matches(PHONE_NUMBER_REG)|| !code.matches(CHECK_CODE)) {
            result.setCode(CommonEmun.PARAMS_ERROR.getCode());
            result.setDescription(CommonEmun.PARAMS_ERROR.getDesc());
            return result;
        }

        SmsSingleSenderResult smsSingleSenderResult = tencentMsgUtils.sendCheckCode(tel, code);
        if(smsSingleSenderResult.result == 0){
            result.setCode(CommonEmun.SUCCESS.getCode());
            result.setDescription(CommonEmun.SUCCESS.getDesc());
            //存入redis缓存
            redisService.set(tel,code, (long) 30*60);

        }else {
            result.setCode(CommonEmun.SEAND_CODE_ERROR.getCode());
            result.setDescription(CommonEmun.SEAND_CODE_ERROR.getDesc());
        }
        result.setData(smsSingleSenderResult);
        return result;
    }
    @RequestMapping("sendMailCheckCode")
    public ResultEntity sendMailCheckCode(@RequestParam String emailAdress ){
        log.info("【url:/sendMailCheckCode】 【emailAdress:"+emailAdress+"】");
        String code = RandomNumberUtil.getRandomNumber6();
        ResultEntity result = new ResultEntity();
        if(StringUtils.isEmpty(emailAdress) || StringUtils.isEmpty(code) || !emailAdress.matches(RULE_EMAIL)|| !code.matches(CHECK_CODE)) {
            result.setCode(CommonEmun.PARAMS_ERROR.getCode());
            result.setDescription(CommonEmun.PARAMS_ERROR.getDesc());
            return result;
        }
        result.setCode(CommonEmun.SUCCESS.getCode());
        result.setDescription(CommonEmun.SUCCESS.getDesc());
        try {
            emailService.sendCode(emailAdress, code);
        } catch (Exception e) {
            result.setCode(CommonEmun.SEAND_CODE_ERROR.getCode());
            result.setDescription(CommonEmun.SEAND_CODE_ERROR.getDesc());
            e.printStackTrace();
        }
        //存入redis缓存
        redisService.set(emailAdress,code, (long) 30*60);
        return result;
    }
}
