package com.pamajon.chat.controller;

import com.pamajon.chat.ActiveUserManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@RestController
public class WebSocketConnectionRestController {

    private ActiveUserManager activeSessionManager;

    public WebSocketConnectionRestController(ActiveUserManager activeSessionManager) {
        this.activeSessionManager = activeSessionManager;
    }

    @PostMapping("/chat/user-connect")
    public String userConnect(HttpServletRequest request,
                              @ModelAttribute("username") String userName,
                              @ModelAttribute("userId") String userId) {
        String remoteAddr = "";
        if (request != null) {
            remoteAddr = request.getHeader("Remote_Addr");
            if (StringUtils.isEmpty(remoteAddr)) {
                remoteAddr = request.getHeader("X-FORWARDED-FOR");
                if (remoteAddr == null || "".equals(remoteAddr)) {
                    remoteAddr = request.getRemoteAddr();
                }
            }
        }
        activeSessionManager.add(userName,userId,remoteAddr);
        return remoteAddr;
    }

    @PostMapping("/chat/user-disconnect")
    public String userDisconnect(@ModelAttribute("username") String userName){

        activeSessionManager.remove(userName);
        return "disconnected";
    }

    @GetMapping("/chat/active-user-except/{userName}")
    public ResponseEntity<Set<String>> getActiveUserExceptionCurrentUser(@PathVariable String userName){
        return new ResponseEntity(activeSessionManager.getActiveUsersExceptCurrentUser(userName), HttpStatus.OK);
    }

}
