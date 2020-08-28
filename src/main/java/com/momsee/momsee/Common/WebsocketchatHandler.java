package com.momsee.momsee.Common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.momsee.momsee.Chatting.ChatMessage;
import com.momsee.momsee.Chatting.ChatRoom;
import com.momsee.momsee.Chatting.ChatService;
import com.momsee.momsee.Chatting.ClientSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.json.simple.JSONObject;

import java.util.ArrayList;

@Slf4j
@RequiredArgsConstructor
@Component
public class WebsocketchatHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;
    private final ChatService chatService;
    private final ArrayList<ClientSession> sessions = new ArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session)
            throws Exception {
        System.out.printf("%s 연결 됨\n", session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payload {}", payload);

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(payload);
        JSONObject object = (JSONObject)obj;
        ClientSession entry = new ClientSession(session,
                object.get("email").toString(),
                object.get("child_name").toString(),
                (boolean)object.get("isParent"));

        boolean isThere = false;
        for(ClientSession p : sessions)
        {
            if(p.getEmail().equals(entry.getEmail()) &&
                    p.getChild_name().equals(entry.getChild_name()) &&
                    p.isParent() == entry.isParent())
            {
                isThere = true;break;
            }
        }

        if(!isThere)
            sessions.add(entry);

        boolean isThereDes = false;
        ClientSession destination = null;
        for(ClientSession p : sessions)
        {
            if(p.getEmail().equals(entry.getEmail()) &&
                    p.getChild_name().equals(entry.getChild_name()) &&
                    p.isParent() == !entry.isParent())
            {
                isThereDes = true;
                destination = p;
                break;
            }
        }
        if(isThereDes) {
            WebSocketSession wss = destination.getSession();
            wss.sendMessage(new TextMessage(objectMapper.writeValueAsString(object.get("content"))));
        }
        else
            System.out.println("목적지가 접속해 있지 않음");

    }

    @Override
    public void afterConnectionClosed(
            WebSocketSession session, CloseStatus status) throws Exception {
        for(ClientSession p :sessions)
        {
            if(p.getSession() == session) {
                sessions.remove(p);
                break;
            }
        }
        System.out.printf("%s 연결 끊김\n", session.getId());
    }
}



