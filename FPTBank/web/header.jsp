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
    </head>

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
            <div class="mil-top-panel"
                 <c:if test="${sessionScope.account.getRoleID() == 4}"> style="background-color:#f9f586;" </c:if>
                 <c:if test="${sessionScope.account.getRoleID() == 5}"> style="background-color: #f0fff0;" </c:if>  
                 <c:if test="${sessionScope.account.getRoleID() == 3}"> style="background-color: #e2d1c3;" </c:if>  
                 <c:if test="${sessionScope.account.getRoleID() == 2}"> style="background-color: #fed6e3;" </c:if>  
                 <c:if test="${sessionScope.account.getRoleID() == 1}"> style="background-color: #D7FFFE;" </c:if>  
                     >
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

                                     </ul>
                                 </li>
                                 <li>
                                     <a href="about">About</a>
                                 </li>
                                 <li>
                                     <a href="faq">FAQ</a>
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
                                             <li><a href="/timibank/myfeedback">My Feedback</a></li>
                                                 <c:if test="${sessionScope.account.getRoleID() == 5}">
                                                 <li><a href="/timibank/myassetsalary">My Asset and Salary</a></li>
                                                 </c:if>
                                                 <c:if test="${sessionScope.account.getRoleID()==4}">
                                                 <li><a href="/timibank/insurance/manageInsurance.jsp">Management</a></li>
                                                 </c:if>
                                                 <c:if test="${sessionScope.account.getRoleID()==1}">
                                                 <li><a href="/timibank/admin/home.jsp">Management</a></li>
                                                 </c:if>
                                                 <c:if test="${sessionScope.account.getRoleID()==2}">
                                                 <li><a href="/timibank/seller/home.jsp">Management</a></li>
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
        <!-- top panel end -->
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
                <%@include file="messenger.jsp" %>
    </body>
</html>