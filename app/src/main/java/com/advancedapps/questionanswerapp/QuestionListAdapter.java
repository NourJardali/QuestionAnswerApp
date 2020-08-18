package com.advancedapps.questionanswerapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class QuestionListAdapter extends RecyclerView.Adapter<QuestionListAdapter.QuestionViewHolder> {
    private final LayoutInflater mInflator;
    private List<Question> mQuestions;
    private AnswerListener mAnswerListener;

    QuestionListAdapter(Context context){
        mInflator = LayoutInflater.from(context);
    }

    public void setAnswerListener(AnswerListener mAnswerListener) {
        this.mAnswerListener = mAnswerListener;
    }

    @NonNull
    @Override
    public QuestionListAdapter.QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflator.inflate(R.layout.questions_item, parent, false);
        return new QuestionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionListAdapter.QuestionViewHolder holder, int position) {
        if(mQuestions != null){
            Question curr = mQuestions.get(position);
            holder.questionItemView.setText(curr.getQuestion());
        }
        else{
            holder.questionItemView.setText("No Question");
        }
    }

    public void setQuestions(List<Question> mQuestions) {
        this.mQuestions = mQuestions;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mQuestions != null ? mQuestions.size() : 0;
    }

    class QuestionViewHolder extends RecyclerView.ViewHolder {
        private final TextView questionItemView;

        private QuestionViewHolder(View itemView){
            super(itemView);
            questionItemView = itemView.findViewById(R.id.tvQuestion);

            questionItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAnswerListener.showAnswers(mQuestions.get(getAdapterPosition()).getId());
                }
            });
        }
    }
}
