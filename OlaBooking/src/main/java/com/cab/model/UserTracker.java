package com.cab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "logged_in_users")
@NoArgsConstructor
public class UserTracker {

	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    
    @Column(unique = true)
    private Integer userId;
    
}
