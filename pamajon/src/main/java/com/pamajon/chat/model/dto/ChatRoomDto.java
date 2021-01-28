package com.pamajon.chat.model.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Setter
@Getter
public class ChatRoomDto {

    private String roomId;
    private String roomName;

    public static ChatRoomDto create(String name){
        ChatRoomDto chatRoom = new ChatRoomDto();
        chatRoom.roomId = UUID.randomUUID().toString();
        chatRoom.roomName = name;
        return chatRoom;
    }

}
