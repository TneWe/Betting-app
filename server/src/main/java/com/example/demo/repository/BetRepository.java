package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Bet;

public interface BetRepository extends JpaRepository<Bet, Long> {

}
