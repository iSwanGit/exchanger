<template>
  <div>
    <h1>환율 계산</h1>

    <b-form @submit.prevent="calculate">
      <b-form-group
        label="송금국가: "
        label-for="from"
        label-cols-sm="4"
        label-cols-lg="3"
      >
        <b-form-input
          id="from"
          type="text"
          value="미국(USD)"
          readonly
        ></b-form-input>
      </b-form-group>
      <b-form-group
        label="수취국가: "
        label-for="currencySelect"
        label-cols-sm="4"
        label-cols-lg="3"
      >
        <b-form-select
          id="currencySelect"
          v-model="targetCurrency" :options="options" @change="onChange"
        ></b-form-select>
      </b-form-group>
      <b-form-group
        label="환율 : "
        label-for="result"
        label-cols-sm="4"
        label-cols-lg="3"
      >
        <div>{{ currency | currencyFilter }} {{ targetCurrency.toUpperCase() }}/USD</div>
      </b-form-group>
      <b-form-group id="input-group-2" label="송금액 : " label-for="amount"
                    label-cols-sm="4" label-cols-lg="3">
        <b-input-group>
          <b-input id="amount" v-model="amount"></b-input><span>USD</span>
        </b-input-group>
      </b-form-group>

      <b-button type="submit" variant="primary">Submit</b-button>
    </b-form>

    <hr>

    <div v-if="result">
      수취금액은 {{ result | currencyFilter }} {{ targetCurrency.toUpperCase() }} 입니다.
    </div>
    <div v-if="message">
      <b-alert show variant="danger">{{ message }}</b-alert>
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
      options: [
        { value: 'krw', text: '한국(KRW)' },
        { value: 'jpy', text: '일본(JPY)' },
        { value: 'php', text: '필리핀(PHP)' },
      ],
      currency: '',
      amount: '',
      dummy: {
        success: true,
        krw: 1162,
        jpy: 104,
        php: 50,
      },
      result: null,
      message: null,
    };
  },
  methods: {
    onChange(event) {
      this.targetCurrency = event;
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
