package com.sda.car_rental.service;

import com.sda.car_rental.model.Employee;
import com.sda.car_rental.model.Loan;
import com.sda.car_rental.model.Reservation;
import com.sda.car_rental.model.enums.CarStatus;
import com.sda.car_rental.repository.EmployeeRepository;
import com.sda.car_rental.repository.LoanRepository;
import com.sda.car_rental.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class LoanService {

    private final ReservationRepository reservationRepository;
    private final LoanRepository loanRepository;
    private final EmployeeRepository employeeRepository;

    public Loan rentCar(Long reservationId, Long employeeId, String comments) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        if (!reservation.getCar().getStatus().equals(CarStatus.BOOKED)) {
            throw new RuntimeException("Car is not booked");
        }

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Loan loan = Loan.builder()
                .reservation(reservation)
                .employee(employee)
                .dateOfRental(LocalDate.now())
                .comments(comments)
                .build();

        reservation.getCar().setStatus(CarStatus.UNAVAILABLE); // Temporarily unavailable during rental
        return loanRepository.save(loan);
    }
}

