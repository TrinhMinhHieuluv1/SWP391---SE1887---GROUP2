<%-- 
    Document   : forgotPass
    Created on : Jan 23, 2025, 10:14:47 PM
    Author     : tiend
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
    </head>
    <body>
        <!-- wrapper -->
        <div id="smooth-wrapper" class="mil-wrapper">
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
                <div class="container" style="padding-top: 150px">
                    <div class="row align-items-center justify-content-center">
                        <div class="col-xl-9">
                            <div class="mil-banner-text mil-text-center">
                                <h2 class="mil-mb-80" style="font-family: serif">Reset Password</h2>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- banner end -->
                 <div class="mil-blog-list mip-p-0-160">
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-xl-5">
                                <form action="forgotPass" method="post" style="background: #ccffcc; padding: 30px">
                                    <p class="mil-text-center mil-mb-15"style="color: black"> Enter your email address and we'll send you an email with instructions to reset your password</p>
                                    <input name="email" type="email"class="mil-input mil-up mil-mb-15" placeholder="Email"/>
                                    
                                    
                                    <h5 style="color: red">${requestScope.err}</h5>
                                    
                                    <div class="mil-up mil-mb-15 mil-mt-30">
                                        <button type="submit" class="mil-btn mil-md mil-fw">Reset password</button>
                                    </div>
                                    <div class="mil-up mil-mb-15">
                                        <a href="/timibank/login" class="mil-btn mil-md mil-grey mil-fw mil-mb-30">Back to login</a>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
    </body>
</html>






