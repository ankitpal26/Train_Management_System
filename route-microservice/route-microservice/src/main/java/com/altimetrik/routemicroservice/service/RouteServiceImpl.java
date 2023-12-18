package com.altimetrik.routemicroservice.service;

import com.altimetrik.routemicroservice.exception.RouteNotFoundException;
import com.altimetrik.routemicroservice.model.Route;
import com.altimetrik.routemicroservice.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Override
    public Route addRoute(Route route) {
        return routeRepository.save(route);
    }

    @Override
    public Route getSingleRoute(String routeId) {
        return routeRepository.findById(routeId).orElseThrow(() -> new RouteNotFoundException("No Route found on this routeId : " + routeId));
    }

    @Override
    public List<Route> getAllRoute() {
        return routeRepository.findAll();
    }

    @Override
    public Route updateRoute(String routeId, Route route) {
        Route route1 = routeRepository.findById(routeId).orElseThrow(() -> new RouteNotFoundException("No Route found on this routeId : " + routeId));
        route1.setSource(route.getSource());
        route1.setDestination(route.getDestination());
        route1.setTotalKms(route.getDestination());
        routeRepository.save(route1);
        return route1;
    }

    @Override
    public void deleteRoute(String routeId) {
        routeRepository.deleteById(routeId);
    }
}
