package kansha.controller;

import com.github.qcloudsms.SmsSingleSenderResult;
import kansha.service.TencentMsgUtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: ks-parent
 * @description:
 * @author: pzy
 * @create: 2019-08-01 16:44
 **/
@RestController
public class TestController {
    @Autowired
    TencentMsgUtilsService tencentMsgUtils;


    @RequestMapping("test")
    public SmsSingleSenderResult testController(@RequestParam String code ){

        SmsSingleSenderResult smsSingleSenderResult = tencentMsgUtils.sendCheckCode("18652488936", code);
        return smsSingleSenderResult;
    }
}
