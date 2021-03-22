'use strict'

let point = 0;
let pointStr = "";
let shipfee = 0;
let priceStr = "";

settingTablePrice();
purchaseTableController();

function processPurchase(){


    if($("input[name='addrReceiver']").val().trim() == ""){
        alert("수취인을 입력해주세요");
        $("input[name='addrReceiver']").focus();
        return false;
    }

    if($("input[name='addr']").val().trim() == ""){
        alert("주소가 입력되지 않았습니다.");
        $("input[name='addrZipcode']").focus();
        return false;
    }

    if($("input[name='addrDetail']").val().trim() == ""){
        alert("상세주소를 넣어주세요");
        $("input[name='addrDetail']").focus();
        return false;
    }
    if($("#input_mile").val()<1000 && $("#input_mile").val()>0){
        alert("적립금 사용은 1000원 이상이어야 사용이 가능합니다.");
        $("#input_mile").focus();
        return false;
    }

    for(let i = 0 ; i<3; i++){

        if(document.getElementsByName("mobile[]")[i].value.trim()==""){
            alert("이동전화 번호를 입력해주세요")
            document.getElementsByName("mobile[]")[i].focus();
            return false;
        }
    }

    for(let i = 0 ; i<2; i++){

        if(document.getElementsByName("email[]")[i].value.trim()==""){
            alert("이메일을 입력해주세요")
            document.getElementsByName("email[]")[i].focus();
            return false;
        }
    }

    let merchant_name_arr = [];
    let merchant_name ="";

    for(let i = 0 ; i<$("#product_table > tbody > tr").length; i++){
        merchant_name_arr.push($(`span.pro_title:eq(${i})`).text().trim());
    }
    merchant_name = merchant_name_arr.join(",");

    var IMP = window.IMP;
    IMP.init("imp14206910");

    IMP.request_pay({
        pg : 'inicis', // version 1.1.0부터 지원.
        pay_method : 'card',
        merchant_uid : 'pamajon_' + new Date().getTime(),
        name : "파마존 결제내역",
        amount : 100, //${removeCommaConvertToNumber($("#total_price").val())} <== 실제 결제테스트는 이 주석을 풀고 사용.
        buyer_email : `${document.getElementsByName("email[]")[0].value.trim()}@${document.getElementsByName("email[]")[1].value.trim()}`,
        buyer_name : `${$("input[name='addrReceiver']").val().trim()}`,
        buyer_tel : `${document.getElementsByName("mobile[]")[0].value.trim()}-${document.getElementsByName("mobile[]")[1].value.trim()}-${document.getElementsByName("mobile[]")[2].value.trim()}`,
        buyer_addr : `${$("input[name='addr']").val().trim()} ${$("input[name='addrDetail']").val().trim()}`,
        buyer_postcode : `${$("input[name='addrZipcode']").val().trim()}`,
    }, function(rsp) {
        if ( rsp.success ) {
            console.log(rsp);
            //주소부터 넣고와야함.
            let date = new Date();

            let SoldObject = [];

            for(let i = 0 ; i<$("#product_table > tbody > tr").length; i++){

                var soldObjectTemp =
                    {
                        optionId:$(`input[name='optionId']:eq(${i})`).val(),
                        soldQuantity:$(`.product_quantity:eq(${i})`).text().trim()
                    }
                SoldObject.push(soldObjectTemp);
            }
            let soldDto = JSON.stringify(SoldObject);

            let orderDto = JSON.stringify({
                userId:`${$("input[name='userNo']").val()}`,
                addrId:`${$("input[name='addrId']").val()}`,
                addr:`${$("input[name='addrZipcode']").val()} ${$("input[name='addr']").val()} ${$("input[name='addrDetail']").val()}`,
                orderStatus:'0',
                orderPhone:`${document.getElementsByName("mobile[]")[0].value.trim()}-${document.getElementsByName("mobile[]")[1].value.trim()}-${document.getElementsByName("mobile[]")[2].value.trim()}`,
                orderEmail:`${document.getElementsByName("email[]")[0].value.trim()}@${document.getElementsByName("email[]")[1].value.trim()}`,
                orderPurchase:`${rsp.paid_amount}`,
                orderMessage:`${$("input[name='orderMessage']").val()}`,
                orderDeliveryStatus:"1",
                orderDate:`${date.getFullYear()}/${date.getMonth()}/${date.getDate()} ${date.getHours()}:${date.getMinutes()}`,
                orderCardNum:`${rsp.card_number}`,
                orderTransName:`${$("input[name='orderTransName']").val()}`,
                orderMethod:`${rsp.pay_method}`,
                orderKey:`${rsp.receipt_url}`,
                orderShipfee:`${removeCommaConvertToNumber($(".product_tfoot_shipfee").text())}`,
            });

            let addressDto = JSON.stringify({
                addrId:`${$("input[name='addrId']").val()}`,
                userId:$("input[name='userNo']").val(),
                addrReceiver:$("input[name='addrReceiver']").val(),
                addrZipcode:$("input[name='addrZipcode']").val(),
                addr:$("input[name='addr']").val(),
                addrDetail:$("input[name='addrDetail']").val(),
                addrReloadCheck:$("input[name='addrReloadCheck']").val(),
                addrCellPhone:`${document.getElementsByName("mobile[]")[0].value.trim()}-${document.getElementsByName("mobile[]")[1].value.trim()}-${document.getElementsByName("mobile[]")[2].value.trim()}`,

            });

            let usedMileageDto = JSON.stringify({
                userId:$("input[name='userNo']").val(),
                mileage:$("input[name='mileage']").val()
            });

            let stackMileageDto = JSON.stringify({
                userId:$("input[name='userNo']").val(),
                mileage:`${removeCommaConvertToNumber($("#mAllMileageSum").text().trim())}`
            });

            $.ajax({
                url:"/pamajon/order/purchase",
                type:"POST",
                traditional: true,
                data:{orderDto,soldDto,addressDto,usedMileageDto,stackMileageDto},
                success:function (result){
                    if(result>0){
                        $.ajax({
                            url:"/pamajon/order/email",
                            type: "POST",
                            data: {to:`${document.getElementsByName("email[]")[0].value.trim()}@${document.getElementsByName("email[]")[1].value.trim()}`,
                                productName:"aaa"},
                            success:function (result){
                                if(result>0){
                                    location.href='/pamajon/member/orderList';
                                }
                            }
                        })
                    }
                }
            })
        }
    });
}
function settingTablePrice(){

    let tableLength = $("#product_table > tbody > tr").length;
    let totalPrice = 0;
    point = 0;
    // 테이블 가격셋팅 for문
    for(let i = 0 ; i<tableLength; i++){

        //상품의 가격을 알아냄
        for(let j = 0 ; j < $(`.product_price:eq(${i})`).text().trim().split(",").length ; j++){
            priceStr +=  $(`.product_price:eq(${i})`).text().trim().split(",")[j];
        }
        priceStr =""+Number(priceStr*$(`.product_quantity:eq(${i})`).text());


        //적립금 Display
        if(Number(priceStr)>=1_0000 && Number(priceStr) < 100_000) {

            $(`.product_point:eq(${i})`).text((Math.floor(Number(priceStr)/10_000)*100).toLocaleString());
            //10만단위
        } else if (Number(priceStr)>=100_000){

            $(`.product_point:eq(${i})`).text((Math.floor(Number(priceStr)/100_000)*1_000).toLocaleString());
            //100만단위
        } else if (Number(priceStr)>=1_000_000){

            $(`.product_point:eq(${i})`).text((Math.floor(Number(priceStr)/1_000_000)*10_000).toLocaleString());
        } else if (Number(priceStr)>=10_000_000){

            $(`.product_point:eq(${i})`).text((Math.floor(Number(priceStr)/10_000_000)*100_000).toLocaleString());
        }
        else {
            $(`.product_point:eq(${i})`).text('0');
        }

        //전체 포인트 연산
        for(let j = 0 ; j<$(`.product_point:eq(${i})`).text().trim().split(",").length; j++){
            pointStr += $(`.product_point:eq(${i})`).text().trim().split(",")[j];
        }
        point += Number(pointStr);

        $(`.product_total:eq(${i})`).text(Number(priceStr).toLocaleString());

        totalPrice += Number(priceStr);
        priceStr="";
        pointStr = "";

    }

    if(totalPrice<50000){
        shipfee = 3000;
    }

    for(let i = 0 ; i<tableLength; i++){
        if(totalPrice>50000){
            $(`.shipfee:eq(${i})`).text('무료');
        } else {
            $(`.shipfee:eq(${i})`).text('조건');
        }
    }

    $(`.product_tfoot_productTotal`).text(totalPrice.toLocaleString());
    $(".product_tfoot_shipfee").text(shipfee.toLocaleString());
    $(".product_tfoot_total").text((totalPrice+shipfee).toLocaleString());
    $("#total_order_price_view").text((totalPrice+shipfee).toLocaleString());
    $("#total_order_sale_price_view").text((totalPrice+shipfee).toLocaleString());

}
function checkAll(){

    if($("#allCheck").is(":checked") == true){

        $("input:checkbox[name=deleteProduct]").each(function() {
            this.checked = true;
        });

    } else {
        $("input:checkbox[name=deleteProduct]").each(function() {
            this.checked = false;
        });
    }

}

function lengthCheck(){

    const num = $("#productdisplay_table > tbody > tr").length;

    if($("input:checkbox[name=deleteProduct]")==num){
        $("#allCheck").prop("checked",true);
    } else {
        $("#allCheck").prop("checked",false);
    }

}
function deleteProduct(){

    if(confirm("선택하신 물품을 삭제 하시겠습니까?")){

        $("input:checkbox[name=deleteProduct]:checked").each(function() {
            this.parentNode.parentNode.remove();
        });
    }
    checkRemainProduct();
}

function checkRemainProduct(){

    if($("#product_table > tbody > tr").length==0){
        alert("모든 상품을 삭제 하였습니다 메인페이지로 이동합니다.")
        location.href="/pamajon";
    } else {
        settingTablePrice();
        purchaseTableController();
    }
}

$("#input_mile").on('keyup change',function (){

    let $pointValue = $("#input_mile");
    let maxMile;
    let total;
    let mileStr = "";
    let totalStr = "";

    for(let i = 0 ; i<$("strong.txtWarn").text().trim().split(",").length; i++){
        mileStr+=$("strong.txtWarn").text().trim().split(",")[i];
    }
    for(let i = 0 ; i<$("#total_order_price_view").text().trim().split(",").length; i++){
        totalStr+=$("#total_order_price_view").text().trim().split(",")[i];
    }
    //최대 마일리지
    total   = Number(totalStr);
    maxMile = Number(mileStr);
    if($pointValue.val()>maxMile){
        $pointValue.val(maxMile);
    }
    if($pointValue.val()<0){
        $pointValue.val(0);
    }

    $("#total_sale_price_view").text(($pointValue.val()).toLocaleString());
    $("#total_addpay_price_view").text(($pointValue.val()).toLocaleString());
    $("#total_order_sale_price_view").text((total-$pointValue.val()).toLocaleString());

    if(!$pointValue.val()){
        $("#total_sale_price_view").text(0);
        $("#total_addpay_price_view").text(0);
    }
    purchaseTableController();

})
function purchaseTableController(){

    let total;
    let totalStr = "";

    for(let i = 0 ; i<$("#total_order_sale_price_view").text().trim().split(",").length; i++){
        totalStr+=$("#total_order_sale_price_view").text().trim().split(",")[i];
    }
    total=Number(totalStr);
    $("#total_price").val(total.toLocaleString());
    $("#mProductMileage").text(point);

    if(total >= 10_000 && total < 100_000) {
        $("#mAllMileageSum").text(((Math.floor(total/10_000))*100).toLocaleString());
        //10만단위
    } else if (total>=100_000){

        $("#mAllMileageSum").text(((Math.floor(total/100_000))*1_000).toLocaleString());
        //100만단위
    } else if (total>=1_000_000){

        $("#mAllMileageSum").text(((Math.floor(total/1_000_000))*10_000).toLocaleString());
    } else if (total>=10_000_000){

        $("#mAllMileageSum").text(((Math.floor(total/10_000_000))*100_000).toLocaleString());
    }
    else {
        $("#mAllMileageSum").text('0');
    }

}
/*
function debuggingajax(){
    let date = new Date();
    let SoldObject = [];
    for(let i = 0 ; i<$("#product_table > tbody > tr").length; i++){
        var soldObjectTemp =
            {
                optionId:$(`input[name='optionId']:eq(${i})`).val(),
                soldQuantity:$(`.product_quantity:eq(${i})`).text().trim()
            }
        SoldObject.push(soldObjectTemp);
    }
    let soldDto = JSON.stringify(SoldObject);
    let orderDto = JSON.stringify({
        userId:`${$("input[name='userNo']").val()}`,
        addrId:`${$("input[name='addrId']").val()}`,
        addr:`${$("input[name='addrZipcode']").val()} ${$("input[name='addr']").val()} ${$("input[name='addrDetail']").val()}`,
        orderStatus:'0',
        orderPhone:`${document.getElementsByName("mobile[]")[0].value.trim()}-${document.getElementsByName("mobile[]")[1].value.trim()}-${document.getElementsByName("mobile[]")[2].value.trim()}`,
        orderEmail:`${document.getElementsByName("email[]")[0].value.trim()}@${document.getElementsByName("email[]")[1].value.trim()}`,
        orderPurchase:`${removeCommaConvertToNumber($("#total_price").val())}`,
        orderMessage:`${$("input[name='orderMessage']").val()}`,
        orderDeliveryStatus:"1",
        orderDate:`${rsp.paid_at}`,
        orderCardNum:`****`,
        orderTransName:`${$("input[name='orderTransName']").val()}`,
        orderMethod:`${rsp.pay_method}`,
        orderKey:`${rsp.receipt_url}`,
        orderShipfee:`${removeCommaConvertToNumber($(".product_tfoot_shipfee").text())}`,
    });
    let addressDto = JSON.stringify({
        addrId:`${$("input[name='addrId']").val()}`,
        userId:$("input[name='userNo']").val(),
        addrReceiver:$("input[name='addrReceiver']").val(),
        addrZipcode:$("input[name='addrZipcode']").val(),
        addr:$("input[name='addr']").val(),
        addrDetail:$("input[name='addrDetail']").val(),
        addrReloadCheck:$("input[name='addrReloadCheck']").val(),
        addrCellPhone:`${document.getElementsByName("mobile[]")[0].value.trim()}-${document.getElementsByName("mobile[]")[1].value.trim()}-${document.getElementsByName("mobile[]")[2].value.trim()}`,
    });
    let usedMileageDto = JSON.stringify({
        userId:$("input[name='userNo']").val(),
        mileage:$("input[name='mileage']").val()
    });
    let stackMileageDto = JSON.stringify({
        userId:$("input[name='userNo']").val(),
        mileage:`${removeCommaConvertToNumber($("#mAllMileageSum").text().trim())}`
    });
    $.ajax({
        url:"/pamajon/order/purchase",
        type:"POST",
        traditional: true,
        data:{orderDto,soldDto,addressDto,usedMileageDto,stackMileageDto},
        success:function (result){
            console.log(result);
        }
    })
}
*/
//receieve String
function removeCommaConvertToNumber(number){

    let numStr = "";
    for(let i = 0 ; i<number.trim().split(",").length; i++){
        numStr += number.trim().split(",")[i]
    }
    return Number(numStr);
}
/*
function emailTest(){

    $.ajax({
        url:"/pamajon/order/email",
        type: "POST",
        data: {to:`${document.getElementsByName("email[]")[0].value.trim()}@${document.getElementsByName("email[]")[1].value.trim()}`,
            productName:"aaa"},
        success:function (result){
            console.log(result);
        }
    })


}
*/

function purchaseTest(){

    let date = new Date();

    let SoldObject = [];

    for(let i = 0 ; i<$("#product_table > tbody > tr").length; i++){

        var soldObjectTemp =
            {
                optionId:$(`input[name='optionId']:eq(${i})`).val(),
                soldQuantity:$(`.product_quantity:eq(${i})`).text().trim()
            }
        SoldObject.push(soldObjectTemp);
    }
    let soldDto = JSON.stringify(SoldObject);

    let orderDto = JSON.stringify({
        userId:`${$("input[name='userNo']").val()}`,
        addrId:`${$("input[name='addrId']").val()}`,
        addr:`${$("input[name='addrZipcode']").val()} ${$("input[name='addr']").val()} ${$("input[name='addrDetail']").val()}`,
        orderStatus:'0',
        orderPhone:`${document.getElementsByName("mobile[]")[0].value.trim()}-${document.getElementsByName("mobile[]")[1].value.trim()}-${document.getElementsByName("mobile[]")[2].value.trim()}`,
        orderEmail:`${document.getElementsByName("email[]")[0].value.trim()}@${document.getElementsByName("email[]")[1].value.trim()}`,
        orderPurchase:`200`,
        orderMessage:`${$("input[name='orderMessage']").val()}`,
        orderDeliveryStatus:"1",
        orderDate:`${date.getFullYear()}/${date.getMonth()}/${date.getDate()} ${date.getHours()}:${date.getMinutes()}`,
        orderCardNum:`4444****`,
        orderTransName:`${$("input[name='orderTransName']").val()}`,
        orderMethod:`card`,
        orderKey:`test`,
        orderShipfee:`${removeCommaConvertToNumber($(".product_tfoot_shipfee").text())}`,
    });

    let addressDto = JSON.stringify({
        addrId:`${$("input[name='addrId']").val()}`,
        userId:`${$("input[name='userNo']").val()}`,
        addrReceiver:$("input[name='addrReceiver']").val(),
        addrZipcode:$("input[name='addrZipcode']").val(),
        addr:$("input[name='addr']").val(),
        addrDetail:$("input[name='addrDetail']").val(),
        addrReloadCheck:$("input[name='addrReloadCheck']").val(),
        addrCellPhone:`${document.getElementsByName("mobile[]")[0].value.trim()}-${document.getElementsByName("mobile[]")[1].value.trim()}-${document.getElementsByName("mobile[]")[2].value.trim()}`,

    });

    let usedMileageDto = JSON.stringify({
        userId:$("input[name='userNo']").val(),
        mileage:$("input[name='mileage']").val()
    });

    let stackMileageDto = JSON.stringify({
        userId:$("input[name='userNo']").val(),
        mileage:`${removeCommaConvertToNumber($("#mAllMileageSum").text().trim())}`
    });
        $.ajax({
            url:"/pamajon/order/purchase",
            type:"POST",
            traditional: true,
            data:{orderDto,soldDto,addressDto,usedMileageDto,stackMileageDto},
            success:function (result){
                if(result>0){
                    $.ajax({
                        url:"/pamajon/order/email",
                        type: "POST",
                        data: {to:`${document.getElementsByName("email[]")[0].value.trim()}@${document.getElementsByName("email[]")[1].value.trim()}`,
                            productName:"aaa"},
                        success:function (result){
                            if(result>0){
                                location.href='/pamajon/member/orderList';
                            }
                        }
                    })
                }
            }
        })

}




