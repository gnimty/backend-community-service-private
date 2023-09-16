package com.gnimty.communityapiserver.global.constant;

public enum Tier {

	CHALLENGER("challenger"),
	GRANDMASTER("grandmaster"),
	MASTER("master"),
	DIAMOND("diamond"),
	EMERALD("emerald"),
	PLATINUM("platinum"),
	GOLD("gold"),
	SILVER("silver"),
	BRONZE("bronze"),
	IRON("iron"),
	NULL("null");

	private final String tier;

	Tier(String tier) {
		this.tier = tier;
	}
}
