package com.sda.car_rental.service;

import com.sda.car_rental.model.Car;
import com.sda.car_rental.model.Employee;
import com.sda.car_rental.model.Refund;
import com.sda.car_rental.model.Reservation;
import com.sda.car_rental.model.enums.CarStatus;
import com.sda.car_rental.repository.CarRepository;
import com.sda.car_rental.repository.EmployeeRepository;
import com.sda.car_rental.repository.RefundRepository;
import com.sda.car_rental.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class RefundService {

    private final ReservationRepository reservationRepository;
    private final RefundRepository refundRepository;
    private final CarRepository carRepository;
    private final EmployeeRepository employeeRepository;

    public Refund returnCar(Long reservationId, Long employeeId, double surcharge, String comments, double mileageAdded) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Car car = reservation.getCar();

        // Update car details
        car.setMileage(car.getMileage() + mileageAdded);
        car.setStatus(CarStatus.AVAILABLE); // Set back to available

        Refund refund = Refund.builder()
                .reservation(reservation)
                .employee(employee)
                .dateOfReturn(LocalDate.now())
                .surcharge(surcharge)
                .comments(comments)
                .build();

        carRepository.save(car);
        return refundRepository.save(refund);
    }
}

