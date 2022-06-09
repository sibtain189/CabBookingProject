package com.cab.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@Entity
public class TripBooking {
	

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer tripId;

    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @NotNull
    private Customer customer;
    

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @NotNull
    private Driver driver;
    
    
    @NotNull
    private String fromLocation;
    
    
    @NotNull
    private String toLocation;
    
    
    private LocalDate fromDateTime;
    
    
//    private LocalDateTime toDateTime;
    

//    private Boolean status;
    @Enumerated
    private TripStatus status;
    
    
    @NotNull
    @Min(1)
    private Double distanceInKm;
    
    
    @NotNull
    private Double bill;
}
