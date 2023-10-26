<%@ page import="com.permit.permit.db.UserDao" %>
<%@ page import="com.permit.permit.model.User" %><%--
  Created by IntelliJ IDEA.
  User: AIONEM.NET
  Date: 10/25/2023
  Time: 11:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="register.css">
</head>
<body>
<div class="div">
    <h2> SignUp</h2>
    <form action="${pageContext.request.contextPath}/api/v1/register" method="post">
        <input type="text" id="user_full_name" name="user_full_name" placeholder="Your Full Name"><br><br>
        <input type="text" id="username" name="username" id="username" placeholder=" your Username"><br><br>
        <input type="email" id="user_email" name="user_email" placeholder="your Email"><br><br>
        <input type="tel" id="user_phone" name="user_phone" placeholder="your phone"><br><br>
        <input type="date"  id="user_birth_date" name="user_birth_date" placeholder="birth date"><br><br>
        <select id="user_gender_id" name="user_gender_id" >
            <option value="" disabled selected>-gender</option>
            <option value="1" > Male</option>
            <option value="2" >Female</option>
        </select><br><br>
        <input type="password" id="user_password" name="user_password" placeholder="password"><br><br>
        <button type="submit">Signup</button><br>
    </form><br>
    <p>already have an account? <a href="${pageContext.request.contextPath}/login/index.jsp"><b>Signin</b></a></p>
</div>
</body>
</html>
