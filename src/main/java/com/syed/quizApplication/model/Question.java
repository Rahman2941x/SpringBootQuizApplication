package com.syed.quizApplication.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.hibernate.annotations.Cache;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String questionTitle;
    @Column(name ="option_A")
    private String optionA;
    @Column(name ="option_B")
    private String optionB;
    @Column(name ="option_C")
    private String optionC;
    @Column(name ="option_D")
    private String optionD;
    @Column(name ="correct_Answer")
    private String correctAnswer;

    private String category;

    @Enumerated(EnumType.STRING)
    private DifficultyLevel difficultyLevel;



    public Question() {
    }




    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", questionTitle='" + questionTitle + '\'' +
                ", optionA='" + optionA + '\'' +
                ", optionB='" + optionB + '\'' +
                ", optionC='" + optionC + '\'' +
                ", optionD='" + optionD + '\'' +
                ", correctAnwser=" + correctAnswer +
                ", category='" + category + '\'' +
                ", difficultyLevel=" + difficultyLevel +
                '}';
    }



    public Question(Integer id, String questionTitle, String optionA, String optionB, String optionC, String optionD, String correctAnwser, String category, DifficultyLevel difficultyLevel) {
        this.id = id;
        this.questionTitle = questionTitle;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctAnswer = correctAnwser;
        this.category = category;
        this.difficultyLevel = difficultyLevel;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }



    public String getOptionD() {
        return optionD;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }
}
