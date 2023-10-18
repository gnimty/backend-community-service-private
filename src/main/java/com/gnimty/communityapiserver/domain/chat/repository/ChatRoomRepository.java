package com.gnimty.communityapiserver.domain.chat.repository;

import com.gnimty.communityapiserver.domain.chat.entity.ChatRoom;
import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface ChatRoomRepository  extends MongoRepository<ChatRoom, String>, ChatRoomRepositoryCustom{

}
