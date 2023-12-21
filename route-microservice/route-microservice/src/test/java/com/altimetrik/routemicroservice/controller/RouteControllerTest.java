package com.altimetrik.routemicroservice.controller;

import com.altimetrik.routemicroservice.model.Route;
import com.altimetrik.routemicroservice.service.RouteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RouteController.class)
class RouteControllerTest {

    @MockBean
    private RouteService routeServiceImpl;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addRouteDetails() throws Exception {
        Route route = Route.builder().routeId("1").source("Nashik").destination("Pune").totalKms("225").build();
        Mockito.when(routeServiceImpl.addRoute(route)).thenReturn(route);
        mockMvc.perform(MockMvcRequestBuilders.post("/route-microservice/route")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(route)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(201))
                .andExpect(MockMvcResultMatchers.jsonPath("$.routeId").exists());
    }

    @Test
    void getAllRouteDetails() throws Exception {
        Route route1 = Route.builder().routeId("1").source("Delhi").destination("Cochin").totalKms("1490").build();
        Route route2 = Route.builder().routeId("2").source("Jaipur").destination("Kolkata").totalKms("1490").build();
        Mockito.when(routeServiceImpl.getAllRoute()).thenReturn(Arrays.asList(route1,route2));

        mockMvc.perform(MockMvcRequestBuilders.get("/route-microservice/route"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }
    @Test
    void getSingleRouteDetail() throws Exception {
        String routeId = "1";
        Route route = Route.builder().routeId(routeId).source("Jaipur").destination("Darjeeling").totalKms("1490").build();
        Mockito.when(routeServiceImpl.getSingleRoute(routeId)).thenReturn(route);

        mockMvc.perform(MockMvcRequestBuilders.get("/route-microservice/route/{id}", routeId))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.routeId").value(routeId));
    }


    @Test
    void updateRoute() throws Exception {
        Route route = Route.builder().routeId("1").source("Jaipur").destination("Darjeeling").totalKms("1490").build();
        Mockito.when(routeServiceImpl.updateRoute(route)).thenReturn(route);

        mockMvc.perform(MockMvcRequestBuilders.put("/route-microservice/route")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(route)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.routeId").exists());
    }


    @Test
    void deleteTrainDetails() throws Exception {
        String routeId = "1";
        Mockito.when(routeServiceImpl.deleteRoute(routeId)).thenReturn("Route deleted successfully.");

        mockMvc.perform(MockMvcRequestBuilders.delete("/route-microservice/route/{id}", routeId))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Route deleted successfully."));

    }
}