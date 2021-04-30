function submitForm(){
    let form = document.getElementById('qna-fm');
    form.submit();
}


document.getElementById("cancelUpload").addEventListener('click',function (){window.history.back();});


//QnaFunction

function writeQna(){
    location.href="/qna/write"
}