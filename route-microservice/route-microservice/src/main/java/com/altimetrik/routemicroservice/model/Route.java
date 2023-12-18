package com.altimetrik.routemicroservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "route_info")
public class Route {
    @Id
    private String routeId;
    private String source;
    private String destination;
    private String totalKms;
}
