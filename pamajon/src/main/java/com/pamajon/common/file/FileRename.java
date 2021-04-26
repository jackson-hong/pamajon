package com.pamajon.common.file;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class FileRename {

    public String fileRename(MultipartFile filename) {

        return  "pamajon_"
                +new SimpleDateFormat("yyyyMMddHHmmss")
                .format(new Date())
                +(int)(Math.random()*90000+10000)+filename.getOriginalFilename()
                .substring(filename.getOriginalFilename().lastIndexOf("."));

    }
}
