package com.gnimty.communityapiserver.domain.championcomments.repository;

import static com.gnimty.communityapiserver.domain.block.entity.QBlock.block;
import static com.gnimty.communityapiserver.domain.championcomments.entity.QChampionComments.championComments;
import static com.gnimty.communityapiserver.domain.member.entity.QMember.member;
import static com.gnimty.communityapiserver.domain.riotaccount.entity.QRiotAccount.riotAccount;

import com.gnimty.communityapiserver.domain.championcomments.service.dto.response.ChampionCommentsEntry;
import com.gnimty.communityapiserver.domain.member.entity.Member;
import com.gnimty.communityapiserver.global.auth.MemberThreadLocal;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ChampionCommentsQueryRepository {

	private final JPAQueryFactory queryFactory;

	public List<ChampionCommentsEntry> findByChampionId(Long championId) {
		Member me = MemberThreadLocal.get();
		return queryFactory.select(
				getProjections(me)
			)
			.from(championComments)
			.join(championComments.member, member)
			.leftJoin(block).on(block.blocked.eq(championComments.member))
			.join(riotAccount).on(riotAccount.member.eq(member))
			.where(championComments.championId.eq(championId))
			.orderBy(championComments.createdAt.asc())
			.fetch();
	}

	private QBean<ChampionCommentsEntry> getProjections(Member me) {
		if (me == null) {
			return Projections.bean(
				ChampionCommentsEntry.class,
				championComments.id.as("id"),
				championComments.lane.as("lane"),
				championComments.opponentChampionId.as("opponentChampionId"),
				championComments.depth.as("depth"),
				championComments.mentionedMemberId.as("mentionedMemberId"),
				championComments.contents.as("contents"),
				championComments.commentsType.as("commentsType"),
				championComments.upCount.as("upCount"),
				championComments.downCount.as("downCount"),
				championComments.version.as("version"),
				championComments.createdAt.as("createdAt"),
				championComments.updatedAt.as("updatedAt"),
				championComments.deleted.as("deleted"),
				championComments.deleted.isNotNull().as("blocked"),
				championComments.member.id.as("memberId"),
				riotAccount.summonerName.as("summonerName")
			);
		}
		return Projections.bean(
			ChampionCommentsEntry.class,
			championComments.id.as("id"),
			championComments.lane.as("lane"),
			championComments.opponentChampionId.as("opponentChampionId"),
			championComments.depth.as("depth"),
			championComments.mentionedMemberId.as("mentionedMemberId"),
			championComments.contents.as("contents"),
			championComments.commentsType.as("commentsType"),
			championComments.upCount.as("upCount"),
			championComments.downCount.as("downCount"),
			championComments.version.as("version"),
			championComments.createdAt.as("createdAt"),
			championComments.updatedAt.as("updatedAt"),
			championComments.deleted.as("deleted"),
			block.blocker.eq(me).as("blocked"),
			championComments.member.id.as("memberId"),
			riotAccount.summonerName.as("summonerName")
		);
	}
}
