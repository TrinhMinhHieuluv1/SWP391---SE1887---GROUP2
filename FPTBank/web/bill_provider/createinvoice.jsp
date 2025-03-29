<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>TIMI - Finance</title>
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

        <!-- Toarst -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <style>
            .sidebar {
                width: 250px; /* or any other width you prefer */
                background-color: green;
                position: fixed; /* Fix the sidebar to the side */
                top: 0;
                left: 0;
                height: 100%;
                padding-top: 20px;
                display: block; /* Ensure it's visible */
                z-index: 1000; /* Ensure it’s on top of other elements */
            }

            .sidebar a {
                padding: 8px 16px;
                text-decoration: none;
                font-size: 18px;
                color: white;
                display: block;
            }

            .sidebar a:hover {
                background-color: #575757;
            }
            .sidebar img {
                display: block;  /* Ensure it's displayed */
                max-width: 100%;  /* Ensure it fits the sidebar */
                height: auto;  /* Keep the aspect ratio */
            }
            .content {
                margin-left: 260px;
                padding: 20px;
            }
            .table-container {
                display: grid;
                grid-template-columns: 1fr 1fr; /* Cột đầu tiên (label) rộng 150px, cột thứ hai (input) chiếm phần còn lại */
                gap: 5px;
                border: 1px solid #ddd; /* Viền ngoài bảng */
                border-radius: 8px; /* Bo góc nhẹ */
                padding: 10px;
                max-width: 100%; /* Độ rộng tối đa */
                background-color: #f9f9f9; /* Màu nền nhạt */
            }

            .table-header {
                font-weight: bold;
                padding: 8px;
                background: #e0e0e0; /* Nền xám nhạt cho header */
                border-right: 1px solid #ddd; /* Viền phải */
                display: flex;
                align-items: center;
            }

            .table-cell {
                padding: 8px;
            }

            .table-cell input {
                width: 100%;
                padding: 6px;
                border: 1px solid #ccc;
                border-radius: 4px;
                font-size: 14px;
            }
        </style>
    </head>
    <body>
        <div class="sidebar">
            <a href="/timibank/home" class="mil-logo">
                <i class="fas fa-home"></i> Home <!-- Add home icon here -->
            </a>
            <a href="/timibank/bill_provider/dashboardbillprovider"><i class="fas fa-tachometer-alt"></i> Dashboard</a>
            <a href="/timibank/bill_provider/invoice"><i class="fas fa-file-invoice"></i> Invoices</a>
            <a href="/timibank/logout"><i class="fas fa-sign-out-alt"></i> Logout</a> <!-- Logout icon -->
        </div>
        <div class="content">
            <div class="container mt-4">
                <h2>Create Invoice</h2>
                <form action="createinvoice" method="get">
                    <div class="form-row">
                        <div class="form-group col-md-4">
                            <label>Customer *</label>
                            <select name="customerid" id="customerSelect" class="form-control">
                                <option value="none">--Select Customer--</option>
                                <c:if test="${not empty requestScope.listC}">
                                    <c:forEach var="customer" items="${requestScope.listC}">
                                        <c:set var="isSelected" value="${not empty param.customerid and param.customerid eq customer.customerId.toString()}" />
                                        <option value="${customer.customerId}" 
                                                ${isSelected ? 'selected' : ''}
                                                data-phone="${customer.phone}" 
                                                data-email="${customer.email}"
                                                data-address="${customer.address}"
                                                data-dob="${customer.dateOfBirth}"
                                                data-cccd="${customer.CCCD}"
                                                data-gender="${customer.gender == true ? "Male" : "Female"}">
                                            ${customer.fullName}
                                        </option>
                                    </c:forEach>
                                </c:if>
                            </select>
                        </div>
                        <div class="form-group col-md-4">
                            <label>Phone</label>
                            <input type="text" id="phoneInput" class="form-control" readonly>
                        </div>
                        <div class="form-group col-md-4">
                            <label>Email</label>
                            <input type="email" id="emailInput" class="form-control" readonly>
                        </div>
                    </div>
                    <script>
                        document.addEventListener("DOMContentLoaded", function () {
                            let customerSelect = document.getElementById("customerSelect");
                            function updateCustomerInfo() {
                                let selectedOption = customerSelect.options[customerSelect.selectedIndex];
                                document.getElementById("phoneInput").value = selectedOption.getAttribute("data-phone") || "";
                                document.getElementById("emailInput").value = selectedOption.getAttribute("data-email") || "";
                                document.getElementById("addressInput").value = selectedOption.getAttribute("data-address") || "";
                                document.getElementById("dobInput").value = selectedOption.getAttribute("data-dob") || "";
                                document.getElementById("cccdInput").value = selectedOption.getAttribute("data-cccd") || "";
                                document.getElementById("genderInput").value = selectedOption.getAttribute("data-gender") || "";
                            }

                            // Gọi ngay khi trang load
                            updateCustomerInfo();

                            // Gọi khi chọn khách hàng mới
                            customerSelect.addEventListener("change", updateCustomerInfo);
                        });
                    </script>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label>Address</label>
                            <input id="addressInput" class="form-control" readonly></input>
                        </div>
                        <div class="form-group col-md-6">
                            <label>Date Of Birth</label>
                            <input id="dobInput" class="form-control" readonly></input>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label>CCCD</label>
                            <input id="cccdInput" class="form-control" readonly></input>
                        </div>
                        <div class="form-group col-md-6">
                            <label>Gender</label>
                            <input id="genderInput" class="form-control" readonly></input>
                        </div>
                    </div>


                    <div class="table-container">
                        <div class="table-row">
                            <div class="table-header">Title</div>
                            <div class="table-cell"><input type="text" name="title" value="${not empty param.title ? param.title : requestScope.bill.title}" class="form-control"></div>
                        </div>
                        <div class="table-row">
                            <div class="table-header">Description</div>
                            <div class="table-cell"><input type="text" name="description" value="${not empty param.description ? param.description : requestScope.bill.description}" class="form-control"></div>
                        </div>
                        <div class="table-row">
                            <div class="table-header">Start Date</div>
                            <div class="table-cell"><input type="date" id="startdate" name="startdate" value="${not empty param.startdate ? param.startdate : (not empty requestScope.bill.startDate ? requestScope.bill.startDate : '')}" class="form-control"></div>
                        </div>
                        <div class="table-row">
                            <div class="table-header">End Date</div>
                            <div class="table-cell"><input type="date" id="enddate" name="enddate" value="${not empty param.enddate ? param.enddate : (not empty requestScope.bill.endDate ? requestScope.bill.endDate : '')}" class="form-control"></div>
                        </div>

                        <div class="table-row">
                            <div class="table-header">Total</div>
                            <div class="table-cell">
                                <input type="text" id="total" name="total" 
                                       value="${param.total != null ? param.total : requestScope.bill.total}"
                                       class="form-control" oninput="formatNumber(this)">
                            </div>
                        </div>
                        <script>
                            function formatNumber(input) {
                                let value = input.value.replace(/,/g, '').replace(/\s/g, ''); // Xóa dấu , cũ nếu có
                                value = value.replace(/\D/g, ''); // Chỉ giữ lại số

                                if (value) {
                                    input.value = parseInt(value, 10).toLocaleString('en-US'); // Định dạng theo kiểu Mỹ
                                }
                            }
                        </script>

                    </div>
                    <% if(request.getAttribute("error")!=null)  {%>
                    <a style="color:red; font-style: italic; "><%out.println(request.getAttribute("error"));%></a>
                    <%}%>
                    <br>
                    <button type="submit" name="action" value="Add" class="btn btn-primary">Save</button>
                </form>
            </div>
        </div>
    </body>
</html>
