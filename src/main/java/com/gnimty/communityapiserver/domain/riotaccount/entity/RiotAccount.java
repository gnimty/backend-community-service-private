package com.gnimty.communityapiserver.domain.riotaccount.entity;

import com.gnimty.communityapiserver.domain.member.entity.Member;
import com.gnimty.communityapiserver.global.constant.Lane;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "riot_account")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RiotAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "riot_account_id", columnDefinition = "BIGINT", updatable = false, unique = true)
	private Long id;

	@NotNull
	@Column(name = "summoner_name", columnDefinition = "VARCHAR(100)", unique = true)
	private String summonerName;

	@NotNull
	@Column(name = "is_main", columnDefinition = "TINYINT")
	private Boolean isMain;

	@NotNull
	@Column(name = "queue", columnDefinition = "VARCHAR(20)")
	private String queue;

	@NotNull
	@Column(name = "lp", columnDefinition = "BIGINT")
	private Long lp;

	@NotNull
	@Column(name = "division", columnDefinition = "TINYINT")
	private Integer division;

	@NotNull
	@Column(name = "mmr", columnDefinition = "BIGINT")
	private Long mmr;

	@Column(name = "frequent_lane_1", columnDefinition = "VARCHAR(10)")
	private Lane frequentLane1;

	@Column(name = "frequent_lane_2", columnDefinition = "VARCHAR(10)")
	private Lane frequentLane2;

	@Column(name = "frequent_champion_id_1", columnDefinition = "BIGINT")
	private Long frequentChampionId1;

	@Column(name = "frequent_champion_id_2", columnDefinition = "BIGINT")
	private Long frequentChampionId2;

	@Column(name = "frequent_champion_id_3", columnDefinition = "BIGINT")
	private Long frequentChampionId3;

	@NotNull
	@Column(name = "puuid", columnDefinition = "VARCHAR(100)")
	private String puuid;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@Builder
	public RiotAccount(
		String summonerName,
		Boolean isMain,
		String queue,
		Long lp,
		Integer division,
		Long mmr,
		Lane frequentLane1,
		Lane frequentLane2,
		Long frequentChampionId1,
		Long frequentChampionId2,
		Long frequentChampionId3,
		String puuid,
		Member member
	) {
		this.summonerName = summonerName;
		this.isMain = isMain;
		this.queue = queue;
		this.lp = lp;
		this.division = division;
		this.mmr = mmr;
		this.frequentLane1 = frequentLane1;
		this.frequentLane2 = frequentLane2;
		this.frequentChampionId1 = frequentChampionId1;
		this.frequentChampionId2 = frequentChampionId2;
		this.frequentChampionId3 = frequentChampionId3;
		this.puuid = puuid;
		this.member = member;
	}
}
