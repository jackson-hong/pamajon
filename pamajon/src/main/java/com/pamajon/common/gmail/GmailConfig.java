package com.pamajon.common.gmail;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;

@Configuration("gmailConfig")
public class GmailConfig {

    private  final StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();

    @Value("${spring.mail.username}")
    private String id;
    @Value("${spring.mail.password}")
    private String password;

    public GmailConfig() {
        encryptor.setPassword("pamajon123");
        encryptor.setAlgorithm("PBEWithMD5AndDES");
    }

    public String getId(){
        return encryptor.decrypt(id);
    }

    public String getPassword(){
        return encryptor.decrypt(password);
    }

    public void gmailMailSender(String to,String productName) throws MessagingException {

        Properties p = System.getProperties();
        p.put("mail.smtp.user",getId());
        p.put("mail.smtp.host","smtp.googlemail.com");
        p.put("mail.smtp.port","465");
        p.put("mail.smtp.starttls","true");
        p.put("mail.smtp.auth","true");
        p.put("mail.smtp.debug","true");
        p.put("mail.smtp.socketFactory.port","465");
        p.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        p.put("mail.smtp.socketFactory.fallback","false");


        Authenticator auth = new Gmail();
        Session session = Session.getInstance(p,auth);
        session.setDebug(true);

        MimeMessage msg = new MimeMessage(session);
        msg.setSubject("[파마존] 주문하신 내역을 확인해 주세요.");

        Address fromAddr = new InternetAddress(getId());
        msg.setFrom(fromAddr);

        Address toAddr = new InternetAddress(to);
        msg.addRecipient(Message.RecipientType.TO, toAddr);

        msg.setContent(productName+"을 구매하셨네요 감사합니다 배송은 2035년에 배송됩니다.","text/html;charset=UTF-8");

        Transport.send(msg);
    }

}
