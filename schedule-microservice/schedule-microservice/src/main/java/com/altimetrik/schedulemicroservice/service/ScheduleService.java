package com.altimetrik.schedulemicroservice.service;

import com.altimetrik.schedulemicroservice.model.Schedule;
import com.altimetrik.schedulemicroservice.model.ScheduleRequest;

import java.util.List;

public interface ScheduleService {
    Schedule addNewScheduleRequest(ScheduleRequest scheduleRequest);

    List<Schedule> getAllScheduleTrains();

    Schedule getSingleTrainSchedule(String scheduleId);

    Schedule getScheduleByTrainNumber(String trainNumber);

    Schedule getScheduleByRouteId(String routeId);

    Schedule updateSchedule(String scheduleId, ScheduleRequest scheduleRequest);

    void deleteSchedule(String scheduleId);

}
