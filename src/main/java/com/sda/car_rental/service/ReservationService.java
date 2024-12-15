package com.sda.car_rental.service;

import com.sda.car_rental.model.Branch;
import com.sda.car_rental.model.Car;
import com.sda.car_rental.model.Customer;
import com.sda.car_rental.model.Reservation;
import com.sda.car_rental.model.enums.CarStatus;
import com.sda.car_rental.repository.BranchRepository;
import com.sda.car_rental.repository.CarRepository;
import com.sda.car_rental.repository.CustomerRepository;
import com.sda.car_rental.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class ReservationService {

    private final CarRepository carRepository;
    private final BranchRepository branchRepository;
    private final CustomerRepository customerRepository;
    private final ReservationRepository reservationRepository;
    private final RevenueService revenueService;

    public Reservation createReservation(Long carId, Long customerId, Long loanBranchId, Long returnBranchId, LocalDate fromDate, LocalDate toDate) {
        Car car = carRepository.findById(carId).orElseThrow(() -> new RuntimeException("Car not found"));
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
        Branch loanBranch = branchRepository.findById(loanBranchId).orElseThrow(() -> new RuntimeException("Loan branch not found"));
        Branch returnBranch = branchRepository.findById(returnBranchId).orElseThrow(() -> new RuntimeException("Return branch not found"));

        if (!car.getStatus().equals(CarStatus.AVAILABLE)) {
            throw new RuntimeException("Car is not available");
        }

        long days = fromDate.until(toDate).getDays();
        double rentalAmount = days * car.getRentalAmountPerDay();

        // Additional fee if pickup and return branches are different
        if (!loanBranch.equals(returnBranch)) {
            rentalAmount += 50; // Example additional fee
        }

        Reservation reservation = Reservation.builder()
                .car(car)
                .customer(customer)
                .loanBranch(loanBranch)
                .returnBranch(returnBranch)
                .dateOfBooking(LocalDate.now())
                .dateFrom(fromDate)
                .dateTo(toDate)
                .totalAmount(rentalAmount)
                .build();

        car.setStatus(CarStatus.BOOKED);
        carRepository.save(car);

        return reservationRepository.save(reservation);
    }

    public void cancelReservation(Long reservationId, boolean isLate) {
        // Fetch the reservation
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        // Update the car's status to AVAILABLE
        Car car = reservation.getCar();
        car.setStatus(CarStatus.AVAILABLE);
        carRepository.save(car);

        // Update revenue
        Branch branch = reservation.getLoanBranch();
        double refundAmount = reservation.getTotalAmount();
        if (isLate) {
            refundAmount = refundAmount * 0.8; // Deduct 20% if canceled late
        }
        revenueService.updateRevenueForCancellation(branch.getId(), reservation.getTotalAmount(), isLate);

        // Delete the reservation
        reservationRepository.delete(reservation);
    }
}


