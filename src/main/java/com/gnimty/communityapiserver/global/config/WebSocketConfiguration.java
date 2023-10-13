//package com.gnimty.communityapiserver.global.config;
//
//import com.gnimty.communityapiserver.domain.chat.handler.ChatHandler;
//import com.gnimty.communityapiserver.global.interceptor.TokenAuthInterceptor;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.config.annotation.EnableWebSocket;
//import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//import org.springframework.web.socket.server.HandshakeInterceptor;
//
//@Configuration
//@RequiredArgsConstructor
//@EnableWebSocket
//@Slf4j
//public class WebSocketConfiguration implements WebSocketConfigurer {
//
//    private final WebSocketHandler chatHandler;
//
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        registry.addHandler(chatHandler, "/chat").setAllowedOrigins("*");
//    }
//}
