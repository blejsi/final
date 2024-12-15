package com.sda.car_rental.controller;

import com.sda.car_rental.dtos.RefundRequest;
import com.sda.car_rental.model.Refund;
import com.sda.car_rental.service.RefundService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/refunds")
@AllArgsConstructor
public class RefundController {

    private final RefundService refundService;

    @PostMapping
    public Refund returnCar(@RequestBody RefundRequest request) {
        return refundService.returnCar(
                request.getReservationId(),
                request.getEmployeeId(),
                request.getSurcharge(),
                request.getComments(),
                request.getMileageAdded()
        );
    }
}

