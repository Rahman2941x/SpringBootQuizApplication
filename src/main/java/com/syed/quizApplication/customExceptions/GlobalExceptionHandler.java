package com.syed.quizApplication.customExceptions;

import com.syed.quizApplication.customExceptions.exceptions.CategoryNotFoundException;
import com.syed.quizApplication.customExceptions.exceptions.FieldNotFoundException;
import com.syed.quizApplication.customExceptions.exceptions.QuestionNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(QuestionNotFoundException.class)
    public ResponseEntity<ResponseDTO> handleQuestionNotFound(
            QuestionNotFoundException ex, HttpServletRequest request
            ){
        ResponseDTO responseDTO = new ResponseDTO(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }


    @ExceptionHandler(value = CategoryNotFoundException.class)
    public ResponseEntity<ResponseDTO> handleCategoryNotFound(CategoryNotFoundException exception,HttpServletRequest request){
        //Object
        ResponseDTO responseDTO=new ResponseDTO(HttpStatus.NOT_FOUND,exception.getMessage(),request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }


    @ExceptionHandler(value = FieldNotFoundException.class)
    public ResponseEntity<ResponseDTO> handleFiledNotFound(FieldNotFoundException exception,HttpServletRequest request){
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.NOT_FOUND,exception.getMessage(),request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

}
