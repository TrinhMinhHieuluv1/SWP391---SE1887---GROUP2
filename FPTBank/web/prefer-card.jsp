
<%-- 
    Document   : about
    Created on : Jan 20, 2025, 12:34:47 AM
    Author     : ADMIN
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet"/>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&amp;display=swap" rel="stylesheet"/>

        <!-- Favicon -->
        <link rel="shortcut icon" href="img/favicon.png" type="image/x-icon">
        <link rel="icon" href="img/favicon.png" type="image/x-icon">

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css"/>
    </head>
     <style>
   body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
        }
        .container {
            max-width: 900px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
        }
        .title {
            font-size: 24px;
            font-weight: bold;
            color: #333;
            margin-bottom: 10px;
        }
        .date {
            display: flex;
            align-items: center;
            color: #666;
            margin-bottom: 20px;
        }
        .date i {
            margin-right: 5px;
        }
        .content {
            font-size: 20px;
            
            color: #333;
            line-height: 1.5;
            margin-bottom: 20px;
        }
        .content a {
            color: #007bff;
            text-decoration: none;
        }
        .content a:hover {
            text-decoration: underline;
        }
        .highlight {
            font-weight: bold;
            color: #007bff;
        }
        .banner {
            text-align: center;
            margin-bottom: 20px;
        }
        .banner img {
            max-width: 100%;
            height: auto;
        }
        .offers {
            text-align: center;
            margin-bottom: 20px;
        }
        .offers img {
            max-width: 100%;
            height: auto;
        }
        .grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(100px, 1fr));
            gap: 10px;
        }
        .grid-item {
            background-color: #fff;
            border: 1px solid #ddd;
            padding: 10px;
            text-align: center;
        }
        .grid-item img {
            max-width: 100%;
            height: auto;
            margin-bottom: 10px;
        }
        .grid-item p {
            margin: 0;
            font-size: 14px;
            color: #333;
        }
        .grid-item .discount {
            font-size: 16px;
            font-weight: bold;
            color: #007bff;
        }
        
  </style>
    <body>


        <!-- wrapper -->
        <div id="smooth-wrapper" class="mil-wrapper">

            <!--preloader--> 
            <!--            <div class="mil-preloader">
            
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
            <div class="mil-top-panel" id="mil-top-panel">
                <div class="container">
                    <a href="index.jsp" class="mil-logo">
                        <img src="img/logo1.png" alt="Plax" width="200">
                    </a>
                    <nav class="mil-top-menu">
                        <ul>
                            <li class="mil-has-children">
                                <a href="#.">Home</a>
                                <ul>
                                    <li><a href="index.jsp">Mở tài khoản</a></li>
                                    <li><a href="home-2.jsp">Gửi tiết kiệm</a></li>
                                    <li><a href="home-3.jsp">Type 3</a></li>
                                    <li><a href="home-4.jsp">Type 4</a></li>
                                    <li><a href="home-5.jsp">Type 5</a></li>

                                </ul>
                            </li>
                            <li class="mil-active">
                                <a href="about.jsp">About</a>
                            </li>
                            <li>
                                <a href="services.jsp">Services</a>
                            </li>
                            <li class="mil-has-children">
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
                        <a href="login.jsp" class="mil-btn mil-sm">Log in</a>
                        <div class="mil-menu-btn">
                            <span></span>
                        </div>
                    </div>
                </div>
            </div>

            <!-- top panel end -->

            <!-- content -->
            <div id="smooth-content">
                <!-- Menu -->
                <div class="menu">
                    <div class="container">
                        <nav class="row row-cols-2">
                            <div class="col">
                                <div class="menu-list">
                                    <ul class="menu-item">
                                        <li class="menu-label">
                                            <a href="services.jsp">Dịch vụ</a>
                                        </li>
                                        <li class="menu-label">
                                            Công cụ
                                            <ul class="sub-menu-item">
                                                <li>

                                                    <a class="sub-menu-link" href="calSaving.jsp">Lãi suất tiết kiệm</a>
                                                </li>
                                                <li>
                                                    <a class="sub-menu-link" href="calLoan.jsp">Lãi vay ngân hàng</a>
                                                </li>
                                            </ul>
                                        </li>
                                        <li class="menu-label">
                                            Ưu đãi
                                            <ul class="sub-menu-item">
                                                <li>

                                                    <a class="sub-menu-link" href="prefer-card.jsp">Thẻ TIMI - Chi tiêu giảm giá hàng đầu Việt Nam</a>
                                                </li>
                                                <li>
                                                    <a class="sub-menu-link" href="prefer.jsp">Tất cả ưu đãi</a>
                                                </li>
                                            </ul>
                                        </li>
                                    </ul>
                                </div>
                            </div>

                            <div class="col">
                                <form action="#!" class="menu-form__search">
                                    <input type="text" name="search_value" class="menu-form__search-input"/>
                                    <div class="mil-menu-buttons">
                                        <input type="submit" class="mil-btn mil-ssm" value="Search" />
                                    </div>
                                </form>
                            </div>
                        </nav>
                    </div>
                </div>
        <div class="container">
   <div class="title">
    THẺ TIMI - CHI TIÊU GIẢM GIÁ HÀNG ĐẦU VIỆT NAM THÁNG 12
   </div>
   <div class="date">
    <i class="fas fa-calendar-alt">
    </i>
    <span>
     01.12.2024
    </span>
   </div>
   <div class="content">
    Biết tin gì chưa? Sở hữu thẻ TIMI là sở hữu ngay kho deal chi tiêu giảm giá siêu khủng!
   </div>
   <div class="content">
    Lưu ngay toàn bộ ưu đãi thẻ TIMI - chi tiêu giảm giá hàng đầu Việt Nam
    <a href="prefer.jsp">
     tại đây
    </a>
    <br/>
    Đặc biệt, bật mí cho chủ thẻ
    <span class="highlight">
     Top 30 ưu đãi nổi bật
    </span>
    cùng loạt deal giảm giá đến 50% và nhiều voucher tiền triệu khi chi tiêu mua sắm những thương hiệu uy tín nhất với thẻ MB:
   </div>
   <div class="banner">
    <img alt="Banner Image" height="100" src="img\about\uudai1.1.png" width="800"/>
   </div>
   <div class="offers">
    <img alt="Top 30 Ưu Đãi" height="200" src="img\about\uudai1.png" width="800"/>
   </div>
            <br/>
            <div>
                <div class="content">
Chủ thẻ Timi Bank thì không thể bỏ qua top deal đỉnh sau:                </div>
                <div class="banner">
                    <img alt="Banner Image" height="100" src="img\about\uudai2.png" width="800"/>
                </div>
                <br/><!-- comment -->
                 <div class="content">
                    Chủ thẻ TIMI BANK cũng có cực nhiều deal hot “bỏ túi” đó!
                </div>
                <div class="banner">
                    <img alt="Banner Image" height="100" src="img\about\uudai3.png" width="800"/>
                </div>
                <br/><!-- comment -->
                
                <div>
                    Đừng quên update lịch chi tiêu giảm giá cùng thẻ TM để “chạm là giảm giá – quẹt là hoàn tiền” nha!

Khám phá thêm tại mục Ưu đãi/App TMBank: 
 
<a class="highlight" href="prefer.jsp">
     tại đây
    </a>
                </div>
                
            </div>
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
