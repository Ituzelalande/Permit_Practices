package com.permit.permit.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.permit.permit.db.QuestionDao;
import com.permit.permit.model.Question;
import com.permit.permit.utils.TextUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "Servlet", value = "/Servlet")
public class ServletQuestion extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String message="";
        boolean success = false;

        String question_title = request.getParameter("question_title");
        String question_choices = request.getParameter("question_choices");
        String question_answer = request.getParameter("question_answer");
        String question_answer_explanation = request.getParameter("question_answer_explanation");
        String question_category_id = request.getParameter("question_category_id");

        JsonObject jsonData = new JsonObject();
        JsonObject jsonResponse = new JsonObject();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();

        try{
        if(TextUtils.isEmpty(question_title)){
            message = "Please enter question title";
        }else if(TextUtils.isEmpty(question_choices)){
            message = "Please enter question choices";
        } else if(TextUtils.isEmpty(question_answer)){
            message = "Please enter question answer";
        } else if(TextUtils.isEmpty(question_category_id)){
            message = "Please enter question category id";
        }else{
            QuestionDao questionDao = new QuestionDao();
            Question question = questionDao.createQuestion(question_title,question_choices, question_answer,question_answer_explanation,question_category_id);

            if(question!= null){
                jsonData.addProperty("question_title",question.getQuestion_title());
                jsonData.addProperty("question_choices",question.getQuestion_choices());
                jsonData.addProperty("question_answer",question.getQuestion_answer());
                jsonData.addProperty("question_answer_explanation",question.getQuestion_answer_explanation());
                jsonData.addProperty("question_category_id",question.getQuestion_category_id());

            }
            success = true;
            message = "Question created successfully";


        }
        }catch(Exception e){
            message = "Error in creating question";
        }
        jsonResponse.addProperty("message",message);
        jsonResponse.addProperty("success",success);
        jsonResponse.add("data",jsonData);

        response.setContentType("application/json");
        response.getWriter().println(gson.toJson(jsonResponse));
        response.setCharacterEncoding("UTF-8");
    }
}
