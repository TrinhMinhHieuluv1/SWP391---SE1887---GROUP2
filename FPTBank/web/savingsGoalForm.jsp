<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Tạo mục tiêu tiết kiệm mới</title>
        <link rel="stylesheet" href="css/styles.css">
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
                color: #0066cc;
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
        </style>
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
    </head>
    <body>
        <div class="container">
            <header>
                <h1>Create a new savings goal</h1>
                <a href="goals" class="back-link">←Back</a>
            </header>

            <c:if test="${not empty errorMessage}">
                <div class="alert error">
                    <p>${errorMessage}</p>
                </div>
            </c:if>

            <div class="form-container">
                <form action="goals?action=create" method="post" id="savingsGoalForm" onsubmit="return validateSavingsGoalForm(event)">
                    <div class="form-group">
                        <label for="goalName">Goal name<span class="required">*</span></label>
                        <input type="text" id="goalName" name="goalName" required 
                               placeholder="Ví dụ: Mua xe máy, Du lịch Đà Nẵng, Sửa nhà">
                    </div>

                    <div class="form-group">
                        <label for="targetAmount">Goal amount<span class="required">*</span></label>
                        <input type="text" 
                               class="form-control form-control-sm" 
                               id="targetAmount"
                               oninput="formatNumber(this)" onkeypress="return validateInput(event)"
                               name="targetAmount" placeholder="Số tiền" required>
                        <span class="hint">VD: 5,000,000₫</span>
                    </div>

                    <!--                    <div class="form-group">
                                            <label for="savedAmount">Amount saved</label>
                                            <input type="number" id="savedAmount" name="savedAmount" min="0" step="1000"
                                                   placeholder="Nhập số tiền đã tiết kiệm (nếu có)" onblur="formatCurrencyField(event)">
                                        </div>-->

                    <div class="form-group">
                        <label for="deadline">Completion deadline</label>
                        <input type="date" id="deadline" name="deadline" 
                               onchange="validateDeadlineDate(event)">
                        <span class="hint">Leave blank if there is no specific deadline</span>
                    </div>

                    <div class="form-actions">
                        <button type="submit" class="button primary">Create a goal</button>
                        <a href="goals" class="button secondary">Cancel</a>
                    </div>
                </form>
            </div>
        </div>

        <footer>
            <p>&copy; 2025 Ứng dụng tài chính cá nhân</p>
        </footer>
    </body>
</html>