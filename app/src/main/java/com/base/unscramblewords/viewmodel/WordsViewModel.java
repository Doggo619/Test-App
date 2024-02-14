package com.base.unscramblewords.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.base.unscramblewords.entity.WordsEntity;
import com.base.unscramblewords.entity.quizEntity.Questions;
import com.base.unscramblewords.repository.WordsRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class WordsViewModel extends BaseViewModel {
    private WordsRepository wordsRepository;

    @Inject
    public WordsViewModel(WordsRepository wordsRepository) {
        super(wordsRepository);
        this.wordsRepository = wordsRepository;
    }

    public void insert(WordsEntity wordsEntity) {
        wordsRepository.insert(wordsEntity);
    }
    public LiveData<List<WordsEntity>> getAllWords() {
        return wordsRepository.getAllWords();
    }
    public LiveData<Integer> getWordCount(String word) {
        return wordsRepository.getWordCount(word);
    }
    public void insertQuestions() {
        Questions question1 = new Questions(
                201,
                "Who will win the ICC T20 WC 2024?",
                "Australia",
                "India",
                "Windies",
                "Sri Lanka",
                "India"
        );
            wordsRepository.insertQuestions(question1);
            Questions question2 = new Questions(
                    202,
                    "When was Chandrayaan-3 launched?",
                    "2022",
                    "2023",
                    "2021",
                    "2020",
                    "2023"
            );
        wordsRepository.insertQuestions(question2);
    }
    public LiveData<List<Questions>> getAllQuestions() {
        return wordsRepository.getAllQuestions();
    }
}
