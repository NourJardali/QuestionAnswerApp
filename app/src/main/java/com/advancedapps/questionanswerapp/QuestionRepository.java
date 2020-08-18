package com.advancedapps.questionanswerapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class QuestionRepository {
    private QuestionQao mQuestionDao;
    private LiveData<List<Question>> mAllQuestions;

    QuestionRepository(Application application){
        QADatabase db = QADatabase.getDatabase(application);
        mQuestionDao = db.getQuestionDao();
        mAllQuestions = mQuestionDao.getAllQuestions();
    }

    public LiveData<List<Question>> getAllQuestions() {
        return mAllQuestions;
    }

    public void insert(Question question){
        new insertAsyncTask(mQuestionDao).execute(question);
    }

    private static class insertAsyncTask extends AsyncTask<Question, Void, Void>{
        private QuestionQao mAsyncTaskDao;

        insertAsyncTask(QuestionQao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Question... questions) {
            mAsyncTaskDao.insert(questions[0]);
            return null;
        }
    }
}
