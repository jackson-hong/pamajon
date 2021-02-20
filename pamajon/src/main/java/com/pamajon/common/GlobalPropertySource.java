package com.pamajon.common;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GlobalPropertySource {

    StandardPBEStringEncryptor encryptor;

   @Value("spring.datasource.driverClassName")
   private String driverClassName;
   @Value("spring.datasource.url")
    private String url;
   @Value("spring.datasource.username")
    private String username;
   @Value("spring.datasource.password")
    private String password;

    public GlobalPropertySource() {
        encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword("JEP");
    }

    public String getDriverClassName() {
        return encryptor.decrypt(driverClassName);
    }
    public String getUrl() {
        return encryptor.decrypt(url);
    }
    public String getUsername() {
        return encryptor.decrypt(username);
    }
    public String getPassword() {
        return encryptor.decrypt(password);
    }
}
