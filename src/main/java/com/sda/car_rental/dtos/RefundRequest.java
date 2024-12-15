package com.sda.car_rental.dtos;

import lombok.Data;

@Data
public class RefundRequest {
    private Long reservationId;
    private Long employeeId;
    private double surcharge;
    private String comments;
    private double mileageAdded;
}

