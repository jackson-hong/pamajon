package com.pamajon.order.model.vo;


import com.pamajon.common.security.AES256Util;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

@Log4j2
@Component
public class EncryptMember {

    @Autowired
    private AES256Util aes256Util;

    private final Logger LOGGER = LoggerFactory.getLogger(EncryptMember.class);

    //Order패키지에 있는 Member
    public Member decryptMember(Member m) throws GeneralSecurityException, UnsupportedEncodingException {

        m.setMemberName(aes256Util.decrypt(m.getMemberName()));
        if(m.getMemberPhone()!=null || m.getMemberPhone().equals("")){
            m.setMemberPhone(aes256Util.decrypt(m.getMemberPhone()));
        }
        return m;
    }
    //Member 패키지에 있는 Member

}
