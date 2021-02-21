package com.pamajon.common.gmail;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class Gmail extends Authenticator {

    private  final StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();

    private final String ID = "BuREqXCz0tlNVbFJiTPKaNrCgx9tNIQf9R8ivWn14EA=";
    private final String PASSWORD ="PVrp/NJZBUejtr8g86fS4SfL/jkHlTab";

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {

        encryptor.setPassword("pamajon123");
        encryptor.setAlgorithm("PBEWithMD5AndDES");

        return new PasswordAuthentication(encryptor.decrypt(ID), encryptor.decrypt(PASSWORD));

    }

}