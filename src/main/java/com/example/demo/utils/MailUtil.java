package com.example.demo.utils;

import com.example.demo.exception.BizException;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.Random;

/**
 * Created by 刘亮 on 2017/11/10.
 */
@Component
public class MailUtil {

    private static JavaMailSender javaMailSender;



    @Autowired
    public MailUtil(JavaMailSender javaMailSender){
        MailUtil.javaMailSender=javaMailSender;

    }

    public static String AuthMail(String mail){
        //校验邮箱格式
        String format = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";
        if(!mail.matches(format)){
            throw new BizException(ResponseCode.USER_MAIL_WRONGFUL_60005);
        }
        //生成随机数，发送邮件
        Random random = new Random();
        Integer i = random.nextInt(9999-1000+1)+1000;
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("939513800@qq.com");
        simpleMailMessage.setTo(mail);
        simpleMailMessage.setSubject(MessageUtil.LOGIN_MAIL);
        simpleMailMessage.setText("欢迎注册亮亮购物商城！验证码： "+i.toString()+",请勿告诉任何人。此验证码将在30分钟后失效！");
        String[] arr = {"939513800@qq.com"};
        simpleMailMessage.setCc(arr);
        javaMailSender.send(simpleMailMessage);
        return i.toString();
    }

    public static String AuthMailSSL(String mail) throws EmailException {
        //校验邮箱格式
        String format = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";
        if(!mail.matches(format)){
            throw new BizException(ResponseCode.USER_MAIL_WRONGFUL_60005);
        }
        //生成随机数，发送邮件
        Random random = new Random();
        Integer i = random.nextInt(9999-1000+1)+1000;
        HtmlEmail hemail = new HtmlEmail();
        hemail.setSSL(true);
        hemail.setHostName("smtp.163.com");
        //端口号
        hemail.setSmtpPort(465);
        hemail.setCharset(Charset.defaultCharset().name());
        hemail.addTo(mail);
        String[] arr = {"loqvliuliang@163.com"};
        hemail.addCc(arr);
        hemail.setFrom("loqvliuliang@163.com", "刘亮");
        hemail.setAuthentication("loqvliuliang@163.com", "handhand123");
        hemail.setSubject(MessageUtil.LOGIN_MAIL);
        hemail.setMsg("欢迎注册亮亮购物商城！验证码： "+i.toString()+",请勿告诉任何人。此验证码将在30分钟后失效！");
        hemail.send();
        return i.toString();
    }
}
