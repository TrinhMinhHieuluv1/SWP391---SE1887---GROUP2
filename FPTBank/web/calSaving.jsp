<%-- 
    Document   : congcu2
    Created on : Jan 24, 2025, 10:49:03 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">

        <title>TIMI - Interest Calculator</title>
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

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css"/>
    </head>
    <style>
        body {
            font-family: sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f5f5f5;
            width: 100vw;
        }

        .cal-container {
            width: calc(100% - 200px);
            margin: 130px auto 20px;
            background-color: #fff;
            padding: 20px;
            border-radius: 16px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .cal-content {
            display: flex;
            gap: 50px;
        }

        .title {
            text-align: center;
            font-size: 24px;
            font-weight: bold;
            margin-bottom: 20px;
        }

        .description {
            text-align: center;
            font-size: 18px;
            margin-bottom: 20px;
        }

        .tabs {
            display: flex;
            justify-content: center;
            margin-bottom: 20px;
        }
        .tab {
            text-decoration: none;
            color: #333;
            font-weight: bold;
            padding: 10px;
        }
        .tab.active {
            color: #008000;
            border-bottom: 2px solid #008000;

        }
        .form-group {
            display: flex;
            align-items: center;

        }

        .form-group + .form-group {
            margin-top: 30px;
        }

        .form-group__label {
            width: 150px;
            font-weight: bold;
            margin-right: 10px;
        }

        .form-group__input-wrap {
            display: flex;
            border: 1px solid #ccc;
            padding: 5px;
            width: 100%;
        }

        .form-group__input {
            border: none;
            outline: none;
            font-size: 18px;
        }

        .sub-text-input {
            color: #ccc;
            margin-left: auto;
        }

        .btn-submit {
            display: block;
            width: 100%;
            padding: 10px;
            background-color: #16e15e;
            color: #fff;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
            margin-top: 50px;
        }

        .cal-form {
            width: 50%;
        }

        .result-table {
            width: 50%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        .result-table th, .result-table td {
            border: 1px solid #ccc;
            padding: 10px;
            text-align: left;
        }

        .result-table th {
            background-color: #f2f2f2;
        }

        .result-table td {
            background-color: #f9f9f9;
        }

        .result {
            margin-top: 20px;
            padding: 10px;
            background-color: #f9f9f9;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .result p {
            margin: 5px 0;
        }
        .result p span {
            float: right;
        }
        .details {
            margin-top: 20px;
        }
        .details table {
            width: 100%;
            border-collapse: collapse;
        }
        .details table, .details th, .details td {
            border: 1px solid #ccc;
        }
        .details th, .details td {
            padding: 10px;
            text-align: center;
        }
        .details th {
            background-color: #f0f0f0;
        }
        .details .total {
            font-weight: bold;
        }
        .note {
            margin-top: 10px;
            font-size: 12px;
            color: #666;
        }
        .red{
            color: red;
        }
        .congthuc{
            font-weight: bold;
            color: black;
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

    </style>
    <body>
        <script type="text/javascript">
            function formatNumber(input) {
                // Loại bỏ tất cả các ký tự không phải số
                let value = input.value.replace(/[^0-9]/g, '');

                // Thêm dấu phẩy sau mỗi 3 chữ số
                value = value.replace(/\B(?=(\d{3})+(?!\d))/g, ',');

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
        <div id="smooth-wrapper" class="mil-wrapper">

            <%@ include file="header.jsp"%>
            <!-- top panel end -->
            <!--show message-->

            <!-- content -->
            <div id="smooth-content">
                <div class="cal-container">
                    <a href="about.jsp"><i class="fa-solid fa-arrow-left-long"></i></a>
                    <div class="tabs">
                        <a href="calSaving.jsp" class="tab active">SAVINGS INTEREST</a>
                        <a href="calLoan.jsp" class="tab">BANK LOAN INTEREST</a>
                    </div>
                    <div class="title">BANK LOAN INTEREST CALCULATOR</div>
                    <div class="description"><br/>
                        LuatVietnam's bank loan interest calculator helps you estimate the periodic interest payments, 
                        total principal and interest at each stage. This allows you to better plan your finances.<br/><br/>
                    </div>
                    <c:if test="${not empty error}">
                        <div class="error-message" style="color: red; font-weight: bold;">
                            ${error}
                        </div>
                    </c:if>
                    <div class="cal-content">

                        <form action="congcu" id="myForm" class="cal-form">

                            <div class="form-group">
                                <label for="nameSaving" class="form-group__label">User Name</label>
                                <div class="form-group__input-wrap">
                                    <input type="text" class="form-group__input" id="nameSaving" name="nameSaving" value="${nameSaving}" 
                                           required>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="emailSaving" class="form-group__label">Email</label>
                                <div class="form-group__input-wrap">
                                    <input type="email" class="form-group__input" id="emailLoan" name="emailSaving" value="${emailSaving}" 
                                           required
                                           pattern="^(?!.*\.\.)[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$" 
                                           title="Invalid email format"
                                           oninput="validateEmail()">
                                    <span id="emailError"  class="sub-text-input"></span>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="loanAmount" class="form-group__label">Deposit Amount</label>
                                <div class="form-group__input-wrap">
                                    <input type="text" class="form-group__input" id="amount" name="amount" value="${amount}" 
                                           oninput="formatNumber(this)" onkeypress="return validateInput(event)" required>
                                    <span class="sub-text-input">VND</span>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="loanRate" class="form-group__label">Interest Rate</label>
                                <div class="form-group__input-wrap">
                                    <input class="form-group__input" step="0.01" type="number" id="loanRate" value="${loanRate}" name="loanRate" 
                                           required
                                           pattern="^[a-zA-Z0-9_.]{6,20}$" 
                                           title="Interest rate must be a positive number without special characters except [.,]"
                                           oninput="validateLoanRate()">
                                    <span id="nameError"  class="sub-text-input">%/year</span>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="duration" class="form-group__label">Deposit Term (*)</label>
                                <div class="form-group__input-wrap" style="display: flex; align-items: center; ">
                                    <input class="form-group__input" step="1" type="number" id="duration" min="1"; value="${duration}" name="duration" 
                                           required
                                           pattern="^[1-9]\d*$" 
                                           title="Term must be a positive integer, no letters or special characters"
                                           oninput="validateTerm()">
                                    <span id="nameError2"  class="sub-text-input"></span>
                                    <select id="durationUnit" name="durationUnit" class="form-group__select">
                                        <option value="days" ${durationUnit == 'days' ? 'selected' : ''}>Days</option>
                                        <option value="months" ${durationUnit == 'months' ? 'selected' : ''}>Months</option>
                                        <option value="years" ${durationUnit == 'years' ? 'selected' : ''}>Years</option>
                                    </select>
                                </div>
                            </div>

                            <button type="submit" onclick="setMethod('post')"  class="btn-submit">CALCULATE</button>
                            <button type="submit" onclick="setMethod('get')"  class="btn-submit">Download as Excel</button>

                            <input type="hidden" name="totalAmount" value="${totalAmount}">
                            <input type="hidden" name="interestPerDay" value="${interestPerDay}">
                            <input type="hidden" name="interestPerMonth" value="${interestPerMonth}">
                            <input type="hidden" name="interestPerYear" value="${interestPerYear}">
                        </form>

                        <table class="result-table">
                            <tr>
                                <th>Deposit Amount</th>
                                <td>${amount} VND</td>
                            </tr>
                            <tr>
                                <th>Interest per 
                                    ${durationUnit == 'days' ? 'Day' : durationUnit == 'months' ? 'Month' : durationUnit == 'years' ? 'Year' : 'Day'}
                                </th>
                                <td>
                                    ${durationUnit == 'days' ? interestPerDay : durationUnit == 'months' ? interestPerMonth : durationUnit == 'years' ? interestPerYear : interestPerDay} VND
                                </td>                            
                            </tr>
                            <tr>
                                <th>Total Amount at Maturity</th>
                                <td style="color: red;">${totalAmount} VND</td>
                            </tr>
                        </table>
                    </div>
                    <br/>
                    <div class="congthuc">Formula for Fixed-Term Savings Interest Calculation:</div><br/>
                    <div class="red">Interest = Deposit Amount x (Annual Interest Rate / 12) x Deposit Term</div> <br/>

                    Example:<br/>

                    If you deposit 30,000,000 VND for a 12-month term at a bank with an annual interest rate of 6.8%, the interest calculation is as follows:<br/>

                    * Monthly interest = 30,000,000 x (6.8 / 100) / 12 x 1 = 170,000 VND<br/>

                    * Total interest after 12 months = 30,000,000 x (6.8 / 100) / 12 x 12 = 2,040,000 VND<br/>
                </div>

                <!-- footer -->
                <%@ include file="footer.jsp"%>
                <!-- footer end -->
            </div>

            <script>
                function setMethod(method) {
                    const form = document.getElementById('myForm');
                    form.method = method; // Cập nhật method cho form
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
            </script>

            <!-- content end -->
        </div>
        <!-- wrapper end -->

        <!-- jquery js -->
        <script src="js/plugins/jquery.min.js"></script>

        <!-- swiper css -->
        <script src="js/plugins/swiper.min.js"></script>
        <!-- gsap js -->
        <script src="js/plugins/gsap.min.js"></script>
        <!-- scroll smoother -->
        <script src="js/plugins/ScrollSmoother.min.js"></script>
        <!-- scroll trigger js -->
        <script src="js/plugins/ScrollTrigger.min.js"></script>
        <!-- scroll to js -->
        <script src="js/plugins/ScrollTo.min.js"></script>
        <!-- magnific -->
        <script src="js/plugins/magnific-popup.js"></script>
        <!-- plax js -->
        <script src="js/main.js"></script>
        <script src="./js/scripts.js"></script>
    </body>
</html>
