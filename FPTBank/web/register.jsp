<%-- 
    Document   : register
    Created on : Jan 13, 2025, 12:32:16 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en-US">

    <head>

        <title>TIMI - Register</title>
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
            function validateForm(event) {
                if (checkConfirmPassword() === false) {
                    alert("Confirm password doesn't match password. Try again!");
                    event.preventDefault();
                }
                if (validateUsername() === false) {
                    alert("Invalid username. Try again!");
                    event.preventDefault();
                }
                if (validatePassword() === false) {
                    alert("Invalid password. Try again!");
                    event.preventDefault();
                }
                if (validatePhone() === false) {
                    alert("Invalid phone number. Try again!");
                    event.preventDefault();
                }
                if (validateCCCD() === false) {
                    alert("Invalid CCCD. Try again!");
                    event.preventDefault();
                }
                if (checkPhone() === false) {
                    alert("Invalid phone number. Try again!");
                    event.preventDefault();
                }
                if (checkCCCD() === false) {
                    alert("Invalid CCCD. Try again!");
                    event.preventDefault();
                }
            }

            function checkConfirmPassword() {
                const password = document.getElementById('password').value;
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

            function checkUsername() {
                const usernameToCheck = document.getElementById('username').value;
                const err = document.getElementById('duplicated-username');
                const usernameArray = <%=request.getAttribute("usernameArray")%>;
                if (usernameArray.includes(usernameToCheck)) {
                    err.style.display = 'block';
                    return false;
                } else {
                    err.style.display = 'none';
                    return true;
                }
            }

            function checkPhone() {
                const phoneToCheck = document.getElementById('phone').value;
                const err = document.getElementById('duplicated-phone');
                const phoneArray = <%=request.getAttribute("phoneArray")%>;
                if (phoneArray.includes(phoneToCheck)) {
                    err.style.display = 'block';
                    return false;
                } else {
                    err.style.display = 'none';
                    return true;
                }
            }

            function checkCCCD() {
                const cccdToCheck = document.getElementById('CCCD').value;
                const err = document.getElementById('duplicated-cccd');
                const cccdArray = <%=request.getAttribute("cccdArray")%>;
                if (cccdArray.includes(cccdToCheck)) {
                    err.style.display = 'block';
                    return false;
                } else {
                    err.style.display = 'none';
                    return true;
                }
            }

            // Function to validate Username
            function validateUsername() {
                const username = document.getElementById('username').value;
                const err = document.getElementById('err-username');
                const usernameRegex = /^[a-zA-Z0-9!@#$%^&*()_+\-=\[\]{};':",.<>?/]{6,20}$/;
                if (usernameRegex.test(username)) {
                    err.style.display = 'none';
                    return true;
                } else {
                    err.style.display = 'block';
                    return false;
                }
                ;
            }

            // Function to validate Password
            function validatePassword() {
                const password = document.getElementById('password').value;
                const err = document.getElementById('err-password');
                const passwordRegex = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[@#$%^&*!])[a-zA-Z\d@#$%^&*!]{8,16}$/;
                if (passwordRegex.test(password)) {
                    err.style.display = 'none';
                    return true;
                } else {
                    err.style.display = 'block';
                    return false;
                }
                ;
            }

            // Function to validate phone number
            function validatePhone() {
                const phone = document.getElementById('phone').value;
                const err = document.getElementById('err-phone');
                const phoneRegex = /^0[0-9]{9}$/;
                if (phoneRegex.test(phone)) {
                    err.style.display = 'none';
                    return true;
                } else {
                    err.style.display = 'block';
                    return false;
                }
                ;
            }

            // Function to validate CCCD (Citizen Identification Number)
            function validateCCCD() {
                const cccd = document.getElementById('CCCD').value;
                const err = document.getElementById('err-cccd');
                const cccdRegex = /^[0-9]{12}$/;
                if (cccdRegex.test(cccd)) {
                    err.style.display = 'none';
                    return true;
                } else {
                    err.style.display = 'block';
                    return false;
                }
                ;
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
            <div class="mil-progress-track">
                <div class="mil-progress"></div>
            </div>
            <!-- scroll progress end -->

            <!-- back to top -->
            <div class="progress-wrap active-progress"></div>

            <!-- top panel end -->
            <div class="mil-top-panel">
                <div class="container">
                    <a href="/timibank/home" class="mil-logo">
                        <img src="img/logo1.png" alt="Plax" width="200">
                    </a>
                    <nav class="mil-top-menu">
                        <ul>
                            <li class="mil-has-children">
                                <a href="/timibank/home">Home</a>
                                <ul>
                                    <li><a href="index.jsp">Mở tài khoản</a></li>
                                    <li><a href="home-2.jsp">Gửi tiết kiệm</a></li>
                                    <li><a href="home-3.jsp">Type 3</a></li>
                                    <li><a href="home-4.jsp">Type 4</a></li>
                                    <li><a href="home-5.jsp">Type 5</a></li>

                                </ul>
                            </li>
                            <li>
                                <a href="about">About</a>
                            </li>
                            <li>
                                <a href="services.jsp">Services</a>
                            </li>
                            <li>
                                <a href="#.">Blog</a>
                                <ul>
                                    <li><a href="blog.jsp">Blog list</a></li>
                                    <li><a href="publication.jsp">Blog details</a></li>
                                </ul>
                            </li>
                            <li>
                                <a href="contact.jsp">Contact</a>
                            </li>
                            <li class="mil-has-children">
                                <a href="#.">Pages</a>
                                <ul>
                                    <li><a href="career.jsp">Career</a></li>
                                    <li><a href="career-details.jsp">Career details</a></li>
                                    <li><a href="price.jsp">Pricing</a></li>
                                    <li><a href="register.jsp">Register</a></li>

                                </ul>
                            </li>
                        </ul>
                    </nav>
                    <div class="mil-menu-buttons">
                        <div class="mil-menu-btn">
                            <span></span>
                        </div>
                    </div>
                </div>
            </div>
            <!-- top panel end -->

            <!-- content -->
            <div id="smooth-content">

                <!-- banner -->
                <div class="mil-banner mil-banner-inner mil-dissolve">
                    <div class="container">
                        <div class="row align-items-center justify-content-center">
                            <div class="col-xl-8">
                                <div class="mil-banner-text mil-text-center">
                                    <h2 class="mil-mb-60" style="font-family: serif">Register with Timi Bank</h2>
                                </div>
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
                                <form action="register" method="post" onsubmit="validateForm(event)">
                                    <input id="username" class="mil-input mil-up mil-mb-15" type="text" placeholder="Username" name="username" required oninput="checkUsername(); validateUsername()">
                                    <div id="err-username" style="color: red; display: none">Username phải từ 6-20 ký tự, chỉ chứa chữ cái, số và các ký tự, không được có dấu cách!</div>
                                    <div id="duplicated-username" style="color: red; display: none">Username already exists. Please choose another!</div>
                                    <div style="position: relative; display: inline-block; width: 100%;">
                                        <input style="width: 100%; padding-right: 40px; box-sizing: border-box;" class="mil-input mil-up mil-mb-15" id="password" type="password" placeholder="Password" name="password" required oninput="validatePassword()">
                                        <span style="position: absolute; top: 40%; right: 10px;transform: translateY(-50%);cursor: pointer;color: #666;" toggle="#password" class="fa fa-fw fa-eye field-icon toggle-password" onclick="togglePassword('password')"></span>
                                    </div>
                                    <div id="err-password" style="color: red; display: none">Mật khẩu phải có 8-16 ký tự, chứa ít nhất một chữ hoa, một chữ thường, một số và một ký tự đặc biệt!</div>
                                    <div style="position: relative; display: inline-block; width: 100%;">
                                        <input style="width: 100%; padding-right: 40px; box-sizing: border-box;" type="password" id="confirm-password" class="mil-input mil-up mil-mb-15" placeholder="Confirm Password" name="confirm-password" oninput="checkConfirmPassword()" required>
                                        <span style="position: absolute; top: 40%; right: 10px;transform: translateY(-50%);cursor: pointer;color: #666;" toggle="#confirm-password" class="fa fa-fw fa-eye field-icon toggle-password" onclick="togglePassword('confirm-password')" style=""></span>
                                    </div>
                                    <div id="err-confirm-password" style="color: red; display: none">Confirm Password is incorrect. Try again!</div>

                                    <input type="text" class="mil-input mil-up mil-mb-15" placeholder="Full Name" name="name" required>
                                    <input type="text" style="width: 100%; padding-right: 40px; box-sizing: border-box;" class="mil-input mil-up mil-mb-15" id="image" name="image" placeholder="Image"
                                           class="form-control" onchange="updateImagePreview(this.value)">
                                    <img id="imagePreview" src="" class="image-preview">
                                    <select name="gender" class="mil-input mil-up mil-mb-15" required>
                                        <option value="" disabled selected hidden style="color: black">Gender</option>
                                        <option value="Male" ${requestScope.gender.equals("Male")?"selected":""}>Male</option>
                                        <option value="Female" ${requestScope.gender.equals("Female")?"selected":""}>Female</option>
                                    </select>
                                    <input type="date" class="mil-input mil-up mil-mb-15" name="dob" placeholder="Date of birth">
                                    <input type="text" class="mil-input mil-up mil-mb-15" name="phone" placeholder="Phone" id="phone" oninput="validatePhone(); checkPhone()" required>
                                    <div id="err-phone" style="color: red; display: none">Phone has to have 10 digits and start with 0. Try again!</div>
                                    <div id="duplicated-phone" style="color: red; display: none">Phone already exists. Please choose another!</div>
                                    <input type="text" class="mil-input mil-up mil-mb-15" name="address" placeholder="Address">
                                    <input type="text" class="mil-input mil-up mil-mb-15" name="CCCD" placeholder="CCCD" id="CCCD" oninput="validateCCCD(); checkCCCD()" required>
                                    <div id="err-cccd" style="color: red; display: none">CCCD has to have 12 digits. Try again!</div>
                                    <div id="duplicated-cccd" style="color: red; display: none">CCCD already exists. Please choose another!</div>
                                    <input required type="checkbox"> <p class="mil-text-xs mil-soft" style="margin-bottom: 10px; display: inline">Do you agree to <a href="/timibank/TermAndService/TermAndService.pdf" target="_blank" class="mil-accent">our terms and conditions</a>.</p>
                                    <div class="mil-up mil-mb-30">
                                        <button type="submit" class="mil-btn mil-md mil-fw">Create Account</button>
                                    </div>
                                    <p class="mil-text-xs mil-text-center mil-soft mil-mb-30">If you already have an account:</p>
                                    <div class="mil-up mil-mb-15">
                                        <a href="/timibank/login" class="mil-btn mil-md mil-grey mil-fw mil-mb-30">Log in</a>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- register form end -->
                
                <div class="mil-space-fix"></div>

            </div>
            <!-- content end -->
        </div>
        <!-- wrapper end -->

        <script>
            // Function to update image preview
            function updateImagePreview(url) {
                const preview = document.getElementById('imagePreview');
                preview.src = url;
            }
        </script>
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
