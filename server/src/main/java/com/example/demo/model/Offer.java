package com.example.demo.model;
import com.example.demo.Outcome;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;


@Entity
@Table(name = "offer")
public class Offer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name="host_id", nullable=false)
	private Team host;

	@ManyToOne
	@JoinColumn(name="guest_id", nullable=false)
	private Team guest;

	@Column(name = "host_odds")
	private float hostOdds;

	@Column(name = "guest_odds")
	private float guestOdds;

	@Column(name = "draw_odds")
	private float drawOdds;

	@Column(name = "outcome")
	@Enumerated(EnumType.STRING)
	private Outcome outcome;

	public Offer() {

	}

	public Offer(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public Team getHost() {
		return host;
	}

	public Team getGuest() {
		return guest;
	}

	public Outcome getOutcome() {
		return outcome;
	}

	public void setHost(Team host) {
		this.host = host;
	}

	public void setGuest(Team guest) {
		this.guest = guest;
	}

	public void setHostOdds(long odds) {
		this.hostOdds = odds;
	}

	public void setGuestOdds(long odds) {
		this.guestOdds = odds;
	}

	public void setDrawOdds(long odds) {
		this.drawOdds = odds;
	}

	public List<OutcomeData> getOutcomes() {
		List<OutcomeData> outcomes = new ArrayList<OutcomeData>();
		outcomes.add(new OutcomeData(Outcome.HOME_WIN, this.hostOdds));
		outcomes.add(new OutcomeData(Outcome.AWAY_WIN, this.guestOdds));
		outcomes.add(new OutcomeData(Outcome.DRAW, this.drawOdds));
		return outcomes;
	}

	private class OutcomeData {
		private Outcome outcome;
		private float odds;

		public OutcomeData(Outcome outcome, float odds) {
			this.outcome = outcome;
			this.odds = odds;
		}

		public Outcome getOutcome() {
			return outcome;
		}

		public float getOdds() {
			return odds;
		}
	}
}
