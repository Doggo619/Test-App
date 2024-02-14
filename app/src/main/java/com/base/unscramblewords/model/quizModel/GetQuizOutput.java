package com.base.unscramblewords.model.quizModel;

import com.base.unscramblewords.model.wordsModel.GetWordsOutput;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class GetQuizOutput {
    @SerializedName("quiz")
    private Quiz quiz;

    public GetQuizOutput(Quiz quiz) {
        this.quiz = quiz;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
    public static GetWordsOutput fromJson(String jsonString) {
        return new Gson().fromJson(jsonString, GetWordsOutput.class);
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
