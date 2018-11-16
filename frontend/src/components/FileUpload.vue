<template>

    <div class="container">
        <v-flex xs12>
            <v-img
                    :src="require('../assets/marclogo.png')"
                    class="my-3"
                    contain
                    height="100"
            ></v-img>
        </v-flex>
        <v-layout justify-center>

            <div class="hello">
                <h1>{{ msg }}</h1>

            </div>
        </v-layout>
        <!--UPLOAD-->
        <form enctype="multipart/form-data" novalidate v-if="isInitial || isSaving || isSuccess">
            <h4>Load file to convert to Excel tab delimited format</h4>
            <div class="dropbox">
                <input type="file" :name="uploadFieldName" :disabled="isSaving"
                       @change="filesChange($event.target.name, $event.target.files); "
                       accept="text/enriched/*" class="input-file">
                <p v-if="isInitial">
                    Drag your file(s) here to begin<br> or click to browse
                </p>
                <p v-if="isSaving">
                    Uploading file...
                </p>
                <p v-if="isSuccess">
                    File converted
                </p>
                <p v-if="isFailed">
                    There was a problem converting the file.
                </p>

            </div>

        </form>

    </div>
</template>


<script>

    import * as axios from 'axios';

    const STATUS_INITIAL = 0, STATUS_SAVING = 1, STATUS_SUCCESS = 2, STATUS_FAILED = 3;


    export default {
        name: "FileUpload",
        props: {
            msg: String
        },
        data() {
            return {
                uploadedFiles: [],
                uploadError: null,
                currentStatus: null,
                uploadFieldName: 'file',
                contenturl: null
            }
        },
        computed: {
            isInitial() {
                return this.currentStatus === STATUS_INITIAL;
            },
            isSaving() {
                return this.currentStatus === STATUS_SAVING;
            },
            isSuccess() {
                return this.currentStatus === STATUS_SUCCESS;
            },
            isFailed() {
                return this.currentStatus === STATUS_FAILED;
            }
        },
        methods: {
            reset() {
                // reset form to initial state
                this.currentStatus = STATUS_INITIAL;
                this.uploadedFiles = [];
                this.uploadError = null;
            },
            upload(formData) {
                this.currentStatus =  STATUS_SAVING;
                axios.post('/download/csv', formData,
                    {
                        headers: {
                            'Content-Type': 'multipart/form-data'
                        }
                    }).then(function (response) {
                    console.log('SUCCESS');
                    this.currentStatus = STATUS_SAVING;

                    const url = window.URL.createObjectURL(new Blob([response.data]));
                    const link = document.createElement('a');
                    link.href = url;
                    link.setAttribute('download', 'marc.txt'); //or any other extension
                    document.body.appendChild(link);

                }).catch(function () {
                    console.log('FAILURE');
                })
            },


            filesChange(fieldName, fileList) {
                // handle file changes
                const formData = new FormData();

                if (!fileList.length) return;

                // append the files to FormData
                Array
                    .from(Array(fileList.length).keys())
                    .map(x => {
                        formData.append(fieldName, fileList[x], fileList[x].name);
                    });

                // save it
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
        }
        ,
        mounted() {
            this.reset();
        }
        ,
        components: {}
    }
</script>

<style lang="scss" scoped>
    .dropbox {
        outline: 2px dashed grey; /* the dash box */
        outline-offset: -10px;
        background: lightcyan;
        color: dimgray;
        padding: 10px 10px;
        min-height: 200px; /* minimum height */
        position: relative;
        cursor: pointer;
    }

    .input-file {
        opacity: 0; /* invisible but it's there! */
        width: 100%;
        height: 200px;
        position: absolute;
        cursor: pointer;
    }

    .dropbox:hover {
        background: lightblue; /* when mouse over to the drop zone, change color */
    }

    .dropbox p {
        font-size: 1.2em;
        text-align: center;
        padding: 50px 0;
    }

</style>
