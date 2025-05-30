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
        </style>
    </head>
    <body>
        <%@ include file="Common/toarst.jsp" %>

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
            <!--breadcrumb-->
            <div class="page-breadcrumb d-none d-sm-flex align-items-center mb-3">
                <div class="breadcrumb-title pe-3">Charts</div>
                <div class="ps-3">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb mb-0 p-0">
                            <li class="breadcrumb-item"><a href="javascript:;"><i class="bx bx-home-alt"></i></a>
                            </li>
                            <li class="breadcrumb-item active" aria-current="page">Statistic of customers</li>
                        </ol>
                    </nav>
                </div>


                <!--                <div class="ms-auto">
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-primary">Settings</button>
                                        <button type="button" class="btn btn-primary split-bg-primary dropdown-toggle dropdown-toggle-split" data-bs-toggle="dropdown">	<span class="visually-hidden">Toggle Dropdown</span>
                                        </button>
                                        <div class="dropdown-menu dropdown-menu-right dropdown-menu-lg-end">
                                            <a class="dropdown-item" href="javascript:;">Action</a>
                                            <a class="dropdown-item" href="javascript:;">Another action</a>
                                            <a class="dropdown-item" href="javascript:;">Something else here</a>
                                            <div class="dropdown-divider"></div>	<a class="dropdown-item" href="javascript:;">Separated link</a>
                                        </div>
                                    </div>
                                </div>-->


            </div>
            <!--end breadcrumb-->


            <div class="row">
                <div class="col-xl-12">

                    <!-- Chart 1 -->
                    <div class="card">
                        <div class="card-header bg-transparent py-3 d-flex align-items-center">
                            <h6 class="mb-0 text-uppercase">Statistic of new Customers</h6>

                            <form action="getData_Cus" id="dateFilterForm" class="ms-auto d-flex align-items-center">
                                <label for="fromDate"  class="me-2">From:</label>
                                <input type="date" value="${fromDate2}" id="fromDate" name="fromDate" class="form-control me-3" style="width: 170px;">
                                <label for="toDate" class="me-2">To:</label>
                                <input type="date" value="${toDate2}" id="toDate" name="toDate" class="form-control me-3" style="width: 170px;">
                                <button type="submit" class="btn btn-primary">Filter</button>
                            </form>

                            <%                       
                                    String labels01 = (String) session.getAttribute("labels01");
                                    String data01 = (String) session.getAttribute("data01");
                                    String percentData01 = (String) session.getAttribute("percentData01");
                                    String fileDate = new SimpleDateFormat("yyyyMMdd").format(new java.util.Date());                           
                            %>

                            <form action="export_excel" method="post" class="d-flex align-items-center gap-2" style="margin-left: 5px">
                                <input type="hidden" name="dataRangeType" value="Date Range">
                                <input type="hidden" name="labels" value="<%= (labels01 == null || labels01.isEmpty()) ? "null" : labels01.replaceAll("'", "") %>">
                                <input type="hidden" name="data" value="<%= (data01 == null || data01.isEmpty()) ? "null" : data01 %>">
                                <input type="hidden" name="percentages" value="<%= (percentData01 == null || percentData01.isEmpty()) ? "null" : percentData01 %>">
                                <input type="hidden" name="fileDate" value="<%= fileDate %>">

                                <button type="submit" class="btn-excel">Export to Excel</button>
                            </form>
                        </div>
                        <div class="card-body">
                            <div class="chart-js-container3">
                                <canvas id="chart1"></canvas>
                            </div>
                        </div>
                    </div>

                    <!-- Chart 2 -->
                    <div class="card">
                        <div class="card-header bg-transparent py-3 d-flex align-items-center">
                            <h6 class="mb-0 text-uppercase">Statistic of customer credit score</h6>

                            <%
                                Object totalCusObj = session.getAttribute("total_cus");
                                Object percentPoorObj = session.getAttribute("percentPoor");
                                Object percentFairObj = session.getAttribute("percentFair");
                                Object percentGoodObj = session.getAttribute("percentGood");
                                Object percentVeryGoodObj = session.getAttribute("percentVeryGood");
                                Object percentExcellentObj = session.getAttribute("percentExcellent");
                                Object dataObj = session.getAttribute("data2");

                                int total_cus = (totalCusObj instanceof Integer) ? (Integer) totalCusObj : 0;
                                double percentPoor = (percentPoorObj instanceof Double) ? (Double) percentPoorObj : 0.0;
                                double percentFair = (percentFairObj instanceof Double) ? (Double) percentFairObj : 0.0;
                                double percentGood = (percentGoodObj instanceof Double) ? (Double) percentGoodObj : 0.0;
                                double percentVeryGood = (percentVeryGoodObj instanceof Double) ? (Double) percentVeryGoodObj : 0.0;
                                double percentExcellent = (percentExcellentObj instanceof Double) ? (Double) percentExcellentObj : 0.0;
                                String data = (dataObj instanceof String) ? (String) dataObj : "0,0,0,0,0"; 
                            %>


                            <div class="ms-auto d-flex align-items-center">
                                <select class="form-select me-1" id="statusFilter2" name="statusFilter2" 
                                        style="width: auto; min-width: 170px; padding: 4px 8px; font-size: 14px;" 
                                        onchange="redirectToServletChart2()">
                                    <option value="" disabled>Status of customer</option>
                                    <option selected value="ActiveChart2">Active</option>
                                    <option value="InactiveChart2">Inactive</option>
                                    <option value="BothChart2">Both</option>
                                </select>

                                <form action="export_excel" method="post">
                                    <input type="hidden" name="dataRangeType" value="Credit Score Range">
                                    <input type="hidden" name="labels" value="Poor (300-499),Fair (500-599),Good (600-699),Very Good (700-799),Excellent (800+)">
                                    <input type="hidden" name="data" value="<%= data %>">
                                    <input type="hidden" name="percentages" value="<%= percentPoor + "," + percentFair + "," + percentGood + "," + percentVeryGood + "," + percentExcellent %>">
                                    <input type="hidden" name="fileDate" value="<%= fileDate %>">
                                    <button type="submit" class="btn-excel">Export to Excel</button>
                                </form>
                            </div>
                        </div>

                        <div class="card-body">
                            <div class="chart-js-container3">
                                <canvas id="chart2"></canvas>
                            </div>
                        </div>
                    </div>

                    <!-- Chart 3 -->
                    <div class="card">
                        <div class="card-header bg-transparent py-3 d-flex align-items-center">
                            <h6 class="mb-0 text-uppercase">Statistic Of Customer Age</h6>

                            <%
                                Object numOf18to24ageObj = session.getAttribute("numOf18to24age");
                                Object numOf25to34ageObj = session.getAttribute("numOf25to34age");
                                Object numOf35to60ageObj = session.getAttribute("numOf35to60age");
                                Object numOf60ageObj = session.getAttribute("numOf60age");
                                
                                int numOf18to24age = (numOf18to24ageObj instanceof Integer) ? (Integer) numOf18to24ageObj : 0;
                                int numOf25to34age = (numOf25to34ageObj instanceof Integer) ? (Integer) numOf25to34ageObj : 0;
                                int numOf35to60age = (numOf35to60ageObj instanceof Integer) ? (Integer) numOf35to60ageObj : 0;
                                int numOf60age = (numOf60ageObj instanceof Integer) ? (Integer) numOf60ageObj : 0;

                                Object percent18to24ageObj = session.getAttribute("percent18to24age");
                                Object percent25to34ageObj = session.getAttribute("percent25to34age");
                                Object percent35to60ageObj = session.getAttribute("percent35to60age");
                                Object percent60ageObj = session.getAttribute("percent60age");

                                double percent18to24age = (percent18to24ageObj instanceof Double) ? (Double) percent18to24ageObj : 0.0;
                                double percent25to34age = (percent25to34ageObj instanceof Double) ? (Double) percent25to34ageObj : 0.0;
                                double percent35to60age = (percent35to60ageObj instanceof Double) ? (Double) percent35to60ageObj : 0.0;
                                double percent60age = (percent60ageObj instanceof Double) ? (Double) percent60ageObj : 0.0;

                            %>

                            <div class="ms-auto d-flex align-items-center">
                                <select class="form-select me-1" id="statusFilter3" name="statusFilter3" 
                                        style="width: auto; min-width: 170px; padding: 4px 8px; font-size: 14px;" 
                                        onchange="redirectToServletChart3()">
                                    <option value="" disabled>Status of customer</option>
                                    <option selected  value="ActiveChart3">Active</option>
                                    <option value="InactiveChart3">Inactive</option>
                                    <option value="BothChart3">Both</option>
                                </select>
                                <form action="export_excel" method="post" class="d-flex ms-auto align-items-center gap-2">
                                    <input type="hidden" name="dataRangeType" value="Age Range">
                                    <input type="hidden" name="labels" value="18-24ages,25-34ages,35-60ages,> 60ages">
                                    <input type="hidden" name="data" value="<%= numOf18to24age + "," + numOf25to34age + "," + numOf35to60age + "," + numOf60age %>">
                                    <input type="hidden" name="percentages" value="<%= percent18to24age + "," + percent25to34age + "," + percent35to60age + "," + percent60age %>">
                                    <input type="hidden" name="fileDate" value="<%= fileDate %>">
                                    <button type="submit" class="btn-excel">Export to Excel</button>
                                </form>
                            </div>

                        </div>
                        <div class="card-body">
                            <div class="chart-js-container3">
                                <canvas id="chart3"></canvas>
                            </div>
                        </div>
                    </div>

                    <!-- Chart 4 -->
                    <div class="card">
                        <div class="card-header bg-transparent py-3 d-flex align-items-center">
                            <h6 class="mb-0 text-uppercase">Statistic of customer balance</h6>

                            <%
                                Object data4Obj = session.getAttribute("data4");
                                Object percentBasicObj = session.getAttribute("percentBasic");
                                Object percentEmergingObj = session.getAttribute("percentEmerging");
                                Object percentMiddleObj = session.getAttribute("percentMiddle");
                                Object percentUpperObj = session.getAttribute("percentUpper");
                                Object percentVipObj = session.getAttribute("percentVip");
                                Object totalCus2Obj = session.getAttribute("totalCus2");

                                String data4 = (data4Obj instanceof String) ? (String) data4Obj : "0,0,0,0,0";
                                double percentBasic = (percentBasicObj instanceof Double) ? (Double) percentBasicObj : 0.0;
                                double percentEmerging = (percentEmergingObj instanceof Double) ? (Double) percentEmergingObj : 0.0;
                                double percentMiddle = (percentMiddleObj instanceof Double) ? (Double) percentMiddleObj : 0.0;
                                double percentUpper = (percentUpperObj instanceof Double) ? (Double) percentUpperObj : 0.0;
                                double percentVip = (percentVipObj instanceof Double) ? (Double) percentVipObj : 0.0;
                                int totalCus2 = (totalCus2Obj instanceof Integer) ? (Integer) totalCus2Obj : 0;
                            %>


                            <div class="ms-auto d-flex align-items-center">
                                <select class="form-select me-1" id="statusFilter4" name="statusFilter4" 
                                        style="width: auto; min-width: 170px; padding: 4px 8px; font-size: 14px;" 
                                        onchange="redirectToServletChart4()">
                                    <option value="" disabled>Status of customer</option>
                                    <option selected value="ActiveChart4">Active</option>
                                    <option value="InactiveChart4">Inactive</option>
                                    <option value="BothChart4">Both</option>
                                </select>
                                <form action="export_excel" method="post" class="d-flex ms-auto align-items-center gap-2">
                                    <input type="hidden" name="dataRangeType" value="Balance Range">
                                    <input type="hidden" name="labels" value="Basic (<50M),Emerging (50M-200M),Middle (200M-500M),Upper (500M-1B),VIP (>1B)">
                                    <input type="hidden" name="data" value="<%= data4 %>">
                                    <input type="hidden" name="percentages" value="<%= percentBasic + "," + percentEmerging + "," + percentMiddle + "," + percentUpper + "," + percentVip %>">
                                    <input type="hidden" name="fileDate" value="<%= fileDate %>">
                                    <button type="submit" class="btn-excel">Export to Excel</button>
                                </form>
                            </div>
                        </div>
                        <div class="card-body">
                            <div class="chart-js-container3">
                                <canvas id="chart4"></canvas>
                            </div>
                        </div>
                    </div>

                    <!-- Chart 5 -->
                    <div class="card">
                        <div class="card-header bg-transparent py-3 d-flex align-items-center">
                            <h6 class="mb-0 text-uppercase">Statistic of customer status</h6>

                            <%
                               
                                Object numOfActiveObj = session.getAttribute("numOfActive");
                                Object numOfInactiveObj = session.getAttribute("numOfInactive");
                                Object percentOfActiveObj = session.getAttribute("percentOfActive");
                                Object percentOfInactiveObj = session.getAttribute("percentOfInactive");

                                
                                int numOfActive = (numOfActiveObj instanceof Integer) ? (Integer) numOfActiveObj : 0;
                                int numOfInactive = (numOfInactiveObj instanceof Integer) ? (Integer) numOfInactiveObj : 0;

                                double percentOfActive = (percentOfActiveObj instanceof Double) ? (Double) percentOfActiveObj : 0.0;
                                double percentOfInactive = (percentOfInactiveObj instanceof Double) ? (Double) percentOfInactiveObj : 0.0;
                            %>


                            <form action="export_excel" method="post" class="d-flex ms-auto align-items-center gap-2">
                                <input type="hidden" name="dataRangeType" value="Status Range">
                                <input type="hidden" name="labels" value="Active,Inactive">
                                <input type="hidden" name="data" value="<%= numOfActive + "," + numOfInactive %>">
                                <input type="hidden" name="percentages" value="<%= percentOfActive + "," + percentOfInactive %>">
                                <input type="hidden" name="fileDate" value="<%= fileDate %>">
                                <button type="submit" class="btn-excel">Export to Excel</button>
                            </form>



                        </div>
                        <div class="card-body">
                            <div class="chart-js-container3">
                                <canvas id="chart5"></canvas>
                            </div>
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

        <jsp:include page="assets/ChartCus/ChartStatisticOfCustomer.jsp"/>


        <script>
                                            // Chart 1
                                            var labels1 = [<%= labels01 %>];
                                            var data1 = [<%= data01 %>];

                                            // Chart 2
                                            var data2 = [<%= data %>];
                                            var totalOfCus = <%= total_cus %>;

                                            // Chart 3
                                            var labels3 = ['18-24ages', '25-34ages', '35-60ages', '> 60ages'];
                                            var data3 = [<%= percent18to24age %>, <%= percent25to34age %>, <%= percent35to60age %>, <%= percent60age %>];

                                            // Chart 4
                                            var data4 = [<%= data4 %>];
                                            var totalCus2 = <%= totalCus2 %>;
                                            // Chart 5
                                            var labels5 = ['Active', 'Inactive'];
                                            var data5 = [<%= percentOfActive %>, <%= percentOfInactive %>];

                                           

//                                            // update data chart 1
//                                            function updateChartWithDateFilter(fromDate, toDate) {
//                                                $.ajax({
//                                                    url: 'filterDateNewCus',
//                                                    type: 'POST',
//                                                    data: {
//                                                        fromDate: fromDate,
//                                                        toDate: toDate
//                                                    },
//                                                    dataType: 'json',
//                                                    success: function (response) {
//                                                        if (response.error) {
//                                                            toastr.error(response.error, "Error");
//                                                            myChart1.data.labels = [];
//                                                            myChart1.data.datasets[0].data = [];
//
//                                                            // Cập nhật form export về giá trị mặc định
//                                                            var chart1Input = document.querySelector('#chart1').closest('.card').querySelector('input[name="data"]');
//                                                            chart1Input.value = "null";
//                                                            var chart1Percent = document.querySelector('#chart1').closest('.card').querySelector('input[name="percentages"]');
//                                                            chart1Percent.value = "null";
//                                                            var chart1Label = document.querySelector('#chart1').closest('.card').querySelector('input[name="labels"]');
//                                                            chart1Label.value = "null";
//                                                        } else {
//                                                            myChart1.data.labels = response.labels01.replaceAll("'", "").split(",");
//                                                            myChart1.data.datasets[0].data = response.data01.split(",").map(Number);
//
//                                                            // Cập nhật form export
//                                                            var chart1Input = document.querySelector('#chart1').closest('.card').querySelector('input[name="data"]');
//                                                            chart1Input.value = response.data01;
//                                                            var chart1Percent = document.querySelector('#chart1').closest('.card').querySelector('input[name="percentages"]');
//                                                            chart1Percent.value = response.percentData01;
//                                                            var chart1Label = document.querySelector('#chart1').closest('.card').querySelector('input[name="labels"]');
//                                                            chart1Label.value = response.labels01.replaceAll("'", "");
//                                                        }
//                                                        myChart1.update();
//                                                    },
//                                                    error: function (xhr, status, error) {
//                                                        toastr.error("An error occurred while retrieving data!", "Error");
//                                                        myChart1.data.labels = [];
//                                                        myChart1.data.datasets[0].data = [];
//
//                                                        var chart1Input = document.querySelector('#chart1').closest('.card').querySelector('input[name="data"]');
//                                                        chart1Input.value = "null";
//                                                        var chart1Percent = document.querySelector('#chart1').closest('.card').querySelector('input[name="percentages"]');
//                                                        chart1Percent.value = "null";
//                                                        var chart1Label = document.querySelector('#chart1').closest('.card').querySelector('input[name="labels"]');
//                                                        chart1Label.value = "null";
//
//                                                        myChart1.update();
//                                                    }
//                                                });
//                                            }

                                            // drownlist chart2
                                            function redirectToServletChart2() {
                                                var selectBox = document.getElementById("statusFilter2");
                                                var selectedValue = selectBox.value;
                                                if (selectedValue) {
                                                    updateChart2(selectedValue);
                                                }
                                            }

                                            function updateChart2(status) {
                                                $.ajax({
                                                    url: status,
                                                    type: 'GET',
                                                    dataType: 'json',
                                                    success: function (response) {
                                                        if (response.error) {
                                                            toastr.error("No data valiable !!", "Error");
                                                            myChart2.data.datasets[0].data = [0];
                                                            myChart2.data.datasets[1].data = [0];
                                                            myChart2.data.datasets[2].data = [0];
                                                            myChart2.data.datasets[3].data = [0];
                                                            myChart2.data.datasets[4].data = [0];
                                                            myChart2.options.scales.y.max = 0;

                                                            // Cập nhật form xuất Excel về 0
                                                            var chart2Input = document.querySelector('#chart2').closest('.card').querySelector('input[name="data"]');
                                                            chart2Input.value = "0,0,0,0,0";
                                                            var chart2Percent = document.querySelector('#chart2').closest('.card').querySelector('input[name="percentages"]');
                                                            chart2Percent.value = "0,0,0,0,0";
                                                        } else {
                                                            // Cập nhật dữ liệu biểu đồ
                                                            myChart2.data.datasets[0].data = [response.data2[0]];
                                                            myChart2.data.datasets[1].data = [response.data2[1]];
                                                            myChart2.data.datasets[2].data = [response.data2[2]];
                                                            myChart2.data.datasets[3].data = [response.data2[3]];
                                                            myChart2.data.datasets[4].data = [response.data2[4]];
                                                            myChart2.options.scales.y.max = response.total_cus;

                                                            // Cập nhật dữ liệu cho form xuất Excel
                                                            var chart2Input = document.querySelector('#chart2').closest('.card').querySelector('input[name="data"]');
                                                            chart2Input.value = response.data2.join(",");  // chuyển mảng thành một chuỗi, ngăn cách bởi dấu phẩy
                                                            var chart2Percent = document.querySelector('#chart2').closest('.card').querySelector('input[name="percentages"]');
                                                            chart2Percent.value = response.percentages.join(",");
                                                        }
                                                        myChart2.update();
                                                    },
                                                    error: function (xhr, status, error) {
                                                        toastr.error("An error occurred while retrieving data !!", "Error");
                                                        myChart2.data.datasets[0].data = [0];
                                                        myChart2.data.datasets[1].data = [0];
                                                        myChart2.data.datasets[2].data = [0];
                                                        myChart2.data.datasets[3].data = [0];
                                                        myChart2.data.datasets[4].data = [0];
                                                        myChart2.options.scales.y.max = 0;
                                                        // Cập nhật form xuất Excel về 0
                                                        var chart2Input = document.querySelector('#chart2').closest('.card').querySelector('input[name="data"]');
                                                        chart2Input.value = "0,0,0,0,0";
                                                        var chart2Percent = document.querySelector('#chart2').closest('.card').querySelector('input[name="percentages"]');
                                                        chart2Percent.value = "0,0,0,0,0";
                                                        myChart2.update();
                                                    }
                                                });
                                            }


                                            // drownlist chart 3
                                            function redirectToServletChart3() {
                                                var selectBox = document.getElementById("statusFilter3");
                                                var selectedValue = selectBox.value;
                                                if (selectedValue) {
                                                    updateChart3(selectedValue);
                                                }
                                            }

                                            function updateChart3(status) {
                                                $.ajax({
                                                    url: status,
                                                    type: 'GET',
                                                    dataType: 'json',
                                                    success: function (response) {
                                                        if (response.error) {
                                                            toastr.error("No data valiable !!", "Error");
                                                            myChart3.data.datasets[0].data = [0, 0, 0, 0];

                                                            var chart3Input = document.querySelector('#chart3').closest('.card').querySelector('input[name="data"]');
                                                            chart3Input.value = "0,0,0,0";
                                                            var chart3Percent = document.querySelector('#chart3').closest('.card').querySelector('input[name="percentages"]');
                                                            chart3Percent.value = "0,0,0,0";
                                                        } else {
                                                            myChart3.data.datasets[0].data = response.percentages3;

                                                            var chart3Input = document.querySelector('#chart3').closest('.card').querySelector('input[name="data"]');
                                                            chart3Input.value = response.data3.join(",");
                                                            var chart3Percent = document.querySelector('#chart3').closest('.card').querySelector('input[name="percentages"]');
                                                            chart3Percent.value = response.percentages3.join(",");
                                                        }
                                                        myChart3.update();
                                                    },
                                                    error: function (xhr, status, error) {
                                                        toastr.error("An error occurred while retrieving data !!", "Error");
                                                        myChart3.data.datasets[0].data = [0, 0, 0, 0];
                                                        var chart3Input = document.querySelector('#chart3').closest('.card').querySelector('input[name="data"]');
                                                        chart3Input.value = "0,0,0,0";
                                                        var chart3Percent = document.querySelector('#chart3').closest('.card').querySelector('input[name="percentages"]');
                                                        chart3Percent.value = "0,0,0,0";
                                                        myChart3.update();
                                                    }
                                                });
                                            }

                                            // drownlist chart 4
                                            function redirectToServletChart4() {
                                                var selectBox = document.getElementById("statusFilter4");
                                                var selectedValue = selectBox.value;
                                                if (selectedValue) {
                                                    updateChart4(selectedValue);
                                                }
                                            }

                                            function updateChart4(status) {
                                                $.ajax({
                                                    url: status,
                                                    type: 'GET',
                                                    dataType: 'json',
                                                    success: function (response) {
                                                        if (response.error) {
                                                            toastr.error("No data valiable !!", "Error");
                                                            myChart4.data.datasets[0].data = [0];
                                                            myChart4.data.datasets[1].data = [0];
                                                            myChart4.data.datasets[2].data = [0];
                                                            myChart4.data.datasets[3].data = [0];
                                                            myChart4.data.datasets[4].data = [0];
                                                            myChart4.options.scales.y.max = 0;

                                                            var chart4Input = document.querySelector('#chart4').closest('.card').querySelector('input[name="data"]');
                                                            chart4Input.value = "0,0,0,0,0";
                                                            var chart4Percent = document.querySelector('#chart4').closest('.card').querySelector('input[name="percentages"]');
                                                            chart4Percent.value = "0,0,0,0,0";
                                                        } else {
                                                            myChart4.data.datasets[0].data = [response.data4[0]];
                                                            myChart4.data.datasets[1].data = [response.data4[1]];
                                                            myChart4.data.datasets[2].data = [response.data4[2]];
                                                            myChart4.data.datasets[3].data = [response.data4[3]];
                                                            myChart4.data.datasets[4].data = [response.data4[4]];
                                                            myChart4.options.scales.y.max = response.total_cus;

                                                            var chart4Input = document.querySelector('#chart4').closest('.card').querySelector('input[name="data"]');
                                                            chart4Input.value = response.data4.join(",");
                                                            var chart4Percent = document.querySelector('#chart4').closest('.card').querySelector('input[name="percentages"]');
                                                            chart4Percent.value = response.percentages4.join(",");
                                                        }
                                                        myChart4.update();
                                                    },
                                                    error: function (xhr, status, error) {
                                                        toastr.error("An error occurred while retrieving data !!", "Error");
                                                        myChart4.data.datasets[0].data = [0];
                                                        myChart4.data.datasets[1].data = [0];
                                                        myChart4.data.datasets[2].data = [0];
                                                        myChart4.data.datasets[3].data = [0];
                                                        myChart4.data.datasets[4].data = [0];
                                                        myChart4.options.scales.y.max = 0;

                                                        var chart4Input = document.querySelector('#chart4').closest('.card').querySelector('input[name="data"]');
                                                        chart4Input.value = "0,0,0,0,0";
                                                        var chart4Percent = document.querySelector('#chart4').closest('.card').querySelector('input[name="percentages"]');
                                                        chart4Percent.value = "0,0,0,0,0";
                                                        myChart4.update();
                                                    }
                                                });
                                            }



        </script>





    </body>
</html>
