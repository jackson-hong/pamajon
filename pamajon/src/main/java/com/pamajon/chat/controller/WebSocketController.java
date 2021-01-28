package com.pamajon.chat.controller;


import com.pamajon.chat.ActiveUserChangeListener;
import com.pamajon.chat.ActiveUserManager;
import com.pamajon.chat.model.dto.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Collection;
import java.util.Set;

@Controller
public class WebSocketController implements ActiveUserChangeListener {

    private final SimpMessagingTemplate webSocket;

    private final ActiveUserManager activeUserManager;

    public WebSocketController(SimpMessagingTemplate webSocket,ActiveUserManager activeUserManager){
        this.webSocket=webSocket;
        this.activeUserManager=activeUserManager;
    }

    @PostConstruct
    private void init() {
        activeUserManager.registerListener(this);
    }

    @PreDestroy
    private void destroy() {
        activeUserManager.removeListener(this);
    }

    @GetMapping("/admin/chatroom")
    public String getWebSocketWithSockJs() {
        return "/admin/chattingRoom";
    }

    @MessageMapping("/chat")
    public void send(SimpMessageHeaderAccessor sha, @Payload ChatMessage chatMessage) throws Exception {
        String sender = sha.getUser().getName();
        String roomId ="";
        if(sha.getUser().getName().equals("운영자")){
            //roomId 는 사용자의 이름이 되어야함으로 운영자가 sending 시 받는사람으로 map 에 스텍이 올라가야함.

            activeUserManager.addMessage(chatMessage.getRecipient(),new ChatMessage(chatMessage.getFrom(),chatMessage.getText(), chatMessage.getRecipient()));
        } else {

            activeUserManager.addMessage(chatMessage.getFrom(),new ChatMessage(chatMessage.getFrom(),chatMessage.getText(), chatMessage.getRecipient()));
        }
        ChatMessage message = new ChatMessage(chatMessage.getFrom(),chatMessage.getText(), chatMessage.getRecipient());
        if (!sender.equals(chatMessage.getRecipient())) {
            webSocket.convertAndSendToUser(sender, "/queue/messages", message);
        }
        webSocket.convertAndSendToUser(chatMessage.getRecipient(), "/queue/messages", message);
    }

    @MessageMapping("/entry")
    public void entryHandler(SimpMessageHeaderAccessor sha,
                             @Payload ChatMessage chatMessage){

        String sender = sha.getUser().getName();

        if(!sender.equals(chatMessage.getRecipient())){
            webSocket.convertAndSendToUser(sender,"/queue/entrymessage",chatMessage.getRecipient()+" 님과 대화를 시작합니다.");
        }
        webSocket.convertAndSendToUser(chatMessage.getRecipient(),"/queue/entrymessage","운영자 와 대화를 시작합니다.");

    }
    //관리자가 요청했을떄.
    @MessageMapping("/admin/messageList")
    public void returnMessageList(SimpMessageHeaderAccessor sha,
                             @Payload ChatMessage chatMessage){

        webSocket.convertAndSendToUser(sha.getUser().getName(),"/queue/messageList",activeUserManager.returnAllMessages(chatMessage.getRecipient()));

    }
    //사용자가 요청했을떄.
    @MessageMapping("/user/messageList")
    public void returnMessageList(SimpMessageHeaderAccessor sha) {
        webSocket.convertAndSendToUser(sha.getUser().getName(),"/queue/messageList",activeUserManager.returnAllMessages(sha.getUser().getName()));

    }

    @Override
    public void notifyActiveUserChange() {

        Set<String> activeUsers = activeUserManager.getAll();
        webSocket.convertAndSend("/topic/active", activeUsers);
    }

}
