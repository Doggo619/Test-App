package com.base.unscramblewords.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.base.unscramblewords.entity.quizEntity.Questions;
import com.base.unscramblewords.repository.WordsRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class WordsViewModel extends BaseViewModel {
    private WordsRepository wordsRepository;
    private Application application;

    @Inject
    public WordsViewModel(WordsRepository wordsRepository) {
        super(wordsRepository);
        this.wordsRepository = wordsRepository;
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
        Questions question3 = new Questions(
                203,
                "What is the name of Rajinikanth in Annamalai??",
                "Annamalai",
                "Arunachalam",
                "Padaiyappa",
                "Muthu",
                "Annamalai"
        );
        wordsRepository.insertQuestions(question3);
        Questions question4 = new Questions(
                204,
                "How many centuries did Sachin Tendulkar score?",
                "200",
                "100",
                "45",
                "77",
                "100"
        );
        wordsRepository.insertQuestions(question4);
        Questions question5 = new Questions(
                205,
                "Will man on moon mission succeed?",
                "Yes",
                "No",
                "Maybe",
                "I don't care",
                "Yes"
        );
        wordsRepository.insertQuestions(question5);
    }

    //    public void insertQuestions(List<Questions> questions) {
//        wordsRepository.insertQuestions(questions);
//    }
    public LiveData<List<Questions>> getAllQuestions() {
        return wordsRepository.getAllQuestions();
    }
}
