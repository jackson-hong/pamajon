package com.pamajon.common.file;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class FileRenameStringType {

    public String fileRenameString(String filename) {

        return  "pamajon_"
                +new SimpleDateFormat("yyyyMMddHHmmss")
                .format(new Date())
                +(int)(Math.random()*90000+10000)+filename
                .substring(filename.lastIndexOf("."));

    }


}
