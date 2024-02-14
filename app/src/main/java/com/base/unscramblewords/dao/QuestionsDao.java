package com.base.unscramblewords.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.base.unscramblewords.entity.WordsEntity;
import com.base.unscramblewords.entity.quizEntity.Questions;

import java.util.List;

@Dao
public interface QuestionsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Questions questions);
    @Query("SELECT * FROM QUESTIONS")
    LiveData<List<Questions>> getAllQuestions();
}
