<%-- 
    Document   : faq-management
    Created on : Feb 13, 2025, 1:15:17 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>FAQ Management</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="HandheldFriendly" content="true">
        <meta name="author" content="bslthemes" />

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
                background-color: #008000;
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

        <c:if test="${not empty requestScope.message}">
            <div id="toastMessage" class="toast-message">
                <i class="fa fa-check-circle"></i>
                ${requestScope.message}
            </div>
        </c:if>

        <div class="news-management">
            <h1 class="page-title">Feedback Management</h1>

            <!-- Filter Controls -->
            <form action="managefeedback" method="get" class="filter-controls">
                <!-- Search Box -->
                <div class="search-container">
                    <i class="fa fa-search"></i>
                    <input type="text" name="searchKeyword" placeholder="Search feedback..." class="search-input">
                </div>

                <div class="filter-group" style="display: flex;">
                    <label for="status" style="margin-left: 30px;">Status:</label>
                    <select name="filterStatus" id="status">
                        <option value="none" ${param.filterStatus == 'none' ? 'selected' : ''}>All Status</option>
                        <option value="active" ${param.filterStatus == 'active' ? 'selected' : ''}>Active</option>
                        <option value="inactive" ${param.filterStatus == 'inactive' ? 'selected' : ''}>Inactive</option>
                    </select>
                    <label for="number" style="margin-left: 30px;" >Number in Page:</label>
                    <select class="form-control" id="statusFilter" name="pagesize">
                        <c:forEach var="num" items="${requestScope.listint}">
                            <option value="${num}" ${param.pagesize == num ? 'selected' : '' }>${num}</option>
                        </c:forEach>
                    </select>
                    <label for="status" style="margin-left: 30px;">Status of Response:</label>
                    <select class="form-control" id="statusFilter" name="statusresponse">
                        <option value="" ${param.statusresponse == '' ? 'selected' :''}>-- Select Status --</option>
                        <option value="true" ${param.statusresponse == 'true' ? 'selected' : ''}>Responsed</option>
                        <option value="false" ${param.statusresponse == 'false' ? 'selected' : ''}>Not Responsed</option>
                    </select>
                    <label for="status" style="margin-left: 30px;">From:</label>
                    <input class="form-control" type="date" name="date1" placeholder="Date"></input>
                    <label for="status" style="margin-left: 30px;">To:</label>
                    <input class="form-control" type="date" name="date2" placeholder="Date"></input>
                </div>
                <button type="submit" style="background-color: green; color: white">Filter</button>
            </form>

            <button class="show-all-news-btn" onclick="window.location.href = '/timibank/seller/managefeedback'">Show All Feedback</button>

            <!-- News Table -->
            <table class="news-table">
                <thead>
                    <tr>
                        <th class="id-column center-align">FeedbackID</th>
                        <th class="author-column">Username</th>
                        <th class="title-column">Message</th>
                        <th class="status-column">Service</th>
                        <th class="status-column">StarRating</th>
                        <th class="status-column">Status Of Response</th>
                        <th class="status-column">CreatedAt</th>
                        <th class="status-column">Status</th>
                        <th class="status-column" colspan="2">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.listfeedback}" var="f">
                        
                        <tr>
                            <td class="center-align">${f.feedbackID}</td>
                            <td class="author-name">${f.customer.username}</td>
                            <td class="created-time">${f.message}</td>
                            <td class="access-count">${f.service.serviceID == 1 ? "Savings": "Loans"}</td>
                            <td><c:forEach begin="1" end="${f.starScore}">
                                    <i class="fa fa-star" style="color: gold;"></i>
                                </c:forEach>
                            </td>
                            <td class="access-count">${f.response == null ? "Not Responsed": "Responsed"}</td>
                            <td class="access-count">${f.createdAt}</td>
                            <td class="access-count"><c:if test="${!f.isStatus()}">
                                    <a href="/timibank/seller/update-feedback?FID=${f.feedbackID}&changeStatus=true"><button type="submit" class="action-button inactivate-btn">Inactivate</button></a>
                                </c:if>
                                <c:if test="${f.isStatus()}">
                                    <a href="/timibank/seller/update-feedback?FID=${f.feedbackID}&changeStatus=true"><button type="submit" class="action-button activate-btn">Activate</button></a>
                                </c:if></td>
                            <td><a href="/timibank/seller/addresponse?FID=${f.feedbackID}"><button style="color: white; background-color: green; border-radius: 5px; padding: 5px 5px;">Add Response</button></a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <!-- Pagination Controls -->
            <div class="pagination">
                <c:if test="${currentPage > 1}">
                    <a href="managefeedback?page=${currentPage - 1}&searchKeyword=${param.searchKeyword}&filterStatus=${param.filterStatus}&statusresponse=${param.statusresponse}&pagesize=${param.pagesize}&date1=${param.date1}&date2=${param.date2}" 
                       class="prev">Previous</a>
                </c:if>

                <c:forEach var="i" begin="1" end="${totalPages}">
                    <a href="managefeedback?page=${i}&searchKeyword=${param.searchKeyword}&filterStatus=${param.filterStatus}&statusresponse=${param.statusresponse}&pagesize=${param.pagesize}&date1=${param.date1}&date2=${param.date2}" class="${i == currentPage ? 'active' : ''}">${i}</a>
                </c:forEach>

                <c:if test="${currentPage < totalPages}">
                    <a href="managefeedback?page=${currentPage + 1}&searchKeyword=${param.searchKeyword}&filterStatus=${param.filterStatus}&statusresponse=${param.statusresponse}&pagesize=${param.pagesize}&date1=${param.date1}&date2=${param.date2}" class="next">Next</a>
                </c:if>
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
                    <p class="news-description" id="modalDescription"></p>
                    <div class="news-image-container">
                        <img id="modalImage" class="news-image" src="" alt="News Image">
                    </div>
                </div>
            </div>
        </div>



    </body>
</html>