

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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

        <link href="https://fonts.googleapis.com/css2?family=Noto+Sans:wght@300;400;500;600&display=swap" rel="stylesheet">
        <link href="assets/css/main.css" rel="stylesheet">
        <link href="assets/css/dark-theme.css" rel="stylesheet">
        <link href="assets/css/semi-dark-theme.css" rel="stylesheet">
        <link href="assets/css/minimal-theme.css" rel="stylesheet">
        <link href="assets/css/shadow-theme.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">

        <!-- Favicon -->
        <link rel="shortcut icon" href="assets/images/logo-icon.png" type="image/x-icon">
        <link rel="icon" href="assets/images/logo-icon.png" type="image/x-icon">

        <!-- Toarst -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>



        <style>
            .pagination {
                display: flex;
                justify-content: center;
                align-items: center;
                margin: auto;
            }

            .pagination a {
                padding: 9px 10px;
                margin: 0 5px;
                background-color: #f4f4f4;

                color: #333;
                text-decoration: none;
                border-radius: 25px;
                font-weight: bold;
                transition: all 0.3s ease-in-out;
            }

            .pagination a:hover {
                background-color: yellowgreen;
            }

            .pagination a.active {
                background-color: green;
                color: #fff;
                border-radius: 30px;
            }

            select {
                margin-top: 15px;
                background-color: #0d6efd;
                color: white ;
                padding: 3px 8px; /* Giảm padding để ô nhỏ hơn */
                border-radius: 6px; /* Bo tròn góc nhẹ */
                font-size: 14px; /* Giảm kích thước chữ */
                cursor: pointer;
            }


            /* Khi focus vào ô chọn */
            select:focus {
                outline: none;
                box-shadow: 0 0 5px #007bff;
            }

            .modal-footer .btn:hover {
                background-color: green;
                color: white;
                border-color: green;
                transition: 0.3s;
            }

            .btn-link{
                --bs-btn-hover-color: orange;
                --bs-btn-active-color: orange;
            }

            .btn-info {
                --bs-btn-hover-bg: #0b5ed7;
                --bs-btn-active-bg: #0b5ed7;
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
                <div class="breadcrumb-title pe-3">Management</div>
                <div class="ps-3">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb mb-0 p-0">
                            <li class="breadcrumb-item"><a href="javascript:;"><i class="bx bx-home-alt"></i></a>
                            </li>
                            <li class="breadcrumb-item active" aria-current="page">Staffs</li>
                        </ol>
                    </nav>
                </div>


                <!-- SETTING -->

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



            <!-- Số lượng của từng users theo role -->
            <div class="product-count d-flex align-items-center gap-3 gap-lg-4 mb-4 fw-bold flex-wrap font-text1">
                <a href="manage_users"><span class="me-1">All</span><span class="text-secondary">(${totalUsers})</span></a>
                <a href="filter_roleName?id=1"><span class="me-1">Admins</span><span class="text-secondary">(${numOfAdmin})</span></a>
                <a href="filter_roleName?id=2"><span class="me-1">Sellers</span><span class="text-secondary">(${numOfSeller})</span></a>
                <a href="filter_roleName?id=3"><span class="me-1">Managers</span><span class="text-secondary">(${numOfManager})</span></a>
                <a href="filter_roleName?id=4"><span class="me-1">Provider Insurances</span><span class="text-secondary">(${numOfProviderInsurance})</span></a>
            </div>

            <div class="row g-3">
                <div class="col-auto">
                    <!-- Search User -->    
                    <div class="col-auto">
                        <form action="search_users" method="GET" class="position-relative">
                            <input class="form-control px-5" type="search" name="keyword" placeholder="Search staffs" value="${keyword}"> 
                            <span class="material-symbols-outlined position-absolute ms-3 translate-middle-y start-0 top-50 fs-5">search</span>
                        </form>
                    </div>
                </div>

                <div class="col-auto flex-grow-1 overflow-auto">
                    <div class="btn-group position-static">

                        <!-- Sort list user by Full Name -->    
                        <div class="btn-group position-static">
                            <button type="button" class="btn border btn-light dropdown-toggle px-4" data-bs-toggle="dropdown" aria-expanded="false">
                                Full Name
                            </button>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="sort_fullname?type1=asc">Sort A → Z</a></li>
                                <li><a class="dropdown-item" href="sort_fullname?type1=des">Sort Z → A</a></li>

                                <!--                                <li><hr class="dropdown-divider"></li>
                                                                <li><a class="dropdown-item" href="javascript:;">Something else here</a></li>-->
                            </ul>
                        </div>


                        <!-- Sort list user by Date Created -->
                        <div class="btn-group position-static">
                            <button type="button" class="btn border btn-light dropdown-toggle px-4" data-bs-toggle="dropdown" aria-expanded="false">
                                Date Created
                            </button>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="sort_dateCreated?type2=asc">Show Oldest</a></li>
                                <li><a class="dropdown-item" href="sort_dateCreated?type2=des">Show Latest</a></li>
                                <!--                                <li><hr class="dropdown-divider"></li>
                                                                <li><a class="dropdown-item" href="javascript:;">Something else here</a></li>-->
                            </ul>
                        </div>

                        <!--Filer list user by Status  -->
                        <div class="btn-group position-static">
                            <button type="button" class="btn border btn-light dropdown-toggle px-4" data-bs-toggle="dropdown" aria-expanded="false">
                                Status Of Users
                            </button>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="filter_byStatus?status=1">Active</a></li>
                                <li><a class="dropdown-item" href="filter_byStatus?status=0">Inactive</a></li>
                                <!--                                <li><hr class="dropdown-divider"></li>
                                                                <li><a class="dropdown-item" href="javascript:;">Something else here</a></li>-->
                            </ul>
                        </div>


                        <!--Filer list user by role name  -->
                        <div class="btn-group position-static">
                            <button type="button" class="btn border btn-light dropdown-toggle px-4" data-bs-toggle="dropdown" aria-expanded="false">
                                Role Name
                            </button>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="filter_roleName?id=1">Admin</a></li>
                                <li><a class="dropdown-item" href="filter_roleName?id=2">Seller</a></li>
                                <li><a class="dropdown-item" href="filter_roleName?id=3">Manager</a></li>
                                <li><a class="dropdown-item" href="filter_roleName?id=4">Provider Insurance</a></li>
                                <!--                                <li><a class="dropdown-item" href="filter_roleName?id=5">Customer</a></li>-->
                                <!--                                <li><hr class="dropdown-divider"></li>
                                                                <li><a class="dropdown-item" href="javascript:;">Something else here</a></li>-->
                            </ul>
                        </div>
                    </div>  
                </div>


                <div class="col-auto">
                    <div class="d-flex align-items-center gap-2 justify-content-lg-end">                      
                        <!--
                        <button class="btn btn-light px-4"><i class="bi bi-box-arrow-right me-2"></i>Export</button>
                        -->

                        <a href="insert_users" class="btn btn-primary px-4">
                            <i class="bi bi-plus-lg me-2"></i>
                            Add Users
                        </a>
                    </div>
                </div>

            </div><!--end row-->


            <form id="entriesForm" action="manage_users" method="GET" accept-charset="UTF-8">
                <label for="entries">Show Entries</label>
                <select id="entries" name="entries" onchange="this.form.submit()">
                    <c:forEach items="${listOfPageSize}" var="ps">
                        <option value="${ps}" ${(ps == entries)?'selected':''}>${ps}</option>
                    </c:forEach>                   
                </select>

                <input type="hidden" name="typeOfSortByName" value="${typeOfSortByName}">
                <input type="hidden" name="typeOfSortByDate" value="${typeOfSortByDate}">
                <input type="hidden" name="status" value="${statusOfUser}">
                <input type="hidden" name="idOfRole" value="${idOfRole}">
                <input type="hidden" name="keyword" value="${keyword}">
            </form>



            <div class="card mt-4">
                <div class="card-body">
                    <div class="customer-table">
                        <div class="table-responsive white-space-nowrap">
                            <table class="table align-middle">


                                <!-- HEAD TABLE -->     
                                <thead class="table-light">

                                    <tr>
                                        <th class="text-center" >USER ID</th>
                                        <th class="text-center" >FULL NAME</th>
                                        <th class="text-center" >PHONE</th>
                                        <th class="text-center" >EMAIL</th>
                                        <th class="text-center" >Gender</th>
                                        <th class="text-center" >ROLE</th>          
                                        <th class="text-center" >STATUS</th>
                                        <th class="text-center" >CREATED AT</th>
                                        <th class="text-center" style="width: 10%">Action</th>
                                    </tr>    
                                </thead>


                                <!--BODY TABLE --> 
                                <tbody>

                                    <c:forEach var="u" items="${listUsers}">
                                        <tr>
                                            <td class="text-center">${u.getUserID()} </td>
                                            <td class="text-center">${u.getFullName()}</td>
                                            <td class="text-center">${u.getPhone()}</td>
                                            <td class="text-center">${u.getEmail()}</td>

                                            <td class="text-center">
                                                <c:if test="${u.isGender() == true}">
                                                    Male
                                                </c:if>
                                                <c:if test="${u.isGender() == false}">
                                                    Female
                                                </c:if>
                                            </td>


                                            <td class="text-center">
                                                <c:choose>
                                                    <c:when test="${u.getRoleID() == 1}">
                                                        Admin
                                                    </c:when>
                                                    <c:when test="${u.getRoleID() == 2}">
                                                        Seller
                                                    </c:when>
                                                    <c:when test="${u.getRoleID() == 3}">
                                                        Manager
                                                    </c:when>
                                                    <c:when test="${u.getRoleID() == 4}">
                                                        Provider Insurance
                                                    </c:when>
                                                    <c:otherwise>
                                                        Customer
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>



                                            <td class="text-center">


                                                <div class="form-check form-switch">
                                                    <input class="form-check-input" type="checkbox" role="switch" id="adminSwitch-${u.getUserID()}" ${u.isStatus() ? 'checked' : ''} onchange="updateStatusOfUsers(${u.getUserID()}, this.checked)">
                                                    <label class="form-check-label" for="adminSwitch-${u.userID}">Active</label>
                                                </div>

                                            </td>

                                            <td class="text-center">
                                                <fmt:formatDate value="${u.createdAt}" pattern="dd-MM-yyyy"/>
                                            </td>


                                            <td class="text-center">
                                                <div class="form-button-action">
                                                    <a href="update_user?id=${u.getUserID()}"
                                                       class="btn btn-link btn-primary btn-lg"
                                                       data-bs-toggle="tooltip"
                                                       title="Update Information"
                                                       data-original-title="Update Information">
                                                        <i class="fa fa-edit"></i>
                                                    </a>

                                                    <a href="javascript:void(0);"
                                                       class="btn btn-link btn-info btn-lg"
                                                       data-bs-toggle="tooltip"
                                                       title="View Profile"
                                                       onclick="showUserDetails('${u.getImage()}', '${u.getUsername()}', '${u.getFullName()}', '${u.getPhone()}',
                                                                       '${u.getEmail()}', '${u.getAddress()}', '${u.getDateOfBirth()}', '${u.isGender()}', '${u.getRoleID()}',
                                                                       '${u.getManager()!=null? u.getManager().getFullName() : 'None'}', '${u.isStatus()}', '${u.getCreatedAt()}')">
                                                        <i class="fa fa-eye"></i>
                                                    </a>

                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>    

                                </tbody>
                            </table>
                        </div>



                        <!-- Modal for show detail user -->
                        <div class="modal fade" id="userDetailModal" tabindex="-1" aria-labelledby="userDetailModalLabel" aria-hidden="true">
                            <div class="modal-dialog modal-lg">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="userDetailModalLabel">User Details</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <div class="row">
                                            <!-- Column left: IMAGE -->
                                            <div class="col-md-6 text-center">
                                                <img id="userImage" src="" alt="User Image"  style="width: 300px; height: 380px;">
                                            </div>
                                            <!-- Column right: User Detail -->
                                            <div class="col-md-6">
                                                <p><strong>Username:</strong> <span id="userUsername"></span></p>
                                                <p><strong>Full Name:</strong> <span id="userFullName"></span></p>
                                                <p><strong>Phone:</strong> <span id="userPhone"></span></p>
                                                <p><strong>Email:</strong> <span id="userEmail"></span></p>
                                                <p><strong>Address:</strong> <span id="userAddress"></span></p>
                                                <p><strong>Date of Birth:</strong> <span id="userDateOfBirth"></span></p>
                                                <p><strong>Gender:</strong> <span id="userGender"></span></p>
                                                <p><strong>Role:</strong> <span id="userRole"></span></p>
                                                <p><strong>Manager:</strong> <span id="userManager"></span></p>
                                                <p><strong>Status:</strong> <span id="userStatus"></span></p>
                                                <p><strong>Created At:</strong> <span id="userCreatedAt"></span></p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>


                        <div class="pagination">
                            <c:if test="${currentPage > 1}">
                                <a href="?page=${currentPage - 1}&keyword=${keyword}&type1=${typeOfSortByName}&type2=${typeOfSortByDate}&id=${idOfRole}&status=${statusOfUser}&entries=${entries}" class="prev">Previous</a>
                            </c:if>

                            <c:forEach var="i" begin="1" end="${totalPages}">
                                <a href="?page=${i}&keyword=${keyword}&type1=${typeOfSortByName}&type2=${typeOfSortByDate}&id=${idOfRole}&status=${statusOfUser}&entries=${entries}" class="${i == currentPage ? 'active' : ''}">${i}</a>
                            </c:forEach>

                            <c:if test="${currentPage < totalPages}">
                                <a href="?page=${currentPage + 1}&keyword=${keyword}&type1=${typeOfSortByName}&type2=${typeOfSortByDate}&id=${idOfRole}&status=${statusOfUser}&entries=${entries}" class="next">Next</a>
                            </c:if>
                        </div>


                    </div>
                </div>
            </div>

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

        <!--BS Scripts-->
        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/main.js"></script>


        <script>
                                                           function updateStatusOfUsers(userID, statusIsChecked) {
                                                               $.ajax({
                                                                   url: 'updateStatus',
                                                                   type: 'POST',
                                                                   data: {
                                                                       userID: userID,
                                                                       status: statusIsChecked
                                                                   },
                                                                   success: function (response) {
                                                                       if (response.success) {
                                                                           alert(response.message);
                                                                       } else {
                                                                           alert(response.message);
                                                                       }
                                                                   },
                                                                   error: function (xhr, status, error) {
                                                                       alert('error: ' + error);
                                                                   }
                                                               });
                                                           }

                                                           function showUserDetails(image, username, fullName, phone, email, address, dateOfBirth, gender, roleID, managerName, status, createdAt) {
                                                               // Điền dữ liệu vào modal
                                                               document.getElementById('userImage').src = image;
                                                               document.getElementById('userUsername').textContent = username;
                                                               document.getElementById('userFullName').textContent = fullName;
                                                               document.getElementById('userPhone').textContent = phone;
                                                               document.getElementById('userEmail').textContent = email;
                                                               document.getElementById('userAddress').textContent = address;
                                                               document.getElementById('userDateOfBirth').textContent = dateOfBirth;
                                                               document.getElementById('userGender').textContent = gender === 'true' ? 'Male' : 'Female';
                                                               document.getElementById('userManager').textContent = managerName;

                                                               // Role Mapping
                                                               const roles = {
                                                                   1: 'Admin',
                                                                   2: 'Seller',
                                                                   3: 'Manager',
                                                                   4: 'Provider Insurance',
                                                                   5: 'Customer'
                                                               };
                                                               document.getElementById('userRole').textContent = roles[roleID] || 'Unknown';

                                                               // Status Mapping
                                                               document.getElementById('userStatus').textContent = status === 'true' ? 'Active' : 'Inactive';
                                                               document.getElementById('userCreatedAt').textContent = createdAt;

                                                               // Hiển thị modal
                                                               const modal = new bootstrap.Modal(document.getElementById('userDetailModal'));
                                                               modal.show();
                                                           }




        </script>






        <%@ include file="Common/toarst.jsp" %>

    </body>
</html>
