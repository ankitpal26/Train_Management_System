package com.altimetrik.schedulemicroservice.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleRequest {
    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;
    private String trainNumber;
    private String routeId;
}
