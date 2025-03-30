<%-- 
    Document   : login
    Created on : Jan 13, 2025, 3:18:30 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.google.gson.Gson" %>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html lang="en" data-bs-theme="light">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Manage</title>

        <!--plugins-->
        <link href="assets/plugins/perfect-scrollbar/css/perfect-scrollbar.css" rel="stylesheet">
        <link href="assets/plugins/metismenu/css/metisMenu.min.css" rel="stylesheet">
        <link href="assets/plugins/simplebar/css/simplebar.css" rel="stylesheet">
        <!-- loader-->
        <link href="assets/css/pace.min.css" rel="stylesheet">
        <script src="assets/js/pace.min.js"></script>
        <!--Styles-->
        <link href="assets/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
        <link rel="stylesheet" href="assets/css/icons.css">

        <link href="https://fonts.googleapis.com/css2?family=Noto+Sans:wght@300;400;500;600&display=swap" rel="stylesheet">
        <link href="assets/css/extra-icons.css" rel="stylesheet">
        <link href="assets/css/main.css" rel="stylesheet">
        <link href="assets/css/dark-theme.css" rel="stylesheet">
        <link href="assets/css/semi-dark-theme.css" rel="stylesheet">
        <link href="assets/css/minimal-theme.css" rel="stylesheet">
        <link href="assets/css/shadow-theme.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <style>

            :root {
                --primary-green: #006837;    /* Xanh lá đậm (tông màu chuyên nghiệp) */
                --secondary-green: #4CAF50;  /* Xanh lá sáng cho tương tác */
                --accent-gold: #FFD700;      /* Vàng kim loại nhấn */
                --text-dark: #2C3E50;        /* Chữ chính - Xám đậm */
                --background-light: #F0FAF0; /* Nền xanh lá nhạt */
                --border-green: #C8E6C9;     /* Viền xanh nhạt */
            }

            thead.table-light th {
                color: #2C3E50;
                align-items: center;
                justify-content: center;
                text-shadow: 0 2px 4px rgba(0, 60, 30, 0.4);
                position: relative;
            }



            /* ========== CUSTOMER ROWS ========== */
            tr.customer-row {
                background-color: white;
                border-left: 5px solid var(--primary-green);
                transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
                width: 100%;
                max-width: 1200px; /* Hoặc giá trị cụ thể như 1200px */
                table-layout: fixed;
                box-sizing: border-box;
            }

            tr.customer-row:hover {
                transform: translateX(10px);
                box-shadow: 0 4px 15px rgba(0, 104, 55, 0.1);
                background-color: #F8FFF8;
                display: table-row; /* Khôi phục lại display mặc định */
                width: auto;
            }

            tr.customer-row td {
                padding: 1.5rem 2.5rem;  /* Tăng 35% */
                font-size: 1.0rem;  /* ~20.7px */
                line-height: 1.9;
                color: var(--text-dark);
                border-bottom: 1px solid var(--border-green);
            }

            /* ========== ASSET LIST ========== */
            .asset-list {
                background-color: var(--background-light);
                border-width: 4px;
                border: 2px solid var(--border-green);
                width: 1150px;
                max-width: none; /* Xóa giới hạn max-width */
                margin: 2rem 0; /* Điều chỉnh margin */
                border-radius: 0; /* Xóa bo góc nếu cần */
                box-shadow: inset 0 2px 4px rgba(0, 104, 55, 0.05);
            }

            .asset-list th {
                padding: 1.5rem 2rem;
                font-size: 1.0rem;
                background-color: #E8F5E9;
                color: var(--primary-green);
            }

            /* ========== INTERACTIVE ELEMENTS ========== */
            .detail-icon {
                font-size: 1.3rem;
                cursor: pointer;
                pointer-events: auto;
                margin: 0 1rem;
                color: var(--primary-green);
                transition: all 0.3s ease;
            }

            .detail-icon:hover {
                color: var(--secondary-green);
                transform: scale(1.2);
            }

            button[type="submit"] {
                background: var(--secondary-green);
                color: white;
                padding: 10px 10px;
                padding: 0.6rem 1.7rem;
                font-size: 0.7rem;
                box-shadow: 0 4px 6px rgba(76, 175, 80, 0.2);
                transition: all 0.3s ease;
            }

            button[type="submit"]:hover {
                background: #45A049;
                transform: translateY(-2px);
                box-shadow: 0 6px 8px rgba(76, 175, 80, 0.3);
            }

            .table td, .table th {
                vertical-align: middle;
                padding: 0.75rem;
            }


            .text-truncate {
                max-width: 150px;
                white-space: nowrap;
                overflow: hidden;
                text-overflow: ellipsis;
            }

            .form-control-sm {
                min-height: 30px;
                padding: 0.25rem 0.5rem;
            }

            .badge {
                font-weight: 500;
                padding: 0.5em 0.75em;
            }
            .modal {
                display: none; /* Ẩn modal mặc định */
                position: fixed; /* Cố định vị trí */
                z-index: 1000; /* Đảm bảo modal hiển thị trên tất cả các phần tử khác */
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                overflow: auto;
                background-color: rgba(0, 0, 0, 0.5); /* Màu nền mờ */
            }

            .modal-content {
                background-color: #fff;
                margin: 10% auto;
                padding: 20px;
                border: 1px solid #888;
                border-radius: 8px;
                width: 80%;
                max-width: 1200px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
                position: relative; /* Để nút đóng có thể định vị đúng */
            }

            .close {
                color: #aaa;
                position: absolute; /* Định vị nút đóng */
                top: 10px;
                right: 10px;
                font-size: 28px;
                font-weight: bold;
                cursor: pointer;
                transition: color 0.3s ease;
            }

            .close:hover,
            .close:focus {
                color: #000;
                text-decoration: none;
            }
            .modal-body {
                display: flex;
                align-items: flex-start;
                gap: 20px;
            }

            .modal-image {
                width: 600px;
                height: 450px;
                object-fit: cover;
                border-radius: 8px;
            }
            .modal-info {
                flex: 1;
            }

            .modal-info p {
                margin: 10px 0;
            }
            #modalDescription,
            #modalValue,
            #modalDate {
                font-size: 1em;
                margin: 10px 0;
            }
            .pdf-link {
                color: #e74c3c;
                text-decoration: none;
            }

            .pdf-link:hover {
                text-decoration: underline;
            }
            li {
                margin: 5px 0; /* Khoảng cách giữa các mục */
            }
        </style>
        <script>
            function submitSortForm(order) {
                document.getElementById('sortOrder').value = order; // Gán giá trị cho trường ẩn
                document.getElementById('sortForm').submit(); // Gửi form
            }

        </script> 
        <script>
            function submitSortForm1(order) {
                document.getElementById('sortDate').value = order; // Gán giá trị cho trường ẩn
                document.getElementById('sortForm1').submit(); // Gửi form
            }

        </script> 
        <script>
            function submitSortForm2(order) {
                document.getElementById('status').value = order; // Gán giá trị cho trường ẩn
                document.getElementById('sortForm2').submit(); // Gửi form
            }

        </script> 

        <script>
            function submitSortForm3(order) {
                document.getElementById('verify').value = order; // Gán giá trị cho trường ẩn
                document.getElementById('sortForm3').submit(); // Gửi form
            }

        </script> 
        <script>
            function submitSearch() {
                var searchInput = document.getElementById('searchInput').value;
                if (searchInput.trim() !== "") {
                    document.getElementById('searchForm').submit();
                } else {
                    alert("Please enter a search term."); // Thông báo nếu ô tìm kiếm rỗng
                }
            }
        </script>


        <script>
            window.showAssetDetails = function (image, description, value, date, listpdf) {
                const modal = document.getElementById("assetDetailModal");
                if (!modal) {
                    console.error("Không tìm thấy modal!");
                    return;
                }

                // Hiển thị thông tin cơ bản
                document.getElementById("modalImage").src = image || 'placeholder.jpg';
                document.getElementById("modalDescription").innerHTML = description || 'Không có mô tả';
                document.getElementById("modalValue").textContent = value || 'N/A';
                document.getElementById("modalDate").textContent = date || 'N/A';

                const pdfListContainer = document.getElementById("pdfList");
                if (!pdfListContainer) {
                    console.error("Không tìm thấy pdfList!");
                    return;
                }

                // Kiểm tra nếu listpdf không rỗng
                if (typeof listpdf === 'string' && listpdf.trim() === "") {
                    console.warn('listpdf là chuỗi rỗng');
                    pdfListContainer.innerHTML = "<li>Không có file PDF</li>";
                    modal.style.display = "block";
                    return;
                }

                // Phân tích chuỗi JSON thành mảng
                let pdfArray;
                try {
                    pdfArray = JSON.parse(listpdf);
                } catch (error) {
                    console.error('Lỗi phân tích JSON:', error);
                    pdfListContainer.innerHTML = "<li>Không có file PDF</li>";
                    modal.style.display = "block";
                    return;
                }

                // Kiểm tra xem pdfArray có phải là mảng không
                if (Array.isArray(pdfArray)) {
                    pdfListContainer.innerHTML = "";
                    pdfArray.forEach(pdf => {
                        if (pdf.PdfName) {
                            const listItem = document.createElement("li");
                            let encodedFileName = encodeURIComponent(pdf.PdfName);

                            console.log("encodedFileName" + encodedFileName);
                            // Tạo thẻ <a> bằng createElement
                            const link = document.createElement("a");
                            link.href = `viewPdf?fileName=` + encodedFileName;
                            link.target = "_blank";
                            link.className = "pdf-link";
                            console.log("[Debug] link.href:", link.href);
                            // Tạo icon và text
                            const icon = document.createElement("i");
                            icon.className = "fas fa-file-pdf";
                            const span = document.createElement("span");
                            span.textContent = pdf.PdfName;

                            // Ghép các phần tử
                            link.appendChild(icon);
                            link.appendChild(span);
                            listItem.appendChild(link);
                            pdfListContainer.appendChild(listItem);
                        }
                    });
                } else {
                    console.warn('listpdf is not an array:', pdfArray);
                    pdfListContainer.innerHTML = "<li>Không có file PDF</li>";
                }

                modal.style.display = "block";
            };
            function closeAssetModal() {
                document.getElementById("assetDetailModal").style.display = "none";
            }
// ✅ Đăng ký sự kiện click SAU KHI hàm đã được định nghĩa
            document.addEventListener("click", function (event) {
                console.log("Clicked element:", event.target); // Log phần tử được click
                if (event.target.classList.contains("detail-icon-ass")) {
                    console.log("Detail icon clicked!"); // Log khi click vào icon
                    const icon = event.target;
                    const image = icon.dataset.image;
                    const description = icon.dataset.description;
                    const value = icon.dataset.value;
                    const createdAt = icon.dataset.createdAt;
                    const listPdf = icon.dataset.listPdf;

                    // Gọi hàm đã được định nghĩa
                    showAssetDetails(image, description, value, createdAt, listPdf);
                }
            });
            document.addEventListener("DOMContentLoaded", function () {
                const commentElements = document.querySelectorAll(".comment-text");
                commentElements.forEach((element) => {
                    const fullComment = element.getAttribute("data-full-comment");
                    if (fullComment.length > 50) {
                        element.textContent = fullComment.substring(0, 50) + "...";
                    }
                });
            });
        </script>
        <script>
            function showFullComment(comment) {
                var formattedComment = comment.replace(/\n/g, '<br>');
                document.getElementById('fullCommentContent').innerHTML = formattedComment;
                document.getElementById('commentDetailModal').style.display = 'block';
            }

            function closeCommentModal() {
                document.getElementById("commentDetailModal").style.display = "none";
            }

        </script>
        <script type="text/javascript">

            function formatNumber(input) {
                // Loại bỏ tất cả các ký tự không phải số
                let value = input.value.replace(/[^0-9]/g, '');
                // Thêm dấu phẩy sau mỗi 3 chữ số
                value = value.replace(/\B(?=(\d{3})+(?!\d))/g, '.');
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
        <script>
            function toggleAssets(icon) {
                const assetList = icon.closest('tr').nextElementSibling.querySelector('.asset-list');
                if (assetList.style.display === "none" || assetList.style.display === "") {
                    assetList.style.display = "block"; // Hiển thị danh sách
                } else {
                    assetList.style.display = "none"; // Ẩn danh sách
                }
            }
        </script>
    </head>
    <body>

        <!--start header-->
        <header class="top-header">
            <nav class="navbar navbar-expand justify-content-between">
                <div class="btn-toggle-menu">
                    <span class="material-symbols-outlined">menu</span>
                </div>
            </nav>
        </header>
        <!--end header-->


        <!--start sidebar-->
        <aside class="sidebar-wrapper">
            <div class="sidebar-header">
                <div class="logo-icon">
                    <img src="assets/images/logo-icon.png" class="logo-img" alt="">
                </div>
                <div class="logo-name flex-grow-1">
                    <h5 class="mb-0">Roksyn</h5>
                </div>
                <div class="sidebar-close ">
                    <span class="material-symbols-outlined">close</span>
                </div>
            </div>
            <div class="sidebar-nav" data-simplebar="true">

                <ul class="metismenu" id="menu">
                    <li>
                        <a href="home.jsp">
                            <div class="parent-icon"><span class="material-symbols-outlined">home</span>
                            </div>
                            <div class="menu-title">Dashboard</div>
                        </a>
                    </li>



                    <li>
                        <a href="listAsset" class="has-arrow">
                            <div class="parent-icon"><span class="material-symbols-outlined">shopping_cart</span>
                            </div>
                            <div class="menu-title">Management</div>
                        </a>
                        <ul>
                    </li>
                    <li> <a href="listAsset"><span class="material-symbols-outlined">arrow_right</span>Asset</a>
                    </li>
                    <li> <a href="listSalary"><span class="material-symbols-outlined">arrow_right</span>Salary</a>
                    </li>
                    <li> <a href="contract-management-for-manager"><span class="material-symbols-outlined">arrow_right</span>Contract</a>
                    </li>

                </ul>
                </li> 
                </ul>
                <!--end navigation-->


            </div>
            <div class="btn btn-sm btn-primary ms-3">
                <a href="/timibank/logout"><span style="color: white">Sign Out</span></a>
            </div>
        </aside>
        <!--end sidebar-->


        <!--start main content-->
        <main class="page-content">

            <div class="row g-3">
                <div class="col-auto">

                    <form id="searchForm" action="sortSala" method="get">   
                        <div class="position-relative">
                            <input id="searchInput" class="form-control px-5" type="search" name="search" placeholder="Search Products">
                            <span class="material-symbols-outlined position-absolute ms-3 translate-middle-y start-0 top-50 fs-5" 
                                  onclick="submitSearch();">
                                search
                            </span>
                        </div>
                    </form>

                </div>
                <div class="col-auto flex-grow-1 overflow-auto">
                    <div class="btn-group position-static">
                        <div class="btn-group position-static">
                            <a href="listSalary">
                                <button type="button" class="btn border btn-light px-4" >
                                    All
                                </button>
                            </a>

                        </div>
                        <div class="btn-group position-static">
                            <button type="button" class="btn border btn-light dropdown-toggle px-4" data-bs-toggle="dropdown" aria-expanded="false">
                                Amount of Salary
                            </button>
                            <form action="sortSala" method="get" id="sortForm">
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" onclick="submitSortForm('asc')">Ascending</a></li>
                                    <li><a class="dropdown-item" onclick="submitSortForm('desc')">Decreasing</a></li>
                                </ul>
                                <input type="hidden" name="sortOrder" id="sortOrder">
                            </form>

                        </div>
                        <div class="btn-group position-static">
                            <button type="button" class="btn border btn-light dropdown-toggle px-4" data-bs-toggle="dropdown" aria-expanded="false">
                                Date
                            </button>

                            <form action="sortSala" method="get" id="sortForm1">
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item"  onclick="submitSortForm1('asc')">Latest</a></li>
                                    <li><a class="dropdown-item"  onclick="submitSortForm1('desc')">Early</a></li>
                                </ul>
                                <input type="hidden" name="sortDate" id="sortDate">
                            </form>

                        </div>
                        <div class="btn-group position-static">
                            <button type="button" class="btn border btn-light dropdown-toggle px-4" data-bs-toggle="dropdown" aria-expanded="false">
                                Salary Used For a Service
                            </button>
                            <form action="sortSala" method="get" id="sortForm3">
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" onclick="submitSortForm3('true')">Used</a></li>
                                    <li><a class="dropdown-item" onclick="submitSortForm3('false')">non-Use</a></li>
                                </ul>
                                <input type="hidden" name="used" id="verify">
                            </form>
                        </div>
                        <div class="btn-group position-static">
                            <button type="button" class="btn border btn-light dropdown-toggle px-4" data-bs-toggle="dropdown" aria-expanded="false">
                                Status
                            </button>
                            <form action="sortSala" method="get" id="sortForm2">
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item"  onclick="submitSortForm2('Pending')">Pending</a></li>
                                    <li><a class="dropdown-item"  onclick="submitSortForm2('Adjusting')">Adjusting</a></li>
                                    <li><a class="dropdown-item"  onclick="submitSortForm2('Adjusted')">Adjusted</a></li>
                                    <li><a class="dropdown-item"  onclick="submitSortForm2('Approved')">Approved</a></li>
                                </ul>
                                <input type="hidden" name="status" id="status">
                            </form>
                        </div>
                    </div>  
                </div>


                <div class="card mt-4">
                    <div class="card-body">
                        <div class="product-table">
                            <div class="table-responsive white-space-nowrap">
                                <table class="table align-middle" style="table-layout: fixed;">
                                    <thead class="table-light">
                                        <tr>
                                            <th style="width: 30%;padding-left: 3%">Customer Name</th>
                                            <th style="width: 30%;padding-left: 3%">Amount of Salaries</th>
                                            <th style="width: 30%;padding-left: 3%">List Salaries</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:if test="${requestScope.customerSalarysMap!= null}">
                                            <c:forEach var="entry" items="${requestScope.customerSalarysMap.entrySet()}">
                                                <c:set var="customer" value="${entry.key}" />
                                                <c:set var="salarys" value="${entry.value}" />

                                                <!-- Customer Row -->
                                                <tr class="customer-row">
                                                    <td>
                                                        <a href="customer?cid=${customer.getCustomerId()}" class="d-flex align-items-center text-decoration-none">
                                                            <i class="bi bi-person-circle me-2"></i>
                                                            <span>${customer.getFullName()}</span>
                                                        </a>
                                                    </td>
                                                    <td>
                                                        <div class="d-flex align-items-center">
                                                            <span>Total Salaries (${salarys.size()})</span>
                                                        </div>
                                                    </td>

                                                    <td>
                                                        <button type="button" onclick="toggleAssets(this)">View Detail</button>
                                                    </td>


                                                </tr>

                                                <!-- Asset List -->
                                                <tr class="asset-list-row">
                                                    <td colspan="2">
                                                        <div class="asset-list" style="display: none;">
                                                            <table class="table table-sm table-hover">
                                                                <thead>
                                                                    <tr class="bg-light">
                                                                        <th style="width: 20%">Salary Name</th>
                                                                        <th style="width: 25%">Comments</th>
                                                                        <th style="width: 15%">Valuation</th>
                                                                        <th style="width: 10%">Used for a service</th>
                                                                        <th style="width: 10%">Status</th>
                                                                        <th style="width: 12%">Action</th>
                                                                        <th style="width: 8%">Confirm</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <c:forEach items="${salarys}" var="salary">
                                                                        <tr>

                                                                            <!-- Asset Name -->
                                                                            <td class="align-middle">
                                                                                <div class="d-flex align-items-center">
                                                                                    <i class="fas fa-info-circle detail-icon detail-icon-ass me-2" 
                                                                                       data-image="${pageContext.request.contextPath}/${salary.getImage()}"
                                                                                       data-description="${salary.getDescription()}"
                                                                                       data-value="${salary.getValue()}"
                                                                                       data-created-at="${salary.getCreatedAt()}"
                                                                                       data-list-pdf='<c:out value="${salary.getListpdfJs()}" escapeXml="true"/>'
                                                                                       title="Xem chi tiết"
                                                                                       style="pointer-events: auto; z-index: 1;">
                                                                                    </i>
                                                                                    <span>${salary.getTitle()}</span>
                                                                                </div>

                                                                            </td>
                                                                    <form action="listSalary" method="post">
                                                                        <!-- Comments -->
                                                                        <td class="align-middle">
                                                                            <c:choose>
                                                                                <c:when test="${empty salary.getComments()}">
                                                                                    <textarea class="form-control form-control-sm" 
                                                                                              name="comment_${salary.getId()}"
                                                                                              placeholder="Add comment..."
                                                                                              required></textarea>
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    <div class="d-flex align-items-center">
                                                                                        <span class="text-truncate me-2" style="max-width: 150px;">
                                                                                            ${salary.getComments()}
                                                                                        </span>
                                                                                        <i class="fas fa-info-circle detail-icon" 
                                                                                           onclick="showFullComment('${salary.getComments()}')"
                                                                                           title="Full comment"></i>
                                                                                    </div>
                                                                                </c:otherwise>
                                                                            </c:choose>
                                                                        </td>

                                                                        <!-- Valuation -->
                                                                        <td class="align-middle">
                                                                            <c:choose>
                                                                                <c:when test="${empty salary.getValuationAmount()}">
                                                                                    <input type="number" 
                                                                                           class="form-control form-control-sm" 
                                                                                           name="valuationAmount_${salary.getId()}"
                                                                                           placeholder="Enter amount">
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    <fmt:formatNumber value="${salary.getValuationAmount()}" 
                                                                                                      pattern="###,###"/>
                                                                                </c:otherwise>
                                                                            </c:choose>
                                                                        </td>

                                                                        <!-- Used -->
                                                                        <td class="align-middle">
                                                                            ${salary.isUsed() ? 'Used' : 'Non-Use'}
                                                                        </td>

                                                                        <!-- Status -->
                                                                        <td class="align-middle">
                                                                            <span class="badge ${salary.getStatus() == 'Approved' ? 'bg-success' : 'bg-warning'}">
                                                                                ${salary.getStatus()}
                                                                            </span>
                                                                        </td>

                                                                        <!-- Action -->
                                                                        <td class="align-middle">
                                                                            <select class="form-select form-select-sm" 
                                                                                    name="action" 
                                                                                    ${salary.getStatus() == 'Approved' ? 'disabled' : ''}required>
                                                                                <option value="">Select action</option>
                                                                                <option value="Adjusting" ${salary.getStatus() == 'Adjusting' ? 'selected' : ''}>
                                                                                    Adjusting
                                                                                </option>
                                                                                <option value="Approved" ${salary.getStatus() == 'Approved' ? 'selected' : ''}>
                                                                                    Approved
                                                                                </option>
                                                                            </select>
                                                                        </td>

                                                                        <!-- Confirm -->
                                                                        <td class="align-middle">
                                                                            <input type="hidden" name="salaryid" value="${salary.getId()}">
                                                                            <button type="submit" 
                                                                                    class="btn btn-sm ${salary.getStatus() != 'Approved' ? 'btn-primary' : 'd-none'}">
                                                                                Confirm
                                                                            </button>
                                                                        </td>
                                                                    </form>
                                                                    </tr>
                                                                </c:forEach>
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </c:if>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="assetDetailModal" class="modal">
                    <div class="modal-content">
                        <span class="close" onclick="closeAssetModal()">&times;</span> <!-- Nút đóng -->
                        <div class="modal-body">
                            <img id="modalImage" src="" alt="" class="modal-image">
                            <div class="modal-info">
                                <p><strong>Date of request:</strong> <span id="modalDate"></span></p>
                                <p><strong>Description:</strong></p>
                                <p><span id="modalDescription"></span></p>
                                <p><strong>Value:</strong> <span id="modalValue"></span></p>
                                <div class="pdf-section">
                                    <div style="color: var(--text-dark)">List of PDF:</div>
                                    <ul id="pdfList"></ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="commentDetailModal" class="modal">
                    <div class="modal-content">
                        <span class="close" onclick="closeCommentModal()">&times;</span>
                        <div class="modal-info">
                            <p><strong>Comment:</strong> <span id="fullCommentContent"></span></p>
                        </div>
                    </div>
                </div>
        </main>
        <!--end main content-->


        <!--start overlay-->
        <div class="overlay btn-toggle-menu"></div>
        <!--end overlay-->

        <!-- Search Modal -->
        <div class="modal" id="exampleModal" tabindex="-1">
            <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
                <div class="modal-content">
                    <div class="modal-header gap-2">
                        <div class="position-relative popup-search w-100">
                            <input class="form-control form-control-lg ps-5 border border-3 border-primary" type="search" placeholder="Search">
                            <span class="material-symbols-outlined position-absolute ms-3 translate-middle-y start-0 top-50">search</span>
                        </div>
                        <button type="button" class="btn-close d-xl-none" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="search-list">
                            <p class="mb-1">Html Templates</p>
                            <div class="list-group">
                                <a href="javascript:;" class="list-group-item list-group-item-action active align-items-center d-flex gap-2"><i class="bi bi-filetype-html fs-5"></i>Best Html Templates</a>
                                <a href="javascript:;" class="list-group-item list-group-item-action align-items-center d-flex gap-2"><i class="bi bi-award fs-5"></i>Html5 Templates</a>
                                <a href="javascript:;" class="list-group-item list-group-item-action align-items-center d-flex gap-2"><i class="bi bi-box2-heart fs-5"></i>Responsive Html5 Templates</a>
                                <a href="javascript:;" class="list-group-item list-group-item-action align-items-center d-flex gap-2"><i class="bi bi-camera-video fs-5"></i>eCommerce Html Templates</a>
                            </div>
                            <p class="mb-1 mt-3">Web Designe Company</p>
                            <div class="list-group">
                                <a href="javascript:;" class="list-group-item list-group-item-action align-items-center d-flex gap-2"><i class="bi bi-chat-right-text fs-5"></i>Best Html Templates</a>
                                <a href="javascript:;" class="list-group-item list-group-item-action align-items-center d-flex gap-2"><i class="bi bi-cloud-arrow-down fs-5"></i>Html5 Templates</a>
                                <a href="javascript:;" class="list-group-item list-group-item-action align-items-center d-flex gap-2"><i class="bi bi-columns-gap fs-5"></i>Responsive Html5 Templates</a>
                                <a href="javascript:;" class="list-group-item list-group-item-action align-items-center d-flex gap-2"><i class="bi bi-collection-play fs-5"></i>eCommerce Html Templates</a>
                            </div>
                            <p class="mb-1 mt-3">Software Development</p>
                            <div class="list-group">
                                <a href="javascript:;" class="list-group-item list-group-item-action align-items-center d-flex gap-2"><i class="bi bi-cup-hot fs-5"></i>Best Html Templates</a>
                                <a href="javascript:;" class="list-group-item list-group-item-action align-items-center d-flex gap-2"><i class="bi bi-droplet fs-5"></i>Html5 Templates</a>
                                <a href="javascript:;" class="list-group-item list-group-item-action align-items-center d-flex gap-2"><i class="bi bi-exclamation-triangle fs-5"></i>Responsive Html5 Templates</a>
                                <a href="javascript:;" class="list-group-item list-group-item-action align-items-center d-flex gap-2"><i class="bi bi-eye fs-5"></i>eCommerce Html Templates</a>
                            </div>
                            <p class="mb-1 mt-3">Online Shoping Portals</p>
                            <div class="list-group">
                                <a href="javascript:;" class="list-group-item list-group-item-action align-items-center d-flex gap-2"><i class="bi bi-facebook fs-5"></i>Best Html Templates</a>
                                <a href="javascript:;" class="list-group-item list-group-item-action align-items-center d-flex gap-2"><i class="bi bi-flower2 fs-5"></i>Html5 Templates</a>
                                <a href="javascript:;" class="list-group-item list-group-item-action align-items-center d-flex gap-2"><i class="bi bi-geo-alt fs-5"></i>Responsive Html5 Templates</a>
                                <a href="javascript:;" class="list-group-item list-group-item-action align-items-center d-flex gap-2"><i class="bi bi-github fs-5"></i>eCommerce Html Templates</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>



        <!--start theme customization-->
        <div class="offcanvas offcanvas-end" tabindex="-1" id="ThemeCustomizer" aria-labelledby="ThemeCustomizerLable">
            <div class="offcanvas-header border-bottom">
                <h5 class="offcanvas-title" id="ThemeCustomizerLable">Theme Customizer</h5>
                <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
            </div>
            <div class="offcanvas-body">
                <h6 class="mb-0">Theme Variation</h6>
                <hr>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="inlineRadioOptions" id="LightTheme" value="option1">
                    <label class="form-check-label" for="LightTheme">Light</label>
                </div>
                <!--                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="inlineRadioOptions" id="DarkTheme" value="option2" checked="">
                                    <label class="form-check-label" for="DarkTheme">Dark</label>
                                </div>-->
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="inlineRadioOptions" id="SemiDarkTheme" value="option3">
                    <label class="form-check-label" for="SemiDarkTheme">Semi Dark</label>
                </div>
                <hr>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="inlineRadioOptions" id="MinimalTheme" value="option3">
                    <label class="form-check-label" for="MinimalTheme">Minimal Theme</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="inlineRadioOptions" id="ShadowTheme" value="option4">
                    <label class="form-check-label" for="ShadowTheme">Shadow Theme</label>
                </div>

            </div>
        </div>
        <!--end theme customization-->
        <!--plugins-->
        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/plugins/perfect-scrollbar/js/perfect-scrollbar.js"></script>
        <script src="assets/plugins/metismenu/js/metisMenu.min.js"></script>
        <script src="assets/plugins/simplebar/js/simplebar.min.js"></script>

        <!--BS Scripts-->
        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/main.js"></script>
    </body>

</html>