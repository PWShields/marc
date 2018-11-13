// file-upload.service.js

import * as axios from 'axios';


function upload(formData, parent) {


    return axios.post('/download/csv', formData,
        {
            headers: {
                'Content-Type': 'multipart/form-data'
            },
            responseType: 'blob'
        }).then(function (response) {
        console.log('SUCCESS');

        const url = window.URL.createObjectURL(new Blob([response.data]));
        parent.contenturl = url;

        url.click();

        // const link = document.createElement('a');
        // link.href = url;
        // link.setAttribute('download', 'marcTabbed.csv'); //or any other extension
        // document.body.appendChild(link);
        // link.click();

    }).catch(function () {
        console.log('FAILURE');
    })

    // return axios({
    //     url: '/download/csv',
    //     data: formData,
    //     method: 'POST',
    //     responseType: 'blob', // important
    // }).then((response) => {
    //     const url = window.URL.createObjectURL(new Blob([response.data]));
    //     const link = document.createElement('a');
    //     link.href = url;
    //     link.setAttribute('download', 'file.pdf'); //or any other extension
    //     document.body.appendChild(link);
    //     link.click();
    //     link.focus();
    //
    // });

}

export {upload}
