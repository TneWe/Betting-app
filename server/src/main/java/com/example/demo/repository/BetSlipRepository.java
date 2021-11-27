package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.BetSlip;

public interface BetSlipRepository extends JpaRepository<BetSlip, Long> {

}
