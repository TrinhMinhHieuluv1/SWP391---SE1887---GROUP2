<%-- 
    Document   : home
    Created on : Jan 25, 2025, 10:56:46 PM
    Author     : SCN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.*, java.text.SimpleDateFormat" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="en" data-bs-theme="light">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>TIMI ADMIN</title>

        <!--plugins-->
        <link href="assets/plugins/perfect-scrollbar/css/perfect-scrollbar.css" rel="stylesheet" >
        <link href="assets/plugins/metismenu/css/metisMenu.min.css" rel="stylesheet">
        <link href="assets/plugins/simplebar/css/simplebar.css" rel="stylesheet">
        <!-- loader-->
        <link href="assets/css/pace.min.css" rel="stylesheet">
        <script src="assets/js/pace.min.js"></script>
        <!--Styles-->
        <link href="assets/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
        <link rel="stylesheet" href="assets/css/icons.css" >

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">

        <link href="https://fonts.googleapis.com/css2?family=Noto+Sans:wght@300;400;500;600&display=swap" rel="stylesheet">
        <link href="assets/css/main.css" rel="stylesheet">
        <link href="assets/css/dark-theme.css" rel="stylesheet">
        <link href="assets/css/semi-dark-theme.css" rel="stylesheet">
        <link href="assets/css/minimal-theme.css" rel="stylesheet">
        <link href="assets/css/shadow-theme.css" rel="stylesheet">
        <!-- Favicon -->
        <link rel="shortcut icon" href="assets/images/logo-icon.png" type="image/x-icon">
        <link rel="icon" href="assets/images/logo-icon.png" type="image/x-icon">

        <!-- Toarst -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>


        <style>
            .btn-excel {
                margin-left: 5px;
                display: block;
                width: 150px;
                padding: 10px;
                background-color: #218838;
                color: #fff;
                border: 2px solid #218838; /* Viền xanh đậm */
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

            .btn-excel {
                background-color: #218838; /* Màu xanh lá */
                color: white;
                padding: 6px 12px; /* Thu gọn nút xuất Excel */
                font-size: 14px;
            }

            .dropdown-menu.show {
                background-color: #28a745;
            }

            .widget-icon{
                margin-bottom: 18px;
            }

            #contractFilterForm input[type="date"] {
                width: 120px !important; /* Giảm kích thước input */
                padding: 5px; /* Thu nhỏ phần padding */
                font-size: 14px; /* Giảm kích thước chữ */
            }

            #contractFilterForm label {
                font-size: 14px; /* Giảm kích thước chữ của label */
                margin-right: 5px; /* Giảm khoảng cách với input */
            }

            #contractFilterForm button {
                padding: 6px 10px; /* Thu gọn nút bấm */
                font-size: 14px; /* Giảm kích thước chữ */
            }

            #savingContractFilterForm input[type="date"]{
                width: 120px !important; /* Giảm kích thước input */
                padding: 5px; /* Thu nhỏ phần padding */
                font-size: 14px; /* Giảm kích thước chữ */
            }

            #savingContractFilterForm label {
                font-size: 14px; /* Giảm kích thước chữ của label */
                margin-right: 5px; /* Giảm khoảng cách với input */
            }

            #savingContractFilterForm button {
                padding: 6px 10px; /* Thu gọn nút bấm */
                font-size: 14px; /* Giảm kích thước chữ */
            }

            #loanContractFilterForm input[type="date"]{
                width: 120px !important; /* Giảm kích thước input */
                padding: 5px; /* Thu nhỏ phần padding */
                font-size: 14px; /* Giảm kích thước chữ */
            }

            #loanContractFilterForm label {
                font-size: 14px; /* Giảm kích thước chữ của label */
                margin-right: 5px; /* Giảm khoảng cách với input */
            }

            #loanContractFilterForm button {
                padding: 6px 10px; /* Thu gọn nút bấm */
                font-size: 14px; /* Giảm kích thước chữ */
            }

            .btn-option {
                padding: 5px 10px; /* Thu gọn nút tùy chọn */
            }

            .dropdown-menu {
                background-color: #218838; /* Màu xanh lá */
                padding: 10px;
                border-radius: 8px;
                min-width: 180px;
            }
            .dropdown-menu select {
                width: 100%;
                padding: 6px;
                border-radius: 5px;
                border: none;
                background-color: #218838;
                font-size: 14px;
                color: white;
                cursor: pointer;
                margin-top: 5px;
            }

            .dropdown-menu select:focus {
                outline: none;
                box-shadow: 0 0 5px rgba(0, 0, 0, 0.2);
            }

            .fw-bold{
                font-size: 15px;
            }





        </style>


    </head>
    <body>

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
                        <a href="home.jsp">
                            <div class="parent-icon"><span class="material-symbols-outlined">home</span>
                            </div>
                            <div class="menu-title">Dashboard</div>
                        </a>
                    </li>
                    <li>
                        <a href="/timibank/home">
                            <div class="parent-icon">
                                <span class="material-symbols-outlined">arrow_back</span>
                            </div>
                            <div class="menu-title">Back to home</div>
                        </a>
                    </li>



                    <!--                <li>
                                      <a href="javascript:;" class="has-arrow">
                                        <div class="parent-icon"><span class="material-symbols-outlined">apps</span>
                                        </div>
                                        <div class="menu-title">Application</div>
                                      </a>
                                      <ul>
                                        <li> <a href="app-emailbox.html"><span class="material-symbols-outlined">arrow_right</span>Email</a>
                                        </li>
                                        <li> <a href="app-chat-box.html"><span class="material-symbols-outlined">arrow_right</span>Chat Box</a>
                                        </li>
                                        <li> <a href="app-file-manager.html"><span class="material-symbols-outlined">arrow_right</span>File Manager</a>
                                        </li>
                                        <li> <a href="app-contact-list.html"><span class="material-symbols-outlined">arrow_right</span>Contatcs</a>
                                        </li>
                                        <li> <a href="app-to-do.html"><span class="material-symbols-outlined">arrow_right</span>Todo List</a>
                                        </li>
                                        <li> <a href="app-invoice.html"><span class="material-symbols-outlined">arrow_right</span>Invoice</a>
                                        </li>
                                        <li> <a href="app-fullcalender.html"><span class="material-symbols-outlined">arrow_right</span>Calendar</a>
                                        </li>
                                      </ul>
                                    </li>-->
                    <li class="menu-label">UI Elements</li>
                    <!--                    <li>
                                            <a href="javascript:;" class="has-arrow">
                                                <div class="parent-icon"><span class="material-symbols-outlined">widgets</span>
                                                </div>
                                                <div class="menu-title">Widgets</div>
                                            </a>
                                            <ul>
                                                <li> <a href="widget-data.jsp"><span class="material-symbols-outlined">arrow_right</span>Data Widget</a>
                                                </li>
                                                <li> <a href="widget-static.jsp"><span class="material-symbols-outlined">arrow_right</span>Widget Static</a>
                                                </li>
                                            </ul>
                                        </li>-->


                    <li>
                        <a href="javascript:;" class="has-arrow">
                            <div class="parent-icon"><span class="material-symbols-outlined">shopping_cart</span>
                            </div>
                            <div class="menu-title">Management</div>
                        </a>
                        <ul>
                            <!--                    <li> <a href="ecommerce-add-product.html"><span class="material-symbols-outlined">arrow_right</span>Add Product</a>
                                                </li>
                                                <li> <a href="ecommerce-products.html"><span class="material-symbols-outlined">arrow_right</span>Products</a>
                                                </li>-->


                            <li> <a href="manage_users"><span class="material-symbols-outlined">arrow_right</span>Staffs</a>
                            <li> <a href="manage_customers"><span class="material-symbols-outlined">arrow_right</span>Customers</a>
                            <li> <a href="manage_serviceItems"><span class="material-symbols-outlined">arrow_right</span>Service Items</a>


                                <!--                    </li>
                                                    <li> <a href="ecommerce-customer-details.html"><span class="material-symbols-outlined">arrow_right</span>Customer Details</a>
                                                    </li>
                                                    <li> <a href="ecommerce-orders.html"><span class="material-symbols-outlined">arrow_right</span>Orders</a>
                                                    </li>
                                                    <li> <a href="ecommerce-customer-details.html"><span class="material-symbols-outlined">arrow_right</span>Order Details</a>-->


                            </li>
                        </ul>
                    </li>



                    <!--                <li>
                                      <a class="has-arrow" href="javascript:;">
                                        <div class="parent-icon"><span class="material-symbols-outlined">redeem</span>
                                        </div>
                                        <div class="menu-title">Components</div>
                                      </a>
                                      <ul>
                                        <li> <a href="component-alerts.html"><span class="material-symbols-outlined">arrow_right</span>Alerts</a>
                                        </li>
                                        <li> <a href="component-accordions.html"><span class="material-symbols-outlined">arrow_right</span>Accordions</a>
                                        </li>
                                        <li> <a href="component-badges.html"><span class="material-symbols-outlined">arrow_right</span>Badges</a>
                                        </li>
                                        <li> <a href="component-buttons.html"><span class="material-symbols-outlined">arrow_right</span>Buttons</a>
                                        </li>
                                        <li> <a href="component-cards.html"><span class="material-symbols-outlined">arrow_right</span>Cards</a>
                                        </li>
                                        <li> <a href="component-lightbox.html"><span class="material-symbols-outlined">arrow_right</span>Lightbox</a>
                                        </li>
                                        <li> <a href="component-carousels.html"><span class="material-symbols-outlined">arrow_right</span>Carousels</a>
                                        </li>
                                        <li> <a href="component-list-groups.html"><span class="material-symbols-outlined">arrow_right</span>List Groups</a>
                                        </li>
                                        <li> <a href="component-media-object.html"><span class="material-symbols-outlined">arrow_right</span>Media Objects</a>
                                        </li>
                                        <li> <a href="component-modals.html"><span class="material-symbols-outlined">arrow_right</span>Modals</a>
                                        </li>
                                        <li> <a href="component-navs-tabs.html"><span class="material-symbols-outlined">arrow_right</span>Navs & Tabs</a>
                                        </li>
                                        <li> <a href="component-paginations.html"><span class="material-symbols-outlined">arrow_right</span>Pagination</a>
                                        </li>
                                        <li> <a href="component-popovers-tooltips.html"><span class="material-symbols-outlined">arrow_right</span>Popovers & Tooltips</a>
                                        </li>
                                        <li> <a href="component-progress-bars.html"><span class="material-symbols-outlined">arrow_right</span>Progress</a>
                                        </li>
                                        <li> <a href="component-spinners.html"><span class="material-symbols-outlined">arrow_right</span>Spinners</a>
                                        </li>
                                        <li> <a href="component-notifications.html"><span class="material-symbols-outlined">arrow_right</span>Notifications</a>
                                        </li>
                                      </ul>
                                    </li>-->




                    <!--                <li>
                                      <a class="has-arrow" href="javascript:;">
                                        <div class="parent-icon"><span class="material-symbols-outlined">add_reaction</span>
                                        </div>
                                        <div class="menu-title">Icons</div>
                                      </a>
                                      <ul>
                                        <li> <a href="icons-line-icons.html"><span class="material-symbols-outlined">arrow_right</span>Line Icons</a>
                                        </li>
                                        <li> <a href="icons-boxicons.html"><span class="material-symbols-outlined">arrow_right</span>Boxicons</a>
                                        </li>
                                        <li> <a href="icons-feather-icons.html"><span class="material-symbols-outlined">arrow_right</span>Feather Icons</a>
                                        </li>
                                      </ul>
                                    </li>-->



                    <!--                <li class="menu-label">Forms & Tables</li>
                                    <li>
                                      <a class="has-arrow" href="javascript:;">
                                        <div class="parent-icon"><span class="material-symbols-outlined">diamond</span>
                                        </div>
                                        <div class="menu-title">Forms</div>
                                      </a>
                                      <ul>
                                        <li> <a href="form-elements.html"><span class="material-symbols-outlined">arrow_right</span>Form Elements</a>
                                        </li>
                                        <li> <a href="form-input-group.html"><span class="material-symbols-outlined">arrow_right</span>Input Groups</a>
                                        </li>
                                        <li> <a href="form-radios-and-checkboxes.html"><span class="material-symbols-outlined">arrow_right</span>Radios & Checkboxes</a>
                                        </li>
                                        <li> <a href="form-layouts.html"><span class="material-symbols-outlined">arrow_right</span>Forms Layouts</a>
                                        </li>
                                        <li> <a href="form-validations.html"><span class="material-symbols-outlined">arrow_right</span>Form Validation</a>
                                        </li>
                                        <li> <a href="form-wizard.html"><span class="material-symbols-outlined">arrow_right</span>Form Wizard</a>
                                        </li>
                                        <li> <a href="form-file-upload.html"><span class="material-symbols-outlined">arrow_right</span>File Upload</a>
                                        </li>
                                        <li> <a href="form-date-time-pickes.html"><span class="material-symbols-outlined">arrow_right</span>Date Pickers</a>
                                        </li>
                                        <li> <a href="form-select2.html"><span class="material-symbols-outlined">arrow_right</span>Select2</a>
                                        </li>
                                        <li> <a href="form-repeater.html"><span class="material-symbols-outlined">arrow_right</span>Form Repeater</a>
                                        </li>
                                      </ul>
                                    </li>-->


                    <!--                <li>
                                      <a class="has-arrow" href="javascript:;">
                                        <div class="parent-icon"><span class="material-symbols-outlined">backup_table</span>
                                        </div>
                                        <div class="menu-title">Tables</div>
                                      </a>
                                      <ul>
                                        <li> <a href="table-basic-table.html"><span class="material-symbols-outlined">arrow_right</span>Basic Table</a>
                                        </li>
                                        <li> <a href="table-datatable.html"><span class="material-symbols-outlined">arrow_right</span>Data Table</a>
                                        </li>
                                      </ul>
                                    </li>-->


                    <!--                <li class="menu-label">Pages</li>
                                    <li>
                                      <a class="has-arrow" href="javascript:;">
                                        <div class="parent-icon"><span class="material-symbols-outlined">lock_open</span>
                                        </div>
                                        <div class="menu-title">Authentication</div>
                                      </a>
                                      <ul>
                                        <li><a class="has-arrow" href="javascript:;"><span class="material-symbols-outlined">arrow_right</span>Basic</a>
                                          <ul>
                                            <li><a href="auth-basic-login.html" target="_blank"><span class="material-symbols-outlined">arrow_right</span>Login</a></li>
                                            <li><a href="auth-basic-register.html" target="_blank"><span class="material-symbols-outlined">arrow_right</span>Register</a></li>
                                            <li><a href="auth-basic-forgot-password.html" target="_blank"><span class="material-symbols-outlined">arrow_right</span>Forgot Password</a></li>
                                            <li><a href="auth-basic-reset-password.html" target="_blank"><span class="material-symbols-outlined">arrow_right</span>Reset Password</a></li>
                                          </ul>
                                        </li>
                                        <li><a class="has-arrow" href="javascript:;"><span class="material-symbols-outlined">arrow_right</span>Cover</a>
                                          <ul>
                                            <li><a href="auth-cover-login.html" target="_blank"><span class="material-symbols-outlined">arrow_right</span>Login</a></li>
                                            <li><a href="auth-cover-reset-password.html" target="_blank"><span class="material-symbols-outlined">arrow_right</span>Register</a></li>
                                            <li><a href="auth-cover-forgot-password.html" target="_blank"><span class="material-symbols-outlined">arrow_right</span>Forgot Password</a></li>
                                            <li><a href="auth-cover-reset-password.html" target="_blank"><span class="material-symbols-outlined">arrow_right</span>Reset Password</a></li>
                                          </ul>
                                        </li>
                                        <li><a class="has-arrow" href="javascript:;"><span class="material-symbols-outlined">arrow_right</span>Boxed</a>
                                          <ul>
                                            <li><a href="auth-boxed-login.html" target="_blank"><span class="material-symbols-outlined">arrow_right</span>Login</a></li>
                                            <li><a href="auth-boxed-register.html" target="_blank"><span class="material-symbols-outlined">arrow_right</span>Register</a></li>
                                            <li><a href="auth-boxed-forgot-password.html" target="_blank"><span class="material-symbols-outlined">arrow_right</span>Forgot Password</a></li>
                                            <li><a href="auth-boxed-reset-password.html" target="_blank"><span class="material-symbols-outlined">arrow_right</span>Reset Password</a></li>
                                          </ul>
                                        </li>
                                      </ul>
                                    </li>-->


                    <!--                                    <li>
                                                          <a href="user-profile.html">
                                                            <div class="parent-icon"><span class="material-symbols-outlined">account_circle</span>
                                                            </div>
                                                            <div class="menu-title">User Profile</div>
                                                          </a>
                                                        </li>
                                                        <li>
                                                          <a href="timeline.html">
                                                            <div class="parent-icon"><span class="material-symbols-outlined">hotel_class</span>
                                                            </div>
                                                            <div class="menu-title">Timeline</div>
                                                          </a>
                                                        </li>
                                                        <li>
                                                          <a class="has-arrow" href="javascript:;">
                                                            <div class="parent-icon"><span class="material-symbols-outlined">warning</span>
                                                            </div>
                                                            <div class="menu-title">Errors</div>
                                                          </a>
                                                          <ul>
                                                            <li> <a href="pages-error-403.html" target="_blank"><span class="material-symbols-outlined">arrow_right</span>403 Error</a>
                                                            </li>
                                                            <li> <a href="pages-error-404.html" target="_blank"><span class="material-symbols-outlined">arrow_right</span>404 Error</a>
                                                            </li>
                                                            <li> <a href="pages-error-500.html" target="_blank"><span class="material-symbols-outlined">arrow_right</span>500 Error</a>
                                                            </li>
                                                            <li> <a href="pages-coming-soon.html" target="_blank"><span class="material-symbols-outlined">arrow_right</span>Coming Soon</a>
                                                            </li>
                                                          </ul>
                                                        </li>
                                                        <li>
                                                          <a href="faq.html">
                                                            <div class="parent-icon"><span class="material-symbols-outlined">call</span>
                                                            </div>
                                                            <div class="menu-title">FAQ</div>
                                                          </a>
                                                        </li>
                                                        <li>
                                                          <a href="pricing-table.html">
                                                            <div class="parent-icon"><span class="material-symbols-outlined">currency_bitcoin</span>
                                                            </div>
                                                            <div class="menu-title">Pricing</div>
                                                          </a>
                                                        </li>-->


                    <li class="menu-label">Charts & Maps</li>
                    <li>
                        <a class="has-arrow" href="javascript:;">
                            <div class="parent-icon"><span class="material-symbols-outlined">monitoring</span>
                            </div>
                            <div class="menu-title">Charts</div>
                        </a>
                        <ul>
                            <!--                            <li> <a href="charts-apex.jsp"><span class="material-symbols-outlined">arrow_right</span>Apex</a>
                                                        </li>-->
                            <li> <a href="getData_Cus"><span class="material-symbols-outlined">arrow_right</span>Statistic of customers</a>
                            </li>
                        </ul>
                    </li>


                    <!--                                    <li>
                                                          <a class="has-arrow" href="javascript:;">
                                                            <div class="parent-icon"><span class="material-symbols-outlined">distance</span>
                                                            </div>
                                                            <div class="menu-title">Maps</div>
                                                          </a>
                                                          <ul>
                                                            <li> <a href="map-google-maps.html"><span class="material-symbols-outlined">arrow_right</span>Google Maps</a>
                                                            </li>
                                                            <li> <a href="map-vector-maps.html"><span class="material-symbols-outlined">arrow_right</span>Vector Maps</a>
                                                            </li>
                                                          </ul>
                                                        </li>
                                                        <li class="menu-label">Others</li>
                                                        <li>
                                                          <a class="has-arrow" href="javascript:;">
                                                            <div class="parent-icon"><span class="material-symbols-outlined">toc</span>
                                                            </div>
                                                            <div class="menu-title">Menu Levels</div>
                                                          </a>
                                                          <ul>
                                                            <li> <a class="has-arrow" href="javascript:;"><span class="material-symbols-outlined">arrow_right</span>Level One</a>
                                                              <ul>
                                                                <li> <a class="has-arrow" href="javascript:;"><span class="material-symbols-outlined">arrow_right</span>Level Two</a>
                                                                  <ul>
                                                                    <li> <a href="javascript:;"><span class="material-symbols-outlined">arrow_right</span>Level Three</a>
                                                                    </li>
                                                                  </ul>
                                                                </li>
                                                              </ul>
                                                            </li>
                                                          </ul>
                                                        </li>
                                                        <li>
                                                          <a href="javascript:;">
                                                            <div class="parent-icon"><span class="material-symbols-outlined">receipt_long</span>
                                                            </div>
                                                            <div class="menu-title">Documentation</div>
                                                          </a>
                                                        </li>
                                                        <li>
                                                          <a href="javascript:;">
                                                            <div class="parent-icon"><span class="material-symbols-outlined">shop</span>
                                                            </div>
                                                            <div class="menu-title">Support</div>
                                                          </a>
                                                        </li>-->
                </ul>
                <!--end navigation-->


            </div>

            <div class="sidebar-bottom dropdown dropup-center dropup">
                <div class="dropdown-toggle d-flex align-items-center px-3 gap-3 w-100 h-100" data-bs-toggle="dropdown">
                    <div class="user-img">
                        <img src="${sessionScope.account.getImage()}" alt="User Image">

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

            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-2 row-cols-xl-4 row-cols-xxl-4">
                <div class="col">
                    <div class="card radius-10 border-0 border-start border-primary border-4">
                        <div class="card-body">
                            <div class="d-flex align-items-center">
                                <div class="">
                                    <p class="mb-1">Total Contracts</p>
                                    <h4 class="mb-0 text-primary">${totalOfContract}</h4>
                                </div>
                                <div class="ms-auto widget-icon bg-primary text-white">
                                    <i class="bi bi-basket2-fill"></i>
                                </div>
                            </div>
                            <div class="progress mt-3" style="height: 4.5px;">
                                <div class="progress-bar" role="progressbar" style="width: 75%;" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card radius-10 border-0 border-start border-success border-4">
                        <div class="card-body">
                            <div class="d-flex align-items-center">
                                <div class="">
                                    <p class="mb-1">Total Revenue</p>
                                    <h4 class="mb-0 text-success">${totalRevenue}</h4>
                                </div>
                                <div class="ms-auto widget-icon bg-success text-white">
                                    <i class="bi bi-currency-dollar"></i>
                                </div>
                            </div>
                            <div class="progress mt-3" style="height: 4.5px;">
                                <div class="progress-bar bg-success" role="progressbar" style="width: 75%;" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card radius-10 border-0 border-start border-danger border-4">
                        <div class="card-body">
                            <div class="d-flex align-items-center">
                                <div class="">
                                    <p class="mb-1">Bounce Rate</p>
                                    <h4 class="mb-0 text-danger">
                                        <fmt:formatNumber value="${bounceRate}" maxFractionDigits="1" minFractionDigits="1"/>%
                                    </h4>
                                </div>
                                <div class="ms-auto widget-icon bg-danger text-white">
                                    <i class="bi bi-graph-down-arrow"></i>
                                </div>
                            </div>
                            <div class="progress mt-3" style="height: 4.5px;">
                                <div class="progress-bar bg-danger" role="progressbar" style="width: 75%;" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card radius-10 border-0 border-start border-warning border-4">
                        <div class="card-body">
                            <div class="d-flex align-items-center">
                                <div class="">
                                    <p class="mb-1">New Customers</p>
                                    <h4 class="mb-0 text-warning">${totalOfNewCus}</h4>
                                </div>
                                <div class="ms-auto widget-icon bg-warning text-dark">
                                    <a href="getData_Cus" class="text-dark">
                                        <i class="bi bi-people-fill"></i>
                                    </a>
                                </div>
                            </div>
                            <div class="progress mt-3" style="height: 4.5px;">
                                <div class="progress-bar bg-warning" role="progressbar" style="width: 75%;" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div><!--end row-->


            <div class="row">
                <div class="col-lg-8">
                    <div class="card">
                        <div class="card-header bg-transparent">


                            <div class="d-flex align-items-center">

                                <div class="">
                                    <h6 class="mb-0 fw-bold">Statistic of contract</h6>
                                </div>


                                <form action="home" id="contractFilterForm" class="ms-auto me-2 d-flex align-items-center">
                                    <label for="fromDate" class="me-1">From:</label>
                                    <input type="date" value="${fromDate}" id="fromDate" name="fromDate" class="form-control me-2">
                                    <label for="toDate" class="me-1">To:</label>
                                    <input type="date" value="${toDate}" id="toDate" name="toDate" class="form-control me-2">
                                    <input name="urlToServletContract" id="urlToSvlContract" type="hidden" value="${urlToServletContract}">
                                    <button type="submit" class="btn btn-primary">Filter</button>
                                </form>

                                <%                       
                                                       String labelsContract = (String) session.getAttribute("labelsContract");
                                                       String dataContract = (String) session.getAttribute("dataContract");
                                                       String percentDataContract = (String) session.getAttribute("percentDataContract");
                                                       String titleOfX = (String) session.getAttribute("titleOfX");
                                                       String fileDate = new SimpleDateFormat("yyyyMMdd").format(new java.util.Date());                           
                                %>

                                <div class="dropdown">
                                    <button type="button" class="btn-option dropdown-toggle dropdown-toggle-nocaret cursor-pointer" data-bs-toggle="dropdown">
                                        <i class="bi bi-three-dots fs-4"></i>
                                    </button>

                                    <ul class="dropdown-menu">
                                        <li>
                                            <form action="export_excel" method="post" class="d-flex align-items-center gap-2">
                                                <input type="hidden" name="dataRangeType" value="Contract Data Range">
                                                <input type="hidden" name="labels" value="<%= (labelsContract == null || labelsContract.isEmpty()) ? "null" : labelsContract.replaceAll("'", "") %>">
                                                <input type="hidden" name="data" value="<%= (dataContract == null || dataContract.isEmpty()) ? "null" : dataContract %>">
                                                <input type="hidden" name="percentages" value="<%= (percentDataContract == null || percentDataContract.isEmpty()) ? "null" : percentDataContract %>">
                                                <input type="hidden" name="fileDate" value="<%= fileDate %>">

                                                <button type="submit" class="btn-excel">Export to Excel</button>
                                            </form>
                                        </li>
                                        <li>

                                            <select name="typeOfContract" id="typeOfChartFilter1" class="form-select" onchange="redirectToTypeOfChart1()">
                                                <option value="" disabled selected>Choose a type</option>
                                                <option value="allTypeContract">All</option>
                                                <option value="chartSaving">Saving</option>
                                                <option value="chartSecuredLoan">Secured Loan</option>
                                                <option value="chartUnsecuredLoan">Unsecured Loan</option>
                                            </select>

                                        </li>
                                    </ul>
                                </div>

                            </div>

                        </div>

                        <div class="card-body">
                            <div id="chart1"></div>
                        </div>
                    </div>
                </div>


                <div class="col-lg-4">
                    <div class="card">
                        <div class="card-header bg-transparent">
                            <div class="d-flex align-items-center">
                                <div class="">
                                    <h6 class="mb-0 fw-bold">Statistic of feedback</h6>
                                </div>
                                <%
                                    Object dataOfChartFbObj = session.getAttribute("dataOfChartFb");
                                    String dataOfChartFb = (dataOfChartFbObj instanceof String) ? (String) dataOfChartFbObj : "0,0,0,0,0"; 
                                        
                                    Object percent1Obj = session.getAttribute("percent1");
                                    Object percent2Obj = session.getAttribute("percent2");
                                    Object percent3Obj = session.getAttribute("percent3");
                                    Object percent4Obj = session.getAttribute("percent4");
                                    Object percent5Obj = session.getAttribute("percent5");

                                    double percent1 = (percent1Obj instanceof Double) ? (Double) percent1Obj : 0.0;
                                    double percent2 = (percent2Obj instanceof Double) ? (Double) percent2Obj : 0.0;
                                    double percent3 = (percent3Obj instanceof Double) ? (Double) percent3Obj : 0.0;
                                    double percent4 = (percent4Obj instanceof Double) ? (Double) percent4Obj : 0.0;
                                    double percent5 = (percent5Obj instanceof Double) ? (Double) percent5Obj : 0.0;
                                

                                %>
                                <div class="dropdown ms-auto">
                                    <button type="button" class="btn-option dropdown-toggle dropdown-toggle-nocaret cursor-pointer" data-bs-toggle="dropdown"><i class="bi bi-three-dots fs-4"></i>
                                    </button>
                                    <ul class="dropdown-menu">

                                        <li>
                                            <form action="export_excel" method="post">
                                                <input type="hidden" name="dataRangeType" value="FeedBack Range">
                                                <input type="hidden" name="labels" value="1 Star, 2 Star, 3 Star, 4 Star, 5 Star">
                                                <input type="hidden" name="data" value="<%= dataOfChartFb %>">
                                                <input type="hidden" name="percentages" value="<%= percent1 + "," + percent2 + "," + percent3 + "," + percent4 + "," + percent5 %>">
                                                <input type="hidden" name="fileDate" value="<%= fileDate %>">
                                                <button type="submit" class="btn-excel">Export to Excel</button>
                                            </form>
                                        </li>
                                        <!--                                        <li><a class="dropdown-item" href="javascript:;">Another action</a>
                                                                                </li>
                                                                                <li>
                                                                                    <hr class="dropdown-divider">
                                                                                </li>
                                                                                <li><a class="dropdown-item" href="javascript:;">Something else here</a>
                                                                                </li>-->
                                    </ul>
                                </div>

                            </div>
                        </div>


                        <div class="card-body">
                            <div id="chart2"></div>
                        </div>


                        <ul class="list-group list-group-flush mb-0">


                            <li class="list-group-item border-top d-flex justify-content-between align-items-center bg-transparent">
                                ⭐⭐⭐⭐⭐<span class="badge bg-success rounded-pill">${percent5} %</span>
                            </li>
                            <li class="list-group-item d-flex justify-content-between align-items-center bg-transparent">
                                ⭐⭐⭐⭐<span class="badge bg-success rounded-pill">${percent4} %</span>
                            </li>
                            <li class="list-group-item d-flex justify-content-between align-items-center bg-transparent">
                                ⭐⭐⭐<span class="badge bg-success rounded-pill">${percent3} %</span>
                            </li>
                            <li class="list-group-item d-flex justify-content-between align-items-center bg-transparent">
                                ⭐⭐<span class="badge bg-success rounded-pill">${percent2} %</span>
                            </li>
                            <li class="list-group-item d-flex justify-content-between align-items-center bg-transparent">
                                ⭐<span class="badge bg-success rounded-pill">${percent1} %</span>
                            </li>
                        </ul>


                    </div>
                </div>

            </div><!--end row-->


            <!--            <div class="row">
                            <div class="col-12 col-lg-6 col-xl-4 d-flex">
                                <div class="card w-100">
                                    <div class="card-header bg-transparent">
                                        <div class="d-flex align-items-center">
                                            <div class="">
                                                <h6 class="mb-0 fw-bold">Team Members</h6>
                                            </div>
                                            <div class="dropdown ms-auto">
                                                <button type="button" class="btn-option dropdown-toggle dropdown-toggle-nocaret cursor-pointer" data-bs-toggle="dropdown"><i class="bi bi-three-dots fs-4"></i>
                                                </button>
                                                <ul class="dropdown-menu">
                                                    <li><a class="dropdown-item" href="javascript:;">Action</a>
                                                    </li>
                                                    <li><a class="dropdown-item" href="javascript:;">Another action</a>
                                                    </li>
                                                    <li>
                                                        <hr class="dropdown-divider">
                                                    </li>
                                                    <li><a class="dropdown-item" href="javascript:;">Something else here</a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <div class="team-list">
                                            <div class="d-flex align-items-center gap-3">
                                                <div class="">
                                                    <img src="assets/images/avatars/01.png" alt="" width="50" height="50" class="rounded-circle">
                                                </div>
                                                <div class="flex-grow-1">
                                                    <h6 class="mb-1 fw-bold">John Michael</h6>
                                                    <span class="badge bg-success bg-success-subtle text-success border border-opacity-25 border-success">ONLINE</span>
                                                </div>
                                                <div class="">
                                                    <button class="btn btn-outline-primary rounded-5 btn-sm px-3">Add</button>
                                                </div>
                                            </div>
                                            <hr>
                                            <div class="d-flex align-items-center gap-3">
                                                <div class="">
                                                    <img src="assets/images/avatars/02.png" alt="" width="50" height="50" class="rounded-circle">
                                                </div>
                                                <div class="flex-grow-1">
                                                    <h6 class="mb-1 fw-bold">Samantha Ivy</h6>
                                                    <span class="badge bg-danger-subtle text-danger border border-opacity-25 border-danger">IN MEETING</span>
                                                </div>
                                                <div class="">
                                                    <button class="btn btn-outline-primary rounded-5 btn-sm px-3">Add</button>
                                                </div>
                                            </div>
                                            <hr>
                                            <div class="d-flex align-items-center gap-3">
                                                <div class="">
                                                    <img src="assets/images/avatars/03.png" alt="" width="50" height="50" class="rounded-circle">
                                                </div>
                                                <div class="flex-grow-1">
                                                    <h6 class="mb-1 fw-bold">Alex Smith</h6>
                                                    <span class="badge bg-warning-subtle text-warning border border-opacity-25 border-warning">OFFLINE</span>
                                                </div>
                                                <div class="">
                                                    <button class="btn btn-outline-primary rounded-5 btn-sm px-3">Add</button>
                                                </div>
                                            </div>
                                            <hr>
                                            <div class="d-flex align-items-center gap-3">
                                                <div class="">
                                                    <img src="assets/images/avatars/04.png" alt="" width="50" height="50" class="rounded-circle">
                                                </div>
                                                <div class="flex-grow-1">
                                                    <h6 class="mb-1 fw-bold">Alex Smith</h6>
                                                    <span class="badge bg-danger-subtle text-danger border border-opacity-25 border-danger">OFFLINE</span>
                                                </div>
                                                <div class="">
                                                    <button class="btn btn-outline-primary rounded-5 btn-sm px-3">Add</button>
                                                </div>
                                            </div>
                                            <hr>
                                            <div class="d-flex align-items-center gap-3">
                                                <div class="">
                                                    <img src="assets/images/avatars/02.png" alt="" width="50" height="50" class="rounded-circle">
                                                </div>
                                                <div class="flex-grow-1">
                                                    <h6 class="mb-1 fw-bold">Samantha</h6>
                                                    <span class="badge bg-success-subtle text-success border border-opacity-25 border-success">IN MEETING</span>
                                                </div>
                                                <div class="">
                                                    <button class="btn btn-outline-primary rounded-5 btn-sm px-3">Add</button>
                                                </div>
                                            </div>
            
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12 col-lg-6 col-xl-4 d-flex">
                                <div class="card w-100">
                                    <div class="card-header bg-transparent">
                                        <div class="d-flex align-items-center">
                                            <div class="">
                                                <h6 class="mb-0 fw-bold">To do list</h6>
                                            </div>
                                            <div class="dropdown ms-auto">
                                                <button type="button" class="btn-option dropdown-toggle dropdown-toggle-nocaret cursor-pointer" data-bs-toggle="dropdown"><i class="bi bi-three-dots fs-4"></i>
                                                </button>
                                                <ul class="dropdown-menu">
                                                    <li><a class="dropdown-item" href="javascript:;">Action</a>
                                                    </li>
                                                    <li><a class="dropdown-item" href="javascript:;">Another action</a>
                                                    </li>
                                                    <li>
                                                        <hr class="dropdown-divider">
                                                    </li>
                                                    <li><a class="dropdown-item" href="javascript:;">Something else here</a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <div class="team-list">
                                            <div class="d-flex align-items-center gap-3 border-start border-success border-4 border-0 px-2 py-1">
                                                <div class="flex-grow-1">
                                                    <h6 class="mb-1 fw-bold">Meeeting with John</h6>
                                                    <span class="">10:56 PM</span>
                                                </div>
                                                <div class="form-check form-switch form-check-success border-0">
                                                    <input class="form-check-input border-1" type="checkbox" role="switch" checked="">
                                                </div>
                                            </div>
                                            <hr>
                                            <div class="d-flex align-items-center gap-3 border-start border-danger border-4 border-0 px-2 py-1">
                                                <div class="flex-grow-1">
                                                    <h6 class="mb-1 fw-bold">Meeeting with John</h6>
                                                    <span class="">10:56 PM</span>
                                                </div>
                                                <div class="form-check form-switch form-check-danger border-0">
                                                    <input class="form-check-input border-1" type="checkbox" role="switch" checked="">
                                                </div>
                                            </div>
                                            <hr>
                                            <div class="d-flex align-items-center gap-3 border-start border-primary border-4 border-0 px-2 py-1">
                                                <div class="flex-grow-1">
                                                    <h6 class="mb-1 fw-bold">Meeeting with John</h6>
                                                    <span class="">10:56 PM</span>
                                                </div>
                                                <div class="form-check form-switch form-check-primary border-0">
                                                    <input class="form-check-input border-1" type="checkbox" role="switch" checked="">
                                                </div>
                                            </div>
                                            <hr>
                                            <div class="d-flex align-items-center gap-3 border-start border-warning border-4 border-0 px-2 py-1">
                                                <div class="flex-grow-1">
                                                    <h6 class="mb-1 fw-bold">Meeeting with John</h6>
                                                    <span class="">10:56 PM</span>
                                                </div>
                                                <div class="form-check form-switch form-check-warning border-0">
                                                    <input class="form-check-input border-1" type="checkbox" role="switch" checked="">
                                                </div>
                                            </div>
                                            <hr>
                                            <div class="d-flex align-items-center gap-3 border-start border-info border-4 border-0 px-2 py-1">
                                                <div class="flex-grow-1">
                                                    <h6 class="mb-1 fw-bold">Meeeting with John</h6>
                                                    <span class="">10:56 PM</span>
                                                </div>
                                                <div class="form-check form-switch form-check-info border-0">
                                                    <input class="form-check-input border-1" type="checkbox" role="switch" checked="">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12 col-lg-12 col-xl-4 d-flex">
                                <div class="card w-100">
                                    <div class="card-header bg-transparent">
                                        <div class="d-flex align-items-center">
                                            <div class="">
                                                <h6 class="mb-0 fw-bold">Projects</h6>
                                            </div>
                                            <div class="dropdown ms-auto">
                                                <button type="button" class="btn-option dropdown-toggle dropdown-toggle-nocaret cursor-pointer" data-bs-toggle="dropdown"><i class="bi bi-three-dots fs-4"></i>
                                                </button>
                                                <ul class="dropdown-menu">
                                                    <li><a class="dropdown-item" href="javascript:;">Action</a>
                                                    </li>
                                                    <li><a class="dropdown-item" href="javascript:;">Another action</a>
                                                    </li>
                                                    <li>
                                                        <hr class="dropdown-divider">
                                                    </li>
                                                    <li><a class="dropdown-item" href="javascript:;">Something else here</a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <div class="team-list">
                                            <div class="d-flex align-items-center gap-3">
                                                <div class="widget-icon bg-transparent border rounded-3">
                                                    <img src="assets/images/icons/apple.png" alt="" width="30">
                                                </div>
                                                <div class="flex-grow-1">
                                                    <p class="mb-2 fw-bold">Angular 12 Dashboard</p>
                                                    <div class="progress" style="height: 5px;">
                                                        <div class="progress-bar" role="progressbar" style="width: 75%;" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100"></div>
                                                    </div>
                                                </div>
                                            </div>
                                            <hr>
                                            <div class="d-flex align-items-center gap-3">
                                                <div class="widget-icon bg-transparent border rounded-3">
                                                    <img src="assets/images/icons/bootstrap.png" alt="" width="30">
                                                </div>
                                                <div class="flex-grow-1">
                                                    <p class="mb-2 fw-bold">Angular 12 Dashboard</p>
                                                    <div class="progress" style="height: 5px;">
                                                        <div class="progress-bar" role="progressbar" style="width: 70%;" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100"></div>
                                                    </div>
                                                </div>
                                            </div>
                                            <hr>
                                            <div class="d-flex align-items-center gap-3">
                                                <div class="widget-icon bg-transparent border rounded-3">
                                                    <img src="assets/images/icons/google-2.png" alt="" width="30">
                                                </div>
                                                <div class="flex-grow-1">
                                                    <p class="mb-2 fw-bold">Angular 12 Dashboard</p>
                                                    <div class="progress" style="height: 5px;">
                                                        <div class="progress-bar" role="progressbar" style="width: 65%;" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100"></div>
                                                    </div>
                                                </div>
                                            </div>
                                            <hr>
                                            <div class="d-flex align-items-center gap-3">
                                                <div class="widget-icon bg-transparent border rounded-3">
                                                    <img src="assets/images/icons/spotify.png" alt="" width="30">
                                                </div>
                                                <div class="flex-grow-1">
                                                    <p class="mb-2 fw-bold">Angular 12 Dashboard</p>
                                                    <div class="progress" style="height: 5px;">
                                                        <div class="progress-bar" role="progressbar" style="width: 55%;" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100"></div>
                                                    </div>
                                                </div>
                                            </div>
                                            <hr>
                                            <div class="d-flex align-items-center gap-3">
                                                <div class="widget-icon bg-transparent border rounded-3">
                                                    <img src="assets/images/icons/outlook.png" alt="" width="30">
                                                </div>
                                                <div class="flex-grow-1">
                                                    <p class="mb-2 fw-bold">Angular 12 Dashboard</p>
                                                    <div class="progress" style="height: 5px;">
                                                        <div class="progress-bar" role="progressbar" style="width: 45%;" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100"></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>end row-->


            <div class="row">


                <div class="col-12 col-lg-12 col-xl-6">
                    <div class="card">
                        <div class="card-header bg-transparent">

                            <div class="d-flex align-items-center">

                                <div class="">
                                    <h6 class="mb-0 fw-bold">Statistic of revenue (Saving)</h6>
                                </div>

                                <form id="savingContractFilterForm" class="ms-auto me-2 d-flex align-items-center">
                                    <label for="fromDateSaving" class="me-1">From:</label>
                                    <input type="date" value="${fromDate}" id="fromDateSaving" name="fromDateSaving" class="form-control me-2">

                                    <label for="toDateSaving" class="me-1">To:</label>
                                    <input type="date" value="${toDate}" id="toDateSaving" name="toDateSaving" class="form-control me-2">

                                    <button type="submit" class="btn btn-primary">Filter</button>
                                </form>

                                <%                       
                                                       String labelsSaving = (String) session.getAttribute("labelsSavingContract");
                                                       String dataSaving = (String) session.getAttribute("dataSavingContract");
                                                       String percentDataSaving = (String) session.getAttribute("percentDataSavingContract");
                                                     
                                %>


                                <div class="dropdown ms-auto">
                                    <button type="button" class="btn-option dropdown-toggle dropdown-toggle-nocaret cursor-pointer" data-bs-toggle="dropdown"><i class="bi bi-three-dots fs-4"></i>
                                    </button>
                                    <ul class="dropdown-menu">

                                        <li>
                                            <form action="export_excel" method="post" class="d-flex align-items-center gap-2">
                                                <input type="hidden" name="dataRangeType" value="Revenue Data Range">
                                                <input type="hidden" name="labels" value="<%= (labelsSaving == null || labelsSaving.isEmpty()) ? "null" : labelsSaving.replaceAll("'", "") %>">
                                                <input type="hidden" name="data" value="<%= (dataSaving == null || dataSaving.isEmpty()) ? "null" : dataSaving %>">
                                                <input type="hidden" name="percentages" value="<%= (percentDataSaving == null || percentDataSaving.isEmpty()) ? "null" : percentDataSaving %>">
                                                <input type="hidden" name="fileDate" value="<%= fileDate %>">

                                                <button type="submit" class="btn-excel">Export to Excel</button>
                                            </form>
                                        </li>

                                    </ul>
                                </div>
                            </div>
                        </div>



                        <div class="card-body">
                            <div id="chart3"></div>
                        </div>


                    </div>
                </div>



                <div class="col-12 col-lg-12 col-xl-6">
                    <div class="card">
                        <div class="card-header bg-transparent">
                            <div class="d-flex align-items-center">
                                <div class="">
                                    <h6 class="mb-0 fw-bold">Statistic of revenue (Loan)</h6>
                                </div>

                                <form action="home" id="loanContractFilterForm" class="ms-auto me-2 d-flex align-items-center">
                                    <label for="fromDateLoan" class="me-1">From:</label>
                                    <input type="date" value="${fromDate}" id="fromDateLoan" name="fromDateLoan" class="form-control me-2">

                                    <label for="toDateLoan" class="me-1">To:</label>
                                    <input type="date" value="${toDate}" id="toDateLoan" name="toDateLoan" class="form-control me-2">

                                    <button type="submit" class="btn btn-primary">Filter</button>
                                </form>

                                <%                       
                                                    String labelsLoan = (String) session.getAttribute("labelsLoanContract");
                                                    String dataLoan = (String) session.getAttribute("dataLoanContract");
                                                    String percentDataLoan = (String) session.getAttribute("percentDataLoanContract");
                                                     
                                %>


                                <div class="dropdown ms-auto">
                                    <button type="button" class="btn-option dropdown-toggle dropdown-toggle-nocaret cursor-pointer" data-bs-toggle="dropdown"><i class="bi bi-three-dots fs-4"></i>
                                    </button>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <form action="export_excel" method="post" class="d-flex align-items-center gap-2">
                                                <input type="hidden" name="dataRangeType" value="Revenue Data Range">
                                                <input type="hidden" name="labels" value="<%= (labelsLoan == null || labelsLoan.isEmpty()) ? "null" : labelsLoan.replaceAll("'", "") %>">
                                                <input type="hidden" name="data" value="<%= (dataLoan == null || dataLoan.isEmpty()) ? "null" : dataLoan %>">
                                                <input type="hidden" name="percentages" value="<%= (percentDataLoan == null || percentDataLoan.isEmpty()) ? "null" : percentDataLoan %>">
                                                <input type="hidden" name="fileDate" value="<%= fileDate %>">

                                                <button type="submit" class="btn-excel">Export to Excel</button>
                                            </form>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="card-body">
                            <div id="chart4"></div>
                        </div>
                    </div>
                </div>
            </div><!--end row-->


            <!--            <div class="card">
                            <div class="card-body">
                                <div class="customer-table">
                                    <div class="table-responsive white-space-nowrap">
                                        <table class="table align-middle">
                                            <thead class="table-light">
                                                <tr>
                                                    <th>
                                                        <input class="form-check-input" type="checkbox">
                                                    </th>
                                                    <th>Order Id</th>
                                                    <th>Price</th>
                                                    <th>Customer</th>
                                                    <th>Payment Status</th>
                                                    <th>Completed Payment</th>
                                                    <th>Delivery Type</th>
                                                    <th>Date</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>
                                                        <input class="form-check-input" type="checkbox">
                                                    </td>
                                                    <td>
                                                        <a href="javascript:;">#2415</a>
                                                    </td>
                                                    <td>$98</td>
                                                    <td>
                                                        <a class="d-flex align-items-center gap-3" href="javascript:;">
                                                            <div class="customer-pic">
                                                                <img src="assets/images/avatars/01.png" class="rounded-circle" width="40" height="40" alt="">
                                                            </div>
                                                            <p class="mb-0 customer-name fw-bold">Andrew Carry</p>
                                                        </a>
                                                    </td>
                                                    <td><span class="lable-table bg-success-subtle text-success rounded border border-success-subtle font-text2 fw-bold">Completed<i class="bi bi-check2 ms-2"></i></span></td>
                                                    <td><span class="lable-table bg-danger-subtle text-danger rounded border border-danger-subtle font-text2 fw-bold">Failed<i class="bi bi-x-lg ms-2"></i></span></td>
                                                    <td>Cash on delivery</td>
                                                    <td>Nov 12, 10:45 PM</td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <input class="form-check-input" type="checkbox">
                                                    </td>
                                                    <td>
                                                        <a href="javascript:;">#7845</a>
                                                    </td>
                                                    <td>$110</td>
                                                    <td>
                                                        <a class="d-flex align-items-center gap-3" href="javascript:;">
                                                            <div class="customer-pic">
                                                                <img src="assets/images/avatars/02.png" class="rounded-circle" width="40" height="40" alt="">
                                                            </div>
                                                            <p class="mb-0 customer-name fw-bold">Andrew Carry</p>
                                                        </a>
                                                    </td>
                                                    <td><span class="lable-table bg-warning-subtle text-warning rounded border border-warning-subtle font-text2 fw-bold">Pending<i class="bi bi-info-circle ms-2"></i></span></td>
                                                    <td><span class="lable-table bg-primary-subtle text-primary rounded border border-primary-subtle font-text2 fw-bold">Completed<i class="bi bi-check2-all ms-2"></i></span></td>
                                                    <td>Cash on delivery</td>
                                                    <td>Nov 12, 10:45 PM</td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <input class="form-check-input" type="checkbox">
                                                    </td>
                                                    <td>
                                                        <a href="javascript:;">#5674</a>
                                                    </td>
                                                    <td>$86</td>
                                                    <td>
                                                        <a class="d-flex align-items-center gap-3" href="javascript:;">
                                                            <div class="customer-pic">
                                                                <img src="assets/images/avatars/03.png" class="rounded-circle" width="40" height="40" alt="">
                                                            </div>
                                                            <p class="mb-0 customer-name fw-bold">Andrew Carry</p>
                                                        </a>
                                                    </td>
                                                    <td><span class="lable-table bg-primary-subtle text-primary rounded border border-primary-subtle font-text2 fw-bold">Completed<i class="bi bi-check2-all ms-2"></i></span></td>
                                                    <td><span class="lable-table bg-danger-subtle text-danger rounded border border-danger-subtle font-text2 fw-bold">Failed<i class="bi bi-x-lg ms-2"></i></span></td>
                                                    <td>Cash on delivery</td>
                                                    <td>Nov 12, 10:45 PM</td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <input class="form-check-input" type="checkbox">
                                                    </td>
                                                    <td>
                                                        <a href="javascript:;">#6678</a>
                                                    </td>
                                                    <td>$78</td>
                                                    <td>
                                                        <a class="d-flex align-items-center gap-3" href="javascript:;">
                                                            <div class="customer-pic">
                                                                <img src="assets/images/avatars/04.png" class="rounded-circle" width="40" height="40" alt="">
                                                            </div>
                                                            <p class="mb-0 customer-name fw-bold">Andrew Carry</p>
                                                        </a>
                                                    </td>
                                                    <td><span class="lable-table bg-success-subtle text-success rounded border border-success-subtle font-text2 fw-bold">Paid<i class="bi bi-check2 ms-2"></i></span></td>
                                                    <td><span class="lable-table bg-danger-subtle text-danger rounded border border-danger-subtle font-text2 fw-bold">Failed<i class="bi bi-x-lg ms-2"></i></span></td>
                                                    <td>Cash on delivery</td>
                                                    <td>Nov 12, 10:45 PM</td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <input class="form-check-input" type="checkbox">
                                                    </td>
                                                    <td>
                                                        <a href="javascript:;">#2367</a>
                                                    </td>
                                                    <td>$69</td>
                                                    <td>
                                                        <a class="d-flex align-items-center gap-3" href="javascript:;">
                                                            <div class="customer-pic">
                                                                <img src="assets/images/avatars/05.png" class="rounded-circle" width="40" height="40" alt="">
                                                            </div>
                                                            <p class="mb-0 customer-name fw-bold">Andrew Carry</p>
                                                        </a>
                                                    </td>
                                                    <td><span class="lable-table bg-danger-subtle text-danger rounded border border-danger-subtle font-text2 fw-bold">Failed<i class="bi bi-x-lg ms-2"></i></span></td>
                                                    <td><span class="lable-table bg-warning-subtle text-warning rounded border border-warning-subtle font-text2 fw-bold">Pending<i class="bi bi-info-circle ms-2"></i></span></td>
                                                    <td>Cash on delivery</td>
                                                    <td>Nov 12, 10:45 PM</td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <input class="form-check-input" type="checkbox">
                                                    </td>
                                                    <td>
                                                        <a href="javascript:;">#9870</a>
                                                    </td>
                                                    <td>$49</td>
                                                    <td>
                                                        <a class="d-flex align-items-center gap-3" href="javascript:;">
                                                            <div class="customer-pic">
                                                                <img src="assets/images/avatars/06.png" class="rounded-circle" width="40" height="40" alt="">
                                                            </div>
                                                            <p class="mb-0 customer-name fw-bold">Andrew Carry</p>
                                                        </a>
                                                    </td>
                                                    <td><span class="lable-table bg-danger-subtle text-danger rounded border border-danger-subtle font-text2 fw-bold">Failed<i class="bi bi-x-lg ms-2"></i></span></td>
                                                    <td><span class="lable-table bg-success-subtle text-success rounded border border-success-subtle font-text2 fw-bold">Completed<i class="bi bi-check2 ms-2"></i></span></td>
                                                    <td>Cash on delivery</td>
                                                    <td>Nov 12, 10:45 PM</td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <input class="form-check-input" type="checkbox">
                                                    </td>
                                                    <td>
                                                        <a href="javascript:;">#3456</a>
                                                    </td>
                                                    <td>$65</td>
                                                    <td>
                                                        <a class="d-flex align-items-center gap-3" href="javascript:;">
                                                            <div class="customer-pic">
                                                                <img src="assets/images/avatars/07.png" class="rounded-circle" width="40" height="40" alt="">
                                                            </div>
                                                            <p class="mb-0 customer-name fw-bold">Andrew Carry</p>
                                                        </a>
                                                    </td>
                                                    <td><span class="lable-table bg-success-subtle text-success rounded border border-success-subtle font-text2 fw-bold">Completed<i class="bi bi-check2 ms-2"></i></span></td>
                                                    <td><span class="lable-table bg-danger-subtle text-danger rounded border border-danger-subtle font-text2 fw-bold">Failed<i class="bi bi-x-lg ms-2"></i></span></td>
                                                    <td>Cash on delivery</td>
                                                    <td>Nov 12, 10:45 PM</td>
                                                </tr> 
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>-->


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
        <script src="assets/plugins/apex/apexcharts.min.js"></script>
        <!--        <script src="assets/js/index.js"></script>-->

        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

        <!--BS Scripts-->
        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/main.js"></script>

        <jsp:include page="assets/ChartContract/ChartStatisticOfContract.jsp"/>
        <%@ include file="Common/toarst.jsp" %>


        <script>
                                                // pie chart (feedback) - chart 2
                                                var dataOfFeedBack = [<%= dataOfChartFb %>];

                                                // bar chart (contract) - chart 1
                                                var labels1 = [<%= labelsContract %>];
                                                var data1 = [<%= dataContract %>];
                                                var titleOfX = "<%= titleOfX %>";

                                                //area chart (saving) - chart 3
                                                var labels3 = [<%= labelsSaving %>];
                                                var data3 = [<%= dataSaving %>];
                                                console.log("labels3:", labels3);
                                                console.log("data3:", data3);

                                                // area chart (loan) - chart4
                                                var labels4 = [<%= labelsLoan %>];
                                                var data4 = [<%= dataLoan %>];
                                                console.log("labels4:", labels4);
                                                console.log("data4:", data4);

                                                // filter date chart1
                                                document.getElementById('contractFilterForm').addEventListener('submit', function (e) {
                                                    e.preventDefault();
                                                    var urlToServletContract = document.getElementById("urlToSvlContract").value;
                                                    var fromDate = document.getElementById('fromDate').value;
                                                    var toDate = document.getElementById('toDate').value;
                                                    console.log("URL to Servlet: ", urlToServletContract);

                                                    if (urlToServletContract) {
                                                        if (fromDate && toDate) {
                                                            updateChart1(urlToServletContract, fromDate, toDate);
                                                        } else {
                                                            toastr.error("Please select both From and To dates!", "Error");
                                                        }
                                                    }
                                                });


                                                // drown list chart1 ( choose type of contract )
                                                function redirectToTypeOfChart1() {
                                                    var selectBox = document.getElementById("typeOfChartFilter1");
                                                    var selectedValue = selectBox.value;

                                                    if (selectedValue) {
                                                        updateChart1(selectedValue, null, null);
                                                    }
                                                }

                                                function updateChart1(urlToServletContract, fromDate, toDate) {
                                                    $.ajax({
                                                        url: urlToServletContract,
                                                        type: 'GET',
                                                        data: {
                                                            fromDate: fromDate,
                                                            toDate: toDate
                                                        },
                                                        dataType: 'json',
                                                        success: function (response) {
                                                            if (response.error) {
                                                                toastr.error(response.error, "Error");
                                                                chart1.updateOptions({
                                                                    xaxis: {
                                                                        title: {
                                                                            text: response.titleOfX // Tiêu đề mới
                                                                        },
                                                                        categories: [] // Nếu muốn cập nhật cả danh mục (categories)
                                                                    },
                                                                    series: [{data: []}] // Nếu muốn cập nhật dữ liệu
                                                                });

                                                                // cập nhật urlToServletContract để filterDate
                                                                var urlSvlContract = document.getElementById("urlToSvlContract");
                                                                urlSvlContract.value = response.urlSvl;

                                                                //
                                                                document.getElementById('fromDate').value = response.fromDate;
                                                                document.getElementById('toDate').value = response.toDate;


                                                                // cập nhật giá trị cho form xuất excel 
                                                                var chart1Input = document.querySelector('#chart1').closest('.card').querySelector('input[name="data"]');
                                                                chart1Input.value = "null";
                                                                var chart1Percent = document.querySelector('#chart1').closest('.card').querySelector('input[name="percentages"]');
                                                                chart1Percent.value = "null";
                                                                var chart1Label = document.querySelector('#chart1').closest('.card').querySelector('input[name="labels"]');
                                                                chart1Label.value = "null";

                                                            } else {
                                                                chart1.updateOptions({
                                                                    xaxis: {title: {
                                                                            text: response.titleOfX // Tiêu đề mới
                                                                        },
                                                                        categories: response.labels01.replaceAll("'", "").split(",")
                                                                    },
                                                                    series: [{data: response.data01.split(",").map(Number)}]
                                                                });

                                                                // cập nhật urlToServletContract để filterDate
                                                                var urlSvlContract = document.getElementById("urlToSvlContract");
                                                                urlSvlContract.value = response.urlSvl;

                                                                // cập nhật giá trị cho form xuất excel 
                                                                var chart1Input = document.querySelector('#chart1').closest('.card').querySelector('input[name="data"]');
                                                                chart1Input.value = response.data01;
                                                                var chart1Percent = document.querySelector('#chart1').closest('.card').querySelector('input[name="percentages"]');
                                                                chart1Percent.value = response.percentData01;
                                                                var chart1Label = document.querySelector('#chart1').closest('.card').querySelector('input[name="labels"]');
                                                                chart1Label.value = response.labels01.replaceAll("'", "");

                                                                //
                                                                document.getElementById('fromDate').value = response.fromDate;
                                                                document.getElementById('toDate').value = response.toDate;

                                                            }
                                                        },
                                                        error: function (xhr, status, error) {
                                                            toastr.error("An error occurred while retrieving data !!", "Error");
                                                            chart1.updateOptions({
                                                                xaxis: {
                                                                    title: {
                                                                        text: response.titleOfX // Tiêu đề mới
                                                                    },
                                                                    categories: [] // Nếu muốn cập nhật cả danh mục (categories)
                                                                },
                                                                series: [{data: []}] // Nếu muốn cập nhật dữ liệu
                                                            });
                                                            // cập nhật urlToServletContract để filterDate
                                                            var urlSvlContract = document.getElementById("urlToSvlContract");
                                                            urlSvlContract.value = response.urlSvl;

                                                            // Cập nhật form xuất Excel về 0
                                                            var chart1Input = document.querySelector('#chart1').closest('.card').querySelector('input[name="data"]');
                                                            chart1Input.value = "null";
                                                            var chart1Percent = document.querySelector('#chart1').closest('.card').querySelector('input[name="percentages"]');
                                                            chart1Percent.value = "null";
                                                            var chart1Label = document.querySelector('#chart1').closest('.card').querySelector('input[name="labels"]');
                                                            chart1Label.value = "null";

                                                            // Reset giá trị của fromDate và toDate
                                                            document.getElementById('fromDate').value = "";
                                                            document.getElementById('toDate').value = "";
                                                        }
                                                    });
                                                }

                                                //==========================================================================================
                                                // filter date chart3 - saving
                                                document.getElementById('savingContractFilterForm').addEventListener('submit', function (e) {
                                                    e.preventDefault();
                                                    var fromDate = document.getElementById('fromDateSaving').value;
                                                    var toDate = document.getElementById('toDateSaving').value;
                                                    if (fromDate && toDate) {
                                                        updateChartSaving(fromDate, toDate);
                                                    } else {
                                                        toastr.error("Please select both From and To dates!", "Error");
                                                    }

                                                });


                                                function updateChartSaving(fromDate, toDate) {
                                                    $.ajax({
                                                        url: 'ChartRevenueSaving',
                                                        type: 'GET',
                                                        data: {
                                                            fromDateSaving: fromDate,
                                                            toDateSaving: toDate
                                                        },
                                                        dataType: 'json',
                                                        success: function (response) {
                                                            if (response.error) {
                                                                toastr.error(response.error, "Error");
                                                                chart3.updateOptions({
                                                                    xaxis: {
                                                                        categories: [] // Nếu muốn cập nhật cả danh mục (categories)
                                                                    },
                                                                    series: [{data: []}] // Nếu muốn cập nhật dữ liệu
                                                                });

                                                                // cập nhật giá trị cho form xuất excel 
                                                                var chart3Input = document.querySelector('#chart3').closest('.card').querySelector('input[name="data"]');
                                                                chart3Input.value = "null";
                                                                var chart3Percent = document.querySelector('#chart3').closest('.card').querySelector('input[name="percentages"]');
                                                                chart3Percent.value = "null";
                                                                var chart3Label = document.querySelector('#chart3').closest('.card').querySelector('input[name="labels"]');
                                                                chart3Label.value = "null";

                                                                document.getElementById('fromDate').value = response.fromDate;
                                                                document.getElementById('toDate').value = response.toDate;
                                                            } else {
                                                                chart3.updateOptions({
                                                                    xaxis: {
                                                                        categories: response.labels03.replaceAll("'", "").split(",")
                                                                    },
                                                                    series: [{data: response.data03.split(",").map(Number)}]
                                                                });

                                                                // cập nhật giá trị cho form xuất excel 
                                                                var chart3Input = document.querySelector('#chart3').closest('.card').querySelector('input[name="data"]');
                                                                chart3Input.value = response.data03;
                                                                var chart3Percent = document.querySelector('#chart3').closest('.card').querySelector('input[name="percentages"]');
                                                                chart3Percent.value = response.percentData03;
                                                                var chart3Label = document.querySelector('#chart3').closest('.card').querySelector('input[name="labels"]');
                                                                chart3Label.value = response.labels03.replaceAll("'", "");

                                                                document.getElementById('fromDate').value = response.fromDate;
                                                                document.getElementById('toDate').value = response.toDate;
                                                            }
                                                        },
                                                        error: function (xhr, status, error) {
                                                            toastr.error("An error occurred while retrieving data !!", "Error");
                                                            chart3.updateOptions({
                                                                xaxis: {
                                                                    categories: [] // Nếu muốn cập nhật cả danh mục (categories)
                                                                },
                                                                series: [{data: []}] // Nếu muốn cập nhật dữ liệu
                                                            });


                                                            // Cập nhật form xuất Excel về 0
                                                            var chart3Input = document.querySelector('#chart3').closest('.card').querySelector('input[name="data"]');
                                                            chart3Input.value = "null";
                                                            var chart3Percent = document.querySelector('#chart3').closest('.card').querySelector('input[name="percentages"]');
                                                            chart3Percent.value = "null";
                                                            var chart3Label = document.querySelector('#chart3').closest('.card').querySelector('input[name="labels"]');
                                                            chart3Label.value = "null";

                                                        }
                                                    });
                                                }

                                                //==========================================================================================
                                                // filter date chart4 - loan
                                                document.getElementById('loanContractFilterForm').addEventListener('submit', function (e) {
                                                    e.preventDefault();
                                                    var fromDate = document.getElementById('fromDateLoan').value;
                                                    var toDate = document.getElementById('toDateLoan').value;
                                                    if (fromDate && toDate) {
                                                        updateChartLoan(fromDate, toDate);
                                                    } else {
                                                        toastr.error("Please select both From and To dates!", "Error");
                                                    }

                                                });


                                                function updateChartLoan(fromDate, toDate) {
                                                    $.ajax({
                                                        url: 'ChartRevenueLoan',
                                                        type: 'GET',
                                                        data: {
                                                            fromDateLoan: fromDate,
                                                            toDateLoan: toDate
                                                        },
                                                        dataType: 'json',
                                                        success: function (response) {
                                                            if (response.error) {
                                                                toastr.error(response.error, "Error");
                                                                chart4.updateOptions({
                                                                    xaxis: {
                                                                        categories: [] // Nếu muốn cập nhật cả danh mục (categories)
                                                                    },
                                                                    series: [{data: []}] // Nếu muốn cập nhật dữ liệu
                                                                });

                                                                // cập nhật giá trị cho form xuất excel 
                                                                var chart4Input = document.querySelector('#chart4').closest('.card').querySelector('input[name="data"]');
                                                                chart4Input.value = "null";
                                                                var chart4Percent = document.querySelector('#chart4').closest('.card').querySelector('input[name="percentages"]');
                                                                chart4Percent.value = "null";
                                                                var chart4Label = document.querySelector('#chart4').closest('.card').querySelector('input[name="labels"]');
                                                                chart4Label.value = "null";

                                                                document.getElementById('fromDate').value = response.fromDate;
                                                                document.getElementById('toDate').value = response.toDate;
                                                            } else {
                                                                chart4.updateOptions({
                                                                    xaxis: {
                                                                        categories: response.labels04.replaceAll("'", "").split(",")
                                                                    },
                                                                    series: [{data: response.data04.split(",").map(Number)}]
                                                                });

                                                                // cập nhật giá trị cho form xuất excel 
                                                                var chart4Input = document.querySelector('#chart4').closest('.card').querySelector('input[name="data"]');
                                                                chart4Input.value = response.data04;
                                                                var chart4Percent = document.querySelector('#chart4').closest('.card').querySelector('input[name="percentages"]');
                                                                chart4Percent.value = response.percentData04;
                                                                var chart4Label = document.querySelector('#chart4').closest('.card').querySelector('input[name="labels"]');
                                                                chart4Label.value = response.labels04.replaceAll("'", "");

                                                                document.getElementById('fromDate').value = response.fromDate;
                                                                document.getElementById('toDate').value = response.toDate;
                                                            }
                                                        },
                                                        error: function (xhr, status, error) {
                                                            toastr.error("An error occurred while retrieving data !!", "Error");
                                                            chart4.updateOptions({
                                                                xaxis: {
                                                                    categories: [] // Nếu muốn cập nhật cả danh mục (categories)
                                                                },
                                                                series: [{data: []}] // Nếu muốn cập nhật dữ liệu
                                                            });


                                                            // Cập nhật form xuất Excel về 0
                                                            var chart4Input = document.querySelector('#chart4').closest('.card').querySelector('input[name="data"]');
                                                            chart4Input.value = "null";
                                                            var chart4Percent = document.querySelector('#chart4').closest('.card').querySelector('input[name="percentages"]');
                                                            chart4Percent.value = "null";
                                                            var chart4Label = document.querySelector('#chart4').closest('.card').querySelector('input[name="labels"]');
                                                            chart4Label.value = "null";

                                                            // Reset giá trị của fromDate và toDate
                                                            document.getElementById('fromDateLoan').value = "";
                                                            document.getElementById('toDateLoan').value = "";
                                                        }
                                                    });
                                                }










        </script>


    </body>
</html>
