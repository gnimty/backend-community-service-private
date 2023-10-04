package com.gnimty.communityapiserver.domain.memberlike.repository;

import com.gnimty.communityapiserver.domain.member.entity.Member;
import com.gnimty.communityapiserver.domain.memberlike.entity.MemberLike;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberLikeRepository extends JpaRepository<MemberLike, Long> {

	Optional<MemberLike> findBySourceMemberAndTargetMember(Member sourceMember, Member targetMember);
	Boolean existsBySourceMemberAndTargetMember(Member sourceMember, Member targetMember);
}
