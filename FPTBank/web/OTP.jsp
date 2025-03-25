<%-- 
    Document   : home.jsp
    Created on : Jan 16, 2025, 12:34:43 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
        <link rel="stylesheet" href="otp-style.css">

        <!-- Toarst -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>


        <style>

            /* Toast Message Styles */
            .toast-message {
                position: fixed;
                top: -100px; /* Start above viewport */
                left: 50%;
                transform: translateX(-50%);
                background-color: #4CAF50;
                color: white;
                padding: 16px 32px;
                border-radius: 8px;
                font-size: 16px;
                font-weight: 500;
                box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
                display: flex;
                align-items: center;
                gap: 12px;
                z-index: 1000;
                transition: top 0.5s ease-in-out;
            }

            .toast-message.show {
                top: 20px; /* Slide down to this position */
            }

            .toast-message i {
                font-size: 24px;
            }
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
            /* Căn giữa form OTP */
            #otpSection {
                max-width: 400px;
                margin: 50px auto;
                padding: 20px;
                background: #fff;
                border-radius: 10px;
                box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
            }

            /* Tiêu đề OTP */
            #otpSection h4 {
                font-weight: bold;
                color: #28a745;
            }

            /* Ô nhập OTP */
            #otpSection input[type="text"] {
                font-size: 18px;
                padding: 10px;
                text-align: center;
                border: 2px solid #28a745;
                border-radius: 5px;
                outline: none;
                width: 100%;
            }

            /* Khi focus vào ô nhập */
            #otpSection input[type="text"]:focus {
                border-color: #218838;
                box-shadow: 0px 0px 5px rgba(40, 167, 69, 0.5);
            }

            /* Nút Back và Verify */
            #otpSection .btn {
                font-size: 16px;
                padding: 10px;
                border-radius: 5px;
                transition: all 0.3s ease-in-out;
            }

            /* Nút Verify */
            #otpSection .btn-success {
                background-color: #28a745;
                border: none;
            }

            #otpSection .btn-success:hover {
                background-color: #218838;
            }

            /* Nút Back */
            #otpSection .btn-outline-secondary {
                border: 2px solid #6c757d;
                color: #6c757d;
            }

            #otpSection .btn-outline-secondary:hover {
                background-color: #6c757d;
                color: white;
            }

            /* Hiển thị lỗi */
            #otpSection a[style*="color:red"] {
                display: block;
                text-align: center;
                margin-top: 10px;
                font-size: 14px;
            }



        </style>

    </head>

    <body>
        <!--show message-->
        <c:if test="${not empty sessionScope.message}">
            <div id="toastMessage" class="toast-message">
                <i class="fa fa-check-circle"></i>
                ${sessionScope.message}
            </div>
            <c:remove var="message" scope="session" />
        </c:if>
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
            function closeNotification() {
                const notification = document.getElementById('notification');
                notification.classList.remove('show');
                setTimeout(() => {
                    notification.style.display = 'none';
                }, 500);
            }

            // Auto close after 5 seconds
            if (document.getElementById('notification').classList.contains('show')) {
                setTimeout(closeNotification, 5000);
            }


            // Toast message animation
            document.addEventListener('DOMContentLoaded', function () {
                const toast = document.getElementById('toastMessage');
                if (toast) {
                    // Show toast
                    setTimeout(() => {
                        toast.classList.add('show');
                    }, 100);

                    // Hide toast after 3 seconds
                    setTimeout(() => {
                        toast.classList.remove('show');
                        // Remove toast from DOM after animation
                        setTimeout(() => {
                            toast.remove();
                        }, 500);
                    }, 6000);
                }
            });
        </script>

        <!-- wrapper -->
        <div id="smooth-wrapper" class="mil-wrapper">

            <!-- preloader -->
            <div class="mil-preloader">

            </div>
            <!-- preloader end -->

            <!-- scroll progress -->

            <!-- scroll progress end -->

            <!-- back to top -->

            <!-- top panel end -->
            <%@ include file="header.jsp"%>
            <!-- content -->
            <div id="smooth-content">
                <%@ include file="toolBar.jsp"%>




                <form action="otp" method="get">
                    <input name="number" type="hidden" value="${number}">
                    <input name="amount" type="hidden" value="${amount}">
                    <input name="note" type="hidden" value="${note}"><input name="receiverID" type="hidden" value="${receiverID}">
                    <input name="transferor" type="hidden" value="${transferor}">
                    <div id="otpSection" name="otpcontainer" class="mt-4">
                        <h4 class="text-center text-primary">Enter OTP</h4>
                        <p class="text-center">A 6-digit OTP has been sent to your phone</p>

                        <div class="border p-3 rounded">
                            <input type="text" name="otp" class="form-control text-center" maxlength="6" pattern="\d{6}" required placeholder="Enter OTP">
                        </div>
                        <% if(request.getAttribute("error")!=null)  {%>
                        <a style="color:red; font-style: italic"><%out.println(request.getAttribute("error"));%></a>
                        <%}%>
                        <div class="d-flex mt-4">
                            <button type="button" class="btn btn-outline-secondary w-50" onclick="showConfirmForm()">Back</button>
                            <button type="submit" name="action" value="verify" class="btn btn-success w-50">Verify</button>
                        </div>
                    </div>
                </form>


                <!-- footer -->
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
