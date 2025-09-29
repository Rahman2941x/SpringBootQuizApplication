package com.syed.quizApplication.controller;


import com.syed.quizApplication.model.Question;
import com.syed.quizApplication.serviceInterface.QuestionServiceInterface;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class QuestionController {


    QuestionServiceInterface questionServiceInterface;

   public QuestionController(QuestionServiceInterface questionServiceInterface){
       this.questionServiceInterface=questionServiceInterface;
   }


   @GetMapping("/questions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionServiceInterface.getAllQuestionss();

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
    public ResponseEntity<Question>  DeleteById(@PathVariable Integer id){
        return questionServiceInterface.DeleteById(id);
    }

    @PutMapping("/update/{id}")
    public Question updateEntierBody(@PathVariable Integer id,@RequestBody Question question){
        return questionServiceInterface.updateBody(id,question);
    }


    @PatchMapping("/Patch/{id}")
    public Question updatePartialBody(@PathVariable Integer id, @RequestBody Map<String , Object> fields){
       return questionServiceInterface.updatePartialBody(id,fields);
    }



    @GetMapping("/questions/sort/{field}")
    public ApiResponse<List<Question>> getAllQuestionWithSorting(@PathVariable String field){
       return questionServiceInterface.getQuestionWithSorted(field);
    }

    @GetMapping("/questions/Paginated")
    public ApiResponse<Page<Question>> getAllQuestionWithPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ){
       return questionServiceInterface.getAllQuestionWithPaging(page,size);
    }

}
