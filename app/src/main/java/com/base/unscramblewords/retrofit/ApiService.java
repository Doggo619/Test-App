package com.base.unscramblewords.retrofit;


import com.base.unscramblewords.model.wordsModel.GetWordsOutput;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("api/v2/entries/en/{word}")
    Call<List<GetWordsOutput>> getWordsOutput(
            @Path("word") String word
    );

}
