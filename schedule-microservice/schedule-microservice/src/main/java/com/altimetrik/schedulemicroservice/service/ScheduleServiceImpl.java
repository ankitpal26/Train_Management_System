package com.altimetrik.schedulemicroservice.service;

import com.altimetrik.schedulemicroservice.exception.NoScheduleFound;
import com.altimetrik.schedulemicroservice.exception.RouteIdNotFoundException;
import com.altimetrik.schedulemicroservice.exception.TrainIdNotFoundException;
import com.altimetrik.schedulemicroservice.model.Route;
import com.altimetrik.schedulemicroservice.model.Schedule;
import com.altimetrik.schedulemicroservice.model.ScheduleRequest;
import com.altimetrik.schedulemicroservice.model.Train;
import com.altimetrik.schedulemicroservice.repository.RouteRepository;
import com.altimetrik.schedulemicroservice.repository.ScheduleRepository;
import com.altimetrik.schedulemicroservice.repository.TrainRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TrainRepository trainRepository;
    @Autowired
    private RouteRepository routeRepository;


    @Override
    public Schedule addNewScheduleRequest(ScheduleRequest scheduleRequest) throws TrainIdNotFoundException, RouteIdNotFoundException {
        //calling train-microservice
        ResponseEntity<Train> trainResponseEntity = restTemplate.getForEntity("http://TRAIN-SERVICE/train-microservice/train/" + scheduleRequest.getTrainNumber(), Train.class);
        Train train = trainResponseEntity.getBody();
        if (train==null){
            throw new TrainIdNotFoundException("TrainNumber Not Found");
        }
        log.info("train-microservice response status code : {}", trainResponseEntity.getStatusCode());
        //calling route-microservice to restTemplate
        ResponseEntity<Route> routeResponseEntity = restTemplate.getForEntity("http://ROUTE-SERVICE/route-microservice/route/" + scheduleRequest.getRouteId(), Route.class);
        Route route = routeResponseEntity.getBody();
        if (route==null){
            throw new RouteIdNotFoundException("TrainNumber Not Found");
        }
        log.info("route-microservice  response status code : {}", routeResponseEntity.getStatusCode());
        //returns Schedule object with help of class builder

        Schedule schedule = Schedule.builder()
                .departureDateTime(scheduleRequest.getDepartureDateTime())
                .arrivalDateTime(scheduleRequest.getArrivalDateTime())
                .train(train)
                .route(route)
                .build();
        scheduleRepository.save(schedule); //save the schedule in database
        trainRepository.save(train);
        routeRepository.save(route);
        return schedule;
    }

    @Override
    public List<Schedule> getAllScheduleTrains() {
        return scheduleRepository.findAll();
    }

    @Override
    public Schedule getSingleTrainSchedule(String scheduleId) {
        return scheduleRepository.findById(scheduleId).orElseThrow(() -> new NoScheduleFound("No Schedule Found are this schedule id : " + scheduleId));
    }

    @Override
    public Schedule updateSchedule(String scheduleId, ScheduleRequest scheduleRequest) {
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(scheduleId);
        Schedule schedule;
        if (optionalSchedule.isPresent()) {
            schedule = optionalSchedule.get();
            schedule.setDepartureDateTime(scheduleRequest.getDepartureDateTime());
            schedule.setArrivalDateTime(scheduleRequest.getArrivalDateTime());
            scheduleRepository.save(schedule);
        } else {
            throw new NoScheduleFound("No schedule found !!");
        }
        return schedule;
    }

    @Override
    public void deleteSchedule(String scheduleId) {
        scheduleRepository.deleteById(scheduleId);
    }
}
