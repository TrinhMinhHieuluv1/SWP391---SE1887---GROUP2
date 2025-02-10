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
        <style>

            .small{
                color: white;
            }
            .cuong {
                margin-bottom: 150px;
            }
            body {
                margin-top: 20px;

            }
            .container-xl{
                background: linear-gradient(to right, #2c3e50, #4ca2cd, #67B26F);
                background: linear-gradient(to right, #2c3e50, #4ca2cd, #67B26F);
                margin-bottom: 50px;
            }
            .img-account-profile {
                height: 10rem;
                border: 3px solid #2e7d32; /* Viền ảnh profile xanh lá */
            }
            .rounded-circle {
                border-radius: 50% !important;
            }
            .card {
                box-shadow: 0 0.15rem 1.75rem 0 rgba(46, 125, 50, 0.15);
                padding: 20px 40px;
            }
            .card-body {
                margin: auto auto;
            }
            .card .card-header {
                font-weight: 500;
                background: linear-gradient(to right, #2c3e50, #4ca2cd, #67B26F);
                color: white; /* Chữ đậm */
            }
            .card-header:first-child {
                border-radius: 0.35rem 0.35rem 0 0;
            }
            .form-control, .dataTable-input {
                display: block;
                width: 100%;
                padding: 0.875rem 1.125rem;
                font-size: 0.875rem;
                font-weight: 400;
                line-height: 1;
                color: #2e7d32;
                background-color: #fff;
                background-clip: padding-box;
                border: 1px solid #66bb6a;
                border-radius: 0.35rem;
                transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
            }
            .form-control:focus {
                border-color: #1b5e20;
                box-shadow: 0 0 10px rgba(27, 94, 32, 0.25);
            }
            .nav-borders .nav-link.active {
                color: #2e7d32;
                border-bottom-color: #2e7d32;
            }
            .nav-borders .nav-link {
                color: #388e3c;
                border-bottom-width: 0.125rem;
                border-bottom-style: solid;
                border-bottom-color: transparent;
                padding-top: 0.5rem;
                padding-bottom: 0.5rem;
                margin-left: 1rem;
                margin-right: 1rem;
            }
            .btn-primary {
                background-color: #388e3c;
                border-color: #2e7d32;
            }
            .btn-primary:hover {
                background-color: #1b5e20;
                border-color: #1b5e20;
            }
            .progress-wrap {
                background: linear-gradient(to right, #66bb6a, #2e7d32);
            }
            .btn.btn-primary {
                background-color: #28a745; /* Màu xanh lá cây */
                border-color: #218838; /* Viền xanh đậm hơn */
                color: white; /* Chữ màu trắng */
                padding: 10px 20px;
                font-size: 16px;
                font-weight: bold;
                border-radius: 8px; /* Bo góc */
                transition: all 0.3s ease-in-out;
                margin-top: 20px;
            }

            .btn.btn-primary:hover {
                background-color: #218838; /* Màu tối hơn khi hover */
                border-color: #1e7e34;
                transform: scale(1.05); /* Hiệu ứng phóng to nhẹ */
            }

            .btn.btn-primary:active {
                background-color: #1e7e34; /* Màu tối hơn khi nhấn */
                border-color: #19692c;
                transform: scale(0.98); /* Hiệu ứng nhấn xuống */
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
            <div class="container-xl px-4 mt-4">
                <hr class="mt-0 mb-4">
                <div class="row">
                    <div class="col-xl-4">
                        <!-- Profile picture card-->
                        <div class="card mb-4 mb-xl-0">
                            <div class="card-header">Profile Picture</div>
                            <div class="card-body text-center">
                                <form action="updateimage" method="get">
                                    <!-- Profile picture image-->
                                    <img class="img-account-profile rounded-circle mb-2" src="${sessionScope.account.image}" alt="">
                                    <!-- Profile picture help block-->
                                    <div class="mb-3">           
                                        <input class="form-control" id="inputUsername" name="image" type="text" placeholder="Enter your url image">
                                    </div>
                                    <% if(request.getAttribute("error2")!=null)  {%>
                                    <a style="color:red; font-style: italic"><%out.println(request.getAttribute("error2"));%></a>
                                    <%}%>
                                    <!-- Profile picture upload button-->
                                    <button class="btn btn-primary" type="submit">Upload new image</button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-8">
                        <!-- Account details card-->
                        <div class="card mb-4">
                            <div class="card-header">Account Details</div>
                            <div class="card-body">
                                <form action="updateprofile" method="get">
                                    <!-- Form Group (username)-->
                                    <div class="mb-3">
                                        <label class="small mb-1" for="inputUsername">Username</label>
                                        <input class="form-control" id="inputUsername" type="text" placeholder="Enter your username" value="${sessionScope.account.username}" readonly>
                                    </div>
                                    <!-- Form Row-->
                                    <div class="row gx-3 mb-3">
                                        <!-- Form Group (first name)-->
                                        <div class="col-md-6">
                                            <label class="small mb-1" for="inputFirstName">Full Name</label>
                                            <input class="form-control" id="inputFullName" name="fullname" type="text" placeholder="Enter your first name" value="${sessionScope.account.fullName}" require>
                                        </div>
                                        <!-- Form Group (last name)-->
                                        <div class="col-md-6">
                                            <label class="small mb-1" for="inputLastName">CCCD</label>
                                            <input class="form-control" id="inputCCCD" name="cccd" type="text" placeholder="Enter your last name" value="${sessionScope.account.CCCD}" require>
                                        </div>
                                    </div>
                                    <!-- Form Row        -->
                                    <div class="row gx-3 mb-3">
                                        <!-- Form Group (organization name)-->
                                        <c:if test="${sessionScope.account.roleID == 1}">
                                            <div class="col-md-6">
                                                <label class="small mb-1" for="inputOrgName">Role</label>
                                                <input class="form-control" id="inputOrgName" type="text" placeholder="Enter your organization name" value="Admin" readonly>
                                            </div>
                                        </c:if>
                                        <c:if test="${sessionScope.account.roleID == 2}">
                                            <div class="col-md-6">
                                                <label class="small mb-1" for="inputOrgName">Role</label>
                                                <input class="form-control" id="inputOrgName" type="text" placeholder="Enter your organization name" value="Seller" readonly>
                                            </div>
                                        </c:if>
                                        <c:if test="${sessionScope.account.roleID == 3}">
                                            <div class="col-md-6">
                                                <label class="small mb-1" for="inputOrgName">Role</label>
                                                <input class="form-control" id="inputOrgName" type="text" placeholder="Enter your organization name" value="Manager" readonly>
                                            </div>
                                        </c:if>
                                        <c:if test="${sessionScope.account.roleID == 4}">
                                            <div class="col-md-6">
                                                <label class="small mb-1" for="inputOrgName">Role</label>
                                                <input class="form-control" id="inputOrgName" type="text" placeholder="Enter your organization name" value="Insurance Provider" readonly>
                                            </div>
                                        </c:if>
                                        <c:if test="${sessionScope.account.roleID == 5}">
                                            <div class="col-md-6">
                                                <label class="small mb-1" for="inputOrgName">Role</label>
                                                <input class="form-control" id="inputOrgName" type="text" placeholder="Enter your organization name" value="Customer" readonly>
                                            </div>
                                        </c:if>
                                        <!-- Form Group (location)-->
                                        <div class="col-md-6">
                                            <label class="small mb-1" for="inputLocation">Gender</label>
                                            <select name="gender" class="form-control">
                                                <option value="Male" ${sessionScope.account.gender == 'Male'?'selected':''}>Male</option>
                                                <option value="Female" ${sessionScope.account.gender == 'Female'?'selected':''}>Female</option>
                                            </select>
                                        </div>
                                    </div>
                                    <!-- Form Group (email address)-->
                                    <div class="mb-3">
                                        <label class="small mb-1" for="inputEmailAddress">Email address</label>
                                        <input class="form-control" id="inputEmailAddress" name="email" type="email" placeholder="Enter your email address" value="${sessionScope.account.email}" require>
                                    </div>
                                    <!-- Form Row-->
                                    <div class="row gx-3 mb-3">
                                        <!-- Form Group (phone number)-->
                                        <div class="col-md-6">
                                            <label class="small mb-1" for="inputPhone">Phone number</label>
                                            <input class="form-control" id="inputPhone" name="phone" type="phone" placeholder="Enter your phone number" value="${sessionScope.account.phone}" require>
                                        </div>
                                        <!-- Form Group (birthday)-->
                                        <div class="col-md-6">
                                            <label class="small mb-1" for="inputBirthday">Birthday</label>
                                            <input type="date" class="form-control" name="dob" value="${sessionScope.account.dateOfBirth}" placeholder="Date of birth">
                                        </div>
                                    </div>
                                    <div class="mb-3">
                                        <label class="small mb-1" for="inputEmailAddress">Address</label>
                                        <input class="form-control" id="inputEmailAddress" name="address" type="text" placeholder="Enter your email address" value="${sessionScope.account.address}" require>
                                    </div>
                                    <!-- Save changes button-->
                                    <% if(request.getAttribute("error")!=null)  {%>
                                    <a style="color:red; font-style: italic"><%out.println(request.getAttribute("error"));%></a>
                                    <%}%>
                                    <br>
                                    <button class="btn btn-primary" type="submit">Save changes</button>
                                </form>
                                <a href="profile.jsp"><button class="btn btn-primary">Back to your profile</button></a>
                            </div>
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
