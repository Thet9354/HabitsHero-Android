package com.sp.madproposal.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sp.madproposal.Model.User;
import com.sp.madproposal.R;
import com.sp.madproposal.listeners.UserListener;

import java.util.Base64;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.CardViewHolder>{

    private final List<User> users;
    private final UserListener userListener;

    private Context mContext;


    public UsersAdapter(List<User> users, UserListener userListener) {
        this.users = users;
        this.userListener = userListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }



    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    @NonNull
    @Override
    public UsersAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = LayoutInflater.from(context).
                inflate(R.layout.item_container_user, parent, false);

        return new UsersAdapter.CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersAdapter.CardViewHolder holder, int position) {
        User user = users.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userListener.onUserClicked(user);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
