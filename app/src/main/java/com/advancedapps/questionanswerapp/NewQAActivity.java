package com.advancedapps.questionanswerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewQAActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY_QUESTION =
            "com.example.android.questionanswersample.REPLY.QUESTION";
    public static final String EXTRA_REPLY_ANSWER1 =
            "com.example.android.questionanswersample.REPLY.ANSWER1";
    public static final String EXTRA_REPLY_ANSWER2 =
            "com.example.android.questionanswersample.REPLY.ANSWER2";
    public static final String EXTRA_REPLY_ANSWER3 =
            "com.example.android.questionanswersample.REPLY.ANSWER3";
    public static final String EXTRA_REPLY_ANSWER4 =
            "com.example.android.questionanswersample.REPLY.ANSWER4";

    private EditText mQuestion, mAnswer1, mAnswer2, mAnswer3, mAnswer4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_qa);

        mQuestion = findViewById(R.id.edit_question);
        mAnswer1 = findViewById(R.id.edit_answer1);
        mAnswer2 = findViewById(R.id.edit_answer2);
        mAnswer3 = findViewById(R.id.edit_answer3);
        mAnswer4 = findViewById(R.id.edit_answer4);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();
                if(TextUtils.isEmpty(mQuestion.getText()) ||
                        TextUtils.isEmpty(mAnswer1.getText()) ||
                        TextUtils.isEmpty(mAnswer2.getText()) ||
                        TextUtils.isEmpty(mAnswer3.getText()) ||
                        TextUtils.isEmpty(mAnswer4.getText())){
                    setResult(RESULT_CANCELED, replyIntent);
                }
                else{
                    String question = mQuestion.getText().toString();
                    String ans1 = mAnswer1.getText().toString();
                    String ans2 = mAnswer2.getText().toString();
                    String ans3 = mAnswer3.getText().toString();
                    String ans4 = mAnswer4.getText().toString();

                    replyIntent.putExtra(EXTRA_REPLY_QUESTION, question);
                    replyIntent.putExtra(EXTRA_REPLY_ANSWER1, ans1);
                    replyIntent.putExtra(EXTRA_REPLY_ANSWER2, ans2);
                    replyIntent.putExtra(EXTRA_REPLY_ANSWER3, ans3);
                    replyIntent.putExtra(EXTRA_REPLY_ANSWER4, ans4);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}