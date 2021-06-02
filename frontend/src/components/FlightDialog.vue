<template>
  <v-dialog
      transition="dialog-bottom-transition"
      max-width="600"
      :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
          {{ isNew ? "Create flight" : "Edit flight" }}
        </v-toolbar>
        <v-form>
          <v-text-field v-model="flight.flightNr" label="Flight Number" />
          <v-text-field v-model="flight.departurePlace" label="Departure place" />
          <v-text-field v-model="flight.destinationPlace" label="Destination place" />
          <div class="datePicker">
             <v-menu
                 ref="menu"
                 v-model="menu"
                 :close-on-content-click="false"
                 :return-value.sync="flight.date"
                 transition="scale-transition"
                 offset-y
                 min-width="auto"
             >
               <template v-slot:activator="{ on, attrs }">
                 <v-text-field
                     v-model="flight.date"
                     label="Flight Date"
                     prepend-icon="mdi-calendar"
                     readonly
                     v-bind="attrs"
                     v-on="on"
                 ></v-text-field>
               </template>
               <v-date-picker
                   v-model="flight.date"
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
                     @click="$refs.menu.save(flight.date)"
                 >
                   OK
                 </v-btn>
               </v-date-picker>
             </v-menu>
          </div>

          <div class="startTimePicker">
            <v-menu
                ref="menu1"
                v-model="menu1"
                :close-on-content-click="false"
                :nudge-right="40"
                :return-value.sync="flight.startTime"
                transition="scale-transition"
                offset-y
                max-width="290px"
                min-width="290px"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-text-field
                    v-model="flight.startTime"
                    label="Start time"
                    prepend-icon="mdi-clock-time-four-outline"
                    readonly
                    v-bind="attrs"
                    v-on="on"
                ></v-text-field>
              </template>
              <v-time-picker
                  v-if="menu1"
                  v-model="flight.startTime"
                  full-width
                  @click:minute="$refs.menu1.save(flight.startTime)"
              ></v-time-picker>
            </v-menu>
          </div>

          <div class="endTimePicker">
            <v-menu
                ref="menu2"
                v-model="menu2"
                :close-on-content-click="false"
                :nudge-right="40"
                :return-value.sync="flight.endTime"
                transition="scale-transition"
                offset-y
                max-width="290px"
                min-width="290px"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-text-field
                    v-model="flight.endTime"
                    label="End time"
                    prepend-icon="mdi-clock-time-four-outline"
                    readonly
                    v-bind="attrs"
                    v-on="on"
                ></v-text-field>
              </template>
              <v-time-picker
                  v-if="menu2"
                  v-model="flight.endTime"
                  full-width
                  @click:minute="$refs.menu2.save(flight.endTime)"
              ></v-time-picker>
            </v-menu>
          </div>
          <v-text-field v-model="flight.price" label="Price" />
          <v-text-field v-model="flight.seatNumbers" label="Seat numbers" />
        </v-form>
        <v-card-actions>
          <v-btn @click="persist">
            {{ isNew ? "Create" : "Save" }}
          </v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "FlightDialog",
  props: {
    flight: Object,
    opened: Boolean,
  },
  data() {
    return {
      menu1: false,
      menu2: false,
      menu: false,
      select: null,
      // options: [
      //   { type: 'Business'},
      //   { type: 'Economy'}
      // ]
    }
  },
  methods: {
    onHidden(){
      this.opened = false;
    },
    persist() {
      if (this.isNew) {
        api.flights
            .create({
              flightNr: this.flight.flightNr,
              departurePlace: this.flight.departurePlace,
              destinationPlace: this.flight.destinationPlace,
              date: this.flight.date,
              startTime: this.flight.startTime,
              endTime: this.flight.endTime,
              price: this.flight.price,
              seatNumbers: this.flight.seatNumbers

            })
            .then(() => this.$emit("refresh"));
      } else {
        api.flights
            .edit({
              id: this.flight.id,
              flightNr: this.flight.flightNr,
              departurePlace: this.flight.departurePlace,
              destinationPlace: this.flight.destinationPlace,
              date: this.flight.date,
              startTime: this.flight.startTime,
              endTime: this.flight.endTime,
              price: this.flight.price,
              seatNumbers: this.flight.seatNumbers
            })
            .then(() => this.$emit("refresh"));
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
