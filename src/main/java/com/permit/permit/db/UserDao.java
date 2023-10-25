package com.permit.permit.db;

import com.permit.permit.model.User;
import com.permit.permit.utils.DataBaseUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao {

    public User createUser(String user_id, String user_full_name, String user_username, String user_email, String user_phone, String user_gender_id, String user_birth_date, String user_password) throws SQLException, ClassNotFoundException {
        User user = null;
        Statement statement = DataBaseUtils.getConnection().createStatement();
        String query = "insert into `users` (`user_id`, `user_full_name`, `user_username`, `user_email`, `user_phone`, `user_gender_id`, `user_birth_date`, `user_password`) values ('" + user_id + "','" + user_full_name + "','" + user_username + "','" + user_email + "','" + user_phone + "','" + user_gender_id + "','" + user_birth_date + "','" + user_password + "')";

        statement.execute(query);

        if (true) {
            user = new User();
            user.setUser_id(user_id);
            user.setUser_full_name(user_full_name);
            user.setUser_user_name(user_username);
            user.setUser_email(user_email);
            user.setUser_phone(user_phone);
            user.setUser_gender_id(user_gender_id);
            user.setUser_birth_date(user_birth_date);
            user.setUser_password(user_password);
        }

        return user;
    }

    public User viewUser(String user_id) throws SQLException, ClassNotFoundException {
        User user = null;
        Statement statement = DataBaseUtils.getConnection().createStatement();
        String query = "select `users`.*, `gender`.`gender_type` from `users` join `gender` on `user`.`user_gender_id`=`gender`.`gender_id` where user_id = '" + user_id + "'";
        ResultSet resultSet = statement.executeQuery(query);
        if (resultSet.next()) {
            user = new User();
            user.setUser_id(resultSet.getString("user_id"));
            user.setUser_full_name(resultSet.getString("user_full_name"));
            user.setUser_user_name(resultSet.getString("user_username"));
            user.setUser_email(resultSet.getString("user_email"));
            user.setUser_phone(resultSet.getString("user_phone"));
            user.setUser_gender_id(resultSet.getString("user_gender_id"));
            user.setUser_birth_date(resultSet.getString("user_birth_date"));
            user.setUser_password(resultSet.getString("user_password"));
        }

        return user;
    }

    public User updateUser(String user_id,String name, String user_name,String email,String phone,String gender_id,String birthDate,String password ) throws SQLException, ClassNotFoundException {
        User user = null;
        Statement statement = DataBaseUtils.getConnection().createStatement();
        String query = "update `users` set `user_full_name`='" + name + "', `user_username`='" + user_name + "', `user_email`='" + email + "', `user_phone`='" + phone + "', `user_gender_id`='" + gender_id + "', `user_birth_date`='" + birthDate + "', ` `user_password`='" + password + "' where `user_id`='" + user_id + "'";
        statement.execute(query);
        if (true) {
            user = new User();
            user.setUser_id(user_id);
            user.setUser_full_name(name);
            user.setUser_user_name(user_name);
            user.setUser_email(email);
            user.setUser_phone(phone);
            user.setUser_gender_id(gender_id);
            user.setUser_birth_date(birthDate);
            user.setUser_password(password);
        }
        return user;
    }
    public void deleteUser(String user_id) throws SQLException, ClassNotFoundException {
        Statement statement = DataBaseUtils.getConnection().createStatement();
        String query = "delete from `users` where `user_id`='" + user_id + "'";
        statement.execute(query);
    }

    public User getUser(String user_password,String user_email) throws SQLException, ClassNotFoundException {
        User user = null;
        Statement statement = DataBaseUtils.getConnection().createStatement();
        String query = "select * from `users` where `user_password`='" + user_password + "' and `user_email`='" + user_email + "'";
        ResultSet resultSet = statement.executeQuery(query);
        if(resultSet.next()) {
            user = new User();
            user.setUser_id(resultSet.getString("user_id"));
            user.setUser_full_name(resultSet.getString("user_full_name"));
            user.setUser_user_name(resultSet.getString("user_username"));
            user.setUser_email(resultSet.getString("user_email"));
            user.setUser_phone(resultSet.getString("user_phone"));
            user.setUser_gender_id(resultSet.getString("user_gender_id"));
            user.setUser_birth_date(resultSet.getString("user_birth_date"));
            user.setUser_password(resultSet.getString("user_password"));
        }
        return user;
    }
}
