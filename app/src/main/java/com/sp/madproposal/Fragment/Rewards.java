package com.sp.madproposal.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sp.madproposal.MainActivity;
import com.sp.madproposal.R;
import com.sp.madproposal.utilities.Constants;
import com.sp.madproposal.utilities.PreferenceManager;

import java.util.HashMap;

public class Rewards extends Fragment {

    private Context mContext;

    private CheckBox cb_reward1, cb_reward2, cb_reward3, cb_reward4, cb_reward5, cb_reward6;

    private TextView txtView_totalPoints;

    FirebaseDatabase database = FirebaseDatabase.getInstance("Your firebase url here");
    DatabaseReference databaseReference  = database.getReference().child("users");

    private String mPhoneNumber = "93542856";

    private String userReward = "User's Reward";

    com.sp.madproposal.Model.Rewards rewards;


    private Button btn_redeem;

    private int totalPoints = 100, redeemPoints = 0;

    private Boolean selectedReward1 = false;
    private Boolean selectedReward2 = false;
    private Boolean selectedReward3 = false;
    private Boolean selectedReward4 = false;
    private Boolean selectedReward5 = false;
    private Boolean selectedReward6 = false;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_rewards, container, false);

        mContext = getActivity();

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
    }

    private void findViews(View v) {

        cb_reward1 = v.findViewById(R.id.cb_reward1);
        cb_reward2 = v.findViewById(R.id.cb_reward2);
        cb_reward3 = v.findViewById(R.id.cb_reward3);
        cb_reward4 = v.findViewById(R.id.cb_reward4);
        cb_reward5 = v.findViewById(R.id.cb_reward5);
        cb_reward6 = v.findViewById(R.id.cb_reward6);

        txtView_totalPoints = v.findViewById(R.id.txtView_totalPoints);

        btn_redeem = v.findViewById(R.id.btn_redeem);


        getIntentData();

        initUI();

        pageDirectories();
    }

    private void getIntentData() {

        try {
            mPhoneNumber = getArguments().getString("FatherNumber");
            System.out.println("Habits " + mPhoneNumber);
        } catch (Exception e) {
            System.out.println("Error here");
        }

        if (mPhoneNumber.isEmpty() | mPhoneNumber == "") {
            mPhoneNumber = "93542856";
        }
    }

    private void initUI() {

        txtView_totalPoints.setText(String.valueOf(totalPoints));
    }

    private void pageDirectories() {

        cb_reward1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    redeemPoints += 10;
                    selectedReward1 = true;
                } else {
                    redeemPoints = 0;
                    selectedReward1 = false;
                }
            }
        });

        cb_reward2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    redeemPoints += 20;
                    selectedReward2 = true;
                }
                else {
                    redeemPoints = 0;
                    selectedReward2 = false;
                }
            }
        });

        cb_reward3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    redeemPoints += 30;
                    selectedReward3 = true;
                }
                else {
                    redeemPoints = 0;
                    selectedReward3 = false;
                }
            }
        });

        cb_reward4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    redeemPoints += 40;
                    selectedReward4 = true;
                }
                else {
                    redeemPoints = 0;
                    selectedReward4 = false;
                }
            }
        });

        cb_reward5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    redeemPoints += 50;
                    selectedReward5 = true;
                }
                else {
                    redeemPoints = 0;
                    selectedReward5 = false;
                }
            }
        });

        cb_reward6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    redeemPoints += 60;
                    selectedReward6 = true;
                }
                else {
                    redeemPoints = 0;
                    selectedReward6 = false;
                }
            }
        });

        btn_redeem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalPoints < redeemPoints) {
                    // Insufficient points to claim selected reward
//                    totalPoints = totalPoints - redeemPoints;
                    Toast.makeText(mContext, "Insufficient points to redeem", Toast.LENGTH_SHORT).show();
                } else {
                    // Deduct the redeem points from the total points
                    totalPoints = totalPoints - redeemPoints;
                    txtView_totalPoints.setText(String.valueOf(totalPoints));

                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.child(mPhoneNumber).hasChild(userReward)) {
                                // Means rewards are already exist, just need update
                                updateData();
                            } else {
                                // Means need to create the child reward and add value for first time in firebase
                                addData();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
    }

    private void addData() {

        databaseReference.child(mPhoneNumber).child(userReward).child(Constants.REWARD_1).setValue(selectedReward1);
        databaseReference.child(mPhoneNumber).child(userReward).child(Constants.REWARD_2).setValue(selectedReward2);
        databaseReference.child(mPhoneNumber).child(userReward).child(Constants.REWARD_3).setValue(selectedReward3);
        databaseReference.child(mPhoneNumber).child(userReward).child(Constants.REWARD_4).setValue(selectedReward4);
        databaseReference.child(mPhoneNumber).child(userReward).child(Constants.REWARD_5).setValue(selectedReward5);
        databaseReference.child(mPhoneNumber).child(userReward).child(Constants.REWARD_6).setValue(selectedReward6);

        databaseReference.child(mPhoneNumber).child(userReward).child(Constants.TOTAL_POINTS).setValue(totalPoints);
        databaseReference.child(mPhoneNumber).child(userReward).child(Constants.REDEEM_POINTS).setValue(redeemPoints);

    }

    private void updateData() {

        HashMap reward = new HashMap();
        reward.put(Constants.TOTAL_POINTS, totalPoints);
        reward.put(Constants.REDEEM_POINTS, redeemPoints);

        reward.put(Constants.REWARD_1, selectedReward1);
        reward.put(Constants.REWARD_2, selectedReward2);
        reward.put(Constants.REWARD_3, selectedReward3);
        reward.put(Constants.REWARD_4, selectedReward4);
        reward.put(Constants.REWARD_5, selectedReward5);
        reward.put(Constants.REWARD_6, selectedReward6);

        databaseReference.child(mPhoneNumber).child(userReward).updateChildren(reward).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {

                if (task.isSuccessful()) {
                    Toast.makeText(mContext, "Habits Logged Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "Habits logged unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}