

<title>UPDATE USER</title>


<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@page contentType="text/html; charset=UTF-8" %>


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
        gap: 20px;
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
        <h3 class="fw-bold mb-3">UPDATE USER</h3>

        <form action="update_user" method="post">

            <!-- User ID -->
            <div>
                <label for="userid">User ID</label>
                <input type="text" id="userid" name="userid" value="${user.getUserID()}" readonly>
            </div>

            <!-- Username -->
            <div>
                <label for="name">User Name</label>
                <input type="text" id="name" name="username" value="${user.getUsername()}" readonly>
            </div>

            <!-- Full Name -->
            <div>
                <label for="full">Full Name</label>
                <input type="text" id="full" name="fullname" value="${user.getFullName()}" readonly>
            </div>

            <!-- Phone Number -->
            <div>
                <label for="phonenum">Phone Number</label>
                <input type="text" id="phonenum" name="phonenumber" value="${user.getPhone()}" readonly>
            </div>

            <!-- Email -->
            <div>
                <label for="mail">Email</label>
                <input type="text" id="mail" name="email" value="${user.getEmail()}" readonly>
            </div>

            <!-- Address -->
            <div>
                <label for="address">Address</label>
                <input type="text" id="address" name="address" value="${user.getAddress()}" readonly>
            </div>

            <!-- Image -->
            <div>
                <label for="img">Image</label>
                <input type="text" id="img" name="img" value="${user.getImage()}" readonly>
            </div>

            <!-- Manager ID -->

            <div>
                <label for="managerid">Manager ID</label>
                <input type="text" id="managerid" name="managerid"
                       pattern="^[0-9]+$"
                       value="${user.getManager() != null ? user.getManager().userID : ''}"
                       title="Vui lòng chỉ nhập số !!">
            </div>

            <!-- Identity Card -->
            <div>
                <label for="card">Identity Card</label>
                <input type="text" id="card" name="card" value="${user.getCCCD()}" readonly>
            </div>

            <!-- Date of Birth -->
            <div>
                <label for="dob">Date Of Birth</label>
                <input type="date" id="dob" name="dob" value="${user.getDateOfBirth()}" readonly>
            </div>

            <!-- Role -->
            <div>
                <label for="role">Role</label>
                <select id="role" name="role" required>
                    <option value="" disabled >Choose Role</option>
                    <option value="1" ${user.getRoleID() == 1 ? 'selected' : ''}>Admin</option>
                    <option value="2" ${user.getRoleID() == 2 ? 'selected' : ''}>Seller</option>
                    <option value="3" ${user.getRoleID() == 3 ? 'selected' : ''}>Manager</option>
                    <option value="4" ${user.getRoleID() == 4 ? 'selected' : ''}>Provider Insurance</option>
                    <option value="5" ${user.getRoleID() == 5 ? 'selected' : ''}>Customer</option>
                </select>
            </div>

            <!-- Gender -->
            <div>
                <label for="gender">Gender</label>
                <input type="text" id="gender" name="gender" value="${user.isGender() == true ? 'Male' : 'Female'}" readonly>
            </div>


            <!-- Submit Button -->
            <input type="submit" value="UPDATE">
        </form>

    </div>



</body>




