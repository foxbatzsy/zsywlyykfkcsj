package order_861385.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.websocket.MessageHandler;
import java.util.Date;

/**
 * 邮箱
 *
 * @ClassName: Email
 * @Author: liunian
 * @Date: 2021/12/14 22:18
 */
@Component
public class Email {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendSimpleMail(String email,Integer id) throws MessagingException {
        // 构建一个邮件对象
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(message,true);
        // 设置邮件主题
        helper.setSubject("确认信息");
        // 设置邮件发送者，这个跟application.yml中设置的要一致
        helper.setFrom("510664109@qq.com");
        // 设置邮件接收者，可以有多个接收者，中间用逗号隔开，以下类似
        // message.setTo("10*****16@qq.com","12****32*qq.com");
        helper.setTo(email);
        // 设置邮件发送日期
        helper.setSentDate(new Date());
        // 设置邮件的正文
//        message.setText("这是测试邮件的正文");
        helper.setText("<a href='http://127.0.0.1:8088/shopping/confirm/"+id+"'>点击确认</a>",true);
        // 发送邮件
        javaMailSender.send(message);
    }
}
