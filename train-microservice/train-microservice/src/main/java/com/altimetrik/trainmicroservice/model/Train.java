package com.altimetrik.trainmicroservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "train_info")
public class Train {
    @Id
    @Column(name = "train_no")
    private String trainNumber;

    @Column(name = "train_name")
    @NotBlank(message = "Train name must not be blank ")
    private String trainName;

    @Column(name = "t_kms")
    @Positive
    private String totalKms;

    @Column(name = "ac_co")
    @NotNull(message = "ac coaches not be null")
    private String acCoaches;

    @Column(name = "t_ac_seats")
    @Positive
    private String acCoachesTotalSeats;

    @Column(name = "slp_co")
    @NotNull
    private String sleeperCoaches;

    @Column(name = "t_slp_seats")
    @Positive(message = "total seats always positive")
    private String sleeperCoachesTotalSeats;

    @Column(name = "g_co")
    @NotNull(message = "general coaches must not be null")
    private String generalCoaches;

    @Column(name = "t_g_seats")
    @Positive(message = "total seats in general coaches is always positive")
    private String generalCoachesTotalSeats;
}
