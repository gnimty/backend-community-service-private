package com.gnimty.communityapiserver.domain.schedule.repository;

import com.gnimty.communityapiserver.domain.member.entity.Member;
import com.gnimty.communityapiserver.domain.schedule.entity.Schedule;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

	List<Schedule> findByMember(Member member);
	void deleteByMember(Member member);
}
