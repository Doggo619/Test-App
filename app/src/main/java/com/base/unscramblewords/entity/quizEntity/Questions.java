package com.base.unscramblewords.entity.quizEntity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "QUESTIONS", indices = {@Index(name = "INDEX_QUESTIONS", value = {"questionId"},
        unique = true)} )
public class Questions {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "questionId")
    private int questionId;
    @ColumnInfo(name = "question")
    private String question;
    @ColumnInfo(name = "option1")
    private String option1;
    @ColumnInfo(name = "option2")
    private String option2;
    @ColumnInfo(name = "option3")
    private String option3;
    @ColumnInfo(name = "option4")
    private String option4;
    @ColumnInfo(name = "correctAnswer")
    private String correctAnswer;
    @ColumnInfo(name = "givenAnswer")
    private String givenAnswer;

    public Questions(int questionId, String question, String option1, String option2, String option3, String option4, String correctAnswer) {
        this.questionId = questionId;
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.correctAnswer = correctAnswer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getGivenAnswer() {
        return givenAnswer;
    }

    public void setGivenAnswer(String givenAnswer) {
        this.givenAnswer = givenAnswer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Questions questions = (Questions) o;
        return id == questions.id && questionId == questions.questionId && Objects.equals(question, questions.question) && Objects.equals(option1, questions.option1) && Objects.equals(option2, questions.option2) && Objects.equals(option3, questions.option3) && Objects.equals(option4, questions.option4) && Objects.equals(correctAnswer, questions.correctAnswer) && Objects.equals(givenAnswer, questions.givenAnswer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, questionId, question, option1, option2, option3, option4, correctAnswer, givenAnswer);
    }
}
