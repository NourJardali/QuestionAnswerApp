package com.advancedapps.questionanswerapp;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class AnswerViewModel extends AndroidViewModel {
    private AnswerRepository mRepository;
    private LiveData<List<Answer>> mAllAnswers;

    public AnswerViewModel(Application application){
        super(application);
        mRepository = new AnswerRepository(application);
        mAllAnswers = mRepository.getAllAnswers();
    }

    public LiveData<List<Answer>> getAllAnswers() {
        return mAllAnswers;
    }

    public void insert(Answer answer){
        mRepository.insert(answer);
    }


}
