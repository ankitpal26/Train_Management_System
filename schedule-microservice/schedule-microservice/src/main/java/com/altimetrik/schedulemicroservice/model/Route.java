package com.altimetrik.schedulemicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Route {
    private String routeId;
    private String source;
    private String destination;
    private String totalKms;
}
