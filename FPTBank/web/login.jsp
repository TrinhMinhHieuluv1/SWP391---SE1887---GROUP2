<%-- 
    Document   : login
    Created on : Jan 13, 2025, 3:18:30 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>

        <title>TIMI - Login</title>
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
        <script>
            function togglePassword(id) {
                const passwordField = document.getElementById(id);
                const passwordFieldType = passwordField.getAttribute('type') === 'password' ? 'text' : 'password';
                passwordField.setAttribute('type', passwordFieldType);
                const eyeIcon = event.target;
                eyeIcon.classList.toggle('fa-eye');
                eyeIcon.classList.toggle('fa-eye-slash');
            }
        </script>
        <style>
            .mil-input:focus {
                border-color: #4caf50 !important;
                box-shadow: 0 0 0 3px rgba(76, 175, 80, 0.2) !important;
                outline: none;
            }
            .mil-btn:hover {
                transform: translateY(-2px);
                box-shadow: 0 5px 15px rgba(76, 175, 80, 0.2);
            }
            body {
                background: #f0fff0;
            }
            .mil-btn.mil-grey:hover {
                background: #d7ecd7;
            }
            /* Modal styles */
            .modal {
                display: none;
                position: fixed;
                z-index: 9999;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                background-color: rgba(0,0,0,0.5);
                opacity: 0;
                transition: opacity 0.3s ease-in-out;
            }
            .modal.show {
                display: flex;
                opacity: 1;
            }
            .modal-content {
                background-color: #fefefe;
                margin: auto;
                padding: 25px;
                border-radius: 15px;
                width: 90%;
                max-width: 400px;
                position: relative;
                transform: translateY(-20px);
                transition: transform 0.3s ease-in-out;
                box-shadow: 0 5px 20px rgba(0,0,0,0.2);
            }
            .modal.show .modal-content {
                transform: translateY(0);
            }
            .modal-header {
                display: flex;
                align-items: center;
                margin-bottom: 15px;
                padding-bottom: 15px;
                border-bottom: 1px solid #e8f5e9;
            }
            .modal-icon {
                background-color: #fde9e9;
                color: #dc3545;
                width: 40px;
                height: 40px;
                border-radius: 50%;
                display: flex;
                align-items: center;
                justify-content: center;
                margin-right: 15px;
            }
            .modal-title {
                color: #333;
                margin: 0;
                font-size: 1.2em;
                font-weight: 600;
            }
            .modal-body {
                color: #666;
                margin-bottom: 20px;
                font-size: 0.95em;
                line-height: 1.5;
            }
            .modal-footer {
                text-align: right;
            }
            .modal-close {
                background: #4caf50;
                color: white;
                border: none;
                padding: 10px 20px;
                border-radius: 8px;
                cursor: pointer;
                font-weight: 500;
                transition: all 0.3s ease;
            }
            .modal-close:hover {
                background: #43a047;
                transform: translateY(-2px);
                box-shadow: 0 5px 15px rgba(76, 175, 80, 0.2);
            }
            /* Notification styles */
            .notification {
                position: fixed;
                top: -100px;
                left: 50%;
                transform: translateX(-50%);
                background: linear-gradient(135deg, #dc3545 0%, #c82333 100%);
                padding: 20px 30px;
                border-radius: 15px;
                box-shadow: 0 10px 30px rgba(220, 53, 69, 0.3),
                           0 0 0 1px rgba(220, 53, 69, 0.2);
                display: flex;
                align-items: center;
                gap: 15px;
                z-index: 10000;
                transition: all 0.5s cubic-bezier(0.68, -0.55, 0.265, 1.55);
                min-width: 400px;
                border: 1px solid rgba(255, 255, 255, 0.2);
                backdrop-filter: blur(10px);
            }
            .notification.show {
                top: 30px;
                animation: shake 0.8s cubic-bezier(.36,.07,.19,.97) both;
            }
            @keyframes shake {
                10%, 90% { transform: translateX(calc(-50% + 1px)); }
                20%, 80% { transform: translateX(calc(-50% - 2px)); }
                30%, 50%, 70% { transform: translateX(calc(-50% + 4px)); }
                40%, 60% { transform: translateX(calc(-50% - 4px)); }
            }
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
                0% { transform: scale(1); }
                50% { transform: scale(1.05); }
                100% { transform: scale(1); }
            }
            .notification-icon i {
                color: #dc3545;
                font-size: 1.4em;
            }
            .notification-content {
                flex-grow: 1;
            }
            .notification-title {
                color: white;
                font-weight: 600;
                margin: 0 0 5px 0;
                font-size: 1.1em;
                text-shadow: 0 1px 2px rgba(0, 0, 0, 0.1);
            }
            .notification-message {
                color: rgba(255, 255, 255, 0.95);
                margin: 0;
                font-size: 0.95em;
                line-height: 1.4;
                text-shadow: 0 1px 2px rgba(0, 0, 0, 0.1);
            }
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
            @media (max-width: 480px) {
                .notification {
                    min-width: 90%;
                    margin: 0 20px;
                }
            }
            /* Custom select styling */
            .mil-form-group {
                margin-bottom: 15px;
            }
            .mil-label {
                display: block;
                margin-bottom: 8px;
                color: #2e7d32; /* Slightly darker green for better contrast */
                font-weight: 600;
                font-size: 15px;
                text-transform: uppercase;
                letter-spacing: 0.5px;
                position: relative;
                padding-left: 5px;
            }
            .mil-label::before {
                content: '';
                position: absolute;
                left: -5px;
                top: 50%;
                transform: translateY(-50%);
                width: 3px;
                height: 80%;
                background-color: #4caf50;
                border-radius: 2px;
            }
            .mil-select-wrapper {
                position: relative;
                width: 150px; /* Narrow width */
            }
            .mil-select {
                width: 100%;
                padding: 6px 10px;
                border: 1px solid #4caf50;
                border-radius: 6px;
                background-color: #ffffff;
                font-size: 13px;
                color: #333;
                transition: all 0.3s ease;
                appearance: none;
                background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='20' height='20' viewBox='0 0 24 24' fill='none' stroke='%234CAF50' stroke-width='2' stroke-linecap='round' stroke-linejoin='round'%3E%3Cpolyline points='6 9 12 15 18 9'%3E%3C/polyline%3E%3C/svg%3E");
                background-repeat: no-repeat;
                background-position: right 8px center;
                cursor: pointer;
                height: 35px;
                box-shadow: 0 1px 3px rgba(76, 175, 80, 0.1);
            }
            .mil-select:focus {
                border-color: #4caf50;
                box-shadow: 0 0 0 2px rgba(76, 175, 80, 0.2);
                outline: none;
            }
            .mil-select option {
                background-color: #ffffff;
                color: #333;
                padding: 5px;
            }
            .notification.success-register {
                background: linear-gradient(135deg, #4caf50 0%, #43a047 100%);
                border: 1px solid rgba(255, 255, 255, 0.2);
                box-shadow: 0 10px 30px rgba(76, 175, 80, 0.3),
                           0 0 0 1px rgba(76, 175, 80, 0.2);
            }
            .notification.success-register .notification-icon {
                background: rgba(255, 255, 255, 0.95);
            }
            .notification.success-register .notification-icon i {
                color: #4caf50;
                font-size: 1.4em;
            }
            .notification.success-register .notification-title,
            .notification.success-register .notification-text {
                color: white;
                text-shadow: 0 1px 2px rgba(0,0,0,0.1);
            }
        </style>
    </head>

    <body>
        <!-- Error Notification -->
        <div id="notification" class="notification 
            ${(not empty requestScope.err or 
               not empty requestScope.message or 
               not empty requestScope.fromRegisterMessage or 
               param.roleErr eq 'true' or 
               param.fromRegister eq 'true') ? 'show' : ''}
            ${(param.fromRegister eq 'true' or not empty requestScope.fromRegisterMessage) ? ' success-register' : ''}">
            <div class="notification-icon">
                <i class="${(param.fromRegister eq 'true' or not empty requestScope.fromRegisterMessage) ? 'fa fa-check-circle' : 'fa fa-exclamation-circle'}"></i>
            </div>
            <div class="notification-content">
                <h4 class="notification-title">
                    <c:choose>
                        <c:when test="${not empty requestScope.err}">Error</c:when>
                        <c:when test="${not empty requestScope.message}">Message</c:when>
                        <c:when test="${param.fromRegister eq 'true' or not empty requestScope.fromRegisterMessage}">Success</c:when>
                        <c:when test="${param.roleErr eq 'true'}">Access Denied</c:when>
                        <c:otherwise>Notification</c:otherwise>
                    </c:choose>
                </h4>
                <p class="notification-text">
                    <c:choose>
                        <c:when test="${not empty requestScope.err}">${requestScope.err}</c:when>
                        <c:when test="${not empty requestScope.message}">${requestScope.message}</c:when>
                        <c:when test="${not empty requestScope.fromRegisterMessage}">${requestScope.fromRegisterMessage}</c:when>
                        <c:when test="${param.fromRegister eq 'true'}">Registration Successful</c:when>
                        <c:when test="${param.roleErr eq 'true'}">You do not have permission to access this page.</c:when>
                        <c:otherwise></c:otherwise>
                    </c:choose>
                </p>
            </div>
            <button class="notification-close" onclick="this.closest('.notification').classList.remove('show')">
                <i class="fa fa-times"></i>
            </button>
        </div>

        <script>
            // Close notification
            function closeNotification() {
                const notification = document.getElementById('notification');
                if (notification) {
                    notification.classList.remove('show');
                }
            }

            // Auto-close notification after 5 seconds
            document.addEventListener('DOMContentLoaded', function() {
                const notification = document.getElementById('notification');
                if (notification && notification.classList.contains('show')) {
                    setTimeout(closeNotification, 5000);
                }
            });
        </script>
        
        <script>
            function closeModal() {
                const modal = document.getElementById('errorModal');
                modal.classList.remove('show');
                setTimeout(() => {
                    modal.style.display = 'none';
                }, 300);
            }

            // Close modal when clicking outside
            window.onclick = function(event) {
                const modal = document.getElementById('errorModal');
                if (event.target === modal) {
                    closeModal();
                }
            }
        </script>
        <!-- wrapper -->
        <div id="smooth-wrapper" class="mil-wrapper">

            <!-- preloader -->
            <div class="mil-preloader">

            </div>
            <!-- preloader end -->

       

            <!-- top panel end -->
            <%@ include file="header.jsp"%>
            <!-- top panel end -->

            <!-- content -->
            <div id="smooth-content">

                <!-- banner -->
                <div class="container" style="padding-top: 150px">
                        <div class="row align-items-center justify-content-center">
                            <div class="col-xl-9">
                                <div class="mil-banner-text mil-text-center">
                                    <h2 class="mil-mb-80" style="font-family: serif">Log in with Timi Bank</h2>
                                </div>
                            </div>
                        </div>
                    </div>
                <!-- banner end -->

                <!-- register form -->
                <div class="mil-blog-list mip-p-0-160" style="background: linear-gradient(135deg, #f0fff0 0%, #ffffff 100%);">
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-xl-5">
                                <c:set var="cookie" value="${pageContext.request.cookies}"></c:set>
                                <form action="login" method="post" style="background: #ffffff; padding: 40px; border-radius: 15px; box-shadow: 0 5px 20px rgba(76, 175, 80, 0.1);">
                                    <div class="form-group mil-form-group mil-mb-25">
                                        <label for="role-select" class="mil-label">You are:</label>
                                        <div class="mil-select-wrapper">
                                            <select id="role-select" name="role" required class="mil-select">
                                                <option value="customer" ${cookie.crole.value eq "customer"?'selected':''}>Customer</option>
                                                <option value="staff" ${cookie.crole.value eq "staff"?'selected':''}>Staff</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group mil-mb-25">
                                        <input id="username" class="mil-input mil-up" type="text" placeholder="Username" name="username" 
                                            value="${cookie.cusername.value}" required oninput="checkDuplicatedUsername()" 
                                            style="border-radius: 8px; border: 2px solid #e8f5e9; transition: all 0.3s ease;">
                                    </div>
                                    <div class="form-group mil-mb-25" style="position: relative;">
                                        <input style="width: 100%; border-radius: 8px; border: 2px solid #e8f5e9; transition: all 0.3s ease;" 
                                            class="mil-input mil-up" id="password" type="password" placeholder="Password" 
                                            name="password" value="${cookie.cpassword.value}" required>
                                        <span style="position: absolute; top: 50%; right: 15px; transform: translateY(-50%); 
                                            cursor: pointer; color: #4caf50;" toggle="#password" 
                                            class="fa fa-fw fa-eye field-icon toggle-password" onclick="togglePassword('password')"></span>
                                    </div>
                                    <div class="form-group mil-mb-25" style="display: flex; align-items: center;justify-content: space-between;">
                                         <label style="margin-right: 10px;">
                                                <input type="checkbox" name="rem"  id="rememberMe"value="ON" ${(cookie.crem.value == "ON") ? 'checked' : ''}>
                                                Remember me
                                            </label>
                                         <a href="/timibank/forgotPass">Forgot Password</a>
                                    </div>
                                           
                                    <div class="mil-up mil-mb-25" style="display: flex; gap: 15px;">
                                        <button type="submit" class="mil-btn mil-md mil-fw" 
                                            style="flex: 1; border-radius: 8px; background: #4caf50; color: white; 
                                            transition: all 0.3s ease; border: none; font-weight: 600; padding: 12px;">Log in</button>
                                        <a href="#" class="mil-btn mil-md mil-fw" 
                                            style="flex: 1; border-radius: 8px; background: #ffffff; color: #757575; 
                                            transition: all 0.3s ease; border: 1px solid #e0e0e0; font-weight: 600; padding: 12px; 
                                            text-align: center; text-decoration: none; display: flex; align-items: center; justify-content: center; gap: 10px;">
                                            <img src="https://www.google.com/favicon.ico" alt="Google" style="width: 18px; height: 18px;">
                                            Google
                                        </a>
                                    </div>
                                    <div class="mil-divider mil-mb-25" style="text-align: center; position: relative;">
                                        <span style="background: #fff; padding: 0 10px; color: #2e7d32; position: relative; z-index: 1;">Or</span>
                                        <hr style="margin: -9px auto 0; border-top: 1px solid #e8f5e9;">
                                    </div>
                                    <div class="mil-up">
                                        <a href="/timibank/register" class="mil-btn mil-md mil-grey mil-fw" 
                                            style="width: 100%; border-radius: 8px; background: #e8f5e9; color: #2e7d32; 
                                            transition: all 0.3s ease; border: none; font-weight: 600; padding: 12px; 
                                            text-align: center; text-decoration: none; display: flex; align-items: center; justify-content: center;">
                                            Create Account
                                        </a>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- register form end -->

                             <%@ include file="footer.jsp"%>


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

    </body>
</html>
