package com.gnimty.communityapiserver.domain.chat.repository;

import com.gnimty.communityapiserver.domain.chat.entity.ChatRoom;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRoomRepository extends MongoRepository<ChatRoom, ObjectId> {

    Optional<ChatRoom> findByChatUsersUserIdIn(List<Long> userIds);

    //@Query(value = "{ '_id': { $oid: ?0 }, 'chats': { $elemMatch: { 'timeSend': { $gte: ?1 } } } }")
    @Query(value = "{ '_id': { $oid: ?0 },'chats.timeSend': { $gte: ?1 }}")
    Optional<ChatRoom> findByChatRoomIdAfterTime(ObjectId id, LocalDateTime time);

}
