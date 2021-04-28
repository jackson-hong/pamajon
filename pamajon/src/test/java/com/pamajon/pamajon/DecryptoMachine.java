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


        System.out.println(aes256Util.decrypt("jqk6jvkFS+NeABsul/H46g=="));

    }
}
