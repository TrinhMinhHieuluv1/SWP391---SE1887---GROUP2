<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Add Asset - TIMI ROKSYN</title>
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

        <!-- Favicon -->
        <link rel="shortcut icon" href="img/favicon.png" type="image/x-icon">
        <link rel="icon" href="img/favicon.png" type="image/x-icon">
        <!-- Toarst -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
        <style>
            /* Reset default styles */
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
                font-family: Arial, sans-serif;
            }

            /* Main layout */
            body {
                display: flex;
                min-height: 100vh;
                background-color: #F4F7FC;
            }

            /* Sidebar */
            .sidebar {
                width: 200px;
                background-color: #1A3C34;
                color: white;
                padding: 20px;
                height: 100vh;
                position: fixed;
            }

            .sidebar .logo {
                font-size: 20px;
                font-weight: bold;
                margin-bottom: 30px;
            }

            .sidebar ul {
                list-style: none;
            }

            .sidebar ul li {
                margin-bottom: 20px;
            }

            .sidebar ul li a {
                color: white;
                text-decoration: none;
                font-size: 16px;
            }

            .sidebar ul li a:hover {
                color: #2196F3;
                font-weight: bold;
            }

            /* Main content */
            .main-content {
                margin-left: 200px;
                padding: 20px;
                width: calc(100% - 200px);
                background-color: #FFFFFF;
            }

            /* Top bar */
            .top-bar {
                display: flex;
                justify-content: space-between;
                align-items: center;
                padding: 10px 20px;
                background-color: #FFFFFF;
                border-bottom: 1px solid #E0E0E0;
            }

            .top-bar .logo {
                font-size: 20px;
                font-weight: bold;
            }

            .top-bar .search-bar {
                display: flex;
                align-items: center;
                gap: 10px;
            }

            .top-bar .search-bar input {
                padding: 5px;
                border: 1px solid #E0E0E0;
                border-radius: 5px;
                width: 200px;
            }

            .top-bar .icons {
                display: flex;
                gap: 10px;
            }

            .top-bar .icons i {
                font-size: 18px;
                color: #757575;
            }

            /* Form Section */
            .form-section {
                margin-top: 20px;
            }

            .form-section h2 {
                font-size: 24px;
                color: #000000;
                margin-bottom: 20px;
            }

            .form-group {
                margin-bottom: 15px;
            }

            .form-group label {
                display: block;
                font-size: 14px;
                color: #757575;
                margin-bottom: 5px;
            }

            .form-group input,
            .form-group textarea {
                width: 100%;
                padding: 8px;
                border: 1px solid #E0E0E0;
                border-radius: 5px;
                font-size: 14px;
            }

            .form-group .upload-btn {
                display: inline-block;
                padding: 8px 15px;
                background-color: #2196F3;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }

            .buttons {
                display: flex;
                justify-content: flex-end;
                gap: 10px;
                margin-top: 20px;
            }

            .buttons button {
                padding: 10px 20px;
                border-radius: 5px;
                font-size: 14px;
                cursor: pointer;
            }

            .buttons .cancel-btn {
                background-color: #E0E0E0;
                color: #000000;
                border: none;
            }

            .buttons .submit-btn {
                background-color: #4CAF50;
                color: white;
                border: none;
            }
            .notification {
                position: fixed;
                top: -100px;
                left: 50%;
                transform: translateX(-50%);
                padding: 20px 30px;
                border-radius: 15px;
                box-shadow: 0 10px 30px rgba(220, 53, 69, 0.3),
                    0 0 0 1px rgba(220, 53, 69, 0.2);
                display: flex;
                align-items: center;
                gap: 15px;
                z-index: 10000;
                transition: all 0.5s cubic-bezier(0.68, -0.55, 0.265, 1.55);
                min-width: 400px;
                border: 1px solid rgba(255, 255, 255, 0.2);
                backdrop-filter: blur(10px);
            }
            .notification.show {
                top: 30px;
                background: linear-gradient(135deg, #ff6b6b 0%, #dc3545 100%);
                animation: shake 0.8s cubic-bezier(.36,.07,.19,.97) both;
            }
            .notification.succ {
                top: 30px;
                background: linear-gradient(135deg, #28a745 0%, #218838 100%);
                animation: shake 0.8s cubic-bezier(.36,.07,.19,.97) both;
            }
            @keyframes shake {
                10%, 90% {
                    transform: translateX(calc(-50% + 1px));
                }
                20%, 80% {
                    transform: translateX(calc(-50% - 2px));
                }
                30%, 50%, 70% {
                    transform: translateX(calc(-50% + 4px));
                }
                40%, 60% {
                    transform: translateX(calc(-50% - 4px));
                }
            }
            .notification-icon {
                width: 45px;
                height: 45px;
                background: rgba(255, 255, 255, 0.95);
                border-radius: 50%;
                display: flex;
                align-items: center;
                justify-content: center;
                flex-shrink: 0;
                animation: pulse 2s infinite;
            }
            @keyframes pulse {
                0% {
                    transform: scale(1);
                }
                50% {
                    transform: scale(1.05);
                }
                100% {
                    transform: scale(1);
                }
            }
            .notification-icon .show {
                color: #dc3545;
                font-size: 1.4em;
            }
            .notification-icon .succ {
                color: #28a745;
                font-size: 1.4em;
            }
            .notification-content {
                flex-grow: 1;
            }
            .notification-title {
                color: white;
                font-weight: 600;
                margin: 0 0 5px 0;
                font-size: 1.1em;
                text-shadow: 0 1px 2px rgba(0, 0, 0, 0.1);
            }
            .notification-message {
                color: rgba(255, 255, 255, 0.95);
                margin: 0;
                font-size: 0.95em;
                line-height: 1.4;
                text-shadow: 0 1px 2px rgba(0, 0, 0, 0.1);
            }
            .notification-close {
                color: rgba(255, 255, 255, 0.8);
                background: rgba(255, 255, 255, 0.1);
                border: none;
                font-size: 1.2em;
                cursor: pointer;
                padding: 8px;
                border-radius: 50%;
                width: 30px;
                height: 30px;
                display: flex;
                align-items: center;
                justify-content: center;
                transition: all 0.3s ease;
                margin-left: 5px;
            }
            .notification-close:hover {
                color: white;
                background: rgba(255, 255, 255, 0.2);
                transform: rotate(90deg);
            }
            @media (max-width: 480px) {
                .notification {
                    min-width: 90%;
                    margin: 0 20px;
                }
            }
        </style>
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
        <!-- Sidebar -->
        <div class="sidebar">
            <div class="logo"style="color: white">My Asset And Salary</div>
            <ul>
                <li><a href="myassetsalary" >My Assets</a></li>
                <li><a href="mysalary" >My Salaries</a></li>
            </ul>
        </div>

        <!-- Main Content -->
        <div class="main-content">
            <c:if test="${requestScope.messType != null}">
                <div id="notification" class="notification ${requestScope.messType eq 'false' ? 'show' : 'succ'}">
                    <div class="notification-icon">
                        <i class="fa fa-exclamation-circle ${requestScope.messType eq 'false' ? 'show' : 'succ'}"></i>
                    </div>
                    <div class="notification-content">
                        <h4 class="notification-title">Message</h4>
                        <p class="notification-message">${requestScope.mess}</p>
                    </div>
                    <button class="notification-close" onclick="closeNotification()">&times;</button>
                </div>
            </c:if>


            <script>
                function closeNotification() {
                    const notification = document.getElementById('notification');
                    notification.classList.remove('show');
                    notification.classList.remove('succ');
                    setTimeout(() => {
                        notification.style.display = 'none';
                    }, 500);
                }

                // Auto close after 5 seconds
                if (document.getElementById('notification').classList.contains('show')) {
                    setTimeout(closeNotification, 5000);
                }
                if (document.getElementById('notification').classList.contains('succ')) {
                    setTimeout(closeNotification, 5000);
                }
            </script>
            <!-- Top Bar -->
            <div class="top-bar">
                <div class="logo">My Asset And Salary</div>

            </div>

            <!-- Form Section -->
            <div class="form-section">
                <h2>Add New Salary</h2>
                <form action="insertSalary" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="assetName">Salary title</label>
                        <input type="text" id="assetName" name="name" required>
                        <c:if test="${requestScope.messT!=null}">
                            <div style="color: red">${requestScope.messT}</div>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <label for="editValuation">Valuation</label>                   
                        <input type="text" 
                               class="form-control form-control-sm" 
                               id="editValuation"
                               oninput="formatNumber(this)" onkeypress="return validateInput(event)"
                               name="value"
                               placeholder="Enter amount"required>
                    </div>
                    <div class="form-group">
                        <label for="comments">Description</label>
                        <textarea id="comments" name="description" rows="4"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="file">Attach Image</label>
                        <input id="file-image" type="file" name="fileImage" accept="image/jpeg, image/png" required/>
                        <div>${requestScope.errorImage}</div>
                    </div>
                    <div class="buttons">
                        <button type="button" class="cancel-btn" onclick="window.location.href = 'mysalary'">Cancel</button>
                        <button type="submit" class="submit-btn">Submit</button>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>