package com.gnimty.communityapiserver.domain.block.repository;

import com.gnimty.communityapiserver.domain.block.entity.Block;
import com.gnimty.communityapiserver.domain.member.entity.Member;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockRepository extends JpaRepository<Block, Long> {

	List<Block> findByBlocker(Member blocker);
	Boolean existsByBlockerAndBlocked(Member blocker, Member blocked);
}
