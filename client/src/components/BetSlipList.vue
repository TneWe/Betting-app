<template>
  <div>
    <h1 class="page-title">Bet Slips</h1>
    <div v-for="betSlip in betSlips" :key="betSlip.id" class="card mb-3">
    <div class="card-body">
      <h5 class="card-title text-muted">Slip #{{ betSlip.id }} (${{ betSlip.wager }})</h5>
      <h6 class="card-subtitle mb-2">Bets: {{ betSlip.bets.length }}</h6>
      <ul>
        <li v-for="bet in betSlip.bets" :key="bet.id">
          Match: {{ bet.offer.host.name }} - {{ bet.offer.guest.name }}
          <div
            class="badge rounded-pill"
            :class="getStatus(bet.offer, bet.outcome).class">
            {{ getStatus(bet.offer, bet.outcome).text }}
            |
            {{ getOdds(bet.offer.outcomes, bet.outcome) }}
          </div>
        </li>
      </ul>
    </div>
  </div>
  </div>
</template>

<script>
import betSlipApi from '../api/betSlip';

const STATUS_BADGE = {
  inProgress: {
    text: 'In progress',
    class: 'bg-warning text-dark'
  },
  win: {
    text: 'Win!',
    class: 'bg-success'
  },
  lost: {
    text: 'Lost',
    class: 'bg-danger'
  }
};

export default {
  name: 'bet-slip-list',
  data: () => ({ betSlips: null }),
  methods: {
    getOdds(offerOutcomes, betOutcome) {
      const outcome = offerOutcomes.find(it => it.outcome === betOutcome);
      return outcome.odds;
    },
    getStatus(offer, betOutcome) {
      if (!offer.outcome) return STATUS_BADGE.inProgress;
      return offer.outcome === betOutcome ? STATUS_BADGE.win : STATUS_BADGE.lost;
    }
  },
  created() {
    betSlipApi.fetch().then(betSlips => (this.betSlips = betSlips));
  }
}
</script>

<style scoped>
.page-title {
  margin: 3rem 0;
}

.badge {
  margin-left: 0.5rem;
}
</style>
