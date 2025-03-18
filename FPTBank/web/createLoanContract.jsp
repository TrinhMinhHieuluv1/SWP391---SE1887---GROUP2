<%-- 
    Document   : createLoanContract
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
        <title>Create Loan Contract</title>

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
                background-color: #fefefe;
                margin: 5% auto;
                padding: 20px;
                border: 1px solid #888;
                width: 80%;
                max-width: 800px;
                border-radius: 8px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            }

            .modal-header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 20px;
                padding-bottom: 10px;
                border-bottom: 1px solid #eee;
            }

            .modal-title {
                color: #2e7d32;
                font-size: 1.5em;
                margin: 0;
            }

            .close-modal {
                color: #aaa;
                font-size: 28px;
                font-weight: bold;
                cursor: pointer;
                transition: color 0.3s ease;
            }

            .close-modal:hover {
                color: #666;
            }

            .modal-body {
                margin-bottom: 20px;
                display: flex;
                gap: 20px;
                /*Modal Styles end*/
            }
        </style>
        <script>
            function updateURLParameter(param, value) {
                let url = new URL(window.location.href);
                let params = new URLSearchParams(url.search);

                params.set(param, value);

                window.location.href = 'create-loan-contract?' + params.toString();
            }
        </script> 
    </head>
    <body>
        <%@include file="header.jsp" %>

        <!-- banner -->
        <div class="mil-banner mil-banner-inner mil-dissolve">
            <div class="container">
                <div class="row align-items-center justify-content-center">
                    <div class="col-xl-8">
                        <div class="mil-banner-text mil-text-center">
                            <h2 class="mil-mb-40" style="font-family: serif">Create Loan Contract</h2>
                            <div style="display: flex; justify-content: space-around">
                                <h4 style="cursor: pointer" onclick="updateURLParameter('Type', 'Secured')">Secured Loan</h4>
                                <h4>|</h4>
                                <h4 style="cursor: pointer" onclick="updateURLParameter('Type', 'Unsecured')">Unsecured Loan</h4>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- banner end -->

        <div class="mil-blog-list mip-p-0-160">
            <div class="container">
                <div class="row justify-content-center" style="background-color: #ccffcc; padding: 50px">
                    <div class="col-xl-10">
                        <form action="create-loan-contract" method="post">
                            <input type="text" name="Type" value="${param.Type}" style="display: none">
                            <c:if test="${param.Type.equals('Secured')}">
                                <c:if test="${requestScope.assetList == null || requestScope.assetList.size() == 0}">
                                    <h4 style="text-align: center">You don't have any available asset.</h4>
                                    <h4 style="text-align: center">Please update <a href="#" style="color: red">Your Asset</a> or use <a style="color: red" href="/timibank/create-loan-contract?Type=Unsecured">Unsecured Loan</a></h4>
                                </c:if>
                                <c:if test="${requestScope.assetList != null && requestScope.assetList.size() != 0}">
                                    <div style="position: relative; display: inline-block; width: 100%;">
                                        <input style="width: 100%; padding-right: 40px; box-sizing: border-box;" class="mil-input mil-up mil-mb-15" id="AssetID" type="text" placeholder="Please choose asset!" name="AssetID" required readonly>
                                        <button style="position: absolute; top: 40%; right: 10px;transform: translateY(-50%);cursor: pointer;color: #666;">Select Asset</button>
                                    </div>
                                    <div id="Asset-Preview" style="display: none; grid-template-columns: repeat(12, 1fr);">
                                        <div style="grid-column: span 3"><img id="Asset-Preview-Image" style="width: 150px; height: 150px;" src=""></div>
                                        <div id="Asset-Preview-Name" style="grid-column: span 6; display: flex; align-items: center; justify-content: center"></div>
                                        <div id="Asset-Preview-Value" style="grid-column: span 3; display: flex; align-items: center; justify-content: center"></div>
                                        <button style="position: absolute; top: 40%; right: 10px;transform: translateY(-50%);cursor: pointer;color: #666;">Select Asset</button>
                                    </div>

                                    <!--        st.setInt(1, contractToAdd.getCustomer().getCustomerId());
                                                st.setInt(3, contractToAdd.getPeriod());
                                                st.setFloat(4, contractToAdd.getLatePaymentRate());
                                                st.setFloat(5, contractToAdd.getEarlyWithdrawRate());
                                                st.setFloat(6, contractToAdd.getInterestRate());
                                                st.setString(8, contractToAdd.getDescription());
                                                st.setInt(9, contractToAdd.getAsset().getId());    
                                                st.setInt(11, contractToAdd.getInsurance().getInsuranceID());
                                                st.setBoolean(12, contractToAdd.isMonthlyPayment());
                                                st.setString(13, contractToAdd.getMonthlyPaymentType());
                                                st.setInt(14, contractToAdd.getStatusID());-->
                                </c:if>
                            </c:if>
                            <c:if test="${param.Type.equals('Unsecured')}">
                                <c:if test="${requestScope.salary == null}">
                                    <h4 style="text-align: center">Your salary is not available.</h4>
                                    <h4 style="text-align: center">Please update <a href="#" style="color: red">Your Salary</a> or use <a style="color: red" href="/timibank/create-loan-contract?Type=Secured">Secured Loan</a></h4>
                                </c:if>
                                <c:if test="${requestScope.salary != null}">
                                    <label for="Salary" style="">Your Salary</label>
                                    <div id="Salary" style="display: grid; grid-template-columns: repeat(12, 1fr); margin-top: 20px; background-color: white">
                                        <input type="text" name="SalaryInput" style="display: none" value="${requestScope.salary.getId()}">
                                        <div style="grid-column: span 3"><img id="Salary-Image" style="width: 150px; height: 150px;" src="${requestScope.salary.getImage()}"></div>
                                        <div id="Salary-Name" style="grid-column: span 6; display: flex; align-items: center; justify-content: center">${requestScope.salary.getTitle()}</div>
                                        <div id="Salary-Value" style="grid-column: span 3; display: flex; align-items: center; justify-content: center"><fmt:formatNumber value="${requestScope.salary.getValuationAmount()}" pattern="###,###" type="number"/></div>
                                    </div>

                                    <div id="Amount" style="position: relative; display: inline-block; width: 100%; margin-top: 20px">
                                        <input style="width: 100%; padding-right: 40px; box-sizing: border-box;" class="mil-input mil-up mil-mb-15" id="AmountInput" type="text" placeholder="Amount to loan" name="AmountInput" required oninput="validateAmount()">
                                        <span style="position: absolute; top: 40%; right: 10px;transform: translateY(-50%);cursor: pointer;color: #666;" class="fa fa-fw fa-info-circle field-icon" onclick="showLoanTermModal()"></span>
                                    </div>
                                    <div id="Amount-err" style="color: red; display: none">Your loan amount must be a number and smaller than <span id="MaxAmount"><fmt:formatNumber value="" pattern="###,###"/></span></div>

                                    <div id="ServiceItem" style="position: relative; display: inline-block; width: 100%; margin-top: 20px">
                                        <input id="ServiceItemInput" name="ServiceItem" class="mil-input mil-up mil-mb-15" placeholder="Select Service Item" readonly required onclick="showServiceItemModal()">
                                    </div>

                                </c:if>
                            </c:if>





                            <!--                            <div id="err-username" style="color: red; display: none">Username phải từ 6-20 ký tự, chỉ chứa chữ cái, số và các ký tự, không được có dấu cách!</div>
                                                        <div id="duplicated-username" style="color: red; display: none">Username already exists. Please choose another!</div>
                                                        <div style="position: relative; display: inline-block; width: 100%;">
                                                            <input style="width: 100%; padding-right: 40px; box-sizing: border-box;" class="mil-input mil-up mil-mb-15" id="password" type="password" placeholder="Password" name="password" required oninput="validatePassword()">
                                                            <span style="position: absolute; top: 40%; right: 10px;transform: translateY(-50%);cursor: pointer;color: #666;" toggle="#password" class="fa fa-fw fa-info-circle field-icon toggle-password" onclick="togglePassword('password')"></span>
                                                        </div>
                                                        <div id="err-password" style="color: red; display: none">Mật khẩu phải có 8-16 ký tự, chứa ít nhất một chữ hoa, một chữ thường, một số và một ký tự đặc biệt!</div>
                                                        <div style="position: relative; display: inline-block; width: 100%;">
                                                            <input style="width: 100%; padding-right: 40px; box-sizing: border-box;" type="password" id="confirm-password" class="mil-input mil-up mil-mb-15" placeholder="Confirm Password" name="confirm-password" oninput="checkConfirmPassword()" required>
                                                            <span style="position: absolute; top: 40%; right: 10px;transform: translateY(-50%);cursor: pointer;color: #666;" toggle="#confirm-password" class="fa fa-fw fa-info-circle field-icon toggle-password" onclick="togglePassword('confirm-password')" style=""></span>
                                                        </div>
                                                        <div id="err-confirm-password" style="color: red; display: none">Confirm Password is incorrect. Try again!</div>
                            
                                                        <input type="text" class="mil-input mil-up mil-mb-15" placeholder="Full Name" name="name" required>
                                                        <input type="text" style="width: 100%; padding-right: 40px; box-sizing: border-box;" class="mil-input mil-up mil-mb-15" id="image" name="image" placeholder="Image"
                                                               class="form-control" onchange="updateImagePreview(this.value)">
                                                        <img id="imagePreview" src="" class="image-preview">
                                                        <select name="gender" class="mil-input mil-up mil-mb-15" required>
                                                            <option value="" disabled selected hidden style="color: black">Gender</option>
                                                            <option value="Male" ${requestScope.gender.equals("Male")?"selected":""}>Male</option>
                                                            <option value="Female" ${requestScope.gender.equals("Female")?"selected":""}>Female</option>
                                                        </select>
                                                        <input type="date" class="mil-input mil-up mil-mb-15" name="dob" placeholder="Date of birth">
                                                        <input type="text" class="mil-input mil-up mil-mb-15" name="phone" placeholder="Phone" id="phone" oninput="validatePhone(); checkPhone()" required>
                                                        <div id="err-phone" style="color: red; display: none">Phone has to have 10 digits and start with 0. Try again!</div>
                                                        <div id="duplicated-phone" style="color: red; display: none">Phone already exists. Please choose another!</div>
                                                        <input type="text" class="mil-input mil-up mil-mb-15" name="address" placeholder="Address">
                                                        <input type="text" class="mil-input mil-up mil-mb-15" name="CCCD" placeholder="CCCD" id="CCCD" oninput="validateCCCD(); checkCCCD()" required>
                                                        <div id="err-cccd" style="color: red; display: none">CCCD has to have 12 digits. Try again!</div>
                                                        <div id="duplicated-cccd" style="color: red; display: none">CCCD already exists. Please choose another!</div>
                                                        <input required type="checkbox"> <p class="mil-text-xs mil-soft" style="margin-bottom: 10px; display: inline">Do you agree to <a href="/timibank/TermAndService/TermAndService.pdf" target="_blank" class="mil-accent">our terms and conditions</a>.</p>
                                                        <div class="mil-up mil-mb-30">
                                                            <button type="submit" class="mil-btn mil-md mil-fw">Create Account</button>
                                                        </div>
                                                        <p class="mil-text-xs mil-text-center mil-soft mil-mb-30">If you already have an account:</p>
                                                        <div class="mil-up mil-mb-15">
                                                            <a href="/timibank/login" class="mil-btn mil-md mil-grey mil-fw mil-mb-30">Log in</a>
                                                        </div>-->
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- Loan Term Modal -->
        <div id="loanTermModal" class="modal" style="display: none">
            <div class="modal-content">
                <div class="modal-header" style="text-align: center">
                    <h1 class="modal-title" id="loanTermModalTitle" style="text-align: center">Loan Term</h1>
                    <span class="close-modal" onclick="closeModal('loanTermModal')">&times;</span>
                </div>
                <div class="modal-body">
                    <table style="width: 80%;">
                        <c:if test="${param.Type.equals('Unsecured')}">
                            <tr>
                                <th>Salary Value</th>
                                <th>Number of salary available to loan</th>
                            </tr>
                            <c:forEach items="${requestScope.loanTermList}" var="loanTerm">
                                <tr>
                                    <th><fmt:formatNumber value="${loanTerm.getMinSalary()}" pattern="###,###"/></th>
                                    <th>${loanTerm.getNumberOfSalary()}</th>
                                </tr>
                            </c:forEach>
                        </c:if>
                        <c:if test="${param.Type.equals('Secured')}">
                            <tr>
                                <th>Asset Value</th>
                                <th>Percent of asset available to loan</th>
                            </tr>
                            <c:forEach items="${requestScope.loanTermList}" var="loanTerm">
                                <tr>
                                    <th><fmt:formatNumber value="${loanTerm.getMinAssetValue()}" pattern="###.###"/></th>
                                    <th>${loanTerm.getPercentOfAsset()}</th>
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
                <div class="modal-header" style="text-align: center">
                    <h1 class="modal-title" id="serviceItemModalTitle" style="text-align: center">Service Item</h1>
                    <span class="close-modal" onclick="closeModal('serviceItemModal')">&times;</span>
                </div>
                <div class="modal-body" >
                    <table style="width: 100%; font-size: 15px" id="tabledata">
                    </table>                  
                </div>
                <div class="modal-footer">
                    <div style="display: flex; justify-content: right">
                        <button class="mil-btn mil-md mil-fw" style="background-color: #cccccc; color: black; width: 60px; height: 40px" onclick="closeModal('serviceItemModal')">Cancel</button>
                        <button class="mil-btn mil-md mil-fw" style="background-color: #33ff33; color: black; width: 60px; height: 40px; margin-left: 20px" onclick="getChoosenServiceItem()">OK</button>
                    </div>
                </div>
            </div>
        </div>
        <script>
            var maxAmount = 0;

            window.onload = function () {
                if (${param.Type.equals('Unsecured')}) {
                    getMaxAmount('Unsecured', 0, ${requestScope.salary.getValuationAmount()});
                }
                ;
            };

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
                        maxAmount = parseFloat(response);
                    }
                });
            }
            ;

            function validateAmount() {
                const Amount = document.getElementById('AmountInput');
                const Amount_err = document.getElementById('Amount-err');
                const MaxAmount = document.getElementById('MaxAmount');
                Amount.value = Amount.value.replace(/[^0-9]/g, '');
                if (parseInt(Amount.value) > maxAmount) {
                    Amount_err.style.display = 'block';
                    MaxAmount.innerText = new Intl.NumberFormat('en-US').format(maxAmount);
                } else {
                    Amount_err.style.display = 'none';
                }
                if (Amount.value !== null && Amount.value.trim() !== '') {
                    Amount.value = parseInt(Amount.value).toLocaleString('de-DE');
                }
            }
            ;
            //Amount end

            //Service Item start
            function showServiceItemModal() {
                const modal = document.getElementById('serviceItemModal');
                console.log('${param.Type}');
                console.log(document.getElementById('AmountInput').value);
                getServiceItemList();
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
                        var tabledata = document.getElementById('tabledata');
                        tabledata.innerHTML = response;
                        console.log('abc' + response);
                    }
                });
            }
            ;
            //Service Item end
        </script>
    </body>
</html>
