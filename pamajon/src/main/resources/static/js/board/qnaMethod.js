function submitForm(){
    let form = document.getElementById('qna-fm');
    form.submit();
}
function fn_paging(pageNum){location.href='/qna/list/'+pageNum;}
document.getElementById("cancelUpload").addEventListener('click',function (){window.history.back();});

function gotoQnaList(){
    location.href = "/qna/list/1";
}

function writeQnaAnswer(){
    let qnaNum = getElementById('qnaNum').val();
    location.href = "/qna/writeAnswer/" +qnaNum;
}
//QnaFunction
function writeQna(){location.href="/qna/write"}
function deleteQna(){
    let qnaNum = document.getElementById('qnaNum').value;
    location.href='/qna/delete/' + qnaNum;}

function modifyQna(){
    let qnaNum = document.getElementById('qnaNum').value;
    location.href='/qna/edit/' + qnaNum;};

function showAlert(){
    let alertMessage = getElementById('message').val();
    if(alertMessage != null){
        alert(alertMessage);
    }
}