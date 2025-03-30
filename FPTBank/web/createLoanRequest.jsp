<%-- 
    Document   : createLoanRequest
    Created on : Mar 5, 2025, 1:36:28 AM
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
                                    <div class="loan-type-selector">
                                        <h4 class="loan-type-option ${param.Type.equals('Secured') ? 'active' : ''}" onclick="updateURLParameter('Type', 'Secured')">Secured Loan</h4>
                                        <h4>|</h4>
                                        <h4 class="loan-type-option ${param.Type.equals('Unsecured') ? 'active' : ''}" onclick="updateURLParameter('Type', 'Unsecured')">Unsecured Loan</h4>
                                    </div>
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
                                    <form action="create-loan-request" method="post" onsubmit="prepareSubmit(event)">
                                        <input type="text" name="Type" value="${param.Type}" style="display: none">
                                        <c:if test="${param.Type.equals('Secured')}">

                                            <c:if test="${requestScope.assetList == null || requestScope.assetList.size() == 0}">
                                                <div class="text-center">
                                                    <h4>You don't have any available asset.</h4>
                                                    <h4>Please update <a href="/timibank/myassetsalary" style="color: #e53935;text-decoration: underline">Your Asset</a> or use <a style="color: #e53935; text-decoration: underline" href="/timibank/create-loan-request?Type=Unsecured">Unsecured Loan</a></h4>
                                                </div>
                                            </c:if>
                                            <c:if test="${requestScope.assetList != null && requestScope.assetList.size() != 0}">
                                                <div id="Asset" class="form-section">
                                                    <input id="AssetInput" name="Asset" class="mil-input mil-up mil-mb-15" placeholder="Select Asset" required readonly onclick="showAssetModal()">
                                                </div>
                                                <div id="AssetPreview" class="asset-preview" style="display: none;">
                                                    <div class="asset-image"><img id="AssetImage" src=""></div>
                                                    <div id="AssetTitle" class="asset-title"></div>
                                                    <div id="AssetValuationAmount" class="asset-value"></div>
                                                </div>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${param.Type.equals('Unsecured')}">
                                            <c:if test="${requestScope.salaryList == null || requestScope.salaryList.size() == 0}">
                                                <div class="text-center">
                                                    <h4>You don't have any available Salary.</h4>
                                                    <h4>Please update <a href="/timibank/mysalary" style="color: #e53935; text-decoration: underline">Your Salary</a> or use <a style="color: #e53935; text-decoration: underline" href="/timibank/create-loan-request?Type=Secured">Secured Loan</a></h4>
                                                </div>
                                            </c:if>
                                            <c:if test="${requestScope.salaryList != null && requestScope.salaryList.size() != 0}">
                                                <div id="Salary" class="form-section">
                                                    <input id="SalaryInput" name="Salary" class="mil-input mil-up mil-mb-15" placeholder="Select Salary" required readonly onclick="showSalaryModal()">
                                                </div>
                                                <div id="SalaryPreview" class="salary-preview" style="display: none;">
                                                    <div class="asset-image"><img id="SalaryImage" src=""></div>
                                                    <div id="SalaryTitle" class="asset-title"></div>
                                                    <div id="SalaryValuationAmount" class="asset-value"></div>
                                                </div>
                                            </c:if>
                                        </c:if>

                                        <div id="Amount" class="form-section">
                                            <input class="mil-input mil-up mil-mb-15" id="AmountInput" type="text" placeholder="Amount to loan" name="Amount" required oninput="validateAmount()" onchange="resetServiceItem(); resetInsurance();">
                                            <span class="fa fa-fw fa-info-circle field-icon" style="position: absolute; top: 40%; right: 10px; transform: translateY(-50%); cursor: pointer;" onclick="showLoanTermModal()"></span>
                                            <div id="Amount-err" class="error-message">Your loan amount must be a number and smaller than <span id="MaxAmount"><fmt:formatNumber value="" pattern="#.###"/></span></div>
                                            <div id="Amount-err-under" class="error-message">We don't provide a loan with amount smaller than 100.000!</div>
                                        </div>

                                        <div id="ServiceItem" class="form-section">
                                            <input id="ServiceItemInput" name="ServiceItem" class="mil-input mil-up mil-mb-15" placeholder="Select Service Item" required readonly onclick="showServiceItemModal()">
                                            <span class="fa fa-fw fa-info-circle field-icon" style="position: absolute; top: 40%; right: 10px; transform: translateY(-50%); cursor: pointer;" onclick="showServiceItemDetail()"></span>
                                        </div>

                                        <div id="ServiceItemDetail" class="detail-section">
                                            <div id="SIDetail-MaxAmount" class="detail-item" style="grid-column: 2/5;"><b>Max Amount: </b></div>
                                            <div id="SIDetail-MaxPeriod" class="detail-item" style="grid-column: 6/9;"><b>Max Period: </b></div>
                                            <div onclick="closeServiceItemDetail()" style="grid-column: 12/13;" class="fa fa-window-close field-icon"></div>
                                            <div id="SIDetail-MinCreditScore" class="detail-item" style="grid-column: 2/5;"><b>Min Credit Score: </b></div>
                                            <div id="SIDetail-LatePaymentRate" class="detail-item" style="grid-column: 6/9;"><b>Late Payment Rate: </b></div>
                                            <div id="SIDetail-InterestRate" class="detail-item" style="grid-column: 10/13;"><b>Interest Rate: </b></div>
                                        </div>

                                        <div id="Period" class="form-section" style="display: none;">
                                            <input id="PeriodInput" name="Period" type="number" class="mil-input mil-up mil-mb-15" placeholder="Period" required oninput="validatePeriod()">
                                            <div id="Period-err" class="error-message">Your period must be a number and smaller than <span id="MaxPeriod"><fmt:formatNumber value="" pattern="#.###"/></span></div>
                                            <div id="Period-err-under" class="error-message">We don't provide a loan with period smaller than 1 month!</div>
                                        </div>

                                        <div id="Description" class="form-section" style="display: none;">
                                            <input id="DescriptionInput" name="Description" type="text" class="mil-input mil-up mil-mb-15" placeholder="Description">
                                        </div>

                                        <div id="MonthlyPayment" class="form-section" style="display: none;">
                                            <label class="checkbox-container">
                                                <input id="MonthlyPaymentInput" name="MonthlyPayment" type="checkbox" onchange="changeMonthlyPayment()"> 
                                                <span class="checkbox-text">Monthly Payment</span>
                                            </label>
                                            <div id="MonthlyPayment_err" class="error-message">You must choose a type of payment!</div>
                                        </div>

                                        <div id="MonthlyPaymentType" class="radio-group">
                                            <div class="radio-option">
                                                <input name="MonthlyPaymentType" type="radio" value="Fixed" id="fixed-payment"> 
                                                <label for="fixed-payment">Fixed Payment</label>
                                            </div>
                                            <div class="radio-option">
                                                <input name="MonthlyPaymentType" type="radio" value="Reducing" id="reducing-payment"> 
                                                <label for="reducing-payment">Reducing Balance Payment</label>
                                            </div>
                                        </div>

                                        <div id="Insurance" class="form-section" style="display: none;">
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
                                            <a href="/timibank/home" class="btn btn-secondary">Back to Home</a>
                                            <button type="submit" class="btn btn-primary" onclick="prepareSubmit()">Create Request</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Asset Modal-->
                <div id="assetModal" class="modal" style="display: none">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h1 class="modal-title" id="assetModalTitle">Asset</h1>
                            <span class="close-modal" onclick="closeModal('assetModal')">&times;</span>
                        </div>
                        <div class="modal-body">
                            <table id="assettabledata">
                                <tr>
                                    <th></th>
                                    <th style="width: 150px; text-align: center">Asset Image</th>
                                    <th>Asset Title</th>
                                    <th>Asset Value</th>
                                </tr>
                                <c:forEach items="${requestScope.assetList}" var="asset">
                                    <tr>
                                        <th><input type="radio" name="assetItem" value="${asset.getId()}*${asset.getTitle()}*${asset.getImage()}*${asset.getValuationAmount().intValue()}*"></th>
                                        <th style="width: 150px; text-align: center"><img style="width: 80px; height: 80px; object-fit: cover; border-radius: 8px;" src="${asset.getImage()}"></th>
                                        <th>${asset.getTitle()}</th>
                                        <th><fmt:formatNumber value="${asset.getValuationAmount()}" pattern="#,###"/></th>
                                    </tr>
                                </c:forEach>
                            </table>                  
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-secondary" style="max-width: 100px" onclick="closeModal('assetModal')">Cancel</button>
                            <button class="btn btn-primary" style="max-width: 100px" onclick="getChoosenAsset()">OK</button>
                        </div>
                    </div>
                </div>
                <%@include file="footer.jsp" %>

                <!-- Salary Modal-->
                <div id="salaryModal" class="modal" style="display: none">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h1 class="modal-title" id="salaryModalTitle">Salary</h1>
                            <span class="close-modal" onclick="closeModal('salaryModal')">&times;</span>
                        </div>
                        <div class="modal-body">
                            <table id="salarytabledata">
                                <tr>
                                    <th></th>
                                    <th style="width: 150px; text-align: center">Salary Image</th>
                                    <th>Salary Title</th>
                                    <th>Salary Value</th>
                                </tr>
                                <c:forEach items="${requestScope.salaryList}" var="salary">
                                    <tr>
                                        <th><input type="radio" name="salaryItem" value="${salary.getId()}*${salary.getTitle()}*${salary.getImage()}*${salary.getValuationAmount().intValue()}"></th>
                                        <th style="width: 150px; text-align: center"><img style="width: 80px; height: 80px; object-fit: cover; border-radius: 8px;" src="${salary.getImage()}"></th>
                                        <th>${salary.getTitle()}</th>
                                        <th><fmt:formatNumber value="${salary.getValuationAmount()}" pattern="#,###"/></th>
                                    </tr>
                                </c:forEach>
                            </table>                  
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-secondary" style="max-width: 100px" onclick="closeModal('salary')">Cancel</button>
                            <button class="btn btn-primary" style="max-width: 100px" onclick="getChoosenSalary()">OK</button>
                        </div>
                    </div>
                </div>

                <!-- Loan Term Modal -->
                <div id="loanTermModal" class="modal" style="display: none">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h1 class="modal-title" id="loanTermModalTitle">Loan Term</h1>
                            <span class="close-modal" onclick="closeModal('loanTermModal')">&times;</span>
                        </div>
                        <div class="modal-body">
                            <table>
                                <c:if test="${param.Type.equals('Unsecured')}">
                                    <tr>
                                        <th>Min Salary Value</th>
                                        <th>Number of salary available to loan</th>
                                    </tr>
                                    <c:forEach items="${requestScope.loanTermList}" var="loanTerm">
                                        <tr>
                                            <td><fmt:formatNumber value="${loanTerm.getMinSalary()}" pattern="###,###"/></td>
                                            <td>${loanTerm.getNumberOfSalary()}</td>
                                        </tr>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${param.Type.equals('Secured')}">
                                    <tr>
                                        <th>Min Asset Value</th>
                                        <th>Percent of asset available to loan</th>
                                    </tr>
                                    <c:forEach items="${requestScope.loanTermList}" var="loanTerm">
                                        <tr>
                                            <td><fmt:formatNumber value="${loanTerm.getMinAssetValue()}" pattern="###,###"/></td>
                                            <td>${loanTerm.getPercentOfAsset()}</td>
                                        </tr>
                                    </c:forEach>
                                </c:if>
                            </table>
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
            </div>
        </div>
        <script>
            var maxAmountForLoan = 0;
            var ServiceItemID = '';
            var ServiceItemName = '';
            var MaxAmount = 0;
            var MaxPeriod = 0;
            var MinCreditScore = 0;
            var LatePaymentRate = 0;
            var InterestRate = 0;

            window.onload = function () {
                if (${param.Type == null || param.Type == ''}) {
                    updateURLParameter('Type', 'Secured');
                } else {
                    if (${param.Type != null && param.Type.equals('Unsecured')}) {
                        if (${requestScope.salary == null}) {
                            document.getElementById('Amount').style.display = 'none';
                            document.getElementById('ServiceItem').style.display = 'none';
                            document.getElementsByClassName('button-group')[0].style.display = 'none';
                        }
                        ;
                        getMaxAmount('Unsecured', 0, ${requestScope.salary.getValuationAmount()});
                    } else {
                        document.getElementById('Amount').style.display = 'none';
                        document.getElementById('ServiceItem').style.display = 'none';
                        if (${requestScope.assetList.size() == 0}) {
                            document.getElementsByClassName('button-group')[0].style.display = 'none';
                        }
                    }
                }
            };

            function updateURLParameter(param, value) {
                let url = new URL(window.location.href);
                let params = new URLSearchParams(url.search);

                params.set(param, value);

                window.location.href = 'create-loan-request?' + params.toString();
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
                if (validateMonthlyPayment() === false) {
                    event.preventDefault();
                    alert('You must choose one payment type if you want to use monthly payment!. Try again!');
                    return;
                }
                if (document.getElementById('ServiceItemInput').value === '') {
                    event.preventDefault();
                    alert('You must choose one service item to continue!. Try again!');
                    return;
                }
                if (document.getElementById('InsuranceInput').value === '') {
                    event.preventDefault();
                    alert('You must choose one insurance to continue!. Try again!');
                    return;
                }

            }

            function showLoanTermModal() {
                const modal = document.getElementById('loanTermModal');
                modal.style.display = 'block';
                modal.style.opacity = '1';
                modal.style.visibility = 'visible';
            }
            ;

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

            //Asset start
            function showAssetModal() {
                const modal = document.getElementById('assetModal');
                modal.style.display = 'block';
                modal.style.opacity = '1';
                modal.style.visibility = 'visible';
            }

            function getChoosenAsset() {
                const ChoosenAsset = document.getElementById('AssetInput');
                const AssetList = document.getElementsByName('assetItem');
                var selectedAsset = '';
                for (var asset of AssetList) {
                    if (asset.checked) {
                        selectedAsset = asset;
                        break;
                    }
                }
                closeModal('assetModal');
                ChoosenAsset.value = selectedAsset.value.split('*')[0] + '-' + selectedAsset.value.split('*')[1];
                getMaxAmount('Secured', selectedAsset.value.split('*')[3], 0);
                document.getElementById("AssetImage").src = selectedAsset.value.split('*')[2];
                document.getElementById("AssetTitle").innerHTML = selectedAsset.value.split('*')[1];
                document.getElementById("AssetValuationAmount").innerHTML = parseInt(selectedAsset.value.split('*')[3]).toLocaleString('de-DE');
                document.getElementById('AssetPreview').style.display = 'grid';
                document.getElementById('Amount').style.display = 'block';
                document.getElementById('ServiceItem').style.display = 'block';
                document.getElementsByClassName('button-group')[0].style.display = 'block';
            }
            //Asset end

            //Salary start
            function showSalaryModal() {
                const modal = document.getElementById('salaryModal');
                modal.style.display = 'block';
                modal.style.opacity = '1';
                modal.style.visibility = 'visible';
            }

            function getChoosenSalary() {
                const ChoosenSalary = document.getElementById('SalaryInput');
                const SalaryList = document.getElementsByName('salaryItem');
                var selectedSalary = '';
                for (var salary of SalaryList) {
                    if (salary.checked) {
                        selectedSalary = salary;
                        break;
                    }
                }
                closeModal('salaryModal');
                ChoosenSalary.value = selectedSalary.value.split('*')[0] + '-' + selectedSalary.value.split('*')[1];
                getMaxAmount('Unsecured', 0, selectedSalary.value.split('*')[3]);
                document.getElementById("SalaryImage").src = selectedSalary.value.split('*')[2];
                document.getElementById("SalaryTitle").innerHTML = selectedSalary.value.split('*')[1];
                document.getElementById("SalaryValuationAmount").innerHTML = parseInt(selectedSalary.value.split('*')[3]).toLocaleString('de-DE');
                document.getElementById('SalaryPreview').style.display = 'grid';
                document.getElementById('Amount').style.display = 'block';
                document.getElementById('ServiceItem').style.display = 'block';
                document.getElementsByClassName('button-group')[0].style.display = 'block';
            }
            //Salary end

            //Amount start
            function getMaxAmount(Type, Asset, Salary) {
                $.ajax({
                    url: '/timibank/get-max-amount',
                    type: 'POST',
                    data: {
                        Type: Type,
                        Asset: Asset,
                        Salary: Salary
                    },
                    success: function (response) {
                        maxAmountForLoan = parseInt(response);
                    }
                });
            }
            ;

            function validateAmount() {
                const Amount = document.getElementById('AmountInput');
                const Amount_err = document.getElementById('Amount-err');
                const MaxAmount = document.getElementById('MaxAmount');
                Amount.value = Amount.value.replace(/[^0-9]/g, '');
                if (Amount.value !== null && Amount.value.trim() !== '') {
                    Amount.value = parseInt(Amount.value).toLocaleString('de-DE');
                }
                if (parseInt(Amount.value.replaceAll('.', '')) > maxAmountForLoan) {
                    Amount_err.style.display = 'block';
                    MaxAmount.innerText = maxAmountForLoan.toLocaleString('de-DE');
                    return false;
                } else if (parseInt(Amount.value.replaceAll('.', '')) < 100000)
                {
                    document.getElementById('Amount-err-under').style.display = 'block';
                    return false;
                } else {
                    Amount_err.style.display = 'none';
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
                        Type: '${param.Type}',
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
                ServiceItemID = '';
                ServiceItemName = '';
                MaxAmount = '';
                MaxPeriod = '';
                MinCreditScore = '';
                LatePaymentRate = '';
                InterestRate = '';
                document.getElementById("SIDetail-MaxAmount").innerHTML = '<b>Max Amount: </b>';
                document.getElementById("SIDetail-MaxPeriod").innerHTML = '<b>Max Period: </b>';
                document.getElementById("SIDetail-MinCreditScore").innerHTML = '<b>Min Credit Score: </b>';
                document.getElementById("SIDetail-LatePaymentRate").innerHTML = '<b>Late Payment Rate: </b>';
                document.getElementById("SIDetail-InterestRate").innerHTML = '<b>Interest Rate: </b>';
                document.getElementById('Period').style.display = 'none';
                document.getElementById('Description').style.display = 'none';
                document.getElementById('MonthlyPayment').style.display = 'none';
                document.getElementById('Insurance').style.display = 'none';
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
                ServiceItemID = selectedServiceItem.value.split('-')[0];
                ServiceItemName = selectedServiceItem.value.split('-')[1];
                MaxAmount = selectedServiceItem.value.split('-')[2];
                MaxPeriod = selectedServiceItem.value.split('-')[3];
                MinCreditScore = selectedServiceItem.value.split('-')[4];
                LatePaymentRate = selectedServiceItem.value.split('-')[5];
                InterestRate = selectedServiceItem.value.split('-')[6];
                choosenServiceItem.value = ServiceItemID + '-' + ServiceItemName;
                document.getElementById("SIDetail-MaxAmount").innerHTML = '<b>Max Amount: </b>' + parseInt(MaxAmount).toLocaleString('de-DE');
                document.getElementById("SIDetail-MaxPeriod").innerHTML = '<b>Max Period: </b>' + MaxPeriod;
                document.getElementById("SIDetail-MinCreditScore").innerHTML = '<b>Min Credit Score: </b>' + MinCreditScore;
                document.getElementById("SIDetail-LatePaymentRate").innerHTML = '<b>Late Payment Rate: </b>' + LatePaymentRate;
                document.getElementById("SIDetail-InterestRate").innerHTML = '<b>Interest Rate: </b>' + InterestRate;
                document.getElementById('Period').style.display = 'block';
                document.getElementById('Description').style.display = 'block';
                document.getElementById('MonthlyPayment').style.display = 'block';
                document.getElementById('Insurance').style.display = 'block';
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
                const MaxPeriodToShow = document.getElementById('MaxPeriod');
                Period.value = Period.value.replace(/[^0-9]/g, '');
                if (parseInt(Period.value) > MaxPeriod) {
                    Period_err.style.display = 'block';
                    MaxPeriodToShow.innerText = MaxPeriod.toLocaleString('de-DE');
                    return false;
                } else if (parseInt(Period.value) < 1) {
                    document.getElementById('Period-err-under').style.display = 'block';
                    return false;
                } else {
                    Period_err.style.display = 'none';
                    document.getElementById('Period-err-under').style.display = 'none';
                    if (Period.value !== null && Period.value.trim() !== '') {
                        Period.value = parseInt(Period.value).toLocaleString('de-DE');
                    }
                    return true;
                }
            }
            ;
            //Period End

            //Monthly Payment Start
            function changeMonthlyPayment() {
                const MonthlyPayment = document.getElementById('MonthlyPaymentInput');
                const MonthlyPaymentType = document.getElementById('MonthlyPaymentType');
                if (MonthlyPayment.checked) {
                    console.log('abc');

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
                        Type: '${param.Type}',
                        Amount: document.getElementById('AmountInput').value
                    },
                    success: function (response) {
                        var insurancetabledata = document.getElementById('insurancetabledata');
                        insurancetabledata.innerHTML = response;
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
