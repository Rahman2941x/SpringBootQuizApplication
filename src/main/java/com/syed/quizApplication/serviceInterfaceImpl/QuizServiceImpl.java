package com.syed.quizApplication.serviceInterfaceImpl;

import com.syed.quizApplication.model.Question;
import com.syed.quizApplication.model.Quiz;
import com.syed.quizApplication.model.QuizQuestions;
import com.syed.quizApplication.model.Responses;
import com.syed.quizApplication.repository.QuestionRepository;
import com.syed.quizApplication.repository.QuizRepository;
import com.syed.quizApplication.serviceInterface.QuizServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceImpl implements QuizServiceInterface {

    QuizRepository quizRepository;
    QuestionRepository questionRepository;


    public QuizServiceImpl(  QuizRepository quizRepository,QuestionRepository questionRepository){
        this.quizRepository=quizRepository;
        this.questionRepository=questionRepository;
    }






   public ResponseEntity<String> createRandomQuizQuestion(Integer numQ, String title, String category){

        try{
        List<Question> questions =questionRepository.findRandomQuestionByCategory(category,numQ);
            if (questions.isEmpty()) {
                throw new RuntimeException("No questions found for category: " + category);
            }
       Quiz quiz= new Quiz();
       quiz.setTitle(title);
       quiz.setQuestion(questions);
       quizRepository.save(quiz);
            return new ResponseEntity<>("Quiz CREATED", HttpStatusCode.valueOf(201));
        }
        catch (Exception e){
            e.printStackTrace();
        }
       return new ResponseEntity<>("Quiz Not Created", HttpStatus.BAD_REQUEST);

    }

    @Override
    public  ResponseEntity<String> createRandomQuizQuestionBasedOnDifficultylevel(Integer numQ, String title, String category, String difficulty_level) {

        try{
            List<Question> questions = questionRepository.findRandomQuestionByCategoryAndDifficultyLevel(numQ,category,difficulty_level);

            Quiz quiz=new Quiz();
            quiz.setTitle(title);
            quiz.setQuestion(questions);
            quizRepository.save(quiz);

            return new ResponseEntity<>("Quiz CREATED", HttpStatusCode.valueOf(201));
        }catch (Exception e){
            e.printStackTrace();
        }



        return new ResponseEntity<>("Quiz Not Created", HttpStatus.BAD_REQUEST);

    }

    @Override
    public ResponseEntity<List<QuizQuestions>> GetQuizQuestion(Integer id) {
        Optional<Quiz> quiz=quizRepository.findById(id);
        List<Question> questions =quiz.get().getQuestion();
        List<QuizQuestions> getquizQuestions = new ArrayList<>();
        for(Question qus:questions){
            QuizQuestions quizQuestions = new QuizQuestions(qus.getId(),qus.getQuestionTitle(),qus.getOptionA(),qus.getOptionB(),qus.getOptionC(),qus.getOptionD());
            getquizQuestions.add(quizQuestions);
        }


        return  new ResponseEntity<>(getquizQuestions,HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Integer> GetQuizResult(Integer id, List<Responses> responses) {

        Quiz quiz=quizRepository.findById(id).get();
        List<Question> question=quiz.getQuestion();
        Integer right=0;
        int i=0;

        for(Responses response :responses){
            if(response.getResponse().equals(question.get(i).getCorrectAnswer()))
                right++;

            i++;
        }

        return new ResponseEntity<>(right,HttpStatus.OK);
    }

}
