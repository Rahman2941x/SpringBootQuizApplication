package com.syed.quizApplication.controller;


import com.syed.quizApplication.model.Question;
import com.syed.quizApplication.serviceInterface.QuestionServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
public class QuestionController {


    QuestionServiceInterface questionServiceInterface;

   public QuestionController(QuestionServiceInterface questionServiceInterface){
       this.questionServiceInterface=questionServiceInterface;
   }


   @GetMapping("/questions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionServiceInterface.getAllQuestions();

    }

    @PostMapping("/question")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){

        return questionServiceInterface.addQuestion(question);
    }


    @PostMapping("/multiple/questions")
    public void addMultipleQuestions(@RequestBody List<Question> questions){
        questionServiceInterface.addMultipleQuestions(questions);
    }

    @GetMapping("/question/{category}")
    public  ResponseEntity<List<Question>> getAllQuestionBasedOnCategory(@PathVariable String category){
       return questionServiceInterface.getAllQuestionBasedOnCategory(category);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Question> DeleteById(@PathVariable Integer id){
       return questionServiceInterface.DeleteById(id);
    }

}
