package com.sp.madproposal.Onboarding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sp.madproposal.R;

public class OnboardingActivity extends AppCompatActivity {

    private Button btn_signUp, btn_logIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        initWidget();

        pageDirectories();
    }

    private void pageDirectories() {
        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
            }
        });

        btn_logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignInActivity.class));
            }
        });
    }

    private void initWidget() {
        //Button
        btn_signUp = findViewById(R.id.btn_signUp);
        btn_logIn = findViewById(R.id.btn_logIn);
    }
}