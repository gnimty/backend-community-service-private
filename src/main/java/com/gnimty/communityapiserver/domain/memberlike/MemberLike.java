package com.gnimty.communityapiserver.domain.memberlike;

import com.gnimty.communityapiserver.domain.member.entity.Member;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member_like")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MemberLike {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_like_id", columnDefinition = "BIGINT", updatable = false, unique = true)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "liker_member_id", nullable = false)
	private Member likerMemberId;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "liked_member_id", nullable = false)
	private Member likedMemberId;

	@Builder
	public MemberLike(
		Member likerMemberId,
		Member likedMemberId
	) {
		this.likerMemberId = likerMemberId;
		this.likedMemberId = likedMemberId;
	}
}
