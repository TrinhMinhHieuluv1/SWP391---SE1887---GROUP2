<%-- 
    Document   : createSavingRequest
    Created on : Mar 25, 2025, 3:10:34 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Saving Request</title>

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
                max-width: 1000px;
                border-radius: 12px;
                box-shadow: 0 8px 20px rgba(0, 0, 0, 0.15);
                animation: modalFadeIn 0.3s ease;
                display: flex;
                flex-direction: column;
                max-height: 90vh;
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
                position: sticky;
                top: 0;
                background-color: #ffffff;
                z-index: 1;
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
                flex: 1; /* Chiếm không gian còn lại giữa header và footer */
                overflow-y: auto; /* Chỉ body cuộn */
                overflow-x: hidden;
            }

            .modal-footer {
                display: flex;
                justify-content: flex-end;
                padding-top: 15px;
                border-top: 1px solid #eaeaea;
                position: sticky;
                bottom: 0;
                background-color: #ffffff;
                z-index: 1;
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
            .asset-preview, .salary-preview {
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
                display: none;
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
                                    <h2 class="mil-mb-40">Create Loan Request</h2>                                    
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
                                    <form action="create-saving-request" method="post" onsubmit="prepareSubmit(event)">
                                        <div id="Amount" class="form-section">
                                            <input class="mil-input mil-up mil-mb-15" id="AmountInput" type="text" placeholder="Amount to saving" name="Amount" required oninput="validateAmount()" onchange="resetServiceItem()">
                                            <div id="Amount-err-under" class="error-message">We don't provide a loan with amount smaller than 100.000!</div>
                                        </div>

                                        <div id="ServiceItem" class="form-section">
                                            <input id="ServiceItemInput" name="ServiceItem" class="mil-input mil-up mil-mb-15" placeholder="Select Service Item" required readonly onclick="showServiceItemModal()">
                                            <span class="fa fa-fw fa-info-circle field-icon" style="position: absolute; top: 40%; right: 10px; transform: translateY(-50%); cursor: pointer;" onclick="showServiceItemDetail()"></span>
                                        </div>

                                        <div id="ServiceItemDetail" class="detail-section">
                                            <div id="SIDetail-MinAmount" class="detail-item" style="grid-column: 2/5;"><b>Min Amount: </b></div>
                                            <div id="SIDetail-MinPeriod" class="detail-item" style="grid-column: 6/9;"><b>Min Period: </b></div>
                                            <div onclick="closeServiceItemDetail()" style="grid-column: 12/13;" class="fa fa-window-close field-icon"></div>
                                            <div id="SIDetail-EarlyWithdrawRate" class="detail-item" style="grid-column: 2/5;"><b>Early Withdraw Rate: </b></div>
                                            <div id="SIDetail-InterestRate" class="detail-item" style="grid-column: 6/9;"><b>Interest Rate: </b></div>
                                        </div>

                                        <div id="Period" class="form-section" style="display: none;">
                                            <input id="PeriodInput" name="Period" type="number" class="mil-input mil-up mil-mb-15" placeholder="Period" required oninput="validatePeriod()">
                                            <div id="Period-err" class="error-message">Your period must be a number and bigger than <span id="MinPeriod"><fmt:formatNumber value="" pattern="#.###"/></span></div>
                                        </div>

                                        <div id="Description" class="form-section" style="display: none;">
                                            <input id="DescriptionInput" name="Description" type="text" class="mil-input mil-up mil-mb-15" placeholder="Description">
                                        </div>                                    

                                        <div class="button-group">
                                            <a href="/timibank/home" class="btn btn-secondary">Back to Home</a>
                                            <button type="submit" class="btn btn-primary" onclick="prepareSubmit()">Create Request</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Service Item Modal-->
                <div id="serviceItemModal" class="modal" style="display: none">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h1 class="modal-title" id="serviceItemModalTitle">Service Item</h1>
                            <span class="close-modal" onclick="closeModal('serviceItemModal')">&times;</span>
                        </div>
                        <div class="modal-body">
                            <table id="serviceitemtabledata">
                            </table>                  
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-secondary" style="max-width: 100px" onclick="closeModal('serviceItemModal')">Cancel</button>
                            <button class="btn btn-primary" style="max-width: 100px" onclick="getChoosenServiceItem()">OK</button>
                        </div>
                    </div>
                </div>

                <%@include file="footer.jsp" %>
            </div>
        </div>
        <script>
            var MinPeriod = 0;
            function updateURLParameter(param, value) {
                let url = new URL(window.location.href);
                let params = new URLSearchParams(url.search);

                params.set(param, value);

                window.location.href = 'create-saving-request?' + params.toString();
            }

            function prepareSubmit(event) {
                if (validateAmount() === false) {
                    event.preventDefault();
                    alert('Your amount is invalid. Try again!');
                    return;
                }
                if (validatePeriod() === false) {
                    event.preventDefault();
                    alert('Your period is invalid. Try again!');
                    return;
                }
                if (document.getElementById('ServiceItemInput').value === '') {
                    event.preventDefault();
                    alert('You must choose one service item to continue!. Try again!');
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


            function validateAmount() {
                const Amount = document.getElementById('AmountInput');
                const Amount_err = document.getElementById('Amount-err');
                const MaxAmount = document.getElementById('MaxAmount');
                Amount.value = Amount.value.replace(/[^0-9]/g, '');
                if (Amount.value !== null && Amount.value.trim() !== '') {
                    Amount.value = parseInt(Amount.value).toLocaleString('de-DE');
                }
                if (parseInt(Amount.value.replaceAll('.', '')) < 100000)
                {
                    document.getElementById('Amount-err-under').style.display = 'block';
                    return false;
                } else {
                    document.getElementById('Amount-err-under').style.display = 'none';
                    return true;
                }
            }
            ;
            //Amount end

            //Service Item start
            function showServiceItemModal() {
                const modal = document.getElementById('serviceItemModal');
                const Amount = document.getElementById('AmountInput').value;
                if (Amount === '') {
                    alert('Please enter amount of loan before!');
                    return;
                }
                modal.style.display = 'block';
                modal.style.opacity = '1';
                modal.style.visibility = 'visible';
            }
            ;

            function getServiceItemList() {
                $.ajax({
                    url: '/timibank/get-service-item-list',
                    type: 'GET',
                    data: {
                        Type: 'Saving',
                        Amount: document.getElementById('AmountInput').value
                    },
                    success: function (response) {
                        var serviceitemtabledata = document.getElementById('serviceitemtabledata');
                        serviceitemtabledata.innerHTML = response;
                    }
                });
            }
            ;

            function resetServiceItem() {
                const choosenServiceItem = document.getElementById("ServiceItemInput");
                getServiceItemList();
                choosenServiceItem.value = '';

                document.getElementById("SIDetail-MinAmount").innerHTML = '<b>Min Amount: </b>';
                document.getElementById("SIDetail-MinPeriod").innerHTML = '<b>Min Period: </b>';
                document.getElementById("SIDetail-EarlyWithdrawRate").innerHTML = '<b>Early Withdraw Rate: </b>';
                document.getElementById("SIDetail-InterestRate").innerHTML = '<b>Interest Rate: </b>';
                document.getElementById('Period').style.display = 'none';
                document.getElementById('Description').style.display = 'none';
            }

            function getChoosenServiceItem() {
                const serviceItemList = document.getElementsByName("choosenServiceItem");
                const choosenServiceItem = document.getElementById("ServiceItemInput");
                let selectedServiceItem = '';
                var CustomerCreditScore = parseInt(${sessionScope.account.getCreditScore()});
                for (var si of serviceItemList) {
                    if (si.checked) {
                        if (CustomerCreditScore < si.value.split('-')[4]) {
                            alert('You can not use this service item because your credit score is not enough!');
                            si.checked = false;
                            return;
                        }
                        selectedServiceItem = si;
                        break;
                    }
                }
                closeModal("serviceItemModal");
                var ServiceItemID = selectedServiceItem.value.split('-')[0];
                var ServiceItemName = selectedServiceItem.value.split('-')[1];
                var MinAmount = selectedServiceItem.value.split('-')[2];
                MinPeriod = selectedServiceItem.value.split('-')[3];
                var EarlyWithdrawRate = selectedServiceItem.value.split('-')[4];
                var InterestRate = selectedServiceItem.value.split('-')[5];
                choosenServiceItem.value = ServiceItemID + '-' + ServiceItemName;
                document.getElementById("SIDetail-MinAmount").innerHTML = '<b>Min Amount: </b>' + parseInt(MinAmount.replaceAll('.', '')).toLocaleString('de-DE');
                document.getElementById("SIDetail-MinPeriod").innerHTML = '<b>Min Period: </b>' + MinPeriod;
                document.getElementById("SIDetail-EarlyWithdrawRate").innerHTML = '<b>Early Withdraw Rate: </b>' + EarlyWithdrawRate;
                document.getElementById("SIDetail-InterestRate").innerHTML = '<b>Interest Rate: </b>' + InterestRate;
                document.getElementById('Period').style.display = 'block';
                document.getElementById('Description').style.display = 'block';
            }

            function showServiceItemDetail() {
                const ServiceItemDetail = document.getElementById('ServiceItemDetail');
                ServiceItemDetail.style.display = 'grid';
            }

            function closeServiceItemDetail() {
                const ServiceItemDetail = document.getElementById('ServiceItemDetail');
                ServiceItemDetail.style.display = 'none';
            }
            //Service Item end

            //Period Start
            function validatePeriod() {
                const Period = document.getElementById('PeriodInput');
                const Period_err = document.getElementById('Period-err');
                const MinPeriodToShow = document.getElementById('MinPeriod');
                Period.value = Period.value.replace(/[^0-9]/g, '');
                if (parseInt(Period.value) < MinPeriod) {
                    Period_err.style.display = 'block';
                    MinPeriodToShow.innerHTML = MinPeriod;
                    return false;
                } else {
                    Period_err.style.display = 'none';
                    return true;
                }
            }
            ;
            //Period End

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

