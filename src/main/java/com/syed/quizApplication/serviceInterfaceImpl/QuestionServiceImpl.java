package com.syed.quizApplication.serviceInterfaceImpl;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.syed.quizApplication.model.Question;
import com.syed.quizApplication.repository.QuestionRepository;
import com.syed.quizApplication.serviceInterface.QuestionServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionServiceInterface {

    QuestionRepository repository;

    public QuestionServiceImpl(QuestionRepository repository){
        this.repository=repository;
    }


    @Override
    public ResponseEntity<List<Question>> getAllQuestions() {
        try{
         return  new ResponseEntity<>(repository.findAll(), HttpStatus.OK);}
         catch (Exception e){
            e.printStackTrace();
         }
        return  new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String>  addQuestion(Question question){
        try {
            repository.save(question);
            return new ResponseEntity<>("SUCCESS",HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
        }
         return new ResponseEntity<>("Failed",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String>  addMultipleQuestions(List<Question> questions){
        try {
            repository.saveAll(questions);
            return new ResponseEntity<>("SUCCESS",HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new  ResponseEntity<>("Failed",HttpStatus.BAD_REQUEST);

    }

    @Override
    public ResponseEntity<List<Question>> getAllQuestionBasedOnCategory(String category) {
        try {
            return new ResponseEntity<>(repository.getAllByCategory(category),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);

    }

    @Override
    public ResponseEntity<Question> DeleteById(Integer id) {
        Optional<Question> deletedQuestion =repository.findById(id);
        try {
            repository.deleteById(id);
            return deletedQuestion.map(ResponseEntity::ok).get();
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(deletedQuestion.orElse(new Question()), HttpStatus.BAD_REQUEST);
    }

}

