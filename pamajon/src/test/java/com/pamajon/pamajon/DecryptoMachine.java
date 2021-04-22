package com.pamajon.pamajon;

import com.pamajon.common.security.AES256Util;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

@SpringBootTest
public class DecryptoMachine {

    AES256Util aes256Util = new AES256Util();
    private final StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();

    public DecryptoMachine() throws UnsupportedEncodingException {
    }

    @Test
    public void decrypto() throws GeneralSecurityException, UnsupportedEncodingException {

        encryptor.setPassword("JEP");
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        System.out.println(encryptor.decrypt("TOcCRGyrZG/BNpxOBAzp/lIMxrGeo8loOjftEjRxCgW3o7QqGReqvWJWpV/N2v1DOnEjNjHCHQhPXz0RfBbKIhWLeHAeI5uJRDb9jgIPbKaGuMWj1RZAs73TMg+bUXoUOqDj6CdIin4N0Rg5iXjal6qmmiE+/Yot"));

    }
}
