<template>
  <v-container>
    <v-layout
            text-xs-center
            wrap
    >
      <v-flex xs12>
        <v-img
                :src="require('../assets/logo.svg')"
                class="my-3"
                contain
                height="200"
        ></v-img>
      </v-flex>



      <v-flex
              mb-5
              xs12
      >


        <v-layout justify-center>

          <div class="hello">
            <h1>{{ msg }}</h1>
            <p><a href="/viewCSV">Download CSV</a></p>
          </div>
        </v-layout>
      </v-flex>
    </v-layout>
  </v-container>

</template>

<script>
export default {
  name: 'Csv',
  props: {
    msg: String
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #148bff;
}
</style>
