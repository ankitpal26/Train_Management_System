package com.altimetrik.schedulemicroservice.repository;

import com.altimetrik.schedulemicroservice.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,String> {

}
