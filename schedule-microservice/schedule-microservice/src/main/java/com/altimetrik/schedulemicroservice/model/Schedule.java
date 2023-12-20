package com.altimetrik.schedulemicroservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "schedule_info")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String scheduleId;
    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;
    @JsonIgnore
    private String trainNumber;
    @JsonIgnore
    private String routeId;
    @Transient
    private Train train;
    @Transient
    private Route route;
}
