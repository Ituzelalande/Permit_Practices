package com.perimi.perimi.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.perimi.perimi.db.UserDao;
import com.perimi.perimi.model.User;
import com.perimi.perimi.utils.TextUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Register", value = "/com/perimi/perimi/api/v1/register")
public class ServletRegister extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String message ="";
        boolean success = false;
        String user_id = request.getParameter("user_id");
        String user_full_name = request.getParameter("user_full_name");
        String user_user_name = request.getParameter("user_user_name");
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


        try {
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
                User user = userDao.getUser(user_password, user_email);
                if(user!=null){
                    message="email already exist";
                }else {
                    User  user1 = userDao.createUser(user_id,user_full_name, user_user_name, user_email, user_phone, user_gender_id, user_birth_date,user_password);

                    if(user1!=null) {
                        success = true;
                        jsonData.addProperty("fullname",user1.getUser_full_name());
                        jsonData.addProperty("username",user1.getUser_user_name());
                        jsonData.addProperty("email",user1.getUser_email());
                        jsonData.addProperty("phone",user1.getUser_phone());
                        jsonData.addProperty("gender_id",user1.getUser_gender_id());
                        jsonData.addProperty("birth_date",user1.getUser_birth_date());

                        message="successfully registered";
                        success = true;
                    }else {
                        message="not registered";
                    }
                }
            }
        }catch (Exception e){
            message="something went wrong";
            e.printStackTrace();

            jsonResponse.addProperty("success",success);
            jsonResponse.addProperty("message",message);

            jsonResponse.add("data",jsonData);
            response.setContentType("application/json");
            response.getWriter().println(gson.toJson(jsonResponse));

            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html");
            response.getWriter().println(message);


        }


    }
}
