<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <div class="mil-top-panel" id="mil-top-panel"
         <c:if test="${sessionScope.account.getRoleID() == 4}">style="background-color:#f9f586;"</c:if>
        <c:if test="${sessionScope.account.getRoleID() == 5}">style="background-color: #f0fff0;"</c:if>  
        <c:if test="${sessionScope.account.getRoleID() == 3}">style="background-color: #e2d1c3;"</c:if>  
        <c:if test="${sessionScope.account.getRoleID() == 2}">style="background-color: #fed6e3;"</c:if>  
        <c:if test="${sessionScope.account.getRoleID() == 1}">style="background-color: #D7FFFE;"</c:if>  
        >
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
                                    <li><a href="/timibank/profile">My Profile</a></li>
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