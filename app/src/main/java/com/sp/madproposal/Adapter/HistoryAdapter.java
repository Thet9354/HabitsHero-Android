package com.sp.madproposal.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sp.madproposal.HistoryDetailActivity;
import com.sp.madproposal.Model.Habit;
import com.sp.madproposal.R;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder>{

    private List<Habit> habitList;

    private Context mContext;


    public HistoryAdapter(List<Habit> habitList, Context mContext) {
        this.habitList = habitList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_history_task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder holder, int position) {
        Habit habit = habitList.get(position);
        holder.txtView_taskName.setText(habit.getHabitName());
        holder.txtView_taskDate.setText(habit.getDateTime());

        holder.ll_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, HistoryDetailActivity.class);
                int pos = holder.getAdapterPosition();

                intent.putExtra("Name", habitList.get(pos).getHabitName());
                intent.putExtra("DateTime", habitList.get(pos).getDateTime());

                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return habitList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtView_taskName;
        public TextView txtView_taskDate;
        public LinearLayout ll_history;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtView_taskName = itemView.findViewById(R.id.txtView_taskName);
            txtView_taskDate = itemView.findViewById(R.id.txtView_taskDate);
            ll_history = itemView.findViewById(R.id.ll_history);
        }
    }
}
