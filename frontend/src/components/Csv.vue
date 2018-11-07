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
                        <!--<p><a href="/viewCSV">Download CSV</a></p>-->
                        <upload-btn :fileChangedCallback="fileChanged" ripple=true title="Convert File to CSV">
                            <template slot="icon-left">
                                <v-icon left>mdi-file-import</v-icon>
                            </template>
                        </upload-btn>
                    </div>
                </v-layout>
            </v-flex>
        </v-layout>
    </v-container>

</template>

<script>
    import UploadButton from 'vuetify-upload-button';
    import axios from 'axios'

    export default {
        name: 'Csv',
        props: {
            msg: String
        },
        methods: {
            fileChanged(file) {
                let formData = new FormData();
                formData.append('file', this.file);

                axios.post('/download/csv', formData,
                    {
                        headers: {
                            'Content-Type': 'multipart/form-data'
                        }
                    }).then(function (response) {
                    console.log('SUCCESS');

                    const url = window.URL.createObjectURL(new Blob([response.data]));
                    const link = document.createElement('a');
                    link.href = url;
                    link.setAttribute('download', 'marc.txt'); //or any other extension
                    document.body.appendChild(link);
                    link.click();

                }).catch(function () {
                    console.log('FAILURE');
                })
            }
        },
        components: {
            'upload-btn': UploadButton
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
