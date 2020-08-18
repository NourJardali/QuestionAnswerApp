package com.advancedapps.questionanswerapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "answer_table",
        foreignKeys = @ForeignKey(entity = Answer.class,
        parentColumns = "id",
        childColumns = "questionId",
        onDelete = CASCADE),
        indices = @Index(value = "questionId"))
public class Answer {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String mId;

    @NonNull
    @ColumnInfo(name = "answer")
    private String mAnswer;

    @NonNull
    @ColumnInfo(name = "questionId")
    public final String mQuestionId;

    public Answer(@NonNull final String id, @NonNull String answer, @NonNull final String questionId){
        mId = id;
        mAnswer = answer;
        mQuestionId = questionId;
    }

    public String getAnswer() {
        return mAnswer;
    }

    @NonNull
    public String getId() {
        return mId;
    }

    @NonNull
    public String getQuestionId() {
        return mQuestionId;
    }
}