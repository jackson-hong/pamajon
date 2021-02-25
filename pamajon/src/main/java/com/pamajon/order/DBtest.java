package com.pamajon.order;


import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class DBtest {
    public static void main(String[] args) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword("JEP");
        encryptor.setAlgorithm("PBEWithMD5AndDES");
       String a= encryptor.encrypt("jdbc:log4jdbc:mysql://pamajon.cqrt9cofij9d.us-east-2.rds.amazonaws.com/pamajon?autoReconnect=true&useSSL=false");
        System.out.println(a);
    }
}
