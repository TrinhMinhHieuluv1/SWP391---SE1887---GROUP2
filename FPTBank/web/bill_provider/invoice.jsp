<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Invoice</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
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
            h2 {
                color: #4CAF50; /* Màu xanh lá cây cho tiêu đề */
            }

            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
                background-color: #ffffff;
                box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            }

            th, td {
                padding: 10px;
                text-align: left;
                font-size: 16px;
            }

            th {
                background-color: #4CAF50; /* Màu xanh lá cây cho tiêu đề bảng */
                color: white;
            }

            tr:nth-child(even) {
                background-color: #f9f9f9; /* Màu nền của dòng chẵn */
            }

            tr:hover {
                background-color: #f1f1f1; /* Đổi màu nền khi hover trên dòng */
            }

            td {
                color: #333; /* Màu chữ của các ô */
            }
            .pagination {
                display: flex;
                justify-content: center;
                align-items: center;
                margin: auto;
            }

            .pagination a {
                padding: 9px 10px;
                margin: 0 5px;
                background-color: #f4f4f4;

                color: #333;
                text-decoration: none;
                border-radius: 25px;
                font-weight: bold;
                transition: all 0.3s ease-in-out;
            }

            .pagination a:hover {
                background-color: yellowgreen;
            }

            .pagination a.active {
                background-color: green;
                color: #fff;
                border-radius: 30px;
            }
            select {
                margin-top: 15px;
                background-color: #0d6efd;
                color: white ;
                padding: 3px 8px; /* Giảm padding để ô nhỏ hơn */
                border-radius: 6px; /* Bo tròn góc nhẹ */
                font-size: 14px; /* Giảm kích thước chữ */
                cursor: pointer;
            }


            /* Khi focus vào ô chọn */
            select:focus {
                outline: none;
                box-shadow: 0 0 5px #007bff;
            }
            .control-form{
                width: 50px;
            }
            .filter-group {
                background: white;
                padding: 15px;
                border-radius: 10px;
                box-shadow: 0 2px 8px rgba(0,0,0,0.1);
                margin-bottom: 20px;
            }

            .filter-group label {
                font-weight: 500;
                color: #333;
                margin-right: 15px;
            }

            .filter-group select {
                padding: 8px 12px;
                border: 1px solid #ddd;
                border-radius: 6px;
                background-color: white;
                color: #333;
                font-size: 0.95em;
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
            <h2 style="text-align:  center;">List of Bill</h2>
            <a href="createinvoice"><button style="background-color: green; color: white; margin-left: 90%; margin-bottom: 10px;">Create+</button></a>
            <form action="/timibank/bill_provider/invoice" method="get">
                <div class="filter-group" style="display: flex;">
                    <label for="status" style="margin-left: 30px;">Status:</label>
                    <select name="filterStatus" id="status">
                        <option value="" ${param.filterStatus == 'none' ? 'selected' : ''}>All Status</option>
                        <option value="true" ${param.filterStatus == 'active' ? 'selected' : ''}>Active</option>
                        <option value="false" ${param.filterStatus == 'inactive' ? 'selected' : ''}>Inactive</option>
                    </select>
                    <label for="number" style="margin-left: 30px;" >Number in Page:</label>
                    <select class="form-control" id="statusFilter" name="pagesize">
                        <c:forEach var="num" items="${requestScope.listint}">
                            <option value="${num}" ${param.pagesize == num ? 'selected' : '' }>${num}</option>
                        </c:forEach>
                    </select>
                    <label for="status" style="margin-left: 30px;">Status of Bill:</label>
                    <select class="form-control" id="statusFilter" name="statusbill">
                        <option value="" ${param.statusbill == '' ? 'selected' :''}>-- Select Status --</option>
                        <option value="true" ${param.statusbill == 'true' ? 'selected' : ''}>Paid</option>
                        <option value="false" ${param.statusbill == 'false' ? 'selected' : ''}>Unpaid</option>
                    </select>
                    <label for="status" style="margin-left: 30px;">From:</label>
                    <input class="form-control" type="date" name="date1" placeholder="Date"></input>
                    <label for="status" style="margin-left: 30px;">To:</label>
                    <input class="form-control" type="date" name="date2" placeholder="Date"></input>
                </div>
                <button type="submit" style="background-color: green; color: white">Filter</button>


                <table>
                    <thead>
                        <tr>
                            <th>No.Number</th>
                            <th>CreatedAt</th>
                            <th>Customer</th>
                            <th>Total</th>
                            <th>Status Of Bill</th>
                            <th>Status</th>
                            <th colspan="2">Action</th>
                        </tr>
                    </thead>
                    <c:forEach var="bill" items="${requestScope.listB}">
                        <tbody>
                            <c:set var="count" value="${count + 1}" />
                            <tr>
                                <td>${count}</td>
                                <td>${bill.getCreatedAt()}</td>
                                <td>${bill.getCustomer().getFullName()}</td>
                                <td>${bill.getTotal()}</td>
                                <td>${bill.getStatusOfBill() == 1 ? "Unpaid" : "Paid"}</td>
                                <td>${bill.getStatus() == 1 ? "Active" : "InActive"}</td>
                                <c:if test="${bill.getStatusOfBill() == 1}">
                                    <td><a href="updateinvoice?billID=${bill.getBillID()}&customerID=${bill.getCustomer().getCustomerId()}" 
                                           style="display: inline-block; background-color: green; color: white;
                                           border-radius: 5px; padding: 5px 10px; text-decoration: none;">
                                            Update
                                        </a></td>
                                    </c:if>
                                <td><a href="billdetail?billID=${bill.getBillID()}&providerID=${bill.getProvider().getUserID()}" 
                                       style="display: inline-block; background-color: green; color: white;
                                       border-radius: 5px; padding: 5px 10px; text-decoration: none;">
                                        Detail
                                    </a></td>
                            </tr>
                        </tbody>
                    </c:forEach>
                </table>
            </form>

            <!-- Pagination Controls -->
            <div class="pagination">
                <c:if test="${currentPage > 1}">
                    <a href="invoice?page=${currentPage - 1}&filterStatus=${param.filterStatus}&statusresponse=${param.statusbill}&pagesize=${param.pagesize}&date1=${param.date1}&date2=${param.date2}" 
                       class="prev">Previous</a>
                </c:if>

                <c:forEach var="i" begin="1" end="${totalPages}">
                    <a href="invoice?page=${i}&filterStatus=${param.filterStatus}&statusresponse=${param.statusbill}&pagesize=${param.pagesize}&date1=${param.date1}&date2=${param.date2}" class="${i == currentPage ? 'active' : ''}">${i}</a>
                </c:forEach>

                <c:if test="${currentPage < totalPages}">
                    <a href="invoice?page=${currentPage + 1}&filterStatus=${param.filterStatus}&statusresponse=${param.statusbill}&pagesize=${param.pagesize}&date1=${param.date1}&date2=${param.date2}" class="next">Next</a>
                </c:if>
            </div>

        </div>
    </body>
</html>
