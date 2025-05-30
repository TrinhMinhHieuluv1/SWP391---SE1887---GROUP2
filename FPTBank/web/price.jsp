<%-- 
    Document   : price
    Created on : Jan 20, 2025, 12:41:48 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
 <head>

    <title>TIMI - Finance & Banking</title>
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
            <div class="mil-banner mil-banner-inner mil-dissolve">
                <div class="container">
                    <div class="row align-items-center justify-content-center">
                        <div class="col-xl-8">
                            <div class="mil-banner-text mil-text-center">
                                
                                <h2 >Transparent, Simple and Adapted to your Needs</h2>
                                <ul class="mil-breadcrumbs mil-pub-info mil-center">
                                    <li><a href="home-3.jsp">Software Engineering</a></li>
                                    <li><a href="home.jsp">Home</a></li>
                                    <li><a href="price.jsp">Pricing</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- banner end -->

            <!-- prices-->
            <div class="mil-blog-list mil-p-0-130">
                <div class="container">
                    <div class="row justify-content-center">

                        <div class="mil-switcher mil-mb-60 mil-up">
                            <span class="mil-active" id="month">Monthly</span>
                            <span class="" id="year">Yearly</span>
                        </div>

                        <div class="row">
                            <div class="col-md-4 col-sm-6">


                                <div class="mil-price-card mil-up mil-mb-30">
                                    <h6 class="mil-mb-15">Plax Consumer</h6>
                                    <p class="mil-text-s mil-soft mil-mb-30">Sending and receiving money in up to 5 countries</p>
                                    <h4 class="mil-mb-30">$ <span class="mil-pricing-table-price" data-year-price="0.00" data-month-price="0.00">0.00</span><span class="mil-sup-text mil-soft"> / month</span></h4>
                                    <a href="contact.jsp" class="mil-btn mil-m mil-fw mil-mb-30">Choose a plan</a>
                                    <ul class="mil-text-mist mil-type-2 mil-check mil-text-s mil-soft mil-mb-60">
                                        <li>Unlimited transactions</li>
                                        <li>Basic Technical Support (Email)</li>
                                        <li>Instructional advisor</li>
                                    </ul>
                                    <a href="#." class="mil-link mil-accent">View all features</a>
                                </div>

                            </div>

                            <div class="col-md-4 col-sm-6">


                                <div class="mil-price-card mil-featured mil-up mil-mb-30">
                                    <h6 class="mil-light mil-mb-15">Plax Business</h6>
                                    <p class="mil-text-s mil-dark-soft mil-mb-30">Access to multi-currency accounts and local payments</p>
                                    <h4 class="mil-light mil-mb-30">$ <span class="mil-light mil-pricing-table-price" data-year-price="9.99" data-month-price="14.99">14.99</span><span class="mil-sup-text mil-dark-soft"> / month</span></h4>
                                    <a href="contact.jsp" class="mil-btn mil-m mil-fw mil-mb-30">Choose a plan</a>
                                    <ul class="mil-text-mist mil-type-2 mil-check mil-text-s mil-dark-soft mil-mb-60">
                                        <li>Unlimited transactions</li>
                                        <li>Basic Technical Support (Email)</li>
                                        <li>Instructional advisor</li>
                                    </ul>
                                    <a href="#." class="mil-link mil-accent">View all features</a>
                                </div>

                            </div>

                            <div class="col-md-4 col-sm-6">

                                <div class="mil-price-card mil-up mil-mb-30">
                                    <h6 class="mil-mb-15">Plax Enterprise</h6>
                                    <p class="mil-text-s mil-soft mil-mb-30">Secure and reliable transactions at the enterprise level</p>
                                    <h4 class="mil-mb-30">$ <span class="mil-pricing-table-price" data-year-price="34.99" data-month-price="49.00">49.99</span><span class="mil-sup-text mil-soft"> / month</span></h4>
                                    <a href="contact.jsp" class="mil-btn mil-m mil-fw mil-mb-30">Choose a plan</a>
                                    <ul class="mil-text-mist mil-type-2 mil-check mil-text-s mil-soft mil-mb-60">
                                        <li>Unlimited transactions</li>
                                        <li>Basic Technical Support (Email)</li>
                                        <li>Instructional advisor</li>
                                    </ul>
                                    <a href="#." class="mil-link mil-accent">View all features</a>
                                </div>

                            </div>

                        </div>

                    </div>
                </div>
            </div>
            <!-- prices end -->

            <!-- call to action -->
            <div class="mil-cta mil-up">
                <div class="container">
                    <div class="mil-out-frame mil-p-160-160" style="background-image: url(img/home-3/5.png)">
                        <div class="row justify-content-between align-items-center">
                            <div class="col-xl-7 mil-sm-text-center">
                                <h2 class="mil-light mil-mb-30 mil-up">Discover the freedom <br>of Total Financial Control</h2>
                                <p class="mil-text-m mil-mb-60 mil-dark-soft mil-up">Join Plax and take the first step towards a more <br> balanced and hassle-free financial life.</p>
                                <div class="mil-buttons-frame mil-up">
                                    <a href="#." class="mil-btn mil-md">App Store</a>
                                    <a href="#." class="mil-btn mil-border mil-md">Google Play</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- call to action end -->

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

</body>
</html>
