<%-- 
    Document   : update-news
    Created on : Feb 6, 2025, 5:06:42 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Add News</title>

        <!-- CKEditor -->
        <script src="https://cdn.ckeditor.com/4.16.2/standard/ckeditor.js"></script>

        <style>
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }

            body {
                font-family: 'Arial', sans-serif;
                background-color: #f5f5f5;
                color: #333;
                line-height: 1.6;
            }

            .container {
                max-width: 1000px;
                margin: 40px auto;
                padding: 20px;
            }

            .update-form {
                background: white;
                padding: 30px;
                border-radius: 10px;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            }

            .form-header {
                margin-bottom: 30px;
                text-align: center;
            }

            .form-title {
                color: #2e7d32;
                font-size: 24px;
                font-weight: 600;
            }

            .form-group {
                margin-bottom: 20px;
            }

            .form-group label {
                display: block;
                margin-bottom: 8px;
                color: #333;
                font-weight: 500;
            }

            .form-control {
                width: 100%;
                padding: 10px 15px;
                border: 1px solid #ddd;
                border-radius: 5px;
                font-size: 14px;
                transition: border-color 0.3s ease;
            }

            .form-control:focus {
                border-color: #4caf50;
                outline: none;
                box-shadow: 0 0 0 2px rgba(76, 175, 80, 0.2);
            }

            .image-preview {
                margin-top: 10px;
                max-width: 300px;
                border-radius: 5px;
            }

            .button-group {
                display: flex;
                gap: 15px;
                margin-top: 30px;
            }

            .btn {
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                font-weight: 500;
                text-decoration: none;
                text-align: center;
                transition: all 0.3s ease;
            }

            .btn-primary {
                background-color: #4caf50;
                color: white;
                flex: 1;
            }

            .btn-primary:hover {
                background-color: #43a047;
                transform: translateY(-2px);
            }

            .btn-secondary {
                background-color: #f5f5f5;
                color: #333;
                flex: 1;
                border: 1px solid #ddd;
            }

            .btn-secondary:hover {
                background-color: #e0e0e0;
                transform: translateY(-2px);
            }

            /* Alert message styling */
            .alert {
                padding: 15px;
                margin-bottom: 20px;
                border-radius: 5px;
                color: #155724;
                background-color: #d4edda;
                border: 1px solid #c3e6cb;
            }

            @media (max-width: 768px) {
                .container {
                    margin: 20px auto;
                    padding: 15px;
                }

                .update-form {
                    padding: 20px;
                }

                .button-group {
                    flex-direction: column;
                }
            }
        </style>
    </head>
    <body>
        <div class="container">
            <form action="add-news" method="post" class="update-form" onsubmit="return prepareSubmit()">
                <div class="form-header">
                    <h1 class="form-title">Update News</h1>
                </div>

                <input type="hidden" name="UserID" value="${sessionScope.account.getUserID()}">

                <div class="form-group">
                    <label for="title">Title</label>
                    <input type="text" id="title" name="Title" 
                           class="form-control" required>
                </div>

                <div class="form-group">
                    <label for="description">Description</label>
                    <textarea id="description" name="Description" class="form-control" required>
                    </textarea>
                    <input type="hidden" id="cleanDescription" name="CleanDescription">
                </div>

                <div class="form-group">
                    <label for="image">Image URL</label>
                    <input type="text" id="image" name="Image" 
                           class="form-control" required onchange="updateImagePreview(this.value)">
                    <img id="imagePreview" src="" class="image-preview">
                </div>

                <div class="button-group">
                    <a href="/timibank/seller/news-management" class="btn btn-secondary">Back to List</a>
                    <button type="submit" class="btn btn-primary">Add News</button>
                </div>
            </form>
        </div>

        <script>
            // Initialize CKEditor
            var editor = CKEDITOR.replace('description', {
                height: 300,
                removeButtons: 'Image',
                enterMode: CKEDITOR.ENTER_BR,
                shiftEnterMode: CKEDITOR.ENTER_P,
                autoParagraph: false,
                fillEmptyBlocks: false,
                removePlugins: 'div,autolink',
                toolbar: [
                    { name: 'basicstyles', items: [ 'Bold', 'Italic', 'Underline', 'Strike' ] },
                    { name: 'paragraph', items: [ 'NumberedList', 'BulletedList', '-', 'Outdent', 'Indent', '-', 'Blockquote' ] },
                    { name: 'links', items: [ 'Link', 'Unlink' ] },
                    { name: 'styles', items: [ 'Format', 'Font', 'FontSize' ] },
                    { name: 'colors', items: [ 'TextColor', 'BGColor' ] },
                    { name: 'tools', items: [ 'Maximize' ] }
                ]
            });

            // Function to update image preview
            function updateImagePreview(url) {
                const preview = document.getElementById('imagePreview');
                preview.src = url;
            }

            function prepareSubmit() {
                // Get CKEditor content
                var content = CKEDITOR.instances.description.getData();
                
                // Remove all HTML tags except allowed formatting
                content = content.replace(/<p[^>]*>/gi, '')
                               .replace(/<\/p>/gi, '<br>')
                               .replace(/<div[^>]*>/gi, '')
                               .replace(/<\/div>/gi, '<br>')
                               .replace(/[\n\r]+/g, '')
                               .replace(/(<br\s*\/?>\s*)+$/g, ''); // Remove trailing <br>
                
                // Clean up any extra spaces and line breaks
                content = content.trim();
                
                // Update both the original textarea and our hidden input
                CKEDITOR.instances.description.setData(content);
                document.getElementById('description').value = content;
                document.getElementById('cleanDescription').value = content;
                
                return true;
            }

            // Clean initial content
            window.onload = function() {
                var initialContent = CKEDITOR.instances.description.getData();
                initialContent = initialContent.replace(/<p[^>]*>/gi, '')
                                            .replace(/<\/p>/gi, '<br>')
                                            .replace(/[\n\r]+/g, '')
                                            .trim();
                CKEDITOR.instances.description.setData(initialContent);
            };
        </script>
    </body>
</html>
