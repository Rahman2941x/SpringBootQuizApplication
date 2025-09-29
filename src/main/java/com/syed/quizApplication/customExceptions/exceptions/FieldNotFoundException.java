package com.syed.quizApplication.customExceptions.exceptions;

import java.net.Socket;

public class FieldNotFoundException extends RuntimeException{
    public FieldNotFoundException(String field){
        super("Requested Field is not available: " +field);
    }
}
