package com.sp.madproposal.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sp.madproposal.R;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.CardViewHolder>{


    @NonNull
    @Override
    public TaskAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = LayoutInflater.from(context).
                inflate(R.layout.row_history_task, parent, false);

        return new TaskAdapter.CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.CardViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
