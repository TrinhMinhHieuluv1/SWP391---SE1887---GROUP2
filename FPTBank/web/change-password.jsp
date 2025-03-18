<%-- 
    Document   : changepassword
    Created on : Jan 21, 2025, 1:00:18 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>

        <title>TIMI - Change Password</title>
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
            function validateForm(event){
                if (checkConfirmPassword()===false) {
                    alert("Confirm password must match password. Try again!");
                    event.preventDefault();
                }
                if (checkNewPassword()===false) {
                    alert("New Password can't be same as Old Password. Try again!");
                    event.preventDefault();
                }                
            }
            
            function checkNewPassword() {
                const password = document.getElementById('password').value;
                const newPassword = document.getElementById('new-password').value;
                const err = document.getElementById('err-new-password');
                if (password === newPassword) {
                    err.style.display = 'block';
                    return false;
                } else {
                    err.style.display = 'none';
                    return true;
                }
            }
            
            function checkConfirmPassword() {
                const password = document.getElementById('new-password').value;
                const confirmPassword = document.getElementById('confirm-password').value;
                const err = document.getElementById('err-confirm-password');
                if (password !== confirmPassword) {
                    err.style.display = 'block';
                    return false;
                } else {
                    err.style.display = 'none';
                    return true;
                }
            }
            
            function togglePassword(id) {
                const passwordField = document.getElementById(id);
                const passwordFieldType = passwordField.getAttribute('type') === 'password' ? 'text' : 'password';
                passwordField.setAttribute('type', passwordFieldType);
                const eyeIcon = event.target;
                eyeIcon.classList.toggle('fa-eye');
                eyeIcon.classList.toggle('fa-eye-slash');
            }
        </script>
    </head>

    <body>
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
            <!-- top panel end -->

            <!-- content -->
            <div id="smooth-content">

                <!-- banner -->
                <div class="container" style="padding-top: 150px">
                        <div class="row align-items-center justify-content-center">
                            <div class="col-xl-9">
                                <div class="mil-banner-text mil-text-center">
                                    <h2 class="mil-mb-80" style="font-family: serif">Change Password</h2>
                                </div>
                            </div>
                        </div>
                    </div>
                <!-- banner end -->

                <!-- register form -->
                <div class="mil-blog-list mip-p-0-160">
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-xl-5">
                                <c:set var="cookie" value="${pageContext.request.cookies}"></c:set>
                                <form action="change-password" method="post" style="background: #ccffcc; padding: 30px" onsubmit="validateForm(event)">
                                    <input id="username" class="mil-input mil-up mil-mb-15" name="username" value="${sessionScope.account.username}" required readonly>
                                    <div style="position: relative; display: inline-block; width: 100%;">
                                        <input style="width: 100%; padding-right: 40px; box-sizing: border-box;" class="mil-input mil-up mil-mb-15" id="password" type="password" placeholder="Old Password" name="password" required>
                                        <span style="position: absolute; top: 40%; right: 10px;transform: translateY(-50%);cursor: pointer;color: #666;" toggle="#password" class="fa fa-fw fa-eye field-icon toggle-password" onclick="togglePassword('password')"></span>
                                    </div>
                                    <div style="position: relative; display: inline-block; width: 100%;">
                                        <input style="width: 100%; padding-right: 40px; box-sizing: border-box;" class="mil-input mil-up mil-mb-15" id="new-password" type="password" placeholder="New Password" name="new-password" required oninput="checkNewPassword()">
                                        <span style="position: absolute; top: 40%; right: 10px;transform: translateY(-50%);cursor: pointer;color: #666;" toggle="#password" class="fa fa-fw fa-eye field-icon toggle-password" onclick="togglePassword('new-password')"></span>
                                    </div>
                                    <div id="err-new-password" style="color: red; display: none">New Password can't be same as Old Password</div>
                                    <div style="position: relative; display: inline-block; width: 100%;">
                                        <input style="width: 100%; padding-right: 40px; box-sizing: border-box;" class="mil-input mil-up mil-mb-15" id="confirm-password" type="password" placeholder="Confirm Password" name="confirm-password" required oninput="checkConfirmPassword()">
                                        <span style="position: absolute; top: 40%; right: 10px;transform: translateY(-50%);cursor: pointer;color: #666;" toggle="#password" class="fa fa-fw fa-eye field-icon toggle-password" onclick="togglePassword('confirm-password')"></span>
                                    </div>
                                    <div id="err-confirm-password" style="color: red; display: none">Confirm Password is incorrect. Try again!</div>
                                    <h5 style="color: red">${requestScope.err}</h5>
                                    <div class="mil-up mil-mb-15 mil-mt-30">
                                        <button type="submit" class="mil-btn mil-md mil-fw">Change Password</button>
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
