package com.base.unscramblewords.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.base.unscramblewords.dao.QuestionsDao;
import com.base.unscramblewords.dao.WordsDao;
import com.base.unscramblewords.entity.WordsEntity;
import com.base.unscramblewords.entity.quizEntity.Questions;

@Database(entities = {WordsEntity.class, Questions.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract WordsDao wordsDao();
    public abstract QuestionsDao questionsDao();
    private static volatile AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    "APP_DATABASE"
            ).fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
