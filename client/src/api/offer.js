import request from "../request";

const BASE_URL = '/offers';

const fetch = () => request.get(BASE_URL).then(response => response.data)

export default {
  fetch
}
