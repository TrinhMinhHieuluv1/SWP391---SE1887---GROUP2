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
        </style>
    </head>
    <body>
        <div class="sidebar">
            <img src="img/logo1.png" alt="Plax" width="200">
            <a href="/timibank/invoice"><i class="fas fa-tachometer-alt"></i> Dashboard</a>
            <a href="/timibank/invoice"><i class="fas fa-file-invoice"></i> Invoices</a>
        </div>
        <form>
            
        </form>
        <div class="content">
            <h2 style="text-align:  center;">List of Bill</h2>
            <a href="createinvoice"><button style="background-color: green; color: white;">Create+</button></a>
            <form action="invoice" method="get">
                <table>
                <thead>
                    <tr>
                        <th>No.Number</th>
                        <th>CreatedAt</th>
                        <th>Customer</th>
                        <th>Total</th>
                        <th>Status</th>
                        <th colspan="2">Action</th>
                    </tr>
                </thead>
                <c:forEach var="bill" items="${requestScope.listB}">
                    <tbody>
                    <tr>
                        <td>${bill.getBillID()}</td>
                        <td>${bill.getCreatedAt()}</td>
                        <td>${bill.getCustomer().getFullName()}</td>
                        <td>${bill.getTotal()}</td>
                        <td>${bill.getStatus() == 1 ? "Unpaid" : "Paid"}</td>
                        <td><button type="submit" style="background-color: green; color: white; border-radius: 5px; text-decoration: none"><a href="updateinvoice?billID=${bill.getBillID()}" style="text-decoration: none;">Update</a></button></td>
                        <td><a href=""><button style="background-color: green; color: white; border-radius: 5px; margin-left: 0px;">Detail</button></a></td>
                    </tr>
                </tbody>
                </c:forEach>
            </table>
            </form>
        </div>
    </body>
</html>
