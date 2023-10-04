package com.gnimty.communityapiserver.domain.chat.service;

import com.gnimty.communityapiserver.domain.chat.entity.ChatRoom;
import com.gnimty.communityapiserver.domain.chat.entity.ChatUser;
import com.gnimty.communityapiserver.domain.chat.entity.User;
import com.gnimty.communityapiserver.domain.chat.repository.ChatRoomRepository;
import com.gnimty.communityapiserver.domain.chat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {

    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    ChatRoom getChatRoom(List<Long> usersId, LocalDateTime currentTime) {
        Optional<ChatRoom> findChatRoom = chatRoomRepository.findByChatUsersUserIdIn(usersId);
        return findChatRoom.orElseGet(() -> createChatRoom(usersId, currentTime));
    }

    private ChatRoom createChatRoom(List<Long> usersId, LocalDateTime currentTime) {
        List<ChatUser> chatUsers = new ArrayList<>();

        for (Long userId: usersId) {
            ChatUser chatUser = convertToChatUser(getUser(userId), currentTime);
            chatUsers.add(chatUser);
        }

        return new ChatRoom(chatUsers);
    }

    private ChatUser convertToChatUser(User user, LocalDateTime currentTime) {
        return new ChatUser(user.getUserId(), currentTime);
    }

    private User getUser(Long userId){
        Optional<User> findUser = userRepository.findByUserId(userId);
        if (findUser.isEmpty()) {
            throw new RuntimeException();
        } else {
            return findUser.get();
        }
    }

}
