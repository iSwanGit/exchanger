<template>
  <div>
    <select name="Currency" @change="onChange($event)" class="form-control"
            v-model="targetCurrency">
      <option value="krw" selected>한국(KRW)</option>
      <option value="jpy">일본(JPY)</option>
      <option value="php">필리핀(PHP)</option>
    </select>

    <div>
      현재환율 : {{ currency }}
    </div>

    <div>
      <input  type="text" v-model="amount">
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
      result: '',
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
  },
  mounted() {
    this.fetchData();
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
