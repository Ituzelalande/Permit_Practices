<%@ page import="com.permit.permit.db.QuestionDao" %>
<%@ page import="com.permit.permit.model.Question" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.google.gson.JsonObject" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.permit.permit.db.User_answerDao" %>
<%@ page import="com.permit.permit.model.User_answer" %><%--
  Created by IntelliJ IDEA.
  User: Lalande-ITUZE
  Date: 11/7/2023
  Time: 10:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String question_id = request.getParameter("question_id");

  QuestionDao questionDao = new QuestionDao();
  Question question = questionDao.viewQuestions(question_id);

%>
<html>
<head>
    <title>questions</title>
  <link rel="stylesheet" href="questions.css">
</head>
<body>
<h2><b>Questions</b></h2>
<div>

  <form action="${pageContext.request.contextPath}/api/v1/userAnswer?user_ans_user_id=1&user_ans_question_id=<%=question_id%>" method="post">

    <p class="p"> <b><%=question.getQuestion_title_rw()%> :</b></p>
    <%
    JsonObject jsonChoices = new Gson().fromJson(question.getQuestion_choices_rw(), JsonObject.class);
    for(String key: jsonChoices.keySet()){

  %>
<div class="div">
  <p> <b><%=key%>.  <%= jsonChoices.get(key).getAsString()%></b> <input type="radio" name="user_ans_choice" value=<%=key%>></p>

</div>

  <%

    }
  %>

    <button type="submit"> Submit</button>

  </form>
</div>

</body>
</html>
