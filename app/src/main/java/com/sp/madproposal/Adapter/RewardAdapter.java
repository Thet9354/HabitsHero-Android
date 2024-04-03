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
import com.sp.madproposal.Model.Reward;
import com.sp.madproposal.Model.Rewards;
import com.sp.madproposal.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RewardAdapter extends RecyclerView.Adapter<RewardAdapter.ViewHolder>{

    private List<Reward> rewardList;

    private Context mContext;


    public RewardAdapter(List<Reward> rewardList, Context mContext) {
        this.rewardList = rewardList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RewardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_history_task, parent, false);
        return new RewardAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RewardAdapter.ViewHolder holder, int position) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String currentDate = dateFormat.format(new Date());

        Reward reward = rewardList.get(position);
        holder.txtView_taskName.setText(reward.getRewardName());
        holder.txtView_taskDate.setText(currentDate);

        holder.ll_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, HistoryDetailActivity.class);
                int pos = holder.getAdapterPosition();

                intent.putExtra("Name", rewardList.get(pos).getRewardName());
                intent.putExtra("DateTime", rewardList.get(pos).getDateTime());

                mContext.startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount() {
        return rewardList.size();
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
