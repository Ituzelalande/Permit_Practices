<%--
  Created by IntelliJ IDEA.
  User: AIONEM.NET
  Date: 10/25/2023
  Time: 11:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="login.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css" />

</head>
<body>

<div class="div-p">
    <div class="div">

        <div class="div1">
            <h2 class="h"> Welcome Back</h2><br><br>
            <p class="p"> please log in using your personal information to stay connected with us</p>
        </div>
        <div class="div2">
    <h2>LOGIN</h2>
    <form action="${pageContext.request.contextPath}/api/v1/login" method="get">

            <input type="email" name="user_email" id="user_email" placeholder="Enter your Email"><br><br>


        <p></p>
            <label for="user_password"></label>
            <input type="password" name="user_password" id="user_password" placeholder="Enter your Password" >
            <i class="bi bi-eye-slash" id="togglePassword"></i> <br><br>

        </p>
            <input class="button" type="submit" value="Login">
    </form>

    <p>Don't have account <a href="${pageContext.request.contextPath}/register/index.jsp">Log In</a></p>
    </div>
    </div>
</div>

<script>

    const togglePassword = document.querySelector("#togglePassword");
    const password = document.querySelector("#user_password");
    togglePassword.addEventListener("click", function () {
        // toggle the type attribute
        const type = password.getAttribute("type") === "password" ? "text" : "password";
        password.setAttribute("type", type);

        // toggle the icon
        this.classList.toggle("bi-eye");
    });

    // prevent form submit
    const form = document.querySelector("form");
    form.addEventListener('submit', function (e) {
        e.preventDefault();
    });
</script>
</body>
</html>
