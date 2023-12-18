package com.altimetrik.routemicroservice.repository;

import com.altimetrik.routemicroservice.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Route,String> {

}
