package com.sda.car_rental.controller;

import com.sda.car_rental.service.StatisticsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/statistics")
@AllArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping("/popular-routes")
    public Map<String, Long> getMostPopularRoutes() {
        return statisticsService.getMostPopularRoutes();
    }

    @GetMapping("/popular-cars")
    public Map<String, Long> getMostPopularCars() {
        return statisticsService.getMostPopularCars();
    }
}

