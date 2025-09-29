package com.syed.quizApplication.customExceptions.exceptions;

public class QuestionNotFoundException extends RuntimeException{
    public QuestionNotFoundException(Integer id){
        super("Question Not found this id: " + id);
    }
}
