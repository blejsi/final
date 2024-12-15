package com.sda.car_rental.service;

import com.sda.car_rental.model.Branch;
import com.sda.car_rental.model.Revenue;
import com.sda.car_rental.repository.BranchRepository;
import com.sda.car_rental.repository.RevenueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RevenueService {

    private final RevenueRepository revenueRepository;
    private final BranchRepository branchRepository;

    public void updateRevenueForReservation(Long branchId, double amount) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new RuntimeException("Branch not found"));

        Revenue revenue = revenueRepository.findByBranch(branch)
                .orElseGet(() -> Revenue.builder().branch(branch).totalRevenue(0).approvedRevenue(0).pendingRevenue(0).build());

        revenue.setTotalRevenue(revenue.getTotalRevenue() + amount);
        revenue.setPendingRevenue(revenue.getPendingRevenue() + amount);

        revenueRepository.save(revenue);
    }

    public void updateRevenueForCancellation(Long branchId, double amount, boolean isLate) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new RuntimeException("Branch not found"));

        Revenue revenue = revenueRepository.findByBranch(branch)
                .orElseThrow(() -> new RuntimeException("Revenue record not found"));

        double adjustment = isLate ? (amount * 0.8) : amount;
        revenue.setTotalRevenue(revenue.getTotalRevenue() - adjustment);
        revenue.setPendingRevenue(revenue.getPendingRevenue() - adjustment);

        revenueRepository.save(revenue);
    }

    public void approveRevenue(Long branchId, double amount) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new RuntimeException("Branch not found"));

        Revenue revenue = revenueRepository.findByBranch(branch)
                .orElseThrow(() -> new RuntimeException("Revenue record not found"));

        revenue.setApprovedRevenue(revenue.getApprovedRevenue() + amount);
        revenue.setPendingRevenue(revenue.getPendingRevenue() - amount);

        revenueRepository.save(revenue);
    }

    // Get revenue for a specific branch
    public Revenue getRevenueForBranch(Long branchId) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new RuntimeException("Branch not found"));
        return revenueRepository.findByBranch(branch)
                .orElseThrow(() -> new RuntimeException("Revenue record not found for branch ID: " + branchId));
    }

    // Get all revenue records
    public List<Revenue> getAllRevenue() {
        return revenueRepository.findAll();
    }
}




