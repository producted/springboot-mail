package com.haohuo.web;

import com.haohuo.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sun.misc.Request;

/**
 * @author zhangpeike
 * @date 13:37 2018/12/26
 */
@Controller
public class MailController {

    @Autowired
    private MailService mailService;


    @RequestMapping(value = "sendStaticFile" , method = RequestMethod.GET)
    void sendStaticFile(){
        String rscId = "testImg";
        String to = "17666210109@163.com";
        String subject = "subject:有附件，注意查收！";
        String content = "<html><body>这是您的图片邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        String imgPath = "C:\\Users\\Zhang Peike\\Desktop\\kenan.jpg";
        mailService.sendStaticFile(to, subject, content, imgPath, rscId);
    }

    @RequestMapping(value = "sendAttachmentsMail" , method = RequestMethod.GET)
    void sendAttachmentsMail() {
        String to = "17666210109@163.com";
        String subject = "subject:有附件，注意查收！";
        String content = "good luck to you! ";
        String filePath = "C:\\Users\\Zhang Peike\\Desktop\\kenan.jpg";
        mailService.sendAttachmentsMail(to, subject, content, filePath);
    }

    @RequestMapping(value = "sendHtml" , method = RequestMethod.GET)
    void sendHtml(){
        String to = "17666210109@163.com";
        String subject = "html mail byZpk";
        String content = "<html>\n" +
                "<body>\n" +
                "    <h3><font color='red'>hello world ! 这是一封Html邮件!</font></h3>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendHtmlMail(to, subject, content);
    }

    @RequestMapping(value = "sendSimple" ,method = RequestMethod.GET)
    void sendSimple(){
        String to = "17666210109@163.com";
        String subject = "test mail byZpk";
        String content = "good luck to you! ";
        mailService.sendSimpMail(to, subject, content);
    }

}
