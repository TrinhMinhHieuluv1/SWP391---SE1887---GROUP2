<%-- 
    Document   : footer
    Created on : Feb 11, 2025, 1:44:04 AM
    Author     : hungk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- footer -->
<style>
    iframe {
        border: 5px solid #3498db; /* Viền màu xanh */
        border-radius: 10px; /* Bo góc */
        box-shadow: 5px 5px 15px rgba(0, 0, 0, 0.3); /* Đổ bóng */
    }
    .input{
        background-color: #fff;
    }
    .modal__content {
        padding: 20px;
        background-color: #f9f9f9;
        border-radius: 8px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        max-width: 500px;
        margin: auto;
    }

    .modal__close {
        float: right;
        font-size: 24px;
        cursor: pointer;
        background: none;
        border: none;
        color: #333;
    }

    .modal__close:hover {
        color: #000;
    }

    .modal__text {
        margin-bottom: 15px;
        font-size: 15px;
    }

    .modal__text label {
        display: block;
        font-weight: bold;
        margin-bottom: 5px;

    }

    .modal__text input,
    .modal__text select {
        width: 100%;
        padding: 8px;
        border: 1px solid #ccc;
        border-radius: 4px;
        font-size: 14px;
        color: #555;
    }

    .modal__text input:focus,
    .modal__text select:focus {
        border-color: #007bff;
        outline: none;
        box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
    }
    .btn {
        padding: 8px 12px;
        font-size: 14px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        transition: background-color 0.3s;
    }
    .btn-pending {
        background-color: green;
        color: white;
    }
    .btn-sent {
        background-color: white;
        color: black;
        border: 1px solid gray;
    }
    .btn:hover {
        opacity: 0.8;
    }
</style>
<footer class=" footer" id="footer"
        <c:if test="${sessionScope.account.getRoleID() == 4}"> style="background-color:#f9f586;" </c:if>
        <c:if test="${sessionScope.account.getRoleID() == 5}"> style="background-color: #f0fff0;" </c:if>  
        <c:if test="${sessionScope.account.getRoleID() == 3}"> style="background-color: #e2d1c3;" </c:if>  
        <c:if test="${sessionScope.account.getRoleID() == 2}"> style="background-color: #fed6e3;" </c:if>  
        <c:if test="${sessionScope.account.getRoleID() == 1}"> style="background-color: #D7FFFE;" </c:if>  >
    <div style="margin: 0 50px 0 50px">
        <div class="row mil-footer-top" >
            <div class="col-xl-2" style="margin-top: 30px">
                <a href="#." class=" mil-footer-logo mil-mb-60">
                    <img src="img/logo1.png" alt="Plax" width="150">
                </a>
            </div>
            <div class="col-xl-2 " style="margin-top: 30px">
                <h6 class="mil-mb-20">Useful Links</h6>
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
            <div class="col-xl-4" style="margin-top: 30px">
                <p>
                    <iframe src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d14896.732010103182!2d105.5378757!3d21.0253624!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31345bc332488903%3A0x5a3525fd6c568826!2zVHLhu40gSHV54buBbiBUaG_huqFp!5e0!3m2!1sen!2s!4v1727714621870!5m2!1sen!2s" width="500" height="250" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
                </p>
            </div>
            <div class="col-xl-3 mil-mb-80" style="margin-top: 30px">
                <h6 class="mil-mb-20">Support</h6>
                <ul class="mil-footer-list">
                    <li class="mil-text-m mil-soft mil-mb-15">
                        <a href="mailto:timibank.se1887@gmail.com" class="modal__link">Email: timibank.se1887@gmail.com</a> 
                    </li>
                    <li class="mil-text-m mil-soft mil-mb-15">
                        <a href="tel:0967368980" class="modal__link">Contact: 0967368980</a>
                    </li>
                </ul>
            </div>
            <div class="col-xl-1 mil-mb-30" style="margin-top: 30px">
                <button class="mil-mb-20 btn btn-pending js-toggle" toggle-target="#modal-guihotro">Send Support</button>
            </div>
        </div>
        <div class="mil-footer-bottom">
            <div class="row">
                <div class="col-xl-5">
                    <p class="mil-text-s mil-soft">© 2025 TIMI Finance & Fintech Design</p>
                </div>
                <div class="col-xl-5">
                    <button type="button" class=" mil-btn mil-ssm footer--toggle--btn" onclick="toggleFooter()">
                        <span class="text-expand">Collapse Footer</span>
                        <span class="text-collapsed" style="display: none">Expand Footer</span>
                        <span class="icon-arrow-right"></span>
                    </button>
                </div>
            </div>
        </div>
        <div id="modal-guihotro" class="modal modal--large hide">
            <div class="modal__content">
                <button class="modal__close js-toggle" toggle-target="#modal-guihotro">&times;</button>
                <div style="color: #008000; text-align: center;">
                    <h2 style="color: #008000; text-align: center;">Please describe what you need support with</h2>
                </div>
                <form action="showfb" method="post" class="mil-subscripe-form-footer" id="form">
                    <div class="mb-3">
                        <input style="background-color: #fff" class="form-control form-control__input mil-input" type="email" placeholder="Email" name="email" required>
                    </div>
                    <div class="mb-3">
                        <input style="background-color: #fff" class="form-control form-control__input mil-input input" type="text" placeholder="Subject" name="tieude" required>
                    </div>
                    <div class="mb-3">
                        <textarea class="form-control form-control__textarea" rows="3" placeholder="Message" name="noidung" required></textarea>
                    </div>
                    <div class="g-recaptcha" data-sitekey="6LcVf94qAAAAAHVxQmDpyNe7nzc7DEC88pgrmnb4"></div>
                    <div id="error"></div>
                    <div class="form-control__row--reverse">
                        <button type="submit" class="form-control__btn form-control__btn--green mil-btn mil-ssm">
                            <i class="far fa-envelope-open form-control__icon"></i> Send
                        </button>
                        <div class="mil-checkbox-frame">
                            <div class="mil-checkbox">
                                <input type="checkbox" id="checkbox" checked>
                                <label for="checkbox"></label>
                            </div>
                            <p class="mil-text-xs mil-soft">Subscribe to receive the latest news</p>
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
            <div class="modal__overlay js-toggle" toggle-target="#modal-guihotro"></div>
        </div>
    </div>

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
</footer>

<!-- footer end -->
