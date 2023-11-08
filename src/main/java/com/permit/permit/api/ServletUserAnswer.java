package com.permit.permit.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.permit.permit.db.QuestionDao;
import com.permit.permit.db.User_answerDao;
import com.permit.permit.model.Question;
import com.permit.permit.model.User_answer;
import com.permit.permit.utils.TextUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ServletUserAnswer", value = "/api/v1/userAnswer")
public class ServletUserAnswer extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String message="";
        boolean success=false;

        String user_ans_user_id = request.getParameter("user_ans_user_id");
        String user_ans_choice = request.getParameter("user_ans_choice");
        String user_ans_question_id = request.getParameter("user_ans_question_id");


        JsonObject jsonData = new JsonObject();
        JsonObject jsonResponse = new JsonObject();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();

        try{
            if(TextUtils.isEmpty(user_ans_user_id)){
                message="user_ans_user_id is empty";
            } else if (TextUtils.isEmpty(user_ans_choice)) {
                message="select your choice";
            } else {
                QuestionDao questionDao = new QuestionDao();
                Question question = questionDao.viewQuestions(user_ans_question_id);
                boolean isCorrect =user_ans_choice.equals(question.getQuestion_answer());

                User_answerDao user_answerDao = new User_answerDao();
                User_answer answer = user_answerDao.addAnswer(user_ans_user_id,user_ans_question_id, user_ans_choice,isCorrect);


                if(answer!=null) {

                    jsonData.addProperty("user_ans_user_id", answer.getUser_ans_user_id());
                    jsonData.addProperty("user_ans_choice", answer.getUser_ans_choice());
                    jsonData.addProperty("user_ans_question_id", answer.getUser_ans_question_id());

                    if (isCorrect) {
                        message = "your answer is correct";
                        success = true;

                    } else {
                        message = "sorry not correct";
                    }

                }
            }

        }catch(Exception e){
            message="something went wrong in the server";
            e.printStackTrace();
        }

        jsonResponse.addProperty("message",message);
        jsonResponse.addProperty("success",success);
        jsonResponse.add("data",jsonData);

        response.setContentType("application/json");
        response.getWriter().println(gson.toJson(jsonResponse));
        response.setCharacterEncoding("UTF-8");

    }
}
