package com.altimetrik.schedulemicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Schedule {
    private String scheduleId;
    private Date departureDateTime;
    private Date arrivalDateTime;
    private Train train;
    private Route route;
}
