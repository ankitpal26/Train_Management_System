package com.altimetrik.trainmicroservice.controller;


import com.altimetrik.trainmicroservice.model.Train;
import com.altimetrik.trainmicroservice.service.TrainService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@WebMvcTest(TrainController.class)
class TrainControllerTest {

    @MockBean
    private TrainService trainService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addTrain() throws Exception {
        Train t1 = Train.builder()
                .trainNumber("123").trainName("Shatabadi Express").totalKms("700")
                .acCoaches("6").acCoachesTotalSeats("3")
                .sleeperCoaches("Available").sleeperCoachesTotalSeats("33")
                .generalCoaches("5").generalCoachesTotalSeats("33").build();
        Mockito.when(trainService.addTrainDetails(t1)).thenReturn(t1);
        mockMvc.perform(post("/train-microservice/train")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(t1)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is(201))
                .andExpect(jsonPath("$.trainNumber").value(123));

    }

    @Test
    void getAllTrainDetails() throws Exception {

        Train train = Train.builder()
                .trainNumber("1")
                .trainName("BangloreExpress")
                .acCoaches("Available")
                .acCoachesTotalSeats("200")
                .generalCoaches("Available")
                .generalCoachesTotalSeats("420")
                .build();

        Train train1 = Train.builder()
                .trainNumber("2")
                .trainName("Al-Arabian Express")
                .acCoaches("Available")
                .acCoachesTotalSeats("240")
                .generalCoaches("Available")
                .generalCoachesTotalSeats("420")
                .build();

        String jsonTrain = objectMapper.writeValueAsString(train);
        Mockito.when(trainService.getAllTrainDetails()).thenReturn(Arrays.asList(train, train1));

        mockMvc.perform(get("/train-microservice/train"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getSingleTrainDetail() throws Exception {

        Train train = Train.builder()
                .trainNumber("1")
                .trainName("Panchavati Express")
                .acCoaches("Available")
                .acCoachesTotalSeats("220")
                .generalCoaches("Available")
                .generalCoachesTotalSeats("440")
                .build();

        Mockito.when(trainService.getSingleTrainDetails(train.getTrainNumber())).thenReturn(train);

        mockMvc.perform(get("/train-microservice/train/{id}", train.getTrainNumber()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trainNumber").value(train.getTrainNumber()));

    }

    @Test
    void deleteTrainDetails() throws Exception {
        Train train = Train.builder()
                .trainNumber("1")
                .trainName("Panchavati Express")
                .acCoaches("Available")
                .acCoachesTotalSeats("220")
                .generalCoaches("Available")
                .generalCoachesTotalSeats("440")
                .build();

//        Mockito.when(trainService.deleteTrainDetails(train.getTrainNumber())).thenReturn(String.valueOf(train))
        mockMvc.perform(delete("/train-microservice/train/{id}", train.getTrainNumber()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }
}