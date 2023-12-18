package com.altimetrik.schedulemicroservice.service;

import com.altimetrik.schedulemicroservice.model.Schedule;
import com.altimetrik.schedulemicroservice.model.ScheduleRequest;

public interface ScheduleService {
    Schedule addNewScheduleRequest(ScheduleRequest scheduleRequest);
}
