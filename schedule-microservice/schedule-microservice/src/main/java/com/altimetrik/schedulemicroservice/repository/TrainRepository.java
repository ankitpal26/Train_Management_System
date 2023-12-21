package com.altimetrik.schedulemicroservice.repository;

import com.altimetrik.schedulemicroservice.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainRepository extends JpaRepository<Train,String> {
}
