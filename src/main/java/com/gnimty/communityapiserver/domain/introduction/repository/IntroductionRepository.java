package com.gnimty.communityapiserver.domain.introduction.repository;

import com.gnimty.communityapiserver.domain.introduction.entity.Introduction;
import com.gnimty.communityapiserver.domain.member.entity.Member;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntroductionRepository extends JpaRepository<Introduction, Long> {

	List<Introduction> findByMember(Member member);

	Long countByMemberAndIsMain(Member member, Boolean isMain);
}
