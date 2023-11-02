package com.permit.permit.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.permit.permit.db.UserDao;
import com.permit.permit.model.User;
import com.permit.permit.utils.TextUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ServletRegister", value = "/api/v1/register")
public class ServletRegister extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String message ="";
        boolean success = false;

        String user_full_name = request.getParameter("user_full_name");
        String user_username = request.getParameter("user_username");
        String user_email = request.getParameter("user_email");
        String user_phone = request.getParameter("user_phone");
        String user_gender_id = request.getParameter("user_gender_id");
        String user_birth_date = request.getParameter("user_birth_date");
        String user_password = request.getParameter("user_password");


        JsonObject jsonData = new JsonObject();
        JsonObject jsonResponse = new JsonObject();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();


        try{

            if(TextUtils.isEmpty(user_full_name)){
                message = "fill your name";
            }
            else if (TextUtils.isEmpty(user_email)) {
                message = "email is required";
            } else if (TextUtils.isEmpty(user_phone)) {
                message = "phone number is required";
            } else if (TextUtils.isEmpty(user_password)) {
                message = "password is required";
            } else if (!user_email.matches("^(.+)@(\\S+)$")){
                message="invalid email";
            }else if (!user_password.matches("^(?=.*[0-9]).{8,15}$")){
                message="password not strong";
            } else {
                UserDao userDao = new UserDao();
                User user = userDao.loginEmail(user_email);
                if (user != null) {
                    message = "email already exist";
                } else {
                    User user1 = userDao.createUser(user_full_name, user_username, user_email, user_phone, user_gender_id, user_birth_date , user_password);
                    if(user1!=null){

                        jsonData.addProperty("user_full_name",user1.getUser_full_name());
                        jsonData.addProperty("user_username",user1.getUser_username());
                        jsonData.addProperty("user_email",user1.getUser_email());
                        jsonData.addProperty("user_phone",user1.getUser_phone ());
                        jsonData.addProperty("user_gender_id",user1.getUser_gender_id ());
                        jsonData.addProperty("user_birth_date",user1.getUser_birth_date ());
                        jsonData.addProperty("user_password",user1.getUser_password ());
                        jsonResponse.add("data",jsonData);
                        success = true;
                        message="new user created";
                    }else{
                        message = "something went wrong";
                    }

                }
            }
        }catch (Exception e){
            message="something went wrong";
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
