'use strict'

//전역변수로써 사용.
const searchDateCheckbox = document.getElementById("searchAllWithoutDate");
const searchstartDate = document.getElementById("shippingSearch-startDate");
const searchEndDate = document.getElementById("shippingSearch-endDate");

/* 날짜가 오늘날짜 이후로 선택할 수 없도록 설정. */
let today = new Date();

let year = today.getFullYear();
let month = today.getMonth() + 1;
let date = today.getDate()

if (month < 10) {
    month = "0" + (today.getMonth() + 1);
}
if (date < 10) {
    date = "0" + today.getDate();
}
searchstartDate.setAttribute('max', `${year}-${month}-${date}`);
searchEndDate.setAttribute('max', `${year}-${month}-${date}`);

searchDateCheckbox.addEventListener('change',searchDateHandler);
searchDateCheckbox.addEventListener('click',searchDateHandler);

//날짜 전체검색 체크박스 핸들러/
function searchDateHandler(){
    //날짜에 상관없이 검색을 유저가 선택한 경우 (전체검색)
    if(searchDateCheckbox.checked){
        searchstartDate.setAttribute("readonly",true);
        searchEndDate.setAttribute("readonly",true);
        searchEndDate.value='';
        searchstartDate.value='';

    }
    //날짜 검색을 선택 한 경우
    if(!searchDateCheckbox.checked){

        searchstartDate.removeAttribute("readonly");
        searchEndDate.removeAttribute("readonly");
        searchEndDate.value=`${year}-${month}-${date}`;
    }

}

searchEndDate.addEventListener('change',checkDateValidation);
searchstartDate.addEventListener('change',checkDateValidation);

function checkDateValidation(){

    //날짜 2개 모두 선택이 이뤄짐.
    if(searchstartDate.value!='' && searchEndDate.value!=''){

        const startDateNumber = Number(searchstartDate.value.split("-").join(""));
        const endDateNumber = Number(searchEndDate.value.split("-").join(""));

        //검색 startDate 가 endDate 보다 클 수 없음
        if(endDateNumber-startDateNumber<0){

            alert("잘못된 날짜 선택입니다.")
            searchstartDate.focus();
            searchstartDate.value='';
            return false;
        }
      // 이경우가 아닐경우
        return true;
    }

}

