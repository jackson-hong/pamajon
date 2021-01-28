package com.pamajon.chat;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.pamajon.chat.model.dto.ChatMessage;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.*;

@Component
public class ActiveUserManager {

    //소켓 연결시 유저 정보가 담기는 map
    private final Map<String, Object> map;

    private final List<ActiveUserChangeListener> listeners;

    private final ThreadPoolExecutor notifyPool;

    private Multimap<String, ChatMessage> messageHandler = ArrayListMultimap.create();

    //private final MultiValueMap<String, Object> roomMultimap;

    private ActiveUserManager() {

        map = new ConcurrentHashMap<>();
        listeners = new CopyOnWriteArrayList<>();
        notifyPool = new ThreadPoolExecutor(1, 5, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));

    }
    public void add(String userName,String userId,String remoteAddress){
        map.put(userName,remoteAddress);
        notifyListeners();
    }

    public void remove(String userName){
        map.remove(userName);
        notifyListeners();
    }
    public void addMessage(String userName,ChatMessage messageInfo){
        messageHandler.put(userName,messageInfo);

    }

    public Collection<ChatMessage> returnAllMessages(String userName){
        return messageHandler.get(userName);
    }

    public Set<String> getAll() {
        return map.keySet();
    }

    public Set<String> getActiveUsersExceptCurrentUser(String username) {
        Set<String> users = new HashSet<>(map.keySet());
        users.remove(username);
        return users;

    }
    public void registerListener(ActiveUserChangeListener listener) {
        listeners.add(listener);
    }

    public void removeListener(ActiveUserChangeListener listener) {
        listeners.remove(listener);
    }

    private void notifyListeners() {
        notifyPool.submit(() -> listeners.forEach(ActiveUserChangeListener::notifyActiveUserChange));
    }
}
