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
            <h2 style="color: #1b5e20;">Feedback Details</h2>

            <label for="message">Your Feedback:</label>
            <textarea id="message" class="input-box" readonly> ${requestScope.detailfeedback != null && requestScope.detailfeedback.message != null ? requestScope.detailfeedback.message : 'No feedback available'}</textarea>

            <label for="response">Bank's Response:</label>
            <textarea id="response" class="input-box" readonly> ${requestScope.detailfeedback.response == null ? "Not yet response" : requestScope.detailfeedback.response}</textarea>
            <button type="button" onclick="window.location.href = 'myfeedback'" style="padding: 10px 15px; background-color: green; color: white; border-radius: 5px; font-weight: bold;">
                Back to View Your Feedback
            </button>
        </div>
    </body>
</html>
