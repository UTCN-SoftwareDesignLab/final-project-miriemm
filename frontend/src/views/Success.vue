<template>
  <div class="successClass">

    <v-toolbar-title class="titleS">Payment successful! </v-toolbar-title>

    <v-btn
        :loading="loading3"
        :disabled="loading3"
        color="blue-grey"
        class="ma-2 white--text"
        @click="pdfReport"
    >
      DOWNLOAD BOARDING PASS
      <v-icon
          right
          dark
      >
        mdi-download
      </v-icon>
    </v-btn>
  </div>
</template>


<script>
import api from "@/api";

export default {
  name: "Success",
  data () {
    return {
    };
  },

  methods:{
    downloadPDF(pdfBytes){
      var blob = new Blob([pdfBytes], {type: "application/pdf"});
      var link = document.createElement('a');
      link.href = window.URL.createObjectURL(blob);
      var fileName = "boardingPass.pdf";
      link.download = fileName;
      link.click();
    },
    pdfReport() {
      var bookingID = this.$route.params.bookingID;
      api.reports.pdfReport(bookingID).then(value => {this.downloadPDF(value)});
    },
  }
}
</script>

