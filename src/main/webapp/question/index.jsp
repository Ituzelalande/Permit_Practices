<%--
  Created by IntelliJ IDEA.
  User: AIONEM.NET
  Date: 10/25/2023
  Time: 10:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>questions</title>
    <link rel="stylesheet" href="question.css">
</head>
<body>
<div class="div">
    <h2><b>CREATE QUESTIONS</b></h2>
<form action="${pageContext.request.contextPath}/api/v1/question" method="post">
    <div>
        <input type="text" name="question_title_rw" value="">  <b>:Title</b><br><br>
    </div>
    <div>
        <input type="text" name="question_choice_a" value="">  <b>:A</b><br>
    </div><br>
    <div>
        <input type="text" name="question_choice_b" value="">  <b>:B</b><br>
    </div><br>
    <div>
        <input type="text" name="question_choice_c" value="">  <b>:C</b><br>
    </div><br>
    <div>
        <input type="text" name="question_choice_d" value="">  <b>:D</b><br>
    </div><br>
    <div>
        <input type="text" name="question_answer" value="">  <b>:Answer</b><br>
    </div><br>
    <div>
        <input type="text" name="question_category_id" value="">  <b>:Category</b><br>
    </div><br>
    <div>
        <button type="submit"><b>CREATE</b></button>
    </div><br>

</form>
</div>
</body>
</html>
