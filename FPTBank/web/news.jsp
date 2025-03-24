
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Trang Tin Tức Vietcombank</title>
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


        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f9f9f9;
            }
            .container {
                max-width: 1200px;
                margin: 0 auto;
                padding: 20px;
            }
            .nav-tabs {
                display: flex;
                align-items: center;
                background-color: transparent;
                padding: 10px 0;
                margin-bottom: 20px;
            }
            .nav-tabs a {
                width: 180px;
                background-color: #d4edda;
                padding: 8px 16px;
                border-radius: 20px;
                font-size: 14px;
                text-decoration: none;
                color: #333;
                display: inline-block;
                transition: background-color 0.3s;
            }
            .nav-tabs a.active {
                background-color: #28a745;
                color: white;
            }
            .nav-tabs a:hover {
                background-color: #c3e6cb;
            }
            .nav-tabs .filter {
                font-size: 14px;
                margin-left:130px;
                color: #666;
                cursor: pointer;
            }
            .card-container {
                display: grid;
                align-items: center;
                justify-content: center;
                grid-template-columns: repeat(2, 1fr);
                gap: 20px;
            }
            .card {
                background-color: #fff;
                border: 1px solid #ddd;
                border-radius: 10px;
                overflow: hidden;
                box-shadow: 0 2px 5px rgba(0,0,0,0.1);
                padding: 20px;
                display: flex;
                width: 570px;
                height: 200px;
            }
            .card img {
                width: 150px;
                height: auto;
                border-radius: 10px;
                object-fit: cover;
                flex-shrink: 0;
            }
            .card-content {
                padding: 10px;
                flex-grow: 1;
                display: flex;
                flex-direction: column;
                justify-content: space-between;
            }
            .card-content h3 {
                margin: 0;
                font-size: 16px;
                color: #333;
            }
            .card-content p {
                margin: 5px 0;
                font-size: 12px;
                color: #666;
            }
            .card-content a {
                display: inline-block;
                margin-top: 10px;
                color: #28a745;
                text-decoration: none;
                font-weight: bold;
            }
            .filter {
                margin: 10px 0;

            }

            #serviceFilter {
                padding: 8px 12px;
                font-size: 16px;
                border: 1px solid #ccc;
                border-radius: 4px;
                background-color: white;
                cursor: pointer;
                background-color: #d4edda;
                outline: none;
                min-width: 150px;

                appearance: none; /* Removes default browser styling */
                background-image: url('data:image/svg+xml;utf8,<svg fill="black" height="24" viewBox="0 0 24 24" width="24" xmlns="http://www.w3.org/2000/svg"><path d="M7 10l5 5 5-5z"/></svg>'); /* Custom dropdown arrow */
                background-repeat: no-repeat;
                background-position: right 8px center;
                padding-right: 30px; /* Makes space for the arrow */
            }

            /* Hover effect */
            #serviceFilter:hover {
                border-color: #888;
            }

            /* Focus effect */
            #serviceFilter:focus {
                border-color: #007bff;
                box-shadow: 0 0 5px rgba(0,123,255,0.3);
            }

            /* Style for options */
            #serviceFilter option {
                padding: 5px;
            }

            /* Optional: Add some transition for smooth changes */
            #serviceFilter {
                transition: all 0.3s ease;
            }
            .load-more {
                text-align: center;
                margin-top: 20px;
            }

            .load-more button {
                padding: 10px 20px;
                background-color: #28a745;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                font-size: 14px;
            }

            .load-more button:hover {
                background-color: #218838;
            }
        </style>
        <script>
            function submitForm(categoryId) {
                // Cập nhật giá trị của input ẩn trước khi gửi form
                document.getElementById('idCate').value = categoryId;
                // Gửi form
                document.getElementById('newsForm').submit();
            }
        </script>

    </head>
    <body>
        <div id="smooth-wrapper" class="mil-wrapper">

        </div>
        <!-- preloader end -->

        <!-- scroll progress -->
        <div class="mil-progress-track">
            <div class="mil-progress"></div>
        </div>
        <!-- scroll progress end -->

        <!-- back to top -->
        <div class="progress-wrap active-progress"></div>

        <!-- top panel end -->
        <%@ include file="header.jsp"%>

        <div class="container">
            <div >
                <img style="width: 1200px;height: 250px; border-radius: 10px;margin-top: 150px;" src="https://www.vietcombank.com.vn/-/media/Project/VCB-Sites/VCB/KHCN/Uu-dai/Anh-uu-dai/Anh-tin-tuc/Desktop_TT-noi-bat-KHCN.jpg?h=750&iar=0&w=3936&ts=20230726121312&hash=28909A2690022BA842E2624AE01CE4F0" alt="">
            </div>
            <div class="nav-tabs">
                <form action="news" method="post" id="newsForm">
                    <div class="nav-tabs">

                        <c:forEach items="${requestScope.dataCate}" var="newCate">
                            <a href="javascript:void(0)" onclick="submitForm('${newCate.getNewsCategoryID()}')" 
                               class="${newCate.getNewsCategoryID() == param.idCate ? 'active' : ''}">
                                ${newCate.getNewsCategoryName()}
                            </a>
                            <input type="hidden" name="idCate" id="idCate" value="${newCate.getNewsCategoryID()}"/>
                        </c:forEach>

                        <div class="filter">
                            <select class="filter" id="serviceFilter" name="opDate" onchange="document.getElementById('newsForm').submit()">
                                <option value="CreatedAtASC" ${param.opDate == 'CreatedAtASC' ? 'selected' : ''}>Latest</option>
                                <option value="CreatedAtDESC" ${param.opDate == 'CreatedAtDESC' ? 'selected' : ''}>Early</option>
                            </select>
                        </div>
                    </div>
                </form>

            </div>
            <div class="card-container" id="cardContainer">
                <c:forEach items="${requestScope.data}" var="news">
                    <div class="card">                    
                            <img src="${pageContext.request.contextPath}/${news.getImage()}" alt="Image">                  
                        <div class="card-content">
                            <h3>${news.getTitle()}</h3>
                            <p>${news.getCreatedAt()}</p>
                            <p>Số lượng xem:${news.getNumberOfAccess()}</p>
                            <a href="/timibank/newdetail?newId=${news.getNewsID()}">Xem chi tiết</a>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="load-more" id="loadMore">
                <button onclick="loadMoreNews()">Xem thêm</button>
            </div>
        </div>
        <%@ include file="footer.jsp"%>
        <script>
         let currentPage = ${requestScope.currentPage}; // Trang hiện tại
    const pageSize = ${requestScope.pageSize}; // Số tin tức mỗi trang
    const totalNews = ${requestScope.totalNews}; // Tổng số tin tức
    const idCate = ${requestScope.idCate};
    const cardContainer = document.getElementById('cardContainer');
    const loadMoreBtn = document.getElementById('loadMore');
    function loadMoreNews() {
        currentPage++; // Tăng trang lên 1
        console.log("onclick");

        // Gửi yêu cầu AJAX để lấy thêm tin tức
           const url =`/timibank/loadMoreNews?cateid=`+idCate+`&page=`+currentPage+`&size=`+pageSize;
           console.log("url"+url);
        fetch(url)
            .then(response => response.json())
            .then(data => {
                console.log("data"+data);
                // Thêm các tin tức mới vào cardContainer
                data.forEach(news => {
                    // Tạo phần tử card
                    const card = document.createElement('div');
                    card.className = 'card';

                    // Tạo phần tử hình ảnh
                    const img = document.createElement('img');
                    img.src = news.Image;
                    img.alt = 'Image';

                    // Tạo phần tử card-content
                    const cardContent = document.createElement('div');
                    cardContent.className = 'card-content';

                    // Tạo tiêu đề
                    const title = document.createElement('h3');
                    title.textContent = news.Title;

                    // Tạo đoạn văn cho ngày tạo
                    const createdAt = document.createElement('p');
                    createdAt.textContent = news.CreatedAt;

                    // Tạo đoạn văn cho số lượt truy cập
                    const numberOfAccess = document.createElement('p');
                    numberOfAccess.textContent = news.NumberOfAccess;

                    // Tạo liên kết "Xem chi tiết"
                    const link = document.createElement('a');
                    link.href = `/timibank/newdetail?newId=${news.NewsID}`;
                    link.textContent = 'Xem chi tiết';

                    // Gắn các phần tử con vào card-content
                    cardContent.appendChild(title);
                    cardContent.appendChild(createdAt);
                    cardContent.appendChild(numberOfAccess);
                    cardContent.appendChild(link);

                    // Gắn hình ảnh và card-content vào card
                    card.appendChild(img);
                    card.appendChild(cardContent);

                    // Gắn card vào cardContainer
                    cardContainer.appendChild(card);
                });

                // Ẩn nút "Xem thêm" nếu đã tải hết tin tức
                if (currentPage * pageSize >= totalNews) {
                    loadMoreBtn.style.display = 'none';
                }
            })
            .catch(error => console.error('Error loading more news:', error));
    }
        </script>
    </body>
</html>