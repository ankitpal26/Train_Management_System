package com.altimetrik.trainmicroservice.service;

import com.altimetrik.trainmicroservice.model.Train;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TrainService {

    Train addTrainDetails(Train train);
    Train getSingleTrainDetails(String trainNumber);
    List<Train> getAllTrainDetails();
    Train updateTrainDetails(String trainNumber, Train train);
    void  deleteTrainDetails(String trainNumber);
}
