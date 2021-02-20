package com.pamajon.common.security;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class DBEncryptionTest {
    public static void main(String[] args) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword("JEP");
        String driverClassName = encryptor.encrypt("org.mariadb.jdbc.Driver");
        String url = encryptor.encrypt("jdbc:mysql://pamajon.cqrt9cofij9d.us-east-2.rds.amazonaws.com/pamajon?autoReconnect=true&useSSL=false");
        String username = encryptor.encrypt("admin");
        String password = encryptor.encrypt("pamajon!");

        System.out.println("driverClassName : " + driverClassName);
        System.out.println("url : " + url);
        System.out.println("username : " + username);
        System.out.println("password : " + password);

        StandardPBEStringEncryptor encryptor1 = new StandardPBEStringEncryptor();
        encryptor1.setPassword("JEP");
        System.out.println(encryptor1.decrypt(driverClassName));
    }

}
