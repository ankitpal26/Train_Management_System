package com.altimetrik.schedulemicroservice.service;

import com.altimetrik.schedulemicroservice.model.Route;
import com.altimetrik.schedulemicroservice.model.Schedule;
import com.altimetrik.schedulemicroservice.model.ScheduleRequest;
import com.altimetrik.schedulemicroservice.model.Train;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private RestTemplate restTemplate;
    private Logger logger = LoggerFactory.getLogger(ScheduleServiceImpl.class);


    @Override
    public Schedule addNewScheduleRequest(ScheduleRequest scheduleRequest) {
        String trainNumber = scheduleRequest.getTrainNumber();
        String routeId = scheduleRequest.getRouteId();
        //calling train-microservice
        ResponseEntity<Train> trainResponseEntity = restTemplate.getForEntity("http://TRAIN-SERVICE/train-microservice/train/" + trainNumber, Train.class);
        Train train = trainResponseEntity.getBody();
        logger.info("train-microservice response status code : {}", trainResponseEntity.getStatusCode());

        //calling route-microservice to restTemplate
        ResponseEntity<Route> routeResponseEntity = restTemplate.getForEntity("http://ROUTE-SERVICE/route-microservice/route/" + routeId, Route.class);
        Route route = routeResponseEntity.getBody();
        logger.info("route-microservice  response status code : {}",routeResponseEntity.getStatusCode());
        //returns Schedule object with help of class builder
        return Schedule.builder().scheduleId(UUID.randomUUID().toString())
                .arrivalDateTime(scheduleRequest.getArrivalDateTime())
                .departureDateTime(scheduleRequest.getDepartureDateTime())
                .route(route)
                .train(train).build();
    }
}
