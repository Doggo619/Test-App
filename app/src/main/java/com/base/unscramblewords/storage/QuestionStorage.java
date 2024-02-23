package com.base.unscramblewords.storage;

import com.base.unscramblewords.entity.quizEntity.Questions;

import java.util.ArrayList;
import java.util.List;

public class QuestionStorage {
    private static final QuestionStorage instance = new QuestionStorage();
    private List<Questions> questionsList = new ArrayList<>();

    private QuestionStorage() {
        // Private constructor to prevent instantiation outside of the class
    }
    public static QuestionStorage getInstance() {
        return instance;
    }
    public List<Questions> getQuestionsList() {
        return questionsList;
    }

    public void setQuestionsList(List<Questions> questions) {
        questionsList.clear();
        questionsList.addAll(questions);
    }
}
