package com.gnimty.communityapiserver.domain.blocklist.entity;

import com.gnimty.communityapiserver.domain.base.entity.BaseEntity;
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
@Table(name = "block_list")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BlockList extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "block_list_id", columnDefinition = "BIGINT", updatable = false, unique = true)
	private Long id;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "blocker_id", nullable = false)
	private Member blocker;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "blocked_id", nullable = false)
	private Member blocked;

	@Column(name = "memo", columnDefinition = "VARCHAR(100)")
	private String memo;

	@Builder
	public BlockList(
		Member blocker,
		Member blocked,
		String memo
	) {
		this.blocker = blocker;
		this.blocked = blocked;
		this.memo = memo;
	}
}
