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
            <a href="/timibank/bill_provider/dashboardbillprovider"><i class="fas fa-tachometer-alt"></i> Dashboard</a>

            <a href="/timibank/bill_provider/invoice"><i class="fas fa-file-invoice"></i> Invoices</a>

        </div>
        <div class="content">
            <div class="container mt-4">
                <h2>Update Invoice</h2>
                <form action="updateinvoice" method="get">
                    <div class="form-row">
                        <input name="billID" value="${requestScope.bill.billID}" hidden>
                        <div class="form-group col-md-4">
                            <label>Customer *</label>
                            <input type="text" class="form-control"  name="customerName" value="${requestScope.bill.customer.fullName}" readonly>
                        </div>
                        <div class="form-group col-md-4">
                            <label>Phone</label>
                            <input type="text" id="phoneInput" class="form-control" value="${requestScope.bill.customer.phone}"  readonly>
                        </div>
                        <div class="form-group col-md-4">
                            <label>Email</label>
                            <input type="email" id="emailInput" class="form-control" value="${requestScope.bill.customer.email}"  readonly>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label>Address</label>
                            <input id="addressInput" class="form-control" value="${requestScope.bill.customer.address}"  readonly></input>
                        </div>
                        <div class="form-group col-md-6">
                            <label>Date Of Birth</label>
                            <input id="dobInput" class="form-control" value="${requestScope.bill.customer.dateOfBirth}"  readonly></input>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label>CCCD</label>
                            <input id="cccdInput" class="form-control" value="${requestScope.bill.customer.CCCD}" readonly></input>
                        </div>
                        <div class="form-group col-md-6">
                            <label>Gender</label>
                            <input id="genderInput" class="form-control" value="${requestScope.bill.customer.gender == true ? "Male" : "Female"}" readonly></input>
                        </div>
                    </div>


                    <div class="table-container">
                        <div class="table-row">
                            <div class="table-header">Title</div>
                            <div class="table-cell"><input type="text" name="title" value="${requestScope.bill.title}" class="form-control"></div>
                        </div>
                        <div class="table-row">
                            <div class="table-header">Description</div>
                            <div class="table-cell"><input type="text" name="description" value="${requestScope.bill.description}" class="form-control"></div>
                        </div>
                        <div class="table-row">
                            <div class="table-header">Start Date</div>
                            <div class="table-cell"><input type="date" name="startdate" value="${requestScope.bill.startDate}" class="form-control"></div>
                        </div>
                        <div class="table-row">
                            <div class="table-header">End Date</div>
                            <div class="table-cell"><input type="date" name="enddate" value="${requestScope.bill.endDate}" class="form-control"></div>
                        </div>
                        <div class="table-row">
                            <div class="table-header">Total</div>
                            <div class="table-cell"> <input type="text" id="total" name="total" 
                                                            value="<fmt:formatNumber value='${requestScope.bill.total}' pattern='#,###'/>"
                                                            class="form-control" oninput="formatNumber(this)"></div>
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
                        <div class="table-row">
                            <div class="table-header">Status</div>
                            <div class="table-cell">
                                <select name="status" class="form-control">
                                    <option value="1" ${requestScope.bill.status == 1 ? 'selected' : ''}>Activce</option>
                                    <option value="0" ${requestScope.bill.status == 0 ? 'selected' : ''}>Inactive</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <% if(request.getAttribute("error")!=null)  {%>
                    <a style="color:red; font-style: italic; "><%out.println(request.getAttribute("error"));%></a>
                    <%}%>
                    <br>
                    <button type="submit" name="action" value="Update" class="btn btn-primary">Save</button>
                </form>
            </div>
        </div>
    </body>
</html>
