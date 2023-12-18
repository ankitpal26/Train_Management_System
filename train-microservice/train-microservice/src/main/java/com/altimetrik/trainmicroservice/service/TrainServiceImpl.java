package com.altimetrik.trainmicroservice.service;

import com.altimetrik.trainmicroservice.exception.TrainNotFoundException;
import com.altimetrik.trainmicroservice.model.Train;
import com.altimetrik.trainmicroservice.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class TrainServiceImpl implements TrainService {

    @Autowired
    private TrainRepository trainRepository;

    @Override
    public Train addTrainDetails(Train train) {
        return trainRepository.save(train);
    }

    @Override
    public Train getSingleTrainDetails(String trainNumber) {
        return trainRepository.findById(trainNumber).orElseThrow(() -> new TrainNotFoundException("No train found at train number : " + trainNumber));
    }

    @Override
    public List<Train> getAllTrainDetails() {
        return trainRepository.findAll();
    }

    @Override
    public Train updateTrainDetails(String trainNumber, Train train) {
        Train oldTrain = trainRepository.findById(trainNumber).orElseThrow(() -> new TrainNotFoundException("No train found at train number : " + trainNumber));
        oldTrain.setTrainName(train.getTrainName());
        oldTrain.setAcCoaches(train.getAcCoaches());
        trainRepository.save(oldTrain);
        return oldTrain;
    }

    @Override
    public void deleteTrainDetails(String trainNumber) {
        trainRepository.deleteById(trainNumber);
    }
}
