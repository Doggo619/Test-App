package com.base.unscramblewords.model.quizModel;

import com.google.gson.annotations.SerializedName;

public class Quiz {
    @SerializedName("delta")
    private Delta delta;

    public Quiz(Delta delta) {
        this.delta = delta;
    }

    public Delta getDelta() {
        return delta;
    }

    public void setDelta(Delta delta) {
        this.delta = delta;
    }
}
