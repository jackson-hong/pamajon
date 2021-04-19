package com.pamajon.member.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mileage {
    int usid;
    int amount;
    String content;
    String type;
    Date date;
}
