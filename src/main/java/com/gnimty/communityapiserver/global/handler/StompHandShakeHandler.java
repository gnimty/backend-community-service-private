package com.gnimty.communityapiserver.global.handler;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeFailureException;
import org.springframework.web.socket.server.HandshakeHandler;

@Component
@Slf4j
public class StompHandShakeHandler implements HandshakeHandler {

    @Override
    public boolean doHandshake(ServerHttpRequest request, ServerHttpResponse response,
        WebSocketHandler wsHandler, Map<String, Object> attributes)
        throws HandshakeFailureException {

        log.info("handshake");

        return false;
    }
}
