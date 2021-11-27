<template>
  <div>
    <h1 class="page-title">Offer List</h1>
    <button
      @click="toggleBetting"
      type="button"
      class="btn btn-warning px-3 float-right">
      <template v-if="placingBet">Cancel bet</template>
      <template v-else>Place a bet</template>
    </button>
    <table class="table offer-table">
      <thead>
        <tr>
          <th scope="col">Home Team</th>
          <th scope="col">Guest Team</th>
          <th scope="col">1 odds</th>
          <th scope="col">draw odds</th>
          <th scope="col">2 odds</th>
          <th v-if="placingBet" scope="col">Your choice</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="offer in offers" :key="offer.id">
          <td>{{ offer.host.name }}</td>
          <td>{{ offer.guest.name }}</td>
          <th>{{ getOdds(offer, 'HOME_WIN') }}</th>
          <th>{{ getOdds(offer, 'DRAW') }}</th>
          <th>{{ getOdds(offer, 'AWAY_WIN') }}</th>
          <th v-if="placingBet">
            <select-outcome @input="setBet(offer.id, $event)" />
          </th>
        </tr>
      </tbody>
    </table>
    <template v-if="betSlip.length">
      <div class="slip-header">
        <h2 class="slip-title">Your slip</h2>
        <div class="form-group input-group">
          <label for="wager">Wager</label>
          <div class="input-group">
            <input
              @input="setWager($event.target.value)"
              :value="wager"
              id="wager"
              type="number"
              class="form-control"
              placeholder="Enter wager here">
            <div class="input-group-append">
              <span class="input-group-text">${{ wager }}</span>
            </div>
          </div>
        </div>
      </div>
      <table class="table">
        <thead>
          <tr>
            <th scope="col">Home Team</th>
            <th scope="col">Guest Team</th>
            <th scope="col">Outcome</th>
            <th scope="col">Odds</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(bet, index) in betSlip" :key="index">
            <td>{{ bet.offer.host.name }}</td>
            <td>{{ bet.offer.guest.name }}</td>
            <th>{{ bet.outcome }}</th>
            <th>{{ bet.odds }}</th>
          </tr>
        </tbody>
        <tfoot>
          <tr>
            <th></th>
            <th></th>
            <th cols="2">Total odds: {{ totalOdds }}</th>
            <th cols="2">Potential win: ${{ potentialWin }}</th>
          </tr>
        </tfoot>
      </table>
      <button
        @click="submit"
        :disabled="!wager"
        type="button"
        class="btn btn-info px-3 float-right">
        Submit
      </button>
    </template>
  </div>
</template>

<script>
import offerApi from '../api/offer';
import betSlipApi from '../api/betSlip';
import SelectOutcome from './SelectOutcome';

export default {
  name: 'offer-list',
  data: () => ({
    offers: null,
    placingBet: false,
    bets: [],
    wager: null
  }),
  computed: {
    betSlip() {
      const { bets, offers } = this;
      return bets.map(bet => {
        const offer = offers.find(it => it.id === bet.offerId);
        const odds = offer.outcomes.find(it => it.outcome === bet.outcome).odds;
        return { offer, odds, outcome: bet.outcome };
      });
    },
    totalOdds() {
      const odds = this.betSlip.reduce((odds, it) => it.odds * odds, 1);
      return Number(odds).toFixed(2);
    },
    potentialWin() {
      return Number(this.totalOdds * this.wager).toFixed(2);
    }
  },
  methods: {
    getOdds(offer, outcome) {
      return offer.outcomes.find(it => it.outcome === outcome).odds;
    },
    toggleBetting() {
      if (this.placingBet) this.bets = [];
      this.placingBet = !this.placingBet;
      this.wager = null;
    },
    setBet(offerId, outcome) {
      const index = this.bets.findIndex(it => it.offerId === offerId);
      if (index < 0) return this.bets.push({ offerId, outcome });
      if (outcome) this.$set(this.bets, index, { offerId, outcome });
      else this.bets.splice(index, 1);
    },
    setWager(value) {
      this.wager = Number(parseFloat(value).toFixed(2));

    },
    submit() {
      const betSlip = { wager: this.wager, bets: this.bets };
      return betSlipApi.create(betSlip)
        .then(() => this.$router.push({ name: 'bet-slips' }));
    }
  },
  created() {
    offerApi.fetch().then(offers => (this.offers = offers));
  },
  components: { SelectOutcome }
}
</script>

<style scoped>
.page-title {
  margin-top: 3rem;
}

.offer-table {
  margin-top: 4rem;
}

.float-right {
 float: right;
}

.slip-header {
  margin-top: 3rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.form-group {
  width: 20rem;
}
</style>
