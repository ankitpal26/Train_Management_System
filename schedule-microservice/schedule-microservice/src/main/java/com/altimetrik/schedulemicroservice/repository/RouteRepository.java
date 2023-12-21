package com.altimetrik.schedulemicroservice.repository;
import com.altimetrik.schedulemicroservice.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Route,String> {

}
