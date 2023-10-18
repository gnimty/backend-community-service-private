package com.gnimty.communityapiserver.domain.chat.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.gnimty.communityapiserver.domain.chat.entity.Chat;
import com.gnimty.communityapiserver.domain.chat.entity.ChatRoom;
import com.gnimty.communityapiserver.domain.chat.entity.Status;
import com.gnimty.communityapiserver.domain.chat.entity.User;
import java.time.LocalDateTime;
import java.util.*;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
public class ChatRoomRepositoryTest {
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChatRoomRepository chatRoomRepository;

    void clear(){
        chatRoomRepository.deleteAll();
        userRepository.deleteAll();
        chatRepository.deleteAll();
    }

    @Test
    void 유저두명_생성해서_chatRoom에넣기_테스트(){

        clear();

        User user1 = userRepository.save(new User(null, 1L, 3L, "diamond", 3, "so1omon", "ON"));
        User user2 = userRepository.save(new User(null, 2L, 4L, "emerald", 3, "solmin23", "ON"));

        Set<ChatRoom.Participant> participants = new HashSet<>();
        participants.add(new ChatRoom.Participant(user1, new Date(), Status.UNBLOCK));
        participants.add(new ChatRoom.Participant(user2, null, Status.UNBLOCK));


        ChatRoom save = chatRoomRepository.save(new ChatRoom(null, 1L, participants, new Date()));
        System.out.println("save = " + save);
    }

    @Test
    void 채팅방_여러개_만들기(){
        clear();
        List<User> users = new ArrayList<>();
        List<ChatRoom> chatRooms = new ArrayList<>();
//
        for(Integer i=0;i<20;i++){
            users.add(userRepository.save(new User(null, i.longValue(), 3L, "diamond", 3, "so1omon", "ON")));
        }

        for(Integer i=1;i<10;i++){
            Set<ChatRoom.Participant> participants = new HashSet<>();
            participants.add(new ChatRoom.Participant(users.get(0), null, Status.UNBLOCK));
            participants.add(new ChatRoom.Participant(users.get(i), null, Status.UNBLOCK));
            chatRooms.add(chatRoomRepository.save(new ChatRoom(null, 1L, participants, new Date())));
        }

        for(Integer i=11;i<20;i++){
            Set<ChatRoom.Participant> participants = new HashSet<>();
            participants.add(new ChatRoom.Participant(users.get(10), null, Status.UNBLOCK));
            participants.add(new ChatRoom.Participant(users.get(i), null, Status.UNBLOCK));
            chatRooms.add(chatRoomRepository.save(new ChatRoom(null, 1L, participants, new Date())));
        }


    }

    @Test
    void 채팅방_조회(){

        채팅방_여러개_만들기();
        User user = userRepository.findByActualUserId(0L).get();

        List<ChatRoom> results = chatRoomRepository.findByUser(user);

        for (ChatRoom result : results) {
            System.out.println("result = " + result);
        }

        Assertions.assertEquals(results.size(), 9);
    }
//	@AfterEach
//	void afterEach() {
//		chatRoomRepository.deleteAll();
//		userRepository.deleteAll();
//	}
//
//	@Autowired
//	UserRepository userRepository;
//	@Autowired
//	ChatRoomRepository chatRoomRepository;
//
//	@Test
//	ChatRoom saveChatRoom() {
//
//		// given
//		User userA = new User(1234L);
//		userRepository.save(userA);
//
//		User userB = new User(5678L);
//		userRepository.save(userB);
//
//		List<ChatUser> chatUsers = new ArrayList<>();
//		ChatUser chatUserA = convertToChatUser(userA);
//		ChatUser chatUserB = convertToChatUser(userB);
//		chatUsers.add(chatUserA);
//		chatUsers.add(chatUserB);
//
//		ChatRoom chatRoom = new ChatRoom(chatUsers);
//
//		// when
//		chatRoomRepository.save(chatRoom);
//
//		// then
//		List<Long> usersId = new ArrayList<>();
//		usersId.add(userA.getUserId());
//		usersId.add(userB.getUserId());
//		Optional<ChatRoom> findChatRoom = chatRoomRepository.findByChatUsersUserIdIn(usersId);
//
//		assertThat(findChatRoom).isNotNull();
//		assertThat(findChatRoom.get().getId()).isEqualTo(chatRoom.getId());
//
//		return chatRoom;
//	}
//
//	@Test
//	@Transactional
//	void deleteChatRoom() {
//
//		// given
//		ChatRoom chatRoom = new ChatRoom();
//		ChatRoom saveChatRoom = chatRoomRepository.save(chatRoom);
//
//		Optional<ChatRoom> findChatRoom = chatRoomRepository.findById(saveChatRoom.getId());
//		assertThat(findChatRoom).isNotEmpty();
//
//		// when
//		chatRoomRepository.deleteById(findChatRoom.get().getId());
//
//		// then
//		Optional<ChatRoom> deletedChatRoom = chatRoomRepository.findById(saveChatRoom.getId());
//		assertThat(deletedChatRoom).isEmpty();
//	}
//
//	@Test
//	ChatRoom addChat() {
//
//		// given
//		ChatRoom chatRoom = saveChatRoom();
//
//		// when
//		List<ChatUser> chatUsers = chatRoom.getChatUsers();
//		for (ChatUser chatUser : chatUsers) {
//			Long userId = chatUser.getUserId();
//			chatRoom.addChat(new Chat(userId, "안녕하세요.", LocalDateTime.now()));
//		}
//		chatRoomRepository.save(chatRoom);
//
//		// then
//		ChatRoom findChatRoom = chatRoomRepository.findById(chatRoom.getId()).orElse(null);
//		List<Chat> chats = findChatRoom.getChats();
//		assertThat(chats.size()).isEqualTo(2);
//
//		return findChatRoom;
//	}
//	private ChatUser convertToChatUser(User user) {
//		Long userId = user.getUserId();
//		return new ChatUser(userId, LocalDateTime.now());
//	}

}