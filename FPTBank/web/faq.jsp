<%-- 
    Document   : faq
    Created on : Feb 3, 2025, 9:34:56 PM
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
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet"/>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&amp;display=swap" rel="stylesheet"/>

        <!-- Favicon -->
        <link rel="shortcut icon" href="img/favicon.png" type="image/x-icon">
        <link rel="icon" href="img/favicon.png" type="image/x-icon">

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css"/>
    </head>

    <style>
        .filter-controls h3{
            text-align: center;
        }
        .search-container {
            position: relative;

            max-width:80%;
            display: flex;
            gap: 10px;
            margin-left: 10%;
        }

        .search-container i {
            position: absolute;
            left: 8px;
            top: 50%;
            transform: translateY(-50%);
            color: #666;
            font-size: 0.9em;
        }

        .search-input {
            padding: 6px 6px 6px 28px;
            border: 1px solid #ddd;
            border-radius: 6px;
            width: 100%;
            font-size: 0.9em;
        }
        .add-news-btn {
            padding: 10px 20px;
            border-radius: 8px;
            font-weight: 600;
            text-transform: uppercase;
            letter-spacing: 0.5px;
            cursor: pointer;
            transition: all 0.3s ease;
            border: none;
            font-size: 0.9em;
            margin: 10px 0;
            display: inline-block;
            text-align: center;
            text-decoration: none;
        }


    </style>
    <body>
        <div id="smooth-wrapper" class="mil-wrapper">

            <%@ include file="header.jsp"%>
            <!-- Menu -->
            <%@ include file="toolBar.jsp"%>
            <!-- Menu end -->

            <!-- top panel end -->

            <!-- content -->
            <div class="mil-features faq-wrap">
                <form action="faq"  class="filter-controls">
                    <!-- Search Box -->
                    <div class="search-container"  >
                        <i class="fa fa-search"></i>
                        <input   type="text" name="searchKeyword" value="${param.searchKeyword}" placeholder="Tìm câu hỏi nhanh ..." class="search-input">
                        <button class="add-news-btn" type="submit" >Search </button>
                    </div>
                    <div class="container faq-question__contents" >
                        <ol class="faq-question__ques-list">
                            <c:forEach items="${listFAQ}" var="faq">
                                <li class="faq-question__ques-item">
                                    <div class="faq-question__ques-wrap">
                                        <p class="faq-question__ques-title">${faq.getQuestion()}</p>
                                        <i class="fa-solid fa-angle-down faq-question__ques-icon"></i>
                                    </div>
                                    <div class="faq-question__ques-ans">
                                        ${faq.getAnswer()}
                                    </div>
                                </li>
                            </c:forEach>
                        </ol>
                    </div>

                </form>

                <div class="container">
                    <h2 class="faq-heading">Quan tâm nhiều nhất</h2>
                    <div class="faq-question js-tabs">
                        <ul class="faq-question__tab-list">
                            <c:forEach items="${faqTypes}" var="type">
                                <li class="faq-question__tab">
                                    <c:choose>
                                        <c:when test="${type == 'borrow'}">Vay</c:when>
                                        <c:when test="${type == 'savings'}">Gửi</c:when>
                                        <c:when test="${type == 'account'}">Tài khoản</c:when>
                                        <c:when test="${type == 'card'}">Thẻ</c:when>
                                        <c:otherwise>${type}</c:otherwise>
                                    </c:choose>
                                </li>
                            </c:forEach>
                        </ul>

                        <div class="faq-question__contents">
                            <c:forEach items="${faqData}" var="faqList">
                                <div class="faq-question__content">
                                    <ol class="faq-question__ques-list">
                                        <c:forEach items="${faqList}" var="faq">
                                            <li class="faq-question__ques-item">
                                                <div class="faq-question__ques-wrap">
                                                    <p class="faq-question__ques-title">${faq.getQuestion()}</p>
                                                    <i class="fa-solid fa-angle-down faq-question__ques-icon"></i>
                                                </div>
                                                <div class="faq-question__ques-ans">
                                                    ${faq.getAnswer()}
                                                </div>
                                            </li>
                                        </c:forEach>
                                    </ol>
                                </div>
                            </c:forEach>
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
    <script src="./js/scripts.js"></script>

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
