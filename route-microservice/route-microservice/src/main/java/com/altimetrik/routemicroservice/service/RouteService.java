package com.altimetrik.routemicroservice.service;

import com.altimetrik.routemicroservice.model.Route;

import java.util.List;

public interface RouteService {

    Route addRoute(Route route);
    Route getSingleRoute(String routeId);
    List<Route> getAllRoute();
    Route updateRoute(String routeId, Route route);
    void deleteRoute(String routeId);

}
