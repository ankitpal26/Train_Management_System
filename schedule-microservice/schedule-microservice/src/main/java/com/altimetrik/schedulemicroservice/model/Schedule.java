package com.altimetrik.schedulemicroservice.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "schedule_info")
public class Schedule {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String scheduleId;
    @Temporal(TemporalType.TIMESTAMP)
    private Date departureDateTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date arrivalDateTime;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "train_number")
    private Train train;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "route_id")
    private Route route;
}
