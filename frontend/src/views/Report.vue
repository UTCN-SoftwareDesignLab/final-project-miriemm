<template>
  <v-card>
    <v-card-title>
      Reports
      <v-spacer></v-spacer>
      <v-btn @click="pdfReport">PDF</v-btn>

    </v-card-title>
  </v-card>
</template>

<script>
import api from "../api";
export default {
  name: "Report",
  data() {
    return {
    };
  },
  methods: {
    downloadPDF(pdfBytes){
      var blob = new Blob([pdfBytes], {type: "application/pdf"});
      var link = document.createElement('a');
      link.href = window.URL.createObjectURL(blob);
      var fileName = "outOfStockReport.pdf";
      link.download = fileName;
      link.click();
    },
    pdfReport() {
      api.reports.pdfReport().then(value => {this.downloadPDF(value)});
    },
  },
};
</script>

<style scoped></style>