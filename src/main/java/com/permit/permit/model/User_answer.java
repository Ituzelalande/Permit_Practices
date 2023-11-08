package com.permit.permit.model;

import lombok.Data;

@Data
public class User_answer {

    String user_ans_id;
    String user_ans_user_id;
    String user_ans_question_id;
    String user_ans_choice;
    boolean is_correct;
}
