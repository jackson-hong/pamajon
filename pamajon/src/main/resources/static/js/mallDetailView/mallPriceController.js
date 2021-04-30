'use strict'

$("select[name='optionId']").on('change', function () {

    const selectedOptionArr = new Array();
    const selectedText = this.options[this.selectedIndex].text;

    for (let i = 0; i < $(".purchaseInfoDisplay").length; i++) {

        selectedOptionArr.push($(".purchaseInfoDisplay").get(i).children[1].innerHTML.trim(" "));

    }

    if (!selectedOptionArr.includes(selectedText) && $("select[name='optionId']").val() != "empty") {

        let productOption = "";
        let arrayNum = 0;

        if($("#productOptionDisplay > tbody > tr").length==0){
            arrayNum = 0;
        } else {

            arrayNum = parseInt($(".optionArr:last").attr('name').substring($(".optionArr:last").attr('name').indexOf("[") + 1, $(".optionArr:last").attr('name').indexOf("[") + 2)) + 1;

        }
        productOption +=
            `<tr class="purchaseInfoDisplay" style="height: 40px;">
                    <th style="display: none">
                        <input type="hidden" class="optionArr"  name="optionList[${arrayNum}].optionId" value="${$("select[name='optionId']").val()}">
                    </th>        
                    <th style="width:50%;">${this.options[this.selectedIndex].text}
                    </th>
                    <th style="display: flex; padding: 10px 15px 15px 15px;" >
                        <input type="number" value="1" 
                               class="optionQuantity ${arrayNum}" 
                               id="optionQuantity${arrayNum}" 
                               min="1" 
                               onchange="priceCal(${arrayNum},this);" 
                               name="optionList[${arrayNum}].optionQuantity" style="width:50px; border: 1px solid lightgray" >
                    <div onclick="optionDelete(this)" style="cursor:pointer;">
                        &nbsp;<img src="//img.echosting.cafe24.com/design/skin/default/product/btn_price_delete.gif" alt="삭제" id="option_box1_del" class="option_box_del"></div></th>
                    <th style="border: width: 100%">
                        <span class="ec-front-product-item-price" style="display: block; text-align: right; font-weight: bold; color: #008bcc">KRW 
                            <span class="productSingleTotal ${arrayNum}" id="productSingleTotal${arrayNum}">${$(productPrice).html()}</span>
                        </span>
                       <span class="mileage" id="mileage${arrayNum}" style="display: block; text-align: right; font-weight: bold; color: #008bcc;">(
                            <img src="//img.echosting.cafe24.com/design/skin/admin/ko_KR/ico_product_point.gif" alt="적립금"> 
                            <span id="option_box1_mileage">KRW 
                                <span class="productSingleMileageTotal ${arrayNum}" id="productSingleMileageTotal${arrayNum}">${numberFormatter($(productPrice).html()) >= 50000 ? (numberFormatter($(productPrice).html()) * 0.01).toLocaleString() : 0}</span>
                            </span>)
                       </span>
                    </th>
                </tr>`;

        $("#productOptionDisplay > tbody").append(productOption);
        $("#productTotalPrice").html(totalPriceReturn() + " KRW");
        $("#totalQuantityDisplay").html("("+totalQuantityReturn()+")개")

    } else if ($("select[name='optionId']").val() != "empty") {
        alert("이미 선택한 옵션입니다.");
    }
})

function priceCal(e,node) {

     $(`#productSingleTotal${e}`).html((numberFormatter(($(productPrice).html()))*node.value).toLocaleString());
     if(numberFormatter(($(productPrice).html()))*node.value >= 50000){
         $(`#productSingleMileageTotal${e}`).html(((numberFormatter(($(productPrice).html()))*node.value)*0.01).toLocaleString());
     }
     if(numberFormatter(($(productPrice).html()))*node.value < 50000){
         $(`#productSingleMileageTotal${e}`).html('0');
     }

     $("#productTotalPrice").html(totalPriceReturn() + " KRW");
}

// 최종 가격 리턴해주는 함수
function totalPriceReturn(){
    let totalPrice = 0;
    const $priceDisplayNode = $(".productSingleTotal");

    for(let i = 0 ; i<$priceDisplayNode.length; i++){
        totalPrice+=numberFormatter($priceDisplayNode[i].innerHTML);
    }
    return totalPrice.toLocaleString();
}
function totalQuantityReturn(){

    let totalQuantity = 0;
    const $quantityDisplayNode = $(".optionQuantity");

    for(let i = 0 ; i<$quantityDisplayNode.length; i++){
        totalQuantity+=numberFormatter($quantityDisplayNode[i].value);
    }
    return totalQuantity.toLocaleString();


}

function optionDelete(event) {

    event.parentNode.parentNode.remove()
    $("#productTotalPrice").html(totalPriceReturn() + " KRW");
    $("#totalQuantityDisplay").html("("+totalQuantityReturn()+")개")

}

function submitOption(){

    if($("#productOptionDisplay > tbody > tr").length==0){
        alert("필수 옵션을 선택해주세요");
        $("input[name='productId']").focus();
    } else {
        $("#sendProductOption").attr("action","/order/purchase");
        $("#sendProductOption").attr("method","GET");
        $("#sendProductOption").submit();
    }
}


function numberFormatter(stirngNumber){

 return Number(stirngNumber.split(',').join(''));
}