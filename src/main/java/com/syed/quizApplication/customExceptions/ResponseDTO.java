package com.syed.quizApplication.customExceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ResponseDTO {
    private LocalDateTime localDate;
    private int status;
    private String error;
    private  String path;

    public ResponseDTO(HttpStatus status,String error,String path){
        this.localDate= LocalDateTime.now();
        this.status=status.value();
        this.error=error;
        this.path=path;
    }


    public String getPath() {
        return path;
    }

    public String getError() {
        return error;
    }

    public LocalDateTime getLocalDate() {
        return localDate;
    }

    public  int getStatus(){
        return status;
    }

}
