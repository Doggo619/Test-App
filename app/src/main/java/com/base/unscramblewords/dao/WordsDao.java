package com.base.unscramblewords.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.base.unscramblewords.entity.WordsEntity;

import java.util.List;

@Dao
public interface WordsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(WordsEntity wordsEntity);
    @Query("SELECT * FROM WORDS")
    LiveData<List<WordsEntity>> getAllWords();
    @Query("SELECT * FROM WORDS WHERE userId = 1")
    LiveData<List<WordsEntity>> getAllPlayerOneWords();
    @Query("SELECT * FROM WORDS WHERE userId = 2")
    LiveData<List<WordsEntity>> getAllPlayerTwoWords();

    @Query("SELECT COUNT(*) FROM WORDS WHERE words = :word")
    LiveData<Integer> getWordCount(String word);
}
