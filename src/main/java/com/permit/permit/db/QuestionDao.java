package com.permit.permit.db;

import com.permit.permit.model.Question;
import com.permit.permit.utils.DataBaseUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class QuestionDao {
    public Question createQuestion( String question_title_rw,String question_choices_rw,String question_answer,String question_answer_explanation_rw,String question_category_id  ) throws SQLException, ClassNotFoundException {
        Question question = new Question();

        Statement statement = DataBaseUtils.getConnection().createStatement();
        String query = "INSERT INTO `questions`(`question_title_rw`,`question_choices_rw`,question_answMer,`question_answer_explanation_rw`,`question_category_id`) VALUES ('" + question_title_rw + "','" + question_choices_rw + "','" + question_answer + "',' " + question_answer_explanation_rw + "','" + question_category_id + "')";
        statement.execute(query);


        if (true) {
            question = new Question();
            question.setQuestion_title_rw(question_title_rw);
            question.setQuestion_choices_rw(question_choices_rw);
            question.setQuestion_answer(question_answer);
            question.setQuestion_answer_explanation_rw(question_answer_explanation_rw);
            question.setQuestion_category_id(question_category_id);

        }
        return question;
    }
        public Question viewQuestions(String question_id) throws SQLException, ClassNotFoundException {
            Question question =null;

            Statement statement= DataBaseUtils.getConnection().createStatement();
            String query = "select * from `questions` where `question_id`='"+question_id+"'";
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next()){
                question = new Question();
                question.setQuestion_id(resultSet.getString("question_id"));
                question.setQuestion_title_rw(resultSet.getString("question_title_rw"));
                question.setQuestion_choices_rw(resultSet.getString("question_choices_rw"));
                question.setQuestion_answer(resultSet.getString("question_answer"));

            }
            return question;
        }

}
