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
@Table(name = "BRANCHES")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;
    private String city;

    @ManyToOne
    @JoinColumn(name = "RENTAL_ID")
    private Rental rental;

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL)
    private List<Employee> employees;

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL)
    private List<Car> cars;
}
