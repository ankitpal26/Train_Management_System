package com.altimetrik.schedulemicroservice.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Route {
    @Id
    private String routeId;
    private String source;
    private String destination;
    private String totalKms;
    private Schedule schedule;
}
