package com.base.unscramblewords.model.quizModel;

import com.google.gson.annotations.SerializedName;

public class Student {
    @SerializedName("answer1")
    private String answer1;
    @SerializedName("answer2")
    private String answer2;
    @SerializedName("timetaken")
    private int timetaken;

    public Student(String answer1, String answer2, int timetaken) {
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.timetaken = timetaken;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public int getTimetaken() {
        return timetaken;
    }

    public void setTimetaken(int timetaken) {
        this.timetaken = timetaken;
    }
}
