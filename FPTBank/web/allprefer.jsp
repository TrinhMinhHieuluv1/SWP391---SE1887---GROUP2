<%-- 
    Document   : allprefer
    Created on : Feb 5, 2025, 8:41:39 PM
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
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f5f5f5;
        }
        .container11 {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
        }
        .card-types {
            display: flex;
            justify-content: space-between;
            margin-bottom: 20px;
        }
        .card-types button {
            background-color: #fff;
            border: 1px solid #ccc;
            border-radius: 20px;
            padding: 10px 20px;
            cursor: pointer;
            font-weight: 500;
        }
        .card-types button.active {
            background-color: #008000;
            color: #fff;
        }
        .content11 {
            display: flex;
            justify-content: space-between;
        }
        .cards {
            display: flex;
            justify-content: space-between;
            width: 75%;
        }
        .card {
            background-color: #fff;
            border: 1px solid #ccc;
            border-radius: 10px;
            width: 30%;
            padding: 20px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        .card img {
            width: 100%;
            border-radius: 10px;
        }
        .card h3 {
            font-size: 18px;
            margin: 15px 0;
        }
        .card p {
            font-size: 14px;
            color: #333;
        }
        .card a {
            color: #0046be;
            text-decoration: none;
            font-weight: 500;
        }
        .card a:hover {
            text-decoration: underline;
        }
        .sidebar {
            background-color: #f0f0f0;
            border-radius: 10px;
            padding: 20px;
            width: 23%;
        }
        .sidebar h3 {
            font-size: 18px;
            margin-bottom: 15px;
        }
        .sidebar p {
            font-size: 14px;
            color: #333;
            margin-bottom: 20px;
        }
        .sidebar a {
            display: block;
            background-color: #008000;
            color: #fff;
            text-align: center;
            padding: 10px;
            border-radius: 5px;
            text-decoration: none;
            margin-bottom: 10px;
        }
        .sidebar a i {
            margin-right: 5px;
        }
        .sidebar a.secondary {
            background-color: #fff;
            color: #0046be;
            border: 1px solid #0046be;
        }
        .sidebar a.secondary:hover {
            background-color: #0046be;
            color: #fff;
        }
        @media (max-width: 768px) {
            .content {
                flex-direction: column;
            }
            .cards {
                width: 100%;
                flex-direction: column;
            }
            .card {
                width: 100%;
                margin-bottom: 20px;
            }
            .sidebar {
                width: 100%;
                margin-top: 20px;
            }
        }
    </style>
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
                    <a href="index.jsp" class="mil-logo">
                        <img src="img/logo1.png" alt="Plax" width="200">
                    </a>
                    <nav class="mil-top-menu">
                        <ul>
                            <li class="mil-has-children">
                                <a href="#.">Home</a>
                                <ul>
                                    <li><a href="index.jsp">Type 1</a></li>
                                    <li><a href="home-2.jsp">Type 2</a></li>
                                    <li><a href="home-3.jsp">Type 3</a></li>
                                    <li><a href="home-4.jsp">Type 4</a></li>
                                    <li><a href="home-5.jsp">Type 5</a></li>

                                </ul>
                            </li>
                            <li>
                                <a href="about">About</a>
                            </li>
                            <li >
                                <a href="faq.jsp">FAQ</a>
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
                        <a href="register.jsp" class="mil-btn mil-sm">Log in</a>
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

                                    <h2 >Dịch vụ TIMIBANK mang lại </h2>
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
                <div class="container11">
                    <div class="card-types">
                        <button class="active">
                            Tất cả
                        </button>
                        <button>
                            Thẻ đa năng Hybrid
                        </button>
                        <button>
                            Thẻ tín dụng
                        </button>
                        <button>
                            Thẻ ghi nợ
                        </button>
                        <button>
                            Thông tin &amp; trả góp
                        </button>
                        <button>
                            Apple Pay
                        </button>
                    </div>
                    <div class="content11">
                        <div class="cards">
                            <div class="card">
                                <img alt="MB Hi LoL card with a character from Liên Minh Huyền Thoại" height="200" src="https://placehold.co/300x200" width="300"/>
                                <h3>
                                    Thẻ MB Hi LoL
                                </h3>
                                <p>
                                    Sự hợp tác giữa ngân hàng MB và Mastercard đã cho ra sản phẩm MB Hi Collection LoL - giải pháp thanh toán kỹ thuật số đa năng dành cho
                                    <strong>
                                        người đam mê eSports
                                    </strong>
                                    nói chung và người hâm mộ
                                    <strong>
                                        Liên Minh Huyền Thoại
                                    </strong>
                                    nói riêng.
                                </p>
                                <a href="#">
                                    Mua Thẻ Ngay
                                </a>
                                <a href="#">
                                    Xem Thêm +
                                </a>
                            </div>
                            <div class="card">
                                <img alt="MB Mastercard Hi Green card with eco-friendly design" height="200" src="https://placehold.co/300x200" width="300"/>
                                <h3>
                                    Thẻ MB Mastercard Hi Green
                                </h3>
                                <p>
                                    MB Mastercard Hi Green là thẻ ngân hàng xanh đầu tiên tại Việt Nam, nhằm lan tỏa những giá trị về phát triển bền vững. Từng điểm chạm của khách hàng trên hành trình sử dụng thẻ đều sẽ góp phần vào việc triển khai các dự án mới.
                                </p>
                                <a href="#">
                                    Mua Thẻ Ngay
                                </a>
                                <a href="#">
                                    Xem Thêm +
                                </a>
                            </div>
                            <div class="card">
                                <img alt="MB Mastercard Hi Ads card with a modern design" height="200" src="https://placehold.co/300x200" width="300"/>
                                <h3>
                                    Thẻ MB Mastercard Hi Ads
                                </h3>
                                <p>
                                    MB Mastercard Hi Ads Dòng thẻ đặc quyền dành cho nhà chạy quảng cáo.
                                </p>
                                <ul>
                                    <li>
                                        Phí chuyển đổi ngoại tệ cạnh tranh nhất thị trường - 1.5%
                                    </li>
                                </ul>
                                <a href="#">
                                    Mua Thẻ Ngay
                                </a>
                                <a href="#">
                                    Xem Thêm +
                                </a>
                            </div>
                        </div>
                        <div class="sidebar">
                            <h3>
                                Hướng dẫn đăng ký
                            </h3>
                            <p>
                                Liên hệ với MB để có thêm thông tin về sản phẩm và sở hữu thẻ sớm nhất:
                            </p>
                            <p>
                                <strong>
                                    1900 545426 - (84-24) 3767 4050
                                </strong>
                                (quốc tế gọi về)
                            </p>
                            <a href="register.jsp">
                                <i class="fas fa-edit">
                                </i>
                                Đăng ký
                            </a>
                            <a class="secondary" href="#">
                                <i class="fas fa-file-alt">
                                </i>
                                Biểu phí
                            </a>
                            <a class="secondary" href="#">
                                <i class="fas fa-question-circle">
                                </i>
                                Q&amp;A
                            </a>
                        </div>
 </div>
                        <!-- footer -->
                        <footer class="mil-p-160-0 footer" id="footer">
                            <div class="container">
                                <div class="row mil-footer-top">
                                    <div class="col-xl-2">
                                        <a href="#." class=" mil-footer-logo mil-mb-60">
                                            <img src="img/logo1.png" alt="Plax" width="150">
                                        </a>
                                    </div>
                                    <div class="col-xl-3 mil-mb-60">
                                        <h6 class="mil-mb-60">Liên kết hữu ích</h6>
                                        <ul class="mil-footer-list">
                                            <li class="mil-text-m mil-soft mil-mb-15">
                                                <a href="index.jsp">Home</a>
                                            </li>
                                            <li class="mil-text-m mil-soft mil-mb-15">
                                                <a href="about.jsp">About Us</a>
                                            </li>
                                            <li class="mil-text-m mil-soft mil-mb-15">
                                                <a href="contact.jsp">Contact Us</a>
                                            </li>
                                            <li class="mil-text-m mil-soft mil-mb-15">
                                                <a href="faq.jsp">FAQs</a>
                                            </li>
                                            <li class="mil-text-m mil-soft mil-mb-15">
                                                <a href="price.jsp">Pricing</a>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="col-xl-3 mil-mb-60">
                                        <h6 class="mil-mb-60">Hỗ trợ</h6>
                                        <ul class="mil-footer-list">
                                            <li class="mil-text-m mil-soft mil-mb-15" >
                                                <a href="mailto:nguyenquangthoai04@gmail.com" class="modal__link">Email: nguyenquangthoai04@gmail.com</a> 
                                            </li>
                                            <li class="mil-text-m mil-soft mil-mb-15">
                                                <a href="tel:0967368980" class="modal__link">Liên hệ: 0967368980</a>                                        </li>

                                        </ul>
                                    </div>
                                    <div class="col-xl-4 mil-mb-80">
                                        <h6 class="mil-mb-60">Gửi Hỗ trợ</h6>
                                        <p class="mil-text-xs mil-soft mil-mb-15">Hãy miêu tả những thứ bạn cần hỗ trợ </p>
                                        <form action="showfb" method="post" class="mil-subscripe-form-footer">
                                            <!-- Email Input -->
                                            <div class="mb-3">
                                                <input class="form-control form-control__input mil-input" type="email" placeholder="Email" name="email" required>
                                            </div>
                                            <!-- Tiêu đề Input -->
                                            <div class="mb-3">
                                                <input  class="form-control form-control__input mil-input" type="text" placeholder="Tiêu đề" name="tieude" required>
                                            </div>
                                            <!-- Nội dung Input -->
                                            <div class="mb-3">
                                                <textarea class="form-control form-control__textarea" rows="3" placeholder="Nội dung" name="noidung" required></textarea>                                    
                                            </div>

                                            <div class="form-control__row--reverse">
                                                <!-- Submit Button -->
                                                <button type="submit" class="form-control__btn form-control__btn--green mil-btn mil-ssm">
                                                    <i class="far fa-envelope-open form-control__icon"></i> Gửi
                                                </button>
                                                <!-- Checkbox -->
                                                <div class="mil-checkbox-frame">
                                                    <div class="mil-checkbox">
                                                        <input type="checkbox" id="checkbox" checked>
                                                        <label for="checkbox"></label>
                                                    </div>
                                                    <p class="mil-text-xs mil-soft">Đăng ký để nhận tin tức mới nhất</p>

                                                </div>
                                            </div>

                                        </form>
                                    </div>

                                </div>
                                <div class="mil-footer-bottom">
                                    <div class="row">
                                        <div class="col-xl-6">
                                            <p class="mil-text-s mil-soft">© 2025 TIMI Finance & Fintech Design</p>
                                        </div>
                                        <div class="col-xl-6">
                                            <button type="button" class=" mil-btn mil-ssm footer--toggle--btn" onclick="toggleFooter()">
                                                <span class="text-expand">Thu gọn chân trang</span>
                                                <span class="text-collapsed" style="display: none">Mở rộng chân trang</span>
                                                <span class="icon-arrow-right">

                                                </span>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </footer>

                        <!-- footer end -->

                   
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
