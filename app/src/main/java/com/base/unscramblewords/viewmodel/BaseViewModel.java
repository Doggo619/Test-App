package com.base.unscramblewords.viewmodel;

import androidx.lifecycle.ViewModel;

import com.base.unscramblewords.repository.WordsRepository;

public abstract class BaseViewModel extends ViewModel {

    public WordsRepository wordsRepository;

    public BaseViewModel(WordsRepository wordsRepository) {
        this.wordsRepository = wordsRepository;
    }
}
