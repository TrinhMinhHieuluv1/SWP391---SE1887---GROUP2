<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Invoice</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <style>
            body {
                background-color: #e9f5e9;
                font-family: Arial, sans-serif;
            }
            .invoice-box {
                max-width: 800px;
                margin: auto;
                padding: 20px;
                border: 1px solid #52b788;
                box-shadow: 0 0 10px rgba(0, 128, 0, 0.15);
                background-color: #ffffff;
                border-radius: 10px;
            }
            .invoice-header {
                display: flex;
                justify-content: space-between;
                align-items: center;
            }
            .invoice-header h2 {
                color: #2d6a4f;
            }
            .table th {
                background-color: #52b788;
                color: white;
            }
            .total-section {
                text-align: right;
                font-weight: bold;
            }
            .total-section .total {
                background-color: #40916c;
                color: white;
                padding: 10px;
                border-radius: 5px;
            }
        </style>
    </head>
    <body>
        <div class="invoice-box">
            <form action="detailbillcustomer" method="get">
                <div class="invoice-header">
                    <img src="img/logo1.png" alt="Plax" width="200">
                    <div>
                        <p><strong>Date:</strong>${billdetail.getCreatedAt()}</p>
                        <p><strong>Invoice #:</strong> ${billdetail.getBillID()}</p>
                    </div>
                </div>
                <hr>
                <div>
                    <h4>${company.getCompanyName()}</h4>
                    <p>Number: ${billdetail.getProvider().getPhone()}<br>${billdetail.getProvider().getAddress()}<br>${billdetail.getProvider().getEmail()}</p>
                </div>
                <div>
                    <h4>${billdetail.getCustomer().getFullName()}</h4>
                    <p>Number: ${billdetail.getCustomer().getPhone()}<br>${billdetail.getCustomer().getAddress()}<br>${billdetail.getCustomer().getEmail()}</p>
                </div>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Title</th>
                            <th>Description</th>
                            <th>StartDate</th>
                            <th>EndDate</th>
                            <th>StatusOfBill</th>
                            <c:if test="${billdetail.getPaymentDate() != null}">
                            <th>PaymentDate</th>
                            </c:if>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>1</td>
                            <td>${billdetail.getTitle()}</td>
                            <td>${billdetail.getDescription()}</td>
                            <td>${billdetail.getStartDate()}</td>
                            <td>${billdetail.getEndDate()}</td>
                            <td>${billdetail.getStatusOfBill() == 1 ? "UnPaid" : "Paid"}</td>
                            <c:if test="${billdetail.getPaymentDate() != null}">
                            <td>${billdetail.getPaymentDate()}</td>
                            </c:if>
                        </tr>
                        
                    </tbody>
                </table>
                <div class="total-section">
                    <p class="total">Total: ${billdetail.getTotal()}</p>
                </div>
            </form>
        </div>
    </body>
</html>
