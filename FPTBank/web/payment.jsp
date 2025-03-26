<%-- 
    Document   : home.jsp
    Created on : Jan 16, 2025, 12:34:43 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>

        <title>TIMI - Finance</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="HandheldFriendly" content="true">
        <meta name="author" content="bslthemes" />

        <!-- switzer font css -->
        <link rel="stylesheet" href="fonts/css/switzer.css" type="text/css" media="all">
        <!-- font awesome css -->
        <link rel="stylesheet" href="fonts/css/font-awesome.min.css" type="text/css" media="all">
        <!-- bootstrap grid css -->
        <link rel="stylesheet" href="css/plugins/bootstrap-grid.css" type="text/css" media="all">
        <!-- swiper css -->
        <link rel="stylesheet" href="css/plugins/swiper.min.css" type="text/css" media="all">
        <!-- magnific popup -->
        <link rel="stylesheet" href="css/plugins/magnific-popup.css" type="text/css" media="all">
        <!-- plax css -->
        <link rel="stylesheet" href="css/style.css" type="text/css" media="all">

        <!-- Favicon -->
        <link rel="shortcut icon" href="img/favicon.png" type="image/x-icon">
        <link rel="icon" href="img/favicon.png" type="image/x-icon">

        <!-- Toarst -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>


        <style>
            /* Chặn thanh kéo ngang */
            body {
                overflow-x: hidden;
            }

            /* Notification styles */
            .notification {
                position: fixed;
                top: -100px;
                left: 50%;
                transform: translateX(-50%);
                background: linear-gradient(135deg, #ff6b6b 0%, #dc3545 100%);
                padding: 20px 30px;
                border-radius: 15px;
                box-shadow: 0 10px 30px rgba(220, 53, 69, 0.3),
                    0 0 0 1px rgba(220, 53, 69, 0.2);
                display: flex;
                align-items: center;
                gap: 15px;
                z-index: 10000;
                transition: all 0.5s cubic-bezier(0.68, -0.55, 0.265, 1.55);
                width: auto;
                max-width: 90vw; /* Giới hạn để không vượt quá màn hình */
                border: 1px solid rgba(255, 255, 255, 0.2);
                backdrop-filter: blur(10px);
                box-sizing: border-box;
            }

            /* Khi hiển thị */
            .notification.show {
                top: 30px;
                animation: shake 0.8s cubic-bezier(.36,.07,.19,.97) both;
            }

            /* Keyframes cho shake */
            @keyframes shake {
                10%, 90% {
                    transform: translateX(calc(-50% + 1px));
                }
                20%, 80% {
                    transform: translateX(calc(-50% - 2px));
                }
                30%, 50%, 70% {
                    transform: translateX(calc(-50% + 4px));
                }
                40%, 60% {
                    transform: translateX(calc(-50% - 4px));
                }
            }

            /* Icon */
            .notification-icon {
                width: 45px;
                height: 45px;
                background: rgba(255, 255, 255, 0.95);
                border-radius: 50%;
                display: flex;
                align-items: center;
                justify-content: center;
                flex-shrink: 0;
                animation: pulse 2s infinite;
            }

            @keyframes pulse {
                0% {
                    transform: scale(1);
                }
                50% {
                    transform: scale(1.05);
                }
                100% {
                    transform: scale(1);
                }
            }

            .notification-icon i {
                color: #dc3545;
                font-size: 1.4em;
            }

            .notification-content {
                flex-grow: 1;
                max-width: calc(100% - 80px); /* Giới hạn để tránh tràn */
                overflow: hidden;
            }

            .notification-title {
                color: white;
                font-weight: 600;
                margin: 0 0 5px 0;
                font-size: 1.1em;
                text-shadow: 0 1px 2px rgba(0, 0, 0, 0.1);
                white-space: nowrap; /* Ngăn xuống dòng */
                overflow: hidden;
                text-overflow: ellipsis;
            }

            .notification-message {
                color: rgba(255, 255, 255, 0.95);
                margin: 0;
                font-size: 0.95em;
                line-height: 1.4;
                text-shadow: 0 1px 2px rgba(0, 0, 0, 0.1);
                white-space: nowrap; /* Ngăn xuống dòng */
                overflow: hidden;
                text-overflow: ellipsis;
            }

            /* Nút đóng */
            .notification-close {
                color: rgba(255, 255, 255, 0.8);
                background: rgba(255, 255, 255, 0.1);
                border: none;
                font-size: 1.2em;
                cursor: pointer;
                padding: 8px;
                border-radius: 50%;
                width: 30px;
                height: 30px;
                display: flex;
                align-items: center;
                justify-content: center;
                transition: all 0.3s ease;
                margin-left: 5px;
            }

            .notification-close:hover {
                color: white;
                background: rgba(255, 255, 255, 0.2);
                transform: rotate(90deg);
            }

            /* Responsive */
            @media (max-width: 480px) {
                .notification {
                    width: 90%;
                    min-width: auto;
                    max-width: 90vw;
                    padding: 15px;
                }
            }
            *
            {
                border: 0;
                box-sizing: content-box;
                color: inherit;
                font-family: inherit;
                font-size: inherit;
                font-style: inherit;
                font-weight: inherit;
                line-height: inherit;
                list-style: none;
                margin: 0;
                padding: 0;
                text-decoration: none;
                vertical-align: top;
            }

            /* content editable */

            *[contenteditable] {
                border-radius: 0.25em;
                min-width: 1em;
                outline: 0;
            }

            *[contenteditable] {
                cursor: pointer;
            }

            *[contenteditable]:hover, *[contenteditable]:focus, td:hover *[contenteditable], td:focus *[contenteditable], img.hover {
                background: #DEF;
                box-shadow: 0 0 1em 0.5em #DEF;
            }

            span[contenteditable] {
                display: inline-block;
            }

            /* heading */

            h1 {
                font: bold 100% sans-serif;
                letter-spacing: 0.5em;
                text-align: center;
                text-transform: uppercase;
            }

            /* table */

            table {
                font-size: 75%;
                table-layout: fixed;
                width: 100%;
            }
            table {
                border-collapse: separate;
                border-spacing: 2px;
            }
            th, td {
                border-width: 1px;
                padding: 0.5em;
                position: relative;
                text-align: left;
            }
            th, td {
                border-radius: 0.25em;
                border-style: solid;
            }
            th {
                background: #EEE;
                border-color: #BBB;
            }
            td {
                border-color: #DDD;
            }

            /* page */





            /* header */

            header {
                margin: 0 0 3em;
            }
            header:after {
                clear: both;
                content: "";
                display: table;
            }

            header h1 {
                background: #000;
                border-radius: 10px; /* Làm bo góc mềm mại hơn */
                color: #FFF;
                margin: 20px auto; /* Căn giữa theo chiều ngang */
                padding: 20px 0; /* Tăng padding để chữ không bị dính */
                width: 100%; /* Thay vì cố định px, dùng % để linh hoạt */
                max-width: 1000px; /* Giới hạn độ rộng tối đa */
                height: auto; /* Để chiều cao tự động phù hợp nội dung */
                text-align: center; /* Căn giữa chữ */
                font-size: 2.5em; /* Làm chữ to rõ hơn */
                font-weight: bold;
                letter-spacing: 2px; /* Tạo khoảng cách giữa các ký tự */
            }

            header address {
                float: left;
                font-size: 75%;
                font-style: normal;
                line-height: 1.25;
                margin: 0 1em 1em 0;
            }
            header address p {
                margin: 0 0 0.25em;
            }
            header span, header img {
                display: block;
                float: right;
            }
            header span {
                margin: 0 0 1em 1em;
                max-height: 25%;
                max-width: 60%;
                position: relative;
            }
            header img {
                max-height: 100%;
                max-width: 100%;
            }
            header input {
                cursor: pointer;
                -ms-filter:"progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";
                height: 100%;
                left: 0;
                opacity: 0;
                position: absolute;
                top: 0;
                width: 100%;
            }

            /* article */

            article, article address, table.meta, table.inventory {
                margin: 0 0 3em;
            }
            article:after {
                clear: both;
                content: "";
                display: table;
            }
            article h1 {
                clip: rect(0 0 0 0);
                position: absolute;
            }

            article address {
                float: left;
                font-size: 125%;
                font-weight: bold;
            }

            /* table meta & balance */

            table.meta, table.balance {
                float: right;
                width: 36%;
            }
            table.meta:after, table.balance:after {
                clear: both;
                content: "";
                display: table;
            }

            /* table meta */

            table.meta th {
                width: 40%;
            }
            table.meta td {
                width: 60%;
                text-align: center;
                vertical-align: middle;
            }

            /* table items */

            table.inventory {
                clear: both;
                width: 100%;
            }
            table.inventory th {
                font-weight: bold;
                text-align: center;
            }

            table.inventory td:nth-child(1) {
                width: 26%;
            }
            table.inventory td:nth-child(2) {
                width: 38%;
            }
            table.inventory td:nth-child(3) {
                text-align: right;
                width: 12%;
            }
            table.inventory td:nth-child(4) {
                text-align: right;
                width: 12%;
            }
            table.inventory td:nth-child(5) {
                text-align: right;
                width: 12%;
            }

            /* table balance */

            table.balance th, table.balance td {
                width: 50%;
            }
            table.balance td {
                text-align: right;
            }

            /* aside */

            aside h1 {
                border: none;
                border-width: 0 0 1px;
                margin: 0 0 1em;
            }
            aside h1 {
                border-color: #999;
                border-bottom-style: solid;
            }

            /* javascript */

            .add, .cut
            {
                border-width: 1px;
                display: block;
                font-size: .8rem;
                padding: 0.25em 0.5em;
                float: left;
                text-align: center;
                width: 0.6em;
            }

            .add, .cut
            {
                background: #9AF;
                box-shadow: 0 1px 2px rgba(0,0,0,0.2);
                background-image: -moz-linear-gradient(#00ADEE 5%, #0078A5 100%);
                background-image: -webkit-linear-gradient(#00ADEE 5%, #0078A5 100%);
                border-radius: 0.5em;
                border-color: #0076A3;
                color: #FFF;
                cursor: pointer;
                font-weight: bold;
                text-shadow: 0 -1px 2px rgba(0,0,0,0.333);
            }

            .add {
                margin: -2.5em 0 0;
            }

            .add:hover {
                background: #00ADEE;
            }

            .cut {
                opacity: 0;
                position: absolute;
                top: 0;
                left: -1.5em;
            }
            .cut {
                -webkit-transition: opacity 100ms ease-in;
            }

            tr:hover .cut {
                opacity: 1;
            }

            @media print {
                * {
                    -webkit-print-color-adjust: exact;
                }
                html {
                    background: none;
                    padding: 0;
                }
                body {
                    box-shadow: none;
                    margin: 0;
                }
                span:empty {
                    display: none;
                }
                .add, .cut {
                    display: none;
                }
            }

            @page {
                margin: 0;
            }
            .content{
                width: 70%;
                margin: 0 auto;
                margin-top: 15%;
                background-color: white;
                padding: 10px;
            }
            .container {
                display: flex;
                gap: 20px;
            }
            .card {
                width: 150px;
                height: 150px;
                background: white;
                border-radius: 12px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                display: flex;
                flex-direction: column;
                align-items: center;
                justify-content: center;
                gap: 10px;
                cursor: pointer;
                transition: 0.3s;
                text-align: center;
            }
            .card:hover {
                box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
                transform: scale(1.05);
            }
            .card img {
                width: 40px;
                height: 40px;
            }
            .card p {
                font-size: 16px;
                font-weight: bold;
                color: #333;
                margin: 0;
            }
        </style>
        <script>
            function selectPayment(method) {
                document.getElementById('paymentMethod').value = method;

                // Xóa viền của tất cả thẻ trước khi chọn lại
                document.querySelectorAll('.card').forEach(card => {
                    card.style.border = "none";
                });

                // Tô viền xanh cho thẻ được chọn
                event.currentTarget.style.border = "3px solid blue";
            }

            function validatePayment(event) {
                const paymentMethod = document.getElementById('paymentMethod').value;
                if (!paymentMethod) {
                    alert("Please choose a payment method!");
                    event.preventDefault(); // Ngăn form gửi nếu chưa chọn
                    return false;
                }

                // Gửi form với phương thức thanh toán trên URL
                const form = document.getElementById('paymentForm');
                form.action = form.action + "&paymentMethod=" + encodeURIComponent(paymentMethod);
        </script>
    </head>
        
    <body>

        <!-- Error Notification -->
        <div id="notification" class="notification ${param.RoleErr eq 'true' ? 'show' : ''}">
            <div class="notification-icon">
                <i class="fa fa-exclamation-circle"></i>
            </div>
            <div class="notification-content">
                <h4 class="notification-title">Access Denied</h4>
                <p class="notification-message">You don't have permission to access this feature.</p>
            </div>
            <button class="notification-close" onclick="closeNotification()">&times;</button>
        </div>

        <script>
                function setPaymentMethod(method) {
                    document.getElementById("paymentMethod").value = method;
                }
        </script>

        <%@ include file="header.jsp"%>
    </div>
    <!-- top panel end -->
    <div class="content">
        <form action="payment" method="get" onsubmit="validatePayment(event)">
            <header>
                <h1>Payment</h1>

                <span><img alt="" src="http://www.jonathantneal.com/examples/invoice/logo.png"><input type="file" accept="image/*"></span>
            </header>
            <h3 class="balance">Your Balance: <span>
                    <fmt:formatNumber value="${requestScope.customer.getBalance()}" type="number" maxFractionDigits="0"/> VNĐ
                </span></h3>
                <% if(request.getAttribute("error")!=null)  {%>
            <a style="color:red; font-style: italic; "><%out.println(request.getAttribute("error"));%></a>
            <%}%>
            <article>


                <table class="inventory">

                    <thead>
                        <tr>

                            <th><span contenteditable>InvoiceID#</span></th>
                            <th><span contenteditable>Title</span></th>
                            <th><span contenteditable>Description</span></th>
                            <th><span contenteditable>Company</span></th>                     
                            <th><span contenteditable>StartDate</span></th>

                            <th><span contenteditable>EndDate</span></th>
                        </tr>
                    </thead>

                    <tbody>
                        <tr>

                            <td><a class="cut">-</a><span contenteditable>${bill.getBillID()}</span>
                                <input type="hidden" name="billID" value="${bill.getBillID()}">
                                <input type="hidden" name="providerID" value="${bill.getProvider().getUserID()}"></td>
                            <td><span contenteditable>${bill.getTitle()}</span></td>
                            <td><span data-prefix></span><span contenteditable>${bill.getDescription()}</span></td>
                            <td><span contenteditable></span>${company.getCompanyName()}</td>
                            <td><span data-prefix></span><span style="margin-left: 5px;">${bill.getStartDate()}</span></td>
                            <td><span data-prefix></span><span style="margin-left: 5px;">${bill.getEndDate()}</span></td>
                        </tr>
                    </tbody>

                </table>
                <table class="balance">
                    <tr>
                        <th><span contenteditable>Total</span></th>
                        <td><span data-prefix>VNĐ</span><span id="total">${bill.getTotal()}</span>
                            <input type="hidden" name="total" value="${bill.getTotal()}">
                        </td>
                    </tr>

                </table>

            </article>
            <div style="font-weight: bold;  ">Please choose payment method</div>
            <input type="hidden" name="paymentMethod" id="paymentMethod">

            <div class="container">
                <!-- Button Balance -->
                <button style="width: 180px; height: 180px; border: 2px solid grey; border-radius: 5px; padding: 5px;" type="submit" class="payment-btn" onclick="setPaymentMethod('balance')">
                    <img src="https://png.pngtree.com/png-clipart/20220616/original/pngtree-icon-game-golden-coin-png-image_8090807.png" alt="Balance">
                    Balance
                </button>

                <!-- Button VNPay -->
                <button type="submit" style="width: 180px; height: 180px; margin-left: 10px; border: 2px solid grey;padding: 5px;" class="payment-btn" onclick="setPaymentMethod('vnpay')">
                    <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTp1v7T287-ikP1m7dEUbs2n1SbbLEqkMd1ZA&s" alt="VNPay">
                    VNPay
                </button>
            </div>
            <div style="diplay: flex;">
                <a href="invoiceshowcustomer" 
                   style="display: inline-block; background-color: blue; color: white;
                   border-radius: 5px; padding: 5px 10px; text-decoration: none; margin-top: 30px;">
                    Back to list bill
                </a>
            </div>
        </form>
    </div>


    <%@ include file="footer.jsp"%>
    <!-- footer end -->

</div>
<!-- content end -->
</div>
<!-- wrapper end -->

<!-- jquery js -->
<script src="js/plugins/jquery.min.js"></script>

<!-- swiper css -->
<script src="js/plugins/swiper.min.js"></script>
<!-- gsap js -->
<script src="js/plugins/gsap.min.js"></script>
<!-- scroll smoother -->
<script src="js/plugins/ScrollSmoother.min.js"></script>
<!-- scroll trigger js -->
<script src="js/plugins/ScrollTrigger.min.js"></script>
<!-- scroll to js -->
<script src="js/plugins/ScrollTo.min.js"></script>
<!-- magnific -->
<script src="js/plugins/magnific-popup.js"></script>
<!-- plax js -->
<script src="js/main.js"></script>
<%@ include file="admin/Common/toarst.jsp" %>

</body>
</html>
