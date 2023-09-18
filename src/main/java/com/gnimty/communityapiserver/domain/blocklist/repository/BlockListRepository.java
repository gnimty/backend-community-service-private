package com.gnimty.communityapiserver.domain.blocklist.repository;

import com.gnimty.communityapiserver.domain.blocklist.entity.BlockList;
import com.gnimty.communityapiserver.domain.member.entity.Member;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockListRepository extends JpaRepository<BlockList, Long> {

	List<BlockList> findByBlocker(Member blocker);
}
