package com.altimetrik.trainmicroservice;

import com.altimetrik.trainmicroservice.model.Train;
import com.altimetrik.trainmicroservice.service.TrainService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TrainMicroserviceApplicationTests {

	@Autowired
	private TrainService trainService;
	@Test
	void TestaddTrainDetails(){
		Train train = trainService.addTrainDetails(new Train("12345", "Sabarmati", "2000", "5", "100", "10", "500", "4", "100"));
		assertEquals(new Train("12345", "Sabarmati", "2000", "5", "100", "10", "500", "4", "100"),train);
	}

	@Test
	 void TestgetSingleTrainDetails(){
		Train train = trainService.getSingleTrainDetails("12345");
		assertEquals(new  Train("12345", "Sabarmati", "2000", "5", "100", "10", "500", "4", "100"),train);

	}

	@Test
	void TestupdateTrainDetails(){
		Train train = trainService.updateTrainDetails("12345", new Train("12345", "Express", "2000", "5", "100", "10", "500", "4", "100"));
        assertEquals(new  Train("12345", "Express", "2000", "5", "100", "10", "500", "4", "100"),train);
	}

}
