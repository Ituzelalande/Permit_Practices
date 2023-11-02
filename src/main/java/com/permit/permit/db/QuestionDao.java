package com.permit.permit.db;

import com.permit.permit.model.Question;
import com.permit.permit.utils.DataBaseUtils;

import java.sql.SQLException;
import java.sql.Statement;

public class QuestionDao {
    public Question createQuestion( String question_title,String question_choices,String question_answer,String question_answer_explanation,String question_category_id  ) throws SQLException, ClassNotFoundException {
        Question question = new Question();
        Statement statement= DataBaseUtils.getConnection().createStatement();
        String query = "INSERT INTO `question`(`question_title`,`question_choices`,`question_answer`,`question_answer_explanation`,`question_category_id`) VALUES ('"+question_title+"','"+question_choices+"','"+question_answer+"',' "+question_answer_explanation+"','"+question_category_id+"')";
        statement.execute(query);

        if(true){
            question=new Question();
            question.setQuestion_title(question_title);
            question.setQuestion_choices(question_choices);
            question.setQuestion_answer(question_answer);
            question.setQuestion_answer_explanation(question_answer_explanation);
            question.setQuestion_category_id(question_category_id);

        }
        return question;
    }
}
