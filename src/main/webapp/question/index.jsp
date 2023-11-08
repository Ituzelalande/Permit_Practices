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
</head>
<body>
<div>
<form action="${pageContext.request.contextPath}/api/v1/question" method="post">
    <input type="text" name="question_title_rw" value=""> Title<br><br>
    <input type="text" name="question_choice_a" value=""> A<br>
    <input type="text" name="question_choice_b" value=""> B<br>
    <input type="text" name="question_choice_c" value=""> C<br>
    <input type="text" name="question_choice_d" value=""> D<br>
    <input type="text" name="question_answer" value=""> Answer<br>
    <input type="text" name="question_category_id" value=""> category<br>

    <input type="submit" value="create">
</form>
</div>
</body>
</html>
