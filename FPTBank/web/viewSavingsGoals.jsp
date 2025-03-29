<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Mục tiêu tiết kiệm của bạn</title>
        <style>
            /* Base styles */
            * {
                box-sizing: border-box;
                margin: 0;
                padding: 0;
            }

            body {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                line-height: 1.6;
                color: #333;
                background-color: #f5f5f5;
            }

            .container {
                max-width: 1200px;
                margin: 0 auto;
                padding: 20px;
            }

            header {
                margin-bottom: 30px;
                text-align: center;
                padding-bottom: 20px;
                border-bottom: 1px solid #ddd;
            }

            header h1 {
                color: #28a745;
                margin-bottom: 10px;
            }

            footer {
                text-align: center;
                padding: 20px;
                margin-top: 40px;
                color: #666;
                font-size: 0.8rem;
            }

            /* Home page styles */
            .main-actions {
                display: flex;
                justify-content: center;
                flex-wrap: wrap;
                gap: 30px;
                margin-bottom: 40px;
            }

            .action-card {
                background-color: white;
                border-radius: 8px;
                padding: 25px;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
                flex: 1;
                min-width: 300px;
                max-width: 400px;
                text-align: center;
            }

            .action-card h2 {
                color: #28a745;
                margin-bottom: 15px;
            }

            .quick-tips {
                background-color: #e6f2ff;
                border-radius: 8px;
                padding: 20px;
                margin-top: 30px;
            }

            .quick-tips h3 {
                color: #0066cc;
                margin-bottom: 15px;
            }

            .quick-tips ul {
                list-style-position: inside;
            }

            .quick-tips li {
                margin-bottom: 10px;
            }

            /* Button styles */
            .button {
                display: inline-block;
                background-color: #0066cc;
                color: white;
                padding: 10px 20px;
                border-radius: 5px;
                text-decoration: none;
                font-weight: 600;
                margin-top: 15px;
                border: none;
                cursor: pointer;
                transition: background-color 0.2s;
            }

            .button:hover {
                background-color: #0052a3;
            }

            .button.secondary {
                background-color: #6c757d;
            }

            .button.secondary:hover {
                background-color: #5a6268;
            }

            .button.primary {
                background-color: #28a745;
            }

            .button.primary:hover {
                background-color: #218838;
            }

            .button.danger {
                background-color: #dc3545;
            }

            .button.danger:hover {
                background-color: #c82333;
            }

            .back-link {
                display: inline-block;
                color: #28a745;
                text-decoration: none;
                margin-top: 10px;
            }

            /* Alert messages */
            .alert {
                padding: 15px;
                border-radius: 5px;
                margin-bottom: 20px;
            }

            .alert.success {
                background-color: #d4edda;
                border: 1px solid #c3e6cb;
                color: #155724;
            }

            .alert.error {
                background-color: #f8d7da;
                border: 1px solid #f5c6cb;
                color: #721c24;
            }

            /* Form styles */
            .form-container {
                background-color: white;
                border-radius: 8px;
                padding: 30px;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
                max-width: 800px;
                margin: 0 auto;
            }

            .form-group {
                margin-bottom: 20px;
            }

            label {
                display: block;
                margin-bottom: 8px;
                font-weight: 600;
            }

            input[type="text"],
            input[type="number"],
            input[type="date"],
            select {
                width: 100%;
                padding: 12px;
                border: 1px solid #ddd;
                border-radius: 5px;
                font-size: 1rem;
            }

            .hint {
                display: block;
                font-size: 0.85rem;
                color: #6c757d;
                margin-top: 5px;
            }

            .required {
                color: #dc3545;
            }

            .form-actions {
                margin-top: 30px;
                display: flex;
                gap: 10px;
                flex-wrap: wrap;
            }

            /* Goals list styles */
            .empty-state {
                text-align: center;
                padding: 40px 20px;
                background-color: white;
                border-radius: 8px;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            }

            .goals-grid {
                display: grid;
                grid-template-columns: repeat(auto-fill, minmax(350px, 1fr));
                gap: 20px;
            }

            .goal-card {
                background-color: white;
                border-radius: 8px;
                padding: 20px;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            }

            .goal-header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 15px;
                padding-bottom: 10px;
                border-bottom: 1px solid #eee;
            }

            .goal-header h2 {
                color: #28a745;
                font-size: 1.4rem;
            }

            .goal-actions {
                display: flex;
                gap: 5px;
            }

            .icon-button {
                text-decoration: none;
                font-size: 1.2rem;
            }

            .goal-amounts {
                display: flex;
                justify-content: space-between;
                margin-bottom: 15px;
            }

            .amount-saved,
            .amount-target {
                flex: 1;
            }

            .label {
                display: block;
                font-size: 0.85rem;
                color: #6c757d;
            }

            .value {
                font-weight: 600;
                font-size: 1.1rem;
            }

            .goal-progress {
                margin-bottom: 20px;
            }

            .progress-bar {
                height: 12px;
                background-color: #e9ecef;
                border-radius: 6px;
                overflow: hidden;
                margin-bottom: 5px;
            }

            .progress-fill {
                height: 100%;
                background-color: #28a745;
                border-radius: 6px;
            }

            .progress-text {
                text-align: right;
                font-size: 0.85rem;
                color: #6c757d;
            }

            .goal-details {
                display: flex;
                flex-wrap: wrap;
                gap: 15px;
                margin-bottom: 20px;
                font-size: 0.9rem;
            }

            .detail-item {
                flex: 1;
                min-width: 120px;
            }

            .goal-update {
                background-color: #f8f9fa;
                padding: 15px;
                border-radius: 5px;
                margin-top: 15px;
            }

            .update-controls {
                display: flex;
                gap: 10px;
                margin-bottom: 10px;
            }

            /* Quick update section in edit page */
            .quick-update {
                background-color: #f8f9fa;
                padding: 20px;
                border-radius: 5px;
                margin: 20px 0;
            }

            .quick-update h3 {
                margin-bottom: 15px;
                color: #0066cc;
            }

            .update-group {
                display: flex;
                flex-wrap: wrap;
                gap: 10px;
                align-items: flex-end;
            }

            /* Action bar */
            .action-bar {
                display: flex;
                justify-content: flex-end;
                margin-bottom: 20px;
            }

            /* Responsive adjustments */
            @media (max-width: 768px) {
                .main-actions {
                    flex-direction: column;
                    align-items: center;
                }

                .goals-grid {
                    grid-template-columns: 1fr;
                }

                .form-container {
                    padding: 20px;
                }

                .update-controls {
                    flex-direction: column;
                }
            }
            .balance-section {
                text-align: start;
            }

            .balance-card {
                background-color: white;
                border-radius: 8px;
                padding: 20px;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
                display: inline-block;
                min-width: 300px;
            }

            .balance-card h2 {
                color: #0066cc;
                margin-bottom: 15px;
            }

            .balance-amount .value {
                font-size: 1.8rem;
                font-weight: 600;
                color: #28a745;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <header>
                <h1>Your savings goal</h1>
                <a href="savingGoals.jsp" class="back-link">← Back</a>
            </header>
            <div class="balance-section">
                <div class="balance-card">
                    <h2>Current Balance</h2>
                    <div class="balance-amount">
                        <span class="value">
                            <c:choose>
                                <c:when test="${not empty sessionScope.account}">
                                    <fmt:formatNumber value="${account.getBalance()}" type="currency" currencySymbol="₫" />
                                </c:when>
                                <c:otherwise>
                                    0 ₫
                                </c:otherwise>
                            </c:choose>
                        </span>
                    </div>


                </div>
            </div>
            <c:if test="${not empty successMessage}">
                <div class="alert success">
                    <p>${successMessage}</p>
                </div>
            </c:if>

            <c:if test="${not empty errorMessage}">
                <div class="alert error">
                    <p>${errorMessage}</p>
                </div>
            </c:if>

            <div class="action-bar">
                <a href="goals?action=new" class="button">+ Create New Saving Goal</a>
            </div>

            <c:choose>
                <c:when test="${empty goals}">
                    <div class="empty-state">
                        <h2>You don't have any savings goals yet?</h2>
                        <p>Start by creating your first savings goal.</p>
                        <a href="goals?action=new" class="button">Create Saving Goal now</a>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="goals-grid">
                        <c:forEach var="goal" items="${goals}">
                            <div class="goal-card">
                                <div class="goal-header">
                                    <h2>${goal.goalName}</h2>
                                    <div class="goal-actions">
                                        <a href="goals?action=edit&id=${goal.id}" class="icon-button edit" title="Chỉnh sửa">✏️</a>
                                        <a href="#" onclick="confirmDelete(${goal.id})" class="icon-button delete" title="Xóa">🗑️</a>
                                    </div>
                                </div>

                                <div class="goal-amounts">
                                    <div class="amount-saved">
                                        <span class="label">Saved amount:</span>
                                        <span class="value"><fmt:formatNumber value="${goal.savedAmount}" type="currency" currencySymbol="₫" /></span>
                                    </div>
                                    <div class="amount-target">
                                        <span class="label">Goal amount:</span>
                                        <span class="value"><fmt:formatNumber value="${goal.targetAmount}" type="currency" currencySymbol="₫" /></span>
                                    </div>
                                </div>

                                <div class="goal-progress">
                                    <div class="progress-bar">
                                        <div class="progress-fill" style="width: ${goal.percentageComplete}%"></div>
                                    </div>
                                    <div class="progress-text">${goal.percentageComplete}% complete</div>
                                </div>

                                <div class="goal-details">
                                    <c:if test="${not empty goal.deadline}">
                                        <div class="detail-item">
                                            <span class="label">Deadline:</span>
                                            <span class="value"><fmt:formatDate value="${goal.deadline}" pattern="dd/MM/yyyy" /></span>
                                        </div>
                                    </c:if>
                                    <div class="detail-item">
                                        <span class="label">Date created:</span>
                                        <span class="value"><fmt:formatDate value="${goal.createdDate}" pattern="dd/MM/yyyy" /></span>
                                    </div>
                                </div>

                                <div class="goal-update">
                                    <form action="updateSavings" method="post">
                                        <input type="hidden" name="goalId" value="${goal.id}">
                                        <div class="update-controls">
                                            <input type="text" 
                                                class="form-control form-control-sm" 
                                                id="editValuation"
                                                oninput="formatNumber(this)" onkeypress="return validateInput(event)"
                                                name="amount" placeholder="Số tiền" required>
                                                <select name="updateType">
                                                    <option value="add">Add money</option>
                                                    <option value="subtract">Withdraw money</option>
                                                </select>
                                        </div>
                                        <button type="submit" class="button">Update</button>
                                    </form>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>

        <footer>
            <p>&copy; 2025 Ứng dụng tài chính cá nhân</p>
        </footer>
        <script type="text/javascript">
            function formatNumber(input) {
                // Loại bỏ tất cả các ký tự không phải số
                let value = input.value.replace(/[^0-9]/g, '');
                // Thêm dấu phẩy sau mỗi 3 chữ số
                value = value.replace(/\B(?=(\d{3})+(?!\d))/g, '.');
                // Gán giá trị đã định dạng lại vào input
                input.value = value;
            }
            function validateInput(event) {
                // Chỉ cho phép nhập các ký tự số
                const charCode = (event.which) ? event.which : event.keyCode;
                if (charCode > 31 && (charCode < 48 || charCode > 57)) {
                    event.preventDefault();
                    return false;
                }
                return true;
            }
        </script>
        <script>
            function confirmDelete(goalId) {
                if (confirm("Are you sure you want to delete this savings goal?")) {
                    window.location.href = "goals?action=delete&id=" + goalId;
                }
            }
        </script>
    </body>
</html>