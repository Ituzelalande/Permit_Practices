package com.permit.permit.db;

import com.permit.permit.model.User_answer;
import com.permit.permit.utils.DataBaseUtils;

import java.sql.SQLException;
import java.sql.Statement;

public class User_answerDao {

    public User_answer addAnswer(String user_ans_user_id,String user_ans_question_id,String user_ans_choice) throws SQLException, ClassNotFoundException {
        User_answer answer = new User_answer();

        Statement statement = DataBaseUtils.getConnection().createStatement();
        String query = "insert into `user_answers` (`user_ans_user_id`,`user_ans_question_id`,`user_ans_choice_`) values ('"+user_ans_user_id+"','"+user_ans_question_id+"',' "+user_ans_choice+"')";
        statement.execute(query);

        if(true){
            answer=new User_answer();
            answer.setUser_ans_user_id(user_ans_user_id);
            answer.setUser_ans_question_id(user_ans_question_id);
            answer.setUser_ans_choice(user_ans_choice);

        }

        return answer;

        }

        public User_answer updateAnswer(String user_ans_user_id,String user_ans_question_id,String user_ans_choice) throws SQLException, ClassNotFoundException {

        User_answer answer = new User_answer();
        Statement statement = DataBaseUtils.getConnection().createStatement();
        String query = "update `user_answers` set `user_ans_choice` = '"+user_ans_choice+"' where `user_ans_user_id` = '"+user_ans_user_id+"' and `user_ans_question_id` = '"+user_ans_question_id+"'";
        statement.execute(query);

        if(true){
            answer=new User_answer();
            answer.setUser_ans_user_id(user_ans_user_id);
            answer.setUser_ans_question_id(user_ans_question_id);
            answer.setUser_ans_choice(user_ans_choice);
        }
        return answer;

        }
}
