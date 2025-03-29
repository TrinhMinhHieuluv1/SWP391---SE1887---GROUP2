<%-- 
    Document   : dashboardbillprovider
    Created on : Mar 16, 2025, 2:36:33 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
            .content {
                margin-left: 260px;
                padding: 20px;
            }
            .chart-container {
                width: calc(100% - 150px); /* Thu hẹp một chút so với khoảng trắng */
                margin-left: 50px; /* Đẩy nội dung ra khỏi sidebar */
                padding: 20px;
            }

            canvas {
                display: block;
                margin: 20px auto;
                width: 100%; /* Giữ nhỏ hơn một chút */
                max-width: 800px; /* Giới hạn kích thước tối đa */
                height: auto;
            }
            /* Container Styling */
            .container {
                margin-top: 50px;
                padding: 0 15px; /* Adjust the padding to prevent overflow */
                margin-left: 260px;
            }

            /* Row Styling: Make sure the cards stay in a row */
            .row {
                display: flex;
                justify-content: space-evenly;
                gap: 20px; /* Add space between cards */
                flex-wrap: wrap; /* Allow cards to wrap to the next line if needed */
                margin: 0; /* Remove margin on row to prevent unwanted overflow */
            }

            /* Card Styling */
            .card {
                background-color: white; /* White background */
                border-radius: 10px; /* Rounded corners for the cards */
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* Light shadow around the cards */
                flex: 1 1 calc(33.333% - 20px); /* Make each card take up 1/3 of the row, minus gap space */
                min-width: 250px; /* Set a minimum width for the card */
                border: 2px solid transparent; /* Initially transparent border */
                transition: border-color 0.3s ease; /* Smooth transition on hover */
                margin-bottom: 20px; /* Add margin-bottom for spacing between rows */
            }

            /* Card Body Styling */
            .card-body {
                text-align: center;
                padding: 20px;
            }

            .card h5 {
                font-size: 18px;
                font-weight: 500;
                margin-bottom: 15px;
            }

            .card h3 {
                font-size: 32px;
                font-weight: 700;
            }

            /* Border Colors for Each Card */
            .card.bg-info {
                border-color: #17a2b8; /* Blue border */
            }

            .card.bg-success {
                border-color: #28a745; /* Green border */
            }

            .card.bg-warning {
                border-color: #ffc107; /* Yellow border */
            }

            /* Hover Effect */
            .card:hover {
                border-color: #333; /* Dark border color on hover */
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
        <form action="dashboardbillprovider" method="get">
            <div class="container mt-5">
                <h2>Welcome, ${companyName}</h2>

                <!-- Statistics Cards -->
                <div class="row">
                    <div class="col-md-4">
                        <div class="card bg-info text-white mb-4">
                            <div class="card-body">
                                <h5>Renevue</h5>
                                <h3><fmt:formatNumber value="${total}" type="number" pattern="#,###" /> VND</h3>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card bg-success text-white mb-4">
                            <div class="card-body">
                                <h5>Total Bill</h5>
                                <h3>${totalbill}</h3>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card bg-warning text-white mb-4">
                            <div class="card-body">
                                <h5>Total Customers</h5>
                                <h3>${totalCustomer}</h3>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Statistics Cards -->
                <div class="chart-container">
                    <h2 style="text-align: center;">Total Revenue</h2>
                    <select style="width: 80px; height: 40px; border-radius: 2px; margin-left: 100px;" name="year" onchange="this.form.submit()">
                        <option value="2025" ${param.year == '2025' ? 'selected' : ''}>2025</option>
                        <option value="2024" ${param.year == '2024' ? 'selected' : ''}>2024</option>
                        <option value="2023" ${param.year == '2023' ? 'selected' : ''}>2023</option>
                        <option value="2022" ${param.year == '2022' ? 'selected' : ''}>2022</option>
                        <option value="2021" ${param.year == '2021' ? 'selected' : ''}>2021</option>
                    </select>

                    <div> <canvas id="barChart"></canvas>
                        <h2 style="text-align: center;">Total Customer</h2>
                        <select style="width: 80px; height: 40px; border-radius: 2px; margin-left: 100px;" name="yearcustomer" onchange="this.form.submit()">
                            <option value="2025" ${param.yearcustomer == '2025' ? 'selected' : ''}>2025</option>
                            <option value="2024" ${param.yearcustomer == '2024' ? 'selected' : ''}>2024</option>
                            <option value="2023" ${param.yearcustomer == '2023' ? 'selected' : ''}>2023</option>
                            <option value="2022" ${param.yearcustomer == '2022' ? 'selected' : ''}>2022</option>
                            <option value="2021" ${param.yearcustomer == '2021' ? 'selected' : ''}>2021</option>
                        </select>


                        <canvas id="lineChart"></canvas>
                        <h2 style="text-align: center;">Total Status of Bill</h2>
                        <canvas style="width: 400px;" id="pieChart"></canvas></div>

                </div>
                <script>
                    // Lấy giá trị từ request attribute sử dụng EL
                    const total1 = ${total1 != null ? total1 : 0};
                    const total2 = ${total2 != null ? total2 : 0};
                    const total3 = ${total3 != null ? total3 : 0};
                    const total4 = ${total4 != null ? total4 : 0};
                    const total5 = ${total5 != null ? total5 : 0};
                    const total6 = ${total6 != null ? total6 : 0};
                    const total7 = ${total7 != null ? total7 : 0};
                    const total8 = ${total8 != null ? total8 : 0};
                    const total9 = ${total9 != null ? total9 : 0};
                    const total10 = ${total10 != null ? total10 : 0};
                    const total11 = ${total11 != null ? total11 : 0};
                    const total12 = ${total12 != null ? total12 : 0};

                    // Mảng dữ liệu cho biểu đồ cột
                    const data = [total1, total2, total3, total4, total5, total6, total7, total8, total9, total10, total11, total12];

                    // Biểu đồ cột (Bar Chart)
                    const barChart = new Chart(document.getElementById('barChart'), {
                        type: 'bar',
                        data: {
                            labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'June', 'July', 'August', 'Sep', 'Oct', 'Nov', 'Dec'],
                            datasets: [{
                                    label: 'Revenue',
                                    data: data,
                                    backgroundColor: 'rgba(54, 162, 235, 0.6)'
                                }]
                        }
                    });
                    const totalcustomer1 = ${totalcustomer1};
                    const totalcustomer2 = ${totalcustomer2};
                    const totalcustomer3 = ${totalcustomer3};
                    const totalcustomer4 = ${totalcustomer4};
                    const totalcustomer5 = ${totalcustomer5};
                    const totalcustomer6 = ${totalcustomer6};
                    const totalcustomer7 = ${totalcustomer7};
                    const totalcustomer8 = ${totalcustomer8};
                    const totalcustomer9 = ${totalcustomer9};
                    const totalcustomer10 = ${totalcustomer10};
                    const totalcustomer11 = ${totalcustomer11};
                    const totalcustomer12 = ${totalcustomer12};
                    const totalCustomers = [totalcustomer1, totalcustomer2, totalcustomer3, totalcustomer4, totalcustomer5, totalcustomer6, totalcustomer7, totalcustomer8, totalcustomer9, totalcustomer10, totalcustomer11, totalcustomer12];
                    // Biểu đồ đường (Line Chart)
                    const lineChart = new Chart(document.getElementById('lineChart'), {
                        type: 'line',
                        data: {
                            labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'June', 'July', 'August', 'Sep', 'Oct', 'Nov', 'Dec'],
                            datasets: [{
                                    label: 'Customer',
                                    data: totalCustomers,
                                    borderColor: 'rgba(255, 99, 132, 1)',
                                    fill: false
                                }]
                        }
                    });
                    // Lấy giá trị đã được set trong Servlet vào JSP
                    const paidRate = ${Paidrate}; // Tỷ lệ đã trả
                    const unpaidRate = ${UnPaidrate}; // Tỷ lệ chưa trả
                    // Biểu đồ hình tròn (Pie Chart)
                    const pieChart = new Chart(document.getElementById('pieChart'), {
                        type: 'pie',
                        data: {
                            labels: ['Paid', 'Unpaid'],
                            datasets: [{
                                    data: [paidRate, unpaidRate],
                                    backgroundColor: ['blue', 'green']
                                }]
                        }
                    });
                </script>
        </form>
        <!-- Bootstrap JS and dependencies -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
