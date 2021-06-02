<template>
  <div class="paymentForm" >
  <v-form
      ref="form"
      v-model="valid"
      lazy-validation
  >
    <div class="review">

      <v-toolbar-title class="title1">Review your selection </v-toolbar-title>

      <v-text-field
          v-model="flight.flightNr"
          label= "Flight"
          readonly
          required
      ></v-text-field>
      <v-text-field
          v-model="flight.departurePlace"
          label= "From"
          readonly
          required
      ></v-text-field>

      <v-text-field
          v-model="flight.destinationPlace"
          label= "To"
          readonly
          required
      ></v-text-field>

      <v-text-field
          v-model="flight.price"
          label= "Price: $"
          readonly
          required
      ></v-text-field>

    </div>

    <div class="space"></div>

    <div class="payment">

      <v-toolbar-title class="title2">Payment Details </v-toolbar-title>

    <v-text-field
        v-model="firstName"
        :counter="10"
        :rules="nameRules"
        label="First Name"
        required
    ></v-text-field>

    <v-text-field
      v-model="lastName"
      :counter="10"
      :rules="nameRules"
      label="Last Name"
      required
    ></v-text-field>

    <v-text-field
        v-model="email"
        :rules="emailRules"
        label="E-mail"
        required
    ></v-text-field>


    </div>

    <div class="space"></div>

    <v-btn
        :disabled="!valid"
        color="success"
        class="mr-4"
        @click="pay()"
    >
      AGREE AND PAY NOW
    </v-btn>

    <v-btn
        color="error"
        class="mr-4"
        @click="reset"
    >
      Reset Form
    </v-btn>
  </v-form>
  </div>
</template>
  <script>
    import router from "../router";

    export default {
      props: {
        flight: Object // always verify the props!!!
      },
      data: () => ({
        valid: true,
        firstName: '',
        lastName: '',
        client: Object,
        nameRules: [
          v => !!v || 'Name is required',
          v => (v && v.length <= 10) || 'Name must be less than 10 characters',
        ],
        email: '',
        emailRules: [
          v => !!v || 'E-mail is required',
          v => /.+@.+\..+/.test(v) || 'E-mail must be valid',
        ],
        select: null,
        // items: [
        //   'Economy',
        //   'Business',
        // ],
      }),

      methods: {
        pay (){
          var checkout = {};
          checkout.firstName = this.firstName;
          checkout.lastName = this.lastName;
          checkout.flightNr = this.flight.flightNr;
          checkout.price = this.flight.price;
          //router.push("/checkout");
          router.push({
            name: 'checkout',
            params: {
              checkout: checkout,
            }
          });
        },
        reset () {
          this.$refs.form.reset()
        },
      },
    }
  </script>