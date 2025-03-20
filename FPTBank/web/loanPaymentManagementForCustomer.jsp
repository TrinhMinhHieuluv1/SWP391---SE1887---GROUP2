<%-- 
    Document   : news-management
    Created on : Feb 5, 2025, 6:50:43 PM
    Author     : HP
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
    <head>
        <title>My Loan Payment</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="HandheldFriendly" content="true">
        <meta name="author" content="bslthemes" />

        <!-- switzer font css -->
        <link rel="stylesheet" href="/fonts/css/switzer.css" type="text/css" media="all">
        <!-- font awesome css -->
        <link rel="stylesheet" href="/fonts/css/font-awesome.min.css" type="text/css" media="all">
        <!-- bootstrap grid css -->
        <link rel="stylesheet" href="/css/plugins/bootstrap-grid.css" type="text/css" media="all">
        <!-- swiper css -->
        <link rel="stylesheet" href="/css/plugins/swiper.min.css" type="text/css" media="all">
        <!-- magnific popup -->
        <link rel="stylesheet" href="/css/plugins/magnific-popup.css" type="text/css" media="all">
        <!-- plax css -->
        <link rel="stylesheet" href="/css/style.css" type="text/css" media="all">

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">       
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet"/>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&amp;display=swap" rel="stylesheet"/>

        <!-- Favicon -->
        <link rel="shortcut icon" href="/img/favicon.png" type="image/x-icon">
        <link rel="icon" href="/img/favicon.png" type="image/x-icon">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

        <style>
            .contract-management {
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

            .contract-table {
                width: 100%;
                border-collapse: separate;
                border-spacing: 0;
                background: white;
                border-radius: 15px;
                overflow: hidden;
                box-shadow: 0 5px 15px rgba(0, 0, 0, 0.08);
            }

            .contract-table th {
                background: #4caf50;
                color: white;
                font-weight: 600;
                padding: 15px;
                font-size: 0.95em;
                text-transform: uppercase;
                letter-spacing: 0.5px;
            }

            .contract-table th.center-align {
                text-align: center;
            }

            .contract-table td {
                padding: 15px;
                border-bottom: 1px solid #e8f5e9;
                color: #333;
                font-size: 0.95em;
            }

            .contract-table td.center-align {
                text-align: center;
            }

            .contract-table tr:last-child td {
                border-bottom: none;
            }

            .contract-table tr:nth-child(even) {
                background-color: #f8fdf8;
            }

            .contract-table tr:hover td {
                background-color: #e8f5e9;
            }

            .contract-table th.contractid-column {
                width: 60px;
            }

            .contract-table th.contracttype-column {
                width: 20%;
            }

            .contract-table th.contractamount-column {
                width: 35%;
            }

            .contract-table th.contractperiod-column {
                width: 100px;
            }

            .contract-table th.contractinterestrate-column {
                width: 120px;
            }

            .contract-table th.contractmonthlypayment-column {
                width: 120px;
            }

            .contract-table th.contractcreatedat-column {
                width: 80px;
            }

            .contract-table th.contractstatus-column {
                width: 180px;
            }

            .action-buttons-container {
                display: flex;
                gap: 10px;
                justify-content: center;
            }

            .action-button {
                padding: 8px 5px;
                border-radius: 8px;
                border: none;
                font-weight: 500;
                cursor: pointer;
                transition: all 0.3s ease;
                font-size: 0.9em;
                text-decoration: none;
                display: inline-block;
                text-align: center;
                min-width: 50px;
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

            .Type {
                color: #333;
                font-weight: 500;
            }

            .Amount {
                font-weight: 500;
                color: #2e7d32;
                text-decoration: none;
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
                .contract-management {
                    padding: 15px;
                }

                .contract-table {
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

                .contract-detail-container {
                    display: flex;
                    flex-direction: column;
                    padding: 10px;
                    background-color: #f9f9f9;
                    border-top: 1px solid #ddd;
                    transition: all 0.3s ease-in-out;
                }

                .info-row {
                    margin-bottom: 5px;
                    font-size: 14px;
                }

            }

            /* Màu sắc cho trạng thái */
            .status-label {
                padding: 5px 10px;
                border-radius: 5px;
                font-weight: bold;
                color: white;
            }

            /* Màu cho từng trạng thái */
            .status-1 {
                background-color: #f39c12;
            } /* Pending - Màu cam */
            .status-2 {
                background-color: #e74c3c;
            } /* Canceled - Màu đỏ */
            .status-3 {
                background-color: #3498db;
            } /* Doing - Màu xanh dương */
            .status-4 {
                background-color: #9b59b6;
            } /* Rejected - Màu tím */
            .status-5 {
                background-color: #2ecc71;
            } /* Completed - Màu xanh lá */

            /* Áp dụng màu cho button */
            .action-button {
                padding: 5px 15px;
                border: none;
                cursor: pointer;
                font-size: 14px;
                border-radius: 5px;
                transition: background 0.3s ease;
                color: white;
            }

            .status-1:hover {
                background-color: #e67e22;
            }
            .status-2:hover {
                background-color: #c0392b;
            }
            .status-3:hover {
                background-color: #2980b9;
            }
            .status-4:hover {
                background-color: #8e44ad;
            }
            .status-5:hover {
                background-color: #27ae60;
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

        <div class="contract-management">
            <h1 class="page-title">My Loan Payments</h1>

            <!-- Filter Controls -->
            <form action="loan-payment-management-for-customer" method="get" class="filter-controls">

                <div class="filter-group">
                    <label for="Status">Status:</label>
                    <select name="Status" id="Status" onchange="updateURLParameter('Status', this.value)">
                        <option value="none" ${param.Status == 'none' ? 'selected' : ''}>All Status</option>
                        <option value="late" ${param.Status == 'late' ? 'selected' : ''}>Late</option>
                        <option value="complete" ${param.Status == 'complete' ? 'selected' : ''}>Complete</option>
                        <option value="pending" ${param.Status == 'pending' ? 'selected' : ''}>Pending</option>
                    </select>

                    <label for="SortBy">SortBy:</label>
                    <select name="SortBy" id="SortBy" onchange="updateURLParameter('SortBy', this.value)">
                        <option value="none" ${param.SortBy == 'none' ? 'selected' : ''}>None</option>
                        <option value="PaymentDateASC" ${param.SortBy == 'PaymentDateASC' ? 'selected' : ''}>Earliest Payment Date</option>
                        <option value="PaymentDateDESC" ${param.SortBy == 'PaymentDateDESC' ? 'selected' : ''}>Latest Payment Date</option>
                        <option value="PaymentAmountASC" ${param.SortBy == 'PaymentAmountASC' ? 'selected' : ''}> Smallest Payment Amount</option>
                        <option value="PaymentAmountDESC" ${param.SortBy == 'PaymentAmountDESC' ? 'selected' : ''}> Biggest Payment Amount</option>
                    </select>

                    <label class="mine-checkbox">
                        <input type="checkbox" name="groupByContract" value="true" ${param.groupByContract == 'true'? 'checked' : ''} onchange="updateURLParameter('groupByContract', this.checked)">
                        Group By Contract
                    </label>

                    <label for="pageSize">Items per page:</label>
                    <c:if test="${requestScope.groupByContract.equals('true')}">
                        <select name="pageSize" id="pageSize" onchange="updateURLParameter('pageSize', this.value)">
                            <c:if test="${requestScope.numberOfContract <= 100}">
                                <option value="5" ${param.pageSize == 5 ? 'selected' : ''}>5</option>
                                <option value="10" ${param.pageSize==null || param.pageSize == 10 ? 'selected' : ''}>10</option>
                                <option value="20" ${param.pageSize == 20 ? 'selected' : ''}>20</option>
                                <option value="30" ${param.pageSize == 30 ? 'selected' : ''}>30</option>
                                <option value="50" ${param.pageSize == 50 ? 'selected' : ''}>50</option>
                            </c:if>
                            <c:if test="${requestScope.numberOfContract > 100}">
                                <c:forEach items="${requestScope.pageSizeArray}" var="pageSizeElement">
                                    <option value="${pageSizeElement}" ${param.pageSize == pageSizeElement ? 'selected' : ''}>${pageSizeElement}</option>
                                </c:forEach>
                            </c:if>
                        </select>
                    </c:if>
                    <c:if test="${requestScope.groupByContract.equals('false')}">
                        <select name="pageSize" id="pageSize" onchange="updateURLParameter('pageSize', this.value)">
                            <c:if test="${requestScope.numberOfLoanPayment <= 100}">
                                <option value="5" ${param.pageSize == 5 ? 'selected' : ''}>5</option>
                                <option value="10" ${param.pageSize==null || param.pageSize == 10 ? 'selected' : ''}>10</option>
                                <option value="20" ${param.pageSize == 20 ? 'selected' : ''}>20</option>
                                <option value="30" ${param.pageSize == 30 ? 'selected' : ''}>30</option>
                                <option value="50" ${param.pageSize == 50 ? 'selected' : ''}>50</option>
                            </c:if>
                            <c:if test="${requestScope.numberOfLoanPayment > 100}">
                                <c:forEach items="${requestScope.pageSizeArray}" var="pageSizeElement">
                                    <option value="${pageSizeElement}" ${param.pageSize == pageSizeElement ? 'selected' : ''}>${pageSizeElement}</option>
                                </c:forEach>
                            </c:if>
                        </select>
                    </c:if>
                </div>
            </form>

            <!-- Loan Payment Table if group by contract -->
            <c:if test="${requestScope.groupByContract.equals('true')}">
                <table class="contract-table">
                    <thead>
                        <tr>
                            <th class="contractid-column center-align">ID</th>
                            <td class="contracttype-column" style="width: 150px; background: #4caf50;color: white;font-weight: 600;padding: 15px;font-size: 0.95em;text-transform: uppercase;letter-spacing: 0.5px;">Type</td>
                            <th class="contractamount-column sortable" style="width: 150px">
                                <div style="width: 100%; display: flex">
                                    <div>Amount</div>
                                </div>
                            </th>
                            <th class="contractperiod-column sortable">
                                <div style="width: 100%; display: flex">
                                    <div>Period (months)</div>
                                </div>
                            </th>
                            <th class="contractstatus-column"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.entryList}" var="mapEntry">
                            <!-- Hàng hiển thị thông tin chính -->
                            <tr>
                                <td class="center-align">${mapEntry.getKey().getContractID()}</td>
                                <td class="Type">${mapEntry.getKey().getType()}</td>
                                <td class="Amount"><fmt:formatNumber value="${mapEntry.getKey().getAmount()}" pattern="#,###"/></td>
                                <td class="Type" style="text-align: center">${mapEntry.getKey().getPeriod()}</td>
                                <td class="action-column">
                                    <div class="action-buttons-container">
                                        <button type="submit" class="action-button" style="background: #cccccc" onclick="toggleDetails(${mapEntry.getKey().getContractID()}, this)">Show Detail</button>
                                    </div>
                                </td>
                            </tr>

                            <tr id="details-${mapEntry.getKey().getContractID()}" class="contract-detail" style="display: none;">
                                <td>
                                    <table>
                                        <tr>
                                            <th>Payment Amount</th>
                                            <th>Payment Date</th>
                                            <th>Status</th>
                                            <th>Action</th>
                                        </tr>
                                        <c:forEach items="${mapEntry.getValue()}" var="lp">
                                            <tr>
                                                <th><fmt:formatNumber value="${lp.getPaymentAmount()}" pattern="#,###"/></th>
                                                <th>${lp.getPaymentDate()}</th>
                                                <th>${lp.getPaymentStatus()}</th>
                                                <th>
                                                    <c:if test="${!lp.getPaymentStatus().equals('Complete')}">
                                                        <button type="submit" class="action-button" style="background: #cccccc" onclick="pay(${lp.getLoanPaymentID()})">Pay</button>
                                                    </c:if>
                                                </th>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </td>
                            </tr>
                        </c:forEach>

                    </tbody>
                </table>
            </c:if>

            <!-- Loan Payment Table if don't group by contract -->
            <c:if test="${requestScope.groupByContract.equals('false')}">
                <table>
                    <tr>
                        <th>Payment Amount</th>
                        <th>Payment Date</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                    <c:forEach items="${requestScope.lpList}" var="lp">
                        <tr>
                            <th><fmt:formatNumber value="${lp.getPaymentAmount()}" pattern="#,###"/></th>
                            <th>${lp.getPaymentDate()}</th>
                            <th>${lp.getPaymentStatus()}</th>
                            <th>
                                <c:if test="${!lp.getPaymentStatus().equals('Complete')}">
                                    <button type="submit" class="action-button" style="background: #cccccc" onclick="pay(${lp.getLoanPaymentID()})">Pay</button>
                                </c:if>
                            </th>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>

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

                    function updateURLParameter(param, value) {
                        let url = new URL(window.location.href);
                        let params = new URLSearchParams(url.search);

                        params.set(param, value);
                        if (param === 'Status' || param === 'SortBy')
                        {
                            params.delete('groupByContract');
                        }
                        ;
                        if (param === 'groupByContract')
                        {
                            params.delete('Status');
                            params.delete('SortBy');
                        }
                        ;
                        params.delete('page');
                        params.delete('pageSize');

                        window.location.href = 'loan-payment-management-for-customer?' + params.toString();
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

                    function toggleDetails(contractID, button) {
                        var detailsRow = document.getElementById("details-" + contractID);
                        if (detailsRow.style.display === "none" || detailsRow.style.display === "") {
                            detailsRow.style.display = "table-row";
                            button.textContent = 'Close Detail';
                        } else {
                            detailsRow.style.display = "none";
                            button.textContent = 'Show Detail';
                        }
                    }
        </script>
    </body>
</html>