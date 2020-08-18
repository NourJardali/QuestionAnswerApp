package com.advancedapps.questionanswerapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class AnswerRepository {
    private AnswerDao mAnswerDao;
    private LiveData<List<Answer>> mAllAnswers;

    AnswerRepository(Application application){
        QADatabase db = QADatabase.getDatabase(application);
        mAnswerDao = db.getAnswerDao();
        mAllAnswers = mAnswerDao.getAllAnswers();
    }

    public LiveData<List<Answer>> getAllAnswers() {
        return mAllAnswers;
    }

    public void insert(Answer answer){
        new insertAsyncTask(mAnswerDao).execute(answer);
    }

    private static class insertAsyncTask extends AsyncTask<Answer, Void, Void> {
        private AnswerDao mAsyncTaskDao;

        insertAsyncTask(AnswerDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Answer... answers) {
            mAsyncTaskDao.insert(answers[0]);
            return null;
        }
    }
}
