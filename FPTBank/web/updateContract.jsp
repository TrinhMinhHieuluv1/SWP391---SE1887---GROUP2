<%-- 
    Document   : updateContract
    Created on : Mar 19, 2025, 11:36:52 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Loan Request</title>

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

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>

        <style>
            /* Modal Styles */
            .modal {
                display: none;
                position: fixed;
                z-index: 10000;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                background-color: rgba(0, 0, 0, 0.5);
            }

            .modal-content {
                background-color: #ffffff;
                margin: 3% auto;
                padding: 30px;
                border: none;
                width: 80%;
                max-width: 800px;
                border-radius: 12px;
                box-shadow: 0 8px 20px rgba(0, 0, 0, 0.15);
                animation: modalFadeIn 0.3s ease;
            }

            @keyframes modalFadeIn {
                from {
                    opacity: 0;
                    transform: translateY(-20px);
                }
                to {
                    opacity: 1;
                    transform: translateY(0);
                }
            }

            .modal-header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 25px;
                padding-bottom: 15px;
                border-bottom: 1px solid #eaeaea;
            }

            .modal-title {
                color: #2e7d32;
                font-size: 1.8em;
                margin: 0;
                font-weight: 600;
            }

            .close-modal {
                color: #888;
                font-size: 28px;
                font-weight: bold;
                cursor: pointer;
                transition: color 0.3s ease, transform 0.2s ease;
            }

            .close-modal:hover {
                color: #333;
                transform: scale(1.1);
            }

            .modal-body {
                margin-bottom: 25px;
                display: flex;
                gap: 20px;
            }

            .modal-footer {
                display: flex;
                justify-content: flex-end;
                padding-top: 15px;
                border-top: 1px solid #eaeaea;
            }
            /*Modal Styles end*/

            /*Button Styles start*/
            .button-group {
                display: flex;
                gap: 20px;
                margin-top: 40px;
            }

            .btn {
                padding: 12px 24px;
                border: none;
                border-radius: 8px;
                cursor: pointer;
                font-weight: 600;
                text-decoration: none;
                text-align: center;
                transition: all 0.3s ease;
                font-size: 16px;
                letter-spacing: 0.5px;
            }

            .btn-primary {
                background-color: #4caf50;
                color: white;
                flex: 1;
                box-shadow: 0 4px 8px rgba(76, 175, 80, 0.2);
            }

            .btn-primary:hover {
                background-color: #43a047;
                transform: translateY(-3px);
                box-shadow: 0 6px 12px rgba(76, 175, 80, 0.3);
            }

            .btn-secondary {
                background-color: #f8f9fa;
                color: #333;
                flex: 1;
                border: 1px solid #e0e0e0;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.05);
            }

            .btn-secondary:hover {
                background-color: #e9ecef;
                transform: translateY(-3px);
                box-shadow: 0 6px 12px rgba(0, 0, 0, 0.1);
            }
            /*Button Styles end*/

            /* Form Styles */
            .loan-form-container {
                background-color: #f0f9f0;
                border-radius: 16px;
                padding: 40px;
                box-shadow: 0 8px 30px rgba(0, 0, 0, 0.08);
            }

            .mil-input {
                border: 1px solid #ddd;
                border-radius: 8px;
                padding: 14px 16px;
                transition: all 0.3s ease;
                font-size: 16px;
                background-color: #ffffff;
            }

            .mil-input:focus {
                border-color: #4caf50;
                box-shadow: 0 0 0 3px rgba(76, 175, 80, 0.2);
                outline: none;
            }

            .field-icon {
                font-size: 18px;
                transition: color 0.3s ease;
            }

            .field-icon:hover {
                color: #4caf50;
            }

            .form-section {
                position: relative;
                display: inline-block;
                width: 100%;
                margin-top: 24px;
            }

            .error-message {
                color: #e53935;
                font-size: 14px;
                margin-top: 5px;
                display: none;
            }

            /* Asset/Salary Display */
            .asset-preview, .salary-display {
                display: grid;
                grid-template-columns: repeat(12, 1fr);
                background-color: white;
                border-radius: 10px;
                padding: 15px;
                margin-top: 15px;
                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.05);
                align-items: center;
            }

            .asset-image, .salary-image {
                grid-column: span 3;
                display: flex;
                justify-content: center;
            }

            .asset-image img, .salary-image img {
                width: 120px;
                height: 120px;
                object-fit: cover;
                border-radius: 8px;
                border: 1px solid #eaeaea;
            }

            .asset-title, .salary-name {
                grid-column: span 6;
                display: flex;
                align-items: center;
                justify-content: center;
                font-size: 18px;
                font-weight: 500;
            }

            .asset-value, .salary-value {
                grid-column: span 3;
                display: flex;
                align-items: center;
                justify-content: center;
                font-size: 18px;
                font-weight: 600;
                color: #2e7d32;
            }

            /* Detail Sections */
            .detail-section {
                padding: 20px;
                display: none;
                grid-template-columns: repeat(12, 1fr);
                row-gap: 20px;
                margin-top: 20px;
                background-color: white;
                border-radius: 10px;
                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.05);
            }

            .detail-item {
                font-size: 15px;
                padding: 12px 15px;
                border: 1px solid #e0e0e0;
                border-radius: 8px;
                background-color: #f8f9fa;
                transition: all 0.3s ease;
            }

            .detail-item:hover {
                border-color: #4caf50;
                background-color: #f1f8e9;
            }

            .detail-item b {
                color: #2e7d32;
            }

            /* Tables */
            table {
                width: 100%;
                border-collapse: collapse;
            }

            table th, table td {
                padding: 12px 15px;
                text-align: left;
                border-bottom: 1px solid #eaeaea;
            }

            table th {
                background-color: #f1f8e9;
                color: #2e7d32;
                font-weight: 600;
            }

            table tr:hover {
                background-color: #f9f9f9;
            }

            /* Banner */
            .mil-banner-inner {
                background-color: #f1f8e9;
                padding: 60px 0;
            }

            .loan-type-selector {
                display: flex;
                justify-content: space-around;
                margin-top: 20px;
                background-color: white;
                border-radius: 50px;
                padding: 5px;
                box-shadow: 0 4px 15px rgba(0, 0, 0, 0.05);
                max-width: 1000px;
                margin-left: auto;
                margin-right: auto;
            }

            .loan-type-option {
                cursor: pointer;
                padding: 10px 25px;
                border-radius: 50px;
                transition: all 0.3s ease;
                font-weight: 500;
            }

            .loan-type-option:hover {
                color: #4caf50;
            }

            .loan-type-option.active {
                background-color: #4caf50;
                color: white;
            }

            .radio-group {
                margin: 10px 50px;
            }

            .radio-option {
                display: flex;
                align-items: center;
                margin-bottom: 10px;
            }

            .radio-option input {
                margin-right: 10px;
            }
        </style>
    </head>
    <body>
        <div id="smooth-wrapper" class="mil-wrapper">
            <%@include file="header.jsp" %>
            <div id="smooth-content" style="margin-top: 50px">

                <!-- banner -->
                <div class="mil-banner mil-banner-inner mil-dissolve">
                    <div class="container">
                        <div class="row align-items-center justify-content-center">
                            <div class="col-xl-8">
                                <div class="mil-banner-text mil-text-center">
                                    <h2 class="mil-mb-40">Update Contract</h2>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- banner end -->

                <div class="mil-blog-list mip-p-0-160">
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-xl-10">
                                <div class="loan-form-container">
                                    <form action="update-contract-for-customer" method="post" onsubmit="prepareSubmit(event)">
                                        <input type="text" name="ContractID" value="${requestScope.contractToUpdate.getContractID()}" style="display: none">
                                        <div id="Amount" class="form-section" style="width: 40%">
                                            <input class="mil-input mil-up mil-mb-15" id="AmountInput" type="text" placeholder="Amount to loan" name="Amount" required readonly>
                                        </div>

                                        <div id="Period" class="form-section" style="width: 40%; margin-left: 50px">
                                            <input id="PeriodInput" name="Period" type="number" class="mil-input mil-up mil-mb-15" placeholder="Period" required readonly>
                                        </div>

                                        <div id="Description" class="form-section">
                                            <input id="DescriptionInput" name="Description" type="text" class="mil-input mil-up mil-mb-15" placeholder="Description">
                                        </div>

                                        <div id="MonthlyPayment" class="form-section">
                                            <label class="checkbox-container">
                                                <input id="MonthlyPaymentInput" name="MonthlyPayment" type="checkbox" onchange="changeMonthlyPayment()" ${requestScope.contractToUpdate.isMonthlyPayment()?"checked":""}> 
                                                <span class="checkbox-text">Monthly Payment</span>
                                            </label>
                                            <div id="MonthlyPayment_err" class="error-message">You must choose a type of payment!</div>
                                        </div>

                                        <div id="MonthlyPaymentType" class="radio-group" style="display: ${requestScope.contractToUpdate.isMonthlyPayment()?"block":"none"}">
                                            <div class="radio-option">
                                                <input name="MonthlyPaymentType" type="radio" value="Fixed" id="fixed-payment" ${(requestScope.contractToUpdate.getMonthlyPaymentType() != null && requestScope.contractToUpdate.getMonthlyPaymentType().equals("Fixed"))?"checked":""}> 
                                                <label for="fixed-payment">Fixed Payment</label>
                                            </div>
                                            <div class="radio-option">
                                                <input name="MonthlyPaymentType" type="radio" value="Reducing" id="reducing-payment" ${(requestScope.contractToUpdate.getMonthlyPaymentType() != null && requestScope.contractToUpdate.getMonthlyPaymentType().equals("Reducing Balance"))?"checked":""}> 
                                                <label for="reducing-payment">Reducing Balance Payment</label>
                                            </div>
                                        </div>

                                        <div id="Insurance" class="form-section">
                                            <input id="InsuranceInput" name="Insurance" class="mil-input mil-up mil-mb-15" placeholder="Select Insurance" required readonly onclick="showInsuranceModal()">
                                            <span class="fa fa-fw fa-info-circle field-icon" style="position: absolute; top: 40%; right: 10px; transform: translateY(-50%); cursor: pointer;" onclick="showInsuranceDetail()"></span>
                                        </div>

                                        <div id="InsuranceDetail" class="detail-section">
                                            <div id="IDetail-FeeRate" class="detail-item" style="grid-column: 1/3;"><b>Fee Rate: </b></div>
                                            <div id="IDetail-CoverageRate" class="detail-item" style="grid-column: 4/7;"><b>Coverage Rate: </b></div>
                                            <div id="IDetail-MaxAmountForLoan" class="detail-item" style="grid-column: 8/12;"><b>Max Amount For Loan: </b></div>
                                            <div onclick="closeInsuranceDetail()" style="grid-column: 12/13;" class="fa fa-window-close field-icon"></div>
                                        </div>

                                        <div class="button-group">
                                            <a href="/timibank/contract-management-for-customer" class="btn btn-secondary">Back to Contract Management</a>
                                            <button type="submit" class="btn btn-primary" onclick="prepareSubmit()">Update Request</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Insurance Modal-->
                <div id="insuranceModal" class="modal" style="display: none">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h1 class="modal-title" id="insuanceModalTitle">Insurance</h1>
                            <span class="close-modal" onclick="closeModal('insuranceModal')">&times;</span>
                        </div>
                        <div class="modal-body">
                            <table id="insurancetabledata">
                            </table>                  
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-secondary" style="max-width: 100px" onclick="closeModal('insuranceModal')">Cancel</button>
                            <button class="btn btn-primary" style="max-width: 100px" onclick="getChoosenInsurance()">OK</button>
                        </div>
                    </div>
                </div>

                <%@include file="footer.jsp" %>
            </div>
        </div>
        <script>
            window.onload = function () {
                document.getElementById('AmountInput').value = parseInt('${requestScope.contractToUpdate.getAmount()}').toLocaleString('de-DE');
                document.getElementById('PeriodInput').value = '${requestScope.contractToUpdate.getPeriod()}';
                document.getElementById('DescriptionInput').value = '${requestScope.contractToUpdate.getDescription()}';
                getInsuranceList();
            };
            function updateURLParameter(param, value) {
                let url = new URL(window.location.href);
                let params = new URLSearchParams(url.search);

                params.set(param, value);

                window.location.href = 'create-loan-request?' + params.toString();
            }

            function prepareSubmit(event) {

                if (validateMonthlyPayment() === false) {
                    event.preventDefault();
                    alert('You must choose one payment type if you want to use monthly payment!. Try again!');
                    return;
                }
                if (document.getElementById('InsuranceInput').value === '') {
                    event.preventDefault();
                    alert('You must choose one insurance to continue!. Try again!');
                    return;
                }

            }

            function closeModal(id) {
                const modal = document.getElementById(id);
                modal.style.display = 'none';
            }
            ;

            // Close modal when clicking outside of it
            window.onclick = function (event) {
                const modals = document.getElementsByClassName('modal');
                for (var m of modals) {
                    if (event.target === m)
                        m.style.display = 'none';
                }
            };

            //Monthly Payment Start
            function changeMonthlyPayment() {
                const MonthlyPayment = document.getElementById('MonthlyPaymentInput');
                const MonthlyPaymentType = document.getElementById('MonthlyPaymentType');
                if (MonthlyPayment.checked) {
                    MonthlyPaymentType.style.display = 'block';
                } else
                {
                    MonthlyPaymentType.style.display = 'none';
                }
            }

            function validateMonthlyPayment() {
                const MonthlyPayment = document.getElementById('MonthlyPaymentInput');
                const MonthlyPaymentType = document.getElementsByName('MonthlyPaymentType');
                const MonthlyPayment_err = document.getElementById('MonthlyPayment_err');
                if (MonthlyPayment.checked) {
                    for (var type of MonthlyPaymentType) {
                        if (type.checked) {
                            MonthlyPayment_err.style.display = 'none';
                            return true;
                        }
                    }
                    MonthlyPayment_err.style.display = 'block';
                    return false;
                } else
                {
                    return true;
                }
            }
            //Monthly Payment End

            //Insurance Start
            function showInsuranceModal() {
                const modal = document.getElementById('insuranceModal');
                modal.style.display = 'block';
                modal.style.opacity = '1';
                modal.style.visibility = 'visible';
            }

            function getInsuranceList() {
                $.ajax({
                    url: "/timibank/get-insurance-list",
                    type: 'GET',
                    data: {
                        Type: '${requestScope.contractToUpdate.getType().equals('Secured Loan')?'Secured':'Unsecured'}',
                        Amount: parseInt('${requestScope.contractToUpdate.getAmount()}')
                    },
                    success: function (response) {
                        var insurancetabledata = document.getElementById('insurancetabledata');
                        insurancetabledata.innerHTML = response;
                        const insuranceList = document.getElementsByName('choosenInsurance');
                        for (var i of insuranceList) {
                            if (i.value.split('-')[0] === '${requestScope.contractToUpdate.getInsurance().getInsuranceID()}') {
                                i.checked = true;
                            }
                        }
                        getChoosenInsurance();
                    }
                });
            }

            function resetInsurance() {
                const choosenInsurance = document.getElementById('InsuranceInput');
                choosenInsurance.value = '';
                getInsuranceList();
                document.getElementById("IDetail-FeeRate").innerHTML = '<b>Fee Rate: </b>';
                document.getElementById("IDetail-CoverageRate").innerHTML = '<b>Coverage Rate: </b>';
                document.getElementById("IDetail-MaxAmountForLoan").innerHTML = '<b>Max Amount For Loan: </b>';
            }

            function getChoosenInsurance() {
                const ChoosenInsurance = document.getElementById('InsuranceInput');
                const InsuranceList = document.getElementsByName('choosenInsurance');
                var selectedInsurance = '';
                for (var insurance of InsuranceList) {
                    if (insurance.checked) {
                        selectedInsurance = insurance;
                        break;
                    }
                }
                closeModal('insuranceModal');
                ChoosenInsurance.value = selectedInsurance.value.split('-')[0] + '-' + selectedInsurance.value.split('-')[1];
                document.getElementById("IDetail-FeeRate").innerHTML = '<b>Fee Rate: </b>' + selectedInsurance.value.split('-')[2];
                document.getElementById("IDetail-CoverageRate").innerHTML = '<b>Coverage Rate: </b>' + selectedInsurance.value.split('-')[3];
                document.getElementById("IDetail-MaxAmountForLoan").innerHTML = '<b>Max Amount For Loan: </b>' + selectedInsurance.value.split('-')[4];
            }

            function showInsuranceDetail() {
                document.getElementById('InsuranceDetail').style.display = 'grid';
            }

            function closeInsuranceDetail() {
                document.getElementById('InsuranceDetail').style.display = 'none';
            }
            //Insurance End
        </script>
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

