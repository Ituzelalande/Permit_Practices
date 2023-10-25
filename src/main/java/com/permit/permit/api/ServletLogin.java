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

@WebServlet(name = "ServletLogin", value = "/ServletLogin")
public class ServletLogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String message = "";
        boolean success = false;

        String user_email = request.getParameter("user_email");
        String user_password = request.getParameter("user_password");

        JsonObject jsonData = new JsonObject();
        JsonObject jsonResponse = new JsonObject();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();

        try {

            if(TextUtils.isEmpty(user_email)){
                message="email is required";

            } else if (TextUtils.isEmpty(user_password)) {
                message="password is required";
            } else {
                UserDao userDao = new UserDao();
                User user = userDao.getUser(user_email, user_password);
                if (user == null) {
                    message = "User not exist";
                }else {
                    User user1 = userDao.getUser(user_email, user_password);
                    if(user1!=null){

                        user1= new User();
                        jsonData.addProperty("user_full_name",user1.getUser_full_name());
                        jsonData.addProperty("user_email",user1.getUser_email());
                        jsonData.addProperty("user_id",user1.getUser_id());
                        jsonData.addProperty("user_phone",user1.getUser_phone());

                    }
                    message="logged in";
                    success=true;
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


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
