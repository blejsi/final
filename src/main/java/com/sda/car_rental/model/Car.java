package com.sda.car_rental.model;

import com.sda.car_rental.model.enums.CarStatus;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "CARS")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;
    private String model;
    private String bodyType;
    private int year;
    private String color;
    private double mileage;
    private double rentalAmountPerDay;

    @Enumerated(EnumType.STRING)
    private CarStatus status; // BOOKED, AVAILABLE, UNAVAILABLE

    @ManyToOne
    @JoinColumn(name = "BRANCH_ID")
    private Branch branch;
}


