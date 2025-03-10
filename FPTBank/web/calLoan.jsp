<%-- 
    Document   : congcu2
    Created on : Jan 24, 2025, 10:49:03 AM
    Author     : ADMIN
--%>
<%@ page import="java.time.LocalDate" %>
<%
    String today = LocalDate.now().toString();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">

        <title>TIMI - Công cụ tính lãi suất</title>
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

        .form-group .radio-group {
            display: flex;
            align-items: center;
        }
        .form-group .radio-group input {
            margin-right: 5px;
        }
        .form-group .radio-group label {
            margin-right: 20px;
        }
        .radio-label {
            font-size: 18px;
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

        .details-title {
            font-weight: bold;
            font-size: 18px;
            margin-top: 30px;
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
        .monthly-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        .monthly-table th, .monthly-table td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        .monthly-table th {
            background-color: #f2f2f2;
            font-weight: bold;
        }

        .monthly-table tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        .monthly-table tr:hover {
            background-color: #f1f1f1;
        }
        #downloadexcel {
            display: none; /* Ẩn form ban đầu */
            margin-top: 10px;
            width: fit-content;
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
            <c:if test="${not empty sessionScope.message1}">
                <div id="toastMessage" class="toast-message">
                    <i class="fa fa-check-circle"></i>
                    ${sessionScope.message1}
                </div>
                <c:remove var="message1" scope="session" />
            </c:if>
            <!-- content -->
            <div id="smooth-content">
                <div class="cal-container">
                    <a href="about"><i class="fa-solid fa-arrow-left-long"></i></a>
                    <div class="tabs">
                        <a href="calSaving.jsp" class="tab">LÃI SUẤT TIẾT KIỆM</a>
                        <a href="calLoan.jsp" class="tab active">LÃI VAY NGÂN HÀNG</a>
                    </div>
                    <div class="title">CÔNG CỤ TÍNH LÃI SUẤT VAY NGÂN HÀNG</div>
                    <div class="description"><br/>
                        Công cụ tính lãi suất vay ngân hàng của LuatVietnam giúp bạn dự tính được số tiền lãi phải trả định kỳ, 
                        tổng gốc và lãi trong từng thời điểm. Từ đó dễ dàng hoạch định tài chính tốt nhất cho mình.<br/><br/>
                    </div>
                    <c:if test="${not empty error}">
                        <div class="error-message" style="color: red; font-weight: bold;">
                            ${error}
                        </div>
                    </c:if>

                    <div class="cal-content ">
                        <form action="congcu2"  id="myForm" class="cal-form">

                            <div class="form-group">
                                <label for="nameLoan" class="form-group__label">Tên Người dùng</label>
                                <div class="form-group__input-wrap">
                                    <input type="text" class="form-group__input" id="nameLoan" name="nameLoan" value="${nameLoan}" 
                                           required>

                                </div>
                            </div>

                            <div class="form-group">
                                <label for="emailLoan" class="form-group__label">Email</label>
                                <div class="form-group__input-wrap">
                                    

                                    <input type="email" class="form-group__input" id="emailLoan" name="emailLoan" value="${emailLoan}" 
                                           required
                                           pattern="^(?!.*\.\.)[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$" 
                                           title="Email is invalid"
                                           oninput="validateEmail()">
                                    <span id="emailError"  class="sub-text-input"></span>
                                </div>
                                          
                                          
                                          
                            </div>


                            <div class="form-group">
                                <label for="loanAmount" class="form-group__label">Số tiền vay</label>
                                <div class="form-group__input-wrap">
                                    <input type="text" class="form-group__input" id="loanAmount" name="loanAmount" value="${loanAmount}"  
                                           oninput="formatNumber(this)" onkeypress="return validateInput(event)" required>
                                    <span class="sub-text-input">VNĐ</span>
                                </div>
                            </div>
                            <div class="form-group" >
                                <label for="loanRate" class="form-group__label">Lãi suất vay</label>
                                <div class="form-group__input-wrap" >

                                    <input class="form-group__input" step="0.01" type="number" id="loanRate" value="${loanRate}" name="loanRate" 
                                           required
                                           pattern="^[a-zA-Z0-9_.]{6,20}$" 
                                           title="LoanRate phải là số dương, không chứa chữ cái, không kí tự đặc biệt ngoài[.,]"
                                           oninput="validateLoanRate()">
                                    <span id="nameError"  class="sub-text-input">%/năm</span>
                                </div>

                            </div>
                            <div class="form-group" >
                                <label for="loanMonths" class="form-group__label">Kỳ hạn vay</label>
                                <div class="form-group__input-wrap">
                                    <select style="width: 50%" class="form-group__input" name="loanMonths" id="numberSelect">
                                        <option  value="${loanMonths}" selected >${loanMonths}</option>
                                    </select>
                                    <span class="sub-text-input">Tháng</span>
                                </div>
                            </div>
                            <div class="form-group" >
                                <label class="form-group__label">Phương pháp tính:</label>
                                <div class="radio-group">
                                    <input type="radio" id="reducingBalance" name="calculationMethod" value="reducing" 
                                           ${calculationMethod == 'reducing' ? 'checked' : ''}>
                                    <label for="reducingBalance" class="radio-label">Trả trên dư nợ giảm dần</label>

                                    <input type="radio" id="initialBalance" name="calculationMethod" value="initial"
                                           ${calculationMethod == 'initial' ? 'checked' : ''}>
                                    <label for="initialBalance" class="radio-label">Trả trên dư nợ ban đầu</label>
                                </div>

                            </div>
                            <input type="hidden" name="TotalloanResult" value="${loanResult}" > 
                            <input type="hidden" name="TotalPayment" value="${total}" >
                            <input type="hidden" name="dateScheducle" value="${todayhour}" >



                            <div class="form-group">
                                <label for="disbursementDate" class="form-group__label">Ngày giải ngân</label>
                                <div class="form-group__input-wrap">
                                    <input type="date" class="form-group__input" id="disbursementDate" name="disbursementDate" 
                                           value="<%= today %>" required> <!-- Hiển thị ngày hiện tại -->
                                </div>
                            </div>
                            <button type="submit"  onclick="setMethod('post')" class="btn-submit">THỰC HIỆN</button>

                            <button type="submit" id="save" onclick="setMethod('get')" class="btn-submit">Click xác nhận để tải thông tin xuống</button>


                        </form>


                        <table class="result-table">
                            <tr>
                                <th>Số tiền vay</th>
                                <td>${loanAmount} VNĐ</td>
                            </tr>
                            <tr>
                                <th>Lãi suất vay</th>
                                <td>${loanRate} %/năm</td>
                            </tr>
                            <tr>
                                <th>Kỳ hạn vay</th>
                                <td>${loanMonths} Tháng</td>
                            </tr>
                            <tr>
                                <th>Phương pháp tính</th>
                                <td>${calculationMethod == 'reducing' ? 'Trả trên dư nợ giảm dần' : 'Trả trên dư nợ ban đầu'}</td>
                            </tr>
                            <tr>
                                <th>Tổng tiền lãi</th>
                                <td>${loanResult} VNĐ</td>
                            </tr>
                            <tr>
                                <th>Tổng số tiền phải trả</th>
                                <td style="color: red;">${total} VNĐ</td>
                            </tr>
                            <tr>
                                <th>Ngày giải ngân</th>
                                <td>${today}</td> <!-- Hiển thị ngày hiện tại -->
                            </tr>
                        </table>
                    </div>
                    <form  action="export" method="get">
                        <input type="hidden" name="dateScheducle2" value="${dateScheducle2}">
                        <c:if test="${ms == 'Add sucesslly'}" >

                            <button type="submit" class="btn-submit">Đồng ý tải xuống Excel</button>
                        </c:if>

                        <br/><!-- comment -->
                    </form>




                    <br><!-- comment -->
                    <!-- Bảng hiển thị kết quả tổng -->
                    <c:if test="${not empty loanResult}">


                        <!-- Bảng chi tiết từng tháng -->
                        <h3>Chi tiết từng tháng</h3>
                        <table class="monthly-table">
                            <tr>
                                <th>Tháng</th>
                                <th>Ngày thanh toán</th>
                                <th>Số gốc còn lại</th>
                                <th>Tiền gốc</th>
                                <th>Tiền lãi</th>
                                <th>Tổng tiền phải trả</th>
                            </tr>
                            <c:forEach var="payment" items="${monthlyPayments}">
                                <tr>
                                    <td>${payment.month}</td>
                                    <td>${payment.paymentDate}</td>
                                    <td>${payment.remainingPrincipal} VNĐ</td>
                                    <td>${payment.principal} VNĐ</td>
                                    <td>${payment.interest} VNĐ</td>
                                    <td>${payment.totalPayment} VNĐ</td>
                                </tr>

                            </c:forEach>
                            <tr>
                                <td style="color: red;"> Tổng tiền </td>
                                <td></td>
                                <td></td>
                                <td>${loanAmount} VNĐ</td>
                                <td>${loanResult} VNĐ</td>
                                <td>${total} VNĐ</td>

                            </tr>
                        </table>
                    </c:if>
                    <div class="details-title">DIỄN GIẢI CHI TIẾT CÁCH TÍNH LÃI SUẤT VAY NGÂN HÀNG:</div>
                    <br/>
                    <div class="congthuc"> <div>
                            <h3>Cách 1: Cách tính lãi suất vay theo số dư nợ gốc</h3> <br/>
                            Với cách tính lãi suất vay theo số dư nợ gốc, tiền lãi của mỗi kỳ trả lãi sẽ bằng nhau trong toàn bộ quá trình vay và được tính theo số tiền gốc ban đầu.

                            <br/> <div class="red">Tiền lãi hàng tháng = Dư nợ gốc x Lãi suất vay/Thời gian vay</div> <br/>

                            Ví dụ:<br/>

                            A vay 300 triệu đồng trong 12 tháng với mức lãi suất là 12%/năm.<br/>

                            Số tiền gốc phải trả ngân hàng hàng tháng là: 300 triệu/12 tháng = 25.000.000 đồng<br/>

                            Số lãi phải trả ngân hàng hàng tháng là: (300 triệu x 12%)/12 tháng = 3.000.000 đồng<br/>

                            Số tiền phải trả hàng tháng là 28.000.000 đồng <br/>
                        </div><br/><!-- comment -->
                        <div>
                            <h3> Cách 2: Cách tính lãi suất vay theo số dư nợ giảm dần</h3><br/>

                            Trường hợp tính lãi suất vay theo số dư nợ giảm dần thì số dư nợ dựa trên số tiền thực tế còn nợ sau khi đã trừ đi phần nợ gốc người vay đã trả trong những tháng trước đó. Số dư nợ giảm dần thì tiền lãi mà người vay phải trả cũng giảm dần.<br/>

                            Công thức tính lãi suất theo dư nợ giảm dần cụ thể như sau:<br/>
                            <div class="red"> - Tiền gốc hàng tháng = Số tiền vay/số tháng vay<br/>

                                - Tiền lãi tháng đầu = Số tiền vay x Lãi suất vay theo tháng<br/>

                                - Tiền lãi các tháng tiếp theo = Số tiền gốc còn lại x Lãi suất vay<br/></div>

                            <br/>

                            Ví dụ:<br/>

                            B vay 300 triệu đồng thời hạn trong 12 tháng với mức lãi suất 12%/năm<br/>

                            Tiền gốc trả hàng tháng = 300triệu/12 = 25.000.000 đồng<br/>

                            Tiền lãi tháng đầu = (300 triệu x 12%)/12 = 3.000.000 đồng. <br/>Tổng số tiền phải trả tháng đầu là 25 triệu + 3 triệu = 28.000.000 đồng.<br/>

                            Tiền lãi tháng thứ 2 = (300 triệu - 25 triệu) x 12%/12 = 2.750.000 đồng. Tổng tiền phải trả tháng thứ 2 là 25 triệu + 2.750.000 = 27.750.000 đồng.<br/>

                            Tiền lãi tháng thứ 3 = (275 triệu - 25 triệu) x 12%/12 = 2.500.000 đồng. Tổng tiền phải trả tháng thứ 3 là 25 triệu + 2,5 triệu = 27.500.000 đồng.<br/>

                            Các tháng tiếp theo tính tương tự như vậy cho đến khi hết nợ.<br/>

                        </div></div>


                </div>
                <!-- footer -->
                <%@ include file="footer.jsp"%>
                <!-- footer end -->
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
            </div>
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
