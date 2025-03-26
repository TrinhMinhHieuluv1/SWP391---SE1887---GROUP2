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

            <!-- scroll progress end -->

            <!-- back to top -->

            <!-- top panel end -->
            <%@ include file="header.jsp"%>
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
                                <form action="updateimage" method="post" enctype="multipart/form-data">
                                    <!-- Profile picture image-->
                                    <img class="img-account-profile rounded-circle mb-2" src="${sessionScope.account.getImage().replace('../', '/timibank/')}" alt="" >
                                    <!-- Profile picture help block-->
                                    <div class="mb-3">           
                                        <input style="color: white;" id="inputUsername" name="image" type="file" accept="uploads/*" >
                                        <span id="imgError" class="error"></span>
                                    </div>
                                    <% if(request.getAttribute("error2") != null) { %>
                                    <div style="color: red; font-style: italic; margin-bottom: 10px;">
                                        <%= request.getAttribute("error2") %>
                                    </div>
                                    <% } %>

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
