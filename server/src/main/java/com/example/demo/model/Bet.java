package com.example.demo.model;
import com.example.demo.Outcome;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "bet")
public class Bet {

	@EmbeddedId
	private BetKey id = new BetKey();

	@ManyToOne
	@MapsId("offerId")
	@JoinColumn(name = "offer_id")
	Offer offer;

	@ManyToOne
	@MapsId("betSlipId")
	@JoinColumn(name = "bet_slip_id")
	BetSlip betSlip;

	@Column(name = "outcome")
	@Enumerated(EnumType.STRING)
	private Outcome outcome;

	public Bet() {
	}

	public Outcome getOutcome() {
		return outcome;
	}

	@JsonProperty("offer")
	public Offer getOffer() {
		return offer;
	}

	public void setBetSlip(BetSlip betSlip) {
		this.betSlip = betSlip;
	}

	@JsonProperty("offerId")
	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	@Embeddable
	private static class BetKey implements Serializable {

			@Column(name = "offer_id")
			Long offerId;

			@Column(name = "bet_slip_id")
			Long betSlipId;

			public BetKey() {
			}
	}
}
