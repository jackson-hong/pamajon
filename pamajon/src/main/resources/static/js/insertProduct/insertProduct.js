'use strict'

window.onload = function (){

    //브랜드 가져옴.
        const httpRequestFir = new XMLHttpRequest();

        httpRequestFir.open('GET','/adminProduct/brand',true);
        httpRequestFir.send();
        httpRequestFir.onload = function (result){

            if(httpRequestFir.status == 200){

                const responseJSON = JSON.parse(httpRequestFir.response);
                let brantListStr = "";

                for (let i = 0 ; i<responseJSON.length; i++){
                    brantListStr+=`<span onclick="brandSelector(${responseJSON[i].brandId},this)" class="brand ${responseJSON[i].brandId}" id='brand${responseJSON[i].brandId}'>${responseJSON[i].brandName}</span>`
                }
                document.querySelector("#brandB > li").innerHTML = brantListStr;
            }
        }
    //대 카테고리 가져옴.
    //class="selectedEffect"
        const httpRequestSec = new XMLHttpRequest();

        httpRequestSec.open('GET',`/adminProduct/wrapcategory`,true);
        httpRequestSec.send();

        httpRequestSec.onload = function (result){

            if(httpRequestSec.status == 200){

                const responseJSON = JSON.parse(httpRequestSec.response);
                let wrapperCateList = "";

                for (let i = 0 ; i<responseJSON.length; i++){
                    wrapperCateList+=`<span onclick="categorySelector(${responseJSON[i].wrapperCategoryId},this)" 
                                            class="wrappCategory ${responseJSON[i].wrapperCategoryId}" 
                                            id='wrappCategory${responseJSON[i].wrapperCategoryId}'>${responseJSON[i].wrapperCategoryName}</span>`
                }
                document.querySelector("#wrapcategory > li").innerHTML = wrapperCateList;

            }
        }
            //원산지 가져옴.
            const httpRequestThir = new XMLHttpRequest();

            httpRequestThir.open('GET',`/adminProduct/origin`,true);
            httpRequestThir.send();

            httpRequestThir.onload = function (){

                if(httpRequestThir.status == 200){
                    const responseJSON = JSON.parse(httpRequestThir.response);
                    let originList = "";
                    for (let i = 0 ; i<responseJSON.length; i++){

                        if(responseJSON[i].commonCodeName=='한국'){
                            originList+=`<option value="${responseJSON[i].commonCodeId}" selected>${responseJSON[i].commonCodeName}</option>`
                        } else {
                            originList+=`<option value="${responseJSON[i].commonCodeId}">${responseJSON[i].commonCodeName}</option>`
                        }



                    }
                    document.getElementById("CountryOfOrigin").innerHTML=originList;
                }

        }
        //사이즈 가져옴.
        const httpRequestFour = new XMLHttpRequest();
    httpRequestFour.open('GET',`/adminProduct/sizeoption/COMSIZE01`,true);
    httpRequestFour.send();

    httpRequestFour.onload = function (){
            //성공.
            const result = JSON.parse(httpRequestFour.response);
            let sizeList = "";
            if(httpRequestFour.status==200){

                for (let i = 0 ; i<result.length; i++){
                    if(result[i].commonCodeName=='M' || result[i].commonCodeName=='FREESIZE' || result[i].commonCodeName=='28')
                    {
                        sizeList +=`<option value="${result[i].commonCodeName}" selected>${result[i].commonCodeName}</option>`;
                    } else {
                        sizeList +=`<option value="${result[i].commonCodeName}">${result[i].commonCodeName}</option>`;
                    }
                }

            }
            document.getElementById("sizeDetail").innerHTML=sizeList;

         }


}

    function categorySelector(wrapperId,node){

        const wrapCategory = document.querySelectorAll(".wrappCategory");
        //리셋 함수 작동시 node 에 null 이 들어올 수 있음.
        if(node != null){
            for(let i = 0 ; i<wrapCategory.length; i++){
                wrapCategory[i].classList.remove("selectedEffect");
            }
            //클릭된것에 css 적용함.
            node.classList.add("selectedEffect");
        }


        const httpRequest = new XMLHttpRequest();
        httpRequest.open('GET',`/adminProduct/category/${wrapperId}`,true);
        httpRequest.send();
        httpRequest.onload = function (result){

            if(httpRequest.status == 200){

                const responseJSON = JSON.parse(httpRequest.response);
                let CateList = "";

                for (let i = 0 ; i<responseJSON.length; i++){
                    CateList+=`<span onclick="categoryDetailSelector(${responseJSON[i].productCategoryId},this)" 
                                     class="category ${responseJSON[i].productCategoryId}" 
                                     id='category${responseJSON[i].productCategoryId}'>${responseJSON[i].productCategoryName}</span>`
                }
                document.querySelector("#categoryDetail > li").innerHTML = CateList;
                //성공시 value 셋팅함.
                document.querySelector('input[name="productBigCateId"]').value=wrapperId;

            }
        }



    }
    function categoryDetailSelector(e,node){

        const brandList = document.querySelectorAll(".category");

        for(let i = 0 ; i<brandList.length; i++){
            brandList[i].classList.remove("selectedEffect");
        }
        //클릭된것에 css 적용함.
        node.classList.add("selectedEffect");
        //value 셋팅함.
        document.querySelector('input[name="productCategory"]').value=e;

    }
// 수량 변경.
    const quantityRadio = document.getElementsByName('quantity');

    quantityRadio.forEach((element)=>{

        element.addEventListener('change',function(){

            if(element.value=='unlimited'){
                document.querySelector('input[name="inputProductQuantity"]').style.display='none';
            } else {
                document.querySelector('input[name="inputProductQuantity"]').style.display='block';
            }

        })

    })
// 재고 수량 수정
function modifyQuantityFromModal(e){
        if(e.checked){
            e.parentNode.childNodes[1].removeAttribute('readonly');
        } else {
            e.parentNode.childNodes[1].setAttribute('readonly',true);
        }
}

function saveProductOption(e){
        e.parentNode.parentNode.parentNode.style.display='none';
}

function brandSelector(e,node){
        //브랜드 선택됨.
      const brandList = document.querySelectorAll(".brand");
      for(let i = 0 ; i<brandList.length; i++){
          brandList[i].classList.remove("selectedEffect");
      }
      node.classList.add("selectedEffect");
        //브랜드가 바뀌었을때 모든 셋팅이 리셋 되어야함.
      document.querySelector('input[name="productBrand"]').value=e;
      document.querySelector('input[name="productBigCateId"]').value='';
      document.querySelector('input[name="productCategory"]').value='';
    // 카테고리 리셋.
    categorySelector(-1,null);
    //대카테고리 리셋
    const wrapcategory = document.querySelectorAll(".wrappCategory");
    for(let i = 0 ; i<wrapcategory.length; i++){
        wrapcategory[i].classList.remove("selectedEffect");
    }
    //카테고리 리셋
    const category = document.querySelectorAll(".category");
    for(let i = 0 ; i<category.length; i++){
        category[i].classList.remove("selectedEffect");
    }

}


    /* 옵션 등록 function 자리 */
    document.getElementById("sizeCategory").addEventListener('change',function(){

        const httpRequest = new XMLHttpRequest();
        httpRequest.open('GET',`/adminProduct/sizeoption/${this.value}`,true);
        httpRequest.send();

        httpRequest.onload = function (){
        //성공.
            const result = JSON.parse(httpRequest.response);
            let sizeList = "";
        if(httpRequest.status==200){

            for (let i = 0 ; i<result.length; i++){
                if(result[i].commonCodeName=='M' || result[i].commonCodeName=='FREESIZE' || result[i].commonCodeName=='28')
                {
                    sizeList +=`<option value="${result[i].commonCodeName}" selected>${result[i].commonCodeName}</option>`;
                } else {
                    sizeList +=`<option value="${result[i].commonCodeName}">${result[i].commonCodeName}</option>`;
                }
            }

        }
        document.getElementById("sizeDetail").innerHTML=sizeList;

        }

    });

    function addOption(){
        //상품 수량 제한이 있지만 상품 수량을 입력 안한경우 return;
        if(document.querySelector('input[name="quantity"]:checked').value == 'limited'
         &&document.querySelector('input[name="inputProductQuantity"]').value==''){

            alert("상품 수량을 입력해주세요");
            document.querySelector('input[name="inputProductQuantity"]').focus();
            return;

        }
        //모달 존재함.
        const quantityDisplayNode = document.getElementsByClassName("modalQuantityDisplay");

        if(quantityDisplayNode != null){

            for(let i = 0 ; i<quantityDisplayNode.length;i++){

                if(quantityDisplayNode[i].value == document.getElementById("sizeDetail").value){
                    alert("이미 등록된 옵션입니다.")
                    return;
                }

            }

        }
        let optionCount;

        if(document.getElementsByClassName("optionModal").length == 0){
             optionCount = 0;
        } else {
            //가장 최근에 만들어진 modal 에 번호 추가됨.
            optionCount = Number(document.
                              getElementsByClassName("optionModal")
                              [document.getElementsByClassName("optionModal")
                              .length-1]
                              .className
                              .split(" ")[1]) + 1;
        }
        //동적으로 Modal 생성.
        if(document.getElementsByName("quantity").value == 'unlimited'){
            const quantityValue = -1;
        }
        if(document.getElementsByName("quantity").value == 'limited'){
            const quantityValue = document.getElementById("inputProductQuantity").value;
        }




        document.getElementById("modalDisplayArea").innerHTML+=
            `
                         <div class="optionModal ${optionCount}" id="optionModal${optionCount}">
                                <h3 align="center" style="font-weight: bold">등록 옵션 ${optionCount}</h3>
                                <hr>
                                <table style="margin-bottom: 10px">
                                    <tbody>
                                        <tr>
                                            <td class="optionQuantitiy">상세사이즈:</td>
                                            <td class="optionQuantitiyDisplay"><input type="text" name="optionList[${optionCount}].optionSize" class="form-control modalQuantityDisplay" value="${document.getElementById("sizeDetail").value}" readonly style="width: 100px;"></td>
                                        </tr>
                                        <tr>
                                            <td class="optionQuantitiy">재고수량:</td>
                                            <td class="optionQuantitiyDisplay">
                                                <input type="number" class="form-control" value="-1" name="optionList[${optionCount}].optionQuantity" readonly style="width: 100px;">
                                                <input type="checkbox" id="quantityModify"  onclick="modifyQuantityFromModal(this)">
                                                <label for="quantityModify" style="margin: 0px">&nbsp;&nbsp;재고 수량 수정하기</label>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                                <div class="optionButtonBox">
                                    <div class="buttonWrapper">
                                    <button type="button" class="btn btn-info" id="optionModify" onclick="saveProductOption(this)">저장</button>
                                    <button type="button" class="btn btn-info" id="optionDelete" onclick="deleteOption(this)">삭제</button>
                                    </div>
                                </div>
                            </div>
            `

        document.getElementById("userInputButton").innerHTML+=`<button type="button" 
                                                                                class="btn btn-info ${optionCount}" 
                                                                                id="optionModalBtn${optionCount}"
                                                                                onclick="optionModalDisplay(${optionCount},this)">등록 옵션 ${optionCount}</button>`
    }



    function optionModalDisplay(e,node){

        const modalList = document.getElementsByClassName("optionModal");

        for(let i = 0 ; i<modalList.length;i++){
            modalList[i].style.display='none';
        }
        document.getElementById(`optionModal${e}`).style.display='block';

    }

    function deleteOption(node){

        const topLevelModal = node.parentNode.parentNode.parentNode;
        //모달 버튼 삭제
        document.getElementById(`optionModalBtn${topLevelModal.className.split(" ")[1]}`).remove();
        //모달 버튼 삭제후 모달 삭제
        topLevelModal.remove();

    }

    function deleteAll(){
        if(confirm("삭제가 진행된후 상품옵션이 저장되지 않습니다 진행하시겠씁니까?")){
        let modals = document.getElementById("modalDisplayArea");
        let btnParentNode = document.getElementById("userInputButton");
        console.log(modals.length);

        //modal 삭제
        while (modals.hasChildNodes()){
            modals.removeChild( modals.firstChild );
        }

        //버튼삭제
        while (btnParentNode.hasChildNodes()){

            btnParentNode.removeChild( btnParentNode.firstChild );

        }

        }

    }

    function productInsert(){

       if(document.querySelector("input[name='productBrand']").value==''){
            alert("브랜드 선택은 필수 항목입니다.")
            return;
        }
        if(document.querySelector("input[name='productBigCateId']").value==''){
            alert("대카테고리 선택은 필수 항목입니다.")
            return;
        }
        if(document.querySelector("input[name='productCategory']").value==''){
            alert("소카테고리 선택은 필수 항목입니다.")
            return;
        }
        if(document.querySelector("input[name='productName']").value==''){
            alert("상품명은 필수 입력 항목입니다.")
            document.querySelector("input[name='productName']").focus();
            return;
        }
        if(document.querySelector("input[name='productPrice']").value==''){
            alert("상품 가격은 필수 입력 항목입니다.")
            document.querySelector("input[name='productPrice']").focus();
            return;
        }
        if(document.querySelector("input[name='productColor']").value==''){
            alert("상품 색상은 필수 입력 항목입니다.")
            document.querySelector("input[name='optionColor']").focus();
            return;
        }
        if(document.getElementById("ex_file2").files.length==0){
            alert("최소 1개 이상의 사진을 올려주세요.")
            return;
        }
        if(CKEDITOR.instances['editor1'].getData()==''){
            alert("멋진 상품 설명을 작성해 주세요")
            return;
        }

        if(document.getElementById("userInputButton").childNodes.length==1){
            alert("최소 1개 이상의 상품 옵션이 등록되어야 합니다.")
            return;
        }

        if(confirm("상품을 등록하시겠습니까?")){

            const insertForm = document.getElementById("productInsert");
            insertForm.method="POST";
            insertForm.action="/adminProduct/product"
            insertForm.submit();


        } else {
            return;
        }





    }

