package com.syed.quizApplication.repository;

import com.syed.quizApplication.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository  extends JpaRepository<Quiz,Integer>{


}
