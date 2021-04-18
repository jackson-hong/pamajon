package com.pamajon.pamajon;

import com.pamajon.common.security.AES256Util;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;

@SpringBootTest
public class DecryptoMachine {

    AES256Util aes256Util = new AES256Util();

    public DecryptoMachine() throws UnsupportedEncodingException {
    }

    @Test
    public void decrypto(){


    }
}
