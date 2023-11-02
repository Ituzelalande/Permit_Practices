package com.permit.permit.model;

import lombok.Data;

@Data
public class Question {
    String question_id;
    String question_title;
    String question_choices;
    String question_answer;
    String question_answer_explanation;
    String question_category_id;


}
