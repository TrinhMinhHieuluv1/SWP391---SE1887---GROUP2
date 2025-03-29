<%-- 
    Document   : service
    Created on : Jan 20, 2025, 12:42:28 AM
    Author     : ADMIN
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
                                    <h2>TIMIBANK Services</h2>
                                    <ul class="mil-breadcrumbs mil-center">
                                        <li><a href="index.jsp">Home</a></li>
                                        <li><a href="services.jsp">Services</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- banner end -->

                <!-- service -->
                <div class="mil-features mil-p-0-80">
                    <div class="container">
                        <div class="row justify-content-between align-items-center">
                            <div class="col-xl-5 mil-mb-80">
                                <h2 class="mil-mb-30 mil-up">Savings Deposit</h2>
                                <div class="about-introduction__content about-introduction__content--current">
                                    <p class="about-introduction__desc">
                                        Bank savings deposits are a safe investment method, where customers deposit a certain amount of money in the bank for a specified period to earn interest. This is one of the most popular ways to protect and grow assets steadily, especially for those who do not want to take high risks in other investment channels such as stocks or real estate.
                                    </p>
                                    <ul class="about-introduction__more send-money__more">
                                        <li class="about-introduction__more-item">High security: Deposits are insured according to State Bank regulations.</li>
                                        <li class="about-introduction__more-item">Stable interest rates: Help preserve and grow assets over time.</li>
                                        <li class="about-introduction__more-item">Liquidity: Easily withdraw money when needed, especially for non-term savings.</li>
                                        <li class="about-introduction__more-item">Flexible options: Various terms and deposit methods suit different needs.</li>
                                    </ul>
                                    <button type="button" class="about-history__btn-more mil-btn mil-ssm mil-empty send-money__toggle--btn">
                                        <span class="text-collapsed" style="display: none">Collapse</span>
                                        <span class="text-expand">See more</span>
                                        <span class="icon-arrow-right"></span>
                                    </button>
                                </div>  
                                <br/>
                                <div class="mil-up"><a href="register.jsp" class="mil-btn mil-m mil-add-arrow">User Guide</a></div>
                            </div>
                            <div class="col-xl-6 mil-mb-80">
                                <div class="mil-image-frame mil-up">
                                    <img src="img/inner-pages/3.png" alt="image" class="mil-scale-img" data-value-1="1" data-value-2="1.2">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- service end -->

                <!-- service -->
                <div class="mil-features mil-p-0-80">
                    <div class="container">
                        <div class="row flex-sm-row-reverse justify-content-between align-items-center">
                            <div class="col-xl-5 mil-mb-80">
                                <h2 class="mil-mb-30 mil-up">Loan Services</h2>
                                <div class="about-introduction__content about-introduction__content--current">
                                    <p class="about-introduction__desc">
                                        Bank loans are financial services where the bank provides individuals or businesses with a sum of money for various purposes, with a commitment to repay within a specified period along with interest. This method helps customers meet financial needs such as buying a house, purchasing a car, running a business, or personal spending.
                                    </p>
                                    <ul class="about-introduction__more borrow-money__more">
                                        <li class="about-introduction__more-item">Flexible financial support: Quick access to capital for individuals and businesses.</li>
                                        <li class="about-introduction__more-item">Diverse loan products: Various options tailored to different needs.</li>
                                        <li class="about-introduction__more-item">Flexible loan terms: Short-term or long-term loans based on repayment capacity.</li>
                                        <li class="about-introduction__more-item">Competitive interest rates: Lower than other forms of borrowing like payday loans.</li>
                                    </ul>
                                    <button type="button" class="about-history__btn-more mil-btn mil-ssm mil-empty borrow-money__toggle--btn">
                                        <span class="text-collapsed" style="display: none">Collapse</span>
                                        <span class="text-expand">See more</span>
                                        <span class="icon-arrow-right"></span>
                                    </button>
                                </div>  
                                <br/>
                                <div class="mil-up"><a href="register.jsp" class="mil-btn mil-m mil-add-arrow">User Guide</a></div>
                            </div>
                            <div class="col-xl-6 mil-mb-80">
                                <div class="mil-image-frame ml-up">
                                    <img src="img/inner-pages/4.png" alt="image" class="mil-scale-img" data-value-1="1" data-value-2="1.2">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- service end -->

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

        <script src="./js/scripts.js"></script>
    </body>
</html>
