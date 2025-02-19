<%-- 
    Document   : faq-update
    Created on : Feb 14, 2025, 5:49:25 PM
    Author     : ADMIN
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Update News</title>


        <style>
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }

            body {
                font-family: 'Arial', sans-serif;
                background-color: #f5f5f5;
                color: #333;
                line-height: 1.6;
            }

            .container {
                max-width: 1000px;
                margin: 40px auto;
                padding: 20px;
            }

            .update-form {
                background: white;
                padding: 30px;
                border-radius: 10px;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            }

            .form-header {
                margin-bottom: 30px;
                text-align: center;
            }

            .form-title {
                color: #2e7d32;
                font-size: 24px;
                font-weight: 600;
            }

            .form-group {
                margin-bottom: 20px;
            }

            .form-control {
                width: 100%;
                padding: 10px 15px;
                border: 1px solid #ddd;
                border-radius: 5px;
                font-size: 14px;
                transition: border-color 0.3s ease;
            }
            
            .form-control__textarea {
                font-family: 'Arial', sans-serif;
                line-height: 1.6;
                resize: none;
                min-height: 200px;
                overflow-y: auto;
            }

            .form-group__label {
                display: inline-block;
                font-weight: bold;
                margin-right: 10px;
                margin-left: 5px;
                margin-bottom: 10px;
            }
            
            .form-control:focus {
                border-color: #4caf50;
                outline: none;
                box-shadow: 0 0 0 2px rgba(76, 175, 80, 0.2);
            }

            .image-preview {
                margin-top: 10px;
                max-width: 300px;
                border-radius: 5px;
            }

            .button-group {
                display: flex;
                gap: 15px;
                margin-top: 30px;
            }

            .btn {
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                font-weight: 500;
                text-decoration: none;
                text-align: center;
                transition: all 0.3s ease;
            }

            .btn-primary {
                background-color: #4caf50;
                color: white;
                flex: 1;
            }

            .btn-primary:hover {
                background-color: #43a047;
                transform: translateY(-2px);
            }

            .btn-secondary {
                background-color: #f5f5f5;
                color: #333;
                flex: 1;
                border: 1px solid #ddd;
            }

            .btn-secondary:hover {
                background-color: #e0e0e0;
                transform: translateY(-2px);
            }

            /* Alert message styling */
            .alert {
                padding: 15px;
                margin-bottom: 20px;
                border-radius: 5px;
                color: #155724;
                background-color: #d4edda;
                border: 1px solid #c3e6cb;
            }

            @media (max-width: 768px) {
                .container {
                    margin: 20px auto;
                    padding: 15px;
                }

                .update-form {
                    padding: 20px;
                }

                .button-group {
                    flex-direction: column;
                }
            }
        </style>
    </head>
    <body>
        <div class="container">
            <form action="faq-update" method="post" class="update-form">
                <div class="form-header">
                    <h1 class="form-title">Update FAQ</h1>
                </div>
                
                <div>
                    <input type="hidden" name="FaqID" value="${requestScope.faqToUpdate.getFaqID()}">
                    <span class="form-group__label">ID:</span>
                    ${requestScope.faqToUpdate.getFaqID()}
                </div>
                
                <div class="form-group">
                    <label for="type" class="form-group__label">Type</label>
                    <select id="type" name="type" class="form-control" required>
                        <option value="">-- Select Type --</option>
                        <c:forEach items="${listType}" var="type">
                            <option value="${type}" ${type == requestScope.faqToUpdate.getType() ? 'selected' : ''}>${type}</option>
                        </c:forEach>
                    </select>
                </div>
                
                <div class="form-group">
                    <label for="question" class="form-group__label">Question</label>
                    <input type="text" id="question" name="question" value="${requestScope.faqToUpdate.getQuestion()}" 
                           class="form-control" required>
                </div>

                <div class="form-group">
                    <label for="answer" class="form-group__label">Answer</label>
                    <textarea id="answer" name="answer" class="form-control form-control__textarea" required><c:out value="${requestScope.faqToUpdate.getAnswer()}" /></textarea>

                </div>

                <div class="button-group">
                    <a href="/timibank/seller/faq-management" class="btn btn-secondary">Back to List</a>
                    <button type="submit" class="btn btn-primary">Update FAQ</button>
                </div>
                    
            </form>
        </div>
    </body>
</html>
