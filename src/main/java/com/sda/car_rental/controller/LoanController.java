package com.sda.car_rental.controller;

import com.sda.car_rental.dtos.LoanRequest;
import com.sda.car_rental.model.Loan;
import com.sda.car_rental.service.LoanService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/loans")
@AllArgsConstructor
public class
LoanController {

    private final LoanService loanService;

    @PostMapping
    public Loan rentCar(@RequestBody LoanRequest request) {
        return loanService.rentCar(request

                .getReservationId(), request.getEmployeeId(), request.getComments());
    }
}

