package com.altimetrik.trainmicroservice.service;

import com.altimetrik.trainmicroservice.model.Train;
import java.util.List;

public interface TrainService {

    Train addTrainDetails(Train train);
    Train getSingleTrainDetails(String trainNumber);
    List<Train> getAllTrainDetails();
    Train updateTrainDetails(String trainNumber, Train train);
    String  deleteTrainDetails(String trainNumber);
}
