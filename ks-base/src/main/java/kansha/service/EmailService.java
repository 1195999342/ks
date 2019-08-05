package kansha.service;

import com.github.qcloudsms.SmsSingleSenderResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

/**
 * JavaMail 版本: 1.6.0
 * JDK 版本: JDK 1.7 以上（必须）
 */
@Service
public class EmailService {

    @Value("${mail.myEmailPassword}")
    private  String myEmailPassword;
    @Value("${mail.myEmail}")
    private  String myEmail;
    @Value("${mail.myEmailSMTPHost}")
    private  String myEmailSMTPHost;
    @Value("${mail.mySmtpPort}")
    private  String smtpPort;


    public void sendCode(String code, String receiveMail){
        try {
            Properties props = new Properties();                    // 参数配置
            props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
            props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
            props.setProperty("mail.smtp.auth", "true");            // 需要请求认证
            Session session = Session.getInstance(props);
            //session.setDebug(true);                                 // 设置为debug模式, 可以查看详细的发送 log
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myEmail, "看啥网", "UTF-8"));
            message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "", "UTF-8"));
            message.setRecipient(Message.RecipientType.CC, new InternetAddress(myEmail, "", "UTF-8"));
            message.setSubject("验证码", "UTF-8");
            message.setContent("【看啥网】"+code+"为您的登录验证码，请于30分钟内填写。如非本人操作，请忽略本短信。", "text/html;charset=UTF-8");
            message.setSentDate(new Date());
            message.saveChanges();
            Transport transport = session.getTransport();
            transport.connect(myEmail, myEmailPassword);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
