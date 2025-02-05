
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
            font-family: 'Roboto', sans-serif;
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
         .result {
            margin-top: 20px;
            padding: 10px;
            border: 1px solid #ddd;
            display: none;
           
        }
        .result2 {
            display: flex;
            justify-content: center;
            margin-bottom: 20px;
        }
        .header {
            background-color: #f0f4ff;
            text-align: center;
            padding: 20px;
        }
        .header img {
            max-width: 100%;
            height: auto;
        }
        .header h1 {
            color: #3a3a3a;
            font-size: 24px;
            margin: 0;
        }
        .header p {
            color: #3a3a3a;
            font-size: 16px;
            margin: 5px 0 0;
        }
        .banner {
            background-color: #e0e7ff;
            text-align: center;
            padding: 40px 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        .banner h2 {
            color: #3a3a3a;
            font-size: 36px;
            margin: 0;
        }
        .banner p {
            color: #3a3a3a;
            font-size: 18px;
            margin: 10px 0 0;
        }
        .banner img {

            max-width: 100%;
            height: auto;
            margin-top: 20px;
        }
        .search-bar {
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
            background-color: #fff;
        }
        .search-bar select, .search-bar button {
            padding: 10px;
            font-size: 16px;
            margin: 0 5px;
        }
        .search-bar button {
            background-color: #0056b3;
            color: #fff;
            border: none;
            cursor: pointer;
        }
        .products {
            text-align: center;
            padding: 40px 20px;
            background-color: #f0f4ff;
        }
        .products h2 {
            color: #3a3a3a;
            font-size: 24px;
            margin: 0 0 20px;
        }
        .products button {
            padding: 10px 20px;
            font-size: 16px;
            background-color: #0056b3;
            color: #fff;
            border: none;
            cursor: pointer;
            margin-bottom: 20px;
        }
        .product-list {
            display: flex;
            justify-content: center;
            flex-wrap: wrap;
        }
        .product-item {
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 8px;
            margin: 10px;
            padding: 20px;
            width: 250px;
            text-align: left;
        }
        .product-item img {
            max-width: 100%;
            height: auto;
        }
        .product-item h3 {
            color: #3a3a3a;
            font-size: 18px;
            margin: 10px 0;
        }
        .product-item p {
            color: #3a3a3a;
            font-size: 14px;
            margin: 10px 0;
        }
        .product-item a {
            color: #0056b3;
            text-decoration: none;
            font-size: 14px;
        }
        .pagination {
            display: flex;
            justify-content: center;
            margin-top: 20px;
        }
        .pagination button {
            padding: 10px;
            font-size: 16px;
            background-color: #0056b3;
            color: #fff;
            border: none;
            cursor: pointer;
            margin: 0 5px;
        }
        @media (max-width: 768px) {
            .product-list {
                flex-direction: column;
                align-items: center;
            }
            .product-item {
                width: 90%;
            }
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

                <div class="banner">
                    <h2>
                        5 CÁCH CẬP NHẬT CƯỚC GẮN CHIP TRÊN APP MBBANK
                    </h2>
                    <p>
                        TÌM HIỂU NGAY
                    </p>
                    <img alt="App MB Bank Banner" height="200" src="https://storage.googleapis.com/a1aa/image/BMCZLwjbjz_VvXIhSja2UzI3LERXuFQkJo1tg6Xk81Y.jpg" width="600"/>
                </div>
                <div class="search-bar">
                
    <form id="serviceForm">
        <select id="serviceSelect">
            <option value="">Tôi muốn ...</option>
            <option value="Đăng kí/Dịch vụ thẻ/App Timibank">Đăng kí/Dịch vụ thẻ/App Timibank</option>
            <option value="Vay tiền">Vay tiền</option>
            <option value="Gửi tiền tiết kiệm">Gửi tiền tiết kiệm</option>
            <option value="Mua bảo hiểm">Mua bảo hiểm</option>
        </select>
        <button type="button" onclick="showInfo()">CHỌN</button>
    </form>

   
                    
                </div>
                <div class="result2"><div class="result" id="resultBox"></div></div>   
                <div class="products">
                    <h2>
                        Sản phẩm Khách hàng Cá nhân nổi bật
                    </h2>
                    <button>'
                        <a href="allprefer.jsp">XEM TOÀN BỘ SẢN PHẨM</a>
                        
                    </button>
                    <div class="product-list">
                        <div class="product-item">
                            <img alt="App MB Bank Icon" height="50" src="https://storage.googleapis.com/a1aa/image/hhVUJgV1Gd0qnh8_tBssYQHwu0YJdHE9oECjBIAfGqU.jpg" width="50"/>
                            <h3>
                                2. APP MBBANK
                            </h3>
                            <p>
                                Ứng dụng ngân hàng của MB trên điện thoại di động, thực hiện các giao dịch tài chính, thanh toán, theo dõi tài khoản dễ dàng, thuận tiện.
                            </p>
                            <button class="team-item__btn mil-btn mil-ssm js-toggle" toggle-target="#modal-1">Xem chi tiết </button>
                           
                            <div id="modal-container">
                                <div id="modal-1" class="modal modal--large hide">
                                    <div class="modal__content">
                                        <button class="modal__close js-toggle" toggle-target="#modal-1">&times;</button>
                                        daaaaaaaaaaaaaaaaaa

                                    </div>
                                    <div class="modal__overlay js-toggle" toggle-target="#modal-1"></div>


                                </div>

                            </div>

                        </div>
                        <div class="product-item">
                            <img alt="Remittance Service Icon" height="50" src="https://storage.googleapis.com/a1aa/image/w0fgVraIc-5YFqFXjP1ZySO0toeeX7kx9lj-Ri0qc2w.jpg" width="50"/>
                            <h3>
                                3. DỊCH VỤ KIỀU HỐI
                            </h3>
                            <p>
                                Chuyển tiền quốc tế, mua bán ngoại tệ, chuyển tiền từ nước ngoài về Việt Nam nhanh chóng, bảo mật, đa dạng ngoại tệ và dễ dàng trên App MBBank.
                            </p>
                            <a href="#">
                                XEM CHI TIẾT
                            </a>
                        </div>
                        <div class="product-item">
                            <img alt="Savings Icon" height="50" src="https://storage.googleapis.com/a1aa/image/yb92lAIijS366JRhHyQLhOocCqu4QtC--lyYpyQGmKs.jpg" width="50"/>
                            <h3>
                                4. TIỀN GỬI
                            </h3>
                            <p>
                                Với lãi suất rất hấp dẫn cùng kỳ hạn và hình thức đa dạng linh hoạt, các sản phẩm tiền gửi tiết kiệm mang lại nhiều lựa chọn phù hợp.
                            </p>
                            <a href="#">
                                XEM CHI TIẾT
                            </a>
                        </div>
                                 
                    </div>
                    <div class="pagi"> 
                                        <div class="container1" id="container-vision"></div>
                                        <div class="pagination" id="pagination-vision"></div>
                                    </div>
<!--                    <div class="pagination">
                                 <div class="navigation">
                                    <button disabled="" class="disabled navigation__prev"><i class="fa-solid fa-arrow-left-long navigation__icon"></i></button>
                                    <button class="navigation__next"><i class="fa-solid fa-arrow-right-long navigation__icon"></i></button>
                                </div>
                    </div>-->


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
