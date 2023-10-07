package com.gnimty.communityapiserver.domain.chat.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gnimty.communityapiserver.domain.chat.handler.dto.ChatRequest;
import com.gnimty.communityapiserver.domain.chat.handler.dto.ChatType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.validation.Valid;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChatHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("session: {}", session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("          message: {}", message);
        String payload = message.getPayload();
        ChatRequest chatRequest = objectMapper.readValue(payload, ChatRequest.class);

        // service에서 chatRequest 처리
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
    }
}