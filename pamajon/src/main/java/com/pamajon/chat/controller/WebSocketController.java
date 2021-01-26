package com.pamajon.chat.controller;

import com.google.gson.Gson;
import com.pamajon.chat.ActiveUserChangeListener;
import com.pamajon.chat.ActiveUserManager;
import com.pamajon.chat.model.dto.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.security.Principal;
import java.util.Map;
import java.util.Set;

@Controller

public class WebSocketController implements ActiveUserChangeListener {

    // private final static Logger LOGGER = LoggerFactory.getLogger(WebSocketChatController.class);

    @Autowired
    private SimpMessagingTemplate webSocket;

    @Autowired
    private ActiveUserManager activeUserManager;

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
    @SendToUser("/queue/messages")
    public String send(String sender, Principal principal, @Payload ChatMessage chatMessage) throws Exception {

        ChatMessage message = new ChatMessage(chatMessage.getFrom(), chatMessage.getText(), chatMessage.getRecipient());
        if (!sender.equals(chatMessage.getRecipient())) {
            return "ㅇㅋㅇㅋ";
        }
        //webSocket.convertAndSendToUser(chatMessage.getRecipient(),"/queue/messages", message);
        return "넘아감";
    }

    @Override
    public void notifyActiveUserChange() {

        Set<String> activeUsers = activeUserManager.getAll();
        webSocket.convertAndSend("/topic/active", activeUsers);

    }

}
