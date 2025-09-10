package com.syed.quizApplication.customExceptions;

public class QuestionNotFoundException extends RuntimeException{
    public QuestionNotFoundException(Integer id){
        super("Question Not found this id: " + id);
    }
}
