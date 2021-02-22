package com.pamajon.common.security;

import lombok.ToString;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@ToString
public class DataBaseConfig {

    private  final StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();

    @Value("${spring.datasource.driverClassName}")
    private String driverClassName;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    public DataBaseConfig() {
        encryptor.setPassword("JEP");
        encryptor.setAlgorithm("PBEWithMD5AndDES");
    }

    public String getDriverClassName() { return driverClassName; }
    public String getUrl() { return encryptor.decrypt(url); }
    public String getUsername() {
        return encryptor.decrypt(username);
    }
    public String getPassword() {
        return encryptor.decrypt(password);
    }


}
