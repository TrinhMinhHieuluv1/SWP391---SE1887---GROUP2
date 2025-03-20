<%-- 
    Document   : TestThanhToan
    Created on : Feb 24, 2025, 9:05:01 PM
    Author     : fptshop
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Thanh toán QR</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f4f4f4;
            margin: 0;
        }
        .card {
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            text-align: center;
            width: 600px;
            height: 400px;
        }
        .qr-code img {
            width: 300px;
            height: 300px;
        }
        .status {
            margin-top: 15px;
            font-size: 18px;
            font-weight: bold;
            color: red;
        }
    </style>
</head>
<body>

    <div class="card">
        <h2>Quét mã để thanh toán</h2>
        <div class="qr-code">
            <img src="https://img.vietqr.io/image/MB-0967368980-compact2.png?amount=2000&addInfo=timibank" alt="QR Code">
        </div>
        <div class="status">Chưa thanh toán</div>
    </div>

</body>
</html>

