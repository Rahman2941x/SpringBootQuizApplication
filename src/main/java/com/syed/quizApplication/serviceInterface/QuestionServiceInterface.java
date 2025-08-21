package com.syed.quizApplication.serviceInterface;

import com.syed.quizApplication.model.Question;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuestionServiceInterface {

      ResponseEntity<List<Question>> getAllQuestions();
      ResponseEntity<String> addQuestion(Question question);
      ResponseEntity<String>  addMultipleQuestions(List<Question> questions);
      ResponseEntity<List<Question>> getAllQuestionBasedOnCategory(String category);

    ResponseEntity<Question> DeleteById(Integer id);
}
