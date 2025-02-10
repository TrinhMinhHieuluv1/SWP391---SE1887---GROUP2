<%-- 
    Document   : index
    Created on : Jan 20, 2025, 12:41:29 AM
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
        <style>
            body {
                display: flex;
                justify-content: center;
                align-items: center;
                padding-top: 10rem;
            }
            .styled-table {
                border-collapse: collapse;
                margin: 25px auto;
                font-size: 0.9em;
                font-family: sans-serif;
                min-width: 1000px;
                box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
            }
            .styled-table thead tr {
                background-color: #009879;
                color: #ffffff;
                text-align: left;
            }
            .styled-table th,
            .styled-table td {
                padding: 12px 15px;
            }
            .styled-table tbody tr {
                border-bottom: 1px solid #dddddd;
            }
            .styled-table tbody tr:nth-of-type(even) {
                background-color: #f3f3f3;
            }
            .styled-table tbody tr:last-of-type {
                border-bottom: 2px solid #009879;
            }
            .styled-table tbody tr.active-row {
                font-weight: bold;
                color: #009879;
            }
            .form-control, .dataTable-input {
                display: block;
                width: 15%;
                margin-left: 17%;
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
                    <a href="index.jsp" class="mil-logo">
                        <img src="img/logo1.png" alt="Plax" width="200">
                    </a>
                    <nav class="mil-top-menu">
                        <ul>
                            <li class="mil-has-children mil-active">
                                <a href="#.">Home</a>
                                <ul>
                                    <li><a href="index.jsp">Trang trủ</a></li>
                                    <li><a href="home-2.jsp">Gửi tiết kiệm</a></li>
                                    <li><a href="home-3.jsp">Type 3</a></li>
                                    <li><a href="home-4.jsp">Type 4</a></li>
                                    <li><a href="home-5.jsp">Type 5</a></li>

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

                    <c:if test="${sessionScope.account == null}">
                        <div class="mil-menu-buttons">
                            <a href="login.jsp" class="mil-btn mil-sm">Log in</a>
                            <a href="register.jsp" class="mil-btn mil-sm" style="margin-left: 10px">Register</a>
                        </div>
                    </c:if>
                    <c:if test="${sessionScope.account != null}">

                        <nav class="mil-top-menu">
                            <ul>
                                <li class="mil-has-children ">
                                    <a href="#." class="mil-btn mil-sm">My Account</a>
                                    <ul>
                                        <li><a href="profile.jsp">My Profile</a></li>
                                        <li><a href="/audio/change_pass">Change Password</a></li>
                                        <li><a href="/audio/purchase">My Purchase</a></li>
                                        <li><a href="feedback.jsp">My Feedback</a></li>
                                        <li><a href="/audio/signout">Sign out</a></li>


                                    </ul>
                                </li>   
                            </ul>


                        </nav>  


                    </c:if>

                </div>
            </div>
            <h2 style="color: green; text-align: center;">Your Feedback</h2>
            <!-- top panel end -->
            <form action="filterfeedback" method="get">
                <div style="display: flex;">
                    <input type="date" class="form-control" name="date" placeholder="Date">
                    <input class="form-control" id="inputFullName" name="search" type="text" placeholder="Searching">
                    <button type="submit" style="color: white; background-color: green; padding: 10px 5px; border-radius: 5px; margin-left: 10%;">Filter</button>
                    <br>
                </div>
                <% if(request.getAttribute("error")!=null)  {%>
                <a style="color:red; font-style: italic; margin-left: 18%;"><%out.println(request.getAttribute("error"));%></a>
                <%}%>
                <% if(request.getAttribute("error2")!=null)  {%>
                <a style="color:red; font-style: italic; margin-left: 18%;"><%out.println(request.getAttribute("error2"));%></a>
                <%}%>
            </form>
            <table class="styled-table">
                <thead>
                    <tr>
                        <th>No.Feedback</th>
                        <th>Message</th>
                        <th>Status</th>
                        <th>CreatedAt</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>        
                    <c:set var="count" value="0" />
                    <c:forEach items="${sessionScope.listfeedback}" var="feedback" varStatus="loop">
                        <c:if test="${feedback.status == true}">
                            <c:set var="count" value="${count + 1}" />
                            <tr>
                                 <td>${count}</td> 
                                <td>${feedback.message}</td>
                                <td>${feedback.response == null ? "Not responsed":"Responsed"}</td>
                                <td>${feedback.createdAt}</td>
                                <td><a href="reclaim?fid=${feedback.feedbackID}&cid=${feedback.customerID}"><button style="color: white; background-color: green; border-radius: 5px; padding: 5px 5px;">Reclaim</button></a></td>
                            </tr>
                        </c:if>
                    </c:forEach>

                </tbody>
            </table>
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
