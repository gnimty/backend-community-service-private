package com.gnimty.communityapiserver.domain.chat.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.gnimty.communityapiserver.domain.chat.entity.User;
import com.gnimty.communityapiserver.global.constant.Status;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@Slf4j
@SpringBootTest
public class UserRepositoryTest {

	@AfterEach
	void afterEach() {
		userRepository.deleteAll();
	}

	@Autowired
	UserRepository userRepository;

	@Test
	void saveUser() {

		// given
		User user = new User(1234L);

		// when
		userRepository.save(user);

		// then
		Optional<User> findUser = userRepository.findByUserId(user.getUserId());
		assertThat(findUser).isNotEmpty();
	}

	@Test
	void updateUser() {

		// given
		User user = new User(2345L);
		user.updateUser("A", Status.ONLINE);
		userRepository.save(user);

		//when
		User findUser = userRepository.findByUserId(2345L).orElse(null);
		findUser.updateUser("B", Status.OFFLINE);
		userRepository.save(findUser);

		// then
		User changedUser = userRepository.findByUserId(2345L).orElse(null);
		assertThat(changedUser.getNickname()).isEqualTo("B");
		assertThat(changedUser.getStatus()).isEqualTo(Status.OFFLINE);
	}

	@Test
	void deleteUser() {
		// given
		User user = new User(2345L);
		user.updateUser("A", Status.ONLINE);
		userRepository.save(user);

		// when
		User findUser = userRepository.findByUserId(2345L).orElse(null);
		userRepository.delete(findUser);

		// then
		Optional<User> deletedUser = userRepository.findByUserId(2345L);
		assertThat(deletedUser).isEmpty();
	}

}