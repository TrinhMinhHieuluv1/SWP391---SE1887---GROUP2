<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
        </style>
    </head>
    <body>
        <div class="sidebar">

            <img src="img/logo1.png" alt="Plax" width="200">
            <a href="/timibank/invoice"><i class="fas fa-tachometer-alt"></i> Dashboard</a>

            <a href="/timibank/invoice"><i class="fas fa-file-invoice"></i> Invoices</a>

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
                                    <option value="${customer.getCustomerId()}" 
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
                        document.getElementById("customerSelect").addEventListener("change", function () {
                            let selectedOption = this.options[this.selectedIndex];
                            document.getElementById("phoneInput").value = selectedOption.getAttribute("data-phone") || "";
                            document.getElementById("emailInput").value = selectedOption.getAttribute("data-email") || "";
                            document.getElementById("addressInput").value = selectedOption.getAttribute("data-address") || "";
                            document.getElementById("dobInput").value = selectedOption.getAttribute("data-dob") || "";
                            document.getElementById("cccdInput").value = selectedOption.getAttribute("data-cccd") || "";
                            document.getElementById("genderInput").value = selectedOption.getAttribute("data-gender") || "";
                        });
                    </script>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label>Address</label>
                            <input id="addressInput" class="form-control"></input>
                        </div>
                        <div class="form-group col-md-6">
                            <label>Date Of Birth</label>
                            <input id="dobInput" class="form-control"></input>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label>CCCD</label>
                            <input id="cccdInput" class="form-control"></input>
                        </div>
                        <div class="form-group col-md-6">
                            <label>Gender</label>
                            <input id="genderInput" class="form-control"></input>
                        </div>
                    </div>


                    <table class="table table-bordered">
                        <thead>
                            <tr>

                                <th>Title</th>
                                <th>Description</th>
                                <th>StartDate</th>
                                <th>EndDate</th>
                                <th>Total</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>

                                <td><input type="text" name="title" class="form-control"></td>
                                <td><input type="text" name="description" class="form-control"></td>
                                <td><input type="date" name="startdate" class="form-control"></td>
                                <td><input type="date" name="enddate" class="form-control"></td>
                                <td><input type="text" name="total" class="font-control"></td>
                            </tr>
                        </tbody>
                    </table>
                    <% if(request.getAttribute("error")!=null)  {%>
                    <a style="color:red; font-style: italic; "><%out.println(request.getAttribute("error"));%></a>
                    <%}%>
                    <br>
                    <button type="submit" class="btn btn-primary">Save</button>
                </form>
            </div>
        </div>
    </body>
</html>
