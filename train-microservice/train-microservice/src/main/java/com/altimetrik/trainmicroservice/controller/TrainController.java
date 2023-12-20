package com.altimetrik.trainmicroservice.controller;

import com.altimetrik.trainmicroservice.model.Train;
import com.altimetrik.trainmicroservice.service.TrainService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("train-microservice/train")
public class TrainController {

    @Autowired
    private TrainService trainService;
    @PostMapping
    public ResponseEntity<Train> addTrain(@RequestBody Train train){
        return new ResponseEntity<>(trainService.addTrainDetails(train),HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Train>> getAllTrainDetails(){
        return ResponseEntity.ok(trainService.getAllTrainDetails());
    }

    @GetMapping("/{trainNumber}")
    public ResponseEntity<Train> getSingleTrainDetail(@PathVariable String trainNumber){
        return ResponseEntity.ok(trainService.getSingleTrainDetails(trainNumber));
    }

    @PutMapping("/{trainNumber}")
    public ResponseEntity<Train> deleteTrain(@PathVariable String trainNumber,@RequestBody @Valid Train train){
        return ResponseEntity.ok(trainService.updateTrainDetails(trainNumber,train));
    }

    @DeleteMapping("/{trainNumber}")
    public ResponseEntity<String> deleteTrainDetails(@PathVariable String trainNumber){
        trainService.deleteTrainDetails(trainNumber);
        return new ResponseEntity<>("Train details deleted successfully !!", HttpStatus.OK);
    }
}
