package com.gnimty.communityapiserver.domain.chat.repository;

import com.gnimty.communityapiserver.domain.chat.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {

    @Query("{ 'userId' : ?0 }")
    Optional<User> findByUserId(Long id);

}
