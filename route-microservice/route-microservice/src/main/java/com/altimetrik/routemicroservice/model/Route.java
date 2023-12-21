package com.altimetrik.routemicroservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "source must not be null")
    private String source;
    @NotNull(message = "destination is not null")
    private String destination;
    @NotNull(message = "total kms must not be null")
    private String totalKms;
}
