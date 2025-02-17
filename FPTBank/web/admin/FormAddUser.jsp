

<title>ADD USER</title>


<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ page contentType="text/html; charset=UTF-8" %>


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
<link href="assets/css/main.css" rel="stylesheet">
<link href="assets/css/dark-theme.css" rel="stylesheet">
<link href="assets/css/semi-dark-theme.css" rel="stylesheet">
<link href="assets/css/minimal-theme.css" rel="stylesheet">
<link href="assets/css/shadow-theme.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>




<style>

    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }

    h1 {
        text-align: center;
        color: #333;
    }

    .form-container {
        margin-top: 50px;
        max-width: 600px;
        width: 100%;
        background: #fff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    }

    .form-container h3 {
        text-align: center;
        color: green;
    }

    form {
        display: grid;
        grid-template-columns: 1fr 1fr;
        gap: 8px;
    }

    label {
        display: block;
        margin-bottom: 5px;
        color: #555;
    }

    input[type="text"],
    input[type="number"],
    input[type="date"],
    select {
        width: 100%;
        padding: 10px;
        border: 1px solid #ddd;
        border-radius: 4px;
        box-sizing: border-box;
        outline: none;
    }

    input[type="text"]:focus,
    input[type="number"]:focus,
    input[type="date"]:focus,
    select:focus {
        border-color: #4CAF50;
        box-shadow: 0 0 5px rgba(76, 175, 80, 0.5);
    }

    input[type="submit"] {
        grid-column: span 2; /* Nút submit chiếm toàn bộ chiều ngang */
        background-color: #4CAF50;
        color: white;
        padding: 10px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }

    input[type="submit"]:hover {
        background-color: #45a049;
    }



</style>
</head>
<body>
    <%@ include file="Common/header.jsp" %>
    <%@ include file="Common/sidebar.jsp" %>
    <%@ include file="Common/toarst.jsp" %>


    <div class="form-container">
        <h3 class="fw-bold mb-3">ADD USER</h3>

        <form action="insert_users" method="post" enctype="multipart/form-data">
            <!-- Username -->
            <div>
                <label for="name">User Name</label>
                <input type="text" id="name" name="username" 
                       required
                       pattern="^[a-zA-Z0-9_.]{6,20}$" 
                       title="Username phải từ 6-20 ký tự, chỉ chứa chữ cái, số, dấu chấm (.) và gạch dưới (_)"
                       oninput="validateAndCheckUsername()">
                <span id="nameError" class="error"></span>
            </div>


            <!-- Password -->
            <div>
                <label for="pass">Password</label>
                <input type="text" id="pass" name="password" 
                       required 
                       placeholder="Abcd@123"
                       pattern="^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[@#$%^&*!])[a-zA-Z\d@#$%^&*!]{8,16}$"
                       title="Mật khẩu phải có 8-16 ký tự, chứa ít nhất một chữ hoa, một chữ thường, một số và một ký tự đặc biệt !!"
                       oninput="validatePassword()" >
                <span id="passError" class="error"></span>
            </div>

            <!-- Full Name -->
            <div>
                <label for="full">Full Name</label>
                <input type="text" id="full" name="fullname" 
                       required
                       placeholder="Nguyen Van A"
                       pattern="^(?!\s*$)[A-Za-zÀ-ỹ\s]+$"
                       maxlength="100"
                       title="Vui lòng không nhập số hoặc kí tự đặc biệt !!"
                       oninput="validateFullName()">
                <span id="fullError"></span>
            </div>

            <!-- Phone Number -->
            <div>
                <label for="phonenum">Phone Number</label>
                <input type="text" id="phonenum" name="phonenumber" 
                       required 
                       pattern="0[0-9]{9}" placeholder="10 digit number"  
                       title="Vui lòng nhập số điện thoại bắt đầu bằng số 0 và gồm đúng 10 chữ số !!"
                       oninput="validatePhoneNumber()">
                <span id="phoneError"></span>
            </div>


            <!-- Image -->
            <div>
                <label for="img">Upload Image</label>
                <input type="file" id="img" name="img" accept=".jpg,.jpeg,.png" required title="Vui lòng upload ảnh !!">
            </div>


            <!-- Email -->
            <div>
                <label for="mail">Email</label>
                <input type="text" id="mail" name="email" 
                       placeholder="example@gmail.com" 
                       title="Vui lòng nhập đúng cú pháp email !!"
                       required pattern="^[a-zA-Z0-9]+@gmail\.com$" 
                       oninput="validateEmail()">
                <span id="mailError" class="error"></span>
            </div>


            <!-- Address -->
            <div>
                <label for="address">Address</label>
                <input type="text" id="address" name="address" 
                       required 
                       pattern="^(?!\s*$)[A-Za-zÀ-ỹ0-9\s,.-]{5,100}$" 
                       title="Địa chỉ phải từ 5-100 ký tự, chỉ chứa chữ cái, số, dấu phẩy (,), dấu chấm (.) và dấu gạch ngang (-)"
                       oninput="validateAddress()">
                <span id="addressError"></span>
            </div>

            <!-- Manager ID -->
            <div>
                <c:choose>
                    <c:when test="${not empty sessionScope.listManager}">
                        <label for="manager">Manager</label>
                        <select id="manager" name="managerid">
                            <option value="">Choose Manager</option>
                            <c:forEach var="listM" items="${sessionScope.listManager}">
                                <option value="${listM.getUserID()}"> ${listM.getFullName()}</option>
                            </c:forEach> 
                        </select>
                    </c:when>
                    <c:otherwise>
                        <label for="manager">Manager</label>
                        <select id="manager" name="managerid">
                            <option value=""disabled>Do not have any manager</option>
                        </select>
                    </c:otherwise>
                </c:choose>
            </div>

            <!-- Identity Card -->
            <div>
                <label for="card">Identity Card</label>
                <input type="text" id="card" name="card" 
                       pattern="\d{12}" placeholder="12 digit number" 
                       title="Vui lòng nhập đúng 12 chữ số" required
                       oninput="validateIdentityCard()">
                <span id="cardError"></span>
            </div>

            <!-- Date of Birth -->
            <div>
                <label for="dob">Date Of Birth</label>
                <input type="date" id="dob" name="dob" required>
            </div>

            <!-- Role -->
            <div>
                <label for="role">Role</label>
                <select id="role" name="role" required>
                    <option value="" disabled selected>Choose Role</option>
                    <option value="1">Admin</option>
                    <option value="2">Seller</option>
                    <option value="3">Manager</option>
                    <option value="4">Provider Insurance</option>
                    <option value="5">Customer</option>
                </select>
            </div>

            <!-- Gender -->
            <div>
                <label for="gender">Gender</label>
                <select id="gender" name="gender" required>
                    <option value="" disabled selected>Choose Gender</option>
                    <option value="1">Male</option>
                    <option value="0">Female</option>
                </select>
            </div>

            <!-- Submit Button -->
            <input type="submit" value="ADD">
        </form>
    </div>



    <script>


        function validateAndCheckUsername() {
            let username = document.getElementById("name").value;
            let errorSpan = document.getElementById("nameError");
            let pattern = /^[a-zA-Z0-9_.]{6,20}$/;

            // Kiểm tra tính hợp lệ của username
            if (!pattern.test(username)) {
                errorSpan.innerText = "Invalid username!! Must be 6-20 characters, containing only letters, numbers, periods (.) and underscores ( _ )";
                errorSpan.style.color = "red";
                errorSpan.style.fontSize = "13px";
                return; // Dừng lại nếu username không hợp lệ
            } else {
                errorSpan.innerText = "";
            }

            // Nếu username hợp lệ, gửi AJAX để kiểm tra username đã tồn tại
            $.ajax({
                url: 'checkFieldExist', // URL của servlet xử lý
                type: 'POST',
                data: {
                    username: username // Gửi username qua POST
                },
                success: function (response) {
                    if (response === "exists") {
                        // Nếu username đã tồn tại
                        errorSpan.innerText = "Username already exists. Please choose another username!!";
                        errorSpan.style.color = "red";
                        errorSpan.style.fontSize = "13px";
                    } else if (response === "available") {
                        // Nếu username khả dụng
                        errorSpan.innerText = "Valid username !!";
                        errorSpan.style.color = "green";
                        errorSpan.style.fontSize = "13px";
                    }
                },
                error: function (xhr, status, error) {
                    // Xử lý lỗi nếu request thất bại
                    errorSpan.innerText = "An error occurred while checking the username. Please try again!!";
                    errorSpan.style.color = "red";
                    errorSpan.style.fontSize = "13px";
                }
            });
        }


        function validatePassword() {
            let password = document.getElementById("pass").value;
            let errorSpan = document.getElementById("passError");
            let pattern = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[@#$%^&*!])[a-zA-Z\d@#$%^&*!]{8,16}$/;
            if (!pattern.test(password)) {
                errorSpan.innerText = "Password must have 8-16 characters, including uppercase letters, lowercase letters, numbers and special characters!!";
                errorSpan.style.color = "red";
                errorSpan.style.fontSize = "13px";
                return; // Dừng lại nếu username không hợp lệ
            } else {
                errorSpan.innerText = "Valid password !!";
                errorSpan.style.color = "green";
                errorSpan.style.fontSize = "13px";
            }

        }

        function validateEmail() {
            let email = document.getElementById("mail").value;
            let errorSpan = document.getElementById("mailError");
            let pattern = /^[a-zA-Z0-9]+@gmail\.com$/;
            if (!pattern.test(email)) {
                errorSpan.innerText = "Email must be in form example@gmail.com !!";
                errorSpan.style.color = "red";
                errorSpan.style.fontSize = "13px";
                return; // Dừng lại nếu username không hợp lệ
            } else {
                errorSpan.innerText = "";
            }

            $.ajax({
                url: 'checkFieldExist', // URL của servlet xử lý
                type: 'POST',
                data: {
                    email: email
                },
                success: function (response) {
                    if (response === "exists") {
                        // Nếu email đã tồn tại
                        errorSpan.innerText = "Email already exists. Please enter another email !!";
                        errorSpan.style.color = "red";
                        errorSpan.style.fontSize = "13px";
                    } else if (response === "available") {
                        // Nếu username khả dụng
                        errorSpan.innerText = "Valid email!!";
                        errorSpan.style.color = "green";
                        errorSpan.style.fontSize = "13px";
                    }
                },
                error: function (xhr, status, error) {
                    // Xử lý lỗi nếu request thất bại
                    errorSpan.innerText = "An error occurred while checking email. Please try again!!";
                    errorSpan.style.color = "red";
                    errorSpan.style.fontSize = "13px";
                }
            });
        }

        function validateFullName() {
            let fullName = document.getElementById("full").value;
            let errorSpan = document.getElementById("fullError");
            let pattern = /^(?!\s*$)[A-Za-zÀ-ỹ\s]+$/; // Chỉ cho phép chữ cái và khoảng trắng

            if (!pattern.test(fullName)) {
                errorSpan.innerText = "Invalid fullname!! Only enter letters and spaces";
                errorSpan.style.color = "red";
                errorSpan.style.fontSize = "13px";
                return; // Dừng lại nếu username không hợp lệ
            } else {
                errorSpan.innerText = "";
            }
        }


        function validatePhoneNumber() {
            let phoneNumber = document.getElementById("phonenum").value;
            let errorSpan = document.getElementById("phoneError");
            let pattern = /^0[0-9]{9}$/; // Bắt đầu bằng 0, tổng cộng đúng 10 số

            if (!pattern.test(phoneNumber)) {
                errorSpan.innerText = "Invalid phone number!! Must start with 0 and have exactly 10 digits";
                errorSpan.style.color = "red";
                errorSpan.style.fontSize = "13px";
                return; // Dừng lại nếu username không hợp lệ
            } else {
                errorSpan.innerText = "";
            }

            $.ajax({
                url: 'checkFieldExist', // URL của servlet xử lý
                type: 'POST',
                data: {
                    phone: phoneNumber
                },
                success: function (response) {
                    if (response === "exists") {
                        // Nếu phonenum đã tồn tại
                        errorSpan.innerText = "Phone number already exists. Please enter another phone number!!";
                        errorSpan.style.color = "red";
                        errorSpan.style.fontSize = "13px";
                    } else if (response === "available") {
                        // Nếu phonenum khả dụng
                        errorSpan.innerText = "Valid phone number !!";
                        errorSpan.style.color = "green";
                        errorSpan.style.fontSize = "13px";
                    }
                },
                error: function (xhr, status, error) {
                    // Xử lý lỗi nếu request thất bại
                    errorSpan.innerText = "An error occurred while checking the phone number. Please try again!!";
                    errorSpan.style.color = "red";
                    errorSpan.style.fontSize = "13px";
                }
            });
        }

        function validateAddress() {
            let address = document.getElementById("address").value;
            let errorSpan = document.getElementById("addressError");
            let pattern = /^(?!\s*$)[A-Za-zÀ-ỹ0-9\s,.-]{5,100}$/; // Địa chỉ hợp lệ

            if (!pattern.test(address)) {
                errorSpan.innerText = "Invalid address !! Must be 5-100 characters, contain only letters, numbers, periods (.), commas (,), hyphens (-)";
                errorSpan.style.color = "red";
                errorSpan.style.fontSize = "13px";
                return; // Dừng lại nếu username không hợp lệ
            } else {
                errorSpan.innerText = "";
            }
        }

        function validateIdentityCard() {
            let card = document.getElementById("card").value;
            let errorSpan = document.getElementById("cardError");
            let pattern = /^\d{12}$/; // Chỉ chứa đúng 12 số

            if (!pattern.test(card)) {
                errorSpan.innerText = "Identity card is not valid!! Please enter correct 12 digits";
                errorSpan.style.color = "red";
                errorSpan.style.fontSize = "13px";
                return; // Dừng lại nếu username không hợp lệ
            } else {
                errorSpan.innerText = "";
            }

            $.ajax({
                url: 'checkFieldExist', // URL của servlet xử lý
                type: 'POST',
                data: {
                    cccd: card
                },
                success: function (response) {
                    if (response === "exists") {
                        // Nếu cccd đã tồn tại
                        errorSpan.innerText = "Identity card already exists!! Please enter another number";
                        errorSpan.style.color = "red";
                        errorSpan.style.fontSize = "13px";
                    } else if (response === "available") {
                        // Nếu cccd khả dụng
                        errorSpan.innerText = "Valid identity card !!";
                        errorSpan.style.color = "green";
                        errorSpan.style.fontSize = "13px";
                    }
                },
                error: function (xhr, status, error) {
                    // Xử lý lỗi nếu request thất bại
                    errorSpan.innerText = "An error occurred while checking the identity card number. Please try again!!";
                    errorSpan.style.color = "red";
                    errorSpan.style.fontSize = "13px";
                }
            });
        }





    </script>


</body>




