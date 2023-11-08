package com.permit.permit.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.permit.permit.db.QuestionDao;
import com.permit.permit.model.Question;
import com.permit.permit.utils.TextUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "Servlet", value = "/api/v1/question")
public class ServletQuestion extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String message = "";
        boolean success = false;

        String question_title_rw = request.getParameter("question_title_rw");
        String question_choiceA = request.getParameter("question_choice_a");
        String question_choiceB = request.getParameter("question_choice_b");
        String question_choiceC = request.getParameter("question_choice_c");
        String question_choiceD = request.getParameter("question_choice_d");
        String question_answer = request.getParameter("question_answer");
        String question_answer_explanation_rw = request.getParameter("question_answer_explanation_rw");
        String question_category_id = request.getParameter("question_category_id");

        JsonObject jsonData = new JsonObject();
        JsonObject jsonResponse = new JsonObject();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();

        try {
            if (TextUtils.isEmpty(question_title_rw)) {
                message = "Please enter question title";
            } else if (TextUtils.isEmpty(question_choiceA)) {
                message = "Please enter all question choices";
            } else if (TextUtils.isEmpty(question_choiceB)) {
                message = "Please enter all question choices";
            } else if (TextUtils.isEmpty(question_choiceC)) {
                message = "Please enter all question choices";
            } else if (TextUtils.isEmpty(question_choiceD)) {
                message = "Please enter all question choices";
            } else if (TextUtils.isEmpty(question_answer)) {
                message = "Please enter question answer";
            } else if (TextUtils.isEmpty(question_category_id)) {
                message = "Please enter question category id";
            } else {
                QuestionDao questionDao = new QuestionDao();

                JsonObject question_Choices = new JsonObject();
                question_Choices.addProperty("A", question_choiceA);
                question_Choices.addProperty("B", question_choiceB);
                question_Choices.addProperty("C", question_choiceC);
                question_Choices.addProperty("D", question_choiceD);

                String question_choices_rw = question_Choices.toString();

                Question question = questionDao.createQuestion(question_title_rw, question_choices_rw, question_answer, question_answer_explanation_rw, question_category_id);


                if (question != null) {
                    jsonData.addProperty("question_title_rw", question.getQuestion_title_rw());
                    jsonData.addProperty("question_choices_rw", question.getQuestion_choices_rw());
                    jsonData.addProperty("question_answer", question.getQuestion_answer());
                    jsonData.addProperty("question_answer_explanation_rw", question.getQuestion_answer_explanation_rw());
                    jsonData.addProperty("question_category_id", question.getQuestion_category_id());

                }
                success = true;
                message = "Question created successfully";


            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "Error in creating question";
        }
        jsonResponse.addProperty("message", message);
        jsonResponse.addProperty("success", success);
        jsonResponse.add("data", jsonData);

        response.setContentType("application/json");
        response.getWriter().println(gson.toJson(jsonResponse));
        response.setCharacterEncoding("UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String question_title_rw = request.getParameter("question_title_rw");
        String question_choices = request.getParameter("question_choices");
        String question_id = request.getParameter("question_id");
        String message = "";
        boolean success = false;


        JsonObject jsonResponse = new JsonObject();
        JsonObject jsonData = new JsonObject();


        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();

        try {
            QuestionDao questionDao = new QuestionDao();
            Question question = questionDao.viewQuestions(question_id);

            if(question!=null){
                jsonData.addProperty("question_title_rw",question.getQuestion_title_rw());
                jsonData.addProperty("question_choices", question.getQuestion_choices_rw());
            }


            message = "all questions";
            success = true;
            jsonResponse.add("data",jsonData);

        }catch (Exception e){
            e.printStackTrace();
            message="something went wrong";
        }

        jsonResponse.addProperty("success",success);
        jsonResponse.addProperty(success? "message": "error",message);

        response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(gson.toJson(jsonResponse));


    }
}
