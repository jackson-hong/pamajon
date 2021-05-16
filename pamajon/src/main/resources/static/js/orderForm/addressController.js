'use strict'
//배송지 뿌려주는곳
getAddrList();
function getAddrList() {
    $.ajax({
        url: '/order/addressinput',
        type: 'GET',
        data: {"userNo": `${$("input[name='userNo']").val()}`},
        dataType: 'json',
        contentType: "application/json;charset=UTF-8",
        success: function (result) {
            let $addrTable = $("#addrTable > tbody");
            let str = "";
            for (let i = 0; i < result.length; i++) {
                str += `<tr class="xans-record-" style="height: 100px; border-bottom: 1px solid rgb(215, 213, 213);">
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
    if($("input[name='updateCheck']").val()=="basic"){

        if(confirm("주소를 입력하시겠습니까?")){
            let phone = [];
            let mobile = [];
            for(let i = 0 ; i<3; i++){
                phone.push(document.getElementsByName("phone[]")[i].value);
                mobile.push(document.getElementsByName("mobile[]")[i].value);
            }
            const promise = new Promise((resolve, reject)=>{

                $.ajax({
                    url:"/order/regular",
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
                    url:"/order/address",
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
    } else if($("input[name='updateCheck']").val()=="Reload"){

        if(confirm("주소를 수정 하시겠습니까?")){
            let phone = [];
            let mobile = [];
            for(let i = 0 ; i<3; i++){
                phone.push(document.getElementsByName("phone[]")[i].value);
                mobile.push(document.getElementsByName("mobile[]")[i].value);
            }
            const promise = new Promise((resolve, reject)=>{

                $.ajax({
                    url:"/order/regular",
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
                    url:"/order/address",
                    type: "PUT",
                    data:{
                        "addrId":$("input[name='addrId']").val(),
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
}

function insertAddr(str){

    $("input[name='updateCheck']").val(str);
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

    $("input:checkbox[name=ma_idx]:checked").each(function() {
        checkedList.push(this.value);
    });

    if($("input:checkbox[name=ma_idx]:checked").length == 0 ){
        alert("선택된 주소가 없습니다.");
        return false;
    }

    if(confirm("체크된 주소를 삭제 하시겠습니까?")){

        $.ajax({
            url:"/order/address",
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
//자식화면에서 부모화면으로 value 값 전송.
function injectAddress(inject){

    $("input[name='addrId']").val(inject.parentNode.parentNode.childNodes[1].innerHTML);
    $.ajax({
        url:"/order/address",
        type:"GET",
        data:{addrNo:`${inject.parentNode.parentNode.childNodes[1].innerHTML}`},
        success:function (result){
            $(opener.document).find("input[name='addrId']").val(result.addrId);
            $(opener.document).find("input[name='addrReloadCheck']").val('basic');
            $(opener.document).find("input[name='addrName']").val(result.addrName);
            $(opener.document).find("input[name='addrReceiver']").val(result.addrReceiver);
            $(opener.document).find("input[name='addrZipcode']").val(result.addrZipcode);
            $(opener.document).find("input[name='addr']").val(result.addr);
            $(opener.document).find("input[name='addrDetail']").val(result.addrDetail);
            let cellArr = result.addrCellPhone.split("-");
            let phoneArr = result.addrPhone.split("-");
            opener.document.getElementsByName("mobile[]")[0].value=cellArr[0];
            opener.document.getElementsByName("mobile[]")[1].value=cellArr[1];
            opener.document.getElementsByName("mobile[]")[2].value=cellArr[2];
            window.close();
        }
    })

}

function modifyAddress(target){

    $("input[name='addrId']").val(target.parentNode.parentNode.childNodes[1].innerHTML);

    $.ajax({
        url:"/order/address",
        type:"GET",
        data:{addrNo:`${target.parentNode.parentNode.childNodes[1].innerHTML}`},
        success:function (result){
            $("input[name='addrName']").val(result.addrName);
            $("input[name='addrReceiver']").val(result.addrReceiver);
            $("input[name='addrZipcode']").val(result.addrZipcode);
            $("input[name='addr']").val(result.addr);
            $("input[name='addrDetail']").val(result.addrDetail);
            let cellArr = result.addrCellPhone.split("-");
            let phoneArr = result.addrPhone.split("-");
            document.getElementsByName("phone[]")[0].value=phoneArr[0];
            document.getElementsByName("phone[]")[1].value=phoneArr[1];
            document.getElementsByName("phone[]")[2].value=phoneArr[2];

            document.getElementsByName("mobile[]")[0].value=cellArr[0];
            document.getElementsByName("mobile[]")[1].value=cellArr[1];
            document.getElementsByName("mobile[]")[2].value=cellArr[2];

            if(result.addrStatus==0){
                $('input[name="regularAddrCheck"]').prop("checked",true);
            } else {
                $('input[name="regularAddrCheck"]').prop("checked",false);
            }
            insertAddr("Reload");
        }
    })
}



