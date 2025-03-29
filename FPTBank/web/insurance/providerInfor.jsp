<%-- 
    Document   : providerInfor
    Created on : Mar 14, 2025, 11:24:34 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en" data-bs-theme="light">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Manage Isurance</title>

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

                <link rel="stylesheet" href="../css/style.css" type="text/css" media="all">

        <link href="https://fonts.googleapis.com/css2?family=Noto+Sans:wght@300;400;500;600&display=swap" rel="stylesheet">
        <link href="assets/css/extra-icons.css" rel="stylesheet">
        <link href="assets/css/main.css" rel="stylesheet">
        <link href="assets/css/dark-theme.css" rel="stylesheet">
        <link href="assets/css/semi-dark-theme.css" rel="stylesheet">
        <link href="assets/css/minimal-theme.css" rel="stylesheet">
        <link href="assets/css/shadow-theme.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

    </head>
  
    <script>

            function changePage(page) {
                const form = document.querySelector(".filter-controls"); // Chỉ lấy form đầu tiên
                const input = document.createElement("input");
                input.type = "hidden";
                input.name = "page";
                input.value = page;
                form.appendChild(input);
                form.submit();
            }

            // Toast message animation
            document.addEventListener('DOMContentLoaded', function () {
                const toast = document.getElementById('toastMessage');
                if (toast) {
                    // Show toast
                    setTimeout(() => {
                        toast.classList.add('show');
                    }, 100);

                    // Hide toast after 3 seconds
                    setTimeout(() => {
                        toast.classList.remove('show');
                        // Remove toast from DOM after animation
                        setTimeout(() => {
                            toast.remove();
                        }, 500);
                    }, 6000);
                }
            });

        </script>
    <style>
        
        .btn-primary,.progress-bar{
            background-color: #008000;
        }
             /* Toast Message Styles */
            .toast-message {
                position: fixed;
                top: -100px; /* Start above viewport */
                left: 50%;
                transform: translateX(-50%);
                background-color: #4CAF50;
                color: white;
                padding: 16px 32px;
                border-radius: 8px;
                font-size: 16px;
                font-weight: 500;
                box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
                display: flex;
                align-items: center;
                gap: 12px;
                z-index: 1000;
                transition: top 0.5s ease-in-out;
            }

            .toast-message.show {
                top: 20px; /* Slide down to this position */
            }

            .toast-message i {
                font-size: 24px;
            }
            
            
         .modal__content {
                padding: 20px;
                background-color: #f9f9f9;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                max-width: 500px;
                margin: auto;
            }

            .modal__close {
                float: right;
                font-size: 24px;
                cursor: pointer;
                background: none;
                border: none;
                color: #333;
            }

            .modal__close:hover {
                color: #000;
            }

            .modal__text {
                margin-bottom: 15px;
                font-size: 15px;
            }

            .modal__text label {
                display: block;
                font-weight: bold;
                margin-bottom: 5px;

            }
             .modal__text input,
            .modal__text select {
                width: 100%;
                padding: 8px;
                border: 1px solid #ccc;
                border-radius: 4px;
                font-size: 14px;
                color: #555;
            }

            .modal__text input:focus,
            .modal__text select:focus {
                border-color: #007bff;
                outline: none;
                box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
            }
    </style> 
        
    <body>

        <!--show message-->
        <c:if test="${not empty sessionScope.message}">
            <div id="toastMessage" class="toast-message">
                <i class="fa fa-check-circle"></i>
                ${sessionScope.message}
            </div>
            <c:remove var="message" scope="session" />
        </c:if>

        <c:if test="${not empty sessionScope.error}">
            <div id="toastMessage" class="toast-message1">
                <i class="fa fa-check-circle"></i>
                ${sessionScope.error}
            </div>
            <c:remove var="error" scope="session" />
        </c:if>
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
                    <h5 class="mb-0">Bank</h5>
                </div>
                <div class="sidebar-close">
                    <span class="material-symbols-outlined">close</span>
                </div>
            </div>
            <div class="sidebar-nav" data-simplebar="true">

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
                            <li> <a href="manageinsurance"><span class="material-symbols-outlined">arrow_right</span>Manage Insurance</a>
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
                        <h5 class="mb-0 user-name">Jhon Maxwell</h5>
                        <p class="mb-0 user-designation">UI Engineer</p>
                    </div>
                </div>
                <ul class="dropdown-menu dropdown-menu-end">
                    <li><a class="dropdown-item" href="javascript:;"><span class="material-symbols-outlined me-2">
                                account_circle
                            </span><span>Profile</span></a>
                    </li>
                    <li><a class="dropdown-item" href="javascript:;"><span class="material-symbols-outlined me-2">
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
                    </li>
                    <li><a class="dropdown-item" href="/timibank/logout"><span class="material-symbols-outlined me-2">
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
                <div class="breadcrumb-title pe-3">Pages</div>
                <div class="ps-3">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb mb-0 p-0">
                            <li class="breadcrumb-item"><a href="javascript:;"><i class="bx bx-home-alt"></i></a>
                            </li>
                            <li class="breadcrumb-item active" aria-current="page">User Profile</li>
                        </ol>
                    </nav>
                </div>
                <div class="ms-auto">
                    <div class="btn-group">
                        <button type="button" class="btn btn-primary">Settings</button>
                        <button type="button" class="btn btn-primary split-bg-primary dropdown-toggle dropdown-toggle-split" data-bs-toggle="dropdown">	<span class="visually-hidden">Toggle Dropdown</span>
                        </button>
                        <div class="dropdown-menu dropdown-menu-right dropdown-menu-lg-end">	<a class="dropdown-item" href="javascript:;">Action</a>
                            <a class="dropdown-item" href="javascript:;">Another action</a>
                            <a class="dropdown-item" href="javascript:;">Something else here</a>
                            <div class="dropdown-divider"></div>	<a class="dropdown-item" href="javascript:;">Separated link</a>
                        </div>
                    </div>
                </div>
            </div>
            <!--end breadcrumb-->

            <div class="row">
                <div class="col-12 col-lg-8 col-xl-9">
                    <div class="card overflow-hidden">
                        <div class="profile-cover bg-dark position-relative mb-4">
                            <div class="user-profile-avatar shadow position-absolute top-50 start-0 translate-middle-x">
                                <img src="${user.getImage()}" alt="...">
                            </div>
                        </div>
                        <div class="card-body">
                            <div class="mt-5 d-flex align-items-start justify-content-between">
                                <div class="">
                                    <h3 class="mb-2">${user.getFullName()}</h3>
                                    <p class="mb-1">Insurance Founder</p>
                                    <p>${user.getAddress()}</p>
                                    <div class="">
                                        <a href="/timibank/profile.jsp" class="btn btn-primary"></i>Profile Management</a>           
                                        <a href="showinsurance" class="btn btn-primary"></i>Insurance Management</a>           
                                    </div>
                                </div>
                                <div class="">

                                    <button class="btn btn-primary js-toggle"  toggle-target="#modal-guiadmin"><i class="bi bi-chat me-2"></i>Send Message For Admin</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-body">
                            <h4 class="mb-2">About Me</h4>
                            <p class="">It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters.</p>
                            <p>Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source.</p>
                            <p>It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters</p>
                        </div>
                    </div>
                </div>
                <div class="col-12 col-lg-4 col-xl-3">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="mb-3">Location</h5>
                            <p class="mb-0"><i class="bi bi-geo-alt-fill me-2"></i>${user.getAddress()}</p>
                        </div>
                    </div>

                    <div class="card">
                        <div class="card-body">
                            <h5 class="mb-3">Connect</h5>
                            <p class=""><i class="bi bi-browser-edge me-2"></i>${user.getEmail()}</p>
                            <p class=""><i class="bi bi-facebook me-2"></i>Facebook</p>
                            <p class=""><i class="bi bi-twitter me-2"></i>Twitter</p>
                            <p class="mb-0"><i class="bi bi-linkedin me-2"></i>${user.getPhone()}</p>
                        </div>
                    </div>

                    <div class="card">
                        <div class="card-body">
                            <h5 class="mb-3">Skills</h5>
                            <div class="mb-3">
                                <p class="mb-1">Web Design</p>
                                <div class="progress" style="height: 5px;">
                                    <div class="progress-bar" role="progressbar" style="width: 45%"></div>
                                </div>
                            </div>
                            <div class="mb-3">
                                <p class="mb-1">HTML5</p>
                                <div class="progress" style="height: 5px;">
                                    <div class="progress-bar" role="progressbar" style="width: 55%"></div>
                                </div>
                            </div>
                            <div class="mb-3">
                                <p class="mb-1">PHP7</p>
                                <div class="progress" style="height: 5px;">
                                    <div class="progress-bar" role="progressbar" style="width: 65%"></div>
                                </div>
                            </div>
                            <div class="mb-3">
                                <p class="mb-1">CSS3</p>
                                <div class="progress" style="height: 5px;">
                                    <div class="progress-bar" role="progressbar" style="width: 75%"></div>
                                </div>
                            </div>
                            <div class="mb-0">
                                <p class="mb-1">Photoshop</p>
                                <div class="progress" style="height: 5px;">
                                    <div class="progress-bar" role="progressbar" style="width: 85%"></div>
                                </div>
                            </div>

                        </div>
                    </div>

                </div>
            </div><!--end row-->

        </main>
        <!--end main content-->


        <!--start overlay-->
        <div class="overlay btn-toggle-menu"></div>
        <!--end overlay-->

        
        <div id="modal-guiadmin" class="modal modal--large hide">
                <div class="modal__content">
                    <button class="modal__close js-toggle" toggle-target="#modal-guiadmin">&times;</button>
                    <div style="color: #008000;
                         text-align: center;">
                        <h2  style="color: #008000;
                     text-align: center;">Gửi yêu cầu Hỗ trợ cho Admin</h2>
                    </div>

                    <form action="SendAdmin" method="post" class="mil-subscripe-form-footer" id="form">
                    <!-- Email Input -->
                    <div class="mb-3">
                        <input style="background-color: #fff" class="form-control form-control__input mil-input" type="email" placeholder="Email" name="email" required>
                    </div>
                    <!-- Tiêu đề Input -->
                    <div class="mb-3">
                        <input style="background-color: #fff" class="form-control form-control__input mil-input" type="text" placeholder="Tiêu đề" name="tieude" value="Provider Insurance ID là ${user.getUserID()} yêu cầu hỗ trợ" readonly="">
                    </div>
                    <!-- Nội dung Input -->
                    <div class="mb-3">
                        <textarea class="form-control form-control__textarea" rows="3" placeholder="Nội dung" name="noidung" required></textarea>                                    
                    </div>
                    <!--mã cap cha-->
                    <div class="g-recaptcha" data-sitekey="6LcVf94qAAAAAHVxQmDpyNe7nzc7DEC88pgrmnb4"></div>
                    <div id="error"></div>


                    <div class="form-control__row--reverse">
                        <!-- Submit Button -->
                        <button type="submit" class="form-control__btn form-control__btn--green mil-btn mil-ssm">
                            <i class="far fa-envelope-open form-control__icon"></i> Gửi
                        </button>
                        <!-- Checkbox -->
                        <div class="mil-checkbox-frame">
                            <div class="mil-checkbox">
                                <input type="checkbox" id="checkbox" checked>
                                <label for="checkbox"></label>
                            </div>
                            <p class="mil-text-xs mil-soft">Đăng ký để nhận tin tức mới nhất</p>

                        </div>
                    </div>

                    <script src="https://www.google.com/recaptcha/api.js" async defer></script>
                    <script>
                        window.onload = function () {
                            let isValid = false;
                            const form = document.getElementById("form");
                            const error = document.getElementById("error");

                            form.addEventListener("submit", function (event) {
                                event.preventDefault();
                                const response = grecaptcha.getResponse();
                                if (response) {
                                    form.submit();
                                } else {
                                    error.innerHTML = "Please check";
                                }
                            });
                        }
                    </script>


                </form>
                </div>
                <div class="modal__overlay js-toggle" toggle-target="#modal-guiadmin"></div>
            </div>
        
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
         <script src="../js/scripts.js"></script>
    </body>
</html>
