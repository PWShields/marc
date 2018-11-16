// file-upload.service.js

import * as axios from 'axios';


function upload(formData) {


    axios.post('/download/csv', formData,
        {
            headers: {
                'Content-Type': 'multipart/form-data'
            },
            responseType: 'blob'
        }).then(response => {

        const url = window.URL.createObjectURL(new Blob([response.data]));
        const link = document.createElement('a');
        link.href = url;
        link.setAttribute('download', 'marc.txt'); //or any other extension
        document.body.appendChild(link);
        link.click();

    });

}

export {upload}
