<%-- 
    Document   : news-management
    Created on : Feb 5, 2025, 6:50:43 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
    <head>
        <title>News Management</title>
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
        <c:if test="${not empty requestScope.message}">
            <div id="toastMessage" class="toast-message">
                <i class="fa fa-check-circle"></i>
                ${requestScope.message}
            </div>
        </c:if>

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
                            <div style="width: 100%; display: flex">
                                <div>Created At</div>
                                <span class="sort-icons">
                                    <i class="fa fa-caret-up" style="display: ${param.sortBy == 'CreatedAtDESC' ? 'none' : ''}; color: white; font-size: 1.5em;" 
                                       onclick="updateURLParameter('sortBy', 'CreatedAtDESC')"></i>
                                    <i class="fa fa-caret-down" style="display: ${param.sortBy == 'CreatedAtASC' ? 'none' : ''}; color: white; font-size: 1.5em;"
                                       onclick="updateURLParameter('sortBy', 'CreatedAtASC')"></i>
                                </span>
                            </div>
                        </th>
                        <th class="access-column sortable">
                            <div style="width: 100%; display: flex">
                                <div>Access</div>
                                <span class="sort-icons">
                                    <i class="fa fa-caret-up icon-sort" style="display: ${param.sortBy == 'NumberOfAccessDESC' ? 'none' : ''}; color: white; font-size: 1.5em;"
                                       onclick="updateURLParameter('sortBy', 'NumberOfAccessDESC')"></i>
                                    <i class="fa fa-caret-down icon-sort" style="display: ${param.sortBy == 'NumberOfAccessASC' ? 'none' : ''}; color: white; font-size: 1.5em;"
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
