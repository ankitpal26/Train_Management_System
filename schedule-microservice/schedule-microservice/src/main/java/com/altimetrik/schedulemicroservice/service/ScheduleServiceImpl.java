package com.altimetrik.schedulemicroservice.service;
import com.altimetrik.schedulemicroservice.model.Route;
import com.altimetrik.schedulemicroservice.model.Schedule;
import com.altimetrik.schedulemicroservice.model.ScheduleRequest;
import com.altimetrik.schedulemicroservice.model.Train;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.UUID;

@Service
public class ScheduleServiceImpl implements ScheduleService{
    @Autowired
    private RestTemplate restTemplate;
    private Logger  logger= LoggerFactory.getLogger(ScheduleServiceImpl.class);


    @Override
    public Schedule addNewScheduleRequest(ScheduleRequest scheduleRequest) {
        String trainNumber=scheduleRequest.getTrainNumber();
        String routeId=scheduleRequest.getRouteId();
        Train train=restTemplate.getForObject("http://localhost:8086/train-microservice/train/"+ trainNumber, Train.class);
        logger.info("{}",train);
        Route route=restTemplate.getForObject("http://localhost:8084/route-microservice/route/"+routeId, Route.class);
        logger.info("{}",route);
        Schedule schedule = new Schedule();
        schedule.setScheduleId(UUID.randomUUID().toString());
        schedule.setTrain(train);
        schedule.setRoute(route);
        schedule.setArrivalDateTime(scheduleRequest.getArrivalDateTime());
        schedule.setDepartureDateTime(scheduleRequest.getDepartureDateTime());
        return schedule;
    }
}
