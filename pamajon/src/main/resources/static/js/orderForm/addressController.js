'use strict'
//배송지 뿌려주는곳
    getAddrList();
    function getAddrList() {
        $.ajax({
            url: '/pamajon/order/addressinput',
            type: 'GET',
            data: {"userNo": `${$("input[name='userNo']").val()}`},
            dataType: 'json',
            contentType: "application/json;charset=UTF-8",
            success: function (result) {
                let $addrTable = $("#addrTable > tbody");
                console.log(result);
                let str = "";
                for (let i = 0; i < result.length; i++) {
                    str += `  <tr class="xans-record-" style="height: 100px; border-bottom: 1px solid rgb(215, 213, 213);">
                        <td style="display: none">${result[i].addrId}</td>
                        <td><input name="ma_idx" value="${result[i].addrId}" type="checkbox" onclick="checkboxLengthCheck()"></td>`
                    if (result[i].addrStatus == "0") {
                        str += `<td>
                        <img
                                    src="//img.echosting.cafe24.com/skin/base_ko_KR/myshop/btn_address_fix.gif" class=""
                                    alt="고정"></a><span class="displaynone"></span>
                        </td>`
                    }
                    if (result[i].addrStatus == "1") {
                        str += `<td>
                        </td>`
                    }
                    str += `<td>
                          <span>${result[i].addrName}</span>
                        </td>
                        <td><span>${result[i].addrReceiver}</span></td>
                        <td><span>${result[i].addrPhone}</span></td>
                        <td><span>${result[i].addrCellPhone}</span></td>
                        <td class="left">(<span>${result[i].addrZipcode}</span>)<span>${result[i].addr}</span> <span>${result[i].addrDetail}</span></td>
                        <td>
                            <img    onclick="injectAddress(this)"
                                    src="//img.echosting.cafe24.com/skin/base_ko_KR/myshop/btn_address_apply.gif"
                                    alt="적용">
                            <img
                                src="//img.echosting.cafe24.com/skin/base_ko_KR/myshop/btn_address_modify.gif"
                                alt="수정" 
                                onclick="modifyAddress(this);"
                                style="cursor: pointer">
                        </td>
                    </tr>`;
                }
                $addrTable.html(str);

                if ($("#addrTable > tbody > tr").length == 0) {

                    $("#addrTable > tbody").html(`<tr>
                        <td colspan="8" style="text-align: center;">등록된 배송지가 없습니다.</td>
                    </tr>`)
                }
            },

        });
    }

    function sendAddrToServer(){

        if($("input[name='addrName']").val().trim() == ""){
            alert("배송지 명을 입력해주세요.")
            $("input[name='addrName']").focus();
            return false;
        }

        if($("input[name='addrReceiver']").val().trim() == ""){
            alert("수취인을 입력해주세요")
            $("input[name='addrReceiver']").focus();
            return false;
        }

        if($("input[name='addrZipcode']").val().trim() == ""){
            alert("주소가 입력되지 않았습니다.")
            $("input[name='addrZipcode']").focus();
            return false;
        }

        if($("input[name='addrDetail']").val().trim() == ""){
            alert("상세주소를 넣어주세요")
            $("input[name='addrDetail']").focus();
            return false;
        }

        for(let i = 0 ; i<3; i++){

            if(document.getElementsByName("phone[]")[i].value.trim()==""){
                alert("연락처를 입력해주세요")
                document.getElementsByName("phone[]")[i].focus();
                return false;
            }

        }
        for(let i = 0 ; i<3; i++){

            if(document.getElementsByName("mobile[]")[i].value.trim()==""){
                alert("이동전화 번호를 입력해주세요")
                document.getElementsByName("mobile[]")[i].focus();
                return false;
            }
        }

        let regularCheck = $("input:checkbox[name='regularAddrCheck']:checked").length;

        if(confirm("주소를 입력하시겠습니까?")){
            let phone = [];
            let mobile = [];
            for(let i = 0 ; i<3; i++){
                phone.push(document.getElementsByName("phone[]")[i].value);
                mobile.push(document.getElementsByName("mobile[]")[i].value);
            }
            const promise = new Promise((resolve, reject)=>{

                $.ajax({
                    url:"/pamajon/order/regular",
                    type:"GET",
                    contentType : "application/json;charset=UTF-8",
                    data:{'userNo':`${$("input[name='userNo']").val()}`},
                    success:function(result) {
                        resolve(result);
                    }
                })

            }).then((result)=>{

                let status = "1";
                if(result==0 || regularCheck==1){
                    alert("현재 입력하신 주소가 기본 주소로 선택됩니다.")
                    status = "0";
                }

                $.ajax({
                    url:"/pamajon/order/address",
                    type: "POST",
                    data:{
                        "addrName":$("input[name='addrName']").val(),
                        "userId":`${$("input[name='userNo']").val()}`,
                        "addrReceiver":$("input[name='addrReceiver']").val(),
                        "addrZipcode":$("input[name='addrZipcode']").val(),
                        "addr":$("input[name='addr']").val(),
                        "addrDetail":$("input[name='addrDetail']").val(),
                        "addrPhone":phone.join("-"),
                        "addrCellPhone":mobile.join("-"),
                        "regularCheck":`${regularCheck}`,
                        "addrStatus":status
                    },
                    success:function(result){

                        if(result>0){
                            getAddrList();
                            listDisplay();
                            inputRefreash();

                        }
                    }
                })
            })
        }
    }

function insertAddr(){

    $("#addrTable").css('display','none');
    $(".ec-base-button.1").css('display','flex');
    $("#inputAddr").css('display','block');
    $(".ec-base-button.2").css('display','none');

}
function inputAddress() {
    new daum.Postcode({
        oncomplete: function (data) {

        $("#address_zip1").val(data.zonecode);
        $("#address_addr1").val(data.roadAddress);
        }
    }).open();
}

function checkAll(){
    console.log("실행");
        if($("#allCheck").is(":checked") == true){

            $("input:checkbox[name=ma_idx]").each(function() {
                this.checked = true;
            });

        } else {
            $("input:checkbox[name=ma_idx]").each(function() {
                this.checked = false;
            });
        }

}

function checkboxLengthCheck(){
        const num = $("#addrTable > tbody > tr").length;
    if($("input:checkbox[name=ma_idx]:checked").length == num){
        $("#allCheck").prop("checked",true);
    } else {
        $("#allCheck").prop("checked",false);
    }
}

function deleteAddress(){
        const checkedList = [];
        console.log("실행");
    $("input:checkbox[name=ma_idx]:checked").each(function() {
        checkedList.push(this.value);
    });

    if($("input:checkbox[name=ma_idx]:checked").length == 0 ){
        alert("선택된 주소가 없습니다.");
        return false;
    }

    if(confirm("체크된 주소를 삭제 하시겠습니까?")){

        $.ajax({
            url:"/pamajon/order/address",
            type:"DELETE",
            data:{"addrIdList":checkedList
                 ,"userNo":`${$("input[name='userNo']").val()}`},
            success:function (result){
                if(result>0){
                    alert("선택하신 주소가 삭제되었습니다.")
                    getAddrList();
                }
            }
        })


    }

}
function listDisplay(){

    $("#addrTable").css('display','block');
    $(".ec-base-button.1").css('display','none');
    $("#inputAddr").css('display','none');
    $(".ec-base-button.2").css('display','flex');
    inputRefreash();
}

function inputRefreash(){

    $("input[name='addrName']").val('');
    $("input[name='addrReceiver']").val('');
    $("input[name='addrZipcode']").val('');
    $("input[name='addr']").val('');
    $("input[name='addrDetail']").val('');

    for(let i = 1 ; i<3; i++){
        document.getElementsByName("phone[]")[i].value='';
        document.getElementsByName("mobile[]")[i].value='';
    }
}

function injectAddress(targer){


}

function modifyAddress(target){

        $.ajax({
            url:"/pamajon/order/address",
            type:"GET",
            data:{addrNo:`${target.parentNode.parentNode.childNodes[1].innerHTML}`},
            success:function (result){
                console.log(result);
            }

        })

}







