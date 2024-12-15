package com.sda.car_rental.model;

import com.sda.car_rental.model.enums.Position;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "EMPLOYEES")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Position position; // EMPLOYEE, MANAGER

    @ManyToOne
    @JoinColumn(name = "BRANCH_ID")
    private Branch branch;
}



