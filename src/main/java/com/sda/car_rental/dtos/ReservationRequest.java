package com.sda.car_rental.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationRequest {
    private Long carId;
    private Long customerId;
    private Long loanBranchId;
    private Long returnBranchId;
    private LocalDate fromDate;
    private LocalDate toDate;
}

