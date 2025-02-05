<%-- 
    Document   : faq
    Created on : Feb 3, 2025, 9:34:56 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    <body>
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
                            <li>
                                <a href="about">About</a>
                            </li>
                            <li class="mil-active">
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
                <!-- Menu end -->

                <div class="mil-features faq-wrap">
                    <div class="container">
                        <div class="row row-cols-2 g-5">
                            <div class="col">
                                <div class="faq-card">
                                    <img src="https://placehold.co/50x50" class="faq-card__img" alt="Digital Banking Icon">
                                    <h3 class="faq-card__heading">Ngân hàng số</h3>
                                    <p class="faq-card__desc">Kích hoạt, cài đặt và đổi mật khẩu VCB Digibank, tra cứu kết quả giao dịch, v.v.</p>
                                    <a href="#!" class="faq-card__btn">Xem chi tiết</a>
                                </div>
                            </div>
                            <div class="col">
                                <div class="faq-card">
                                    <img src="https://placehold.co/50x50" class="faq-card__img" alt="Digital Banking Icon">
                                    <h3 class="faq-card__heading">Ngân hàng số</h3>
                                    <p class="faq-card__desc">Kích hoạt, cài đặt và đổi mật khẩu VCB Digibank, tra cứu kết quả giao dịch, v.v.</p>
                                    <a href="#!" class="faq-card__btn">Xem chi tiết</a>
                                </div>
                            </div>
                            <div class="col">
                                <div class="faq-card">
                                    <img src="https://placehold.co/50x50" class="faq-card__img" alt="Digital Banking Icon">
                                    <h3 class="faq-card__heading">Ngân hàng số</h3>
                                    <p class="faq-card__desc">Kích hoạt, cài đặt và đổi mật khẩu VCB Digibank, tra cứu kết quả giao dịch, v.v.</p>
                                    <a href="#!" class="faq-card__btn">Xem chi tiết</a>
                                </div>
                            </div>
                            <div class="col">
                                <div class="faq-card">
                                    <img src="https://placehold.co/50x50" class="faq-card__img" alt="Digital Banking Icon">
                                    <h3 class="faq-card__heading">Ngân hàng số</h3>
                                    <p class="faq-card__desc">Kích hoạt, cài đặt và đổi mật khẩu VCB Digibank, tra cứu kết quả giao dịch, v.v.</p>
                                    <a href="#!" class="faq-card__btn">Xem chi tiết</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>



                <div class="mil-features faq-wrap">
                    <div class="container">
                        <h2 class="faq-heading">Quan tâm nhiều nhất</h2>
                        <div class="faq-question js-tabs">
                            <ul class="faq-question__tab-list">
                                <li class="faq-question__tab faq-question__tab--current">
                                    Ngân hàng số
                                </li>
                                <li class="faq-question__tab">
                                    Thẻ
                                </li>
                                <li class="faq-question__tab">
                                    Vay
                                </li>
                            </ul>

                            <div class="faq-question__contents">
                                <div class="faq-question__content faq-question__content--current" >
                                    <ol class="faq-question__ques-list">
                                        <li class="faq-question__ques-item">
                                            <div class="faq-question__ques-wrap">
                                                <p class="faq-question__ques-title">Tôi là chủ Doanh nghiệp tư nhân, tôi có được vay đầu tư tài sản cố định theo sản phẩm này không?</p>
                                                <i class="fa-solid fa-angle-down faq-question__ques-icon"></i>
                                            </div>

                                            <div class="faq-question__ques-ans">
                                                Đối tượng khách hàng của Sản phẩm An tâm kinh doanh là cá nhân tự kinh doanh, chủ Hộ kinh doanh, chủ sở hữu Doanh nghiệp tư nhân. Khách hàng vui lòng liên hệ hotline 1900 54 54 13 hoặc điểm giao dịch Vietcombank gần nhất để được tư vấn thêm thông tin chi tiết thủ tục vay vốn thực hiện phương án đầu tư tài sản cố định, mở rộng sản xuất kinh doanh của mình.
                                            </div>
                                        </li>

                                        <li class="faq-question__ques-item">
                                            <div class="faq-question__ques-wrap">
                                                <p class="faq-question__ques-title">Số tiền tuyệt đối tối đa mà tôi có thể vay đầu tư để mở rộng sản xuất kinh doanh trung hạn tại Vietcombank?</p>
                                                <i class="fa-solid fa-angle-down faq-question__ques-icon"></i>
                                            </div>

                                            <div class="faq-question__ques-ans">
                                                Khách hàng có thể vay tối đa 70% phương án kinh doanh. Vietcombank xác định số tiền vay tối đa tuyệt đối trên cơ sở đánh giá nhiều tiêu chí khác nhau bao gồm:
                                                <ul>
                                                    <li>Phương án mở rộng hoạt động sản xuất kinh doanh mà bạn có nhu cầu vay vốn.</li>
                                                    <li>Hoạt động sản xuất kinh doanh hiện tại.</li>
                                                    <li>Hoạt động sản xuất kinh doanh hiện tại.</li>
                                                </ul>
                                            </div>
                                        </li>
                                    </ol>

                                </div>  

                                <div class="faq-question__content " >
                                    <ol class="faq-question__ques-list">
                                        <li class="faq-question__ques-item">
                                            <div class="faq-question__ques-wrap">
                                                <p class="faq-question__ques-title">Tôi là chủ Doanh nghiệp tư nhân, tôi có được vay đầu tư tài sản cố định theo sản phẩm này không?</p>
                                                <i class="fa-solid fa-angle-down faq-question__ques-icon"></i>
                                            </div>

                                            <div class="faq-question__ques-ans">
                                                Đối tượng khách hàng của Sản phẩm An tâm kinh doanh là cá nhân tự kinh doanh, chủ Hộ kinh doanh, chủ sở hữu Doanh nghiệp tư nhân. Khách hàng vui lòng liên hệ hotline 1900 54 54 13 hoặc điểm giao dịch Vietcombank gần nhất để được tư vấn thêm thông tin chi tiết thủ tục vay vốn thực hiện phương án đầu tư tài sản cố định, mở rộng sản xuất kinh doanh của mình.
                                            </div>
                                        </li>
                                    </ol>
                                </div>   

                                <div class="faq-question__content " >
                                    <ol class="faq-question__ques-list">
                                        <li class="faq-question__ques-item">
                                            <div class="faq-question__ques-wrap">
                                                <p class="faq-question__ques-title">Tôi là chủ Doanh nghiệp tư nhân, tôi có được vay đầu tư tài sản cố định theo sản phẩm này không?</p>
                                                <i class="fa-solid fa-angle-down faq-question__ques-icon"></i>
                                            </div>

                                            <div class="faq-question__ques-ans">
                                                Đối tượng khách hàng của Sản phẩm An tâm kinh doanh là cá nhân tự kinh doanh, chủ Hộ kinh doanh, chủ sở hữu Doanh nghiệp tư nhân. Khách hàng vui lòng liên hệ hotline 1900 54 54 13 hoặc điểm giao dịch Vietcombank gần nhất để được tư vấn thêm thông tin chi tiết thủ tục vay vốn thực hiện phương án đầu tư tài sản cố định, mở rộng sản xuất kinh doanh của mình.
                                            </div>
                                        </li>
                                    </ol>
                                </div> 


                            </div>
                        </div>
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
            </div>
        </div>

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
