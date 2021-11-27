package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Bet;
import com.example.demo.model.BetSlip;
import com.example.demo.repository.BetRepository;
import com.example.demo.repository.BetSlipRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class BetSlipController {

	@Autowired
	BetSlipRepository betSlipRepository;

	@Autowired
	BetRepository betRepository;

	@GetMapping("/bet-slips")
	public ResponseEntity<List<BetSlip>> getAllBetSlips() {
		try {
			List<BetSlip> betSlip = new ArrayList<BetSlip>();

      betSlipRepository.findAll().forEach(betSlip::add);

			if (betSlip.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(betSlip, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/bet-slips")
	public ResponseEntity<BetSlip> createBetSlip(@RequestBody BetSlip betSlip) {
		try {
			BetSlip _betSlip = betSlipRepository.save(betSlip);
			for (Bet bet : betSlip.getBets()) {
				bet.setBetSlip(betSlip);
				betRepository.save(bet);
			}
			return new ResponseEntity<>(_betSlip, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
