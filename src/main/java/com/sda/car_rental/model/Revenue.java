package com.sda.car_rental.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "REVENUE")
public class Revenue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double totalRevenue;
    private double approvedRevenue; // Revenue that is finalized
    private double pendingRevenue; // Refunds or unprocessed amounts

    @ManyToOne
    @JoinColumn(name = "BRANCH_ID")
    private Branch branch;
}

