package com.sda.car_rental.repository;

import com.sda.car_rental.model.Branch;
import com.sda.car_rental.model.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RevenueRepository extends JpaRepository<Revenue, Long> {
    Optional<Revenue> findByBranch(Branch branch);

    Optional<Revenue> findByBranchId(Long branchId);

    List<Revenue> findAll();
}

