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

        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,900&display=swap" rel="stylesheet">
        <!-- Bootstrap CSS -->
        <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css'>
        <!-- Font Awesome CSS -->
        <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css'>
        <style>
            .student-profile .card {
                border-radius: 10px;
            }
            .student-profile .card .card-header .profile_img {
                width: 150px;
                height: 150px;
                object-fit: cover;
                margin: 10px auto;
                border: 10px solid #ccc;
                border-radius: 50%;
            }
            .student-profile .card .card-header{
                color : white;
            }
            .card-body{
                color : white;
            }
            .student-profile .card p {
                font-size: 16px;
                color: #000;
            }
            .student-profile .table th,
            .student-profile .table td {
                font-size: 14px;
                padding: 5px 10px;
                color: #000;
            }
            .cuong{
                margin-bottom: 150px;
            }
            .update-profile-btn {
                display: block;
                width: 80%;
                margin: 15px auto;
                padding: 10px 20px;
                font-size: 16px;
                font-weight: bold;
                color: white;
                background: linear-gradient(to right, #4CAF50, #2E8B57); /* Xanh lá gradient */
                border: none;
                border-radius: 5px;
                cursor: pointer;
                transition: all 0.3s ease-in-out;
                text-align: center;
            }

            .update-profile-btn:hover {
                background: linear-gradient(to right, #2E8B57, #4CAF50); /* Đảo gradient khi hover */
                transform: scale(1.05);
            }
            .student-profile {
                background: linear-gradient(to right, #2c3e50, #4ca2cd, #67B26F); /* Xanh galaxy */
                padding: 40px 0;
                border-radius: 10px;
                box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.2);
                padding: 100px;
            }
            .student-profile .card {
                background: rgba(255, 255, 255, 0.2); /* Làm trong suốt nhẹ */
                backdrop-filter: blur(10px);
                border-radius: 10px;
                border: none;
            }
            
        </style>
    </head>

    <body>


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
                            <a href="/timibank/login" class="mil-btn mil-sm">Log in</a>
                            <a href="/timibank/register" class="mil-btn mil-sm" style="margin-left: 10px">Register</a>
                        </c:if>
                        <c:if test="${sessionScope.account != null}">
                            <nav class="mil-top-menu">
                                <ul>
                                    <li class="mil-has-children ">
                                        <a href="#." class="mil-btn mil-sm">My Account</a>
                                        <ul style="list-style-type: none">
                                            <li><a href="/timibank/profile.jsp">My Profile</a></li>
                                            <li><a href="/timibank/change-password">Change Password</a></li>
                                            <li><a href="/timibank/purchase">My Purchase</a></li>
                                            <li><a href="/timibank/logout">Log out</a></li>
                                        </ul>
                                    </li>   
                                </ul>
                            </nav>
                        </c:if>
                    </div>
                </div>
            </div>
            <!-- top panel end -->
            <div class="cuong"></div>
            <div class="student-profile py-4">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-4">
                            <div class="card shadow-sm">
                                <div class="card-header bg-transparent text-center">
                                    <img class="profile_img" src="${sessionScope.account.image}">
                                    <h3>${sessionScope.account.fullName}</h3>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-8">
                            <div class="card shadow-sm">
                                <div class="card-header bg-transparent border-0">
                                    <h3 class="mb-0"><i class="far fa-clone pr-1"></i>General Information</h3>
                                </div>
                                <div class="card-body pt-0">
                                    <table class="table table-bordered">
                                        <c:if test="${sessionScope.account.roleID == 1}">
                                            <tr>
                                                <th width="30%">Roll</th>
                                                <td width="2%">:</td>
                                                <td>Admin</td>
                                            </tr>
                                        </c:if>
                                        <c:if test="${sessionScope.account.roleID == 2}">
                                            <tr>
                                                <th width="30%">Roll</th>
                                                <td width="2%">:</td>
                                                <td>Seller</td>
                                            </tr>
                                        </c:if>
                                        <c:if test="${sessionScope.account.roleID == 3}">
                                            <tr>
                                                <th width="30%">Roll</th>
                                                <td width="2%">:</td>
                                                <td>Manager</td>
                                            </tr>
                                        </c:if>
                                        <c:if test="${sessionScope.account.roleID == 4}">
                                            <tr>
                                                <th width="30%">Roll</th>
                                                <td width="2%">:</td>
                                                <td>Insurance Provider</td>
                                            </tr>
                                        </c:if>
                                        <c:if test="${sessionScope.account.roleID == 5}">
                                            <tr>
                                                <th width="30%">Roll</th>
                                                <td width="2%">:</td>
                                                <td>Customer</td>
                                            </tr>
                                        </c:if>
                                        <tr>
                                            <th width="30%">Date Of Birth</th>
                                            <td width="2%">:</td>
                                            <td>${sessionScope.account.dateOfBirth}</td>
                                        </tr>
                                        <tr>
                                            <th width="30%">Gender</th>
                                            <td width="2%">:</td>
                                            <td>${sessionScope.account.gender == true ? "Male" : "Female"}</td>
                                        </tr>
                                        <tr>
                                            <th width="30%">CCCD</th>
                                            <td width="2%">:</td>
                                            <td>${sessionScope.account.CCCD}</td>
                                        </tr>
                                        <tr>
                                            <th width="30%">Phone</th>
                                            <td width="2%">:</td>
                                            <td>${sessionScope.account.phone}</td>
                                        </tr>
                                        <tr>
                                            <th width="30%">Email</th>
                                            <td width="2%">:</td>
                                            <td>${sessionScope.account.email}</td>
                                        </tr>
                                        <tr>
                                            <th width="30%">Address</th>
                                            <td width="2%">:</td>
                                            <td>${sessionScope.account.address}</td>
                                        </tr>
                                        <tr>
                                            <th width="30%">Created At</th>
                                            <td width="2%">:</td>
                                            <td>${sessionScope.account.createdAt}</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                            <div style="height: 26px"></div>
                            <a href="updateprofile.jsp"><button class="update-profile-btn">Update Profile</button></a>
                        </div>
                    </div>
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
                                <p class="mil-text-s mil-soft">© 2024 Plax Finance & Fintech Design</p>
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

</body>
</html>
