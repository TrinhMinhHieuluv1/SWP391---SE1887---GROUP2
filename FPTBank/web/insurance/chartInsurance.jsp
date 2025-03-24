<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList, java.util.List" %>
<%@ page import="java.util.*, java.text.SimpleDateFormat" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en" data-bs-theme="light">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>TIMI BANK</title>

        <!--plugins-->
        <link href="assets/plugins/perfect-scrollbar/css/perfect-scrollbar.css" rel="stylesheet">
        <link href="assets/plugins/metismenu/css/metisMenu.min.css" rel="stylesheet">
        <link href="assets/plugins/simplebar/css/simplebar.css" rel="stylesheet">
        <!-- loader-->
        <link href="assets/css/pace.min.css" rel="stylesheet">
        <script src="assets/js/pace.min.js"></script>
        <!--Styles-->
        <link href="assets/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
        <link rel="stylesheet" href="assets/css/icons.css">

        <!-- Favicon -->
        <link rel="shortcut icon" href="assets/images/logo-icon.png" type="image/x-icon">
        <link rel="icon" href="assets/images/logo-icon.png" type="image/x-icon">

        <link href="https://fonts.googleapis.com/css2?family=Noto+Sans:wght@300;400;500;600&display=swap" rel="stylesheet">
        <link href="assets/css/main.css" rel="stylesheet">
        <link href="assets/css/dark-theme.css" rel="stylesheet">
        <link href="assets/css/semi-dark-theme.css" rel="stylesheet">
        <link href="assets/css/minimal-theme.css" rel="stylesheet">
        <link href="assets/css/shadow-theme.css" rel="stylesheet">

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>

        <style>
            .btn-excel {
                display: block;
                width: 150px;
                padding: 10px;
                background-color: #28a745;
                color: #fff;
                border: none;
                border-radius: 5px;
                text-align: center;
                text-decoration: none;
                font-weight: 500;
                transition: background-color 0.3s ease;
                cursor: pointer;
            }
            .btn-excel:hover {
                background-color: #218838;
                color: #fff;
            }
            .chart-box {
                flex: 1; /* Chia đều chiều rộng */
                max-width: 50%; /* Mỗi ô chiếm tối đa 50% */
                padding: 10px; /* Thêm khoảng cách */
            }
            .btn {
                padding: 8px 12px;
                font-size: 14px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                transition: background-color 0.3s;
            }
            .btn-pending {
                background-color: green;
                color: white;
            }
            .btn-sent {
                background-color: white;
                color: black;
                border: 1px solid gray;
            }
            .btn:hover {
                opacity: 0.8;
            }
        </style>
    </head>
    <body>
        <%@ include file="toarst.jsp" %>

        <!--start header-->
        <header class="top-header">
            <nav class="navbar navbar-expand justify-content-between">
                <div class="btn-toggle-menu">
                    <span class="material-symbols-outlined">menu</span>
                </div>
                <div class="position-relative search-bar d-lg-block d-none" data-bs-toggle="modal" data-bs-target="#exampleModal">
                    <input class="form-control form-control-sm rounded-5 px-5" disabled type="search" placeholder="Search">
                    <span class="material-symbols-outlined position-absolute ms-3 translate-middle-y start-0 top-50">search</span>
                </div>
                <ul class="navbar-nav top-right-menu gap-2">
                    <li class="nav-item d-lg-none d-block" data-bs-toggle="modal" data-bs-target="#exampleModal">
                        <a class="nav-link" href="javascript:;"><span class="material-symbols-outlined">
                                search
                            </span></a>
                    </li>
                    <li class="nav-item dark-mode">
                        <a class="nav-link dark-mode-icon" href="javascript:;"><span class="material-symbols-outlined">dark_mode</span></a>
                    </li>
                    <li class="nav-item dropdown dropdown-app">
                        <a class="nav-link dropdown-toggle dropdown-toggle-nocaret" data-bs-toggle="dropdown" href="javascript:;"><span class="material-symbols-outlined">
                                apps
                            </span></a>
                        <div class="dropdown-menu dropdown-menu-end mt-lg-2 p-0">
                            <div class="app-container p-2 my-2">
                                <div class="row gx-0 gy-2 row-cols-3 justify-content-center p-2">
                                    <div class="col">
                                        <a href="javascript:;">
                                            <div class="app-box text-center">
                                                <div class="app-icon">
                                                    <img src="assets/images/icons/slack.png" width="30" alt="">
                                                </div>
                                                <div class="app-name">
                                                    <p class="mb-0 mt-1">Slack</p>
                                                </div>
                                            </div>
                                        </a>
                                    </div>
                                    <div class="col">
                                        <a href="javascript:;">
                                            <div class="app-box text-center">
                                                <div class="app-icon">
                                                    <img src="assets/images/icons/behance.png" width="30" alt="">
                                                </div>
                                                <div class="app-name">
                                                    <p class="mb-0 mt-1">Behance</p>
                                                </div>
                                            </div>
                                        </a>
                                    </div>
                                    <div class="col">
                                        <a href="javascript:;">
                                            <div class="app-box text-center">
                                                <div class="app-icon">
                                                    <img src="assets/images/icons/google-drive.png" width="30" alt="">
                                                </div>
                                                <div class="app-name">
                                                    <p class="mb-0 mt-1">Dribble</p>
                                                </div>
                                            </div>
                                        </a>
                                    </div>
                                    <div class="col">
                                        <a href="javascript:;">
                                            <div class="app-box text-center">
                                                <div class="app-icon">
                                                    <img src="assets/images/icons/outlook.png" width="30" alt="">
                                                </div>
                                                <div class="app-name">
                                                    <p class="mb-0 mt-1">Outlook</p>
                                                </div>
                                            </div>
                                        </a>
                                    </div>
                                    <div class="col">
                                        <a href="javascript:;">
                                            <div class="app-box text-center">
                                                <div class="app-icon">
                                                    <img src="assets/images/icons/github.png" width="30" alt="">
                                                </div>
                                                <div class="app-name">
                                                    <p class="mb-0 mt-1">GitHub</p>
                                                </div>
                                            </div>
                                        </a>
                                    </div>
                                    <div class="col">
                                        <a href="javascript:;">
                                            <div class="app-box text-center">
                                                <div class="app-icon">
                                                    <img src="assets/images/icons/stack-overflow.png" width="30" alt="">
                                                </div>
                                                <div class="app-name">
                                                    <p class="mb-0 mt-1">Stack</p>
                                                </div>
                                            </div>
                                        </a>
                                    </div>
                                    <div class="col">
                                        <a href="javascript:;">
                                            <div class="app-box text-center">
                                                <div class="app-icon">
                                                    <img src="assets/images/icons/figma.png" width="30" alt="">
                                                </div>
                                                <div class="app-name">
                                                    <p class="mb-0 mt-1">Stack</p>
                                                </div>
                                            </div>
                                        </a>
                                    </div>
                                    <div class="col">
                                        <a href="javascript:;">
                                            <div class="app-box text-center">
                                                <div class="app-icon">
                                                    <img src="assets/images/icons/twitter.png" width="30" alt="">
                                                </div>
                                                <div class="app-name">
                                                    <p class="mb-0 mt-1">Twitter</p>
                                                </div>
                                            </div>
                                        </a>
                                    </div>
                                    <div class="col">
                                        <a href="javascript:;">
                                            <div class="app-box text-center">
                                                <div class="app-icon">
                                                    <img src="assets/images/icons/google-calendar.png" width="30" alt="">
                                                </div>
                                                <div class="app-name">
                                                    <p class="mb-0 mt-1">Calendar</p>
                                                </div>
                                            </div>
                                        </a>
                                    </div>
                                    <div class="col">
                                        <a href="javascript:;">
                                            <div class="app-box text-center">
                                                <div class="app-icon">
                                                    <img src="assets/images/icons/spotify.png" width="30" alt="">
                                                </div>
                                                <div class="app-name">
                                                    <p class="mb-0 mt-1">Spotify</p>
                                                </div>
                                            </div>
                                        </a>
                                    </div>
                                    <div class="col">
                                        <a href="javascript:;">
                                            <div class="app-box text-center">
                                                <div class="app-icon">
                                                    <img src="assets/images/icons/google-photos.png" width="30" alt="">
                                                </div>
                                                <div class="app-name">
                                                    <p class="mb-0 mt-1">Photos</p>
                                                </div>
                                            </div>
                                        </a>
                                    </div>
                                    <div class="col">
                                        <a href="javascript:;">
                                            <div class="app-box text-center">
                                                <div class="app-icon">
                                                    <img src="assets/images/icons/pinterest.png" width="30" alt="">
                                                </div>
                                                <div class="app-name">
                                                    <p class="mb-0 mt-1">Photos</p>
                                                </div>
                                            </div>
                                        </a>
                                    </div>
                                    <div class="col">
                                        <a href="javascript:;">
                                            <div class="app-box text-center">
                                                <div class="app-icon">
                                                    <img src="assets/images/icons/linkedin.png" width="30" alt="">
                                                </div>
                                                <div class="app-name">
                                                    <p class="mb-0 mt-1">linkedin</p>
                                                </div>
                                            </div>
                                        </a>
                                    </div>
                                    <div class="col">
                                        <a href="javascript:;">
                                            <div class="app-box text-center">
                                                <div class="app-icon">
                                                    <img src="assets/images/icons/dribble.png" width="30" alt="">
                                                </div>
                                                <div class="app-name">
                                                    <p class="mb-0 mt-1">Dribble</p>
                                                </div>
                                            </div>
                                        </a>
                                    </div>
                                    <div class="col">
                                        <a href="javascript:;">
                                            <div class="app-box text-center">
                                                <div class="app-icon">
                                                    <img src="assets/images/icons/youtube.png" width="30" alt="">
                                                </div>
                                                <div class="app-name">
                                                    <p class="mb-0 mt-1">YouTube</p>
                                                </div>
                                            </div>
                                        </a>
                                    </div>
                                    <div class="col">
                                        <a href="javascript:;">
                                            <div class="app-box text-center">
                                                <div class="app-icon">
                                                    <img src="assets/images/icons/google.png" width="30" alt="">
                                                </div>
                                                <div class="app-name">
                                                    <p class="mb-0 mt-1">News</p>
                                                </div>
                                            </div>
                                        </a>
                                    </div>
                                    <div class="col">
                                        <a href="javascript:;">
                                            <div class="app-box text-center">
                                                <div class="app-icon">
                                                    <img src="assets/images/icons/envato.png" width="30" alt="">
                                                </div>
                                                <div class="app-name">
                                                    <p class="mb-0 mt-1">Envato</p>
                                                </div>
                                            </div>
                                        </a>
                                    </div>
                                    <div class="col">
                                        <a href="javascript:;">
                                            <div class="app-box text-center">
                                                <div class="app-icon">
                                                    <img src="assets/images/icons/safari.png" width="30" alt="">
                                                </div>
                                                <div class="app-name">
                                                    <p class="mb-0 mt-1">Safari</p>
                                                </div>
                                            </div>
                                        </a>
                                    </div>

                                </div><!--end row-->

                            </div>
                        </div>
                    </li>
                    <li class="nav-item dropdown dropdown-large">
                        <a class="nav-link dropdown-toggle dropdown-toggle-nocaret" href="javascript:;" data-bs-toggle="dropdown">
                            <div class="position-relative">
                                <span class="notify-badge">8</span>
                                <span class="material-symbols-outlined">
                                    notifications_none
                                </span>
                            </div>
                        </a>
                        <div class="dropdown-menu dropdown-menu-end mt-lg-2">
                            <a href="javascript:;">
                                <div class="msg-header">
                                    <p class="msg-header-title">Notifications</p>
                                    <p class="msg-header-clear ms-auto">Marks all as read</p>
                                </div>
                            </a>
                            <div class="header-notifications-list">
                                <a class="dropdown-item" href="javascript:;">
                                    <div class="d-flex align-items-center">
                                        <div class="notify text-primary border">
                                            <span class="material-symbols-outlined">
                                                add_shopping_cart
                                            </span>
                                        </div>
                                        <div class="flex-grow-1">
                                            <h6 class="msg-name">New Orders <span class="msg-time float-end">2 min
                                                    ago</span></h6>
                                            <p class="msg-info">You have recived new orders</p>
                                        </div>
                                    </div>
                                </a>
                                <a class="dropdown-item" href="javascript:;">
                                    <div class="d-flex align-items-center">
                                        <div class="notify text-danger border">
                                            <span class="material-symbols-outlined">
                                                account_circle
                                            </span>
                                        </div>
                                        <div class="flex-grow-1">
                                            <h6 class="msg-name">New Customers<span class="msg-time float-end">14 Sec
                                                    ago</span></h6>
                                            <p class="msg-info">5 new user registered</p>
                                        </div>
                                    </div>
                                </a>
                                <a class="dropdown-item" href="javascript:;">
                                    <div class="d-flex align-items-center">
                                        <div class="notify text-success border">
                                            <span class="material-symbols-outlined">
                                                picture_as_pdf
                                            </span>
                                        </div>
                                        <div class="flex-grow-1">
                                            <h6 class="msg-name">24 PDF File<span class="msg-time float-end">19 min
                                                    ago</span></h6>
                                            <p class="msg-info">The pdf files generated</p>
                                        </div>
                                    </div>
                                </a>
                                <a class="dropdown-item" href="javascript:;">
                                    <div class="d-flex align-items-center">
                                        <div class="notify text-info border">
                                            <span class="material-symbols-outlined">
                                                store
                                            </span>
                                        </div>
                                        <div class="flex-grow-1">
                                            <h6 class="msg-name">New Product Approved <span class="msg-time float-end">2 hrs ago</span></h6>
                                            <p class="msg-info">Your new product has approved</p>
                                        </div>
                                    </div>
                                </a>
                                <a class="dropdown-item" href="javascript:;">
                                    <div class="d-flex align-items-center">
                                        <div class="notify text-warning border">
                                            <span class="material-symbols-outlined">
                                                event_available
                                            </span>
                                        </div>
                                        <div class="flex-grow-1">
                                            <h6 class="msg-name">Time Response <span class="msg-time float-end">28 min
                                                    ago</span></h6>
                                            <p class="msg-info">5.1 min avarage time response</p>
                                        </div>
                                    </div>
                                </a>
                                <a class="dropdown-item" href="javascript:;">
                                    <div class="d-flex align-items-center">
                                        <div class="notify text-danger border">
                                            <span class="material-symbols-outlined">
                                                forum
                                            </span>
                                        </div>
                                        <div class="flex-grow-1">
                                            <h6 class="msg-name">New Comments <span class="msg-time float-end">4 hrs
                                                    ago</span></h6>
                                            <p class="msg-info">New customer comments recived</p>
                                        </div>
                                    </div>
                                </a>
                                <a class="dropdown-item" href="javascript:;">
                                    <div class="d-flex align-items-center">
                                        <div class="notify text-primary border">
                                            <span class="material-symbols-outlined">
                                                local_florist
                                            </span>
                                        </div>
                                        <div class="flex-grow-1">
                                            <h6 class="msg-name">New 24 authors<span class="msg-time float-end">1 day
                                                    ago</span></h6>
                                            <p class="msg-info">24 new authors joined last week</p>
                                        </div>
                                    </div>
                                </a>
                                <a class="dropdown-item" href="javascript:;">
                                    <div class="d-flex align-items-center">
                                        <div class="notify text-success border">
                                            <span class="material-symbols-outlined">
                                                park
                                            </span>
                                        </div>
                                        <div class="flex-grow-1">
                                            <h6 class="msg-name">Your item is shipped <span class="msg-time float-end">5 hrs
                                                    ago</span></h6>
                                            <p class="msg-info">Successfully shipped your item</p>
                                        </div>
                                    </div>
                                </a>
                                <a class="dropdown-item" href="javascript:;">
                                    <div class="d-flex align-items-center">
                                        <div class="notify text-warning border">
                                            <span class="material-symbols-outlined">
                                                elevation
                                            </span>
                                        </div>
                                        <div class="flex-grow-1">
                                            <h6 class="msg-name">Defense Alerts <span class="msg-time float-end">2 weeks
                                                    ago</span></h6>
                                            <p class="msg-info">45% less alerts last 4 weeks</p>
                                        </div>
                                    </div>
                                </a>
                            </div>
                            <a href="javascript:;">
                                <div class="text-center msg-footer">View All</div>
                            </a>
                        </div>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" data-bs-toggle="offcanvas" href="#ThemeCustomizer"><span class="material-symbols-outlined">
                                settings
                            </span></a>
                    </li>
                </ul>
            </nav>
        </header>
        <!--end header-->


        <!--start sidebar-->
        <aside class="sidebar-wrapper">
            <div class="sidebar-header">
                <div class="logo-icon">
                    <img src="assets/images/logo-icon.png" class="logo-img" alt="">
                </div>
                <div class="logo-name flex-grow-1">
                    <h5 class="mb-0">BANK</h5>
                </div>
                <div class="sidebar-close ">
                    <span class="material-symbols-outlined">close</span>
                </div>
            </div>
            <div class="sidebar-nav" data-simplebar="true">

                <!--navigation-->
                <ul class="metismenu" id="menu">
                    <li>
                        <a href="/timibank/home.jsp">
                            <div class="parent-icon"><span class="material-symbols-outlined">home</span>
                            </div>
                            <div class="menu-title">Back to home</div>
                        </a>
                    </li>



                    <li>
                        <a href="#" class="has-arrow">
                            <div class="parent-icon"><span class="material-symbols-outlined">shopping_cart</span>
                            </div>
                            <div class="menu-title">Management</div>
                        </a>
                        <ul>


                            <li> <a href="insurancedetail"><span class="material-symbols-outlined">arrow_right</span>Provider Information</a>
                            </li>
                            <li> <a href="showinsurance"><span class="material-symbols-outlined">arrow_right</span>Manage Insurance</a>
                            </li>

                        </ul>
                    </li> 


                    <li class="menu-label">Charts & Maps</li>
                    <li>
                        <a class="has-arrow" href="javascript:;">
                            <div class="parent-icon"><span class="material-symbols-outlined">monitoring</span>
                            </div>
                            <div class="menu-title">Charts</div>
                        </a>
                        <ul>

                            <li> <a href="getdatainsurance"><span class="material-symbols-outlined">arrow_right</span>Statistic of Insurance</a>
                            </li>
                        </ul>
                    </li>

                </ul>
                <!--end navigation-->


            </div>

            <div class="sidebar-bottom dropdown dropup-center dropup">
                <div class="dropdown-toggle d-flex align-items-center px-3 gap-3 w-100 h-100" data-bs-toggle="dropdown">
                    <div class="user-img">
                        <img src="assets/images/avatars/01.png" alt="">
                    </div>
                    <div class="user-info">
                        <h5 class="mb-0 user-name">${sessionScope.account.username}</h5>
                        <p class="mb-0 user-designation">${sessionScope.account.email}</p>
                    </div>
                </div>

                <ul class="dropdown-menu dropdown-menu-end">

                    <li><a class="dropdown-item" href="javascript:;"><span class="material-symbols-outlined me-2">
                                account_circle
                            </span><span>Profile</span></a>
                    </li>
                    <!--                    <li><a class="dropdown-item" href="javascript:;"><span class="material-symbols-outlined me-2">
                                                    tune
                                                </span><span>Settings</span></a>
                                        </li>
                                        <li><a class="dropdown-item" href="javascript:;"><span class="material-symbols-outlined me-2">
                                                    dashboard
                                                </span><span>Dashboard</span></a>
                                        </li>
                                        <li><a class="dropdown-item" href="javascript:;"><span class="material-symbols-outlined me-2">
                                                    account_balance
                                                </span><span>Earnings</span></a>
                                        </li>
                                        <li><a class="dropdown-item" href="javascript:;"><span class="material-symbols-outlined me-2">
                                                    cloud_download
                                                </span><span>Downloads</span></a>
                                        </li>
                                        <li>
                                            <div class="dropdown-divider mb-0"></div>
                                        </li>-->

                    <li><a class="dropdown-item" href="logout"><span class="material-symbols-outlined me-2">
                                logout
                            </span><span>Logout</span></a>
                    </li>
                </ul>
            </div>
        </aside>
        <!--end sidebar-->


        <!--start main content-->
        <main class="page-content">
            <!--breadcrumb-->
            <div class="page-breadcrumb d-none d-sm-flex align-items-center mb-3">
                <div class="breadcrumb-title pe-3">Charts</div>
                <div class="ps-3">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb mb-0 p-0">
                            <li class="breadcrumb-item"><a href="javascript:;"><i class="bx bx-home-alt"></i></a>
                            </li>
                            <li class="breadcrumb-item active" aria-current="page">Statistic of Insurance</li>
                        </ol>
                    </nav>
                </div>

            </div>
            <!--end breadcrumb-->


            <div class="row">
                <div class="col-xl-12">

                    <!-- Chart Amount -->
                    <form id="pdfForm" action="ChartToPDFServlet" method="post">

                        <div class="card">
                            <div class="card-header bg-transparent py-3 d-flex align-items-center">
                                <h6 class="mb-0 text-uppercase">Statistic of Insurance Max Amount Of Loan level</h6>
                                <span class="ms-3">Status: <%= request.getParameter("statusFilterAmount") != null ? request.getParameter("statusFilterAmount").replace("ChartAmount", "") : "Both" %></span>
                                <%
                                                       
                                                            
                                                                      Object amount1bj = session.getAttribute("amount1");
                                                                      Object amount2bj = session.getAttribute("amount2");
                                                                      Object amount4bj = session.getAttribute("amount4");
                                                                      Object amount6bj = session.getAttribute("amount6");
                                                                      Object amount8bj = session.getAttribute("amount8");
                                                                      Object amount10bj = session.getAttribute("amount10");
                                                                      Object ToltalAmountbj = session.getAttribute("ToltalAmount");
                                                           
                                                                      int amount1 = (amount1bj instanceof Integer) ? (Integer) amount1bj : 0;
                                                                      int amount2 = (amount2bj instanceof Integer) ? (Integer) amount2bj : 0;
                                                                      int amount4 = (amount4bj instanceof Integer) ? (Integer) amount4bj : 0;
                                                                      int amount6 = (amount6bj instanceof Integer) ? (Integer) amount6bj : 0;
                                                                      int amount8 = (amount8bj instanceof Integer) ? (Integer) amount8bj : 0;
                                                                      int amount10 = (amount10bj instanceof Integer) ? (Integer) amount10bj : 0;
                                                                      int ToltalAmount= (ToltalAmountbj instanceof Integer) ? (Integer) ToltalAmountbj : 0;
                                                         

                                %>



                                <div class="ms-auto d-flex align-items-center">

                                    <div >
                                        <input type="hidden" name="textData" id="textData">
                                        <!-- Thêm các trường ẩn để lưu dữ liệu cập nhật -->
                                        <input type="hidden" name="amount1" id="amount1Input" value="<%= amount1 %>">
                                        <input type="hidden" name="amount2" id="amount2Input" value="<%= amount2 %>">
                                        <input type="hidden" name="amount4" id="amount4Input" value="<%= amount4 %>">
                                        <input type="hidden" name="amount6" id="amount6Input" value="<%= amount6 %>">
                                        <input type="hidden" name="amount8" id="amount8Input" value="<%= amount8 %>">
                                        <input type="hidden" name="amount10" id="amount10Input" value="<%= amount10 %>">
                                        <input type="hidden" name="ToltalAmount" id="ToltalAmountInput" value="<%= ToltalAmount %>">

                                        <input type="hidden" name="image" id="imageInput">
                                        <input type="hidden" name="statusFilterAmount" id="statusFilterAmountInput" value="<%= request.getParameter("statusFilterAmount") != null ? request.getParameter("statusFilterAmount") : "BothChartAmount" %>">
                                        <button class="btn btn-pending" type="submit" onclick="convertCanvasToImage()">Tải PDF</button>

                                    </div>
                                    <div class="btn-group position-static">

                                        <button type="button" class="btn border btn-light dropdown-toggle px-4" data-bs-toggle="dropdown" aria-expanded="false">
                                            <%= session.getAttribute("statusFilterAmount") != null ? session.getAttribute("statusFilterAmount").toString().replace("ChartAmount", "") : "Status" %>
                                        </button>                                        <ul class="dropdown-menu">
                                            <li><a class="dropdown-item" href="getdatainsurance?statusFilterAmount=ActiveChartAmount">Active</a></li>
                                            <li><a class="dropdown-item" href="getdatainsurance?statusFilterAmount=InactiveChartAmount">Inactive</a></li>
                                            <li><a class="dropdown-item" href="getdatainsurance?statusFilterAmount=BothChartAmount">Both</a></li>
                                        </ul>
                                    </div>

                                </div>
                            </div>

                            <div class="card-body">
                                <div class="chart-js-container3">
                                    <canvas id="chart7"></canvas>
                                </div>
                            </div>
                        </div>
                    </form>



                    <!--                    chart coverate-->
                    <div class="card">
                        <form id="pdfForm" action="ChartToPDFServletCover" method="post">

                            <div class="card-header bg-transparent py-3 d-flex align-items-center">
                                <h6 class="mb-0 text-uppercase">Statistic of Insurance coverRate lever</h6>
                                <span class="ms-3">Status: <%= request.getParameter("statusFilterCover") != null ? request.getParameter("statusFilterCover").replace("ChartCover", "") : "Both" %></span>

                                <%
                                                       
                                                            
                                                                      Object coverate30bj = session.getAttribute("coverate30");
                                                                      Object coverate50bj = session.getAttribute("coverate50");
                                                                      Object coverate60bj = session.getAttribute("coverate60");
                                                                      Object coverate70bj = session.getAttribute("coverate70");
                                                                      Object coverate80bj = session.getAttribute("coverate80");
                                                                      Object coverate100bj = session.getAttribute("coverate100");
                                                                      Object totalCoveratebj = session.getAttribute("totalCoverate");
                                                           
                                                                      int coverate30 = (coverate30bj instanceof Integer) ? (Integer) coverate30bj : 0;
                                                                      int coverate50 = (coverate50bj instanceof Integer) ? (Integer) coverate50bj : 0;
                                                                      int coverate60 = (coverate60bj instanceof Integer) ? (Integer) coverate60bj : 0;
                                                                      int coverate70 = (coverate70bj instanceof Integer) ? (Integer) coverate70bj : 0;
                                                                       int coverate80 = (coverate80bj instanceof Integer) ? (Integer) coverate80bj : 0;
                                                                      int coverate100 = (coverate100bj instanceof Integer) ? (Integer) coverate100bj : 0;
                                                                      int totalCoverate= (totalCoveratebj instanceof Integer) ? (Integer) totalCoveratebj : 0;
                                                         

                                %>


                                <div class="ms-auto d-flex align-items-center">


                                    <div >
                                        <input type="hidden" name="coverate30" id="coverate30Input" value="<%= coverate30 %>">
                                        <input type="hidden" name="coverate50" id="coverate50Input" value="<%= coverate50 %>">
                                        <input type="hidden" name="coverate60" id="coverate60Input" value="<%= coverate60 %>">
                                        <input type="hidden" name="coverate70" id="coverate70Input" value="<%= coverate70 %>">
                                        <input type="hidden" name="coverate80" id="coverate80Input" value="<%= coverate80 %>">
                                        <input type="hidden" name="coverate100" id="coverate100Input" value="<%= coverate100 %>">
                                        <input type="hidden" name="totalCoverate" id="totalCoverateInput" value="<%= totalCoverate %>">
                                        <input type="hidden" name="imageCover" id="imageInputCover">
                                        <input type="hidden" name="statusFilterCover" id="statusFilterCoverInput" value="<%= request.getParameter("statusFilterCover") != null ? request.getParameter("statusFilterCover") : "BothChartCover" %>">

                                        <button class="btn btn-pending" type="submit" onclick="convertCanvasToImageCover()">Tải PDF</button>

                                    </div>
                                    <div class="btn-group position-static">
                                        <button type="button" class="btn border btn-light dropdown-toggle px-4" data-bs-toggle="dropdown" aria-expanded="false">
                                            <%= session.getAttribute("statusFilterCover") != null ? session.getAttribute("statusFilterCover").toString().replace("ChartCover", "") : "Status" %>
                                        </button>                                        <ul class="dropdown-menu">
                                            <li><a class="dropdown-item" href="getdatainsurance?statusFilterCover=ActiveChartCover">Active</a></li>
                                            <li><a class="dropdown-item" href="getdatainsurance?statusFilterCover=InactiveChartCover">Inactive</a></li>
                                            <li><a class="dropdown-item" href="getdatainsurance?statusFilterCover=BothChartCover">Both</a></li>
                                        </ul>
                                    </div>


                                </div>
                            </div>

                            <div class="card-body">
                                <div class="chart-js-container3">
                                    <canvas id="chart21"></canvas>
                                </div>
                            </div>
                        </form>
                    </div>

                    <!--             
                    Chart feerate 
                    -->                            
                    <div class="card">
                        <form id="pdfForm" action="ChartToPDFServletFee" method="post">

                            <div class="card-header bg-transparent py-3 d-flex align-items-center">
                                <h6 class="mb-0 text-uppercase">Statistic of Insurance feeRate level</h6>
                                <span class="ms-3">Status: <%= request.getParameter("statusFilterFee") != null ? request.getParameter("statusFilterFee").replace("ChartFee", "") : "Both" %></span>

                                <%
                                                       
                                                            
                                                                      Object percentfeerate10Obj = session.getAttribute("feerate10");
                                                                      Object percentfeerate20Obj = session.getAttribute("feerate20");
                                                                      Object percentfeerate30Obj = session.getAttribute("feerate30");
                                                                      Object percentfeerate100Obj = session.getAttribute("feerate100");
                                                                      Object totalFeerateObj = session.getAttribute("totalFeerate");
                                                           
                                                                      int percentfeerate10 = (percentfeerate10Obj instanceof Integer) ? (Integer) percentfeerate10Obj : 0;
                                                                      int percentfeerate20 = (percentfeerate20Obj instanceof Integer) ? (Integer) percentfeerate20Obj : 0;
                                                                      int percentfeerate30 = (percentfeerate30Obj instanceof Integer) ? (Integer) percentfeerate30Obj : 0;
                                                                      int percentfeerate100 = (percentfeerate100Obj instanceof Integer) ? (Integer) percentfeerate100Obj : 0;
                                                                      int totalFeerate= (totalFeerateObj instanceof Integer) ? (Integer) totalFeerateObj : 0;
                                                         

                                %>


                                <div class="ms-auto d-flex align-items-center">

                                    <div >
                                        <input type="hidden" name="percentfeerate10" id="percentfeerate10Input" value="<%= percentfeerate10 %>">
                                        <input type="hidden" name="percentfeerate20" id="percentfeerate20Input" value="<%= percentfeerate20 %>">
                                        <input type="hidden" name="percentfeerate30" id="percentfeerate30Input" value="<%= percentfeerate30 %>">
                                        <input type="hidden" name="percentfeerate100" id="percentfeerate100Input" value="<%= percentfeerate100 %>">
                                        <input type="hidden" name="totalFeerate" id="totalFeerateInput" value="<%= totalFeerate %>">
                                        <input type="hidden" name="imageFee" id="imageInputFee">
                                        <input type="hidden" name="statusFilterFee" id="statusFilterFeeInput" value="<%= request.getParameter("statusFilterFee") != null ? request.getParameter("statusFilterFee") : "BothChartFee" %>">

                                        <button class="btn btn-pending" type="submit" onclick="convertCanvasToImageFee()">Tải PDF</button>

                                    </div>
                                    <div class="btn-group position-static">
                                        <button type="button" class="btn border btn-light dropdown-toggle px-4" data-bs-toggle="dropdown" aria-expanded="false">
                                            <%= session.getAttribute("statusFilterFee") != null ? session.getAttribute("statusFilterFee").toString().replace("ChartFee", "") : "Status" %>
                                        </button>                                        <ul class="dropdown-menu">
                                            <li><a class="dropdown-item" href="getdatainsurance?statusFilterFee=ActiveChartFee">Active</a></li>
                                            <li><a class="dropdown-item" href="getdatainsurance?statusFilterFee=InactiveChartFee">Inactive</a></li>
                                            <li><a class="dropdown-item" href="getdatainsurance?statusFilterFee=BothChartFee">Both</a></li>
                                        </ul>
                                    </div>

                                </div>
                            </div>

                            <div class="card-body">
                                <div class="chart-js-container3">
                                    <canvas id="chart2"></canvas>
                                </div>
                            </div>
                        </form>
                    </div>

                    <div style="display: flex; width: 100%">
                        <!--Chart type -->

                        <div class="card chart-box">
                            <form id="pdfForm" action="ChartToPDFServletType" method="post">

                                <div >
                                    <input type="hidden" name="imageType" id="imageInputType">
                                    <button class="btn btn-pending" type="submit" onclick="convertCanvasToImageType()">Tải PDF</button>

                                </div>
                                <div class="card-header bg-transparent py-3 d-flex align-items-center">
                                    <h6 class="mb-0 text-uppercase">Statistic Of Type Insurance</h6>
                                    <%
                                                       

                                                                   Object percentSeloanObj = session.getAttribute("percentSeloan");
                                                                   Object percentUnseloanObj = session.getAttribute("percentUnseloan");
                                                           

                                                                   double percentSeloan = (percentSeloanObj instanceof Double) ? (Double) percentSeloanObj : 0.0;
                                                                   double percentUnseloan = (percentUnseloanObj instanceof Double) ? (Double) percentUnseloanObj : 0.0;
                                                         

                                    %>


                                </div>
                                <div class="card-body">
                                    <div class="chart-js-container3">
                                        <canvas id="chart3"></canvas>
                                    </div>
                                </div>
                            </form>
                        </div>

                        <!--Chart status -->
                        <div class="card chart-box">
                            <form id="pdfForm" action="ChartToPDFServletType1" method="post">
                                <div >
                                    <input type="hidden" name="imageType1" id="imageInputType1">
                                    <button class="btn btn-pending" type="submit" onclick="convertCanvasToImageType1()">Tải PDF</button>

                                </div>
                                <div class="card-header bg-transparent py-3 d-flex align-items-center">
                                    <h6 class="mb-0 text-uppercase">Statistic of Insurance status</h6>

                                    <%
                             
                                                                      Object percentActiveObj = session.getAttribute("percentActive");
                                                                      Object percentInActiveObj = session.getAttribute("percentInActive");
                                                           

                                                                      double percentActive = (percentActiveObj instanceof Double) ? (Double) percentActiveObj : 0.0;
                                                                      double percentInActive = (percentInActiveObj instanceof Double) ? (Double) percentInActiveObj : 0.0;  
                              
                                    %>





                                </div>
                                <div class="card-body">
                                    <div class="chart-js-container3">
                                        <canvas id="chart5"></canvas>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>

                </div>
            </div>
            <!--end row-->

        </main>
        <!--end main content-->


        <!--start overlay-->
        <div class="overlay btn-toggle-menu"></div>
        <!--end overlay-->

        <!-- Search Modal -->
        <div class="modal" id="exampleModal" tabindex="-1">
            <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
                <div class="modal-content">
                    <div class="modal-header gap-2">
                        <div class="position-relative popup-search w-100">
                            <input class="form-control form-control-lg ps-5 border border-3 border-primary" type="search" placeholder="Search">
                            <span class="material-symbols-outlined position-absolute ms-3 translate-middle-y start-0 top-50">search</span>
                        </div>
                        <button type="button" class="btn-close d-xl-none" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="search-list">
                            <p class="mb-1">Html Templates</p>
                            <div class="list-group">
                                <a href="javascript:;" class="list-group-item list-group-item-action active align-items-center d-flex gap-2"><i class="bi bi-filetype-html fs-5"></i>Best Html Templates</a>
                                <a href="javascript:;" class="list-group-item list-group-item-action align-items-center d-flex gap-2"><i class="bi bi-award fs-5"></i>Html5 Templates</a>
                                <a href="javascript:;" class="list-group-item list-group-item-action align-items-center d-flex gap-2"><i class="bi bi-box2-heart fs-5"></i>Responsive Html5 Templates</a>
                                <a href="javascript:;" class="list-group-item list-group-item-action align-items-center d-flex gap-2"><i class="bi bi-camera-video fs-5"></i>eCommerce Html Templates</a>
                            </div>
                            <p class="mb-1 mt-3">Web Designe Company</p>
                            <div class="list-group">
                                <a href="javascript:;" class="list-group-item list-group-item-action align-items-center d-flex gap-2"><i class="bi bi-chat-right-text fs-5"></i>Best Html Templates</a>
                                <a href="javascript:;" class="list-group-item list-group-item-action align-items-center d-flex gap-2"><i class="bi bi-cloud-arrow-down fs-5"></i>Html5 Templates</a>
                                <a href="javascript:;" class="list-group-item list-group-item-action align-items-center d-flex gap-2"><i class="bi bi-columns-gap fs-5"></i>Responsive Html5 Templates</a>
                                <a href="javascript:;" class="list-group-item list-group-item-action align-items-center d-flex gap-2"><i class="bi bi-collection-play fs-5"></i>eCommerce Html Templates</a>
                            </div>
                            <p class="mb-1 mt-3">Software Development</p>
                            <div class="list-group">
                                <a href="javascript:;" class="list-group-item list-group-item-action align-items-center d-flex gap-2"><i class="bi bi-cup-hot fs-5"></i>Best Html Templates</a>
                                <a href="javascript:;" class="list-group-item list-group-item-action align-items-center d-flex gap-2"><i class="bi bi-droplet fs-5"></i>Html5 Templates</a>
                                <a href="javascript:;" class="list-group-item list-group-item-action align-items-center d-flex gap-2"><i class="bi bi-exclamation-triangle fs-5"></i>Responsive Html5 Templates</a>
                                <a href="javascript:;" class="list-group-item list-group-item-action align-items-center d-flex gap-2"><i class="bi bi-eye fs-5"></i>eCommerce Html Templates</a>
                            </div>
                            <p class="mb-1 mt-3">Online Shoping Portals</p>
                            <div class="list-group">
                                <a href="javascript:;" class="list-group-item list-group-item-action align-items-center d-flex gap-2"><i class="bi bi-facebook fs-5"></i>Best Html Templates</a>
                                <a href="javascript:;" class="list-group-item list-group-item-action align-items-center d-flex gap-2"><i class="bi bi-flower2 fs-5"></i>Html5 Templates</a>
                                <a href="javascript:;" class="list-group-item list-group-item-action align-items-center d-flex gap-2"><i class="bi bi-geo-alt fs-5"></i>Responsive Html5 Templates</a>
                                <a href="javascript:;" class="list-group-item list-group-item-action align-items-center d-flex gap-2"><i class="bi bi-github fs-5"></i>eCommerce Html Templates</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <!--start theme customization-->
        <div class="offcanvas offcanvas-end" tabindex="-1" id="ThemeCustomizer" aria-labelledby="ThemeCustomizerLable">
            <div class="offcanvas-header border-bottom">
                <h5 class="offcanvas-title" id="ThemeCustomizerLable">Theme Customizer</h5>
                <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
            </div>
            <div class="offcanvas-body">
                <h6 class="mb-0">Theme Variation</h6>
                <hr>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="inlineRadioOptions" id="LightTheme" value="option1">
                    <label class="form-check-label" for="LightTheme">Light</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="inlineRadioOptions" id="DarkTheme" value="option2" checked="">
                    <label class="form-check-label" for="DarkTheme">Dark</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="inlineRadioOptions" id="SemiDarkTheme" value="option3">
                    <label class="form-check-label" for="SemiDarkTheme">Semi Dark</label>
                </div>
                <hr>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="inlineRadioOptions" id="MinimalTheme" value="option3">
                    <label class="form-check-label" for="MinimalTheme">Minimal Theme</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="inlineRadioOptions" id="ShadowTheme" value="option4">
                    <label class="form-check-label" for="ShadowTheme">Shadow Theme</label>
                </div>

            </div>
        </div>
        <!--end theme customization-->

        <!--plugins-->
        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/plugins/perfect-scrollbar/js/perfect-scrollbar.js"></script>
        <script src="assets/plugins/metismenu/js/metisMenu.min.js"></script>
        <script src="assets/plugins/simplebar/js/simplebar.min.js"></script>
        <script src="assets/plugins/chartjs/chart.min.js"></script>
        <!--        <script src="assets/plugins/chartjs/custom-script.js"></script>-->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <!--BS Scripts-->
        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/main.js"></script>
        <jsp:include page="assets/chartInsurance/chartStaticInsurance.jsp"/>


        <script>
                                        var data7 = [<%= amount1 %>, <%= amount2 %>,<%= amount4 %>, <%= amount6 %>,<%= amount8 %>, <%= amount10 %>];
                                        var ToltalAmount = <%= ToltalAmount %>;
                                        // Chart 21
                                        var data21 = [<%= coverate30 %>, <%= coverate50 %>,<%= coverate60 %>, <%= coverate70 %>,<%= coverate80 %>, <%= coverate100 %>];
                                        var totalCoverate = <%= totalCoverate %>;
                                        // Chart 2
                                        var data2 = [<%= percentfeerate10 %>, <%= percentfeerate20 %>,<%= percentfeerate30 %>, <%= percentfeerate100 %>];
                                        var totalFeerate = <%= totalFeerate %>;
                                        // Chart 3
                                        var labels3 = ['Unsecured Loan', 'Secured Loan'];
                                        var data3 = [<%= percentUnseloan %>, <%= percentSeloan %>];
                                        console.log(data3);
                                        console.log(labels3);
                                        // Chart 5
                                        var labels5 = ['Active', 'Inactive'];
                                        var data5 = [<%= percentActive %>, <%= percentInActive %>];
                                        console.log(data5);
                                        console.log(labels5);

//                                        // drownlist chart2
//                                        function redirectToServletChartFee() {
//                                            var selectBox = document.getElementById("statusFilterFee");
//                                            var selectedValue = selectBox.value;
//                                            console.log(selectedValue);
//                                            if (selectedValue) {
//                                                updateChartFee(selectedValue); // Gọi AJAX để cập nhật
//
//                                            }
//                                        }
//
//                                        function updateChartFee(status) {
//                                            $.ajax({
//                                                url: status,
//                                                type: 'GET',
//                                                dataType: 'json',
//                                                success: function (response) {
//                                                    if (response.error) {
//                                                        toastr.error("No data valiable !!", "Error");
//                                                        myChart2.data.datasets[0].data = [0];
//                                                        myChart2.data.datasets[1].data = [0];
//                                                        myChart2.data.datasets[2].data = [0];
//                                                        myChart2.data.datasets[3].data = [0];
//                                                        myChart2.options.scales.y.max = 0;
//                                                    } else {
//                                                        // Cập nhật dữ liệu biểu đồ
//                                                        myChart2.data.datasets[0].data = [response.data2[0]];
//                                                        myChart2.data.datasets[1].data = [response.data2[1]];
//                                                        myChart2.data.datasets[2].data = [response.data2[2]];
//                                                        myChart2.data.datasets[3].data = [response.data2[3]];
//                                                        myChart2.options.scales.y.max = response.total_fee;
//                                                        // Cập nhật các trường input ẩn
//                                                        document.getElementById("percentfeerate10Input").value = response.data2[0];
//                                                        document.getElementById("percentfeerate20Input").value = response.data2[1];
//                                                        document.getElementById("percentfeerate30Input").value = response.data2[2];
//                                                        document.getElementById("percentfeerate100Input").value = response.data2[3];
//                                                        document.getElementById("totalFeerateInput").value = response.total_fee;
//
//                                                        console.log(response.data2);
//                                                        console.log(response.total_fee);
//                                                    }
//                                                    myChart2.update();
//
//                                                },
//                                                error: function (xhr, status, error) {
//                                                    toastr.error("An error occurred while retrieving data !!", "Error");
//                                                    myChart2.data.datasets[0].data = [0];
//                                                    myChart2.data.datasets[1].data = [0];
//                                                    myChart2.data.datasets[2].data = [0];
//                                                    myChart2.data.datasets[3].data = [0];
//                                                    myChart2.options.scales.y.max = 0;
//                                                    myChart2.update();
//                                                }
//                                            });
//                                        }
//
//
//                                        // drownlist chart21
//                                        function redirectToServletChartCover() {
//                                            var selectBox = document.getElementById("statusFilterCover");
//                                            var selectedValue = selectBox.value;
//                                            console.log(selectedValue);
//                                            if (selectedValue) {
//                                                updateChartCover(selectedValue); // Gọi AJAX để cập nhật
//
//                                            }
//                                        }
//
//                                        function updateChartCover(status) {
//                                            $.ajax({
//                                                url: status,
//                                                type: 'GET',
//                                                dataType: 'json',
//                                                success: function (response) {
//                                                    if (response.error) {
//                                                        toastr.error("No data valiable !!", "Error");
//                                                        myChart6.data.datasets[0].data = [0];
//                                                        myChart6.data.datasets[1].data = [0];
//                                                        myChart6.data.datasets[2].data = [0];
//                                                        myChart6.data.datasets[3].data = [0];
//                                                        myChart6.data.datasets[4].data = [0];
//                                                        myChart6.data.datasets[5].data = [0];
//                                                        myChart6.options.scales.y.max = 0;
//                                                    } else {
//                                                        // Cập nhật dữ liệu biểu đồ
//                                                        myChart6.data.datasets[0].data = [response.data21[0]];
//                                                        myChart6.data.datasets[1].data = [response.data21[1]];
//                                                        myChart6.data.datasets[2].data = [response.data21[2]];
//                                                        myChart6.data.datasets[3].data = [response.data21[3]];
//                                                        myChart6.data.datasets[4].data = [response.data21[4]];
//                                                        myChart6.data.datasets[5].data = [response.data21[5]];
//                                                        myChart6.options.scales.y.max = response.total_Cover;
//
//                                                        // Cập nhật các trường input ẩn
//                                                        document.getElementById("coverate30Input").value = response.data21[0];
//                                                        document.getElementById("coverate50Input").value = response.data21[1];
//                                                        document.getElementById("coverate60Input").value = response.data21[2];
//                                                        document.getElementById("coverate70Input").value = response.data21[3];
//                                                        document.getElementById("coverate80Input").value = response.data21[4];
//                                                        document.getElementById("coverate100Input").value = response.data21[5];
//                                                        document.getElementById("totalCoverateInput").value = response.total_Cover;
//
//                                                        console.log(response.data21);
//                                                        console.log(response.total_Cover);
//                                                    }
//                                                    myChart6.update();
//
//                                                },
//                                                error: function (xhr, status, error) {
//                                                    toastr.error("An error occurred while retrieving data !!", "Error");
//                                                    myChart6.data.datasets[0].data = [0];
//                                                    myChart6.data.datasets[1].data = [0];
//                                                    myChart6.data.datasets[2].data = [0];
//                                                    myChart6.data.datasets[3].data = [0];
//                                                    myChart6.data.datasets[4].data = [0];
//                                                    myChart6.data.datasets[5].data = [0];
//                                                    myChart6.options.scales.y.max = 0;
//                                                    myChart6.update();
//                                                }
//                                            });
//                                        }
//
//
//
//                                        // drownlist chart7
//                                        function redirectToServletChartAmount() {
//                                            var selectBox = document.getElementById("statusFilterAmount");
//                                            var selectedValue = selectBox.value;
//                                            console.log(selectedValue);
//                                            if (selectedValue) {
//                                                updateChartAmount(selectedValue); // Gọi AJAX để cập nhật
//
//                                            }
//                                        }
//
//                                        function updateChartAmount(status) {
//                                            $.ajax({
//                                                url: status,
//                                                type: 'GET',
//                                                dataType: 'json',
//                                                success: function (response) {
//                                                    if (response.error) {
//                                                        toastr.error("No data valiable !!", "Error");
//                                                        myChart7.data.datasets[0].data = [0];
//                                                        myChart7.data.datasets[1].data = [0];
//                                                        myChart7.data.datasets[2].data = [0];
//                                                        myChart7.data.datasets[3].data = [0];
//                                                        myChart7.data.datasets[4].data = [0];
//                                                        myChart7.data.datasets[5].data = [0];
//                                                        myChart7.options.scales.y.max = 0;
//                                                    } else {
//                                                        // Cập nhật dữ liệu biểu đồ
//                                                        myChart7.data.datasets[0].data = [response.data7[0]];
//                                                        myChart7.data.datasets[1].data = [response.data7[1]];
//                                                        myChart7.data.datasets[2].data = [response.data7[2]];
//                                                        myChart7.data.datasets[3].data = [response.data7[3]];
//                                                        myChart7.data.datasets[4].data = [response.data7[4]];
//                                                        myChart7.data.datasets[5].data = [response.data7[5]];
//                                                        myChart7.options.scales.y.max = response.total_Amount;
//
//
//                                                        // Cập nhật các trường input ẩn
//                                                        document.getElementById("amount1Input").value = response.data7[0];
//                                                        document.getElementById("amount2Input").value = response.data7[1];
//                                                        document.getElementById("amount4Input").value = response.data7[2];
//                                                        document.getElementById("amount6Input").value = response.data7[3];
//                                                        document.getElementById("amount8Input").value = response.data7[4];
//                                                        document.getElementById("amount10Input").value = response.data7[5];
//                                                        document.getElementById("ToltalAmountInput").value = response.total_Amount;
//                                                        console.log(response.data7);
//                                                        console.log(response.total_Amount);
//
//                                                    }
//                                                    myChart7.update();
//
//                                                },
//                                                error: function (xhr, status, error) {
//                                                    toastr.error("An error occurred while retrieving data !!", "Error");
//                                                    myChart7.data.datasets[0].data = [0];
//                                                    myChart7.data.datasets[1].data = [0];
//                                                    myChart7.data.datasets[2].data = [0];
//                                                    myChart7.data.datasets[3].data = [0];
//                                                    myChart7.data.datasets[4].data = [0];
//                                                    myChart7.data.datasets[5].data = [0];
//                                                    myChart7.options.scales.y.max = 0;
//                                                    myChart7.update();
//                                                }
//                                            });
//                                        }

                                        function convertCanvasToImage() {
                                            let canvas = document.getElementById("chart7");
                                            if (!canvas) {
                                                console.error("Canvas element not found!");
                                                return;
                                            }

                                            let imageBase64 = canvas.toDataURL("image/png"); // Chuyển canvas thành Base64
                                            document.getElementById("imageInput").value = imageBase64; // Gán vào input ẩn

                                            console.log("Base64 Image:", imageBase64); // Kiểm tra dữ liệu trước khi gửi đi

                                        }

                                        function convertCanvasToImageCover() {
                                            let canvas = document.getElementById("chart21");
                                            if (!canvas) {
                                                console.error("Canvas element not found!");
                                                return;
                                            }

                                            let imageBase64 = canvas.toDataURL("image/png"); // Chuyển canvas thành Base64
                                            document.getElementById("imageInputCover").value = imageBase64; // Gán vào input ẩn

                                            console.log("Base64 Image:", imageBase64); // Kiểm tra dữ liệu trước khi gửi đi

                                        }

                                        function convertCanvasToImageFee() {
                                            let canvas = document.getElementById("chart2");
                                            if (!canvas) {
                                                console.error("Canvas element not found!");
                                                return;
                                            }

                                            let imageBase64 = canvas.toDataURL("image/png"); // Chuyển canvas thành Base64
                                            document.getElementById("imageInputFee").value = imageBase64; // Gán vào input ẩn

                                            console.log("Base64 Image:", imageBase64); // Kiểm tra dữ liệu trước khi gửi đi

                                        }
                                        function convertCanvasToImageType() {
                                            let canvas = document.getElementById("chart3");
                                            if (!canvas) {
                                                console.error("Canvas element not found!");
                                                return;
                                            }

                                            let imageBase64 = canvas.toDataURL("image/png"); // Chuyển canvas thành Base64
                                            document.getElementById("imageInputType").value = imageBase64; // Gán vào input ẩn

                                            console.log("Base64 Image:", imageBase64); // Kiểm tra dữ liệu trước khi gửi đi

                                        }
                                        function convertCanvasToImageType1() {
                                            let canvas = document.getElementById("chart5");
                                            if (!canvas) {
                                                console.error("Canvas element not found!");
                                                return;
                                            }

                                            let imageBase64 = canvas.toDataURL("image/png"); // Chuyển canvas thành Base64
                                            document.getElementById("imageInputType1").value = imageBase64; // Gán vào input ẩn

                                            console.log("Base64 Image:", imageBase64); // Kiểm tra dữ liệu trước khi gửi đi

                                        }
        </script>
    </body>
</html>
