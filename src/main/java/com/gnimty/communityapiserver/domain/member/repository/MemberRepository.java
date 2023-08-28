package com.gnimty.communityapiserver.domain.member.repository;

import com.gnimty.communityapiserver.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

	Boolean existsByEmail(String email);
}
