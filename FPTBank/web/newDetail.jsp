<%-- 
    Document   : newDetai
    Created on : Mar 20, 2025, 9:16:26 AM
    Author     : tiend
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>News Detail</title>
        <link rel="stylesheet" href="styles.css">
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f5f5f5;
            }

            .container {
                max-width: 700px;
                margin: 20px auto;
                padding: 20px;
                background-color: #fff;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            h1 {
                font-size: 22px;
                color: #333;
                margin-bottom: 10px;
            }

            .date {
                font-size: 12px;
                color: #666;
                margin-bottom: 20px;
            }

            .content {
                font-size: 14px;
                line-height: 1.6;
                color: #333;
            }

            .content p {
                margin: 10px 0;
            }

            .content ul {
                list-style-type: disc;
                padding-left: 20px;
            }

            .content ul li {
                margin: 5px 0;
            }

            .actions {
                margin-top: 20px;
                text-align: right;
            }

            .print-btn, .share-btn {
                background-color: #f0f0f0;
                border: none;
                padding: 10px 15px;
                margin-left: 10px;
                cursor: pointer;
                font-size: 14px;
            }

            .print-btn:hover, .share-btn:hover {
                background-color: #e0e0e0;
            }
            .related-news {
                margin-top: 40px;
                padding-left: 100px;
                padding-right:100px;
            }

            .related-news h2 {
                font-size: 20px;
                color: #333;
                margin-bottom: 20px;
            }

            .news-grid {
                display: flex;
                gap: 20px;
                flex-wrap: wrap;
            }

            .news-item {
                flex: 1;
                min-width: 250px;
                background-color: #fff;
                border-radius: 8px;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
                overflow: hidden;
            }

            .news-item img {
                width: 100%;
                height: 150px;
                object-fit: cover;
            }

            .news-item h3 {
                font-size: 16px;
                color: #333;
                margin: 10px;
                line-height: 1.4;
            }

            .news-item .news-date {
                font-size: 12px;
                color: #666;
                margin: 0 10px 10px;
            }

            .news-item .read-more {
                display: inline-block;
                margin: 0 10px 10px;
                color: #008000;
                text-decoration: none;
                font-size: 14px;
            }

            .news-item .read-more:hover {
                text-decoration: underline;
            }

            .view-all {
                text-align: right;
                margin-top: 20px;
            }

            .view-all a {
                color: #008000;
                text-decoration: none;
                font-size: 14px;
            }

            .view-all a:hover {
                text-decoration: underline;
            }
        </style>
    </head>
    <body>
        <%@ include file="header.jsp"%>
        <div class="container" style="margin-top: 150px">
           <c:set value="${requestScope.newdetail}" var="detail"/>
            <h3>${detail.getTitle()}</h3>
            <div class="date">${detail.getCreatedAt()}</div>
            <div class="content">
                <p>${detail.getDescription()}</p>
            </div>
            <div class="actions">
                
            </div>
        </div>
        <div class="related-news">
            <h2>Tin liên quan</h2>
            <div class="news-grid">
                <c:forEach  items="${requestScope.data}" var="news">
                    <div class="news-item">
                        <img src="${pageContext.request.contextPath}/${news.getImage()}" alt="News Image 1">
                        <h3>${news.getTitle()}</h3>
                        <p class="news-date">${news.getCreatedAt()}</p>
                        <p class="news-date">Số lượng xem:${news.getNumberOfAccess()}</p>
                        <a href="/timibank/newdetail?newId=${news.getNewsID()}"class="read-more">Xem chi tiết →</a>
                    </div>
                </c:forEach>
            </div>
            <div class="view-all">
                <a href="/timibank/news?idCate=${detail.getNewsCategory().getNewsCategoryID()}">Xem tất cả →</a>
            </div>
        </div>
        <%@ include file="footer.jsp"%>
    </body>
</html>
