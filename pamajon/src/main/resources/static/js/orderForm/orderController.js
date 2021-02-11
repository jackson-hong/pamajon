'use strict'

$("#btn_shipp_addr").on('click',function (){
    window.open( "/pamajon/order/addresslist",
               "주소입력창",
             "width=800, height=800, toolbar=no, menubar=no, scrollbars=no, resizable=yes",
             "_blank" );
})
$("input[name='shippingAddr']").on('click',function(){

    if($("input[name='shippingAddr']:checked").val()=="basic"){
        getAddress();
    } else {
        $("input[name='addrName']").val('');
        $("input[name='addrReceiver']").val('');
        $("input[name='addrZipcode']").val('');
        $("input[name='addr']").val('');
        $("input[name='addrDetail']").val('');
        document.getElementsByName("mobile[]")[0].value='010';
        document.getElementsByName("mobile[]")[1].value='';
        document.getElementsByName("mobile[]")[2].value='';
    }

})
getAddress();
function getAddress(){

    $.ajax({
        url:"/pamajon/order/regularAddress",
        type:"GET",
        data:{userNo:`${$("input[name='userNo']").val()}`},
        success:function (result){
            console.log(result);
            $("input[name='addrName']").val(result.addrName);
            $("input[name='addrReceiver']").val(result.addrReceiver);
            $("input[name='addrZipcode']").val(result.addrZipcode);
            $("input[name='addr']").val(result.addr);
            $("input[name='addrDetail']").val(result.addrDetail);

            let cellArr = result.addrCellPhone.split("-");
            document.getElementsByName("mobile[]")[0].value=cellArr[0];
            document.getElementsByName("mobile[]")[1].value=cellArr[1];
            document.getElementsByName("mobile[]")[2].value=cellArr[2];

        }
    })
}
