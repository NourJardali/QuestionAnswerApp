package com.advancedapps.questionanswerapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AnswerDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Answer answer);

    @Update
    void update(Answer answer);

    @Delete
    void delete(Answer answer);

    @Query("SELECT * FROM answer_table")
    LiveData<List<Answer>> getAllAnswers();

    @Query("SELECT * FROM answer_table WHERE questionId=:questionId")
    LiveData<List<Answer>> findAnswersforQuestion(final int questionId);

    @Query("DELETE FROM answer_table")
    void deleteAll();
}