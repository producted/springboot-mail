package com.haohuo.service;

/**
 * @author zhangpeike
 * @date 15:51 2018/12/26
 */
public interface MailService {
    void sendSimpMail(String to, String subject, String content);

    void sendHtmlMail(String to, String subject, String content);

    void sendAttachmentsMail(String to, String subject, String content, String filePath);

    void sendStaticFile(String to, String subject, String content, String imgPath, String rscId);
}
