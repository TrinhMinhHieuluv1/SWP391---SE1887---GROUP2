<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Feedback Detail</title>
        <style>
            body {
                font-family: 'Poppins', sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f4f7f6;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }

            .container {
                max-width: 600px;
                width: 100%;
                padding: 25px;
                border-radius: 12px;
                background-color: #ffffff;
                box-shadow: 0px 5px 15px rgba(0, 0, 0, 0.15);
                text-align: center;
                transition: all 0.3s ease-in-out;
            }

            h2 {
                color: #2e7d32;
                margin-bottom: 15px;
                font-size: 22px;
                font-weight: 600;
            }

            label {
                font-weight: 500;
                color: #2e7d32;
                display: block;
                margin-top: 15px;
                text-align: left;
                font-size: 14px;
            }

            .input-box {
                width: 95%;
                padding: 12px;
                margin-top: 8px;
                border: 1px solid #81c784;
                border-radius: 8px;
                background-color: #e8f5e9;
                color: #2e7d32;
                font-size: 14px;
                resize: none;
                transition: all 0.3s;
            }

            .input-box:focus {
                border-color: #388e3c;
                box-shadow: 0px 0px 8px rgba(46, 125, 50, 0.3);
                outline: none;
            }

            button {
                margin-top: 20px;
                padding: 12px 18px;
                background: linear-gradient(135deg, #43a047, #2e7d32);
                color: white;
                font-size: 14px;
                font-weight: bold;
                border: none;
                border-radius: 8px;
                cursor: pointer;
                transition: 0.3s ease-in-out;
            }

            button:hover {
                background: linear-gradient(135deg, #388e3c, #1b5e20);
                transform: scale(1.05);
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2 style="color: #1b5e20;">Response for Customer</h2>

            <form action="addresponse" method="post">
                <input type="text" value="${param.FID}" name="FID" hidden="">

                <label for="name">Customer Name:</label>
                <input id="name" class="input-box" value="${requestScope.feedbackre.customer.fullName}">
                <label for="email">Customer Email:</label>
                <input id="email" class="input-box" value="${requestScope.feedbackre.customer.email}">

                <label for="CCCD">Customer CCCD:</label>
                <input id="CCCD" class="input-box" value="${requestScope.feedbackre.customer.CCCD}">
                <label for="message">Customer Feedback:</label>
                <textarea id="message" class="input-box" readonly>${requestScope.feedbackre.message}</textarea>
                <label for="response">Bank's Response:</label>
                <input type="text" id="response" class="input-box" name="response" value="${requestScope.feedbackre != null && requestScope.feedbackre.response != null ? requestScope.feedbackre.response : ''}">
                <% if(request.getAttribute("error2")!=null)  {%>
                <a style="color:red; font-style: italic"><%out.println(request.getAttribute("error2"));%></a>
                <%}%>
                <button type="submit" style="padding: 10px 15px; background-color: green; color: white; border-radius: 5px; font-weight: bold; margin: 10px;">
                    Add Response
                </button>
                <button type="button" onclick="window.location.href = 'managefeedback'" style="padding: 10px 15px; background-color: green; color: white; border-radius: 5px; font-weight: bold;">
                    Back to View Your Feedback
                </button>
            </form>
        </div>
    </body>
</html>
