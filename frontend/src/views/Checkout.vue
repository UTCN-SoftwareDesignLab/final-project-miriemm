<template>
  <div class="div_class">
    <h3>You will be redirected to payment page</h3>

    <div class="alert alert-primary" role="alert">
      While making payment use card number 4242 4242 4242 4242 and enter random cvv(3 digit)
    </div>

    <button class="checkout_button" id="proceed-to-checkout" @click="goToCheckout()">
      Make payment
    </button>
  </div>
</template>
<script>
import api from "../api";
import { loadStripe } from '@stripe/stripe-js';

export default {
  data(){
    return {
      stripeAPIToken: "pk_test_51IxYmmGeUW98lxL3hWN47sWe9Ns4uS4t2eBvR8sFdMe8BQKBbDpJae5z0ao0EfBpcC0Ni4PPVdf2wdvCsqGrPCOD004y3AL976",
      stripe: '',
      token: null,
      sessionId: null,
      checkoutBodyArray:[]
    }
  },

  name:'Checkout',
  //props:["baseURL"],
  props: {
    checkout: Object // always verify the props!!!
  },

  methods: {
    async goToCheckout(){
      const stripe = await loadStripe(this.stripeAPIToken);

      api.payment.checkoutList(this.checkout
      ).then((response)=>{
        localStorage.setItem("sessionId",response.sessionId)
        return stripe.redirectToCheckout({ sessionId: response.sessionId })
      });
    }
  }
}

</script>

<style >

.alert{
  width: 50%;
}

.div_class{
  margin-top: 5%;
  margin-left: 30%;
}

.checkout_button{
  background-color: #5d3dec;
  border: none;
  color: white;
  margin-left: 15%;
  padding: 15px 30px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 15px;
  font-weight: bold;
  border-radius: 15px;
}

.checkout_button:focus{
  outline: none;
  box-shadow: none;
}

.checkout_button:disabled{
  background-color: #9b86f7;
  border: none;
  color: white;
  margin-left: 15%;
  padding: 15px 30px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 15px;
  font-weight: bold;
  border-radius: 15px;
  cursor:not-allowed;
}

</style>