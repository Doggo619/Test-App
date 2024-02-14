package com.base.unscramblewords.model.quizModel;

import com.google.gson.annotations.SerializedName;

public class Question {
    @SerializedName("question1")
    private String question1;
    @SerializedName("option1")
    private String option1;
    @SerializedName("option2")
    private String option2;
    @SerializedName("option3")
    private String option3;
    @SerializedName("option4")
    private String option4;

    public Question(String question1, String option1, String option2, String option3, String option4) {
        this.question1 = question1;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
    }

    public String getQuestion1() {
        return question1;
    }

    public void setQuestion1(String question1) {
        this.question1 = question1;
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
}
