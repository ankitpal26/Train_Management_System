package com.altimetrik.schedulemicroservice.controller;

import com.altimetrik.schedulemicroservice.exception.RouteIdNotFoundException;
import com.altimetrik.schedulemicroservice.exception.TrainIdNotFoundException;
import com.altimetrik.schedulemicroservice.model.Schedule;
import com.altimetrik.schedulemicroservice.model.ScheduleRequest;
import com.altimetrik.schedulemicroservice.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/schedule-service")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("/newRequest")
    public ResponseEntity<Schedule> newScheduleRequest(@RequestBody ScheduleRequest scheduleRequest) throws TrainIdNotFoundException, RouteIdNotFoundException {
        return ResponseEntity.ok(scheduleService.addNewScheduleRequest(scheduleRequest));
    }

    @GetMapping("/getAllScheduleTrain")
    public ResponseEntity<List<Schedule>> getAllScheduleTrains() {
        return ResponseEntity.ok(scheduleService.getAllScheduleTrains());
    }

    @GetMapping("/getSingleSchedule/{scheduleId}")
    public ResponseEntity<Schedule> getSingleSchedule(@PathVariable String scheduleId) {
        return ResponseEntity.ok(scheduleService.getSingleTrainSchedule(scheduleId));
    }

    @PutMapping("/updateSchedule/{scheduleId}")
    public ResponseEntity<Schedule> updateSchedule(@PathVariable String scheduleId, @RequestBody ScheduleRequest scheduleRequest) {
        return ResponseEntity.ok(scheduleService.updateSchedule(scheduleId, scheduleRequest));
    }

    @DeleteMapping("/deleteSchedule/{scheduleId}")
    public ResponseEntity<String> deleteSchedule(@PathVariable String scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
        return new ResponseEntity<>("Train Schedule deleted !!", HttpStatus.OK);
    }
}
