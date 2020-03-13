package com.liuyu.miaosha.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

@Component
public class MailServiceImpl implements MailService, InitializingBean {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private JavaMailSenderImpl mailSender;

//    @Autowired
//    JavaMailSender javamailSender;

    private String from = "13032829032@163.com";

    @Override
    public void sendSimpleMail(String to, String subject, String content) throws UnsupportedEncodingException, MessagingException {
//        //创建SimpleMailMessage对象
//        SimpleMailMessage message = new SimpleMailMessage();
//        //邮件发送人
//        message.setFrom(from);
//        //邮件接收人
//        message.setTo(to);
//        //邮件主题
//        message.setSubject(subject);
//        //邮件内容
//        message.setText(content);
//        //发送邮件
//        mailSender.send(message);


            /*1.创建邮件的from内容*/
            String nick = MimeUtility.encodeText("测试邮箱");
            InternetAddress from = new InternetAddress(nick+"<13032829032@163.com>");
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            /*4.把MimeMessage对象作为参数创建MimeMessageHelper对象*/
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            /*把邮件的内容设置到MimeMessageHelper对象*/
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setText(content);
            mimeMessageHelper.setSubject(subject);
            /*发送邮箱*/
            mailSender.send(mimeMessage);


    }

    @Override
    public void sendHtmlMail(String to, String subject, String content) {
        //获取MimeMessage对象
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        try {
            messageHelper = new MimeMessageHelper(message, true);
            //邮件发送人
            messageHelper.setFrom(from);
            //邮件接收人
            messageHelper.setTo(subject);
            //邮件主题
            message.setSubject(subject);
            //邮件内容，html格式
            messageHelper.setText(content, true);
            //发送
            mailSender.send(message);
            //日志信息
            logger.info("邮件已经发送。");
        } catch (MessagingException e) {
            logger.error("发送邮件时发生异常！", e);
        }
    }

    @Override
    public void sendAttachmentsMail(String to, String subject, String content, String filePath) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName, file);
            mailSender.send(message);
            //日志信息
            logger.info("邮件已经发送。");
        } catch (MessagingException e) {
            logger.error("发送邮件时发生异常！", e);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        /*发送邮件使用JavaMailSenderImpl对象*/
        mailSender = new JavaMailSenderImpl();
        /*设置发送邮件的邮箱的地址和密码*/
        mailSender.setUsername("13032829032@163.com");
        mailSender.setPassword("xyzw77");
        /*设置smtp地址*/
        mailSender.setHost("smtp.163.com");
        /*设置端口*/
        mailSender.setPort(465);
        /*设置解码格式*/
        mailSender.setDefaultEncoding("UTF-8");

        /*通过Properties对象设置ssl开启*/
        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.ssl.enable",true);
        mailSender.setJavaMailProperties(javaMailProperties);
    }
}
