package com.syed.quizApplication.serviceInterface;

import com.syed.quizApplication.model.QuizQuestions;
import com.syed.quizApplication.model.Responses;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuizServiceInterface {

    ResponseEntity<String> createRandomQuizQuestion(Integer numQ,String title, String category);

    ResponseEntity<String> createRandomQuizQuestionBasedOnDifficultylevel(Integer numQ, String title, String category, String difficulty_level);

    ResponseEntity<List<QuizQuestions>> GetQuizQuestion(Integer id);

    ResponseEntity<Integer> GetQuizResult(Integer id, List<Responses> responses);

    ResponseEntity<String> DeleteById(Integer id);
}
