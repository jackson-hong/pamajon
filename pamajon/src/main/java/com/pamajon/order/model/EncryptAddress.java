package com.pamajon.order.model;

import com.pamajon.common.security.AES256Util;
import com.pamajon.order.model.vo.AddressDto;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.List;

@Component
public class EncryptAddress {

    private final AES256Util aes256Util;

    public EncryptAddress(AES256Util aes256Util){
        this.aes256Util = aes256Util;
    }

    public AddressDto encryption(AddressDto address){

        try {
            address.setAddrPhone(aes256Util.encrypt(address.getAddrPhone()));
            address.setAddrZipcode(aes256Util.encrypt(address.getAddrZipcode()));
            address.setAddr(aes256Util.encrypt(address.getAddr()));
            address.setAddrDetail(aes256Util.encrypt(address.getAddrDetail()));
            address.setAddrCellPhone(aes256Util.encrypt(address.getAddrCellPhone()));
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return address;
    }

    public AddressDto decryption(AddressDto address){
        try {
            address.setAddrPhone(aes256Util.decrypt(address.getAddrPhone()));
            address.setAddr(aes256Util.decrypt(address.getAddr()));
            address.setAddrZipcode(aes256Util.decrypt(address.getAddrZipcode()));
            address.setAddrDetail(aes256Util.decrypt(address.getAddrDetail()));
            address.setAddrCellPhone(aes256Util.decrypt(address.getAddrCellPhone()));
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return address;
    }

    public List<AddressDto> decryption(List<AddressDto> address){
            try {
                for(int i = 0 ; i<address.size(); i++){
                    address.get(i).setAddrPhone(aes256Util.decrypt(address.get(i).getAddrPhone()));
                    address.get(i).setAddrCellPhone(aes256Util.decrypt(address.get(i).getAddrCellPhone()));
                    address.get(i).setAddrZipcode(aes256Util.decrypt(address.get(i).getAddrZipcode()));
                    address.get(i).setAddr(aes256Util.decrypt(address.get(i).getAddr()));
                    address.get(i).setAddrDetail(aes256Util.decrypt(address.get(i).getAddrDetail()));
                }
            } catch (GeneralSecurityException e) {
               e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        return address;
    }
}
