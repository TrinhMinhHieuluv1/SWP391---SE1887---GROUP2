<%-- 
    Document   : home.jsp
    Created on : Jan 16, 2025, 12:34:43 PM
    Author     : HP
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

        <!-- Toarst -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>


        <style>
            /* Chặn thanh kéo ngang */
            body {
                overflow-x: hidden;
            }

            /* Notification styles */
            .notification {
                position: fixed;
                top: -100px;
                left: 50%;
                transform: translateX(-50%);
                background: linear-gradient(135deg, #ff6b6b 0%, #dc3545 100%);
                padding: 20px 30px;
                border-radius: 15px;
                box-shadow: 0 10px 30px rgba(220, 53, 69, 0.3),
                    0 0 0 1px rgba(220, 53, 69, 0.2);
                display: flex;
                align-items: center;
                gap: 15px;
                z-index: 10000;
                transition: all 0.5s cubic-bezier(0.68, -0.55, 0.265, 1.55);
                width: auto;
                max-width: 90vw; /* Giới hạn để không vượt quá màn hình */
                border: 1px solid rgba(255, 255, 255, 0.2);
                backdrop-filter: blur(10px);
                box-sizing: border-box;
            }

            /* Khi hiển thị */
            .notification.show {
                top: 30px;
                animation: shake 0.8s cubic-bezier(.36,.07,.19,.97) both;
            }

            /* Keyframes cho shake */
            @keyframes shake {
                10%, 90% {
                    transform: translateX(calc(-50% + 1px));
                }
                20%, 80% {
                    transform: translateX(calc(-50% - 2px));
                }
                30%, 50%, 70% {
                    transform: translateX(calc(-50% + 4px));
                }
                40%, 60% {
                    transform: translateX(calc(-50% - 4px));
                }
            }

            /* Icon */
            .notification-icon {
                width: 45px;
                height: 45px;
                background: rgba(255, 255, 255, 0.95);
                border-radius: 50%;
                display: flex;
                align-items: center;
                justify-content: center;
                flex-shrink: 0;
                animation: pulse 2s infinite;
            }

            @keyframes pulse {
                0% {
                    transform: scale(1);
                }
                50% {
                    transform: scale(1.05);
                }
                100% {
                    transform: scale(1);
                }
            }

            .notification-icon i {
                color: #dc3545;
                font-size: 1.4em;
            }

            .notification-content {
                flex-grow: 1;
                max-width: calc(100% - 80px); /* Giới hạn để tránh tràn */
                overflow: hidden;
            }

            .notification-title {
                color: white;
                font-weight: 600;
                margin: 0 0 5px 0;
                font-size: 1.1em;
                text-shadow: 0 1px 2px rgba(0, 0, 0, 0.1);
                white-space: nowrap; /* Ngăn xuống dòng */
                overflow: hidden;
                text-overflow: ellipsis;
            }

            .notification-message {
                color: rgba(255, 255, 255, 0.95);
                margin: 0;
                font-size: 0.95em;
                line-height: 1.4;
                text-shadow: 0 1px 2px rgba(0, 0, 0, 0.1);
                white-space: nowrap; /* Ngăn xuống dòng */
                overflow: hidden;
                text-overflow: ellipsis;
            }

            /* Nút đóng */
            .notification-close {
                color: rgba(255, 255, 255, 0.8);
                background: rgba(255, 255, 255, 0.1);
                border: none;
                font-size: 1.2em;
                cursor: pointer;
                padding: 8px;
                border-radius: 50%;
                width: 30px;
                height: 30px;
                display: flex;
                align-items: center;
                justify-content: center;
                transition: all 0.3s ease;
                margin-left: 5px;
            }

            .notification-close:hover {
                color: white;
                background: rgba(255, 255, 255, 0.2);
                transform: rotate(90deg);
            }

            /* Responsive */
            @media (max-width: 480px) {
                .notification {
                    width: 90%;
                    min-width: auto;
                    max-width: 90vw;
                    padding: 15px;
                }
            }
            .content {
                margin-top: 150px ;
                padding: 20px;
            }
            h2 {
                color: #4CAF50; /* Màu xanh lá cây cho tiêu đề */
            }

            table {
                width: 70%;
                border-collapse: collapse;
                margin-top: 20px;
                background-color: #ffffff;
                box-shadow: 0 2px 4px rgba(0,0,0,0.1);
                margin: 0 auto;
            }

            th, td {
                padding: 10px;
                text-align: left;
                font-size: 16px;
            }

            th {
                background-color: #4CAF50; /* Màu xanh lá cây cho tiêu đề bảng */
                color: white;
            }

            tr:nth-child(even) {
                background-color: #f9f9f9; /* Màu nền của dòng chẵn */
            }

            tr:hover {
                background-color: #f1f1f1; /* Đổi màu nền khi hover trên dòng */
            }

            td {
                color: #333; /* Màu chữ của các ô */
            }
            .pagination {
                display: flex;
                justify-content: center;
                align-items: center;
                margin: auto;
            }

            .pagination a {
                padding: 9px 10px;
                margin: 0 5px;
                background-color: #f4f4f4;

                color: #333;
                text-decoration: none;
                border-radius: 25px;
                font-weight: bold;
                transition: all 0.3s ease-in-out;
            }

            .pagination a:hover {
                background-color: yellowgreen;
            }

            .pagination a.active {
                background-color: green;
                color: #fff;
                border-radius: 30px;
            }
            select {
                margin-top: 15px;
                background-color: #0d6efd;
                color: white ;
                padding: 3px 8px; /* Giảm padding để ô nhỏ hơn */
                border-radius: 6px; /* Bo tròn góc nhẹ */
                font-size: 14px; /* Giảm kích thước chữ */
                cursor: pointer;
            }


            /* Khi focus vào ô chọn */
            select:focus {
                outline: none;
                box-shadow: 0 0 5px #007bff;
            }
            .control-form{
                width: 50px;
            }
            .filter-group {
                background: white;
                padding: 15px;
                border-radius: 10px;
                box-shadow: 0 2px 8px rgba(0,0,0,0.1);
                margin-bottom: 20px;
                margin: 0 auto;
                width: 70%;
            }

            .filter-group label {
                font-weight: 500;
                color: #333;
                margin-right: 15px;
            }

            .filter-group select {
                padding: 8px 12px;
                border: 1px solid #ddd;
                border-radius: 6px;
                background-color: white;
                color: #333;
                font-size: 0.95em;
            }
        </style>

    </head>

    <body>

        <!-- Error Notification -->
        <div id="notification" class="notification ${param.RoleErr eq 'true' ? 'show' : ''}">
            <div class="notification-icon">
                <i class="fa fa-exclamation-circle"></i>
            </div>
            <div class="notification-content">
                <h4 class="notification-title">Access Denied</h4>
                <p class="notification-message">You don't have permission to access this feature.</p>
            </div>
            <button class="notification-close" onclick="closeNotification()">&times;</button>
        </div>

        <script>
            function closeNotification() {
                const notification = document.getElementById('notification');
                notification.classList.remove('show');
                setTimeout(() => {
                    notification.style.display = 'none';
                }, 500);
            }

            // Auto close after 5 seconds
            if (document.getElementById('notification').classList.contains('show')) {
                setTimeout(closeNotification, 5000);
            }
        </script>

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
                            <li class="mil-has-children mil-active">
                                <a href="#.">Home</a>
                                <ul>

                                    <li><a href="/timibank/home">Trang trủ</a></li>
                                    <li><a href="home-2.html">Gửi tiết kiệm</a></li>
                                    <li><a href="home-3.html">Type 3</a></li>
                                    <li><a href="home-4.html">Type 4</a></li>
                                    <li><a href="home-5.html">Type 5</a></li>
                                </ul>
                            </li>
                            <li>
                                <a href="about">About</a>
                            </li>
                            <li>
                                <a href="faq.jsp">FAQ</a>
                            </li>
                            <li class="mil-has-children">
                                <a href="#.">Blog</a>
                                <ul>
                                    <li><a href="blog.jsp">Blog list</a></li>
                                    <li><a href="publication.jsp">Blog details</a></li>
                                </ul>
                            </li>
                            <c:if test="${sessionScope.account != null}">
                                <li>
                                    <a href="contact.jsp">Feedback</a>
                                </li>
                            </c:if>
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
                        <c:if test="${sessionScope.account == null}">
                            <div class="mil-menu-buttons">
                                <a href="login" class="mil-btn mil-sm">Log in</a>
                                <a href="register" class="mil-btn mil-sm" style="margin-left: 10px">Register</a>
                            </div>
                        </c:if>
                        <c:if test="${sessionScope.account != null}">

                            <nav class="mil-top-menu">
                                <ul>
                                    <li class="mil-has-children ">
                                        <a href="#." class="mil-btn mil-sm">My Account</a>
                                        <ul>
                                            <li><a href="profile.jsp">My Profile</a></li>
                                            <li><a href="/timibank/change-password">Change Password</a></li>
                                                <c:if test="${sessionScope.account.getRoleID() == 5}">
                                                <li><a href="/timibank/invoiceshowcustomer">My Bill</a></li>
                                                </c:if>
                                                <c:if test="${sessionScope.account.getRoleID() == 5}">
                                                <li><a href="/timibank/myfeedback">My Feedback</a></li>
                                                </c:if>
                                                <c:if test="${sessionScope.account.getRoleID() == 5}">
                                                <li><a href="/timibank/myassetsalary">My Asset and Salary</a></li>
                                                </c:if>
                                            <li><a href="/timibank/logout">Sign out</a></li>
                                        </ul>
                                    </li>   
                                </ul>


                            </nav>  


                        </c:if>

                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- top panel end -->
    <div class="content">
        <h2 style="text-align:  center;">List of Bill</h2>

        <form action="invoiceshowcustomer" method="get">

            <div class="filter-group" style="display: flex;">
                <label for="number" style="margin-left: 30px;" >Number in Page:</label>
                <select class="form-control" id="statusFilter" name="pagesize">
                    <c:forEach var="num" items="${requestScope.listint}">
                        <option value="${num}" ${param.pagesize == num ? 'selected' : '' }>${num}</option>
                    </c:forEach>
                </select>
                <label for="status" style="margin-left: 30px;">Status of Bill:</label>
                <select class="form-control" id="statusFilter" name="statusbill">
                    <option value="" ${param.statusbill == '' ? 'selected' :''}>-- Select Status --</option>
                    <option value="true" ${param.statusbill == 'true' ? 'selected' : ''}>Paid</option>
                    <option value="false" ${param.statusbill == 'false' ? 'selected' : ''}>Unpaid</option>
                </select>
                <label for="status" style="margin-left: 30px;">From:</label>
                <input class="form-control" type="date" name="date1" placeholder="Date"></input>
                <label for="status" style="margin-left: 30px;">To:</label>
                <input class="form-control" type="date" name="date2" placeholder="Date"></input>
            </div>
            <button type="submit" style="background-color: green; color: white; margin-left: 15%;">Filter</button>
            <table>
                <thead>
                    <tr>
                        <th>No.Number</th>
                        <th>CreatedAt</th>
                        <th>Customer</th>
                        <th>Total</th>
                        <th>Status Of Bill</th> 
                        <th colspan="2">Action</th>
                    </tr>
                </thead>
                <c:forEach var="bill" items="${requestScope.listB}">

                    <tbody>
                        <c:set var="count" value="${count + 1}" />
                        <tr>
                            <td>${count}</td>
                            <td>${bill.getCreatedAt()}</td>
                            <td>${bill.getCustomer().getFullName()}</td>
                            <td>${bill.getTotal()}</td>
                            <td>${bill.getStatusOfBill() == 1 ? "Unpaid" : "Paid"}</td>  

                            <td><a href="detailbillcustomer?billID=${bill.getBillID()}&providerID=${bill.getProvider().getUserID()}" 
                                   style="display: inline-block; background-color: green; color: white;
                                   border-radius: 5px; padding: 5px 10px; text-decoration: none;">
                                    Detail
                                </a></td>
                                <c:if test="${bill.getStatusOfBill() == 1}">
                                <td><a href="payment?billID=${bill.getBillID()}&providerID=${bill.getProvider().getUserID()}&total=${bill.getTotal()}" 
                                       style="display: inline-block; background-color: green; color: white;
                                       border-radius: 5px; padding: 5px 10px; text-decoration: none;">
                                        Paid
                                    </a></td>
                                </c:if>
                        </tr>
                    </tbody>

                </c:forEach>
            </table>
        </form>


        <!-- Pagination Controls -->
        <div class="pagination">
            <c:if test="${currentPage > 1}">
                <a href="invoiceshowcustomer?page=${currentPage - 1}&statusresponse=${param.statusbill}&pagesize=${param.pagesize}&date1=${param.date1}&date2=${param.date2}" 
                   class="prev">Previous</a>
            </c:if>

            <c:forEach var="i" begin="1" end="${totalPages}">
                <a href="invoiceshowcustomer?page=${i}&statusresponse=${param.statusbill}&pagesize=${param.pagesize}&date1=${param.date1}&date2=${param.date2}" class="${i == currentPage ? 'active' : ''}">${i}</a>
            </c:forEach>

            <c:if test="${currentPage < totalPages}">
                <a href="invoiceshowcustomer?page=${currentPage + 1}&statusresponse=${param.statusbill}&pagesize=${param.pagesize}&date1=${param.date1}&date2=${param.date2}" class="next">Next</a>
            </c:if>
        </div>

    </div>

    <!-- footer -->
    <footer class="mil-footer-with-bg mil-p-160-0">
        <div class="container">
            <div class="row">
                <div class="col-xl-3">
                    <a href="#." class="mil-footer-logo mil-mb-60">
                        <img src="img/logo-2.png" alt="Plax" width="28" height="32">
                    </a>
                </div>
                <div class="col-xl-3 mil-mb-60">
                    <h6 class="mil-mb-60">Usefull Links</h6>
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
                            <a href="services.jsp">Services</a>
                        </li>
                        <li class="mil-text-m mil-soft mil-mb-15">
                            <a href="price.jsp">Pricing</a>
                        </li>
                    </ul>
                </div>
                <div class="col-xl-3 mil-mb-60">
                    <h6 class="mil-mb-60">Help</h6>
                    <ul class="mil-footer-list">
                        <li class="mil-text-m mil-soft mil-mb-15">
                            999 Rue du Cherche-Midi, 7755500666 Paris, <br>France
                        </li>
                        <li class="mil-text-m mil-soft mil-mb-15">
                            +001 (808) 555-0111
                        </li>
                        <li class="mil-text-m mil-soft mil-mb-15">
                            support@plax.network
                        </li>
                    </ul>
                </div>
                <div class="col-xl-3 mil-mb-80">
                    <h6 class="mil-mb-60">Newsletter</h6>
                    <p class="mil-text-xs mil-soft mil-mb-15">Subscribe to get the latest news form us</p>
                    <form class="mil-subscripe-form-footer">
                        <input class="mil-input" type="email" placeholder="Email">
                        <button type="submit"><i class="far fa-envelope-open mil-dark"></i></button>
                        <div class="mil-checkbox-frame mil-mt-15">
                            <div class="mil-checkbox">
                                <input type="checkbox" id="checkbox" checked>
                                <label for="checkbox"></label>
                            </div>
                            <p class="mil-text-xs mil-soft">Subscribe to get the latest news</p>
                        </div>
                    </form>
                </div>
            </div>
            <div class="mil-footer-bottom">
                <div class="row">
                    <div class="col-xl-6">
                        <p class="mil-text-s mil-soft"> 2024 Plax Finance & Fintech Design</p>
                    </div>
                    <div class="col-xl-6">
                        <p class="mil-text-s mil-text-right mil-sm-text-left mil-soft">Developed by <a href="https://bslthemes.com" target="blank">bslthemes</a></p>
                    </div>
                </div>
            </div>
        </div>
    </footer>
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
<%@ include file="admin/Common/toarst.jsp" %>

</body>
</html>
