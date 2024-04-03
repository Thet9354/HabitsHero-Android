package com.sp.madproposal.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.sp.madproposal.ChatBot_Activity;
import com.sp.madproposal.R;

import java.util.concurrent.Executor;


public class SMS extends Fragment {

    private Context mContext;

    private Button btn_biometric;

    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_s_m_s, container, false);

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

        btn_biometric = v.findViewById(R.id.btn_biometric);

        initUI();

        pageDirectories();
    }

    private void initUI() {

        // Biometric feature does not work on emulator, please test on an actual device
        executor = ContextCompat.getMainExecutor(mContext);

        biometricPrompt = new BiometricPrompt((FragmentActivity) mContext, executor, (BiometricPrompt.AuthenticationCallback) new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(mContext, "Error while using biometric login", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(mContext, ChatBot_Activity.class));
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                startActivity(new Intent(mContext, ChatBot_Activity.class));
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(mContext, "Authentication failed", Toast.LENGTH_SHORT).show();
            }
        });

        //Setup title, description on auth dialog
        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric Authentiction")
                .setSubtitle("Login using fingerprint or face")
                .setNegativeButtonText("Cancel")
                .build();
    }

    private void pageDirectories() {

        btn_biometric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                biometricPrompt.authenticate(promptInfo);
            }
        });
    }
}