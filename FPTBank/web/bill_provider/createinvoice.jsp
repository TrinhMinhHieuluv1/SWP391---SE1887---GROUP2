<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Create Invoice</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <style>
            .sidebar {
                height: 100vh;
                width: 250px;
                position: fixed;
                top: 0;
                left: 0;
                background-color: #f8f9fa;
                padding-top: 20px;
                background-color: green;
            }
            .sidebar a {
                padding: 10px 15px;
                text-decoration: none;
                font-size: 18px;
                color: #333;
                display: block;
                color : white;
            }
            .sidebar a:hover {
                background-color: #ddd;
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

            <img src="img/logo1.png" alt="Plax" width="200">
            <a href="/timibank/bill_provider/invoice"><i class="fas fa-tachometer-alt"></i> Dashboard</a>

            <a href="/timibank/bill_provider/invoice"><i class="fas fa-file-invoice"></i> Invoices</a>

        </div>
        <div class="content">
            <div class="container mt-4">
                <h2>Create Invoice</h2>
                <form action="createinvoice" method="get">
                    <div class="form-row">
                        <div class="form-group col-md-4">
                            <label>Customer *</label>
                            <select name="customerid" id="customerSelect" class="form-control">
                                <option>--Select Customer--</option>
                                <c:forEach var="customer" items="${requestScope.listC}">
                                    <option value="${customer.getCustomerId()}" ${param.customerid == customer.getCustomerId() ? 'selected' : ''}
                                            data-phone="${customer.getPhone()}" 
                                            data-email="${customer.getEmail()}" 
                                            data-address="${customer.getAddress()}" 
                                            data-dob="${customer.getDateOfBirth()}"
                                            data-cccd="${customer.getCCCD()}"
                                            data-gender="${customer.isGender() == true ? "Male" : "Female"}"
                                            >${customer.getFullName()}</option>
                                </c:forEach>
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
                            <div class="table-cell"><input type="date" name="startdate" value="${not empty param.startdate ? param.startdate : (not empty requestScope.bill.startDate ? requestScope.bill.startDate : '')}" class="form-control"></div>
                        </div>
                        <div class="table-row">
                            <div class="table-header">End Date</div>
                            <div class="table-cell"><input type="date" name="enddate" value="${not empty param.enddate ? param.enddate : (not empty requestScope.bill.endDate ? requestScope.bill.endDate : '')}" class="form-control"></div>
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
