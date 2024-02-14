package com.base.unscramblewords.repository;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import androidx.lifecycle.LiveData;

import com.base.unscramblewords.dao.QuestionsDao;
import com.base.unscramblewords.dao.WordsDao;
import com.base.unscramblewords.database.AppDatabase;
import com.base.unscramblewords.entity.WordsEntity;
import com.base.unscramblewords.entity.quizEntity.Questions;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

public class WordsRepository {
    private Application application;
    private Context context;
    private WordsDao wordsDao;
    private QuestionsDao questionsDao;
    Handler handler = new Handler();
    ExecutorService databaseWriteExecutor = Executors.newSingleThreadExecutor();

    @Inject
    public WordsRepository(Application application, Context context) {
        this.application = application;
        this.context = context;
        AppDatabase database = AppDatabase.getInstance(context);
        wordsDao = database.wordsDao();
        questionsDao = database.questionsDao();
    }

    public void insert(WordsEntity wordsEntity) {
        databaseWriteExecutor.execute(() -> {
            wordsDao.insert(wordsEntity);
        });
    }

    public LiveData<List<WordsEntity>> getAllWords() {
       return wordsDao.getAllWords();
    }
    public LiveData<Integer> getWordCount(String word) {
        databaseWriteExecutor.execute(() -> {
        });
        return wordsDao.getWordCount(word);
    }

    public void insertQuestions(Questions questions) {
        databaseWriteExecutor.execute(() -> {
            questionsDao.insert(questions);
        });
    }
    public LiveData<List<Questions>> getAllQuestions() {
        return questionsDao.getAllQuestions();
    }

}
