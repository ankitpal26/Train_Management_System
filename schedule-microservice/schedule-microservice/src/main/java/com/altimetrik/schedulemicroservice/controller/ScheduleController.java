package com.altimetrik.schedulemicroservice.controller;

import com.altimetrik.schedulemicroservice.model.Schedule;
import com.altimetrik.schedulemicroservice.model.ScheduleRequest;
import com.altimetrik.schedulemicroservice.service.ScheduleService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedule-service")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;
    @PostMapping("/newRequest")
    public ResponseEntity<Schedule>  newScheduleRequest(@RequestBody ScheduleRequest scheduleRequest){
        return  ResponseEntity.ok(scheduleService.addNewScheduleRequest(scheduleRequest));
    }
}
