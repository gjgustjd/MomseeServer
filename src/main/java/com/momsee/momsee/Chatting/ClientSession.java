package com.momsee.momsee.Chatting;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;

@Getter
public class ClientSession {
    WebSocketSession session;
    String email;
    String child_name;
    boolean isParent;

    public ClientSession(WebSocketSession session,String email,String child_name,boolean isParent)
    {
        this.session = session;
        this.email = email;
        this.child_name = child_name;
        this.isParent = isParent;
    }

}
