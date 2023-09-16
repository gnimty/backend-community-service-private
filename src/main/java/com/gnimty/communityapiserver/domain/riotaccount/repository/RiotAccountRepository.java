package com.gnimty.communityapiserver.domain.riotaccount.repository;

import com.gnimty.communityapiserver.domain.member.entity.Member;
import com.gnimty.communityapiserver.domain.riotaccount.entity.RiotAccount;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RiotAccountRepository extends JpaRepository<RiotAccount, Long> {

	Boolean existsByPuuid(String puuid);

	Boolean existsByMember(Member member);

	List<RiotAccount> findByMember(Member member);

	Optional<RiotAccount> findByMemberAndIsMain(Member member, Boolean isMain);
}
