package com.syed.quizApplication.controller;
import com.syed.quizApplication.model.QuizQuestions;
import com.syed.quizApplication.model.Responses;
import com.syed.quizApplication.serviceInterface.QuizServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {


    QuizServiceInterface quizInterface;

    private QuizController(QuizServiceInterface quizInterface){
        this.quizInterface=quizInterface;
    }

    @PostMapping("/create-category" )
    public void getRandomQuestion(@RequestParam Integer numQ,
                                                    @RequestParam String title,
                                                    @RequestParam String category
                                                   ){
        quizInterface.createRandomQuizQuestion(numQ,title,category);
    }

    @PostMapping("/create-difficultylevel")
    public void getByDifficultyLevel(@RequestParam Integer numQ,
                                                       @RequestParam String title,
                                                       @RequestParam String category,
                                                       @RequestParam String difficulty_level)
    {
        quizInterface.createRandomQuizQuestionBasedOnDifficultylevel(numQ,title,category,difficulty_level);
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuizQuestions>> getQuizQuestion(@PathVariable Integer id){

        return quizInterface.GetQuizQuestion(id);
    }

    @PostMapping("/submit/{id}")
    public  ResponseEntity<Integer> getQuizResult(@PathVariable Integer id,@RequestBody List<Responses> responses){

        return quizInterface.GetQuizResult(id,responses);

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> DeleteById(@PathVariable Integer id){
        return quizInterface.DeleteById(id);
    }

}
