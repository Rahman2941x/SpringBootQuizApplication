package com.syed.quizApplication.repository;


import com.syed.quizApplication.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  QuestionRepository extends JpaRepository<Question,Integer> {

    List<Question> getAllByCategory(String category);

    @Query(value = "SELECT * FROM question q WHERE q.category =:category ORDER BY RAND() LIMIT :numQ",nativeQuery = true)
    List<Question> findRandomQuestionByCategory(String category,Integer numQ);


    @Query(value = "SELECT * FROM question q WHERE q.category =:category AND q.difficulty_level=:difficulty_level ORDER BY RAND() LIMIT :numQ",nativeQuery = true)
    List<Question> findRandomQuestionByCategoryAndDifficultyLevel(Integer numQ, String category, String difficulty_level);
}
