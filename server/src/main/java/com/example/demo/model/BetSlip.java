package com.example.demo.model;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "bet_slip")
public class BetSlip {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "wager")
	private float wager;

	@ManyToMany
	@JoinTable(
		name = "bet", 
		joinColumns = @JoinColumn(name = "bet_slip_id"), 
		inverseJoinColumns = @JoinColumn(name = "offer_id"))
	private Set<Offer> offers;

	@OneToMany(mappedBy="betSlip")
	private Set<Bet> bets;

	public BetSlip() {
	}

	public BetSlip(float wager) {
		this.wager = wager;
	}

	public long getId() {
		return id;
	}

	public float getWager() {
		return wager;
	}

	public Set<Bet> getBets() {
		return bets;
	}

	// public void setWager(float wager) {
	// 	this.wager = wager;
	// }
}
