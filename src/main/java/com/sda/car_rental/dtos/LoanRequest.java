package com.sda.car_rental.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanRequest {
    private Long reservationId;
    private Long employeeId;
    private String comments;
}

