'use strict'

$("select[name='productOption']").on('change', function () {



    const selectedOptionArr = new Array();

    for (let i = 0; i < $(".purchaseInfoDisplay").length; i++) {

        selectedOptionArr.push($(".purchaseInfoDisplay").get(i).children[0].innerText);
    }

    if (!selectedOptionArr.includes($("select[name='productOption']").val()) && $("select[name='productOption']").val() != "empty") {

        let productOption = "";

        let arrayNum = 0;

        if ($(".purchaseInfoDisplay").length != 0) {

            arrayNum = parseInt($(".purchaseInfoDisplay").get($(".purchaseInfoDisplay").length - 1).children[0].children[0].getAttribute('name').substring(
                $(".purchaseInfoDisplay").get($(".purchaseInfoDisplay").length - 1).children[0].children[0].getAttribute('name').lastIndexOf("[") + 1,
                $(".purchaseInfoDisplay").get($(".purchaseInfoDisplay").length - 1).children[0].children[0].getAttribute('name').lastIndexOf("[") + 2
            )) + 1;
        }
        productOption +=
            `<tr class="purchaseInfoDisplay">
            <th style="width:50%">${$("select[name='productOption']").val()}<input type="hidden"  name="productSize[${parseInt(arrayNum)}]" value="${$("select[name='productOption']").val()}">
            </th>
            <th style="display: flex; padding-top: 5px;" >
            <input type="number" value="1" min="1" onchange="priceCal(event);" name="quantity[${parseInt(arrayNum)}]" style="width:80px" >
            <div onclick="optionDelete(this)" style="cursor:pointer;">&nbsp;x</div></th>
            <th align="center">89000</th>
                </tr>`;

        $("#productOptionDisplay > tbody").append(productOption);

        optionListCount()


    } else if ($("select[name='productOption']").val() != "empty") {
        alert("이미 선택한 옵션입니다.");
    }

})

function priceCal(event) {

    event.target.parentNode.parentNode.children[2].innerText = parseInt(event.target.value) * 89000;
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

            result += parseInt($(".purchaseInfoDisplay").get(i).children[2].innerText);

        }

    }

    $("#priceDisplay").html(`Total:${result} KRW`);
}





/*
    `  <tr class="purchaseInfoDisplay">
<th style="width:50%">M(95~100)</th>
<th style="display: flex; padding-top: 5px;" ><input type="number" name="quantity" style="width:80px" ><div>&nbsp;x</div></th>
<th align="center">22000</th>
</tr>`

*/