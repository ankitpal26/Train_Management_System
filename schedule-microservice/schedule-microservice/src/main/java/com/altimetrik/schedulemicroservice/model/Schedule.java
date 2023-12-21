package com.altimetrik.schedulemicroservice.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String scheduleId;
    @Temporal(TemporalType.TIMESTAMP)
    private Date departureDateTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date arrivalDateTime;
    @ManyToOne
    @JoinColumn(name = "trainNumber")
    private Train train;
    @ManyToOne
    @JoinColumn(name = "routeId")
    private Route route;
}
