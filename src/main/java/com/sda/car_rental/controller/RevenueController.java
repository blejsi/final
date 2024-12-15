package com.sda.car_rental.controller;

import com.sda.car_rental.model.Revenue;
import com.sda.car_rental.service.RevenueService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/revenue")
@AllArgsConstructor
public class RevenueController {

    private final RevenueService revenueService;

    // Endpoint to get revenue details for a specific branch
    @GetMapping("/branch/{branchId}")
    public Revenue getRevenueForBranch(@PathVariable Long branchId) {
        return revenueService.getRevenueForBranch(branchId);
    }

    // Endpoint to get all revenue records
    @GetMapping
    public List<Revenue> getAllRevenue() {
        return revenueService.getAllRevenue();
    }

    // Endpoint to update revenue for a reservation
    @PostMapping("/update/reservation")
    public String updateRevenueForReservation(@RequestParam Long branchId, @RequestParam double amount) {
        revenueService.updateRevenueForReservation(branchId, amount);
        return "Revenue updated for reservation.";
    }

    // Endpoint to update revenue for a cancellation
    @PostMapping("/update/cancellation")
    public String updateRevenueForCancellation(@RequestParam Long branchId, @RequestParam double amount, @RequestParam boolean isLate) {
        revenueService.updateRevenueForCancellation(branchId, amount, isLate);
        return "Revenue updated for cancellation.";
    }

    // Endpoint to approve pending revenue
    @PostMapping("/approve")
    public String approveRevenue(@RequestParam Long branchId, @RequestParam double amount) {
        revenueService.approveRevenue(branchId, amount);
        return "Revenue approved.";
    }
}
