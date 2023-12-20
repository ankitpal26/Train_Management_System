package com.altimetrik.trainmicroservice.service;

import com.altimetrik.trainmicroservice.exception.TrainNotFoundException;
import com.altimetrik.trainmicroservice.model.Train;
import com.altimetrik.trainmicroservice.repository.TrainRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrainServiceImplTest {

    @Mock
    private TrainRepository trainRepository;

    @InjectMocks
    private TrainServiceImpl trainService;

    @Test
    void addTrainDetails() {
        Train train = Train.builder().trainNumber("100").trainName("BangloreExpress").acCoaches("Available").acCoachesTotalSeats("200").generalCoaches("Available").generalCoachesTotalSeats("420").build();
        when(trainRepository.save(train)).thenReturn(train);
        assertEquals(train, trainService.addTrainDetails(train));
    }

    @Test
    void getSingleTrainDetails() {
        Train train1 = Train.builder().trainNumber("1").trainName("Ramshej-Express").acCoaches("Available").acCoachesTotalSeats("100").generalCoaches("Available").generalCoachesTotalSeats("240").build();

        when(trainRepository.findById(train1.getTrainNumber())).thenReturn(Optional.of(train1));
        assertEquals("1", trainService.getSingleTrainDetails(train1.getTrainNumber()).getTrainNumber());
    }

    @Test
    void getAllTrainDetails() {
        Train train1 = Train.builder().trainNumber("1").trainName("Allahabadi-Express").acCoaches("Available").acCoachesTotalSeats("120").generalCoaches("Available").generalCoachesTotalSeats("240").build();
        Train train2 = Train.builder().trainNumber("2").trainName("Palghar-Express").acCoaches("Available").acCoachesTotalSeats("110").generalCoaches("Available").generalCoachesTotalSeats("220").build();

        when(trainRepository.findAll()).thenReturn(Arrays.asList(train1, train2));
        assertEquals(2, trainService.getAllTrainDetails().size());
    }

    @Test
    void updateTrainDetails() {
        Train train1 = Train.builder().trainNumber("1").trainName("KedarnathShahi").acCoaches("Available").acCoachesTotalSeats("1000").generalCoaches("Available").generalCoachesTotalSeats("2200").build();

        Mockito.lenient().when(trainRepository.save(train1)).thenReturn(train1);
        assertThrows(TrainNotFoundException.class, () -> {
            trainService.updateTrainDetails("1",train1);
        });
    }

    @Test
    void deleteTrainDetails() {
        Train train = Train.builder().trainNumber("100").trainName("Shatabdi Express")
                .acCoaches("Available").acCoachesTotalSeats("200").generalCoaches("Available").generalCoachesTotalSeats("420").build();
        String expectedResult = "Train with ID 100 deleted successfully";
        when(trainRepository.findById("1")).thenReturn(Optional.of(train));

        String message = trainService.deleteTrainDetails("1");

        assertEquals("Train deleted successfully", message);

        verify(trainRepository, times(1)).findById("1");
        verify(trainRepository, times(1)).deleteById("1");
//        //Mockito.when(trainRepository.deleteById("100")).thenReturn(expectedResult);
//        Mockito.lenient().doNothing().when(trainRepository).deleteById("100");
//        assertThrows(TrainNotFoundException.class, () -> {
//            trainService.deleteTrainDetails("100");
//        });
    }
}