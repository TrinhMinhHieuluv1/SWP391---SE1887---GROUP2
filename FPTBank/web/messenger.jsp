

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <style>
            /* Ensure the top panel has a defined z-index */
            .mil-top-panel {
                position: relative; /* Ensure it has a positioning context */
                z-index: 1000; /* Set a reasonable z-index for the top panel */
            }

            /* Style for the messenger icon */
            .messenger-icon {
                position: fixed;
                bottom: 90px;
                right: 18px;
                width: 60px;
                height: 60px;
                background-color: #0078FF;
                border-radius: 50%;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                display: flex;
                align-items: center;
                justify-content: center;
                z-index: 9999; /* Ensure the icon is above most elements */
                transition: transform 0.2s ease-in-out, background-color 0.2s;
                cursor: pointer;
            }

            .messenger-icon img {
                width: 40px;
                height: 40px;
            }

            .messenger-icon:hover {
                transform: scale(1.1);
                background-color: #005ce6;
            }

            /* CSS cho df-messenger chatbot */
            df-messenger {
                --df-messenger-chat-window-width: 280px; /* Giữ nguyên chiều rộng */
                --df-messenger-chat-window-height: 100px; /* Giảm chiều cao từ 350px xuống 250px */
                z-index: 10000;
            }

            df-messenger::part(chat-container) {
                width: 280px;
                height: 100px; /* Giảm chiều cao từ 350px xuống 250px */
            }

            df-messenger::part(titlebar) {
                height: 10px; /* Giữ nguyên chiều cao thanh tiêu đề */
            }

            df-messenger chat-box {
                bottom: 20px;
                right: 20px;
                z-index: 10000;
            }

            df-messenger chat-box[opened] {
                max-width: 280px;
                max-height: 100px; /* Giảm chiều cao tối đa từ 350px xuống 250px */
                z-index: 10000;
            }


        </style>

    </head>
    <body>
        <a href="https://m.me/575284275673355" target="_blank" class="messenger-icon">
            <img src="https://cdn4.iconfinder.com/data/icons/social-media-2285/1024/logo-512.png" alt="Messenger">
        </a>

        <script src="https://www.gstatic.com/dialogflow-console/fast/messenger/bootstrap.js?v=1"></script>
    <df-messenger
        intent="WELCOME"
        chat-title="TimiBank"
        agent-id="497635a6-206b-4a8c-96ef-bef36f7789cd"
        language-code="vi"
        ></df-messenger>

</body>


</html>
