package com.gnimty.communityapiserver.domain.oauthinfo.repository;

import com.gnimty.communityapiserver.domain.oauthinfo.entity.OauthInfo;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OauthInfoRepository extends JpaRepository<OauthInfo, Long> {

	Optional<OauthInfo> findByEmail(String email);
}
