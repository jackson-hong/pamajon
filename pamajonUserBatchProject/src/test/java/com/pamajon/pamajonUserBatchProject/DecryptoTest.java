package com.pamajon.pamajonUserBatchProject;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DecryptoTest {

    private final StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();

    @Test
    public void test1(){
        encryptor.setPassword("JEP");
        encryptor.setAlgorithm("PBEWithMD5AndDES");

        System.out.println(encryptor.decrypt("TOcCRGyrZG/BNpxOBAzp/lIMxrGeo8loOjftEjRxCgW3o7QqGReqvWJWpV/N2v1DOnEjNjHCHQhPXz0RfBbKIhWLeHAeI5uJRDb9jgIPbKaGuMWj1RZAs73TMg+bUXoUOqDj6CdIin4N0Rg5iXjal6qmmiE+/Yot"));


    }


}
