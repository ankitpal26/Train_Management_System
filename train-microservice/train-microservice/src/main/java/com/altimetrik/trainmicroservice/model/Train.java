package com.altimetrik.trainmicroservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "train_info")
public class Train {
    @Id
    @Column(name = "train_no")
    private String trainNumber;
    @Column(name = "train_name")
    private String trainName;
    @Column(name = "t_kms")
    private String totalKms;
    @Column(name = "ac_co")
    private String acCoaches;
    @Column(name = "t_ac_seats")
    private String acCoachesTotalSeats;
    @Column(name = "slp_co")
    private String sleeperCoaches;
    @Column(name = "t_slp_seats")
    private String sleeperCoachesTotalSeats;
    @Column(name = "g_co")
    private String generalCoaches;
    @Column(name = "t_g_seats")
    private String generalCoachesTotalSeats;
}
