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
</head>
<body>

<div class="div-p">
    <h2>Signin</h2>
    <form action="${pageContext.request.contextPath}/api/v1/login" method="get">
        <div>
            <input type="email" name="user_email" id="user_email" placeholder="Enter your Email">
        </div><br>
        <div>
            <input type="password" name="user_password" id="user_password" placeholder="Enter your Password">
        </div><br>
        <div>
            <input type="submit" value="Login">
        </div>
    </form>

    <p>Don't have account <a href="${pageContext.request.contextPath}/register/index.jsp">Signup</a></p>
</div>

</body>
</html>
