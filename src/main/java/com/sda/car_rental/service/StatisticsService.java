package com.sda.car_rental.service;

import com.sda.car_rental.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StatisticsService {

    private final ReservationRepository reservationRepository;

    public Map<String, Long> getMostPopularRoutes() {
        return reservationRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        reservation -> reservation.getLoanBranch().getCity() + " -> " + reservation.getReturnBranch().getCity(),
                        Collectors.counting()
                ));
    }

    public Map<String, Long> getMostPopularCars() {
        return reservationRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        reservation -> reservation.getCar().getBrand() + " " + reservation.getCar().getModel(),
                        Collectors.counting()
                ));
    }
}

