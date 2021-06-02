<template>
  <v-content>
    <Carousel />
    <Gallery />
  </v-content>
</template>

<script>
import Carousel from "../components/Carousel";
import Gallery from "../components/Gallery";

export default {
  name: "Home",

  components: {
    Carousel,
    Gallery,
  }
};
</script>