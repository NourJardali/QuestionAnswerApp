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
public interface QuestionQao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Question question);

    @Update
    void update(Question question);

    @Delete
    void delete(Question question);

    @Query("DELETE FROM question_table")
    void deleteAll();

    @Query("SELECT * FROM question_table")
    LiveData<List<Question>> getAllQuestions();
}
