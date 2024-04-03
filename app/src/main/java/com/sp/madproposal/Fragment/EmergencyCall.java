package com.sp.madproposal.Fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sp.madproposal.MainActivity;
import com.sp.madproposal.R;
import com.sp.madproposal.utilities.Constants;

import org.w3c.dom.Text;


public class EmergencyCall extends Fragment {

    private Context mContext;

    private ImageView btn_back;

    private TextView txtView_fatherEmergencyNumber, txtView_motherEmergencyNumber;

    private RelativeLayout rel_police, rel_emergencies, rel_childCare, rel_mommy, rel_daddy;

    private String mPhoneNumber, fatherNumber, motherNumber;

    FirebaseDatabase database = FirebaseDatabase.getInstance("Your firebase url here");
    DatabaseReference databaseReference  = database.getReference().child("users");

    static int PERMISSION_CODE = 100;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_emergency_call, container, false);

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

        //ImageView
        btn_back = v.findViewById(R.id.btn_back);

        //RelativeLayout
        rel_police = v.findViewById(R.id.rel_police);
        rel_emergencies = v.findViewById(R.id.rel_emergencies);
        rel_childCare = v.findViewById(R.id.rel_childCare);
        rel_mommy = v.findViewById(R.id.rel_mommy);
        rel_daddy = v.findViewById(R.id.rel_daddy);

        // TextView
        txtView_fatherEmergencyNumber = v.findViewById(R.id.txtView_fatherEmergencyNumber);
        txtView_motherEmergencyNumber = v.findViewById(R.id.txtView_motherEmergencyNumber);

        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.CALL_PHONE},PERMISSION_CODE);
        }

        getIntentData();

        pageDirectories();

    }

    private void getIntentData() {

        mPhoneNumber = getArguments().getString("FatherNumber");

        if (mPhoneNumber.isEmpty() | mPhoneNumber == "") {
            mPhoneNumber = "93542856";
        }

        // Retrieve the mother number from firebase
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(mPhoneNumber)) {

                    final String getMotherNumber = snapshot.child(mPhoneNumber).child("User's Information").child(Constants.KEY_MOTHER_NUMBER).getValue(String.class);

                    motherNumber = getMotherNumber;
                    fatherNumber = mPhoneNumber;

                    // Update page UI
                    txtView_fatherEmergencyNumber.setText("Daddy \n" + mPhoneNumber);
                    txtView_motherEmergencyNumber.setText("Mummy \n" + getMotherNumber);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void pageDirectories() {

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, MainActivity.class));
            }
        });

        rel_police.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phoneno = "999";
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+phoneno));
                startActivity(intent);
            }
        });

        rel_emergencies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneno = "995";
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+phoneno));
                startActivity(intent);
            }
        });

        rel_childCare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneno = "8888 8888";
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+phoneno));
                startActivity(intent);
            }
        });

        rel_mommy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+motherNumber));
                startActivity(intent);
            }
        });

        rel_daddy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+fatherNumber));
                startActivity(intent);
            }
        });
    }

}