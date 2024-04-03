package com.sp.madproposal.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sp.madproposal.Adapter.HistoryAdapter;
import com.sp.madproposal.Adapter.RewardAdapter;
import com.sp.madproposal.Model.Habit;
import com.sp.madproposal.Model.Reward;
import com.sp.madproposal.R;
import com.sp.madproposal.utilities.Constants;
import com.sp.madproposal.utilities.PreferenceManager;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class History extends Fragment {


    private Context mContext;

    private androidx.recyclerview.widget.RecyclerView rv_taskCompleted, rv_rewardRedemption;

    FirebaseDatabase database = FirebaseDatabase.getInstance("Your firebase url here");
    DatabaseReference databaseReference  = database.getReference().child("users");

    private String mPhoneNumber = "93542856";

    List<Habit> habitList = new ArrayList<>();

    List<Reward> rewardList = new ArrayList<>();

    private HistoryAdapter historyAdapter;
    private RewardAdapter rewardAdapter;


    private String userHabits = "User's Habits";
    private String userReward = "User's Reward";


    // Monday
    private Boolean checkedMonHabit1 = false;
    private Boolean checkedMonHabit2 = false;
    private Boolean checkedMonHabit3 = false;

    // Tuesday
    private Boolean checkedTuesHabit1 = false;
    private Boolean checkedTuesHabit2 = false;
    private Boolean checkedTuesHabit3 = false;

    // Wednesday
    private Boolean checkedWedHabit1 = false;
    private Boolean checkedWedHabit2 = false;
    private Boolean checkedWedHabit3 = false;

    // Thursday
    private Boolean checkedThurHabit1 = false;
    private Boolean checkedThurHabit2 = false;
    private Boolean checkedThurHabit3 = false;

    // Friday
    private Boolean checkedFriHabit1 = false;
    private Boolean checkedFriHabit2 = false;
    private Boolean checkedFriHabit3 = false;

    // Saturday
    private Boolean checkedSatHabit1 = false;
    private Boolean checkedSatHabit2 = false;
    private Boolean checkedSatHabit3 = false;

    // Sunday
    private Boolean checkedSunHabit1 = false;
    private Boolean checkedSunHabit2 = false;
    private Boolean checkedSunHabit3 = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_history, container, false);

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

        // RecyclerView
        rv_taskCompleted = v.findViewById(R.id.rv_taskCompleted);
        rv_rewardRedemption = v.findViewById(R.id.rv_rewardRedemption);

        getIntentData();

        initTaskRecView();

        initRewardRecView();

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

    private void pageDirectories() {

    }

    private void initRewardRecView() {

        // Retrieve all the reward data in firebase
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child(mPhoneNumber).hasChild(userReward)) {

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                    String currentDate = dateFormat.format(new Date());

                    final Boolean getReward1 = snapshot.child(mPhoneNumber).child(userReward).child(Constants.REWARD_1).getValue(Boolean.class);
                    final Boolean getReward2 = snapshot.child(mPhoneNumber).child(userReward).child(Constants.REWARD_2).getValue(Boolean.class);
                    final Boolean getReward3 = snapshot.child(mPhoneNumber).child(userReward).child(Constants.REWARD_3).getValue(Boolean.class);
                    final Boolean getReward4 = snapshot.child(mPhoneNumber).child(userReward).child(Constants.REWARD_4).getValue(Boolean.class);
                    final Boolean getReward5 = snapshot.child(mPhoneNumber).child(userReward).child(Constants.REWARD_5).getValue(Boolean.class);
                    final Boolean getReward6 = snapshot.child(mPhoneNumber).child(userReward).child(Constants.REWARD_6).getValue(Boolean.class);

                    if (getReward1)
                        rewardList.add(new Reward("Reward 1", true, currentDate));

                    if (getReward2)
                        rewardList.add(new Reward("Reward 2", true, currentDate));

                    if (getReward3)
                        rewardList.add(new Reward("Reward 3", true, currentDate));

                    if (getReward4)
                        rewardList.add(new Reward("Reward 4", true, currentDate));

                    if (getReward5)
                        rewardList.add(new Reward("Reward 5", true, currentDate));

                    if (getReward6)
                        rewardList.add(new Reward("Reward 6", true, currentDate));


                    rv_rewardRedemption.setLayoutManager(new LinearLayoutManager(mContext));
                    rewardAdapter = new RewardAdapter(rewardList, mContext);
                    rv_rewardRedemption.setAdapter(rewardAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initTaskRecView() {

        // Retrieve all the task habit data in firebase
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child(mPhoneNumber).hasChild(userHabits)) {

                    final Boolean getMonHabit1 = snapshot.child(mPhoneNumber).child(userHabits).child(Constants.CHECKED_MON_HABIT1).getValue(Boolean.class);
                    final Boolean getMonHabit2 = snapshot.child(mPhoneNumber).child(userHabits).child(Constants.CHECKED_MON_HABIT2).getValue(Boolean.class);
                    final Boolean getMonHabit3 = snapshot.child(mPhoneNumber).child(userHabits).child(Constants.CHECKED_MON_HABIT3).getValue(Boolean.class);

                    final Boolean getTuesHabit1 = snapshot.child(mPhoneNumber).child(userHabits).child(Constants.CHECKED_TUES_HABIT1).getValue(Boolean.class);
                    final Boolean getTuesHabit2 = snapshot.child(mPhoneNumber).child(userHabits).child(Constants.CHECKED_TUES_HABIT2).getValue(Boolean.class);
                    final Boolean getTuesHabit3 = snapshot.child(mPhoneNumber).child(userHabits).child(Constants.CHECKED_TUES_HABIT3).getValue(Boolean.class);

                    final Boolean getWedHabit1 = snapshot.child(mPhoneNumber).child(userHabits).child(Constants.CHECKED_WED_HABIT1).getValue(Boolean.class);
                    final Boolean getWedHabit2 = snapshot.child(mPhoneNumber).child(userHabits).child(Constants.CHECKED_WED_HABIT2).getValue(Boolean.class);
                    final Boolean getWedHabit3 = snapshot.child(mPhoneNumber).child(userHabits).child(Constants.CHECKED_WED_HABIT3).getValue(Boolean.class);

                    final Boolean getThurHabit1 = snapshot.child(mPhoneNumber).child(userHabits).child(Constants.CHECKED_THU_HABIT1).getValue(Boolean.class);
                    final Boolean getThurHabit2 = snapshot.child(mPhoneNumber).child(userHabits).child(Constants.CHECKED_THU_HABIT2).getValue(Boolean.class);
                    final Boolean getThurHabit3 = snapshot.child(mPhoneNumber).child(userHabits).child(Constants.CHECKED_THU_HABIT3).getValue(Boolean.class);

                    final Boolean getFriHabit1 = snapshot.child(mPhoneNumber).child(userHabits).child(Constants.CHECKED_FRI_HABIT1).getValue(Boolean.class);
                    final Boolean getFriHabit2 = snapshot.child(mPhoneNumber).child(userHabits).child(Constants.CHECKED_FRI_HABIT2).getValue(Boolean.class);
                    final Boolean getFriHabit3 = snapshot.child(mPhoneNumber).child(userHabits).child(Constants.CHECKED_FRI_HABIT3).getValue(Boolean.class);

                    final Boolean getSatHabit1 = snapshot.child(mPhoneNumber).child(userHabits).child(Constants.CHECKED_SAT_HABIT1).getValue(Boolean.class);
                    final Boolean getSatHabit2 = snapshot.child(mPhoneNumber).child(userHabits).child(Constants.CHECKED_SAT_HABIT2).getValue(Boolean.class);
                    final Boolean getSatHabit3 = snapshot.child(mPhoneNumber).child(userHabits).child(Constants.CHECKED_SAT_HABIT3).getValue(Boolean.class);

                    final Boolean getSunHabit1 = snapshot.child(mPhoneNumber).child(userHabits).child(Constants.CHECKED_SUN_HABIT1).getValue(Boolean.class);
                    final Boolean getSunHabit2 = snapshot.child(mPhoneNumber).child(userHabits).child(Constants.CHECKED_SUN_HABIT2).getValue(Boolean.class);
                    final Boolean getSunHabit3 = snapshot.child(mPhoneNumber).child(userHabits).child(Constants.CHECKED_SUN_HABIT3).getValue(Boolean.class);


                    // Iterate through all and filter out the logic
                    if (getMonHabit1)
                        habitList.add(new Habit("Habit 1", true, "Monday"));

                    if (getMonHabit2)
                        habitList.add(new Habit("Habit 2", true, "Monday"));

                    if (getMonHabit3)
                        habitList.add(new Habit("Habit 3", true, "Monday"));

                    if (getTuesHabit1)
                        habitList.add(new Habit("Habit 1", true, "Tuesday"));

                    if (getTuesHabit2)
                        habitList.add(new Habit("Habit 2", true, "Tuesday"));

                    if (getTuesHabit3)
                        habitList.add(new Habit("Habit 3", true, "Tuesday"));

                    if (getWedHabit1)
                        habitList.add(new Habit("Habit 1", true, "Wednesday"));

                    if (getWedHabit2)
                        habitList.add(new Habit("Habit 2", true, "Wednesday"));

                    if (getWedHabit3)
                        habitList.add(new Habit("Habit 3", true, "Wednesday"));

                    if (getThurHabit1)
                        habitList.add(new Habit("Habit 1", true, "Thursday"));

                    if (getThurHabit2)
                        habitList.add(new Habit("Habit 2", true, "Thursday"));

                    if (getThurHabit3)
                        habitList.add(new Habit("Habit 3", true, "Thursday"));

                    if (getFriHabit1)
                        habitList.add(new Habit("Habit 1", true, "Friday"));

                    if (getFriHabit2)
                        habitList.add(new Habit("Habit 2", true, "Friday"));

                    if (getFriHabit3)
                        habitList.add(new Habit("Habit 3", true, "Friday"));

                    if (getSatHabit1)
                        habitList.add(new Habit("Habit 1", true, "Saturday"));

                    if (getSatHabit2)
                        habitList.add(new Habit("Habit 2", true, "Saturday"));

                    if (getSatHabit3)
                        habitList.add(new Habit("Habit 3", true, "Saturday"));

                    if (getSunHabit1)
                        habitList.add(new Habit("Habit 1", true, "Sunday"));

                    if (getSunHabit2)
                        habitList.add(new Habit("Habit 2", true, "Sunday"));

                    if (getSunHabit3)
                        habitList.add(new Habit("Habit 3", true, "Sunday"));


                    rv_taskCompleted.setLayoutManager(new LinearLayoutManager(mContext));
                    historyAdapter = new HistoryAdapter(habitList, mContext);
                    rv_taskCompleted.setAdapter(historyAdapter);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}