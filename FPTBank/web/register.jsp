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

        <title>FPT - Finance & Banking</title>
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

            function checkConfirmPassword() {
                const password = document.getElementById('password').value;
                const confirmPassword = document.getElementById('confirm-password').value;
                const err = document.getElementById('err-confirm-password');
                if (password !== confirmPassword) {
                    err.style.display = 'block';
                } else {
                    err.style.display = 'none';
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
            <!--        <div class="mil-preloader">
                        <div class="mil-load"></div>
                        <p class="h2 mil-mb-30"><span class="mil-light mil-counter" data-number="100">100</span><span class="mil-light">%</span></p>
                    </div>-->
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
                    <a href="index.html" class="mil-logo">
                        <img src="img/logo1.png" alt="Plax" width="200">
                    </a>
                    <nav class="mil-top-menu">
                        <ul>
                            <li class="mil-has-children">
                                <a href="#.">Home</a>
                                <ul>
                                    <li><a href="index.html">Mở tài khoản</a></li>
                                    <li><a href="home-2.html">Gửi tiết kiệm</a></li>
                                    <li><a href="home-3.html">Type 3</a></li>
                                    <li><a href="home-4.html">Type 4</a></li>
                                    <li><a href="home-5.html">Type 5</a></li>

                                </ul>
                            </li>
                            <li>
                                <a href="about.html">About</a>
                            </li>
                            <li>
                                <a href="services.html">Services</a>
                            </li>
                            <li>
                                <a href="#.">Blog</a>
                                <ul>
                                    <li><a href="blog.html">Blog list</a></li>
                                    <li><a href="publication.html">Blog details</a></li>
                                </ul>
                            </li>
                            <li>
                                <a href="contact.html">Contact</a>
                            </li>
                            <li class="mil-has-children">
                                <a href="#.">Pages</a>
                                <ul>
                                    <li><a href="career.html">Career</a></li>
                                    <li><a href="career-details.html">Career details</a></li>
                                    <li><a href="price.html">Pricing</a></li>
                                    <li><a href="register.html">Register</a></li>

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
                                    <h1 class="mil-mb-60" style="font-family: serif">Đăng kí với FPT Bank:</h1>
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
                                <form action="register" method="post">
                                    <input class="mil-input mil-up mil-mb-15" type="text" placeholder="Username" name="username" value="${requestScope.username}" required>
                                    <div style="position: relative; display: inline-block; width: 100%;">
                                        <input style="width: 100%; padding-right: 40px; box-sizing: border-box;" class="mil-input mil-up mil-mb-15" id="password" type="password" placeholder="Password" name="password" value="${requestScope.password}" required>
                                        <span style="position: absolute; top: 40%; right: 10px;transform: translateY(-50%);cursor: pointer;color: #666;" toggle="#password" class="fa fa-fw fa-eye field-icon toggle-password" onclick="togglePassword('password')"></span>
                                    </div>
                                    <div style="position: relative; display: inline-block; width: 100%;">
                                        <input style="width: 100%; padding-right: 40px; box-sizing: border-box;" type="password" id="confirm-password" class="mil-input mil-up mil-mb-15" placeholder="Confirm Password" name="confirm-password" oninput="checkConfirmPassword()" value="${requestScope.password}"  required>
                                        <span style="position: absolute; top: 40%; right: 10px;transform: translateY(-50%);cursor: pointer;color: #666;" toggle="#confirm-password" class="fa fa-fw fa-eye field-icon toggle-password" onclick="togglePassword('confirm-password')" style=""></span>
                                    </div>
                                    <div id="err-confirm-password" style="color: red; display: none">Confirm Password is incorrect. Try again!</div>
                                    <input type="text" class="mil-input mil-up mil-mb-15" placeholder="Full Name" name="name" value="${requestScope.name}"  required>
                                    <select name="gender" class="mil-input mil-up mil-mb-15">
                                        <option value="" disabled selected hidden style="color: black">Gender</option>
                                        <option value="Male" ${requestScope.gender.equals("Male")?"selected":""}>Male</option>
                                        <option value="Female" ${requestScope.gender.equals("Female")?"selected":""}>Female</option>
                                        <option value="Others" ${requestScope.gender.equals("Others")?"selected":""}>Others</option>
                                    </select>
                                    <input type="date" class="mil-input mil-up mil-mb-15" name="dob" value="${requestScope.dob}" placeholder="Date of birth">
                                    <input type="text" class="mil-input mil-up mil-mb-15" name="phone" value="${requestScope.phone}" placeholder="Phone">
                                    <input type="text" class="mil-input mil-up mil-mb-15" name="mail" value="${requestScope.mail}" placeholder="Mail">
                                    <p class="mil-text-xs mil-soft" style="margin-bottom: 10px">Bạn có đồng ý với <a href="#." class="mil-accent">điều khoản và quy định của chúng tôi không </a>.</p>
                                    <div class="mil-up mil-mb-30">
                                        <button type="submit" class="mil-btn mil-md mil-fw">Create Account</button>
                                    </div>
                                    <p class="mil-text-xs mil-text-center mil-soft mil-mb-30">If you already have an account:</p>
                                    <div class="mil-up mil-mb-15">
                                        <a href="/fptbank/login" class="mil-btn mil-md mil-grey mil-fw mil-mb-30">Log in</a>
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
