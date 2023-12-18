package com.altimetrik.trainmicroservice.repository;

import com.altimetrik.trainmicroservice.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainRepository extends JpaRepository<Train,String> {

}
