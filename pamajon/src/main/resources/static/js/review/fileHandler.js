'use strict'

function setThumbnail(event){
    let preview = document.querySelector("div#imgArea");
    let fileCounter = document.getElementById("ex_file2").files.length;
    let boxCount = 1;
    console.log(event.files);
    if(fileCounter > 5){
        alert("사진은 최대 5개까지만 업로드가 가능합니다. 5개 이하로 사진을 선택해 주십시오.");
    } else {

        for(let i = 0; i<$(".imgBox").length; i++){

            while($(".imgBox")[i].hasChildNodes()){
                $(".imgBox")[i].removeChild($(".imgBox")[i].firstChild);
            }
        }

        for(let image of event.files){
            let reader = new FileReader();

            reader.onload = function(event){

                let img = document.createElement('img');
                img.setAttribute('src',event.target.result);
                img.setAttribute('class','previewImg');
                $(`.imgBox.${boxCount}`).append(img);
                boxCount++;

            }
            reader.readAsDataURL(image);

        }



    }


}