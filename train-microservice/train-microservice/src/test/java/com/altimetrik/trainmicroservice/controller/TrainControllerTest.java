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
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
        Train train = Train.builder().trainName("1").trainName("Rajadhani Express").generalCoaches("Available")
                .generalCoachesTotalSeats("400").acCoaches("Available")
                .acCoachesTotalSeats("200").sleeperCoaches("Available").
                sleeperCoachesTotalSeats("500").totalKms("1000").build();
        Mockito.when(trainService.addTrainDetails(train)).thenReturn(train);
        mockMvc.perform(MockMvcRequestBuilders.post("/train-microservice/train")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(train)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.trainNumber").exists());

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
    void deleteTrain() throws Exception {
        Train train = Train.builder()
                .trainNumber("1")
                .trainName("Panchavati Express")
                .acCoaches("Available")
                .acCoachesTotalSeats("220")
                .generalCoaches("Available")
                .generalCoachesTotalSeats("440")
                .build();

        Mockito.when(trainService.updateTrainDetails(train.getTrainNumber(),train)).thenReturn(train);

        mockMvc.perform((RequestBuilder) put("/train-microservice/train/{id}", train.getTrainNumber())
                        .contentType(MediaType.APPLICATION_JSON))
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