<%-- 
    Document   : news-management
    Created on : Feb 5, 2025, 6:50:43 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
            }
        </style>
    </head>
    <body>
        <div class="news-management">
            <h1 class="page-title">News Management</h1>
            <table class="news-table">
                <thead>
                    <tr>
                        <th class="id-column">ID</th>
                        <th class="author-column">Author</th>
                        <th class="title-column">Title</th>
                        <th class="status-column">Status</th>
                        <th class="time-column center-align">Created Time</th>
                        <th class="access-column center-align">Access</th>
                        <th class="actions-column center-align">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.newsList}" var="news">
                        <tr>
                            <td>${news.getNewsID()}</td>
                            <td><span class="author-name">${news.getUser().getFullName()}</span></td>
                            <td><a href="#" class="news-title">${news.getTitle()}</a></td>
                            <td>
                                <span class="status-badge ${news.isStatus() ? 'status-active' : 'status-inactive'}">
                                    ${news.isStatus() ? 'Active' : 'Inactive'}
                                </span>
                            </td>
                            <td class="center-align"><span class="created-time">${news.getCreatedAt()}</span></td>
                            <td class="center-align"><span class="access-count">${news.getNumberOfAccess()}</span></td>
                            <td>
                                <c:if test="${sessionScope.account.getUserID() == news.getUser().getUserID()}">
                                    <div class="action-buttons-container">
                                        <a href="/timibank/seller/update-news?NewsID=${news.getNewsID()}" 
                                           class="action-button update-btn">Update</a>
                                        <a href="/timibank/seller/inactivate-news?NewsID=${news.getNewsID()}" 
                                           class="action-button ${!news.isStatus() ? 'activate-btn' : 'inactivate-btn'}">
                                            ${!news.isStatus() ? 'Activate' : 'Inactivate'}
                                        </a>
                                    </div>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
