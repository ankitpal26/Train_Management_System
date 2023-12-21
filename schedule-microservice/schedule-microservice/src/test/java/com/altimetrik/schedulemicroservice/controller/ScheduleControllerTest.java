package com.altimetrik.schedulemicroservice.controller;

import com.altimetrik.schedulemicroservice.model.ScheduleRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.time.LocalDateTime;
import java.util.Date;


@WebMvcTest(ScheduleController.class)
class ScheduleControllerTest {
    @MockBean
    private ScheduleController scheduleController;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void newScheduleRequest() throws Exception {
        ScheduleRequest scheduleRequest = ScheduleRequest.builder().arrivalDateTime(new Date()).departureDateTime(new Date())
                .routeId("1").trainNumber("2").build();
        String jsonTrain = objectMapper.writeValueAsString(scheduleRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/schedule-service/newRequest").contentType(MediaType.APPLICATION_JSON).content(jsonTrain)).andDo(MockMvcResultHandlers.print()) // Print the request and response for debugging
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}