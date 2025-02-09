<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html
    lang="en"
    class="light-style customizer-hide"
    dir="ltr"
    data-theme="theme-default"
    data-assets-path="../assets2/"
    data-template="vertical-menu-template-free"
    >
    <head>
        <meta charset="utf-8" />
        <meta
            name="viewport"
            content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"
            />
        <title>TIMI BANK - Login</title>
        <meta name="description" content="" />
        <link rel="icon" type="image/x-icon" href="../assets2/img/favicon/favicon.png" />
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Public+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap"
            rel="stylesheet"
            />
        <link rel="stylesheet" href="../assets2/vendor/fonts/boxicons.css" />
        <link rel="stylesheet" href="../assets2/vendor/css/core.css" class="template-customizer-core-css" />
        <link rel="stylesheet" href="../assets2/vendor/css/theme-default.css" class="template-customizer-theme-css" />
        <link rel="stylesheet" href="../assets2/css/demo_1.css" />
        <link rel="stylesheet" href="../assets2/vendor/libs/perfect-scrollbar/perfect-scrollbar.css" />
        <link rel="stylesheet" href="../assets2/vendor/css/pages/page-auth.css" />
        <script src="../assets2/vendor/js/helpers.js"></script>
        <script src="../assets2/js/config.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <!-- toastr CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">

        <!-- toastr JS -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
    </head>

    <body>
        <div class="container-xxl">
            <div class="authentication-wrapper authentication-basic container-p-y">
                <div class="authentication-inner">
                    <!-- Register -->
                    <div class="card">
                        <div class="card-body">
                            
                            
                            
                            
                            
                            <form id="formAuthentication" class="mb-3" action="login" method="POST">
                                <div class="mb-3">
                                    <label for="email" class="form-label">Username</label>
                                    <input
                                    <%--     type="email"
                                        --%>
                                        required
                                        class="form-control"
                                        id="email"
                                        name="username"
                                        placeholder="Enter your username"
                                        autofocus
                                        />
                                </div>
                                <div class="mb-3 form-password-toggle">
                                    <div class="d-flex justify-content-between">
                                        <label class="form-label" for="password">Password</label>
                                        <a href="auth-forgot-password-basic.html">
                                            <small>Forgot Password?</small>
                                        </a>
                                    </div>
                                    <div class="input-group input-group-merge">
                                        <input
                                            type="password"
                                            required
                                            id="password"
                                            class="form-control"
                                            name="password"
                                            placeholder="&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;"
                                            aria-describedby="password"
                                            />
                                        <span class="input-group-text cursor-pointer"><i class="bx bx-hide"></i></span>
                                    </div>
                                </div>
                                <div class="mb-3">
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" id="remember-me" />
                                        <label class="form-check-label" for="remember-me"> Remember Me </label>
                                    </div>
                                </div>
                                <div class="mb-3">
                                    <button class="btn btn-primary d-grid w-100" type="submit">Sign in</button>
                                </div>
                            </form>
                            
                            
                            
                            

<!--                            <p class="text-center">
                                <span>New on our platform?</span>
                                <a href="auth-register-basic.html">
                                    <span>Create an account</span>
                                </a>
                            </p>-->



                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="../assets2/vendor/libs/jquery/jquery.js"></script>
        <script src="../assets2/vendor/libs/popper/popper.js"></script>
        <script src="../assets2/vendor/js/bootstrap.js"></script>
        <script src="../assets2/vendor/libs/perfect-scrollbar/perfect-scrollbar.js"></script>
        <script src="../assets2/vendor/js/menu.js"></script>
        <script src="../assets2/js/main.js"></script>
        <script async defer src="https://buttons.github.io/buttons.js"></script>
    </script>
</script>
<%@ include file="Common/toarst.jsp" %>

</body>
</html>
