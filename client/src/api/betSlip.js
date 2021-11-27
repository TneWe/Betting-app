import request from "../request";

const URL = '/bet-slips';

const fetch = () => request.get(URL).then(response => response.data)

const create = betSlip => request.post(URL, betSlip);

export default {
  create,
  fetch
}
