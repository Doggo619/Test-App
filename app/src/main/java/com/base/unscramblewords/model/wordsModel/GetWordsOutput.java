package com.base.unscramblewords.model.wordsModel;

import com.google.gson.Gson;

import java.util.List;

public class GetWordsOutput {
    private String word;
    private List<Meaning> meanings;

    public GetWordsOutput(String word, List<Meaning> meanings) {
        this.word = word;
        this.meanings = meanings;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<Meaning> getMeanings() {
        return meanings;
    }

    public void setMeanings(List<Meaning> meanings) {
        this.meanings = meanings;
    }
    public static GetWordsOutput fromJson(String jsonString) {
        return new Gson().fromJson(jsonString, GetWordsOutput.class);
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
