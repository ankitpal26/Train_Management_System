package com.altimetrik.routemicroservice.service;

import com.altimetrik.routemicroservice.model.Route;
import com.altimetrik.routemicroservice.repository.RouteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RouteServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private RouteServiceImpl routeService;

    @Mock
    private RouteRepository routeRepository;

    @Test
    void addRoute() {
        Route route = new Route();
        route.setRouteId("1");
        route.setSource("Source");
        route.setDestination("Destination");
        when(routeRepository.save(route)).thenReturn(route);
        Route savedRoute = routeService.addRoute(route);
        assertNotNull(savedRoute);
        assertEquals(route.getRouteId(), savedRoute.getRouteId());
        assertEquals(route.getSource(), savedRoute.getSource());
        assertEquals(route.getDestination(), savedRoute.getDestination());
        verify(routeRepository, times(1)).save(route);
    }

    @Test
    void getSingleRoute() {
        Route route = new Route();
        route.setRouteId("1");
        route.setSource("Source");
        route.setDestination("Destination");

        when(routeRepository.findById("1")).thenReturn(Optional.of(route));

        Route fetchedRoute = routeService.getSingleRoute("1");

        assertNotNull(fetchedRoute);
        assertEquals(route.getRouteId(), fetchedRoute.getRouteId());
        assertEquals(route.getSource(), fetchedRoute.getSource());
        assertEquals(route.getDestination(), fetchedRoute.getDestination());

        verify(routeRepository, times(1)).findById("1");

    }

    @Test
    void getAllRoute() {
        Route route1 = new Route();
        route1.setRouteId("1");
        route1.setSource("Source1");
        route1.setDestination("Destination1");

        Route route2 = new Route();
        route2.setRouteId("2");
        route2.setSource("Source2");
        route2.setDestination("Destination2");

        when(routeRepository.findAll()).thenReturn(Arrays.asList(route1, route2));

        List<Route> routes = routeService.getAllRoute();

        assertNotNull(routes);
        assertEquals(2, routes.size());

        verify(routeRepository, times(1)).findAll();

    }

    @Test
    void updateRoute() {
        Route route = new Route();
        route.setRouteId("1");
        route.setSource("Source");
        route.setDestination("Destination");

        when(routeRepository.findById("1")).thenReturn(Optional.of(route));
        when(routeRepository.save(route)).thenReturn(route);

        Route updatedRoute = routeService.updateRoute(route);

        assertNotNull(updatedRoute);
        assertEquals(route.getRouteId(), updatedRoute.getRouteId());
        assertEquals(route.getSource(), updatedRoute.getSource());
        assertEquals(route.getDestination(), updatedRoute.getDestination());

        verify(routeRepository, times(1)).findById("1");
        verify(routeRepository, times(1)).save(route);

    }

    @Test
    void deleteRoute() {
        Route route = new Route();
        route.setRouteId("1");
        route.setSource("Source");
        route.setDestination("Destination");

        when(routeRepository.findById("1")).thenReturn(Optional.of(route));

        String message = routeService.deleteRoute("1");

        assertEquals("Route deleted successfully", message);

        verify(routeRepository, times(1)).findById("1");
        verify(routeRepository, times(1)).deleteById("1");
    }
}