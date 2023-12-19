package com.altimetrik.schedulemicroservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "schedule_info")
public class Schedule {
    @Id
    private String scheduleId;
    private Date departureDateTime;
    private Date arrivalDateTime;
    @JsonIgnore
    private String trainNumber;
    @JsonIgnore
    private String routeId;
    @Transient
    private Train train;
    @Transient
    private Route route;
}
