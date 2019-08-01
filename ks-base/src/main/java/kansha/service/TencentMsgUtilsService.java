package kansha.service;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @program: ks-parent
 * @description: 腾讯发送验证码工具类
 * @author: pzy
 * @create: 2019-08-01 16:59
 **/
@Component
public class TencentMsgUtilsService {
    @Value("${tencent.msg.appid}")
    private  int appid;

    @Value("${tencent.msg.appkey}")
    private  String appkey;

    @Value("${tencent.msg.smsSign}")
    private  String smsSign;

    @Value("${tencent.msg.templateId}")
    private  int templateId;

    @Value("${tencent.msg.time}")
    private  String time;

    /**
     * 自定义短信内容发送短信
     * @param tel
     * @param msg
     * @return
     */
    public  SmsSingleSenderResult sendMsg(String msg,String tel){
        SmsSingleSenderResult result = null;
        try {
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            result = ssender.send(0, "86", tel,
                    "【腾讯云】您的验证码是: 5678", "", "");
            System.out.println(result);
        } catch (HTTPException e) {
            // HTTP 响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // JSON 解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络 IO 错误
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 按模板发送单个短息验证码
     * @param tel
     * @param code
     * @return
     */
    public  SmsSingleSenderResult sendCheckCode(String tel, String code){

        SmsSingleSenderResult result = null;
        try {
            String[] params = {code,time};
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            result = ssender.sendWithParam("86", tel,
                    templateId, params, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
        } catch (HTTPException e) {
            // HTTP 响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // JSON 解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络 IO 错误
            e.printStackTrace();
        }
        return result;

    }
}
