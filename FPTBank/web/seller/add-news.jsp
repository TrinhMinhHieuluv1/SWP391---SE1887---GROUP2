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
        <script src="https://cdn.ckeditor.com/4.16.2/full/ckeditor.js"></script>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

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
            <form action="add-news" method="post" class="update-form" onsubmit="prepareSubmit(event)" enctype="multipart/form-data">
                <div class="form-header">
                    <h1 class="form-title">Add News</h1>
                </div>

                <label for="NewsCategory" style="margin-left: 20px">News Category: </label>
                <select name="NewsCategoryID" id="NewsCategory" required >
                    <c:forEach items="${requestScope.ncList}" var="nc">
                        <option value="${nc.getNewsCategoryID()}">${nc.getNewsCategoryName()}</option>
                    </c:forEach>
                </select>

                <div class="form-group">
                    <label for="title">Title</label>
                    <input type="text" id="title" name="Title" 
                           class="form-control" required onchange="validateTitle()">
                    <div id="title-err" style="color: red; display: none">Title can't be empty or only contain space. Try again!</div>
                </div>

                <div class="form-group">
                    <label for="description">Description</label>
                    <textarea id="description" name="Description" class="form-control" required>
                    </textarea>
                </div>

                <div class="form-group">
                    <label for="url-image">Image URL</label>
                    <input type="text" id="url-image" name="url-image" 
                           class="form-control" required onchange="updateImagePreviewByUrl(this.value)"><br><br>
                    <div id="URL-err" style="color: red; display: none">URL must end with '.jpg' or '.png'</div>
                    <input id="file-image" type="file" name="file-image" accept="image/jpeg, image/png" required onchange="updateImagePreviewByFile()"><br>
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
            CKEDITOR.replace('description');

            // Function to clean CKEditor content by removing unnecessary <p> tags
            function cleanCKEditorContent(content) {
                // Remove empty <p> tags
                content = content.replace(/<p>\s*<\/p>/gi, '');

                // Remove <p> tags that only wrap the entire content
                content = content.replace(/^<p>(.*)<\/p>$/gi, '$1');

                // Trim whitespace
                content = content.trim();

                return content;
            }

            // Function to update image preview
            function updateImagePreviewByUrl(url) {
                const preview = document.getElementById('imagePreview');
                validateURL(url);
                preview.src = url.replaceAll('//', '/');
                document.getElementById('file-image').value = '';
                document.getElementById('file-image').removeAttribute('required');
            }

            function updateImagePreviewByFile() {
                let formData = new FormData();
                let file_image = document.getElementById('file-image');

                if (file_image.files.length === 0) {
                    alert('Please choose a file to upload!');
                    return;
                }

                formData.append('file', file_image.files[0]);

                fetch('update-preview-image-by-file', {
                    method: 'POST',
                    body: formData
                })
                        .then(response => response.blob())
                        .then(blob => {
                            const imageUrl = URL.createObjectURL(blob);
                            document.getElementById('imagePreview').src = imageUrl;
                            document.getElementById('url-image').value = '';
                            document.getElementById('url-image').removeAttribute('required');
                        })
                        .catch(error => console.error('Error:', error));

            }

            function validateTitle() {
                const title = document.getElementById('title').value;
                const title_err = document.getElementById('title-err');
                if (title.trim() !== '' && title.length!==0) {
                    title_err.style.display = 'none';
                    return true;
                } else {
                    title_err.style.display = 'block';
                    return false;
                }
            }

            function validateURL() {
                const url = document.getElementById('url-image').value;
                const URL_err = document.getElementById('URL-err');
                if (document.getElementById('file-image').value !== null){
                    return true;
                }
                if (url.endsWith('.jpg') || url.endsWith('.png')) {
                    URL_err.style.display = 'none';
                    return true;
                } else {
                    URL_err.style.display = 'block';
                    return false;
                }
            }

            // Prepare form submission
            function prepareSubmit(event) {
                // Get CKEditor content
                const description = CKEDITOR.instances.description.getData();

                // Clean description
                const cleanDescription = cleanCKEditorContent(description);

                // Set cleaned description directly to textarea
                CKEDITOR.instances.description.setData(cleanDescription);
                if (validateURL() === false) {
                    alert("URL must end with '.jpg' or '.png'. Try again!");
                    event.preventDefault();
                }
                if (validateTitle() === false) {
                    alert("Title can't be empty or only contain space. Try again!");
                    event.preventDefault();
                }
            }
        </script>
    </body>
</html>
