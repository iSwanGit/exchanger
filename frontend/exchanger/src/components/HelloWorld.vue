<template>
  <div>
    <h1>환율 계산</h1>
    <form>
      <div>송금국가: 미국(USD)</div>
      <div>
        <label for="currencySelect">수취국가: </label>
        <select id="currencySelect" name="Currency" @change="onChange($event)" class="form-control"
                v-model="targetCurrency">
          <option value="krw" selected>한국(KRW)</option>
          <option value="jpy">일본(JPY)</option>
          <option value="php">필리핀(PHP)</option>
        </select>
      </div>
      <div>
        환율 : {{ currency }} {{ targetCurrency.toUpperCase() }}/USD
      </div>
      <div>
        <label for="amount">송금액: </label>
        <input id="amount" type="text" v-model="amount"> USD
      </div>
      <div>
        <button type="submit" @click.prevent="calculate">Submit</button>
      </div>
    </form>

    <div v-if="result">
      수취금액은 {{ result }} 입니다.
    </div>
  </div>
</template>

<script>
// import axios from 'axios';

export default {
  name: 'HelloWorld',
  data() {
    return {
      targetCurrency: 'krw',
      currency: '',
      amount: '',
      dummy: {
        success: true,
        krw: 1162,
        jpy: 104,
        php: 50,
      },
      result: null,
      message: '',
    };
  },
  methods: {
    onChange(event) {
      this.targetCurrency = event.target.value;
      this.fetchData();
    },
    fetchData() {
      // axios.get('https://jsonplaceholder.typicode.com/users/')
      //   .then((response) => {
      //     this.kwd = response.status;
      //     console.log(response);
      //   })
      //   .catch((error) => {
      //     console.log(error);
      //   });
      this.currency = this.dummy[this.targetCurrency];
    },
    calculate() {
      this.result = this.amount * 100;
    },
  },
  mounted() {
    this.fetchData();
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
