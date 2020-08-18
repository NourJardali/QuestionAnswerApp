package com.advancedapps.questionanswerapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AnswerListAdapter extends RecyclerView.Adapter<AnswerListAdapter.AnswerViewHolder> {
    private final LayoutInflater mInflater;
    private List<Answer> mAnswers; //Cached copy of answers

    AnswerListAdapter(Context context){
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public AnswerListAdapter.AnswerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.answers_item, parent, false);
        return new AnswerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AnswerListAdapter.AnswerViewHolder holder, int position) {
        if(mAnswers != null){
            Answer cur = mAnswers.get(position);
            holder.answerItemView.setText(cur.getAnswer());
        }
        else{
            //Covers the case of data not being ready yet
            holder.answerItemView.setText("No Answer");
        }
    }

    public void setAnswers(List<Answer> mAnswers) {
        this.mAnswers = mAnswers;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mAnswers != null ? mAnswers.size() : 0;
    }

    class AnswerViewHolder extends RecyclerView.ViewHolder {
        private final TextView answerItemView;

        private AnswerViewHolder(View itemView){
            super(itemView);
            answerItemView = itemView.findViewById(R.id.tvAnswer);
        }
    }
}
