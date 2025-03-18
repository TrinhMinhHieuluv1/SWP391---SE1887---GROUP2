<%-- 
    Document   : faq-management
    Created on : Feb 13, 2025, 1:15:17 PM
    Author     : ADMIN
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Calculation Management</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="HandheldFriendly" content="true">
        <meta name="author" content="bslthemes" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <!-- switzer font css -->
        <link rel="stylesheet" href="fonts/css/switzer.css" type="text/css" media="all">
        <!-- font awesome css -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">        <!-- bootstrap grid css -->
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

            /* Search Box */
            .search-container {
                position: relative;
                margin-bottom: 20px;
                max-width:50%;
                display: flex;
                gap: 10px;

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
            .filter-controls h3{
                text-align: center;
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

            /* Toast Message Styles */
            .toast-message1 {
                position: fixed;
                top: -100px; /* Start above viewport */
                left: 50%;
                transform: translateX(-50%);
                background-color: #FF0000;
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
                 background-color: #f0f0f0;
           
            }

            .show-all-news-btn1{
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
                background-color: #008000;
                color: white;
            }

            .add-news-btn:hover {
                background-color: #43a047;
                transform: translateY(-2px);
                box-shadow: 0 4px 6px rgba(76,175,80,0.2);
            }
            
           
        }
            .checkbox-bar {
                display: flex;
                justify-content: space-around;
                background-color: #f4f4f4;
                padding: 10px;
            }
            .checkbox-item {
                display: flex;
                align-items: center;
            }
            .checkbox-item label {
                margin-left: 5px;
            }

            select {
                margin-top: 15px;
                background-color: #008000;
                color: white ;
                padding: 3px 8px; /* Giảm padding để ô nhỏ hơn */
                border-radius: 6px; /* Bo tròn góc nhẹ */
                font-size: 14px; /* Giảm kích thước chữ */
                cursor: pointer;
                margin-bottom: 20px;
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

            @media (max-width: 768px) {
                .news-management {
                    padding: 15px;
                }

                .news-table {
                    display: block;
                    overflow-x: auto;
                    width: 100%;
                    table-layout: fixed;
                }

                .action-button {
                    padding: 6px 12px;
                    font-size: 0.85em;
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


        <div class="news-management">
            <h1 class="page-title">Calculation Management</h1>
            <!-- Filter Controls -->
            <form action="managesav" method="get" class="filter-controls">
                <!-- Search Box -->
                <div class="search-container"  >
                    <i class="fa fa-search"></i>
                    <input   type="text" name="searchSav" value="${searchSav}" placeholder="Search by name..." class="search-input">
                    <button class="add-news-btn" type="submit" >Search </button>
                </div>
            </form>
            <form  action="managesav" method="GET">
               

                    <label for="status">Trạng thái:</label>
                    <select id="status" name="status">
                        <option value="true">Đã gửi</option>
                        <option value="false">Chưa gửi</option>
                    </select>
                    <button class="add-news-btn" type="submit">Tìm kiếm</button>
             

            </form>




            <button class="show-all-news-btn" onclick=" window.location.href = '/timibank/seller/showcalsaving'">
                Show All Saving
            </button>

            <button class="show-all-news-btn" onclick=" window.location.href = '/timibank/seller/showcalloan'">
                Show All Loan
            </button>
       <!-- Dropdown cho sắp xếp theo số lượng -->
            <div  class="show-all-news-btn1">
                <button class="btn btn-secondary dropdown-toggle" style="background-color: #008000" type="button" id="dropdownAmount" data-bs-toggle="dropdown" aria-expanded="false">
                    Amount
                </button>
                <form action="managesav" method="get" id="sortForm">
                    <ul class="dropdown-menu"  aria-labelledby="dropdownAmount">
                        <li><a class="dropdown-item" href="#" onclick="submitSortForm('asc')">Tăng dần</a></li>
                        <li><a class="dropdown-item" href="#" onclick="submitSortForm('desc')">Giảm dần</a></li>
                    </ul>
                    <input type="hidden" name="sortOrder" id="sortOrderInput">
                </form>
            </div>

            <!-- Dropdown cho sắp xếp theo ngày -->
            <div class="show-all-news-btn1">
                <button class="btn btn-secondary dropdown-toggle" style="background-color: #008000" type="button" id="dropdownDate" data-bs-toggle="dropdown" aria-expanded="false">
                    Date
                </button>
                <form action="managesav" method="get" id="sortForm1">
                    <ul class="dropdown-menu" aria-labelledby="dropdownDate">
                        <li><a class="dropdown-item" href="#" onclick="submitSortForm1('asc')">Cuối cùng</a></li>
                        <li><a class="dropdown-item" href="#" onclick="submitSortForm1('desc')">Bắt đầu</a></li>
                    </ul>
                    <input type="hidden" name="sortOrderDate" id="sortOrderDateInput">
                </form>
            </div>

            <form class="show-all-news-btn1"  action="managesav" method="GET">
                <label for="searchDate">Chọn ngày:</label>
                <input value="${searchDate}" type="date" id="searchDate" name="searchDate" required>
                <button type="submit">Tìm kiếm</button>
            </form>








            <!-- News Table -->

            <table  class="news-table" border="1">

                <thead>
                    <tr>
                        <th class="id-column center-align">ID</th>                    
                        <th class="title-column">Tên</th>
                        <th class="status-column">Email</th>
                        <th class="status-column">Amount</th>
                        <th class="status-column">Rate</th>
                        <th class="status-column">CreatedDate</th>
                        <th class="status-column">Response</th>


                    </tr>
                </thead>
                <tbody> 
                    <c:forEach items="${ListSav}" var="f">

                        <tr>
                            <td class="center-align">${f.getId()}</td>                        
                            <td class="created-time">${f.getName()}</td>
                            <td class="access-count">${f.getEmail()}</td>
                            <td class="center-align">${f.getDeposit_amount()}</td>                        
                            <td class="created-time">${f.getInterest_rate()}</td>
                            <td class="access-count">${f.getCreateDate()}</td>
                            <td class="access-count">
                                <c:choose>
                                    <c:when test="${f.isStatus() == false}">
                                        <form action="emailEx"><button type="submit" name="saveID" value="${f.getId()}" class="btn btn-pending">Chưa gửi</button></form>

                                    </c:when>
                                    <c:otherwise>
                                        <button class="btn btn-sent">Đã gửi</button>
                                    </c:otherwise>
                                </c:choose>
                            </td>


                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <br/><!-- comment -->

            <div class="pagination">
                <c:if test="${currentPage > 1}">
                    <a href="?page=${currentPage - 1}&searchSav=${searchSav}&sortOrder=${sortOrder}&sortOrderDate=${sortOrderDate}&status=${status}&searchDate=${searchDate}&entries=${entries}"  class="prev" > Previous</a>
                </c:if>

                <c:forEach var="i" begin="1" end="${totalPages}">
                    <a href="?page=${i}&searchSav=${searchSav}&sortOrder=${sortOrder}&sortOrderDate=${sortOrderDate}&status=${status}&searchDate=${searchDate}&entries=${entries}" class="${i == currentPage ? 'active' : ''}">${i}</a>
                </c:forEach>

                <c:if test="${currentPage < totalPages}">
                    <a href="?page=${currentPage + 1}&searchSav=${searchSav}&sortOrder=${sortOrder}&sortOrderDate=${sortOrderDate}&status=${status}&searchDate=${searchDate}&entries=${entries}" class="next">Next</a>
                </c:if>
            </div>


        </div>

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
                    }, 3000);
                }
            });

            document.addEventListener("DOMContentLoaded", function () {
                const checkboxes = document.querySelectorAll(".faq-checkbox");

                checkboxes.forEach(checkbox => {
                    checkbox.addEventListener("change", function () {
                        checkboxes.forEach(cb => {
                            if (cb !== this) {
                                cb.checked = false;
                            }
                        });
                    });
                });
            });

// Hàm submit cho form sắp xếp theo số lượng
            function submitSortForm(value) {
                document.getElementById("sortOrderInput").value = value;
                document.getElementById("sortForm").submit();
            }

// Hàm submit cho form sắp xếp theo ngày
            function submitSortForm1(value) {
                document.getElementById("sortOrderDateInput").value = value;
                document.getElementById("sortForm1").submit();
            }

// Giữ giá trị đã chọn khi trang reload
            document.addEventListener("DOMContentLoaded", function () {
                const urlParams = new URLSearchParams(window.location.search);
                const sortOrder = urlParams.get("sortOrder");
                const sortOrderDate = urlParams.get("sortOrderDate");

                if (sortOrder) {
                    document.getElementById("sortOrderInput").value = sortOrder;
                    document.getElementById("dropdownAmount").textContent = sortOrder === "asc" ? "Sắp xếp theo số lượng: Tăng dần" : "Sắp xếp theo số lượng: Giảm dần";
                }
                if (sortOrderDate) {
                    document.getElementById("sortOrderDateInput").value = sortOrderDate;
                    document.getElementById("dropdownDate").textContent = sortOrderDate === "asc" ? "Sắp xếp theo ngày: Cuối cùng" : "Sắp xếp theo ngày: Bắt đầu";
                }
            });
            
        

        </script>

    </body>
</html>