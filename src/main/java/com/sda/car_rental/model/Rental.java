package com.sda.car_rental.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "RENTALS")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String domain;
    private String contactAddress;
    private String owner;
    private String logotype;

    @OneToMany(mappedBy = "rental", cascade = CascadeType.ALL)
    private List<Branch> branches;
}

