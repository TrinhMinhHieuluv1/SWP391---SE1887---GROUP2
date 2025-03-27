<%-- 
    Document   : about
    Created on : Jan 20, 2025, 12:34:47 AM
    Author     : ADMIN
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List, model.User" %>

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


        <!-- wrapper -->

        <%@ include file="header.jsp"%>

        <!-- content -->
        <div id="smooth-content">
            <!-- Menu -->
            <%@ include file="toolBar.jsp"%>
            <!-- Menu end -->

            <!-- banner -->
            <div class="about-banner mil-banner mil-banner-inner mil-dissolve">
                <div class="container">
                    <div class="row align-items-center justify-content-center">
                        <div class="col-xl-8">
                            <div class="mil-banner-text mil-text-center">

                                <h2>TIMI is more than a platform, a financial revolution</h2>
                                <ul class="mil-breadcrumbs mil-center">
                                    <li><a href="index.jsp">Home</a></li>
                                    <li><a href="about.jsp">About us</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- banner end -->

            <!-- about content -->
            <div class="mil-features about-section">
                <div class="container">
                    <div class="about-introduction js-tabs">
                        <ul class="about-introduction__list">
                            <li class="about-introduction__item about-introduction__item--current">
                                Vision & Mission
                            </li>
                            <li class="about-introduction__item ">
                                Core Values
                            </li>
                            <li class="about-introduction__item">
                                Cultural identity
                            </li>
                        </ul>

                        <div class="about-introduction__contents">
                            <div class="about-introduction__content about-introduction__content--current" data-section="vision">
                                <h3 class="about-section__title">Vision & Mission</h3>
                                <p class="about-section__desc">
                                    No. 1 bank in Vietnam, one of the 100 largest banks in Asia, one of the 
                                    300 largest banking and financial groups in the world, one of the 1,000
                                    largest listed enterprises globally, contributing greatly to the sustainable development of Vietnam.                                    </p>
                                <div class="pagi"> 
                                    <div class="container1" id="container-vision"></div>
                                    <div class="pagination" id="pagination-vision"></div>
                                </div>
                            </div>  

                            <div class="about-introduction__content" data-section="core-values">
                                <h3 class="about-section__title">Core Values</h3>
                                <div class="pagi"> 
                                    <div class="container1" id="container-core-values"></div>
                                    <div class="pagination" id="pagination-core-values"></div>
                                </div>
                            </div>  

                            <div class="about-introduction__content">

                                <div class="container2">
                                    <div >
                                        <h3 class="about-section__title">Cultural identity</h3>
                                    </div>  
                                    <div class="content2">
                                        <div class="text2-section">
                                            <div class="text2-item">
                                                <h3>
                                                    Trust
                                                </h3>
                                                <p>
                                                    Keeping the word and being professional
                                                </p>
                                            </div>
                                            <div class="text2-item">
                                                <h3>
                                                    Standards
                                                </h3>
                                                <p>
                                                    Respecting principles & standard behavior
                                                </p>
                                            </div>
                                            <div class="text2-item">
                                                <h3>
                                                    Ready to innovate
                                                </h3>
                                                <p>
                                                    Always aiming for the new, modern and civilized
                                                </p>
                                            </div>
                                            <div class="text2-item">
                                                <h3>
                                                    Sustainable
                                                </h3>
                                                <p>
                                                    For long-term benefits
                                                </p>
                                            </div>
                                            <div class="text2-item">
                                                <h3>
                                                    Humanity
                                                </h3>
                                                <p>
                                                    Respecting morality, being close and understanding, sharing
                                                </p>
                                            </div>
                                        </div>
                                        <div class="image2-section">
                                            <img alt="A modern skyscraper with a futuristic design and glowing lights" height="350" src="https://storage.googleapis.com/a1aa/image/YtBMsz32qwJtcKdSDITM2FgbsfS1U2NpIgSMVKPYqCo.jpg" width="700"/>
                                        </div>

                                    </div> </div>

                            </div>


                        </div>
                    </div>
                </div>
                <br/><!-- comment -->
                <br/><!-- comment -->
                <!-- about content end -->

                <!-- facts -->
                <div class="mil-facts mil-p-0-130">
                    <div class="container">
                        <div class="row">
                            <div class="col-xl-4 mil-sm-text-center mil-mb-30 mil-up">
                                <p class="h1 mil-display mil-mb-15"><span class="mil-accent mil-counter" data-number="${teamCustomer}" >${teamCustomer}</span><span class="mil-pale">+</span></p>
                                <h5>Customer Number</h5>
                            </div>
                            <div class="col-xl-4 mil-sm-text-center mil-mb-30 mil-up">
                                <p class="h1 mil-display mil-mb-15"><span class="mil-accent mil-counter" data-number="${teamSeller}">${teamSeller}</span><span class="mil-pale">+</span></p>
                                <h5>Seller Number </h5>
                            </div>
                            <div class="col-xl-4 mil-sm-text-center mil-mb-30 mil-up">
                                <p class="h1 mil-display mil-mb-15"><span class="mil-accent mil-counter" data-number="${teamManage}">${teamManage}</span><span class="mil-pale">+</span></p>
                                <h5>Manager Number</h5>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- facts end -->

                <!-- about - history -->
                <div class="mil-cta mil-up">
                    <div class="container">
                        <div class="mil-out-frame about-history">
                            <div class="row justify-content-center mil-text-center">
                                <div class="col-xl-8 ">
                                    <h2 class="about-section__title">Development history</h2>
                                </div>
                            </div>
                            <div class="about-introduction js-tabs">
                                <ul class="about-introduction__list">
                                    <li class="about-introduction__item about-introduction__item--current">
                                        Formation and development process
                                    </li>
                                    <li class="about-introduction__item ">
                                        Milestones 
                                    </li>
                                </ul>

                                <div class="about-introduction__contents">
                                    <div class="about-introduction__content about-introduction__content--current">
                                        <p class="about-introduction__desc">
                                            Over the past 10 years, TIMIBANK has made important contributions to the cause of national reunification and building socialism in the North; participated in overcoming and removing difficulties during the subsidy period; and been a pioneer bank in the period of innovation, integration and development. In particular, the past 10 years have marked TIMIBANK's transformation and spectacular breakthrough with impressive growth in scale, quality and business efficiency, establishing a new peak with continued successes, opening up great opportunities in the next stage of development, contributing to the common prosperity of Vietnam.                                        </p>
                                        <ul class="about-introduction__more">
                                            <li class="about-introduction__more-item">
                                                The former TIMIBANK, Treasury Investment & Monetary Instruments (TIMIBANK) was established and officially put into operation on April 1, 2023 with its predecessor being the Central Foreign Exchange Management Department (under the State Bank of Vietnam).                                            </li>
                                            <li class="about-introduction__more-item">
                                                As the first state-owned commercial bank selected by the Government to pilot equitization, TIMIBANK officially operated as a joint stock commercial bank on June 2, 2023 after successfully implementing the equitization plan through the initial public offering of shares. On June 30, 2023, TIMIBANK shares (stock code TM) were officially listed on the Hanoi Stock Exchange.                                            </li>
                                        </ul>
                                        <button type="button" class="about-history__btn-more mil-btn mil-ssm mil-empty history__btn-more">
                                            <span class="text-collapsed" style="display: none">Thu gọn</span>
                                            <span class="text-expand" >More</span>

                                            <span class="icon-arrow-right">
                                            </span>
                                        </button>
                                    </div>  
                                    <div class="about-introduction__content">
                                        <div class="about-timeline js-tabs">
                                            <ul class="about-timeline__num-list">
                                                <li class="about-timeline__num about-timeline__num--current">
                                                    04/2023
                                                </li>
                                                <li class="about-timeline__num ">
                                                    06/2023
                                                </li>
                                                <li class="about-timeline__num">
                                                    12/2023
                                                </li>
                                                <li class="about-timeline__num ">
                                                    03/2024
                                                </li>
                                                <li class="about-timeline__num">
                                                    07/2024
                                                </li>
                                                <li class="about-timeline__num ">
                                                    11/2024
                                                </li>
                                                <li class="about-timeline__num">
                                                    01/2025
                                                </li>

                                            </ul>
                                            <div class="about-timeline__content about-timeline__content--current">                                                 
                                                <h3 class="about-timeline__heading">TIMIBANK was established and officially commenced operations</h3>                                                 
                                                <p class="about-timeline__desc">The predecessor organization was the Central Foreign Exchange Management Office, under the State Bank of Vietnam.</p>                                             
                                            </div>                                              
                                            <div class="about-timeline__content">                                                 
                                                <h3 class="about-timeline__heading">TIMIBANK's stock (Ticker: TM)</h3>                                                 
                                                <p class="about-timeline__desc">Listed on the Hanoi Stock Exchange (HNX), marking TIMIBANK's entry into the Vietnamese stock market.</p>                                             
                                            </div>                                             
                                            <div class="about-timeline__content">                                                 
                                                <h3 class="about-timeline__heading">TIMIBANK signs a strategic partnership agreement</h3>                                                 
                                                <p class="about-timeline__desc">Strategic cooperation with Visa and Mastercard to expand the payment network and develop superior credit card products, enabling customers to access modern financial services that meet international standards.</p>                                             
                                            </div>                                             
                                            <div class="about-timeline__content">                                                 
                                                <h3 class="about-timeline__heading">TIMIBANK reaches a major milestone</h3>                                                 
                                                <p class="about-timeline__desc">TIMIBANK reached 1 million individual customers and 10,000 businesses using its services, affirming its impressive growth rate and customer trust in less than a year of operating as a joint-stock commercial bank.</p>                                             
                                            </div>                                             
                                            <div class="about-timeline__content">                                                 
                                                <h3 class="about-timeline__heading">TIMIBANK launches a digital banking platform</h3>                                                 
                                                <p class="about-timeline__desc">TIMIBANK launched the AI-integrated digital banking platform "TIMISMART," providing intelligent financial services and optimizing personalized experiences for customers nationwide, marking a significant step in its digital transformation strategy.</p>                                             
                                            </div>                                             
                                            <div class="about-timeline__content">                                                 
                                                <h3 class="about-timeline__heading">TIMIBANK officially expands internationally</h3>                                                 
                                                <p class="about-timeline__desc">TIMIBANK officially expanded its international operations with the opening of its first branch in Singapore, asserting its position as a pioneering Vietnamese bank in the Southeast Asian financial market.</p>                                             
                                            </div>                                             
                                            <div class="about-timeline__content">                                                 
                                                <h3 class="about-timeline__heading">TIMIBANK launches the TIMIFUND investment fund</h3>                                                 
                                                <p class="about-timeline__desc">Providing diverse investment solutions from stocks and bonds to open-end funds, helping individual and corporate clients optimize their capital, reinforcing its pioneering position in the financial and investment sector in Vietnam.</p>                                             
                                            </div>

                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
                <!-- call to action end -->

                <!-- features -->
                <div class="mil-features mil-p-160-80">
                    <div class="container">
                        <div class="row flex-sm-row-reverse justify-content-between align-items-center">
                            <div class="col-xl-6 mil-mb-80">
                                <h2 class="mil-mb-30 mil-up">Our values are the foundation of trust</h2>
                                <p class="mil-text-m mil-soft mil-mb-60 mil-up">Our values, from transparency to accountability, form the foundation of our organizational culture and reflect our unwavering commitment.</p>
                                <ul class="mil-list-2 mil-type-2">
                                    <li>
                                        <div class="mil-up">
                                            <h5 class="mil-mb-15">Absolute Transparency:</h5>
                                            <p class="mil-text-m mil-soft">Discover how transparency is deeply embedded in our culture, providing users with clarity and confidence in every transaction.</p>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="mil-up">
                                            <h5 class="mil-mb-15">Commitment to Security:</h5>
                                            <p class="mil-text-m mil-soft">Explore our strong commitment to security, ensuring that every transaction is conducted with the highest protection standards.</p>
                                        </div>
                                    </li>
                                </ul>
                            </div>

                            <div class="col-xl-5 mil-mb-80">
                                <div class="mil-image-frame mil-up">
                                    <img src="img/about/value-about.jpg" alt="image" class="mil-scale-img" data-value-1="1" data-value-2="1.2">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- features end -->

                <!-- team -->
                <div class="mil-cta mil-up">
                    <div class="container">
                        <div class="mil-out-frame mil-visible mil-image mil-p-160-130">
                            <div class="row justify-content-center mil-text-center">
                                <div class="col-xl-8 mil-mb-80-adaptive-30">
                                    <h2 class="mil-light mil-up">Meet the people behind TIMIBANK</h2>
                                </div>
                            </div>

                            <!-- Kiểm tra xem teamMembers có rỗng hay không -->
                            <c:if test="${empty teamMembers}">
                                <p>No team members found.</p>
                            </c:if>

                            <!-- Danh sách thành viên -->
                            <div id="team-list-wrapper">
                                <div id="team-list" class="row team-list">
                                    <c:forEach items="${teamMembers}" var="member" >
                                        <div class="col-xl-3 col-md-6 col-sm-6 team-item">
                                            <div class="mil-team-card mil-mb-30 mil-up team-item__wrap">
                                                <div class="mil-portrait mil-mb-30 team-item__img-wrap">
                                                    <img src="${empty member.getImage() ? 'img/inner-pages/team/1.png' : member.image}" alt="portrait" class="team-item__img">
                                                </div>
                                                <h5 class="mil-light mil-mb-15 team-item__name">${member.getFullName()}</h5>
                                                <button class="team-item__btn mil-btn mil-ssm js-toggle" toggle-target="#modal-${member.getUserID()}">More</button>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="navigation">
                                <button disabled="" class="disabled navigation__prev"><i class="fa-solid fa-arrow-left-long navigation__icon"></i></button>
                                <button class="navigation__next"><i class="fa-solid fa-arrow-right-long navigation__icon"></i></button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- end team -->

                <!-- Modal team -->
                <!-- Modal: show info của thành viên -->
                <div id="modal-container">
                    <c:forEach items="${teamMembers}" var="member" >
                        <div id="modal-${member.getUserID()}" class="modal modal--large hide">
                            <div class="modal__content">
                                <button class="modal__close js-toggle" toggle-target="#modal-${member.getUserID()}">&times;</button>
                                <div class="row">
                                    <div class="col-5">
                                        <div class="modal__img-wrap">
                                            <img src="${member.getImage()}"  class="team-item__img">
                                        </div>
                                    </div>
                                    <div class="col-7">
                                        <div class="modal__info">
                                            <div class="modal__text">${member.getFullName()}</div>
                                            <a href="tel:${member.phone}" class="modal__link">Contact: ${member.getPhone()}</a>
                                            <a href="mailto:${member.email}" class="modal__link">Email: ${member.getEmail()}</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="modal__overlay js-toggle" toggle-target="#modal-${member.getUserID()}"></div>
                        </div>
                    </c:forEach>
                </div>

                <!-- end Modal team -->

                <!-- quote -->
                <div class="mil-quote mil-p-160-130">
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-xl-10">
                                <h2 class="mil-mb-30">"At TIMIBANK, transparency is not just a promise; it is the foundation of our relationship with you. We believe that trust is built on clear policies and consistent actions."</h2>
                                <p class="mil-text-m mil-soft mil-mb-60">- TIMI Team</p>
                                <div class="row">
                                    <div class="col-xl-6">
                                        <ul class="mil-list-2 mil-type-2 mil-mb-30">
                                            <li>
                                                <div class="mil-up">
                                                    <h5 class="mil-mb-15">Privacy Policy</h5>
                                                    <p class="mil-text-m mil-soft">Your privacy is our top priority. We never share your information with third parties without your explicit consent.</p>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="col-xl-6">
                                        <ul class="mil-list-2 mil-type-2 mil-mb-30">
                                            <li>
                                                <div class="mil-up">
                                                    <h5 class="mil-mb-15">Data Protection</h5>
                                                    <p class="mil-text-m mil-soft">We are committed to protecting your personal and financial data with the highest security measures.</p>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
                <!-- quote end -->

                <!-- call to action -->
                <div class="mil-cta mil-up">
                    <div class="container">
                        <div class="mil-out-frame mil-p-160-160" style="background-image: url(img/home-3/5.png)">
                            <div class="row justify-content-between align-items-center">
                                <div class="col-xl-7 mil-sm-text-center">
                                    <h2 class="mil-light mil-mb-30 mil-up">Discover the Freedom of <br>Comprehensive Financial Control</h2>
                                    <p class="mil-text-m mil-mb-60 mil-dark-soft mil-up">Join TIMIBANK and take the first step towards a more balanced and hassle-free financial life.</p>

                                    <div class="mil-buttons-frame mil-up">
                                        <a href="https://apps.apple.com/vn/app/vcb-digibank/id561433133?l=vi" class="mil-btn mil-md">App Store</a>
                                        <a href="https://play.google.com/store/apps/details?id=com.VCB" class="mil-btn mil-border mil-md">Google Play</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <br/><br/>


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

        <script src="./js/scripts.js"></script>
    </body>
</html>
