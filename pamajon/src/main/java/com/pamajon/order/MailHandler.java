package com.pamajon.order;


import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import javax.mail.internet.MimeMessage;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import java.util.Properties;

@Configuration
@PropertySource("classpath:application.properties")
public class MailHandler{

    private  final StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();

    @Autowired
    JavaMailSender emailSender;

    @Value("${mail.smtp.port}")
    private int port;
    @Value("${mail.smtp.socketFactory.port}")
    private int socketPort;
    @Value("${mail.smtp.auth}")
    private boolean auth;
    @Value("${mail.smtp.starttls.enable}")
    private boolean starttls;
    @Value("${mail.smtp.starttls.required}")
    private boolean startlls_required;
    @Value("${mail.smtp.socketFactory.fallback}")
    private boolean fallback;
    @Value("${spring.mail.username}")
    private String id;
    @Value("${spring.mail.password}")
    private String password;

    public MailHandler() {
        encryptor.setPassword("pamajon123");
        encryptor.setAlgorithm("PBEWithMD5AndDES");
    }

    public String getId(){
        return encryptor.decrypt(id);
    }

    public String getPassword(){
        return encryptor.decrypt(password);
    }

    @Bean
    public JavaMailSender javaMailService() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setUsername(getId());
        javaMailSender.setPassword(getPassword());
        javaMailSender.setPort(port);
        javaMailSender.setJavaMailProperties(getMailProperties());
        javaMailSender.setDefaultEncoding("UTF-8");
        return javaMailSender;
    }

    private Properties getMailProperties()
    {
        Properties pt = new Properties();
        pt.put("mail.smtp.socketFactory.port", socketPort);
        pt.put("mail.smtp.auth", auth);
        pt.put("mail.smtp.starttls.enable", starttls);
        pt.put("mail.smtp.starttls.required", startlls_required);
        pt.put("mail.smtp.socketFactory.fallback",fallback);
        pt.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        return pt;
    }

    private MimeMessage createMessage(String to, String productName,String userName) throws Exception {

        MimeMessage message = emailSender.createMimeMessage();
        message.addRecipients(Message.RecipientType.TO, to);
        message.setSubject("테스트 메일");
        String messageContent ="테스트 메일";
        message.setText(messageContent,"utf-8","html");
        message.setFrom(new InternetAddress(getId(),"Pamajon"));

        return message;
    }

    public void sendSimpleMessage(String to,String product,String userName) throws Exception {
        MimeMessage message = createMessage(to,product,userName);
        emailSender.send(message);

    }

}
