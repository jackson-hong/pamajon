<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Pamajon</title>
    <link rel="stylesheet" type="text/css" href="resources/images/glider.css">
    <style>
        body {
            /*border:1px red solid;*/
            font: 0.75em Verdana,Dotum,AppleGothic,sans-serif;
            color: #353535;
        }
        #wrapper {
            display: flex;
            justify-content: center;
            padding-top: 1.5em;
        }
        #content {
            width:88%;
            height: 100em;
        }
        #logo-search{
            display: flex;
        }
        #logo-container{
            display: flex;
            align-items: center;
            width:30%;
        }
        #searchbar-container{
            width:40%;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
        }
        #searchbar {
            display: flex;
            align-items: center;
        }
        #search-input{
            border: 2px rgb(7,34,66) solid;
            height:3em;
            width: 34em;
        }
        #search-input:focus{
            outline: none;
        }
        #search-btn {
            border: 2px rgb(7,34,66) solid;
            background-color: rgb(7,34,66);
        }
        ion-icon[name="search-outline"]{
            font-size: 2.72em;
            color: white;
        }
        #shop-info-container{
            width:30%;
            text-align: right;
            display: flex;
            flex-direction: column;
            justify-content: center;
            font-size: 11px;
        }
        #logo{
            width:100px;
            height:100px;
        }
        #menus {
            display: flex;
        }
        #shop-cates {
            width:80%;
            display: flex;
        }
        #user-cates{
            width:20%;
            display: flex;
            justify-content: flex-end;
        }
        .shop-cate{
            margin-right: 5em;
            height: 3.5em;
            display: flex;
            align-items: center;
            font-size: 12px;
            font-weight: bold;
            border:0;
        }
        .user-cate{
            margin-right: 1em;
            display: flex;
            align-items: center;
            font-size: small;
            border:0;
        }
        #carousel-sales{
            margin-top: 1.5em;
            display: flex;
        }
        #left-sales{
            width: 20%;
        }
        #center-carousel{
            width: 60%;
            padding-left: 1em;
            padding-right: 1em;
            overflow: hidden;
        }

        #center-carousel .glider {
            height: 100%;
        }

        .glider-track {
            height: 100%;
        }

        #center-carousel .glider-prev {
            position: absolute;
            margin-top: 1em;
            left: 0.4em;
            color: white;
            width: 1em;
            background: rgba(0,0,0,0.2);
            height: 1em;
            display: flex;
            align-items: center;
            justify-content: center;
            padding-bottom: 0.18em;
            padding-right: 0.05em;
        }
        #center-carousel .glider-next {
            position: absolute;
            margin-top: 1em;
            right: 0.4em;
            color: white;
            width: 1em;
            background: rgba(0,0,0,0.2);
            height: 1em;
            display: flex;
            align-items: center;
            justify-content: center;
            padding-bottom: 0.18em;
            padding-right: 0.05em;
        }

        .glider-pic-con img {
            width: 100%;
            height: 100%;
            object-fit: cover;
        }
        .dots {
            position:absolute;
            left: 44%;
            bottom: 1em;
        }
        #right-sales{
            width: 20%;
        }
        .left-sale{
            display: flex;
            margin-bottom: 0.5em;
            justify-content: center;
            align-items: center;
            height: 5em;
            border:1px black solid;
            font-size: 20px;
            letter-spacing: 0.1em;
            font-weight: 900;
        }
        #left-sale-1{
            color:rgb(190,57,50);
        }
        #left-sale-2{
            color:rgb(190,57,50);
        }
        #left-sale-3{
            color:rgb(203,171,68);
        }
        #left-sale-4{
            color:black;
            margin-bottom: 0;
        }
        .right-sale{
            display: flex;
            margin-bottom: 0.5em;
            justify-content: center;
            align-items: center;
            height: 7.49em;
            border:1px black solid;
            font-size: 28px;
            letter-spacing: 0.1em;
            font-weight: 900;
            text-shadow: 1px 1px black;
        }
        #right-sale-1{
            color:rgb(46,65,134);
        }
        #right-sale-2{
            color:rgb(181,41,30);
            margin-bottom: 0;
        }
        .separator {
            margin-top: 1.5em;
            display: flex;
            align-items: center;
            text-align: center;
            font-size:16px;
            font-weight: 700;
        }
        .separator::before, .separator::after {
            content: '';
            flex: 1;
            border-bottom: 1px solid silver;
        }
        .separator::before {
            margin-right: 1em;
        }
        .separator::after {
            margin-left: 1em;
        }
        #new-arrival {
            display: flex;
            flex-wrap: wrap;
        }
        .item {
            width: 20%;
        }

        .item-img-con {
            width: 100%;
        }
        .item-img-con img {
            width: 100%;
            height: 100%;
            object-fit: cover;
        }
        .item-description {
            display: flex;
            flex-direction: column;
        }
        .item-description div {
            margin-bottom: 0.5em;
            font-size: 13px;
        }
        .item-description .item-name {
            color: gray;
        }
        .item-description .item-price {
            font-weight: bold;
        }
        #footer-separator {
            margin-top: 4em;
            margin-bottom: 2em;
        }
        #footer {
            display: flex;
            padding-bottom: 4em;
        }
        #footer-logo-con {
            width: 15%;
        }
        #footer-logo-con img {
            width: 60%;
            object-fit: cover;
        }
        #footer-info1 {
            width: 27%;
        }
        #footer-info2 {
            width: 27%;
        }
        #footer-info3 {
            width: 27%;
        }
    </style>
</head>
<body>
<div id="wrapper">
    <div id="content">
        <div id="logo-search">
            <div id="logo-container">
                <img id="logo" src="resources/images/Logo.png">
            </div>
            <div id="searchbar-container">
                <div id="searchbar">
                    <input id="search-input" type="text">
                    <button id="search-btn" type="button"><ion-icon name="search-outline"></ion-icon></button>
                </div>
            </div>
            <div id="shop-info-container">
                MON-FRI : 11~9 / SAT : 12~9 / SUN : 12~8<br>
                555-13 4TH FL. SINSA-DONG, GANGNAM-GU, SEOUL<br>
                JHLEE1@BLUESMAN.CO.KR / 02-543-7137
            </div>
        </div>
        <div id="menus">
            <div id="shop-cates">
                <div class="shop-cate">
                    HOME
                </div>
                <div class="shop-cate">
                    NEW ARRIVAL
                </div>
                <div class="shop-cate">
                    BRAND
                </div>
                <div class="shop-cate">
                    CATEGORIES
                </div>
            </div>
            <div id="user-cates">
                <div class="user-cate">
                    JOIN
                </div>
                <div class="user-cate">
                    LOG-IN
                </div>
                <div class="user-cate">
                    MYPAGE
                </div>
            </div>
        </div>
        <hr>
        <div id="carousel-sales">
            <div id="left-sales">
                <div class="left-sale" id="left-sale-1">
                    NEW ARRIVALS
                </div>
                <div class="left-sale" id="left-sale-2">
                    SALE
                </div>
                <div class="left-sale" id="left-sale-3">
                    USED
                </div>
                <div class="left-sale" id="left-sale-4">
                    OUTLET
                </div>
            </div>
            <div id="center-carousel" class="glider-contain">
                <div class="glider">
                    <div class="glider-pic-con"><img src="resources/images/glider-1.jpg"></div>
                    <div class="glider-pic-con"><img src="resources/images/glider-2.jpg"></div>
                    <div class="glider-pic-con"><img src="resources/images/glider-3.jpg"></div>
                    <div class="glider-pic-con"><img src="resources/images/glider-4.jpg"></div>
                </div>
                <button aria-label="Previous" class="glider-prev">«</button>
                <button aria-label="Next" class="glider-next">»</button>
                <div role="tablist" class="dots"></div>
            </div>
            <div id="right-sales">
                <div class="right-sale" id="right-sale-1">
                    WINTER<br>
                    PRESALE
                </div>
                <div class="right-sale" id="right-sale-2">
                    ALL<br>
                    COATS
                </div>
            </div>
        </div>
        <div class="separator">NEW ARRIVAL</div>
        <div id="new-arrival">
            <div class="item">
                <div class="item-img-con">
                    <img src="resources/images/new1.jpg">
                </div>
                <div class="item-description">
                    <div class="item-brand">
                        [BARNS OUTFITTERS]
                    </div>
                    <div class="item-name">
                        GUSSET CREW SWEATSHIRTS - CALIFORNIA (BR8533)
                    </div>
                    <div class="item-price">
                        KRW 179,000
                    </div>
                </div>
            </div>
            <div class="item">
                <div class="item-img-con">
                    <img src="resources/images/new1.jpg">
                </div>
                <div class="item-description">
                    <div class="item-brand">
                        [BARNS OUTFITTERS]
                    </div>
                    <div class="item-name">
                        GUSSET CREW SWEATSHIRTS - CALIFORNIA (BR8533)
                    </div>
                    <div class="item-price">
                        KRW 179,000
                    </div>
                </div>
            </div>
            <div class="item">
                <div class="item-img-con">
                    <img src="resources/images/new1.jpg">
                </div>
                <div class="item-description">
                    <div class="item-brand">
                        [BARNS OUTFITTERS]
                    </div>
                    <div class="item-name">
                        GUSSET CREW SWEATSHIRTS - CALIFORNIA (BR8533)
                    </div>
                    <div class="item-price">
                        KRW 179,000
                    </div>
                </div>
            </div>
            <div class="item">
                <div class="item-img-con">
                    <img src="resources/images/new1.jpg">
                </div>
                <div class="item-description">
                    <div class="item-brand">
                        [BARNS OUTFITTERS]
                    </div>
                    <div class="item-name">
                        GUSSET CREW SWEATSHIRTS - CALIFORNIA (BR8533)
                    </div>
                    <div class="item-price">
                        KRW 179,000
                    </div>
                </div>
            </div>
            <div class="item">
                <div class="item-img-con">
                    <img src="resources/images/new1.jpg">
                </div>
                <div class="item-description">
                    <div class="item-brand">
                        [BARNS OUTFITTERS]
                    </div>
                    <div class="item-name">
                        GUSSET CREW SWEATSHIRTS - CALIFORNIA (BR8533)
                    </div>
                    <div class="item-price">
                        KRW 179,000
                    </div>
                </div>
            </div>
            <div class="item">
                <div class="item-img-con">
                    <img src="resources/images/new1.jpg">
                </div>
                <div class="item-description">
                    <div class="item-brand">
                        [BARNS OUTFITTERS]
                    </div>
                    <div class="item-name">
                        GUSSET CREW SWEATSHIRTS - CALIFORNIA (BR8533)
                    </div>
                    <div class="item-price">
                        KRW 179,000
                    </div>
                </div>
            </div>
            <div class="item">
                <div class="item-img-con">
                    <img src="resources/images/new1.jpg">
                </div>
                <div class="item-description">
                    <div class="item-brand">
                        [BARNS OUTFITTERS]
                    </div>
                    <div class="item-name">
                        GUSSET CREW SWEATSHIRTS - CALIFORNIA (BR8533)
                    </div>
                    <div class="item-price">
                        KRW 179,000
                    </div>
                </div>
            </div>
            <div class="item">
                <div class="item-img-con">
                    <img src="resources/images/new1.jpg">
                </div>
                <div class="item-description">
                    <div class="item-brand">
                        [BARNS OUTFITTERS]
                    </div>
                    <div class="item-name">
                        GUSSET CREW SWEATSHIRTS - CALIFORNIA (BR8533)
                    </div>
                    <div class="item-price">
                        KRW 179,000
                    </div>
                </div>
            </div>
            <div class="item">
                <div class="item-img-con">
                    <img src="resources/images/new1.jpg">
                </div>
                <div class="item-description">
                    <div class="item-brand">
                        [BARNS OUTFITTERS]
                    </div>
                    <div class="item-name">
                        GUSSET CREW SWEATSHIRTS - CALIFORNIA (BR8533)
                    </div>
                    <div class="item-price">
                        KRW 179,000
                    </div>
                </div>
            </div>
            <div class="item">
                <div class="item-img-con">
                    <img src="resources/images/new1.jpg">
                </div>
                <div class="item-description">
                    <div class="item-brand">
                        [BARNS OUTFITTERS]
                    </div>
                    <div class="item-name">
                        GUSSET CREW SWEATSHIRTS - CALIFORNIA (BR8533)
                    </div>
                    <div class="item-price">
                        KRW 179,000
                    </div>
                </div>
            </div>
        </div>
        <hr id="footer-separator">
        <div id="footer">
            <div id="footer-logo-con">
                <img src="resources/images/Logo.png">
            </div>
            <div id="footer-info1">
                <b>COMPANY INFO</b><br><br>
                <b>COMPANY</b> : 딜리버리보이즈컴퍼니<br>
                <b>OWNER</b> : 이종헌 / CPO : 이종헌<br>
                <b>ADDRESS</b> : 서울특별시 강남구 신사동 555-13 동명빌딩 4층<br>
                <b>BUSINESS</b> LICENSE : 230-01-00419<br>
                <b>MAIL ORDER LICENSE</b> :제2016-서울강남-00099호<br>
                <br>
                TERMS OF USE / PRIVACY POLICY
            </div>
            <div id="footer-info2">
                <b>STORE</b><br><br>
                555-13 DONGMYUNG BLDG 4TH FL.<br>
                SINSA-DONG, GANGNAM-GU, SEOUL, KOREA<br>
                T. +82 2 543 7137M<br><br>

                <b>STORE HOURS</b><br><br>
                MON - FRI : 11AM ~ 9PM<br>
                SAT : 12AM ~ 9PM<br>
                SUN : 12AM ~ 8PM<br>
            </div>
            <div id="footer-info3">
                <b>CUSTOMER SERVICE</b><br><br>
                T. : 02 543 7137<br>
                EMAIL : JHLEE1@BLUESMAN.CO.KR<br><br>

                <b>BANK ACCOUNT</b> [예금주 : 이종헌(딜리버리보이즈컴퍼니)]<br>
                신한 110 390 336769<br>
                국민 654501 04 008112<br>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://unpkg.com/ionicons@5.2.3/dist/ionicons.js"></script>
<script src="resources/js/glider.js"></script>
<script>
    new Glider(document.querySelector('.glider'), {
        slidesToShow: 1,
        dots: '.dots',
        arrows: {
            prev: '.glider-prev',
            next: '.glider-next'
        }
    });
</script>
</body>
</html>