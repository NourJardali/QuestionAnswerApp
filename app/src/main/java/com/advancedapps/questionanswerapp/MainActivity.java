package com.advancedapps.questionanswerapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements AnswerListener {
    private QuestionViewModel mQuestionViewModel;

    private static int counter = 3;
    private static int counter2 = 10;

    public static final int NEW_QA_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewQAActivity.class);
                startActivityForResult(intent, NEW_QA_ACTIVITY_REQUEST_CODE);
            }
        });

        RecyclerView rvQuestions = findViewById(R.id.rvQuestions);
        final QuestionListAdapter adapter = new QuestionListAdapter(this);
        rvQuestions.setAdapter(adapter);
        rvQuestions.setLayoutManager(new LinearLayoutManager(this));

        mQuestionViewModel = ViewModelProviders.of(this).get(QuestionViewModel.class);
        mQuestionViewModel.getAllQuestions().observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(List<Question> questions) {
                adapter.setQuestions(questions);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == NEW_QA_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            assert data != null;
            Question question = new Question(counter + "", Objects.requireNonNull(data.getStringExtra(NewQAActivity.EXTRA_REPLY_QUESTION)));
            Answer ans1 = new Answer(counter2 + "", Objects.requireNonNull(data.getStringExtra(NewQAActivity.EXTRA_REPLY_ANSWER1)), counter + "");
            counter2++;
            Answer ans2 = new Answer(counter2 + "", Objects.requireNonNull(data.getStringExtra(NewQAActivity.EXTRA_REPLY_ANSWER2)), counter + "");
            counter2++;
            Answer ans3 = new Answer(counter2 + "", Objects.requireNonNull(data.getStringExtra(NewQAActivity.EXTRA_REPLY_ANSWER3)), counter + "");
            counter2++;
            Answer an4 = new Answer(counter2 + "", Objects.requireNonNull(data.getStringExtra(NewQAActivity.EXTRA_REPLY_ANSWER4)), counter + "");
            counter2++;
            counter++;
            mQuestionViewModel.insert(question);
        }
        else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void showAnswers(String questionId) {
        
    }
}
