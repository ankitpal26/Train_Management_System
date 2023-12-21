package com.altimetrik.schedulemicroservice.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Train {
    @Id
    private String trainNumber;
    private String trainName;
    private String totalKms;
    private String acCoaches;
    private String acCoachesTotalSeats;
    private String sleeperCoaches;
    private String sleeperCoachesTotalSeats;
    private String generalCoaches;
    private String generalCoachesTotalSeats;
}
