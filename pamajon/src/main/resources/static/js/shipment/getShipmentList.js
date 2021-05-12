'use strict'

getshipmentList(1)

function getshipmentList(pageNum){

    const shipmentListHttpRequest = new XMLHttpRequest();
    shipmentListHttpRequest.open("GET",`/admin/shipmentList/${pageNum}`,true);
    shipmentListHttpRequest.send();

    shipmentListHttpRequest.onload = function (result){

        if(shipmentListHttpRequest.status==200){

            //주문배열 리스트
            const shipmentList = JSON.parse(shipmentListHttpRequest.response);

            //마지막 배열이 페이지 정보를 담고있음
            const pageInfo = shipmentList[shipmentList.length-1];
            shipmentList.pop();

            //결제된 사항이 아무것도 없음. Page정보만 넘어왔으므로 아무일도 일어나지 않음.
            if(shipmentList.length==0){
                return false;
            }
            //결제된 사항이 있음.
            if(shipmentList.length>0){

                let str="";
                let deliveryStatus = ["beforeDepositComplete","pending","shipped","delivered"];
                for(let i = 0 ; i<shipmentList.length;i++){

                str+=`
                 <tr class="shippingList" onclick="showupShipmentDetailPopup(this)">
                    <td style="vertical-align: middle">${shipmentList[i].orderId}</td>
                    <td style="vertical-align: middle">${shipmentList[i].orderEmail}</td>
                    <td style="vertical-align: middle">${shipmentList[i].receiver}</td>
                    <td style="vertical-align: middle">${shipmentList[i].orderPhone}</td>
                    <td style="vertical-align: middle">${shipmentList[i].orderPurchase}</td>`

                    if(shipmentList[i].orderDeliveryStatus==0){
                        str+=`<td style="vertical-align: middle" class="${deliveryStatus[shipmentList[i].orderDeliveryStatus]}">입금전</td>`
                    }
                    if(shipmentList[i].orderDeliveryStatus==1){
                        str+=`<td style="vertical-align: middle" class="${deliveryStatus[shipmentList[i].orderDeliveryStatus]}">배송준비중</td>`
                    }
                    if(shipmentList[i].orderDeliveryStatus==2){
                        str+=`<td style="vertical-align: middle" class="${deliveryStatus[shipmentList[i].orderDeliveryStatus]}">배송중</td>`
                    }
                    if(shipmentList[i].orderDeliveryStatus==3){
                        str+=`<td style="vertical-align: middle" class="${deliveryStatus[shipmentList[i].orderDeliveryStatus]}">배송완료</td>`
                    }
                    str+=`<td style="vertical-align: middle">${shipmentList[i].orderDate}</td>
                </tr>
                `
                }
                // 결제 리스트
                document.getElementById("shipmentListDisplayArea").innerHTML=str;

                let pageStr = "";
                pageStr+= `<li class="page-item" onclick="getshipmentList(1)"><a class="page-link" href="#">&laquo;</a></li>`
                if(pageInfo.currentPage>10){
                    pageStr+=`<li class="page-item" onclick="getshipmentList(${pageInfo.startPage-10})"><a class="page-link" href="#">&lt;</a></li>`
                }
                for(let i = pageInfo.startPage; i<=pageInfo.endPage;i++){
                    if(i==pageInfo.currentPage){

                        pageStr+=`<li class="page-item" onclick="getshipmentList(${i})" ><a class="page-link" style="color: red" href="#">${i}</a></li>`;
                    }
                    if(i!=pageInfo.currentPage) {
                        pageStr += `<li class="page-item" onclick="getshipmentList(${i})"><a class="page-link" href="#">${i}</a></li>`;
                    }
                }
                //10칸 앞으로 갈 여지가 남아있을때.
                if(pageInfo.endPage+1<pageInfo.maxPage){
                    pageStr+=`<li class="page-item" onclick="getshipmentList(${pageInfo.startPage+10})"><a class="page-link" href="#">&gt;</a></li>`
                }
                pageStr+=`<li class="page-item" onclick="getshipmentList(${pageInfo.maxPage})"><a class="page-link" href="#">&raquo;</a></li>`;

                document.getElementById("pagination").innerHTML=pageStr;

            }

        }
    }

}
//검색버튼 클릭했을시 parameter값 담길 JSON;
let searchParameter;

//첫 1페이지 검색 구현 함수
function getShipmentListSearch(pageNum){

    if(!document.getElementById("searchAllWithoutDate").checked){

        if( searchEndDate.value=='' || searchstartDate.value==''){
            alert("검색 시작날짜와 마지막 날짜를 넣어주세요.");
            return;
        }
    }

    if(document.getElementById("searchOrderValue").value.trim(" ")==''){
        alert("검색어를 넣어주세요.");
        document.getElementById("searchOrderValue").value='';
        document.getElementById("searchOrderValue").focus();
        return;
    }
    let searchDateOptionCheckBox = "";

    if(document.getElementById("searchAllWithoutDate").checked){
        searchDateOptionCheckBox="checked";
    }
    if(!document.getElementById("searchAllWithoutDate").checked){
        searchDateOptionCheckBox="unchecked";
    }

    //검색 모든조건 통과.
    searchParameter = JSON.stringify(
        {
            pageNum:`${pageNum}`,
            searchOption:`${document.getElementById("searchOption").value}`,
            startDate:`${document.getElementById("shippingSearch-startDate").value}`,
            endDate:`${document.getElementById("shippingSearch-endDate").value}`,
            searchDateOptionCheckBox:`${searchDateOptionCheckBox}`,
            searchOrderValue:`${document.getElementById("searchOrderValue").value}`
        }
    )

    $.ajax({
        type:"POST",
        url:"/admin/shipmentList",
        data:{searchParameter},
        success:function (ShipmentListBySearchResult){
            const shipmentList = ShipmentListBySearchResult;
            const pageInfo = ShipmentListBySearchResult[ShipmentListBySearchResult.length-1];
            // ShipmentListBySearchResult 는 자바단에서 페이지 정보도 같이 가져오기 때문에 마지막에 포함되어있는 페이지 객체를 삭제해주어야함.
            shipmentList.pop();

            if(shipmentList.length>0){
                //리스트 생성
                createShipmentListBySearchResult(shipmentList);
                //페이지바 생성
                createPageBySearchResult(pageInfo);
            }
        }
    })
}

function getShipmentListByPageAndSearh(pageNum){

    //전역변수로 설정된 searchParameter 는 String 타입으로 설정되어있어 JSON 으로 변형후 pageNum 을 변경한 다음
    //서버로 다시 보내줘야함.
    let searchParameterByPage = JSON.parse(searchParameter);
    searchParameterByPage.pageNum=`${pageNum}`;
    searchParameterByPage=JSON.stringify(searchParameterByPage);

    $.ajax({
        type:"POST",
        url:"/admin/shipmentlistbypage",
        data:{searchParameterByPage},
        success:function (ShipmentListBySearchResult){
            const shipmentList = ShipmentListBySearchResult;
            const pageInfo = ShipmentListBySearchResult[ShipmentListBySearchResult.length-1];
            // ShipmentListBySearchResult 는 자바단에서 페이지 정보도 같이 가져오기 때문에 마지막에 포함되어있는 페이지 객체를 삭제해주어야함.
            shipmentList.pop();

            if(shipmentList.length>0){
                //리스트 생성
                createShipmentListBySearchResult(shipmentList);
                //페이지바 생성
                createPageBySearchResult(pageInfo);
            }
        }
    })


}
function createShipmentListBySearchResult(shipmentList){

    let str="";
    let deliveryStatus = ["beforeDepositComplete","pending","shipped","delivered"];

    for(let i = 0 ; i<shipmentList.length;i++){

        str+=`
                 <tr class="shippingList" onclick="showupShipmentDetailPopup(this)">
                    <td style="vertical-align: middle">${shipmentList[i].orderId}</td>
                    <td style="vertical-align: middle">${shipmentList[i].orderEmail}</td>
                    <td style="vertical-align: middle">${shipmentList[i].receiver}</td>
                    <td style="vertical-align: middle">${shipmentList[i].orderPhone}</td>
                    <td style="vertical-align: middle">${shipmentList[i].orderPurchase}</td>`

        if(shipmentList[i].orderDeliveryStatus==0){
            str+=`<td style="vertical-align: middle" class="${deliveryStatus[shipmentList[i].orderDeliveryStatus]}">입금전</td>`
        }
        if(shipmentList[i].orderDeliveryStatus==1){
            str+=`<td style="vertical-align: middle" class="${deliveryStatus[shipmentList[i].orderDeliveryStatus]}">배송준비중</td>`
        }
        if(shipmentList[i].orderDeliveryStatus==2){
            str+=`<td style="vertical-align: middle" class="${deliveryStatus[shipmentList[i].orderDeliveryStatus]}">배송중</td>`
        }
        if(shipmentList[i].orderDeliveryStatus==3){
            str+=`<td style="vertical-align: middle" class="${deliveryStatus[shipmentList[i].orderDeliveryStatus]}">배송완료</td>`
        }
        str+=`<td style="vertical-align: middle">${shipmentList[i].orderDate}</td>
                </tr>
                `
    }

    document.getElementById("shipmentListDisplayArea").innerHTML=str;

}

//검색 결과에 따라 페이지를 새로 생성.
function createPageBySearchResult(pageInfo){

    let pageStr = "";
    pageStr+= `<li class="page-item" onclick="getShipmentListByPageAndSearh(1)"><a class="page-link" href="#">&laquo;</a></li>`
    if(pageInfo.currentPage>10){
        pageStr+=`<li class="page-item" onclick="getShipmentListByPageAndSearh(${pageInfo.startPage-10})"><a class="page-link" href="#">&lt;</a></li>`
    }
    for(let i = pageInfo.startPage; i<=pageInfo.endPage;i++){
        if(i==pageInfo.currentPage){

            pageStr+=`<li class="page-item" onclick="getShipmentListByPageAndSearh(${i})" ><a class="page-link" style="color: red" href="#">${i}</a></li>`;
        }
        if(i!=pageInfo.currentPage) {
            pageStr += `<li class="page-item" onclick="getShipmentListByPageAndSearh(${i})"><a class="page-link" href="#">${i}</a></li>`;
        }
    }
    //10칸 앞으로 갈 여지가 남아있을때.
    if(pageInfo.endPage+1<pageInfo.maxPage){
        pageStr+=`<li class="page-item" onclick="getShipmentListByPageAndSearh(${pageInfo.startPage+10})"><a class="page-link" href="#">&gt;</a></li>`
    }
    pageStr+=`<li class="page-item" onclick="getShipmentListByPageAndSearh(${pageInfo.maxPage})"><a class="page-link" href="#">&raquo;</a></li>`;

    document.getElementById("pagination").innerHTML=pageStr;

}

function showupShipmentDetailPopup(node){

        window.open(
            `/admin/orderlist/${node.childNodes[1].innerHTML}`,
            "결제 상세내역",
            "width=800, height=800, toolbar=no, menubar=no, scrollbars=no, resizable=yes",
            "_blank"
        );

}