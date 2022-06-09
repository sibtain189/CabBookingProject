package com.cab.model;

import java.util.Locale;


import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@NoArgsConstructor
public class Cab {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cabId;

    @NotNull
    private CabType cabType;

    private Double perKmRate;

    private Integer sittingCapcity;
    
    private Boolean available = true;

    @NotNull
    private String registrationNumber;
    
    
}
