package com.altimetrik.schedulemicroservice.service;

import com.altimetrik.schedulemicroservice.exception.RouteIdNotFoundException;
import com.altimetrik.schedulemicroservice.exception.TrainIdNotFoundException;
import com.altimetrik.schedulemicroservice.model.Schedule;
import com.altimetrik.schedulemicroservice.model.ScheduleRequest;

import java.util.List;

public interface ScheduleService {
    Schedule addNewScheduleRequest(ScheduleRequest scheduleRequest) throws TrainIdNotFoundException, RouteIdNotFoundException;

    List<Schedule> getAllScheduleTrains();

    Schedule getSingleTrainSchedule(String scheduleId);


    Schedule updateSchedule(String scheduleId, ScheduleRequest scheduleRequest);

    void deleteSchedule(String scheduleId);

}
