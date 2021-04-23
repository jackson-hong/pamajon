'use strict'

document.getElementById('submitButton').addEventListener('click',function(){

    const submitForm = document.getElementById("adminLogin")
    submitForm.action = '/admin/login';
    submitForm.method = 'POST';
    submitForm.submit();

})

window.addEventListener('keypress',function(e){

    if(e.key == 'Enter'){

        const submitForm = document.getElementById("adminLogin")
        submitForm.action = '/admin/login';
        submitForm.method = 'POST';
        submitForm.submit();

    }

})

document.getElementById("rememberPwd").addEventListener('change',function(){

    if(document.querySelector("#rememberPwd:checked")){
        alert("안전한 방법은 아닙니다 다큰 성인이니까 알아서 잘 선택한것이리라 믿습니다.");
    }


})