package com.haohuo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @author zhangpeike
 * @date 15:51 2018/12/26
 */
@Component
public class MailServiceImpl implements MailService {

    private final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    @Autowired
    private JavaMailSender mailSender;

    @Value("${mail.fromMail.addr}")
    private String from;




    //简单邮件
    @Override
    public void sendSimpMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        try {
            mailSender.send(message);
            logger.info("发送邮件成功");
        } catch (MailException e) {
            e.printStackTrace();
            logger.error("发送邮件出现异常" , e);
        }

    }
    //html类型邮件
    @Override
    public void sendHtmlMail(String to, String subject, String content) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);//这块注意哦，我首次就忘记了
            mailSender.send(mimeMessage);
            logger.debug("发送成功");
        } catch (MessagingException e) {
            e.printStackTrace();
            logger.debug("发送不友好", e);
        }
    }
    //发送带附件的邮箱
    @Override
    public void sendAttachmentsMail(String to, String subject, String content, String filePath) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName,file);
//            添加多个附件可以使用多条 helper.addAttachment(fileName, file)

            mailSender.send(mimeMessage);
            logger.debug("发送成功");
        } catch (MessagingException e) {
            e.printStackTrace();
            logger.debug("发送不友好", e);
        }
    }
    //发送邮件图片
    @Override
    public void sendStaticFile(String to, String subject, String content, String imgPath, String rscId) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource file = new FileSystemResource(new File(imgPath));
            helper.addInline(rscId, file);
//            添加多个附件可以使用多条 helper.addAttachment(fileName, file)

            mailSender.send(mimeMessage);
            logger.debug("发送成功");
        } catch (MessagingException e) {
            e.printStackTrace();
            logger.debug("发送不友好", e);
        }
    }
}
