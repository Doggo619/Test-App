package com.base.unscramblewords.model.quizModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class Delta {
    @SerializedName("questions")
    private List<Question> questions;
    @SerializedName("students")
    private Map<String, Student> students;

    public Delta(List<Question> questions, Map<String, Student> students) {
        this.questions = questions;
        this.students = students;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Map<String, Student> getStudents() {
        return students;
    }

    public void setStudents(Map<String, Student> students) {
        this.students = students;
    }
}
