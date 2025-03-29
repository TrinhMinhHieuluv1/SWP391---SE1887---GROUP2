<%-- 
    Document   : login
    Created on : Jan 13, 2025, 3:18:30 AM
    Author     : HP
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html lang="en" data-bs-theme="light">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Manage FAQ</title>

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

        <style>
            .news-management {
                padding: 30px;
                max-width: 1200px;
                margin: 0 auto;
            }

            .page-title {
                color: #2e7d32;
                font-size: 2em;
                margin-bottom: 30px;
                font-weight: 600;
                text-align: center;
            }

            .news-table {
                width: 100%;
                border-collapse: separate;
                border-spacing: 0;
                background: white;
                border-radius: 15px;
                overflow: hidden;
                box-shadow: 0 5px 15px rgba(0, 0, 0, 0.08);
            }

            .news-table th {
                background: #4caf50;
                color: white;
                font-weight: 600;
                padding: 15px;
                text-align: left;
                font-size: 0.95em;
                text-transform: uppercase;
                letter-spacing: 0.5px;
            }

            .news-table th.center-align {
                text-align: center;
            }

            .news-table td {
                padding: 15px;
                border-bottom: 1px solid #e8f5e9;
                color: #333;
                font-size: 0.95em;
            }

            .news-table td.center-align {
                text-align: center;
            }

            .news-table tr:last-child td {
                border-bottom: none;
            }

            .news-table tr:nth-child(even) {
                background-color: #f8fdf8;
            }

            .news-table tr:hover td {
                background-color: #e8f5e9;
            }

            .news-table th.id-column {
                width: 60px;
            }

            .news-table th.author-column {
                width: 20%;
            }

            .news-table th.title-column {
                width: 35%;
            }

            .news-table th.status-column {
                width: 100px;
            }

            .news-table th.time-column {
                width: 120px;
            }

            .news-table th.access-column {
                width: 80px;
            }

            .news-table th.actions-column {
                width: 180px;
            }

            .action-buttons-container {
                display: flex;
                gap: 10px;
                justify-content: center;
            }

            .action-button {
                padding: 8px 12px;
                border-radius: 8px;
                border: none;
                font-weight: 500;
                cursor: pointer;
                transition: all 0.3s ease;
                font-size: 0.9em;
                text-decoration: none;
                display: inline-block;
                text-align: center;
                min-width: 80px;
            }

            .update-btn {
                background: #4caf50;
                color: white;
            }

            .update-btn:hover {
                background: #43a047;
                transform: translateY(-2px);
                box-shadow: 0 5px 15px rgba(76, 175, 80, 0.2);
            }

            .activate-btn {
                background: #2196f3;
                color: white;
            }

            .activate-btn:hover {
                background: #1e88e5;
                transform: translateY(-2px);
                box-shadow: 0 5px 15px rgba(33, 150, 243, 0.2);
            }

            .inactivate-btn {
                background: #f44336;
                color: white;
            }

            .inactivate-btn:hover {
                background: #e53935;
                transform: translateY(-2px);
                box-shadow: 0 5px 15px rgba(244, 67, 54, 0.2);
            }

            .action-column {
                white-space: nowrap;
                text-align: center;
            }

            .created-time {
                color: #666;
                font-size: 0.9em;
            }

            .access-count {
                font-weight: 500;
                color: #1976d2;
            }

            .author-name {
                color: #333;
                font-weight: 500;
            }

            .news-title {
                font-weight: 500;
                color: #2e7d32;
                text-decoration: none;
            }

            .news-title:hover {
                color: #43a047;
            }

            .pagination {
                display: flex;
                justify-content: center;
                align-items: center;
                margin-top: 20px;
                gap: 10px;
            }

            .pagination-button {
                padding: 8px 16px;
                border: 1px solid #4caf50;
                background-color: white;
                color: #4caf50;
                cursor: pointer;
                border-radius: 4px;
                transition: all 0.3s ease;
            }

            .pagination-button:hover {
                background-color: #4caf50;
                color: white;
            }

            .pagination-button.active {
                background-color: #4caf50;
                color: white;
            }

            .pagination-button:disabled {
                border-color: #ccc;
                color: #ccc;
                cursor: not-allowed;
            }

            .pagination-info {
                color: #666;
                font-size: 0.9em;
            }

            /* Search Box */
            .search-container {
                position: relative;
                margin-bottom: 20px;
                max-width: 300px;
            }

            .search-container i {
                position: absolute;
                left: 8px;
                top: 50%;
                transform: translateY(-50%);
                color: #666;
                font-size: 0.9em;
            }

            .search-input {
                padding: 6px 6px 6px 28px;
                border: 1px solid #ddd;
                border-radius: 6px;
                width: 100%;
                font-size: 0.9em;
            }

            /* Sort arrows styling */
            .sortable {
                position: relative;
                cursor: pointer;
            }

            .sort-icons {
                display: inline-block;
                margin-left: 5px;
                vertical-align: middle;
            }

            .icon-sort,
            .fa-caret-up,
            .fa-caret-down {
                color: white;
                font-size: 1.5em;
                margin-left: 5px;
                vertical-align: middle;
            }

            .sort-icons i {
                display: block;
                font-size: 0.8em;
                line-height: 0.8em;
                color: #999;
            }

            .sort-icons i.active {
                color: #4caf50;
            }

            /* Filter styling */
            .filter-group {
                background: white;
                padding: 15px;
                border-radius: 10px;
                box-shadow: 0 2px 8px rgba(0,0,0,0.1);
                margin-bottom: 20px;
            }

            .filter-group label {
                font-weight: 500;
                color: #333;
                margin-right: 15px;
            }

            .filter-group select {
                padding: 8px 12px;
                border: 1px solid #ddd;
                border-radius: 6px;
                background-color: white;
                color: #333;
                font-size: 0.95em;
            }

            .mine-checkbox {
                margin-left: 20px;
            }

            .mine-checkbox input[type="checkbox"] {
                margin-right: 8px;
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

            /* Modal Styles */
            .modal {
                display: none;
                position: fixed;
                z-index: 1000;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                background-color: rgba(0, 0, 0, 0.5);
            }

            .modal-content {
                background-color: #fefefe;
                margin: 5% auto;
                padding: 20px;
                border: 1px solid #888;
                width: 80%;
                max-width: 800px;
                border-radius: 8px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            }

            .modal-header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 20px;
                padding-bottom: 10px;
                border-bottom: 1px solid #eee;
            }

            .modal-title {
                color: #2e7d32;
                font-size: 1.5em;
                margin: 0;
            }

            .close-modal {
                color: #aaa;
                font-size: 28px;
                font-weight: bold;
                cursor: pointer;
                transition: color 0.3s ease;
            }

            .close-modal:hover {
                color: #666;
            }

            .modal-body {
                margin-bottom: 20px;
                display: flex;
                gap: 20px;
            }

            .news-description {
                color: #333;
                line-height: 1.6;
                flex: 6;
                padding-right: 20px;
            }

            .news-image-container {
                flex: 4;
                display: flex;
                align-items: flex-start;
            }

            .news-image {
                width: 100%;
                height: auto;
                border-radius: 4px;
                object-fit: cover;
            }

            .show-all-news-btn,
            .add-news-btn {
                padding: 10px 20px;
                border-radius: 8px;
                font-weight: 600;
                text-transform: uppercase;
                letter-spacing: 0.5px;
                cursor: pointer;
                transition: all 0.3s ease;
                border: none;
                font-size: 0.9em;
                margin: 10px 0;
                display: inline-block;
                text-align: center;
                text-decoration: none;
            }

            .show-all-news-btn {
                background-color: #e0e0e0;
                color: #333;
                border: 1px solid #ccc;
            }

            .show-all-news-btn:hover {
                background-color: #d0d0d0;
                transform: translateY(-2px);
                box-shadow: 0 4px 6px rgba(0,0,0,0.1);
            }

            .add-news-btn {
                background-color: #4caf50;
                color: white;
            }

            .add-news-btn:hover {
                background-color: #43a047;
                transform: translateY(-2px);
                box-shadow: 0 4px 6px rgba(76,175,80,0.2);
            }

            @media (max-width: 768px) {
                .news-management {
                    padding: 15px;
                }

                .news-table {
                    display: block;
                    overflow-x: auto;
                }

                .action-button {
                    padding: 6px 12px;
                    font-size: 0.85em;
                }

                .modal-body {
                    flex-direction: column;
                }

                .news-description {
                    padding-right: 0;
                }

                .news-image-container {
                    width: 100%;
                }

                .icon-sort {
                    font-size: 2px;
                    color: white;

                }
            }
        </style>


    </head>
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



                            <li> <a href="news-management"><span class="material-symbols-outlined">arrow_right</span>News</a>
                            </li>
                            <li> <a href="managesav"><span class="material-symbols-outlined">arrow_right</span>Tool Calculator</a>
                            </li>
                            <li> <a href="managefeedback"><span class="material-symbols-outlined">arrow_right</span>Feedback</a>
                            </li>

                        </ul>
                    </li> 


                    <!--                    <li class="menu-label">Charts & Maps</li>
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
                                        </li>-->

                </ul>
                <!--end navigation-->


            </div>
            <div class="sidebar-bottom dropdown dropup-center dropup">
                <div class="dropdown-toggle d-flex align-items-center px-3 gap-3 w-100 h-100" data-bs-toggle="dropdown">
                    <div class="user-img">
                        <img src="assets/images/avatars/01.png" alt="">
                    </div>
                    <div class="user-info">
                        <h5 class="mb-0 user-name">${sessionScope.account.getFullName()}</h5>
                        <p class="mb-0 user-designation">${sessionScope.account.getEmail()}</p>
                    </div>
                </div>
                <ul class="dropdown-menu dropdown-menu-end">
                    
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
            <div class="news-management">
                <h1 class="page-title">News Management</h1>

                <!-- Filter Controls -->
                <form action="news-management" method="get" class="filter-controls">
                    <!-- Search Box -->
                    <div class="search-container">
                        <i class="fa fa-search"></i>
                        <input type="text" name="searchKeyword" value="${param.searchKeyword}" placeholder="Search news..." class="search-input">
                    </div>

                    <div class="filter-group">
                        <label for="status">Status:</label>
                        <select name="filterStatus" id="status" onchange="updateURLParameter('filterStatus', this.value)">
                            <option value="none" ${param.filterStatus == 'none' ? 'selected' : ''}>All Status</option>
                            <option value="active" ${param.filterStatus == 'active' ? 'selected' : ''}>Active</option>
                            <option value="inactive" ${param.filterStatus == 'inactive' ? 'selected' : ''}>Inactive</option>
                        </select>

                        <label for="NewsCategory" style="margin-left: 20px">News Category: </label>
                        <select name="filterNewsCategoryID" id="NewsCategory" onchange="updateURLParameter('filterNewsCategoryID', this.value)">
                            <option value="0" ${param.filterNewsCategoryID == 0 ? 'selected' : ''}>All Category</option>
                            <c:forEach items="${requestScope.ncList}" var="nc">
                                <option value="${nc.getNewsCategoryID()}" ${param.filterNewsCategoryID == nc.getNewsCategoryID() ? 'selected' : ''}>${nc.getNewsCategoryName()}</option>
                            </c:forEach>
                        </select>

                        <label class="mine-checkbox">
                            <input type="checkbox" name="filterMine" value="true" ${param.filterMine == 'true'? 'checked' : ''} onchange="updateURLParameter('filterMine', this.checked)">
                            Show only my news
                        </label>

                        <label for="pageSize">Items per page:</label>
                        <select name="pageSize" id="pageSize" onchange="updateURLParameter('pageSize', this.value)">
                            <c:if test="${requestScope.numberOfNews <= 100}">
                                <option value="5" ${param.pageSize == 5 ? 'selected' : ''}>5</option>
                                <option value="10" ${param.pageSize==null || param.pageSize == 10 ? 'selected' : ''}>10</option>
                                <option value="20" ${param.pageSize == 20 ? 'selected' : ''}>20</option>
                                <option value="30" ${param.pageSize == 30 ? 'selected' : ''}>30</option>
                                <option value="50" ${param.pageSize == 50 ? 'selected' : ''}>50</option>
                            </c:if>
                            <c:if test="${requestScope.numberOfNews > 100}">
                                <c:forEach items="${requestScope.pageSizeArray}" var="pageSizeElement">
                                    <option value="${pageSizeElement}" ${param.pageSize == pageSizeElement ? 'selected' : ''}>${pageSizeElement}</option>
                                </c:forEach>
                            </c:if>
                        </select>
                    </div>
                </form>
                <button class="show-all-news-btn" onclick="window.location.href = '/timibank/seller/news-management'">Show All News</button>
                <button class="add-news-btn" onclick="window.location.href = '/timibank/seller/add-news'">Add News</button>

                <!-- News Table -->
                <table class="news-table">
                    <thead>
                        <tr>
                            <th class="id-column center-align">ID</th>
                            <th class="author-column">Author</th>
                            <th class="title-column">Title</th>
                            <th class="status-column">Status</th>
                            <th class="time-column sortable">
                                <div style="width: 100%;
                                     display: flex">
                                    <div>Created At</div>
                                    <span class="sort-icons">
                                        <i class="fa fa-caret-up" style="display: ${param.sortBy == 'CreatedAtDESC' ? 'none' : ''};
                                           color: white;
                                           font-size: 1.5em;" 
                                           onclick="updateURLParameter('sortBy', 'CreatedAtDESC')"></i>
                                        <i class="fa fa-caret-down" style="display: ${param.sortBy == 'CreatedAtASC' ? 'none' : ''};
                                           color: white;
                                           font-size: 1.5em;"
                                           onclick="updateURLParameter('sortBy', 'CreatedAtASC')"></i>
                                    </span>
                                </div>
                            </th>
                            <th class="access-column sortable">
                                <div style="width: 100%;
                                     display: flex">
                                    <div>Access</div>
                                    <span class="sort-icons">
                                        <i class="fa fa-caret-up icon-sort" style="display: ${param.sortBy == 'NumberOfAccessDESC' ? 'none' : ''};
                                           color: white;
                                           font-size: 1.5em;"
                                           onclick="updateURLParameter('sortBy', 'NumberOfAccessDESC')"></i>
                                        <i class="fa fa-caret-down icon-sort" style="display: ${param.sortBy == 'NumberOfAccessASC' ? 'none' : ''};
                                           color: white;
                                           font-size: 1.5em;"
                                           onclick="updateURLParameter('sortBy', 'NumberOfAccessASC')"></i>
                                    </span>
                                </div>
                            </th>
                            <th class="actions-column">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.newsList}" var="news">
                            <tr>
                                <td class="center-align">${news.getNewsID()}</td>
                                <td class="author-name">${news.getUser().getFullName()}</td>
                                <td><a href="#" class="news-title" onclick="showNewsModal('${news.getTitle()}', '${fn:escapeXml(news.getDescription())}', '${fn:escapeXml(news.getImage())}')">${news.getTitle()}</a></td>
                                <td id="status-${news.getNewsID()}">
                                    ${news.isStatus() ? 'Active' : 'Inactive'}
                                </td>
                                <td class="created-time">${news.getCreatedAt()}</td>
                                <td class="access-count">${news.getNumberOfAccess()}</td>
                                <td class="action-column">
                                    <c:if test="${news.getUser().getUserID() == sessionScope.account.getUserID()}">
                                        <div class="action-buttons-container">
                                            <a href="/timibank/seller/update-news?NewsID=${news.getNewsID()}" class="action-button update-btn">Update</a>
                                            <c:if test="${news.isStatus()}">
                                                <button type="submit" class="action-button inactivate-btn" onclick="changeStatus(${news.getNewsID()}, this)">Inactivate</button>
                                            </c:if>
                                            <c:if test="${!news.isStatus()}">
                                                <button type="submit" class="action-button activate-btn" onclick="changeStatus(${news.getNewsID()}, this)">Activate</button>
                                            </c:if>
                                        </div>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <!-- Pagination Controls -->
                <div class="pagination">
                    <button class="pagination-button" onclick='changePage(${currentPage - 1})' ${currentPage == 1 ? 'disabled' : ''}>
                        Previous
                    </button>

                    <c:forEach begin="1" end="${totalPages}" var="page">
                        <button class="pagination-button ${currentPage == page ? 'active' : ''}" 
                                onclick="changePage(${page})">
                            ${page}
                        </button>
                    </c:forEach>

                    <button class="pagination-button" onclick="changePage(${currentPage + 1})" ${currentPage == totalPages ? 'disabled' : ''}>
                        Next
                    </button>

                    <span class="pagination-info">
                        Page ${currentPage} of ${totalPages}
                    </span>
                </div>
            </div>

            <!-- News Modal -->
            <div id="newsModal" class="modal">
                <div class="modal-content">
                    <div class="modal-header">
                        <h2 class="modal-title" id="modalTitle"></h2>
                        <span class="close-modal" onclick="closeNewsModal()">&times;</span>
                    </div>
                    <div class="modal-body">
                        <div class="news-description" id="modalDescription"></div>
                        <div class="news-image-container">
                            <img id="modalImage" class="news-image" src="" alt="News Image">
                        </div>
                    </div>
                </div>
            </div>


        </main>

        <!--end main content-->



        <!--        modal customer info-->
        <c:forEach items="${requestScope.contractList}" var="contract">
            <div id="modal-${contract.getCustomer().getCustomerId()}" class="modal modal--large hide">
                <div class="modal__content">

                    <div class="customer__container">
                        <button class="modal__close js-toggle" toggle-target="#modal-${contract.getCustomer().getCustomerId()}">&times;</button>
                        <h3>Information Customer</h3>

                        <div class="customer__grid">
                            <!-- Ô 1: Ảnh -->
                            <div class="customer__image">
                                <img src="${contract.getCustomer().getImage()}" alt="Customer Image">
                            </div>

                            <!-- Ô 2: Tên, Credit Score, Phone -->
                            <div class="customer__info">
                                <p><strong>Name:</strong> ${contract.getCustomer().getFullName()}</p>
                                <p><strong>Credit Score:</strong> ${contract.getCustomer().getCreditScore()}</p>
                                <p><strong>Phone:</strong> ${contract.getCustomer().getPhone()}</p>
                            </div>

                            <!-- Ô 3: Email, Address -->
                            <div class="customer__info">
                                <p><strong>Email:</strong> ${contract.getCustomer().getEmail()}</p>
                                <p><strong>Address:</strong> ${contract.getCustomer().getAddress()}</p>
                            </div>

                            <!-- Ô 4: Date of Birth, Gender -->
                            <div class="customer__info">
                                <p><strong>Date of Birth:</strong> ${contract.getCustomer().getDateOfBirth()}</p>
                                <p><strong>Gender:</strong> ${contract.getCustomer().isGender() ? 'Male' : 'Female'}</p>
                            </div>
                        </div>
                    </div>

                </div>


                <div class="modal__overlay js-toggle" toggle-target="#modal-${contract.getCustomer().getCustomerId()}"></div>
            </div>
        </c:forEach>

        <!--        add insurance-->




        <!--        start overlay-->
        <div class="overlay btn-toggle-menu"></div>
        <!--        end overlay-->

        <!--         Search Modal -->

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
                <!--                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="inlineRadioOptions" id="DarkTheme" value="option2" checked="">
                                    <label class="form-check-label" for="DarkTheme">Dark</label>
                                </div>-->
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

        <script src="../js/scripts.js"></script>

        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/plugins/perfect-scrollbar/js/perfect-scrollbar.js"></script>
        <script src="assets/plugins/metismenu/js/metisMenu.min.js"></script>
        <script src="assets/plugins/simplebar/js/simplebar.min.js"></script>

        <!--BS Scripts-->
        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/main.js"></script>
        <script src="./js/scripts.js"></script>

        <!-- jquery js -->
        <script src="/js/plugins/jquery.min.js"></script>

        <!-- swiper css -->
        <script src="/js/plugins/swiper.min.js"></script>
        <!-- gsap js -->
        <script src="/js/plugins/gsap.min.js"></script>
        <!-- scroll smoother -->
        <script src="/js/plugins/ScrollSmoother.min.js"></script>
        <!-- scroll trigger js -->
        <script src="/js/plugins/ScrollTrigger.min.js"></script>
        <!-- scroll to js -->
        <script src="/js/plugins/ScrollTo.min.js"></script>
        <!-- magnific -->
        <script src="/js/plugins/magnific-popup.js"></script>
        <!-- plax js -->
        <script src="/js/main.js"></script>

        <script>
                            function changePage(page) {
                                const form = document.querySelector('.filter-controls');
                                const input = document.createElement('input');
                                input.type = 'hidden';
                                input.name = 'page';
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
                                    }, 3000);
                                }
                            });
                            function showNewsModal(title, description, image) {
                                const modal = document.getElementById('newsModal');
                                const modalTitle = document.getElementById('modalTitle');
                                const modalDescription = document.getElementById('modalDescription');
                                const modalImage = document.getElementById('modalImage');

                                modalTitle.innerHTML = title;
                                modalDescription.innerHTML = description;
                                modalImage.src = image;

                                modal.style.display = 'block';
                            }

                            function closeNewsModal() {
                                const modal = document.getElementById('newsModal');
                                modal.style.display = 'none';
                            }

                            // Close modal when clicking outside of it
                            window.onclick = function (event) {
                                const modal = document.getElementById('newsModal');
                                if (event.target == modal) {
                                    modal.style.display = 'none';
                                }
                            };

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
                                    }, 3000);
                                }
                            });

                            function updateURLParameter(param, value) {
                                let url = new URL(window.location.href);
                                let params = new URLSearchParams(url.search);

                                params.set(param, value);
                                params.delete('fromUpdate');
                                params.delete('page');

                                window.location.href = 'news-management?' + params.toString();
                            }

                            function changeStatus(NewsID, element) {
                                $.ajax({
                                    url: 'update-news',
                                    type: 'GET',
                                    data: {
                                        NewsID: NewsID,
                                        changeStatus: "true"
                                    }

                                });
                                const status = document.getElementById("status-" + NewsID);
                                if (status.textContent.trim() === 'Active') {
                                    status.textContent = 'Inactive';
                                    element.textContent = 'Activate';
                                    element.classList.remove('inactivate-btn');
                                    element.classList.add('activate-btn');
                                } else {
                                    status.textContent = 'Active';
                                    element.textContent = 'Inactivate';
                                    element.classList.remove('activate-btn');
                                    element.classList.add('inactivate-btn');
                                }
                            }




        </script>
    </body>

</html>