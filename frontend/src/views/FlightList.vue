<template>
  <v-card>
    <v-card-title>
      Flights
      <v-spacer></v-spacer>
      <v-text-field
          v-model="search"
          append-icon="mdi-magnify"
          label="Search"
          single-line
          hide-details
      ></v-text-field>
      <v-btn @click="addFlight">Add Flight</v-btn>
    </v-card-title>
    <v-data-table
        :headers="headers"
        :items="flights"
        :search="search"
    >
      <template v-slot:item.actions="{ item }">
        <v-icon
            color="green"
            small
            class="mr-2"
            @click="editFlight(item)"
        >
          mdi-pencil
        </v-icon>
        <v-icon
            color="red"
            small
            @click="deleteFlight(item)"
        >
          mdi-delete
        </v-icon>
      </template>
    </v-data-table>
    <FlightDialog
        v-bind:opened="dialogVisible"
        :flight="selectedItem"
        @refresh="refreshList"
    ></FlightDialog>
  </v-card>
</template>

<script>
import api from "../api";
import FlightDialog from "../components/FlightDialog";

export default {
  name: "FlightList",
  components: { FlightDialog },
  data() {
    return {
      flights: [],
      search: "",
      headers: [
        { text: "Flight Number", value: "flightNr"},
        { text: "Departure", value: "departurePlace" },
        { text: "Destination", value: "destinationPlace"},
        { text: "Date", value: "date"},
        { text: "Start Time", value: "startTime"},
        { text: "End Time", value: "endTime"},
        {text: "Price", value: "price"},
        {text: "Number of seats", value: "seatNumbers"},
        { text: "Actions", value: "actions"}
      ],
      dialogVisible: false,
      selectedItem: {},
    };
  },
  methods: {
    editFlight(flight) {
      this.selectedItem = flight;
      this.dialogVisible = true;
    },
    async deleteFlight(flight) {
      await api.flights.delete(flight);
      this.message = "Delete of flight succesful!";
      await this.refreshList();

    },
    addFlight() {
      this.dialogVisible = true;
    },
    async refreshList() {
      this.dialogVisible = false;
      this.selectedItem = {};
      this.flights = await api.flights.allFlights();
    },
  },
  created() {
    this.refreshList();
  },
};
</script>

<style scoped></style>