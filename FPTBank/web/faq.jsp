<%-- 
    Document   : faq
    Created on : Feb 3, 2025, 9:34:56 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
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
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet"/>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&amp;display=swap" rel="stylesheet"/>

        <!-- Favicon -->
        <link rel="shortcut icon" href="img/favicon.png" type="image/x-icon">
        <link rel="icon" href="img/favicon.png" type="image/x-icon">

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css"/>
    </head>

    <style>
        .mine-checkbox {
            margin-left: 20px;
        }

        .mine-checkbox input[type="checkbox"] {
            margin-right: 8px;
        }

        .checkbox-item {
            display: flex;
            align-items: center;
        }
        .checkbox-item label {
            margin-left: 5px;
        }

        .checkbox-bar {
            display: flex;
            justify-content: space-around;
            background-color: #f4f4f4;
            padding: 10px;
        }

        .filter-group {
            background: white;
            padding: 15px;
            border-radius: 10px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
            margin-bottom: 20px;
        }
        .filter-controls h3{
            text-align: center;
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


        .search-container {
            position: relative;

            max-width:80%;
            display: flex;
            gap: 10px;
            margin-left: 10%;
        }

        .search-container i {
            position: absolute;
            left: 8px;
            top: 50%;
            transform: translateY(-50%);
            color: #666;
            font-size: 0.9em;
        }

        .search-input {
            padding: 6px 6px 6px 28px;
            border: 1px solid #ddd;
            border-radius: 6px;
            width: 100%;
            font-size: 0.9em;
        }
        .add-news-btn {
            padding: 10px 20px;
            border-radius: 8px;
            font-weight: 600;
            text-transform: uppercase;
            letter-spacing: 0.5px;
            cursor: pointer;
            transition: all 0.3s ease;
            border: none;
            font-size: 0.9em;
            margin: 10px 0;
            display: inline-block;
            text-align: center;
            text-decoration: none;
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


    </style>
    <body>
        <div id="smooth-wrapper" class="mil-wrapper">

            <%@ include file="header.jsp"%>
            <!-- Menu -->
            <%@ include file="toolBar.jsp"%>
            <!-- Menu end -->

            <!-- top panel end -->

            <!-- content -->
            <div class="mil-features faq-wrap">

                <form action="faq-servlet-search"  class="filter-controls">  
                    <div class="search-container"  > 
                        <i class="fa fa-search"></i>
                        <input   type="text" name="searchKeyword"  value="${keyword}" placeholder="Tìm câu hỏi nhanh ..." class="search-input">
                        <button style="color: white; background-color: #008000" class="add-news-btn" type="submit" >Search </button>
                    </div>
                </form>

                <form action="faq-servlet-type" method="get" class="filter-controls">
                    <div class="filter-group">
                        <h3>Câu hỏi về</h3>
                        <div class="checkbox-bar">
                            <%
                                String selectedType = request.getAttribute("selectedType") != null ? request.getAttribute("selectedType").toString() : "";
                            %>
                            <div class="checkbox-item">
                                <input type="checkbox" id="faq1" name="faqType" value="account" class="faq-checkbox"
                                       <%= "account".equals(selectedType) ? "checked" : "" %>>
                                <label for="faq1">Tài khoản ngân hàng</label>
                            </div>
                            <div class="checkbox-item">
                                <input type="checkbox" id="faq2" name="faqType" value="borrow" class="faq-checkbox"
                                       <%= "borrow".equals(selectedType) ? "checked" : "" %>>
                                <label for="faq2">Vay tiền</label>
                            </div>
                            <div class="checkbox-item">
                                <input type="checkbox" id="faq3" name="faqType" value="savings" class="faq-checkbox"
                                       <%= "savings".equals(selectedType) ? "checked" : "" %>>
                                <label for="faq3">Gửi tiền</label>
                            </div>
                            <div class="checkbox-item">
                                <input type="checkbox" id="faq4" name="faqType" value="card" class="faq-checkbox"
                                       <%= "card".equals(selectedType) ? "checked" : "" %>>
                                <label for="faq4">Thẻ</label>
                            </div>
                            <div class="checkbox-item">
                                <button style="color: white; background-color: #008000" class="add-news-btn" type="submit">SearchType</button>
                            </div>
                        </div>
                    </div>
                </form>     

                <div class="search-container"  > 
                    <!--show entry-->
                    <form id="entriesForm" action="faq" method="GET" accept-charset="UTF-8">
                        <label for="entries">Show Entries</label>
                        <select  id="entries" name="entries" onchange="this.form.submit()">
                            <c:forEach items="${listOfPageSize}" var="ps">
                                <option value="${ps}" ${(ps == entries)?'selected':''}>${ps}</option>
                            </c:forEach>                   
                        </select>
                        <input type="hidden" name="searchKey" value="${keyword}">
                        <input type="hidden" name="searchType" value="${selectedType}">
                    </form>
                </div>

                <div class="container faq-question__contents" >
                    <ol class="faq-question__ques-list">
                        <c:forEach items="${listFAQ}" var="faq">
                            <li class="faq-question__ques-item">
                                <div class="faq-question__ques-wrap">
                                    <p class="faq-question__ques-title">${faq.getQuestion()}</p>
                                    <i class="fa-solid fa-angle-down faq-question__ques-icon"></i>
                                </div>
                                <div class="faq-question__ques-ans">
                                    ${faq.getAnswer()}
                                </div>
                            </li>
                        </c:forEach>
                    </ol>
                </div>

                <div class="pagination">
                    <c:if test="${currentPage > 1}">
                        <a href="?page=${currentPage - 1}&searchKeyword=${keyword}&faqType=${selectedType}&entries=${entries}"  class="prev" > Previous</a>
                    </c:if>

                    <c:forEach var="i" begin="1" end="${totalPages}">
                        <a href="?page=${i}&searchKeyword=${keyword}&faqType=${selectedType}&entries=${entries}" class="${i == currentPage ? 'active' : ''}">${i}</a>
                    </c:forEach>

                    <c:if test="${currentPage < totalPages}">
                        <a href="?page=${currentPage + 1}&searchKeyword=${keyword}&faqType=${selectedType}&entries=${entries}" class="next">Next</a>
                    </c:if>
                </div>



            </div>





            <!-- footer -->
            <%@ include file="footer.jsp"%>

            <!-- footer end -->
        </div>
        <script>
            function changePage(page) {
                const form = document.querySelector(".filter-controls"); // Chỉ lấy form đầu tiên
                const input = document.createElement("input");
                input.type = "hidden";
                input.name = "page";
                input.value = page;
                form.appendChild(input);
                form.submit();
            }

            // Toast message animation
            document.addEventListener('DOMContentLoaded', function () {
                const toast = document.getElementById('toastMessage');
                if (toast) {
                    // Show toast
                    setTimeout(() => {
                        toast.classList.add('show');
                    }, 100);

                    // Hide toast after 3 seconds
                    setTimeout(() => {
                        toast.classList.remove('show');
                        // Remove toast from DOM after animation
                        setTimeout(() => {
                            toast.remove();
                        }, 500);
                    }, 3000);
                }
            });

            document.addEventListener("DOMContentLoaded", function () {
                const checkboxes = document.querySelectorAll(".faq-checkbox");

                checkboxes.forEach(checkbox => {
                    checkbox.addEventListener("change", function () {
                        checkboxes.forEach(cb => {
                            if (cb !== this) {
                                cb.checked = false;
                            }
                        });
                    });
                });
            });

        </script>

    </div>
    <script src="./js/scripts.js"></script>

    <!-- jquery js -->
    <script src="js/plugins/jquery.min.js"></script>

    <!-- swiper css -->
    <script src="js/plugins/swiper.min.js"></script>
    <!-- gsap js -->
    <script src="js/plugins/gsap.min.js"></script>
    <!-- scroll smoother -->
    <script src="js/plugins/ScrollSmoother.min.js"></script>
    <!-- scroll trigger js -->
    <script src="js/plugins/ScrollTrigger.min.js"></script>
    <!-- scroll to js -->
    <script src="js/plugins/ScrollTo.min.js"></script>
    <!-- magnific -->
    <script src="js/plugins/magnific-popup.js"></script>
    <!-- plax js -->
    <script src="js/main.js"></script>


</body>
</html>
