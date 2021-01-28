package com.pamajon.chat;

import com.pamajon.chat.model.dto.ChatRoomDto;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class ChatRoomRepository {

    private Map<String, ChatRoomDto> chatRoomMap;


    @PostConstruct
    private void init(){
        chatRoomMap = new LinkedHashMap<>();
    }

}
