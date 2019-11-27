<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Music_service_v1</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="resources/images/icons/favicon.ico"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="resources/vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="resources/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="resources/vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="resources/css-hamburgers/hamburgers.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="resources/css/util.css">
    <link rel="stylesheet" type="text/css" href="resources/css/main.css">
    <!--===============================================================================================-->
  </head>
    <body>
        <div class="limiter">
            <div class="container-login100">
                <div class="wrap-login100 p-l-50 p-r-50 p-t-77 p-b-30">
                    <c:if test="${not empty loginError}">
                        <div class="alert alert-danger" role="alert">
                                ${loginError}
                        </div>
                    </c:if>
                    <form class="login100-form validate-form" method="post" action="">
                            <span class="login100-form-title p-b-55">
                                Login
                            </span>

                        <div class="wrap-input100 validate-input m-b-16" data-validate = "Valid email is required: ex@abc.xyz">
                            <input class="input100" type="text" name="login" placeholder="Email" value="<%=request.getParameter("login")!=null?request.getParameter("login"):""%>">
                            <span class="focus-input100"></span>
                            <span class="symbol-input100">
                                    <span class="lnr lnr-envelope"></span>
                                </span>
                        </div>

                        <div class="wrap-input100 validate-input m-b-16" data-validate = "Password is required">
                            <input class="input100" type="password" name="password" placeholder="Password" value="<%=request.getParameter("password")!=null?request.getParameter("password"):""%>">
                            <span class="focus-input100"></span>
                            <span class="symbol-input100">
                                    <span class="lnr lnr-lock"></span>
                                </span>
                        </div>

                        <div class="container-login100-form-btn p-t-25">
                            <button class="login100-form-btn">
                                Login
                            </button>
                        </div>

                        <div class="text-center w-full p-t-115">
                                <span class="txt1">
                                    Not a member?
                                </span>

                            <a class="txt1 bo1 hov1" href="registration">
                                Sign up now
                            </a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>