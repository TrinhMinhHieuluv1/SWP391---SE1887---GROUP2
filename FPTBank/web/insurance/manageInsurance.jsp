<%-- 
    Document   : login
    Created on : Jan 13, 2025, 3:18:30 AM
    Author     : HP
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
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
        <style>
            .form__error{
                padding: 10px 0 ;
                font-size: 13px;
                color: red;
            }
            .error {
                color: red;
            }
            .form__text-input{
                display: flex;
                width: 100%;
            }
            .form-group__input-wrap {
                display: flex;

                width: 100%;
            }

            .form-group__input {
                border: none;
                outline: none;
                font-size: 18px;
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

            .row {
                display: flex;
                flex-wrap: wrap;
                margin: -10px;
            }

            .col-6 {
                flex: 0 0 50%;
                max-width: 50%;
                padding: 10px;
            }



            detail-icon {
                cursor: pointer;
                color: #007bff;
                font-size: 1.2em;
                transition: color 0.3s ease;
            }

            .detail-icon:hover {
                color: #0056b3;
            }


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

            .modal-image {
                width: 600px;
                height: 450px;
                object-fit: cover;
                border-radius: 8px;
            }

            .modal-info {
                flex: 1;
            }

            .modal-info p {
                margin: 10px 0;
            }
            /*            #modalImage {
                            width: 100%;
                            height: auto;
                            align-items: center;
                            justify-content: center;
                            border-radius: 8px;
                            margin-bottom: 15px;
                        }*/

            #modalDescription,
            #modalValue,
            #modalDate {
                font-size: 1em;
                margin: 10px 0;
            }
            .form-control {
                width: 80%;
                padding: 5px;
                border: 1px solid #ccc;
                border-radius: 4px;
                font-size: 14px;
            }
            .comment-input {
                width: 100%;
                height: 50px;
                padding: 10px;
                border: 1px solid #ced4da;
                border-radius: 5px;
                resize: none; /* Không cho phép thay đổi kích thước */
                font-size: 16px;
            }
            .comment-input:focus {
                border-color: #80bdff;
                outline: none;
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

            .toast-message1 {
                position: fixed;
                top: -100px; /* Start above viewport */
                left: 50%;
                transform: translateX(-50%);
                background-color: red;
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

            .toast-message1.show {
                top: 20px; /* Slide down to this position */
            }

            .toast-message1 i {
                font-size: 24px;
            }


        </style>


        <script>
            function submitSortForm1(order) {
                document.getElementById('sortFee').value = order; // Gán giá trị cho trường ẩn
                document.getElementById('sortForm1').submit(); // Gửi form
            }

        </script> 
        <script>
            function submitSortForm2(order) {
                document.getElementById('status').value = order; // Gán giá trị cho trường ẩn
                document.getElementById('sortForm2').submit(); // Gửi form
            }

        </script> 
        <script>
            function submitSortForm3(order) {
                document.getElementById('verify').value = order; // Gán giá trị cho trường ẩn
                document.getElementById('sortForm3').submit(); // Gửi form
            }

        </script> 
        <script>
            function submitSortForm4(order) {
                document.getElementById('CoverageRate').value = order; // Gán giá trị cho trường ẩn
                document.getElementById('sortForm4').submit(); // Gửi form
            }

        </script> 
        <script>
            function submitSortForm5(order) {
                document.getElementById('MaxAmountOfLoan').value = order; // Gán giá trị cho trường ẩn
                document.getElementById('sortForm5').submit(); // Gửi form
            }

        </script> 

        <script>
            function submitSearch() {
                var searchInput = document.getElementById('searchInput').value;
                if (searchInput.trim() !== "") {
                    document.getElementById('searchForm').submit();
                } else {
                    alert("Please enter a search term."); // Thông báo nếu ô tìm kiếm rỗng
                }
            }
        </script>
        <script type="text/javascript">
            function formatNumber(input) {
                // Loại bỏ tất cả các ký tự không phải số
                let value = input.value.replace(/[^0-9]/g, '');

                // Thêm dấu phẩy sau mỗi 3 chữ số
                value = value.replace(/\B(?=(\d{3})+(?!\d))/g, ',');

                // Gán giá trị đã định dạng lại vào input
                input.value = value;
            }

            function validateInput(event) {
                // Chỉ cho phép nhập các ký tự số
                const charCode = (event.which) ? event.which : event.keyCode;
                if (charCode > 31 && (charCode < 48 || charCode > 57)) {
                    event.preventDefault();
                    return false;
                }
                return true;
            }
        </script>




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

            <div class="row g-3">
                <div class="col-auto">
                    <!-- Search Form -->
                    <form action="manageinsurance" method="get">
                        <div class="position-relative">
                            <input class="form-control px-5" type="search" name="searchIsu" value="${searchIsu}" placeholder="Search Products">
                            <button type="submit" class="material-symbols-outlined position-absolute ms-3 translate-middle-y start-0 top-50 fs-5">search</button>
                        </div>
                    </form>
                </div>
                <div class="col-auto flex-grow-1 overflow-auto">
                    <div class="btn-group position-static">
                        <!-- All Button -->
                        <a href="manageinsurance"><button type="button" class="btn border btn-light px-4">All</button></a>

                        <!-- Fee Rate Sort -->
                        <div class="btn-group position-static">
                            <button type="button" class="btn border btn-light dropdown-toggle px-4" data-bs-toggle="dropdown" aria-expanded="false">FeeRate</button>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="manageinsurance?sortFee=asc">ASC</a></li>
                                <li><a class="dropdown-item" href="manageinsurance?sortFee=desc">DESC</a></li>
                            </ul>
                        </div>

                        <!-- Coverage Rate Sort -->
                        <div class="btn-group position-static">
                            <button type="button" class="btn border btn-light dropdown-toggle px-4" data-bs-toggle="dropdown" aria-expanded="false">CoverageRate</button>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="manageinsurance?CoverageRate=asc">ASC</a></li>
                                <li><a class="dropdown-item" href="manageinsurance?CoverageRate=desc">DESC</a></li>
                            </ul>
                        </div>

                        <!-- Max Amount of Loan Sort -->
                        <div class="btn-group position-static">
                            <button type="button" class="btn border btn-light dropdown-toggle px-4" data-bs-toggle="dropdown" aria-expanded="false">MaxAmountOfLoan</button>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="manageinsurance?MaxAmountOfLoan=true">ASC</a></li>
                                <li><a class="dropdown-item" href="manageinsurance?MaxAmountOfLoan=false">DESC</a></li>
                            </ul>
                        </div>

                        <!-- Type Filter -->
                        <div class="btn-group position-static">
                            <button type="button" class="btn border btn-light dropdown-toggle px-4" data-bs-toggle="dropdown" aria-expanded="false">Type</button>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="manageinsurance?typeIns=Secured Loan">Secured Loan</a></li>
                                <li><a class="dropdown-item" href="manageinsurance?typeIns=Unsecured Loan">Unsecured Loan</a></li>
                            </ul>
                        </div>

                        <!-- Status Filter -->
                        <div class="btn-group position-static">
                            <button type="button" class="btn border btn-light dropdown-toggle px-4" data-bs-toggle="dropdown" aria-expanded="false">Status</button>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="manageinsurance?status=true">Active</a></li>
                                <li><a class="dropdown-item" href="manageinsurance?status=false">Inactive</a></li>
                            </ul>
                        </div>

                        <!-- Add Insurance Button -->
                        <div class="btn-group position-static">
                            <button class="btn btn-pending js-toggle" toggle-target="#modal-addInsurance">Add Insurance</button>
                        </div>
                    </div>
                </div>

                <form action="manageinsurance" method="get">
                    <select name="pageSize" onchange="this.form.submit()">
                        <option value="5" ${pageSize == 5 ? 'selected' : ''}>5</option>
                        <option value="10" ${pageSize == 10 ? 'selected' : ''}>10</option>
                        <option value="30" ${pageSize == 30 ? 'selected' : ''}>30</option>
                        <option value="40" ${pageSize == 40 ? 'selected' : ''}>40</option>
                    </select>
                    <input type="hidden" name="page" value="1">
                    <input type="hidden" name="searchIsu" value="${searchIsu}">
                    <input type="hidden" name="typeIns" value="${typeIns}">
                    <input type="hidden" name="status" value="${status}">
                    <input type="hidden" name="sortFee" value="${sortFee}">
                    <input type="hidden" name="CoverageRate" value="${CoverageRate}">
                    <input type="hidden" name="MaxAmountOfLoan" value="${MaxAmountOfLoan}">
                </form>      
            </div>

            <div class="card mt-4">
                <div class="card-body">
                    <div class="product-table">
                        <div class="table-responsive white-space-nowrap">
                            <table class="table align-middle">
                                <thead class="table-light">
                                    <tr>
                                        <th>Insurance ID</th>
                                        <th>Insurance Name</th>
                                        <th>Type</th>
                                        <th>Fee rate</th>
                                        <th>Coverage Rate</th>
                                        <th>Max Amount Of Loan</th>
                                        <th>Status</th>
                                        <th>Update</th>

                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${ListInsu}" var="sal">
                                        <tr>
                                            <td>
                                                ${sal.getInsuranceID()}

                                            </td>

                                            <td>
                                                ${sal.getInsuranceName()}                   
                                            </td>

                                            <td>  
                                                ${sal.getType()}
                                            </td>
                                            <td>${sal.getFeeRate()} %</td>
                                            <td>${sal.getCoverageRate()} %</td>

                                            <td><fmt:formatNumber value="${sal.getMaxAmountOfLoan()}" pattern="#,###" /> VND</td>

                                            <td>
                                                <c:choose>
                                                    <c:when test="${sal.isStatus() == true}">
                                                        <!-- Nếu Status là true, hiển thị button "Active" màu xanh -->
                                                        <form method="post" action="manageinsurance">
                                                            <button type="submit" name="active" value="${sal.getInsuranceID()}" class="btn btn-pending">Active</button>
                                                        </form>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <!-- Nếu Status là false, hiển thị button "Inactive" màu xám -->
                                                        <form method="post" action="manageinsurance">
                                                            <button type="submit" name="inactive" value="${sal.getInsuranceID()}" class="btn btn-sent">Inactive</button>
                                                        </form>
                                                    </c:otherwise>
                                                </c:choose>

                                            </td>
                                            <td>      <button class="btn btn-pending js-toggle"  toggle-target="#modal-${sal.getInsuranceID()}"> <i class="fa fa-edit"></i></button>
                                            </td>
                                        </tr> 
                                    </c:forEach>

                                </tbody>
                            </table>
                           <div class="pagination">
                                <c:if test="${currentPage > 1}">
                                    <a href="manageinsurance?page=${currentPage - 1}&searchIsu=${searchIsu}&typeIns=${typeIns}&status=${status}&sortFee=${sortFee}&CoverageRate=${CoverageRate}&MaxAmountOfLoan=${MaxAmountOfLoan}&pageSize=${pageSize}" class="prev">Previous</a>
                                </c:if>
                                <c:forEach var="i" begin="1" end="${totalPages}">
                                    <a href="manageinsurance?page=${i}&searchIsu=${searchIsu}&typeIns=${typeIns}&status=${status}&sortFee=${sortFee}&CoverageRate=${CoverageRate}&MaxAmountOfLoan=${MaxAmountOfLoan}&pageSize=${pageSize}" class="${i == currentPage ? 'active' : ''}">${i}</a>
                                </c:forEach>
                                <c:if test="${currentPage < totalPages}">
                                    <a href="manageinsurance?page=${currentPage + 1}&searchIsu=${searchIsu}&typeIns=${typeIns}&status=${status}&sortFee=${sortFee}&CoverageRate=${CoverageRate}&MaxAmountOfLoan=${MaxAmountOfLoan}&pageSize=${pageSize}" class="next">Next</a>
                                </c:if>
                            </div>

                        </div>


                    </div>
                </div>
            </div>


        </main>
        <!--end main content-->

        <c:forEach items="${ListInsu}" var="sal" >
            <div id="modal-${sal.getInsuranceID()}" class="modal modal--large hide">
                <div class="modal__content">
                    <button class="modal__close js-toggle" toggle-target="#modal-${sal.getInsuranceID()}">&times;</button>
                    <div style="color: #008000;
                         text-align: center;">
                        <h2>Update Insurance</h2>
                    </div>

                    <div class="modal__text">
                        <label for="insuranceName">Insurance Name:</label>
                        <input  id="insuranceName" value="${sal.getInsuranceName()}" readonly="">
                    </div>
                    <form action="updateinsurance" method="get" id="updateinsurance-${sal.getInsuranceID()}">

                        <div class="row">  
                            <div class="col-6">
                                <div class="form__group modal__text">
                                    <label for="type">TYPE:</label>
                                    <select name="type" id="type">
                                        <option value="">-- Select Type --</option>
                                        <option value="Secured Loan" ${sal.getType() == 'Secured Loan' ? 'selected' : ''}>Secured Loan</option>
                                        <option value="Unsecured Loan" ${sal.getType() == 'Unsecured Loan' ? 'selected' : ''}>Unsecured Loan</option>
                                    </select
                                    <p class="form__error"></p>         
                                </div>

                                <div class="form__group modal__text">
                                    <label for="feerate">Fee rate: </label>
                                    <div class="form__text-input">
                                        <input type="number" step="0.01"  name="feerate" id="feerate" value="${sal.getFeeRate()}"
                                               class="form__input" placeholder="Fee Rate" >
                                        <span   class="sub-text-input1">%/năm</span>
                                    </div>
                                    <p class="form__error"></p>
                                </div>       
                            </div>
                            <div class="col-6">
                                <div class="form__group modal__text">
                                    <label for="coverage">Coverage Rate:</label>
                                    <div class="form__text-input" >                                        
                                        <input  step="0.01" name="coverage" type="number" id="coverage" value="${sal.getCoverageRate()}" 
                                                class="form__input" placeholder="Cover Rate"
                                                >

                                        <span class="sub-text-input1">%/năm</span>
                                    </div>
                                    <p class="form__error"></p>
                                </div>
                                <div class="modal__text">
                                    <label for="maxamount">Max Amount Of Loan:</label>

                                    <div class="form-group__input-wrap">
                                        <input type="text" class="form-group__input" id="maxamount" name="maxamount" value="<fmt:formatNumber value="${sal.getMaxAmountOfLoan()}" pattern="#,###" />" 
                                               oninput="formatNumber(this)" onkeypress="return validateInput(event)" required>
                                        <span class="sub-text-input">VNĐ</span>
                                    </div>
                                </div>
                            </div>
                        </div>


                        <input type="hidden" name="isuid" value="${sal.getInsuranceID()}">



                        <div class="modal__text" style="text-align: center;">
                            <button class="btn btn-pending" type="submit">Update</button>
                        </div>
                    </form>
                </div>
                <div class="modal__overlay js-toggle" toggle-target="#modal-${sal.getInsuranceID()}"></div>
            </div>
        </c:forEach>



        <!--        add insurance-->
        <div id="modal-addInsurance" class="modal modal--large hide">
            <div class="modal__content">
                <button class="modal__close js-toggle" toggle-target="#modal-addInsurance">&times;</button>
                <div style="color: #008000;
                     text-align: center;">
                    <h2>Add Insurance</h2>
                </div>
                <form action="updateinsurance" method="post" id="updateinsurance1">
                    <div class="modal__text">
                        <label for="insuranceName">Insurance Name:</label>
                        <input type="text" id="insuranceName" name="insuranceName1" >
                    </div>


                    <div class="row">  
                        <div class="col-6">
                            <div class="modal__text">
                                <label for="type">TYPE:</label>
                                <select name="type1" id="type">
                                    <option value="">-- Select Type --</option>
                                    <option value="Secured Loan" ${sal.getType() == 'Secured Loan' ? 'selected' : ''}>Secured Loan</option>
                                    <option value="Unsecured Loan" ${sal.getType() == 'Unsecured Loan' ? 'selected' : ''}>Unsecured Loan</option>
                                </select>
                                <p class="form__error"></p>

                            </div>
                            <div class="form__group modal__text">
                                <label for="feerate">Fee rate: </label>
                                <div class="form__text-input">
                                    <input type="number" step="0.01"  name="feerate1" id="feerate1" value="${sal.getFeeRate()}"
                                           class="form__input" placeholder="Fee Rate" >
                                    <span   class="sub-text-input1">%/năm</span>
                                </div>
                                <p class="form__error"></p>
                            </div>       
                        </div>
                        <div class="col-6">
                            <div class="form__group modal__text">
                                <label for="coverage">Coverage Rate:</label>
                                <div class="form__text-input" >                                        
                                    <input  step="0.01" name="coverage1" type="number" id="coverage1" value="${sal.getCoverageRate()}" 
                                            class="form__input" placeholder="Cover Rate"
                                            >

                                    <span class="sub-text-input1">%/năm</span>
                                </div>
                                <p class="form__error"></p>
                            </div>
                            <div class="modal__text">
                                <label for="maxamount">Max Amount Of Loan:</label>

                                <div class="form-group__input-wrap">
                                    <input type="text" class="form-group__input" id="maxamount" name="maxamount1"  
                                           oninput="formatNumber(this)" onkeypress="return validateInput(event)" required>
                                    <span class="sub-text-input">VNĐ</span>
                                </div>

                            </div>
                        </div>
                    </div>





                    <div class="modal__text" style="text-align: center;">
                        <button class="btn btn-pending" type="submit">Add</button>
                    </div>
                </form>
            </div>

            <div class="modal__overlay js-toggle" toggle-target="#modal-addInsurance"></div>
        </div>



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
        <script src="assets/js/validationForm.js"></script>
        <script>
            <c:forEach items="${ListInsu}" var="sal" >
                                               Validator({

                                                   form: '#updateinsurance-${sal.getInsuranceID()}',
                                                   formGroupSelector: '.form__group',
                                                   errorSelector: '.form__error',
                                                   rules: [
                                                       Validator.isLessThan('#feerate', 30, 'Vui lòng nhập giá trị nhỏ hơn 30%'),
                                                       Validator.isLessThan('#coverage', 100, 'Vui lòng nhập giá trị nhỏ hơn 100%')

                                                   ],
                                                   onsubmit: function (formValue) {
                                                       document.querySelector('#updateinsurance-${sal.getInsuranceID()}').submit();
                                                   }
                                               })

            </c:forEach>

                                               Validator({
                                                   form: '#updateinsurance1',
                                                   formGroupSelector: '.form__group',
                                                   errorSelector: '.form__error',
                                                   rules: [
                                                       Validator.isLessThan('#feerate1', 30, 'Vui lòng nhập giá trị nhỏ hơn 30%'),
                                                       Validator.isLessThan('#coverage1', 100, 'Vui lòng nhập giá trị nhỏ hơn 100%')

                                                   ],
                                                   onsubmit: function (formValue) {
                                                       document.querySelector('#updateinsurance1').submit();
                                                   }
                                               })

        </script>
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

    </body>

</html>