package com.permit.permit.model;

import lombok.Data;

@Data
public class Question {
    String question_id;
    String question_title_rw;
    String question_choices_rw;
    String question_answer;
    String question_answer_explanation_rw;
    String question_category_id;


}
