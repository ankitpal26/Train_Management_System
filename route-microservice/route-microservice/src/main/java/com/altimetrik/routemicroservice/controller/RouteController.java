package com.altimetrik.routemicroservice.controller;
import com.altimetrik.routemicroservice.model.Route;
import com.altimetrik.routemicroservice.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("route-microservice/route")
public class RouteController {
    @Autowired
    private RouteService routeService;
    @PostMapping
    public ResponseEntity<Route> addRouteDetails(@RequestBody Route route){
        return new ResponseEntity<>(routeService.addRoute(route), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Route>> getAllRouteDetails(){
        return ResponseEntity.ok(routeService.getAllRoute());
    }

    @GetMapping("/{routeId}")
    public ResponseEntity<Route> getSingleSingleDetail(@PathVariable String routeId){
        return ResponseEntity.ok(routeService.getSingleRoute(routeId));
    }

    @PutMapping("/{routeId}")
    public ResponseEntity<Route> updateRoute(@PathVariable String routeId,@RequestBody Route route){
        return ResponseEntity.ok(routeService.updateRoute(routeId,route));
    }

    @DeleteMapping("/{routeId}")
    public ResponseEntity<String> deleteTrainDetails(@PathVariable String routeId){
        routeService.deleteRoute(routeId);
        return new ResponseEntity<>("Route  deleted successfully !!", HttpStatus.OK);
    }
}
