package com.altimetrik.schedulemicroservice.model;

import lombok.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleRequest {
    private Date departureDateTime;
    private Date arrivalDateTime;
    private String trainNumber;
    private String routeId;
}
