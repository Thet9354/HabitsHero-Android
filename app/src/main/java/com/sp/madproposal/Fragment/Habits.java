package com.sp.madproposal.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.CalendarContract;
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
import com.sp.madproposal.ChatBot_Activity;
import com.sp.madproposal.R;
import com.sp.madproposal.utilities.Constants;

import org.w3c.dom.Text;

import java.util.HashMap;


public class Habits extends Fragment {

    private CheckBox cb_monHabit1, cb_monHabit2, cb_monHabit3, cb_tuesHabit1, cb_tuesHabit2,
            cb_tuesHabit3, cb_wedHabit1, cb_wedHabit2, cb_wedHabit3, cb_thursHabit1,
            cb_thursHabit2, cb_thursHabit3, cb_friHabit1, cb_friHabit2, cb_friHabit3,
            cb_satHabit1, cb_satHabit2, cb_satHabit3, cb_sunHabit1, cb_sunHabit2,
            cb_sunHabit3;

    private TextView txtView_monHabit1, txtView_monHabit2, txtView_monHabit3, txtView_tuesHabit1,
            txtView_tuesHabit2, txtView_tuesHabit3, txtView_wedHabit1, txtView_wedHabit2,
            txtView_wedHabit3, txtView_thursHabit1, txtView_thursHabit2, txtView_thursHabit3,
            txtView_friHabit1, txtView_friHabit2, txtView_friHabit3, txtView_satHabit1,
            txtView_satHabit2, txtView_satHabit3, txtView_sunHabit1, txtView_sunHabit2,
            txtView_sunHabit3;

    private Button btn_logIt;

    private com.google.android.material.floatingactionbutton.FloatingActionButton fab_aiBot;

    private Context mContext;

    com.sp.madproposal.Model.Habits habits;

    private String mEmail;

    FirebaseDatabase database = FirebaseDatabase.getInstance("Your firebase url here");
    DatabaseReference databaseReference  = database.getReference().child("users");

    private String mPhoneNumber = "93542856";
    private int totalPoints = 0;

    String userHabits = "User's Habits";

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

    Intent intent;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_habits, container, false);

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

        // Checkbox
        cb_monHabit1 = v.findViewById(R.id.cb_monHabit1);
        cb_monHabit2 = v.findViewById(R.id.cb_monHabit2);
        cb_monHabit3 = v.findViewById(R.id.cb_monHabit3);

        cb_tuesHabit1 = v.findViewById(R.id.cb_tuesHabit1);
        cb_tuesHabit2 = v.findViewById(R.id.cb_tuesHabit2);
        cb_tuesHabit3 = v.findViewById(R.id.cb_tuesHabit3);

        cb_wedHabit1 = v.findViewById(R.id.cb_wedHabit1);
        cb_wedHabit2 = v.findViewById(R.id.cb_wedHabit2);
        cb_wedHabit3 = v.findViewById(R.id.cb_wedHabit3);

        cb_thursHabit1 = v.findViewById(R.id.cb_thursHabit1);
        cb_thursHabit2 = v.findViewById(R.id.cb_thursHabit2);
        cb_thursHabit3 = v.findViewById(R.id.cb_thursHabit3);

        cb_friHabit1 = v.findViewById(R.id.cb_friHabit1);
        cb_friHabit2 = v.findViewById(R.id.cb_friHabit2);
        cb_friHabit3 = v.findViewById(R.id.cb_friHabit3);

        cb_satHabit1 = v.findViewById(R.id.cb_satHabit1);
        cb_satHabit2 = v.findViewById(R.id.cb_satHabit2);
        cb_satHabit3 = v.findViewById(R.id.cb_satHabit3);

        cb_sunHabit1 = v.findViewById(R.id.cb_sunHabit1);
        cb_sunHabit2 = v.findViewById(R.id.cb_sunHabit2);
        cb_sunHabit3 = v.findViewById(R.id.cb_sunHabit3);

        // TextView
        txtView_monHabit1 = v.findViewById(R.id.txtView_monHabit1);
        txtView_monHabit2 = v.findViewById(R.id.txtView_monHabit2);
        txtView_monHabit3 = v.findViewById(R.id.txtView_monHabit3);

        txtView_tuesHabit1 = v.findViewById(R.id.txtView_tuesHabit1);
        txtView_tuesHabit2 = v.findViewById(R.id.txtView_tuesHabit2);
        txtView_tuesHabit3 = v.findViewById(R.id.txtView_tuesHabit3);

        txtView_wedHabit1 = v.findViewById(R.id.txtView_wedHabit1);
        txtView_wedHabit2 = v.findViewById(R.id.txtView_wedHabit2);
        txtView_wedHabit3 = v.findViewById(R.id.txtView_wedHabit3);

        txtView_thursHabit1 = v.findViewById(R.id.txtView_thursHabit1);
        txtView_thursHabit2 = v.findViewById(R.id.txtView_thursHabit2);
        txtView_thursHabit3 = v.findViewById(R.id.txtView_thursHabit3);

        txtView_friHabit1 = v.findViewById(R.id.txtView_friHabit1);
        txtView_friHabit2 = v.findViewById(R.id.txtView_friHabit2);
        txtView_friHabit3 = v.findViewById(R.id.txtView_friHabit3);

        txtView_satHabit1 = v.findViewById(R.id.txtView_satHabit1);
        txtView_satHabit2 = v.findViewById(R.id.txtView_satHabit2);
        txtView_satHabit3 = v.findViewById(R.id.txtView_satHabit3);

        txtView_sunHabit1 = v.findViewById(R.id.txtView_sunHabit1);
        txtView_sunHabit2 = v.findViewById(R.id.txtView_sunHabit2);
        txtView_sunHabit3 = v.findViewById(R.id.txtView_sunHabit3);

        // Button
        btn_logIt = v.findViewById(R.id.btn_logIt);
        fab_aiBot = v.findViewById(R.id.fab_aiBot);

        getIntentData();

        initUI();

        pageDirectories();

    }

    private void initUI() {

        // Retrieve past habit check boxes
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child(mPhoneNumber).hasChild(userHabits)) {
                    // Means pass habits are already inside, now just need update

                    final String getEmail = snapshot.child(mPhoneNumber).child("User's Information").child(Constants.KEY_EMAIL).getValue(String.class);

                    mEmail = getEmail;

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

                    if (getMonHabit1)
                        cb_monHabit1.setChecked(true);

                    if (getMonHabit2)
                        cb_monHabit2.setChecked(true);

                    if (getMonHabit3)
                        cb_monHabit3.setChecked(true);

                    if (getTuesHabit1)
                        cb_tuesHabit1.setChecked(true);

                    if (getTuesHabit2)
                        cb_tuesHabit2.setChecked(true);

                    if (getTuesHabit3)
                        cb_tuesHabit3.setChecked(true);

                    if (getWedHabit1)
                        cb_wedHabit1.setChecked(true);

                    if (getWedHabit2)
                        cb_wedHabit2.setChecked(true);

                    if (getWedHabit3)
                        cb_wedHabit3.setChecked(true);

                    if (getThurHabit1)
                        cb_thursHabit1.setChecked(true);

                    if (getThurHabit2)
                        cb_thursHabit2.setChecked(true);

                    if (getThurHabit3)
                        cb_thursHabit3.setChecked(true);

                    if (getFriHabit1)
                        cb_friHabit1.setChecked(true);

                    if (getFriHabit2)
                        cb_friHabit2.setChecked(true);

                    if (getFriHabit3)
                        cb_friHabit3.setChecked(true);

                    if (getSatHabit1)
                        cb_satHabit1.setChecked(true);

                    if (getSatHabit2)
                        cb_satHabit2.setChecked(true);

                    if (getSatHabit3)
                        cb_satHabit3.setChecked(true);

                    if (getSunHabit1)
                        cb_sunHabit1.setChecked(true);

                    if (getSunHabit2)
                        cb_sunHabit2.setChecked(true);

                    if (getSunHabit3)
                        cb_sunHabit3.setChecked(true);


                } else {
                    // Means need to create the child habit and add values for first time
                    // Do nothing
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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

        cb_monHabit1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    totalPoints++;
                    checkedMonHabit1 = true;
                } else {
                    totalPoints--;
                    checkedMonHabit1 = false;
                }
            }
        });

        cb_monHabit2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    totalPoints++;
                    checkedMonHabit2 = true;
                } else {
                    totalPoints--;
                    checkedMonHabit2 = false;
                }
            }
        });

        cb_monHabit3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    totalPoints++;
                    checkedMonHabit3 = true;
                } else {
                    totalPoints--;
                    checkedMonHabit3 = false;
                }
            }
        });

        cb_tuesHabit1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    totalPoints++;
                    checkedTuesHabit1 = true;
                } else {
                    totalPoints--;
                    checkedTuesHabit1 = false;
                }
            }
        });

        cb_tuesHabit2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    totalPoints++;
                    checkedTuesHabit2 = true;
                } else {
                    totalPoints--;
                    checkedTuesHabit2 = false;
                }
            }
        });

        cb_tuesHabit3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    totalPoints++;
                    checkedTuesHabit3 = true;
                } else {
                    totalPoints--;
                    checkedTuesHabit3 = false;
                }
            }
        });

        cb_wedHabit1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    totalPoints++;
                    checkedWedHabit1 = true;
                } else {
                    totalPoints--;
                    checkedWedHabit1 = false;
                }
            }
        });

        cb_wedHabit2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    totalPoints++;
                    checkedWedHabit2 = true;
                } else {
                    totalPoints--;
                    checkedWedHabit2 = false;
                }
            }
        });

        cb_wedHabit3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    totalPoints++;
                    checkedWedHabit3 = true;
                } else {
                    totalPoints--;
                    checkedWedHabit3 = false;
                }
            }
        });

        cb_thursHabit1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    totalPoints++;
                    checkedThurHabit1 = true;
                } else {
                    totalPoints--;
                    checkedThurHabit1 = false;
                }
            }
        });

        cb_thursHabit2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    totalPoints++;
                    checkedThurHabit2 = true;
                } else {
                    totalPoints--;
                    checkedThurHabit2 = false;
                }
            }
        });

        cb_thursHabit3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    totalPoints++;
                    checkedThurHabit3 = true;
                } else {
                    totalPoints--;
                    checkedThurHabit3 = false;
                }
            }
        });

        cb_friHabit1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    totalPoints++;
                    checkedFriHabit1 = true;
                } else {
                    totalPoints--;
                    checkedFriHabit1 = false;
                }
            }
        });

        cb_friHabit2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    totalPoints++;
                    checkedFriHabit2 = true;
                } else {
                    totalPoints--;
                    checkedFriHabit2 = false;
                }
            }
        });

        cb_friHabit3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    totalPoints++;
                    checkedFriHabit3 = true;
                } else {
                    totalPoints--;
                    checkedFriHabit3 = false;
                }
            }
        });

        cb_satHabit1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    totalPoints++;
                    checkedSatHabit1 = true;
                } else {
                    totalPoints--;
                    checkedSatHabit1 = false;
                }
            }
        });

        cb_satHabit2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    totalPoints++;
                    checkedSatHabit2 = true;
                } else {
                    totalPoints--;
                    checkedSatHabit2 = false;
                }
            }
        });

        cb_satHabit3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    totalPoints++;
                    checkedSatHabit3 = true;
                } else {
                    totalPoints--;
                    checkedSatHabit3 = false;
                }
            }
        });

        cb_sunHabit1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    totalPoints++;
                    checkedSunHabit1 = true;
                } else {
                    totalPoints--;
                    checkedSunHabit1 = false;
                }
            }
        });

        cb_sunHabit2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    totalPoints++;
                    checkedSunHabit2 = true;
                } else {
                    totalPoints--;
                    checkedSunHabit2 = false;
                }
            }
        });

        cb_sunHabit3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    totalPoints++;
                    checkedSunHabit3 = true;
                } else {
                    totalPoints--;
                    checkedSunHabit3 = false;
                }
            }
        });

        btn_logIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // Add or Update habits inside firebase realtime db
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.child(mPhoneNumber).hasChild(userHabits)) {
                            // Means pass habits are already inside, now just need update
                            updateData();
                        } else {
                            // Means need to create the child habit and add values for first time
                            addData();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        fab_aiBot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, ChatBot_Activity.class));

            }
        });

        txtView_monHabit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!cb_monHabit1.isChecked()) {
                    //Add the event to your local calendar
                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setData(CalendarContract.Events.CONTENT_URI);
                    intent.putExtra(CalendarContract.Events.TITLE, txtView_monHabit1.getText().toString());
                    intent.putExtra(CalendarContract.Events.ALL_DAY, "true");
                    intent.putExtra(Intent.EXTRA_EMAIL, mEmail);

                    startActivity(intent);
                } else {

                }


            }
        });

        txtView_monHabit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!cb_monHabit2.isChecked()) {
                    //Add the event to your local calendar
                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setData(CalendarContract.Events.CONTENT_URI);
                    intent.putExtra(CalendarContract.Events.TITLE, txtView_monHabit2.getText().toString());
                    intent.putExtra(CalendarContract.Events.ALL_DAY, "true");
                    intent.putExtra(Intent.EXTRA_EMAIL, mEmail);

                    startActivity(intent);
                } else {

                }
            }
        });

        txtView_monHabit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!cb_monHabit3.isChecked()) {
                    //Add the event to your local calendar
                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setData(CalendarContract.Events.CONTENT_URI);
                    intent.putExtra(CalendarContract.Events.TITLE, txtView_monHabit3.getText().toString());
                    intent.putExtra(CalendarContract.Events.ALL_DAY, "true");
                    intent.putExtra(Intent.EXTRA_EMAIL, mEmail);

                    startActivity(intent);
                } else {

                }
            }
        });

        txtView_tuesHabit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!cb_tuesHabit1.isChecked()) {
                    //Add the event to your local calendar
                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setData(CalendarContract.Events.CONTENT_URI);
                    intent.putExtra(CalendarContract.Events.TITLE, txtView_tuesHabit1.getText().toString());
                    intent.putExtra(CalendarContract.Events.ALL_DAY, "true");
                    intent.putExtra(Intent.EXTRA_EMAIL, mEmail);

                    startActivity(intent);
                } else {

                }
            }
        });

        txtView_tuesHabit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!cb_tuesHabit2.isChecked()) {
                    //Add the event to your local calendar
                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setData(CalendarContract.Events.CONTENT_URI);
                    intent.putExtra(CalendarContract.Events.TITLE, txtView_tuesHabit2.getText().toString());
                    intent.putExtra(CalendarContract.Events.ALL_DAY, "true");
                    intent.putExtra(Intent.EXTRA_EMAIL, mEmail);

                    startActivity(intent);
                } else {

                }
            }
        });

        txtView_tuesHabit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!cb_tuesHabit3.isChecked()) {
                    //Add the event to your local calendar
                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setData(CalendarContract.Events.CONTENT_URI);
                    intent.putExtra(CalendarContract.Events.TITLE, txtView_tuesHabit3.getText().toString());
                    intent.putExtra(CalendarContract.Events.ALL_DAY, "true");
                    intent.putExtra(Intent.EXTRA_EMAIL, mEmail);

                    startActivity(intent);
                } else {

                }
            }
        });

        txtView_wedHabit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!cb_wedHabit1.isChecked()) {
                    //Add the event to your local calendar
                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setData(CalendarContract.Events.CONTENT_URI);
                    intent.putExtra(CalendarContract.Events.TITLE, txtView_wedHabit1.getText().toString());
                    intent.putExtra(CalendarContract.Events.ALL_DAY, "true");
                    intent.putExtra(Intent.EXTRA_EMAIL, mEmail);

                    startActivity(intent);
                } else {

                }
            }
        });

        txtView_wedHabit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!cb_wedHabit2.isChecked()) {
                    //Add the event to your local calendar
                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setData(CalendarContract.Events.CONTENT_URI);
                    intent.putExtra(CalendarContract.Events.TITLE, txtView_wedHabit2.getText().toString());
                    intent.putExtra(CalendarContract.Events.ALL_DAY, "true");
                    intent.putExtra(Intent.EXTRA_EMAIL, mEmail);

                    startActivity(intent);
                } else {

                }
            }
        });

        txtView_wedHabit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!cb_wedHabit3.isChecked()) {
                    //Add the event to your local calendar
                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setData(CalendarContract.Events.CONTENT_URI);
                    intent.putExtra(CalendarContract.Events.TITLE, txtView_wedHabit3.getText().toString());
                    intent.putExtra(CalendarContract.Events.ALL_DAY, "true");
                    intent.putExtra(Intent.EXTRA_EMAIL, mEmail);

                    startActivity(intent);
                } else {

                }
            }
        });

        txtView_thursHabit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!cb_thursHabit1.isChecked()) {
                    //Add the event to your local calendar
                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setData(CalendarContract.Events.CONTENT_URI);
                    intent.putExtra(CalendarContract.Events.TITLE, txtView_thursHabit1.getText().toString());
                    intent.putExtra(CalendarContract.Events.ALL_DAY, "true");
                    intent.putExtra(Intent.EXTRA_EMAIL, mEmail);

                    startActivity(intent);
                } else {

                }
            }
        });

        txtView_thursHabit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!cb_thursHabit2.isChecked()) {
                    //Add the event to your local calendar
                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setData(CalendarContract.Events.CONTENT_URI);
                    intent.putExtra(CalendarContract.Events.TITLE, txtView_thursHabit2.getText().toString());
                    intent.putExtra(CalendarContract.Events.ALL_DAY, "true");
                    intent.putExtra(Intent.EXTRA_EMAIL, mEmail);

                    startActivity(intent);
                } else {

                }
            }
        });

        txtView_thursHabit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!cb_thursHabit3.isChecked()) {
                    //Add the event to your local calendar
                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setData(CalendarContract.Events.CONTENT_URI);
                    intent.putExtra(CalendarContract.Events.TITLE, txtView_thursHabit3.getText().toString());
                    intent.putExtra(CalendarContract.Events.ALL_DAY, "true");
                    intent.putExtra(Intent.EXTRA_EMAIL, mEmail);

                    startActivity(intent);
                } else {

                }
            }
        });

        txtView_friHabit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!cb_friHabit1.isChecked()) {
                    //Add the event to your local calendar
                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setData(CalendarContract.Events.CONTENT_URI);
                    intent.putExtra(CalendarContract.Events.TITLE, txtView_friHabit1.getText().toString());
                    intent.putExtra(CalendarContract.Events.ALL_DAY, "true");
                    intent.putExtra(Intent.EXTRA_EMAIL, mEmail);

                    startActivity(intent);
                } else {

                }
            }
        });

        txtView_friHabit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!cb_friHabit2.isChecked()) {
                    //Add the event to your local calendar
                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setData(CalendarContract.Events.CONTENT_URI);
                    intent.putExtra(CalendarContract.Events.TITLE, txtView_friHabit2.getText().toString());
                    intent.putExtra(CalendarContract.Events.ALL_DAY, "true");
                    intent.putExtra(Intent.EXTRA_EMAIL, mEmail);

                    startActivity(intent);
                } else {

                }
            }
        });

        txtView_friHabit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!cb_friHabit3.isChecked()) {
                    //Add the event to your local calendar
                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setData(CalendarContract.Events.CONTENT_URI);
                    intent.putExtra(CalendarContract.Events.TITLE, txtView_friHabit3.getText().toString());
                    intent.putExtra(CalendarContract.Events.ALL_DAY, "true");
                    intent.putExtra(Intent.EXTRA_EMAIL, mEmail);

                    startActivity(intent);
                } else {

                }
            }
        });

        txtView_satHabit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!cb_satHabit1.isChecked()) {
                    //Add the event to your local calendar
                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setData(CalendarContract.Events.CONTENT_URI);
                    intent.putExtra(CalendarContract.Events.TITLE, txtView_satHabit1.getText().toString());
                    intent.putExtra(CalendarContract.Events.ALL_DAY, "true");
                    intent.putExtra(Intent.EXTRA_EMAIL, mEmail);

                    startActivity(intent);
                } else {

                }
            }
        });

        txtView_satHabit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!cb_satHabit2.isChecked()) {
                    //Add the event to your local calendar
                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setData(CalendarContract.Events.CONTENT_URI);
                    intent.putExtra(CalendarContract.Events.TITLE, txtView_satHabit2.getText().toString());
                    intent.putExtra(CalendarContract.Events.ALL_DAY, "true");
                    intent.putExtra(Intent.EXTRA_EMAIL, mEmail);

                    startActivity(intent);
                } else {

                }
            }
        });

        txtView_satHabit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!cb_satHabit3.isChecked()) {
                    //Add the event to your local calendar
                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setData(CalendarContract.Events.CONTENT_URI);
                    intent.putExtra(CalendarContract.Events.TITLE, txtView_satHabit3.getText().toString());
                    intent.putExtra(CalendarContract.Events.ALL_DAY, "true");
                    intent.putExtra(Intent.EXTRA_EMAIL, mEmail);

                    startActivity(intent);
                } else {

                }
            }
        });

        txtView_sunHabit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!cb_sunHabit1.isChecked()) {
                    //Add the event to your local calendar
                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setData(CalendarContract.Events.CONTENT_URI);
                    intent.putExtra(CalendarContract.Events.TITLE, txtView_sunHabit1.getText().toString());
                    intent.putExtra(CalendarContract.Events.ALL_DAY, "true");
                    intent.putExtra(Intent.EXTRA_EMAIL, mEmail);

                    startActivity(intent);
                } else {

                }
            }
        });

        txtView_sunHabit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!cb_sunHabit2.isChecked()) {
                    //Add the event to your local calendar
                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setData(CalendarContract.Events.CONTENT_URI);
                    intent.putExtra(CalendarContract.Events.TITLE, txtView_sunHabit2.getText().toString());
                    intent.putExtra(CalendarContract.Events.ALL_DAY, "true");
                    intent.putExtra(Intent.EXTRA_EMAIL, mEmail);

                    startActivity(intent);
                } else {

                }
            }
        });

        txtView_sunHabit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!cb_sunHabit3.isChecked()) {
                    //Add the event to your local calendar
                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setData(CalendarContract.Events.CONTENT_URI);
                    intent.putExtra(CalendarContract.Events.TITLE, txtView_sunHabit3.getText().toString());
                    intent.putExtra(CalendarContract.Events.ALL_DAY, "true");
                    intent.putExtra(Intent.EXTRA_EMAIL, mEmail);

                    startActivity(intent);
                } else {

                }
            }
        });



    }

    private void addData() {

        databaseReference.child(mPhoneNumber).child(userHabits).child(Constants.CHECKED_MON_HABIT1).setValue(checkedMonHabit1);
        databaseReference.child(mPhoneNumber).child(userHabits).child(Constants.CHECKED_MON_HABIT2).setValue(checkedMonHabit2);
        databaseReference.child(mPhoneNumber).child(userHabits).child(Constants.CHECKED_MON_HABIT3).setValue(checkedMonHabit3);

        databaseReference.child(mPhoneNumber).child(userHabits).child(Constants.CHECKED_TUES_HABIT1).setValue(checkedTuesHabit1);
        databaseReference.child(mPhoneNumber).child(userHabits).child(Constants.CHECKED_TUES_HABIT2).setValue(checkedTuesHabit2);
        databaseReference.child(mPhoneNumber).child(userHabits).child(Constants.CHECKED_TUES_HABIT3).setValue(checkedTuesHabit3);

        databaseReference.child(mPhoneNumber).child(userHabits).child(Constants.CHECKED_WED_HABIT1).setValue(checkedWedHabit1);
        databaseReference.child(mPhoneNumber).child(userHabits).child(Constants.CHECKED_WED_HABIT2).setValue(checkedWedHabit2);
        databaseReference.child(mPhoneNumber).child(userHabits).child(Constants.CHECKED_WED_HABIT3).setValue(checkedWedHabit3);

        databaseReference.child(mPhoneNumber).child(userHabits).child(Constants.CHECKED_THU_HABIT1).setValue(checkedThurHabit1);
        databaseReference.child(mPhoneNumber).child(userHabits).child(Constants.CHECKED_THU_HABIT2).setValue(checkedThurHabit2);
        databaseReference.child(mPhoneNumber).child(userHabits).child(Constants.CHECKED_THU_HABIT3).setValue(checkedThurHabit3);

        databaseReference.child(mPhoneNumber).child(userHabits).child(Constants.CHECKED_FRI_HABIT1).setValue(checkedFriHabit1);
        databaseReference.child(mPhoneNumber).child(userHabits).child(Constants.CHECKED_FRI_HABIT2).setValue(checkedFriHabit2);
        databaseReference.child(mPhoneNumber).child(userHabits).child(Constants.CHECKED_FRI_HABIT3).setValue(checkedFriHabit3);

        databaseReference.child(mPhoneNumber).child(userHabits).child(Constants.CHECKED_SAT_HABIT1).setValue(checkedSatHabit1);
        databaseReference.child(mPhoneNumber).child(userHabits).child(Constants.CHECKED_SAT_HABIT2).setValue(checkedSatHabit2);
        databaseReference.child(mPhoneNumber).child(userHabits).child(Constants.CHECKED_SAT_HABIT3).setValue(checkedSatHabit3);

        databaseReference.child(mPhoneNumber).child(userHabits).child(Constants.CHECKED_SUN_HABIT1).setValue(checkedSunHabit1);
        databaseReference.child(mPhoneNumber).child(userHabits).child(Constants.CHECKED_SUN_HABIT2).setValue(checkedSunHabit2);
        databaseReference.child(mPhoneNumber).child(userHabits).child(Constants.CHECKED_SUN_HABIT3).setValue(checkedSunHabit3);

    }

    private void updateData() {

        HashMap habit = new HashMap();
        habit.put(Constants.CHECKED_MON_HABIT1, checkedMonHabit1);
        habit.put(Constants.CHECKED_MON_HABIT2, checkedMonHabit2);
        habit.put(Constants.CHECKED_MON_HABIT3, checkedMonHabit3);

        habit.put(Constants.CHECKED_TUES_HABIT1, checkedTuesHabit1);
        habit.put(Constants.CHECKED_TUES_HABIT2, checkedTuesHabit2);
        habit.put(Constants.CHECKED_TUES_HABIT3, checkedTuesHabit3);

        habit.put(Constants.CHECKED_WED_HABIT1, checkedWedHabit1);
        habit.put(Constants.CHECKED_WED_HABIT2, checkedWedHabit2);
        habit.put(Constants.CHECKED_WED_HABIT3, checkedWedHabit3);

        habit.put(Constants.CHECKED_THU_HABIT1, checkedThurHabit1);
        habit.put(Constants.CHECKED_THU_HABIT2, checkedThurHabit2);
        habit.put(Constants.CHECKED_THU_HABIT3, checkedThurHabit3);

        habit.put(Constants.CHECKED_FRI_HABIT1, checkedFriHabit1);
        habit.put(Constants.CHECKED_FRI_HABIT2, checkedFriHabit2);
        habit.put(Constants.CHECKED_FRI_HABIT3, checkedFriHabit3);

        habit.put(Constants.CHECKED_SAT_HABIT1, checkedSatHabit1);
        habit.put(Constants.CHECKED_SAT_HABIT2, checkedSatHabit2);
        habit.put(Constants.CHECKED_SAT_HABIT3, checkedSatHabit3);

        habit.put(Constants.CHECKED_SUN_HABIT1, checkedSunHabit1);
        habit.put(Constants.CHECKED_SUN_HABIT2, checkedSunHabit2);
        habit.put(Constants.CHECKED_SUN_HABIT3, checkedSunHabit3);

        databaseReference.child(mPhoneNumber).child(userHabits).updateChildren(habit).addOnCompleteListener(new OnCompleteListener() {
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