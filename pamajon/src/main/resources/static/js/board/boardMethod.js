function submitForm(){
    let form = document.getElementById('qna-fm');
    form.submit();
}
function fn_paging(pageNum){location.href='/qna/list/'+pageNum;}

document.getElementById("cancelUpload").addEventListener('click',function (){window.history.back();});


//QnaFunction

function writeQna(){
    location.href="/qna/write"
}