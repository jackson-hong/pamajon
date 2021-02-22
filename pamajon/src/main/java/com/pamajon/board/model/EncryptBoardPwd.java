package com.pamajon.board.model;

import com.pamajon.common.security.AES256Util;
import com.pamajon.board.model.vo.QnaDto;
import com.pamajon.board.model.vo.ReviewDto;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

@Component
public class EncryptBoardPwd {

    private final AES256Util aes256Util;

    public EncryptBoardPwd(AES256Util aes256Util) {
        this.aes256Util = aes256Util;
    }

    public QnaDto qnaPwdEncryption(QnaDto qna){
     try {
         qna.setQnaPwd(aes256Util.encrypt(qna.getQnaPwd()));
     }catch (GeneralSecurityException e){
         e.printStackTrace();
     }catch (UnsupportedEncodingException e){
         e.printStackTrace();
     }
     return qna;
    }

    public QnaDto qnaPwdDecryption(QnaDto qna){
        try{
            qna.setQnaPwd(aes256Util.decrypt(qna.getQnaPwd()));
        }catch (GeneralSecurityException e){
            e.printStackTrace();
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return qna;
    }
}
