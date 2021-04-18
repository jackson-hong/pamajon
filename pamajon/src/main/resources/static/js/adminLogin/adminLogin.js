'use strict'

document.getElementById('submitButton').addEventListener('click',function(){

    const submitForm = document.getElementById("adminLogin")
    submitForm.action = '/pamajon/admin/login';
    submitForm.method = 'POST';
    submitForm.submit();

})