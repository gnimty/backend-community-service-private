package com.gnimty.communityapiserver.domain.chat.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.gnimty.communityapiserver.domain.chat.entity.Chat;
import com.gnimty.communityapiserver.domain.chat.entity.ChatRoom;
import com.gnimty.communityapiserver.domain.chat.entity.ChatUser;
import com.gnimty.communityapiserver.domain.chat.entity.User;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
public class ChatRoomRepositoryTest {

	@AfterEach
	void afterEach() {
		chatRoomRepository.deleteAll();
		userRepository.deleteAll();
	}

	@Autowired
	UserRepository userRepository;
	@Autowired
	ChatRoomRepository chatRoomRepository;

	@Test
	ChatRoom saveChatRoom() {

		// given
		User userA = new User(1234L);
		userRepository.save(userA);

		User userB = new User(5678L);
		userRepository.save(userB);

		List<ChatUser> chatUsers = new ArrayList<>();
		ChatUser chatUserA = convertToChatUser(userA);
		ChatUser chatUserB = convertToChatUser(userB);
		chatUsers.add(chatUserA);
		chatUsers.add(chatUserB);

		ChatRoom chatRoom = new ChatRoom(chatUsers);

		// when
		chatRoomRepository.save(chatRoom);

		// then
		List<Long> usersId = new ArrayList<>();
		usersId.add(userA.getUserId());
		usersId.add(userB.getUserId());
		Optional<ChatRoom> findChatRoom = chatRoomRepository.findByChatUsersUserIdIn(usersId);

		assertThat(findChatRoom).isNotNull();
		assertThat(findChatRoom.get().getId()).isEqualTo(chatRoom.getId());

		return chatRoom;
	}

	@Test
	@Transactional
	void deleteChatRoom() {

		// given
		ChatRoom chatRoom = new ChatRoom();
		ChatRoom saveChatRoom = chatRoomRepository.save(chatRoom);

		Optional<ChatRoom> findChatRoom = chatRoomRepository.findById(saveChatRoom.getId());
		assertThat(findChatRoom).isNotEmpty();

		// when
		chatRoomRepository.deleteById(findChatRoom.get().getId());

		// then
		Optional<ChatRoom> deletedChatRoom = chatRoomRepository.findById(saveChatRoom.getId());
		assertThat(deletedChatRoom).isEmpty();
	}

	@Test
	ChatRoom addChat() {

		// given
		ChatRoom chatRoom = saveChatRoom();

		// when
		List<ChatUser> chatUsers = chatRoom.getChatUsers();
		for (ChatUser chatUser : chatUsers) {
			Long userId = chatUser.getUserId();
			chatRoom.addChat(new Chat(userId, "안녕하세요.", LocalDateTime.now()));
		}
		chatRoomRepository.save(chatRoom);

		// then
		ChatRoom findChatRoom = chatRoomRepository.findById(chatRoom.getId()).orElse(null);
		List<Chat> chats = findChatRoom.getChats();
		assertThat(chats.size()).isEqualTo(2);

		return findChatRoom;
	}
	private ChatUser convertToChatUser(User user) {
		Long userId = user.getUserId();
		return new ChatUser(userId, LocalDateTime.now());
	}

}