<template>
  <div>
      <v-card class="boarding">
        <v-toolbar color="primary" dark>
          Where Next?
        </v-toolbar>
        <v-form>
          <v-text-field v-model="departurePlace" label="Departure place" />
          <v-text-field v-model="destinationPlace" label="Destination place" />
          <div class="datePicker">
            <v-menu
                ref="menu"
                v-model="menu"
                :close-on-content-click="false"
                :return-value.sync="date"
                transition="scale-transition"
                offset-y
                min-width="auto"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-text-field
                    v-model="date"
                    label="Flight Date"
                    prepend-icon="mdi-calendar"
                    readonly
                    v-bind="attrs"
                    v-on="on"
                ></v-text-field>
              </template>
              <v-date-picker
                  v-model="date"
                  no-title
                  scrollable
              >
                <v-spacer></v-spacer>
                <v-btn
                    text
                    color="primary"
                    @click="menu = false"
                >
                  Cancel
                </v-btn>
                <v-btn
                    text
                    color="primary"
                    @click="$refs.menu.save(date)"
                >
                  OK
                </v-btn>
              </v-date-picker>
            </v-menu>
          </div>
        </v-form>
        <v-card-actions>
          <v-btn @click="findFlight(departurePlace, destinationPlace, date)">
            SEARCH
          </v-btn>
        </v-card-actions>
      </v-card>
    <div class= "noFlights" v-if="noFlightsFound">
      NO FLIGHTS AVAILABLE!
    </div>
    <div v-for="flight in flights" :key="flight.flightNr">
        <v-card
            :loading="loading"
            class="mx-auto my-12"
            max-width="374"
        >
          <template slot="progress">
            <v-progress-linear
                color="deep-purple"
                height="10"
                indeterminate
            ></v-progress-linear>
          </template>


          <v-card-title>{{ "Flight Number: " + flight.flightNr }}
            <v-icon

                color="green"
                large
                class="mr-2"
                @click="editUser(item)"
            >
              fa-plane
            </v-icon>
          </v-card-title>

          <v-card-text>
            <v-row
                align="center"
                class="mx-0"
            >

            </v-row>

            <div class="my-4 subtitle-1">
              {{"Price: " + flight.price + "$"}}
            </div>
          </v-card-text>

          <v-divider class="mx-4"></v-divider>

          <v-card-title>{{"From: " + flight.departurePlace}}</v-card-title>
          <v-card-title>{{"To: " + flight.destinationPlace}}</v-card-title>

          <v-card-text>
            <v-chip-group
                v-model="selection"
                active-class="deep-purple accent-4 white--text"
                column
            >
              <v-chip>{{ flight.startTime + "-" + flight.endTime}}</v-chip>

            </v-chip-group>
          </v-card-text>

          <v-card-actions>
            <v-btn
                color="deep-purple lighten-2"
                text
                @click="selectFlight(flight)"
            >
              SELECT
            </v-btn>
          </v-card-actions>
        </v-card>
    </div>
  </div>
</template>


<script>

import api from "@/api";
import router from "@/router";

export default {
  name: "BoardingList",
  props: {
    departurePlace: String,
    destinationPlace: String,
    date: String,
    opened: Boolean,
    noFlightsFound: Boolean
  },
  data() {
    return {
      auditKey: '',
      loading: false,
      menu: false,
      select: null,
      flights: [],
      options: [
        { type: 'Business'},
        { type: 'Economy'}
      ]
    }
  },
  methods: {
    selectFlight (flight) {
      this.loading = true
      setTimeout(() => (this.loading = false), 2000)
      //router.push("/payment");

      //
      router.push({
        name: 'payment',
        params: {
          flight: flight
        }
      });
    },
    onHidden(){
      this.opened = false;
    },
    async findFlight(departurePlace, destinationPlace, date){
      this.flights = await api.flights.findByMultipleCriteria(departurePlace, destinationPlace, date);
      if(this.flights.length == 0){
        this.noFlightsFound = true;
      } else{
        // router.push("/available");
      }
    },
  },
  computed: {
    isNew: function () {
      return !this.flight.id;
    },
  },
};
</script>

<style scoped></style>
