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

            console.log(pageInfo);

            //결제된 사항이 아무것도 없음. Page정보만 넘어왔으므로 아무일도 일어나지 않음.
            if(shipmentList.length==1){
                return false;
            }
            //결제된 사항이 있음.
            if(shipmentList.length>1){

                let str="";
                let deliveryStatus = ["beforeDepositComplete","pending","shipped","delivered"];
                for(let i = 0 ; i<shipmentList.length;i++){

                str+=`
                 <tr class="shippingList">
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
                for(let i = 1; i<=pageInfo.endPage;i++){
                    pageStr+=`<li class="page-item" onclick="getshipmentList(${i})"><a class="page-link" href="#">${i}</a></li>`;
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
