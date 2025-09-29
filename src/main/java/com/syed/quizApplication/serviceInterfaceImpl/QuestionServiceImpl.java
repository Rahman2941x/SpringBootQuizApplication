package com.syed.quizApplication.serviceInterfaceImpl;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syed.quizApplication.controller.ApiResponse;
import com.syed.quizApplication.customExceptions.exceptions.CategoryNotFoundException;
import com.syed.quizApplication.customExceptions.exceptions.FieldNotFoundException;
import com.syed.quizApplication.customExceptions.exceptions.QuestionNotFoundException;
import com.syed.quizApplication.model.Question;
import com.syed.quizApplication.repository.QuestionRepository;
import com.syed.quizApplication.serviceInterface.QuestionServiceInterface;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class QuestionServiceImpl implements QuestionServiceInterface, InitializingBean, DisposableBean {



    private QuestionServiceImpl questionService;

    public QuestionServiceImpl(QuestionServiceImpl questionService){
        this.questionService=questionService;
        System.out.println("System created this 'questionServiceImpl'....");
    }

    // old version
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("After Bean created Operation()");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Before Bean Destroy Operation()");
    }

    // new version
    @PostConstruct
    public void customBeanAfterCreation(){
        System.out.println("After Bean created Operation()");
    }

    @PreDestroy
    public void customBeanBeforeDestroy(){
        System.out.println("Before Bean Destroy Operation()");
    }





    public QuestionServiceImpl(){}


   private QuestionRepository repository;

    public QuestionServiceImpl(QuestionRepository repository){
        this.repository=repository;
        System.out.println("System created this 'questionServiceImpl'....");
    }

    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public ResponseEntity<List<Question>> getAllQuestionss() {
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

       List<Question> questions= repository.findAllByCategory(category);
       if(questions.isEmpty())
           throw new CategoryNotFoundException(category);

       return ResponseEntity.status(HttpStatus.OK).body(questions);

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
    @Transactional
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

    @Override
    public ApiResponse<List<Question>> getQuestionWithSorted(String fields) {


        List<String> validField = Arrays.asList("id","category","correctAnswer","optionA","optionB","optionC","optionD","difficultyLevel","questionTitle");
        if(!validField.contains(fields)){
            throw  new FieldNotFoundException(fields);
        }

        List<Question> questions =repository.findAll(Sort.by(Sort.Direction.ASC,fields));
        if(questions.isEmpty()){
            throw  new FieldNotFoundException(fields);
        }

        return new ApiResponse<>(true,"Successfully sorted list fetched",questions);
    }

    @Override
    public ApiResponse<Page<Question>> getAllQuestionWithPaging(Integer offset, Integer size) {
      Pageable questionPage=  PageRequest.of(offset,size,Sort.by("id").ascending());
      Page<Question> questions=repository.findAll(questionPage);
      if(questions.isEmpty()) {
          throw new QuestionNotFoundException(Integer.MIN_VALUE);
      }
      return new ApiResponse<>(true,"Success",questions);
    }


}

