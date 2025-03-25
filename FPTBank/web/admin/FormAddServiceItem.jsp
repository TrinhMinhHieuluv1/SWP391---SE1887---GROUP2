

<title>ADD SERVICE ITEMS</title>


<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


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
<!-- Favicon -->
<link rel="shortcut icon" href="assets/images/logo-icon.png" type="image/x-icon">
<link rel="icon" href="assets/images/logo-icon.png" type="image/x-icon">

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

    <div class="form-container" >
        <h3 class="fw-bold mb-3">ADD SERVICE ITEM</h3>

        <form action="insert_serviceItem" method="post" >

            <!-- Service Item Name -->
            <div>
                <label for="name">Service Item Name</label> 
                <input type="text" id="name" name="serviceItemName" 
                       required 
                       value="${serviceItemToAdd.getServiceItemName()}"
                       pattern="^[a-zA-Z0-9_.]{6,20}$" 
                       title="Service Item Name phải từ 6-20 ký tự, chỉ chứa chữ cái, số, dấu chấm (.) và gạch dưới (_)"
                       >

            </div>

            <!-- Max Amount -->
            <div>
                <label for="maxAmount">Max Amount (VNĐ)</label>
                <input type="text" id="maxAmount" name="maxAmount" 
                        
                       value="<c:if test='${serviceItemToAdd.getMaxAmount() != null}'><fmt:formatNumber value='${serviceItemToAdd.getMaxAmount()}' type='number' groupingUsed='true' minFractionDigits='0' maxFractionDigits='0'/></c:if>"
                           placeholder="1.000.000"
                           pattern="^([1-9][0-9]{0,11}|[1-9][0-9]{0,2}(\.[0-9]{3}){0,3})$"
                           title="Số tiền tối đa phải là số nguyên dương, từ 1 đến 999.999.999.999 VNĐ. Có thể nhập không dấu chấm (1000000) hoặc có dấu chấm (1.000.000).">
                </div>


                <!-- Max period -->
                <div>
                    <label for="maxPeriod">Max Period (Month)</label>
                    <input type="text" id="maxPeriod" name="maxPeriod" 
                            
                           value="${serviceItemToAdd.getMaxPeriod() != null && serviceItemToAdd.getMaxPeriod() != 0  ? serviceItemToAdd.getMaxPeriod() : ''}"
                    placeholder="12"
                    pattern="^[1-9][0-9]{0,2}$"
                    maxlength="3"
                    title="Kỳ hạn tối đa phải là số nguyên dương từ 1 đến 999 tháng."
                    >

            </div>


            <!-- Min Period -->
            <div>
                <div style="display:flex;">
                    <label for="minPeriod">Min Period (Month)</label>
                    <label for="minPeriod" style="color: red; font-size: small; margin-left: 5px; display: inline;">(Saving only)</label>
                </div>
                <input type="text" id="minPeriod" name="minPeriod" 
                       value="${serviceItemToAdd.getMinPeriod() != null && serviceItemToAdd.getMinPeriod() != 0 ? serviceItemToAdd.getMinPeriod() : ''}"
                       placeholder="1"
                       pattern="^[1-9][0-9]{0,2}$"
                       maxlength="3"
                       title="Kỳ hạn tối thiểu phải là số nguyên dương từ 1 đến 999 tháng."
                       >

            </div>


            <!-- Late Payment Rate -->
            <div>
                <label for="latePaymentRate">Late Payment Rate (%)</label>
                <input type="text" id="latePaymentRate" name="latePaymentRate" 
                        
                       value="${serviceItemToAdd.getLatePaymentRate() != null && serviceItemToAdd.getLatePaymentRate() != 0.0? serviceItemToAdd.getLatePaymentRate() : ''}"
                       placeholder="1.5"
                       pattern="^[0-9]{1,2}(\.[0-9]{1,2})?$|^100(\.0{1,2})?$"
                       title="Tỷ lệ phạt trễ hạn phải là số dương từ 0 đến 100%, tối đa 2 chữ số thập phân (ví dụ: 1.5, 12.75, 100)."
                       >

            </div>


            <!-- Min Amount -->
            <div>
                <div style="display:flex;">
                    <label for="minAmount">Min Amount (VNĐ)</label>
                    <label for="minAmount" style="color: red; font-size: small; margin-left: 5px; display: inline;">(Saving only)</label>
                </div>
                <input type="text" id="minAmount" name="minAmount" 
                       value="<c:if test='${serviceItemToAdd.getMinAmount() != null}'><fmt:formatNumber value='${serviceItemToAdd.getMinAmount()}' type='number' groupingUsed='true' minFractionDigits='0' maxFractionDigits='0'/></c:if>"
                           placeholder="1.000.000"
                           pattern="^([1-9][0-9]{0,11}|[1-9][0-9]{0,2}(\.[0-9]{3}){0,3})$"
                           title="Số tiền tối thiểu phải là số nguyên dương từ 1 đến 999.999.999.999 VNĐ. Có thể nhập không dấu chấm (1000000) hoặc có dấu chấm (1.000.000).">
                </div>


                <!-- Min Credit Score -->
                <div>
                    <label for="minCreditScore">Min Credit Score</label>
                    <input type="text" id="minCreditScore" name="minCreditScore" 
                            
                           value="${serviceItemToAdd.getMinCreditScore() != null && serviceItemToAdd.getMinCreditScore() != 0  ? serviceItemToAdd.getMinCreditScore() : ''}"
                    placeholder="300"
                    pattern="^[0-9]{1,3}$"
                    maxlength="3"
                    title="Điểm tín dụng tối thiểu phải là số nguyên từ 0 đến 999."
                    >

            </div>


            <!-- Early Withdraw Rate -->
            <div>
                <div style="display:flex;">
                    <label for="earlyWithdrawRate">Early Withdraw Rate (%)</label>
                    <label for="earlyWithdrawRate" style="color: red; font-size: small; margin-left: 5px; display: inline;">(Saving only)</label>
                </div>
                <input type="text" id="earlyWithdrawRate" name="earlyWithdrawRate" 
                       value="${serviceItemToAdd.getEarlyWithdrawRate() != null && serviceItemToAdd.getEarlyWithdrawRate() != 0.0 ? serviceItemToAdd.getEarlyWithdrawRate() : ''}"
                       placeholder="0.5"
                       pattern="^[0-9]{1,2}(\.[0-9]{1,2})?$|^100(\.0{1,2})?$"
                       title="Tỷ lệ lãi rút trước hạn phải là số dương từ 0 đến 100%, tối đa 2 chữ số thập phân (ví dụ: 0.5, 2.75, 100)."
                       >

            </div>


            <!-- Interest Rate -->
            <div>
                <label for="interestRate">Interest Rate (%)</label>
                <input type="text" id="interestRate" name="interestRate" 
                       required value="${serviceItemToAdd.getInterestRate()}"
                       placeholder="5.5"
                       pattern="^[0-9]{1,2}(\.[0-9]{1,2})?$|^100(\.0{1,2})?$"
                       title="Lãi suất phải là số dương từ 0 đến 100%, tối đa 2 chữ số thập phân (ví dụ: 5.5, 7.25, 100)."
                       >

            </div>


            <!-- Type -->
            <div>
                <label for="type">Type</label>
                <select id="type" name="type" required onchange="validateType()">
                    <option value="" disabled selected>Choose Type</option>
                    <option value="Saving" ${serviceItemToAdd.getType() == 'Saving' ? 'selected' : ''}>Saving</option>
                    <option value="Secured Loan" ${serviceItemToAdd.getType() == 'Secured Loan' ? 'selected' : ''}>Secured Loan</option>
                    <option value="Unsecured Loan" ${serviceItemToAdd.getType() == 'Unsecured Loan' ? 'selected' : ''}>Unsecured Loan</option>
                </select>

            </div>


            <!-- Submit Button -->
            <input type="submit" value="ADD">
        </form>
    </div>



</body>




