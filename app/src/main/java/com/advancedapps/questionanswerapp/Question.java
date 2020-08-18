package com.advancedapps.questionanswerapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "question_table")
public class Question {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String mId;

    @NonNull
    @ColumnInfo(name = "question")
    private String mQuestion;

    public Question(@NonNull String id, @NonNull String question){
        this.mId = id;
        this.mQuestion = question;
    }

    public String getQuestion() {
        return mQuestion;
    }

    @NonNull
    public String getId() {
        return mId;
    }
}
