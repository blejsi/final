package com.sda.car_rental.controller;

import com.sda.car_rental.dtos.ReservationRequest;
import com.sda.car_rental.model.Reservation;
import com.sda.car_rental.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")
@AllArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public Reservation createReservation(@RequestBody ReservationRequest request) {
        return reservationService.createReservation(
                request.getCarId(),
                request.getCustomerId(),
                request.getLoanBranchId(),
                request.getReturnBranchId(),
                request.getFromDate(),
                request.getToDate()
        );
    }

    @DeleteMapping("/{reservationId}")
    public String cancelReservation(@PathVariable Long reservationId, @RequestParam boolean isLate) {
        reservationService.cancelReservation(reservationId, isLate);
        return "Reservation canceled successfully.";
    }

}


