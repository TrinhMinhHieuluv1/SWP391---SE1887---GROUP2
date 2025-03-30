<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.google.gson.Gson" %>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>List Assets - TIMI ROKSYN</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <style>
            /* Reset default styles */
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
                font-family: Arial, sans-serif;
            }

            /* Main layout */
            body {
                display: flex;
                min-height: 100vh;
                background-color: #F4F7FC;
            }

            /* Sidebar */
            .sidebar {
                width: 200px;
                background-color: #1A3C34;
                color: white;
                padding: 20px;
                height: 100vh;
                position: fixed;
                display: flex;
                flex-direction: column;
                justify-content: space-between; /* Đẩy nút Back to Home xuống dưới cùng */
            }

            .sidebar .logo {
                font-size: 20px;
                font-weight: bold;
                margin-bottom: 30px;
            }

            .sidebar ul {
                list-style: none;
                flex-grow: 1; /* Danh sách menu chiếm phần còn lại của không gian */
            }

            .sidebar ul li {
                margin-bottom: 20px;
            }

            .sidebar ul li a {
                color: white;
                text-decoration: none;
                font-size: 16px;
            }

            .sidebar ul li a:hover {
                color: #2196F3;
                font-weight: bold;
            }

            .sidebar .back-to-home {
                display: block;
                padding: 10px 20px;
                background-color: #2196F3;
                color: white;
                text-decoration: none;
                border-radius: 5px;
                text-align: center;
                font-size: 14px;
            }

            .sidebar .back-to-home:hover {
                background-color: #1976D2;
            }

            /* Main content */
            .main-content {
                margin-left: 200px;
                padding: 20px;
                width: calc(100% - 200px);
                background-color: #FFFFFF;
            }

            /* Top bar */
            .top-bar {
                display: flex;
                justify-content: space-between;
                align-items: center;
                padding: 10px 20px;
                background-color: #FFFFFF;
                border-bottom: 1px solid #E0E0E0;
            }

            .top-bar .logo {
                font-size: 20px;
                font-weight: bold;
            }

            .top-bar .search-bar {
                display: flex;
                align-items: center;
                gap: 10px;
            }

            .top-bar .search-bar input {
                padding: 5px;
                border: 1px solid #E0E0E0;
                border-radius: 5px;
                width: 200px;
            }

            .top-bar .icons {
                display: flex;
                gap: 10px;
            }

            .top-bar .icons i {
                font-size: 18px;
                color: #757575;
            }

            /* Filter Section */
            .filter-section {
                display: flex;
                gap: 15px;
                margin-bottom: 15px;
                align-items: center;
            }

            .filter-section label {
                font-size: 14px;
                color: #757575;
                margin-right: 5px;
            }

            .filter-section select,
            .filter-section input {
                padding: 5px;
                border: 1px solid #E0E0E0;
                border-radius: 5px;
                font-size: 14px;
            }

            /* Table Section */
            .table-section {
                margin-top: 20px;
            }

            .table-section h2 {
                font-size: 20px;
                color: #000000;
                margin-bottom: 10px;
            }

            .table-section .add-btn {
                display: inline-block;
                padding: 10px 20px;
                background-color: #4CAF50;
                color: white;
                text-decoration: none;
                border-radius: 5px;
                margin-bottom: 10px;
            }

            .table-section table {
                width: 100%;
                border-collapse: collapse;
                background-color: #FFFFFF;
            }

            .table-section th,
            .table-section td {
                padding: 10px;
                text-align: left;
                border-bottom: 1px solid #E0E0E0;
            }

            .table-section th {
                background-color: #F4F7FC;
                color: #757575;
                font-size: 14px;
            }

            .table-section tr:nth-child(even) {
                background-color: #F4F7FC;
            }

            .table-section .status {
                padding: 5px 5px;
                border-radius: 5px;
                font-size: 12px;
                color: white;
                width: 100%; /* Chiếm toàn bộ chiều rộng của ô */
                box-sizing: border-box; /* Để padding không làm tăng kích thước */
                display: flex; /* Sử dụng flexbox để căn giữa nội dung */
                align-items: center; /* Căn giữa theo chiều dọc */
                justify-content: center; /* Căn giữa theo chiều ngang */
                height: 35px; /* Chiều cao cố định (thay đổi theo nhu cầu) */
                white-space: normal; /* Cho phép xuống dòng */
                overflow: hidden; /* Ẩn nội dung vượt quá */
                text-overflow: ellipsis; /* Thêm dấu ba chấm nếu nội dung quá dài */
            }
            .table-section .status.NotProcessed{
                background-color: #808080;
            }
            .table-section .status.Pending,
            .table-section .status.Adjusting{
                background-color: #FFB300;
            }

            .table-section .status.Approved,
            .table-section .status.Adjusted {
                background-color: #4CAF50;
            }

            .table-section .action-buttons {
                display: flex;
                gap: 5px;
            }

            .table-section .view-btn,
            .table-section .edit-btn,
            .table-section .delete-btn,
            .table-section .confirm-btn{
                padding: 5px 10px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                font-size: 12px;
                box-sizing: border-box; /* Để padding không làm tăng kích thước */
                display: flex; /* Sử dụng flexbox để căn giữa nội dung */
                align-items: center; /* Căn giữa theo chiều dọc */
                justify-content: center; /* Căn giữa theo chiều ngang */
                height: 35px; /* Chiều cao cố định (thay đổi theo nhu cầu) */
                white-space: normal; /* Cho phép xuống dòng */
                overflow: hidden; /* Ẩn nội dung vượt quá */
                text-overflow: ellipsis; /* Thêm dấu ba chấm nếu nội dung quá dài */
            }

            .table-section .view-btn {
                background-color: #2196F3;
                color: white;
            }

            .table-section .edit-btn {
                background-color: #FFB300;
                color: white;
            }
            .table-section .delete-btn {
                background-color: #FF0000;
                color: white;
            }
            .table-section .confirm-btn {
                background-color: #4CAF50;
                color: white;
            }


            /* Modal (View Detail) */
            .modal {
                display: none;
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background-color: rgba(0, 0, 0, 0.5);
                justify-content: center;
                align-items: center;
            }

            .modal-content {
                background-color: #FFFFFF;
                padding: 20px;
                margin: 10% auto;
                border-radius: 10px;
                width: 1200px;
                display: flex;
                position: relative;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            }

            .modal-content .close-btn {
                position: absolute;
                top: 10px;
                right: 10px;
                font-size: 20px;
                cursor: pointer;
                color: #757575;
            }

            .modal-content .close-btn:hover {
                color: #F44336;
            }

            .modal-content .image-section {
                flex: 1;
                padding-right: 20px;
            }

            .modal-content .image-section img {
                width: 600px;
                height: 450px;
                object-fit: cover;
                border-radius: 5px;
            }

            .modal-content .details-section {
                flex: 1;
                padding-left: 20px;
            }

            .modal-content .details-section p {
                margin-bottom: 15px;
                font-size: 14px;
                color: #000000;
            }

            .modal-content .details-section p strong {
                font-weight: bold;
                display: block;
                margin-bottom: 5px;
            }

            .modal-content .details-section .pdf-list {
                margin-top: 10px;
            }


            /* Modal (Edit Asset) */
            .edit-modal-content {
                background-color: #FFFFFF;
                padding: 20px;
                border-radius: 5px;
                width: 750px;
                max-height: 90%;
                position: relative;
                overflow-y: auto;
            }

            .edit-modal-content h3 {
                font-size: 18px;
                color: #000000;
                margin-bottom: 20px;
            }

            .edit-modal-content .form-group {
                margin-bottom: 15px;
            }

            .edit-modal-content .form-group label {
                display: block;
                font-size: 14px;
                color: #757575;
                margin-bottom: 5px;
            }

            .edit-modal-content .form-group input,
            .edit-modal-content .form-group textarea,
            .edit-modal-content .form-group select {
                width: 100%;
                padding: 8px;
                border: 1px solid #E0E0E0;
                border-radius: 5px;
                font-size: 14px;
            }

            .edit-modal-content .buttons {
                display: flex;
                justify-content: flex-end;
                gap: 10px;
                margin-top: 20px;
            }

            .edit-modal-content .buttons button {
                padding: 10px 20px;
                border-radius: 5px;
                font-size: 14px;
                cursor: pointer;
            }

            .edit-modal-content .buttons .cancel-btn {
                background-color: #E0E0E0;
                color: #000000;
                border: none;
            }

            .edit-modal-content .buttons .save-btn {
                background-color: #4CAF50;
                color: white;
                border: none;
            }
            .pdf-link {
                color: #e74c3c;
                text-decoration: none;
            }

            .pdf-link:hover {
                color: red;
                text-decoration: underline;
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
            .delete-button {
                background-color: #ff4d4d; /* Màu đỏ nhạt */
                color: white; /* Chữ trắng để nổi bật */
                border: none; /* Loại bỏ viền mặc định */
                border-radius: 4px; /* Bo góc nhẹ */
                padding: 4px 8px; /* Kích thước đệm nhỏ gọn */
                margin-left: 10px; /* Khoảng cách với liên kết PDF */
                font-size: 12px; /* Cỡ chữ nhỏ */
                cursor: pointer; /* Con trỏ tay khi hover */
                transition: background-color 0.3s ease; /* Hiệu ứng chuyển màu mượt */
            }

            /* Hiệu ứng khi hover */
            .delete-button:hover {
                background-color: #cc0000; /* Đậm hơn khi hover */
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
            .empty-state {
                text-align: center;
                padding: 40px 20px;
                background-color: white;
                border-radius: 8px;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            }
        </style>
    </head>
    <body>
        <!-- Sidebar -->
        <div class="sidebar">
            <div>
                <div class="logo">My Asset and Salaries</div>
                <ul>
                    <li><a href="myassetsalary" >My Assets</a></li>
                    <li><a href="mysalary" >My Salaries</a></li>
                </ul>
            </div>
            <a href="home" class="back-to-home">Back to Home</a>
        </div>

        <!-- Main Content -->
        <div class="main-content">
            <!-- Top Bar -->
            <div class="top-bar">
                <div class="logo">TIMI ROKSYN</div>
                <div class="search-bar">
                </div>
            </div>

            <!-- Table Section -->
            <div class="table-section">
                <h2>Salary Submitted</h2>
                <!-- Filter Section -->
                <div >
                    <form class="filter-section" action="searchSalary">
                        <div>
                            <label for="nameFilter">Salary Name</label>
                            <input type="text" id="nameFilter" name="name"oninput="filterAssets()" placeholder="Enter name">
                        </div>
                        <div>
                            <label for="statusFilter">Status:</label>
                            <select id="statusFilter" onchange="filterAssets()" name="opStatus">
                                <option value="All">All</option>
                                <option value="Not Processed">Not Processed</option>
                                <option value="Pending">Pending</option>
                                <option value="Adjusting">Adjusting</option>
                                <option value="Adjusted">Adjusted</option>
                                <option value="Approved">Approved</option>
                            </select>
                        </div>
                        <div>
                            <label for="serviceFilter">Used for a Service:</label>
                            <select id="serviceFilter" onchange="filterAssets()" name="opUse">
                                <option value="All">All</option>
                                <option value="false">Non Use</option>
                                <option value="true">Used</option>
                            </select>
                        </div>
                        <div>
                            <label for="dateFilter">Date Created:</label>                       
                            <select id="dateFilter"  name="opDate">
                                <option value="asc">Latest</option>
                                <option value="desc">Early</option>
                            </select>

                        </div>
                        <input name="pageSize" value="${pageSize}" hidden/>
                        <button type="submit" class="add-btn">Search</button>
                    </form>
                    <div>
                        <label for="pageSizeSelect">Page Size:</label>    
                        <select id="pageSizeSelect" onchange="changePageSize()">
                            <c:forEach items="${requestScope.listSize}" var="size">
                                <option value="${size}" ${pageSize == size ? 'selected' : ''}>${size}</option>
                            </c:forEach>
                        </select>
                        <span><a href="addSalary.jsp" class="add-btn">Add New Salary</a></span>
                    </div>
                </div>

                <!-- Assets Table -->
                <table id="assetsTable">
                    <thead>
                        <tr>
                            <th>Salary name</th>
                            <th>Comments of banker</th>
                            <th>Valuation Amount</th>
                            <th>Used for a Service</th>
                            <th>Status</th>
                            <th>Date Created</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody id="assetsTableBody">
                        <c:if test="${empty requestScope.data}">
                            <tr>
                        <div class="empty-state">
                            <h2>You don't have any appraisal Salary yet?</h2>
                            <p>Start by creating your first Salary.</p>
                        </div>  
                        </tr>
                    </c:if>
                    <c:if test="${not empty requestScope.data}" >
                        <c:forEach items="${requestScope.data}" var="salary">
                            <tr data-id="${salary.getId()}" data-names="${salary.getTitle()}" data-status="${salary.getStatus()}" data-service="${salary.isUsed()}" data-date="${salary.getCreatedAt()}">
                                <td>${salary.getTitle()}</td>
                                <td>
                                    <c:if test="${empty salary.getComments()}">
                                        Non comment
                                    </c:if>
                                    <c:if  test="${salary.getComments()!= null}" >
                                        <div class="d-flex align-items-center">
                                            <span 
                                                style="max-width: 150px;display: inline-block;overflow: hidden;vertical-align: middle;line-height: normal;text-overflow: ellipsis">
                                                Have a Comment
                                            </span>
                                            <i class="fas fa-info-circle detail-icon" style="display: inline-block; vertical-align: middle;line-height: normal;cursor: pointer;"
                                               data-comment="${fn:escapeXml(salary.getComments())}"
                                               onclick="showFullCommentFromElement(this)"
                                               title="Full comment"></i>
                                        </div>
                                    </c:if>

                                </td>
                                <td>
                                    <c:if test="${empty salary.getValuationAmount()}">
                                        Non valuation amount
                                    </c:if>
                                    <fmt:formatNumber value="${salary.getValuationAmount()}" 
                                                      pattern="###,###"/>
                                </td>
                                <td>${salary.isUsed() ? 'Used' : 'Non-Use'}</td>
                                <c:if test="${salary.getStatus()=='Not Processed'}">
                                    <td><span class="status NotProcessed">${salary.getStatus()}</span></td>
                                    </c:if>
                                    <c:if test="${salary.getStatus()!='Not Processed'}">
                                    <td><span class="status ${salary.getStatus()}">${salary.getStatus()}</span></td>
                                    </c:if>
                                <td>${salary.getCreatedAt()}</td>
                                <td>
                                    <div class="action-buttons">
                                        <button class="view-btn detail-icon-ass" 
                                                data-image="${pageContext.request.contextPath}/${salary.getImage()}"
                                                data-description="${salary.getDescription()}"
                                                data-value="<fmt:formatNumber value="${salary.getValue()}" 
                                                                  pattern="###,###"/>"
                                                data-created-at="${salary.getCreatedAt()}"
                                                data-list-pdf='<c:out value="${salary.getListpdfJs()}" escapeXml="true"/>'
                                                title="Xem chi tiết"
                                                style="pointer-events: auto;">Detail</button>
                                        <c:if test="${salary.getStatus() =='Not Processed'||salary.getStatus() == 'Pending'||salary.getStatus() == 'Adjusting' }">
                                            <button class="edit-btn edit-icon-ass"
                                                    data-id="${salary.getId()}"
                                                    data-image="${pageContext.request.contextPath}/${salary.getImage()}"
                                                    data-name ="${salary.getTitle()}"
                                                    data-description="${salary.getDescription()}"
                                                    data-value="<fmt:formatNumber value="${salary.getValue()}" 
                                                                      pattern="###,###"/>"
                                                    data-list-pdf='<c:out value="${salary.getListpdfJs()}" escapeXml="true"/>'
                                                    style="pointer-events: auto;"
                                                    >
                                                Edit
                                            </button>  
                                        </c:if>


                                        <c:if test="${salary.getStatus()!='Approved'}">
                                            <form action="mysalary" method="post">
                                                <input name="ass" value="${salary.getId()}" hidden/>
                                                <button class="confirm-btn" type="submit">Send Req</button>
                                            </form>
                                        </c:if>
                                        <c:if test="${salary.getStatus()=='Not Processed'}">
                                            <form action="mysalary" method="Post">
                                                <input name="assdelet" value="${salary.getId()}" hidden/>
                                                <button class="delete-btn" type="submit">Delete</button>
                                            </form>
                                        </c:if>

                                    </div>
                                </td>
                            </tr>        
                        </c:forEach>
                    </c:if>
                    </tbody>
                </table>
            </div>
            <div class="pagination" style="margin-top: 30px;">
                <c:if test="${currentPage > 1}">
                    <a href="?page=${currentPage - 1}&pageSize=${pageSize}&name=${name}&opStatus=${opStatus}&opUse=${opUse}&opDate=${opDate}" class="prev">Previous</a>
                </c:if>

                <c:forEach var="i" begin="1" end="${totalPages}">
                    <a href="?page=${i}&pageSize=${pageSize}&name=${name}&opStatus=${opStatus}&opUse=${opUse}&opDate=${opDate}" class="${i == currentPage ? 'active' : ''}">${i}</a>
                </c:forEach>

                <c:if test="${currentPage < totalPages}">
                    <a href="?page=${currentPage + 1}&pageSize=${pageSize}&name=${name}&opStatus=${opStatus}&opUse=${opUse}&opDate=${opDate}"class="next">Next</a>
                </c:if>
            </div> 
        </div>
        <div id="commentDetailModal" class="modal">
            <div class="modal-content" style="width: 500px; max-height: 500px; overflow-y: auto;">

                <span class="close" onclick="closeCommentModal()" style="right: 10px;top: 10px;position: absolute;cursor: pointer;font-size: 24px;">&times;</span>
                <div class="modal-info">
                    <p><strong>Comment:</strong></p>
                    <p><span id="fullCommentContent"></span></p>
                </div>
            </div>
        </div>
        <!-- Modal (View Detail) -->
        <div id="assetDetailModal" class="modal">
            <div class="modal-content">
                <span class="close-btn" onclick="closeAssetModal()">×</span>
                <div class="image-section">
                    <img id="modalImage" src="" alt="" class="modal-image">
                </div>
                <div class="details-section">
                    <p>
                        <strong>Date of Request:</strong>
                        <span id="modalDate"></span>
                    </p>
                    <p>
                        <strong>Description:</strong>
                        <span id="modalDescription"></span>
                    </p>
                    <p>
                        <strong>Value</strong>
                        <span id="modalValue"></span>
                    </p>
                    <p>
                        <strong>List PDF:</strong>
                    <div class="pdf-list">
                        <a id="pdfList"></a>
                    </div>
                    </p>
                </div>
            </div>
        </div>


        <!-- Modal (Edit Asset) -->
        <div id="editModal" class="modal">
            <div class="edit-modal-content">
                <h3>Edit Asset</h3>
                <form id="uploadForm" enctype="multipart/form-data">
                    <input id="idAsset" name="id"  hidden/>
                    <div class="form-group">
                        <label for="editName">Asset Name</label>
                        <input type="text" id="editName" name="name" required>
                    </div>
                    <div class="form-group">
                        <label for="editValuation">Value</label>                       
                        <input type="text" 
                               class="form-control form-control-sm" 
                               id="editValuation"
                               oninput="formatNumber(this)" onkeypress="return validateInput(event)"
                               name="value"
                               value=""
                               placeholder="Enter amount">
                    </div>
                    <div class="form-group">
                        <label for="editDescription">Description</label>
                        <textarea id="editDescription" name="description" rows="6"></textarea>
                    </div>
                    <div class="form-group" style="display: grid;align-items: center;justify-content: center;">
                        <label for="editImage">Image</label>
                        <input id="file-image" type="file" name="fileImage" accept="image/jpeg, image/png" onchange="updateImagePreviewByFile()"><br>
                        <img id="editImage" src="" class="image-preview" style="width: 650px;height: 350px;"/>
                        <h6>${requestScope.errorImage}</h6>
                    </div>
                    <div class="form-group">
                        <label for="editPdf">List Pdf</label>
                        <div class="pdf-list">
                            <ul id="pdfList2" style="margin-left: 15px"></ul>
                        </div>
                        <input type="hidden" id="current_pdfs" name="current_pdfs" value="">
                        <input type="hidden" id="deleted_pdfs" name="deleted_pdfs" value="[]">
                        <input id="editPdf" name="file" type="file" accept=".pdf"/>
                        <h6>${requestScope.errorPdf}</h6>
                    </div>

                    <div class="buttons">
                        <button type="button" class="cancel-btn" onclick="closeEditAssetModal()">Cancel</button>
                        <button type="submit" class="save-btn">Save</button>
                    </div>
                </form>
            </div>
        </div>
        <script>
            function changePageSize() {
                var pageSize = document.getElementById("pageSizeSelect").value;
                window.location.href = "?pageSize=" + pageSize;
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
            function showFullComment(comment) {
                const modal = document.getElementById("commentDetailModal");
                const commentContent = document.getElementById("fullCommentContent");

                if (comment && comment.trim() !== "") {
                    // Thay thế ký tự xuống dòng bằng <br>
                    var formattedComment = comment.replace(/\n/g, '<br>');
                    // Hiển thị nội dung trong modal
                    commentContent.innerHTML = formattedComment;
                } else {
                    commentContent.innerHTML = "No comment available.";
                }

                modal.style.display = "block";
            }
            function showFullCommentFromElement(element) {
                // Lấy giá trị từ data-comment
                const comment = element.getAttribute('data-comment');
                showFullComment(comment);
            }
            function closeCommentModal() {
                document.getElementById("commentDetailModal").style.display = "none";
            }

        </script>
        <script>
            // Show Detail Modal     

            window.showAssetDetails = function (image, description, value, date, listpdf) {
                const modal = document.getElementById("assetDetailModal");
                if (!modal) {
                    console.error("Không tìm thấy modal!");
                    return;
                }

                // Hiển thị thông tin cơ bản
                document.getElementById("modalImage").src = image ? (image + "?t=" + new Date().getTime()) : 'placeholder.jpg';
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
            window.showEditAssetDetails = function (id, image, name, description, value, listpdf) {
                const modal = document.getElementById("editModal");
                if (!modal) {
                    console.error("Không tìm thấy modal!");
                    return;
                }

                // Hiển thị thông tin cơ bản

                document.getElementById("idAsset").value = id || 'N/A';
                document.getElementById("editImage").src = image ? (image + "?t=" + new Date().getTime()) : 'placeholder.jpg';
                document.getElementById("editDescription").innerHTML = description || 'Không có mô tả';
                document.getElementById("editValuation").value = value || '';
                document.getElementById("editName").value = name || '';

                const pdfListContainer = document.getElementById("pdfList2");
                if (!pdfListContainer) {
                    console.error("Không tìm thấy pdfList2!");
                    return;
                }

                // Kiểm tra nếu listpdf không rỗng
                if (typeof listpdf === 'string' && listpdf.trim() === "") {
                    console.warn('listpdf là chuỗi rỗng');
                    pdfListContainer.innerHTML = "<li>Không có file PDF</li>";
                    modal.style.display = "block";
                    return;
                }
                // Danh sách PdfId của các file bị xóa
                let deletedPdfs = [];

                // Khởi tạo giá trị ban đầu cho deleted_pdfs
                document.getElementById("deleted_pdfs").value = JSON.stringify(deletedPdfs);
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
                            const deleteButton = document.createElement("button");
                            deleteButton.textContent = "Delete";
                            deleteButton.className = "delete-button";
                            deleteButton.onclick = function () {
                                listItem.remove();

                                // Thêm PdfId vào danh sách deletedPdfs
                                const pdfIdToDelete = pdf.PdfID;
                                deletedPdfs.push(pdfIdToDelete);

                                // Cập nhật trường deleted_pdfs
                                document.getElementById("deleted_pdfs").value = JSON.stringify(deletedPdfs);

                                // Lọc pdfArray để loại bỏ file đã xóa (vẫn dựa trên PdfName để cập nhật current_pdfs)
                                pdfArray = pdfArray.filter(p => p.PdfId !== pdfIdToDelete);
                                document.getElementById("current_pdfs").value = JSON.stringify(pdfArray);
                            };
                            listItem.appendChild(deleteButton);
                            pdfListContainer.appendChild(listItem);
                        }
                    });
                } else {
                    console.warn('listpdf is not an array:', pdfArray);
                    pdfListContainer.innerHTML = "<li>Không có file PDF</li>";
                }

                modal.style.display = "flex";
            };
            function closeEditAssetModal() {
                document.getElementById("editModal").style.display = "none";
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
                if (event.target.classList.contains("edit-icon-ass")) {
                    console.log("Detail icon clicked!"); // Log khi click vào icon
                    const icon = event.target;
                    const image = icon.dataset.image;
                    const id = icon.dataset.id;
                    const description = icon.dataset.description;
                    const value = icon.dataset.value;
                    const name = icon.dataset.name;
                    const listPdf = icon.dataset.listPdf;

                    // Gọi hàm đã được định nghĩa
                    showEditAssetDetails(id, image, name, description, value, listPdf);
                }
            });
            document.getElementById("uploadForm").addEventListener("submit", function (event) {
                event.preventDefault(); // Ngăn hành động submit mặc định

                const form = event.target;
                const formData = new FormData(form);

                fetch('updateSalary', {
                    method: 'POST',
                    body: formData
                })
                        .then(response => response.json()) // Giả sử server trả về JSON
                        .then(data => {
                            if (data.success) {
                                // Đóng modal
                                closeEditAssetModal();

                                // Cập nhật giao diện chính
                                const assetId = document.getElementById("idAsset").value;
                                // Tìm hàng tương ứng trong bảng và cập nhật dataset
                                console.log(assetId);
                                const row = document.querySelector('tr[data-id="' + assetId + '"]');
                                if (row) {
                                    const name = document.getElementById("editName").value;
                                    const description = document.getElementById("editDescription").value;
                                    const value = document.getElementById("editValuation").value;
                                    const updatedImage = data.imagePath; // Giả sử server trả về đường dẫn ảnh mới
                                    const updatedListPdf = JSON.stringify(data.listPdf); // Giả sử server trả về danh sách PDF mới

                                    row.cells[0].textContent = data.title || "Default Title";//
                                    const viewButton = row.querySelector('.view-btn');
                                    viewButton.dataset.image = updatedImage + "?t=" + new Date().getTime();
                                    viewButton.dataset.description = data.description;
                                    viewButton.dataset.value = value;
                                    viewButton.dataset.listPdf = updatedListPdf;
                                    const editButton = row.querySelector('.edit-btn');
                                    if (editButton) {
                                        editButton.dataset.image = updatedImage + "?t=" + new Date().getTime();
                                        editButton.dataset.name = data.title;
                                        editButton.dataset.description = description;
                                        editButton.dataset.value = value;
                                        editButton.dataset.listPdf = updatedListPdf;
                                    }
                                    alert("Asset updating successfully! ");
                                } else {
                                    alert("Error updating asset: ");
                                }



                            } else {
                                alert("Error updating asset: " + data.message);
                            }
                        })
                        .catch(error => {
                            console.error("Error:", error);
                            alert("An error occurred while updating the asset.");
                        });
            });
            function updateImagePreviewByFile() {
                let formData = new FormData();
                let file_image = document.getElementById('file-image');

                if (file_image.files.length === 0) {
                    alert('Please choose a file to upload!');
                    return;
                }

                formData.append('file', file_image.files[0]);

                fetch('update-preview-image-by-file', {
                    method: 'POST',
                    body: formData
                })
                        .then(response => response.blob())
                        .then(blob => {
                            const imageUrl = URL.createObjectURL(blob);
                            document.getElementById('editImage').src = imageUrl;
                        })
                        .catch(error => console.error('Error:', error));

            }


            // Filter Assets
            function filterAssets() {
                const nameFilter = document.getElementById('nameFilter').value.toLowerCase();
                const statusFilter = document.getElementById('statusFilter').value;
                const serviceFilter = document.getElementById('serviceFilter').value;

                const rows = document.querySelectorAll('#assetsTableBody tr');

                rows.forEach(row => {
                    const names = row.getAttribute('data-names').toLowerCase();
                    const status = row.getAttribute('data-status');
                    const service = row.getAttribute('data-service');

                    const nameMatch = (nameFilter === '' || names.includes(nameFilter));
                    const statusMatch = (statusFilter === 'All' || status === statusFilter);
                    const serviceMatch = (serviceFilter === 'All' || service.includes(serviceFilter));

                    if (nameMatch && statusMatch && serviceMatch) {
                        row.style.display = '';
                    } else {
                        row.style.display = 'none';
                    }
                });
            }
        </script>
    </body>
</html>