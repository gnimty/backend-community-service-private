package com.gnimty.communityapiserver.domain.prefergamemode.entity;

import com.gnimty.communityapiserver.domain.base.entity.BaseEntity;
import com.gnimty.communityapiserver.domain.member.entity.Member;
import com.gnimty.communityapiserver.global.constant.GameMode;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "prefer_game_mode")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PreferGameMode extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prefer_game_mode_id", columnDefinition = "BIGINT", nullable = false, updatable = false)
	private Long id;

	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(name = "game_mode", columnDefinition = "VARCHAR(20)")
	private GameMode gameMode;

	@NotNull
	@Column(name = "is_preferred", columnDefinition = "TINYINT")
	private Boolean isPreferred;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "member_id", updatable = false)
	private Member member;
}
