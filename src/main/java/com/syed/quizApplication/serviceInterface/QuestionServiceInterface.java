package com.syed.quizApplication.serviceInterface;

import com.syed.quizApplication.controller.ApiResponse;
import com.syed.quizApplication.model.Question;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface QuestionServiceInterface {

      ResponseEntity<List<Question>> getAllQuestionss();
      ResponseEntity<String> addQuestion(Question question);
      ResponseEntity<String>  addMultipleQuestions(List<Question> questions);
      ResponseEntity<List<Question>> getAllQuestionBasedOnCategory(String category);

    ResponseEntity<Question>  DeleteById(Integer id);

    Question updateBody(Integer id, Question question);

    Question updatePartialBody(Integer id, Map<String, Object> fields);

    ApiResponse<List<Question>> getQuestionWithSorted(String fields);

    ApiResponse<Page<Question>> getAllQuestionWithPaging(Integer page, Integer size);
}
