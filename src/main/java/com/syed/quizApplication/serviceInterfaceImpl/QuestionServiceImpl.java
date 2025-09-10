package com.syed.quizApplication.serviceInterfaceImpl;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syed.quizApplication.customExceptions.QuestionNotFoundException;
import com.syed.quizApplication.model.Question;
import com.syed.quizApplication.repository.QuestionRepository;
import com.syed.quizApplication.serviceInterface.QuestionServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;

@Service
public class QuestionServiceImpl implements QuestionServiceInterface {

    QuestionRepository repository;

    public QuestionServiceImpl(QuestionRepository repository){
        this.repository=repository;
    }

    @Autowired
    private ObjectMapper objectMapper;


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

       Question question = repository.findById(id).orElseThrow(() ->new QuestionNotFoundException(id));


       repository.deleteById(id);
       return ResponseEntity.status(HttpStatus.OK).body(question);



    }

    @Override
    public Question updateBody(Integer id, Question question) {

        return repository.findById(id)
                .map(updateUser->{
                    question.setId(id);
                    return repository.save(question);
                }).orElseThrow(()->new QuestionNotFoundException(id));
    }

    @Override
    public Question updatePartialBody(Integer id, Map<String, Object> fields) {

        Question question =repository.findById(id)
                .orElseThrow(()->new QuestionNotFoundException(id));

            try {
                objectMapper.updateValue(question,fields);
            } catch (JsonMappingException e) {
                throw new RuntimeException(e);
            }


        return repository.save(question);

//        if(question.isPresent()){
//            fields.forEach((key,value)->{
//                Field field= ReflectionUtils.findField(Question.class,key);
//                                field.setAccessible(true);
//                ReflectionUtils.setField(field,question,value);
//            });
//            return repository.save(question.get());
//        }
//            return question.orElseThrow(()->new QuestionNotFoundException(id));
    }

}

