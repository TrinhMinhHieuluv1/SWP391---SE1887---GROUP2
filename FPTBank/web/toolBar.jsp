<%-- 
    Document   : toolBar
    Created on : Feb 11, 2025, 1:53:50 AM
    Author     : hungk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- Menu -->
<div class="menu">
    <div class="container">
        <nav class="row row-cols-2">
            <div class="col">
                <div class="menu-list">
                    <ul class="menu-item">
                        <li class="menu-label">
                            <a href="services.jsp">Dịch vụ</a>
                        </li>
                        <li class="menu-label">
                            Công cụ
                            <ul class="sub-menu-item">
                                <li>

                                    <a class="sub-menu-link" href="calSaving.jsp">Lãi suất tiết kiệm</a>
                                </li>
                                <li>
                                    <a class="sub-menu-link" href="calLoan.jsp">Lãi vay ngân hàng</a>
                                </li>
                            </ul>
                        </li>
                        <li class="menu-label">
                            Ưu đãi
                            <ul class="sub-menu-item">
                                <li>

                                    <a class="sub-menu-link" href="prefer-card.jsp">Thẻ TIMI - Chi tiêu giảm giá hàng đầu Việt Nam</a>
                                </li>
                                <li>
                                    <a class="sub-menu-link" href="prefer.jsp">Tất cả ưu đãi</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>

            <div class="col">
                <form action="#!" class="menu-form__search">
                    <input type="text" name="search_value" class="menu-form__search-input"/>
                    <div class="mil-menu-buttons">
                        <input type="submit" class="mil-btn mil-ssm" value="Search" />
                    </div>
                </form>
            </div>
        </nav>
    </div>
</div>
<!-- Menu end -->
