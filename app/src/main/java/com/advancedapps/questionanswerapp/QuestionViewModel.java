package com.advancedapps.questionanswerapp;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class QuestionViewModel extends AndroidViewModel {
    private QuestionRepository mRepository;
    private LiveData<List<Question>> mAllQuestions;

    public QuestionViewModel(Application application){
        super(application);
        mRepository = new QuestionRepository(application);
        mAllQuestions = mRepository.getAllQuestions();
    }

    public LiveData<List<Question>> getAllQuestions() {
        return mAllQuestions;
    }

    public void insert(Question question){
        mRepository.insert(question);
    }
}
