<%-- 
    Document   : footer
    Created on : Feb 11, 2025, 1:44:04 AM
    Author     : hungk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- footer -->
<footer class="mil-p-160-0 footer" id="footer">
    <div class="container">
        <div class="row mil-footer-top">
            <div class="col-xl-2">
                <a href="#." class=" mil-footer-logo mil-mb-60">
                    <img src="img/logo1.png" alt="Plax" width="150">
                </a>
            </div>
            <div class="col-xl-3 mil-mb-60">
                <h6 class="mil-mb-60">Liên kết hữu ích</h6>
                <ul class="mil-footer-list">
                    <li class="mil-text-m mil-soft mil-mb-15">
                        <a href="index.jsp">Home</a>
                    </li>
                    <li class="mil-text-m mil-soft mil-mb-15">
                        <a href="about.jsp">About Us</a>
                    </li>
                    <li class="mil-text-m mil-soft mil-mb-15">
                        <a href="contact.jsp">Contact Us</a>
                    </li>
                    <li class="mil-text-m mil-soft mil-mb-15">
                        <a href="faq.jsp">FAQs</a>
                    </li>
                    <li class="mil-text-m mil-soft mil-mb-15">
                        <a href="price.jsp">Pricing</a>
                    </li>
                </ul>
            </div>
            <div class="col-xl-3 mil-mb-60">
                <h6 class="mil-mb-60">Hỗ trợ</h6>
                <ul class="mil-footer-list">
                    <li class="mil-text-m mil-soft mil-mb-15" >
                        <a href="mailto:nguyenquangthoai04@gmail.com" class="modal__link">Email: nguyenquangthoai04@gmail.com</a> 
                    </li>
                    <li class="mil-text-m mil-soft mil-mb-15">
                        <a href="tel:0967368980" class="modal__link">Liên hệ: 0967368980</a>                                        </li>

                </ul>
            </div>

            <!--email-->

            <div class="col-xl-4 mil-mb-80">
                <h6 class="mil-mb-60">Gửi Hỗ trợ</h6>
                <p class="mil-text-xs mil-soft mil-mb-15">Hãy miêu tả những thứ bạn cần hỗ trợ </p>
                <form action="showfb" method="post" class="mil-subscripe-form-footer" id="form">
                    <!-- Email Input -->
                    <div class="mb-3">
                        <input class="form-control form-control__input mil-input" type="email" placeholder="Email" name="email" required>
                    </div>
                    <!-- Tiêu đề Input -->
                    <div class="mb-3">
                        <input  class="form-control form-control__input mil-input" type="text" placeholder="Tiêu đề" name="tieude" required>
                    </div>
                    <!-- Nội dung Input -->
                    <div class="mb-3">
                        <textarea class="form-control form-control__textarea" rows="3" placeholder="Nội dung" name="noidung" required></textarea>                                    
                    </div>
                            <!--mã cap cha-->
                    <div class="g-recaptcha" data-sitekey="6LcVf94qAAAAAHVxQmDpyNe7nzc7DEC88pgrmnb4"></div>
			<div id="error"></div>
                        
                        
                    <div class="form-control__row--reverse">
                        <!-- Submit Button -->
                        <button type="submit" class="form-control__btn form-control__btn--green mil-btn mil-ssm">
                            <i class="far fa-envelope-open form-control__icon"></i> Gửi
                        </button>
                        <!-- Checkbox -->
                        <div class="mil-checkbox-frame">
                            <div class="mil-checkbox">
                                <input type="checkbox" id="checkbox" checked>
                                <label for="checkbox"></label>
                            </div>
                            <p class="mil-text-xs mil-soft">Đăng ký để nhận tin tức mới nhất</p>

                        </div>
                    </div>

                    <script src="https://www.google.com/recaptcha/api.js" async defer></script>
                    <script>
                        window.onload = function () {
                            let isValid = false;
                            const form = document.getElementById("form");
                            const error = document.getElementById("error");

                            form.addEventListener("submit", function (event) {
                                event.preventDefault();
                                const response = grecaptcha.getResponse();
                                if (response) {
                                    form.submit();
                                } else {
                                    error.innerHTML = "Please check";
                                }
                            });
                        }
                    </script>


                </form>
            </div>


            <!--email-->

        </div>
        <div class="mil-footer-bottom">
            <div class="row">
                <div class="col-xl-6">
                    <p class="mil-text-s mil-soft">© 2025 TIMI Finance & Fintech Design</p>
                </div>
                <div class="col-xl-6">
                    <button type="button" class=" mil-btn mil-ssm footer--toggle--btn" onclick="toggleFooter()">
                        <span class="text-expand">Thu gọn chân trang</span>
                        <span class="text-collapsed" style="display: none">Mở rộng chân trang</span>
                        <span class="icon-arrow-right">

                        </span>
                    </button>
                </div>
            </div>
        </div>
    </div>
</footer>

<!-- footer end -->
