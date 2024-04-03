package com.sp.madproposal.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sp.madproposal.R;
import com.sp.madproposal.utilities.Constants;
import com.sp.madproposal.utilities.PreferenceManager;

public class Account extends Fragment {

    private TextView txtView_childCareNumber, txtView_fatherEmergency, txtView_motherEmergency,
            txtView_accEmail, txtView_fatherName, txtView_fatherNumber, txtView_motherName,
            txtView_motherNumber, txtView_childNumber, txtView_childName;


    private String mPhoneNumber = "93542856";


    private Context mContext;

    FirebaseDatabase database = FirebaseDatabase.getInstance("");
    DatabaseReference databaseReference  = database.getReference().child("users");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_account, container, false);

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

        // TextView
        txtView_childCareNumber = v.findViewById(R.id.txtView_childCareNumber);
        txtView_fatherEmergency = v.findViewById(R.id.txtView_fatherEmergency);
        txtView_motherEmergency = v.findViewById(R.id.txtView_motherEmergency);
        txtView_accEmail = v.findViewById(R.id.txtView_accEmail);
        txtView_fatherName = v.findViewById(R.id.txtView_fatherName);
        txtView_fatherNumber = v.findViewById(R.id.txtView_fatherNumber);
        txtView_motherName = v.findViewById(R.id.txtView_motherName);
        txtView_motherNumber = v.findViewById(R.id.txtView_motherNumber);
        txtView_childName = v.findViewById(R.id.txtView_childName);
        txtView_childNumber = v.findViewById(R.id.txtView_childNumber);

        getIntentData();

        initUI();
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

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(mPhoneNumber)) {

                    final String getFatherNumber = snapshot.child(mPhoneNumber).child("User's Information").child(Constants.KEY_FATHER_NUMBER).getValue(String.class);
                    final String getMotherNumber = snapshot.child(mPhoneNumber).child("User's Information").child(Constants.KEY_MOTHER_NUMBER).getValue(String.class);
                    final String getFatherName = snapshot.child(mPhoneNumber).child("User's Information").child(Constants.KEY_FATHER_NAME).getValue(String.class);
                    final String getMotherName = snapshot.child(mPhoneNumber).child("User's Information").child(Constants.KEY_MOTHER_NAME).getValue(String.class);
                    final String getEmail = snapshot.child(mPhoneNumber).child("User's Information").child(Constants.KEY_EMAIL).getValue(String.class);
                    final String getChildName = snapshot.child(mPhoneNumber).child("User's Information").child(Constants.KEY_CHILD_NAME).getValue(String.class);
                    final String getChildNumber = snapshot.child(mPhoneNumber).child("User's Information").child(Constants.KEY_CHILD_NUMBER).getValue(String.class);

                    txtView_fatherEmergency.setText(getFatherNumber);
                    txtView_motherEmergency.setText(getMotherNumber);
                    txtView_accEmail.setText(getEmail);
                    txtView_fatherName.setText(getFatherName);
                    txtView_fatherNumber.setText(getFatherNumber);
                    txtView_motherName.setText(getMotherName);
                    txtView_motherNumber.setText(getMotherNumber);
                    txtView_childNumber.setText(getChildNumber);
                    txtView_childName.setText(getChildName);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}