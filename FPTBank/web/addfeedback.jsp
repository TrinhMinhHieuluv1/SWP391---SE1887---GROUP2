<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Add Feedback</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                display: flex;
                flex-direction: column;
                align-items: center;
                justify-content: center;
                height: 100vh;
                background-color: #f8f9fa;
            }

            .container {
                width: 50%;
                background: white;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            .input-group {
                display: flex;
                gap: 10px;
                margin-bottom: 15px;
            }

            .input-group input {
                flex: 1;
                padding: 10px;
                border: 1px solid #ddd;
                border-radius: 10px;
                font-size: 16px;
                font-weight: bold;
                color: #0f4c5c;
                text-align: center;
            }

            textarea {
                width: 100%;
                height: 150px;
                padding: 10px;
                border-radius: 10px;
                border: 1px solid #ddd;
                font-size: 16px;
                color: #0f4c5c;
                resize: none;
            }

            button {
                display: block;
                width: 100%;
                padding: 10px;
                background-color: green;
                color: white;
                font-size: 16px;
                font-weight: bold;
                border: none;
                border-radius: 10px;
                cursor: pointer;
                margin-top: 10px;
            }

            button:hover {
                background-color: darkgreen;
            }
            .form-control, .dataTable-input {
                display: block;
                width: 15%;
                padding: 0.875rem 1.125rem;
                font-size: 0.875rem;
                font-weight: 400;
                line-height: 1;
                color: #2e7d32;
                background-color: #fff;
                background-clip: padding-box;
                border: 1px solid #66bb6a;
                border-radius: 0.35rem;
                transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
            }
            .form-control:focus {
                border-color: #1b5e20;
                box-shadow: 0 0 10px rgba(27, 94, 32, 0.25);
            }
            select {
                width: 91%;
                padding: 10px;
                border: 1px solid #66bb6a;
                border-radius: 10px;
                font-size: 16px;
                font-weight: bold;
                color: #0f4c5c;
                background-color: white;
                cursor: pointer;
                text-align: center;
            }

            /* Khi focus vào select */
            select:focus {
                border-color: #1b5e20;
                box-shadow: 0 0 10px rgba(27, 94, 32, 0.25);
                outline: none;
            }

            /* Tạo khoảng cách và căn chỉnh */
            .input-group select {
                flex: 1;
                min-width: 150px;
                text-align: center;
            }
            .rating {
                direction: rtl; /* Hiển thị từ phải sang trái */
                font-size: 30px;
                display: flex;
                justify-content: center;
                gap: 5px;
            }

            .rating input {
                display: none; /* Ẩn radio button */
            }

            .rating label {
                cursor: pointer;
                color: gray;
                transition: color 0.3s;
            }

            .rating input:checked ~ label,
            .rating label:hover,
            .rating label:hover ~ label {
                color: gold;
            }
        </style>
    </head>
    <body>

        <div class="container">
            <h2 style="text-align: center; color: green;">Add Feedback</h2>
            <form action="contact" method="get">
                <div class="input-group">
                    <input type="text" name="name" value="${sessionScope.account.fullName}" readonly >
                    <input type="email" name="email" value="${sessionScope.account.email}" readonly>
                </div>
                <select class="input-group" id="statusFilter" name="serviceid" style="margin-left: 30px;">
                    <option value="">-- Select Service --</option>
                    <c:forEach var="service" items="${requestScope.listservice}">
                        <option value="${service.getServiceID()}">${service.getServiceName()}</option>
                    </c:forEach>
                </select>
                <!-- Đánh giá sao -->
                <div class="rating">
                    <input type="radio" id="star5" name="stars" value="5"><label for="star5">★</label>
                    <input type="radio" id="star4" name="stars" value="4"><label for="star4">★</label>
                    <input type="radio" id="star3" name="stars" value="3"><label for="star3">★</label>
                    <input type="radio" id="star2" name="stars" value="2"><label for="star2">★</label>
                    <input type="radio" id="star1" name="stars" value="1"><label for="star1">★</label>
                </div>
                <textarea name="message" placeholder="Message"></textarea>
                <% if(request.getAttribute("error")!=null)  {%>
                <a style="color:red; font-style: italic"><%out.println(request.getAttribute("error"));%></a>
                <%}%>
                <button type="submit">Submit Feedback</button>
                <button type="button" onclick="window.location.href = 'myfeedback'" style="padding: 10px 15px; background-color: green; color: white; border-radius: 5px; font-weight: bold;">
                    Back to View Your Feedback
                </button>
            </form>

        </div>

    </body>
</html>
