'use strict'

$("select[name='optionId']").on('change', function () {

    const selectedOptionArr = new Array();
    const selectedText = this.options[this.selectedIndex].text;

    for (let i = 0; i < $(".purchaseInfoDisplay").length; i++) {

        selectedOptionArr.push($(".purchaseInfoDisplay").get(i).children[1].innerHTML.trim(" "));

    }
    console.log(selectedOptionArr);
    console.log(selectedText);

    if (!selectedOptionArr.includes(selectedText) && $("select[name='optionId']").val() != "empty") {

        let productOption = "";
        let arrayNum = 0;

        if($("#productOptionDisplay > tbody > tr").length==0){
            arrayNum = 0;
        } else {

            arrayNum = parseInt($(".optionArr:last").attr('name').substring($(".optionArr:last").attr('name').indexOf("[") + 1, $(".optionArr:last").attr('name').indexOf("[") + 2)) + 1;


        }
        productOption +=
            `<tr class="purchaseInfoDisplay">
                    <th style="display: none">
                    <input type="hidden" class="optionArr"  name="optionList[${arrayNum}].optionId" value="${$("select[name='optionId']").val()}">
                    </th>        
                    <th style="width:50%">${this.options[this.selectedIndex].text}
                    </th>
                    <th style="display: flex; padding-top: 5px;" >
                    <input type="number" value="1" min="1" onchange="priceCal(event);" name="optionList[${arrayNum}].optionQuantity" style="width:80px" >
                    <div onclick="optionDelete(this)" style="cursor:pointer;">&nbsp;x</div></th>
                    <th align="center">89000</th>
                </tr>`;

        $("#productOptionDisplay > tbody").append(productOption);

        optionListCount()


    } else if ($("select[name='optionId']").val() != "empty") {
        alert("이미 선택한 옵션입니다.");
    }

})

function priceCal(event) {

    event.target.parentNode.parentNode.children[3].innerText = parseInt(event.target.value) * 89000;
    optionListCount()

}

function optionDelete(event) {

    event.parentNode.parentNode.remove()
    optionListCount()

}
//가격을 보여줌
function optionListCount() {

    let result = 0;

    if ($(".purchaseInfoDisplay").length != 0) {

        for (let i = 0; i < $(".purchaseInfoDisplay").length; i++) {

            result += parseInt($(".purchaseInfoDisplay").get(i).children[3].innerText);
        }
    }
    $("#priceDisplay").html(`Total:${result} KRW`);
}

function submitOption(){

    if($("#productOptionDisplay > tbody > tr").length==0){
        alert("필수 옵션을 선택해주세요");
        $("input[name='productId']").focus();
    } else {
        $("#sendProductOption").attr("action","/pamajon/order/purchase");
        $("#sendProductOption").attr("method","GET");
        $("#sendProductOption").submit();
    }



}

