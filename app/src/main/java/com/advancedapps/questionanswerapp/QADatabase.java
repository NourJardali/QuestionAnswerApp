package com.advancedapps.questionanswerapp;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {
        Answer.class, Question.class},
        version = 2,
    exportSchema = false)
public abstract class QADatabase extends RoomDatabase {
    private static QADatabase INSTANCE;

    public abstract AnswerDao getAnswerDao();

    public abstract QuestionQao getQuestionDao();

    static QADatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (QADatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            QADatabase.class, "qa_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){
                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    //Populate the database in the background
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void>{
        private final AnswerDao mAnswerDao;
        private final QuestionQao mQuestionDao;

        PopulateDbAsync(QADatabase db){
            mAnswerDao = db.getAnswerDao();
            mQuestionDao = db.getQuestionDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            // Start the app with a clean database every time.
            // Not needed if you only populate the database
            // when it is first created
            mAnswerDao.deleteAll();
            mQuestionDao.deleteAll();

            mQuestionDao.insert(new Question("1", "What are the advantages of using a Room database?"));
            mAnswerDao.insert(new Answer("1", "Creates and manages an Android SQLite database for you.", "1"));
            mAnswerDao.insert(new Answer("2", "Eliminates a lot of boilerplate code.", "1"));
            mAnswerDao.insert(new Answer("3", "Helps you manage multiple backends.", "1"));
            mAnswerDao.insert(new Answer("4", "Using a DAO, provides a mechanism for mapping Java methods to database queries.", "1"));

            mQuestionDao.insert(new Question("2", "Which of the following are reasons for using a ViewModel?"));
            mAnswerDao.insert(new Answer("5", "Cleanly separates the UI from the backend.", "2"));
            mAnswerDao.insert(new Answer("6", "Often used with LiveData for changeable data that the UI will use or display.", "2"));
            mAnswerDao.insert(new Answer("7", "Prevents your data from being lost when the app crashes.", "2"));
            mAnswerDao.insert(new Answer("8", "Acts as a communication center between the Repository and the UI.", "2"));
            mAnswerDao.insert(new Answer("9", "ViewModel instances survive device configuration changes.", "2"));

            return null;
        }
    }
}
