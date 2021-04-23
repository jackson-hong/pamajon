package com.pamajon.order.model.vo;


import com.pamajon.common.security.AES256Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

@Component
public class EncryptMember {

    @Autowired
    private AES256Util aes256Util;

    public Member decryptMember(Member m) throws GeneralSecurityException, UnsupportedEncodingException {

        m.setMemberName(aes256Util.decrypt(m.getMemberName()));
        if(m.getMemberPhone()!=null || m.getMemberPhone().equals("")){
            m.setMemberPhone(aes256Util.decrypt(m.getMemberPhone()));
        }


        return m;
    }
}
