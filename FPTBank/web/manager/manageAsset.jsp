<%-- 
    Document   : login
    Created on : Jan 13, 2025, 3:18:30 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html lang="en" data-bs-theme="light">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Manage</title>

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
        <link href="assets/css/extra-icons.css" rel="stylesheet">
        <link href="assets/css/main.css" rel="stylesheet">
        <link href="assets/css/dark-theme.css" rel="stylesheet">
        <link href="assets/css/semi-dark-theme.css" rel="stylesheet">
        <link href="assets/css/minimal-theme.css" rel="stylesheet">
        <link href="assets/css/shadow-theme.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        
        <style>
            detail-icon {
                cursor: pointer;
                color: #007bff;
                font-size: 1.2em;
                transition: color 0.3s ease;
            }

            .detail-icon:hover {
                color: #0056b3;
            }

            .modal {
                display: none; /* Ẩn modal mặc định */
                position: fixed; /* Cố định vị trí */
                z-index: 1000; /* Đảm bảo modal hiển thị trên tất cả các phần tử khác */
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                overflow: auto;
                background-color: rgba(0, 0, 0, 0.5); /* Màu nền mờ */
            }

            .modal-content {
                background-color: #fff;
                margin: 10% auto;
                padding: 20px;
                border: 1px solid #888;
                border-radius: 8px;
                width: 80%;
                max-width: 1200px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
                position: relative; /* Để nút đóng có thể định vị đúng */
            }

            .close {
                color: #aaa;
                position: absolute; /* Định vị nút đóng */
                top: 10px;
                right: 10px;
                font-size: 28px;
                font-weight: bold;
                cursor: pointer;
                transition: color 0.3s ease;
            }

            .close:hover,
            .close:focus {
                color: #000;
                text-decoration: none;
            }
            .modal-body {
                display: flex;
                align-items: flex-start;
                gap: 20px;
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

        </style>

        <script>
//            function openModal(button) {
//                var modal = document.getElementById("modal");
//                var modalImage = document.getElementById("modalImage");
//                var image = button.parentElement.querySelector('.clickable-image'); // Lấy hình ảnh từ thẻ cha
//                modalImage.src = image.src; // Lấy đường dẫn ảnh từ hình ảnh đã nhấn
//                modal.style.display = "flex"; // Hiện modal
//            }
//
//            function closeModal() {
//                document.getElementById("modal").style.display = "none"; // Ẩn modal khi nhấn bên ngoài
//            }
//

        </script> 
        <script>
            function submitSortForm(order) {
                document.getElementById('sortOrder').value = order; // Gán giá trị cho trường ẩn
                document.getElementById('sortForm').submit(); // Gửi form
            }

        </script> 
        <script>
            function submitSortForm1(order) {
                document.getElementById('sortDate').value = order; // Gán giá trị cho trường ẩn
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
            function submitSearch() {
                var searchInput = document.getElementById('searchInput').value;
                if (searchInput.trim() !== "") {
                    document.getElementById('searchForm').submit();
                } else {
                    alert("Please enter a search term."); // Thông báo nếu ô tìm kiếm rỗng
                }
            }
        </script>

        <script>
            function showAssetDetails(image, description, value, date,id) {
                document.getElementById("modalImage").src = image;
                document.getElementById("modalDescription").innerHTML = description;
                document.getElementById("modalValue").innerText = value;
                document.getElementById("modalDate").innerText = date;
                document.getElementById("modalidAss").innerText = id;
                document.getElementById("assetDetailModal").style.display = "block";
            }
            function closeAssetModal() {
                document.getElementById("assetDetailModal").style.display = "none";
            }

        </script>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                const commentElements = document.querySelectorAll(".comment-text");

                commentElements.forEach((element) => {
                    const fullComment = element.getAttribute("data-full-comment");
                    if (fullComment.length > 50) {
                        element.textContent = fullComment.substring(0, 50) + "...";
                    }
                });
            });
        </script>
        <script>
            function showFullComment(comment) {
                 var formattedComment = comment.replace(/\n/g, '<br>');
                document.getElementById('fullCommentContent').innerHTML = formattedComment;
                document.getElementById('commentDetailModal').style.display = 'block';
            }

            function closeCommentModal() {
                document.getElementById("commentDetailModal").style.display = "none";
            }

        </script>
        <script>
            function viewPdf(filenames) {
                // Lấy giá trị từ span
                var modalValue = document.getElementById("modalidAss").innerText;

                // Kết hợp giá trị từ span với filenames
                var fileName =  filenames+modalValue ; // Điều chỉnh tùy theo cách bạn muốn kết hợp

                // Chuyển hướng đến URL
                window.location.href = "viewPdf?fileName=" + encodeURIComponent(fileName);
            }
        </script>
        <script type="text/javascript">

           function formatNumber(input) {
                // Loại bỏ tất cả các ký tự không phải số
                let value = input.value.replace(/[^0-9]/g, '');

                // Thêm dấu phẩy sau mỗi 3 chữ số
                value = value.replace(/\B(?=(\d{3})+(?!\d))/g, '.');

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
                    <h5 class="mb-0">Roksyn</h5>
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
                        <a href="listAsset" class="has-arrow">
                            <div class="parent-icon"><span class="material-symbols-outlined">shopping_cart</span>
                            </div>
                            <div class="menu-title">Management</div>
                        </a>
                        <ul>
                            <!--                    <li> <a href="ecommerce-add-product.html"><span class="material-symbols-outlined">arrow_right</span>Add Product</a>
                                                </li>
                                                <li> <a href="ecommerce-products.html"><span class="material-symbols-outlined">arrow_right</span>Products</a>
                                                </li>-->
                    </li>
                    <li> <a href="listAsset"><span class="material-symbols-outlined">arrow_right</span>Asset</a>
                    </li>
                    <li> <a href="listSalary"><span class="material-symbols-outlined">arrow_right</span>Salary</a>
                    </li>
                    <!--                    <li> <a href="ecommerce-customer-details.html"><span class="material-symbols-outlined">arrow_right</span>Customer Details</a>
                                        </li>
                                        <li> <a href="ecommerce-orders.html"><span class="material-symbols-outlined">arrow_right</span>Orders</a>
                                        </li>
                                        <li> <a href="ecommerce-customer-details.html"><span class="material-symbols-outlined">arrow_right</span>Order Details</a>
                                        </li>-->
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
                        <h5 class="mb-0 user-name">${sessionScope.manage.username}</h5>
                        <p class="mb-0 user-designation">${sessionScope.manage.email}</p>
                    </div>


                </div>
                <ul class="dropdown-menu dropdown-menu-end">

                    <li><a class="dropdown-item" href="javascript:;"><span class="material-symbols-outlined me-2">
                                account_circle
                            </span><span>Profile</span></a>
                    </li>

                    <!--                <li><a class="dropdown-item" href="javascript:;"><span class="material-symbols-outlined me-2">
                                      tune
                                      </span><span>Settings</span></a>
                                    </li>-->

                    <!--                <li><a class="dropdown-item" href="javascript:;"><span class="material-symbols-outlined me-2">
                                      dashboard
                                      </span><span>Dashboard</span></a>
                                    </li>-->

                    <!--                <li><a class="dropdown-item" href="javascript:;"><span class="material-symbols-outlined me-2">
                                      account_balance
                                      </span><span>Earnings</span></a>
                                    </li>-->

                    <!--                <li><a class="dropdown-item" href="javascript:;"><span class="material-symbols-outlined me-2">
                                      cloud_download
                                      </span><span>Downloads</span></a>
                                    </li>-->
                    <!--                <li>
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

            <div class="row g-3">
                <div class="col-auto">
                    <form id="searchForm" action="sort" method="get">   
                        <div class="position-relative">
                            <input id="searchInput" class="form-control px-5" type="search" name="search" placeholder="Search Products">
                            <span class="material-symbols-outlined position-absolute ms-3 translate-middle-y start-0 top-50 fs-5" 
                                  onclick="submitSearch();">
                                search
                            </span>
                        </div>
                    </form>
                </div>
                <div class="col-auto flex-grow-1 overflow-auto">
                    <div class="btn-group position-static">
                        <div class="btn-group position-static">


                            <a href="listAsset">
                                <button type="button" class="btn border btn-light px-4" >
                                    All
                                </button>
                            </a>
                        </div>
                        <div class="btn-group position-static">
                            <button type="button" class="btn border btn-light dropdown-toggle px-4" data-bs-toggle="dropdown" aria-expanded="false">
                                Value
                            </button>
                            <form action="sort" method="get" id="sortForm">
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" onclick="submitSortForm('asc')">Low to High</a></li>
                                    <li><a class="dropdown-item" onclick="submitSortForm('desc')">High to Low</a></li>
                                </ul>
                                <input type="hidden" name="sortOrder" id="sortOrder">
                            </form>

                        </div>
                        <div class="btn-group position-static">
                            <button type="button" class="btn border btn-light dropdown-toggle px-4" data-bs-toggle="dropdown" aria-expanded="false">
                                Date
                            </button>
                            <form action="sort" method="get" id="sortForm1">
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item"  onclick="submitSortForm1('asc')">Latest</a></li>
                                    <li><a class="dropdown-item"  onclick="submitSortForm1('desc')">Early</a></li>
                                </ul>
                                <input type="hidden" name="sortDate" id="sortDate">
                            </form>
                        </div>
                        <div class="btn-group position-static">
                            <button type="button" class="btn border btn-light dropdown-toggle px-4" data-bs-toggle="dropdown" aria-expanded="false">
                                Used
                            </button>
                            <form action="sort" method="get" id="sortForm3">
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" onclick="submitSortForm3('true')">Used</a></li>
                                    <li><a class="dropdown-item" onclick="submitSortForm3('false')">non-Use</a></li>
                                </ul>
                                <input type="hidden" name="used" id="verify">
                            </form>
                        </div>
                        <div class="btn-group position-static">
                            <button type="button" class="btn border btn-light dropdown-toggle px-4" data-bs-toggle="dropdown" aria-expanded="false">
                                Status
                            </button>
                            <form action="sort" method="get" id="sortForm2">
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item"  onclick="submitSortForm2('Pending')">Pending</a></li>
                                    <li><a class="dropdown-item"  onclick="submitSortForm2('Adjusting')">Adjusting</a></li>
                                    <li><a class="dropdown-item"  onclick="submitSortForm2('Adjusted')">Adjusted</a></li>
                                    <li><a class="dropdown-item"  onclick="submitSortForm2('Approved')">Approved</a></li>
                                </ul>
                                <input type="hidden" name="status" id="status">
                            </form>

                        </div>
                    </div>  
                </div>


                <div class="card mt-4">
                    <div class="card-body">
                        <div class="product-table">
                            <div class="table-responsive white-space-nowrap">
                                <table class="table align-middle">
                                    <thead class="table-light">
                                        <tr>
                                            <th>Customer ID</th>
                                            <th>Asset ID</th>
                                            <th>Comments</th>
                                            <th>Valuation Amount</th>
                                            <th>Used</th>
                                            <th>Status</th>
                                            <th>Action</th>
                                            <th>Confirm</th>
                                        </tr>
                                    </thead>
                                    <tbody>


                                        <c:if test="${requestScope.data!= null}">
                                            <c:forEach items="${requestScope.data}" var="asset">
                                                <tr>
                                            <form action="listAsset" method="post">
                                                <td>

                                                    <a href="customer?cid=${asset.getCustomerId()}"class="bi bi-person-circle" title="Xem chi tiết">
                                                        ${asset.getCustomerId()}</a>

                                                </td>
                                                <td>
                                                    <i class="fas fa-info-circle detail-icon" 
                                                       onclick="showAssetDetails('${asset.getImage()}', '${asset.getDescription()}', '${asset.getValue()}', '${asset.getCreatedAt()}', '${asset.getId()}')"
                                                       title="Xem chi tiết">
                                                    </i>
                                                    <span>${asset.getId()}</span>
                                                </td>


                                                <td>
                                                    <c:choose>
                                                        
                                                        <c:when test="${empty asset.getComments()}">
                                                            <textarea type="text" class="comment-input"
                                                                      placeholder="Nhập comment"
                                                                      name="comment_${asset.getId()}"required></textarea> 
                                                        </c:when>
                                                        
                                                        <c:otherwise>
                                                            <span class="comment-text " data-full-comment="${asset.getComments()}">
                                                                ${asset.getComments()}
                                                            </span>
                                                            <i class="fas fa-info-circle detail-icon" 
                                                               onclick="showFullComment('${asset.getComments()}')"
                                                               title="Xem chi tiết"> 
                                                            </i>

                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>

                                                <td>  
                                                    <c:choose>
                                                        <c:when test="${empty asset.getValuationAmount()}">
                                                            <input type="number" class="form-control"  id="AmountAsset"
                                                                   placeholder="Nhập giá trị định giá"
                                                                   name="valuationAmount_${asset.getId()}" />
                                                        </c:when>
                                                        <c:otherwise>
                                                            <span >
                                                                <fmt:formatNumber value=" ${asset.getValuationAmount()}" pattern="###,###"/>
                                                               
                                                            </span>
                                                        
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <c:if test="${asset.isUsed() == false}">
                                                    <td>Non-Use</td>
                                                </c:if>
                                                <c:if test="${asset.isUsed() != false}">
                                                    <td>Used</td>
                                                </c:if>
                                                <td>${asset.getStatus()}</td>

                                                <input hidden type="text" name="assetid" value="${asset.getId()}">
                                                <td>
                                                    <div class="form-group">
                                                        <select class="form-select" id="assetAction${asset.getId()}"name="action" required>
                                                            <option value="">Select an action</option>
                                                            <option value="Adjusting">Adjusting</option>
                                                            <option value="Approved">Approved</option>
                                                        </select>
                                                    </div>
                                                </td>
                                               <c:if test="${asset.getStatus() eq 'Approved'}">
                                                    <td><button type="submit" style="display:none;">Confirm</button></td>
                                                </c:if>
                                                <c:if test="${asset.getStatus() ne 'Approved'}">
                                                    <td><button type="submit">Confirm</button></td>
                                                </c:if>  
                                            </form>

                                            </tr> 
                                        </c:forEach>

                                    </c:if>


                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="assetDetailModal" class="modal">
                    <div class="modal-content">
                        <span class="close" onclick="closeAssetModal()">&times;</span> <!-- Nút đóng -->
                        <div class="modal-body">
                            <img id="modalImage" src="" alt="" class="modal-image">
                            <div class="modal-info">
                                <span id="modalidAss"></span>
                                <p><strong>Date of request:</strong> <span id="modalDate"></span></p>
                                <p><strong>Description:</strong> <span id="modalDescription"></span></p>
                                <p><strong>Value:</strong> <span id="modalValue"></span></p>
                                
                                <td>
                                    <a href="#" onclick="viewPdf('${requestScope.filenames[0]}')">Xem thêm</a>
                                </td>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="commentDetailModal" class="modal">
                    <div class="modal-content">
                        <span class="close" onclick="closeCommentModal()">&times;</span>
                        <div class="modal-info">
                            <p><strong>Comment:</strong> <span id="fullCommentContent"></span></p>
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
    </body>

</html>