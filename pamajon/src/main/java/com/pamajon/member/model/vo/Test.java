package com.pamajon.member.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class Test {
    String email;
    String name;
    String gender;
    String birthday;
}
