package com.gnimty.communityapiserver.domain.prefergamemode.repository;

import com.gnimty.communityapiserver.domain.member.entity.Member;
import com.gnimty.communityapiserver.domain.prefergamemode.entity.PreferGameMode;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreferGameModeRepository extends JpaRepository<PreferGameMode, Long> {

	List<PreferGameMode> findByMember(Member member);

	void deleteByMember(Member member);
}
