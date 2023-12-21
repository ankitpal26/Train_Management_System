package com.altimetrik.schedulemicroservice.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "route_info")
public class Route {
    @Id
    private String routeId;
    private String source;
    private String destination;
    private String totalKms;
    @JsonIgnore
    @OneToOne(mappedBy = "route")
    private Schedule schedule;
}
