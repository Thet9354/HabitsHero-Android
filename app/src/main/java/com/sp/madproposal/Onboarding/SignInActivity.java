package com.sp.madproposal.Onboarding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sp.madproposal.MainActivity;
import com.sp.madproposal.R;
import com.sp.madproposal.utilities.Constants;
import com.sp.madproposal.utilities.PreferenceManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignInActivity extends AppCompatActivity {

    private EditText editTxt_phoneNumber, editTxt_password;

    private Button btn_login;

    private String mPhoneNumber, mPassword;

    FirebaseDatabase database = FirebaseDatabase.getInstance("Your firebase url");
    DatabaseReference databaseReference  = database.getReference().child("users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        initWidget();

        pageDirectories();
    }

    private void pageDirectories() {

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mPhoneNumber = editTxt_phoneNumber.getText().toString();
                mPassword = editTxt_password.getText().toString();

                validatePhoneNumber();
                validatePassword();
                validateInput();
            }
        });
    }

    private void validateInput() {

        if (!validatePhoneNumber() | !validatePassword())
        {
            Toast.makeText(this, "Sign In Validation failed", Toast.LENGTH_SHORT).show();
        } else {
            // Authenticating with real time firebase database
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.hasChild(mPhoneNumber)) {
                        // Name exist in firebase database
                        // now getting password of user from firebase data and match if with user entered password and username

                        final String getPassword = snapshot.child(mPhoneNumber).child("User's Information").child(Constants.KEY_PASSWORD).getValue(String.class);
                        final String getPhoneNumber = snapshot.child(mPhoneNumber).child("User's Information").child(Constants.KEY_FATHER_NUMBER).getValue(String.class);

                        if ((getPassword.equals(mPassword)) && getPhoneNumber.equals(mPhoneNumber)) {
                            // Lead use to the Main Menu page
                            Toast.makeText(SignInActivity.this, "Successfully logged in", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                            intent.putExtra("FatherNumber", mPhoneNumber);
                            startActivity(intent);
                            finish();
                        } else
                            Toast.makeText(SignInActivity.this, "Log In Unsuccessful, please check your password", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(SignInActivity.this, "Log In Unsuccessful, please check your mobile number", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    private boolean validatePassword() {
        //Regex pattern to require alphanumeric and special characters
        Pattern regexPassword = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$");
        Matcher matcher = regexPassword.matcher(mPassword);

        if (mPassword.isEmpty())
        {
            editTxt_password.setError("Required");
            return false;
        }
        else if (!matcher.matches())
        {
            editTxt_password.setError("Invalid password");
            return false;
        }
        else
            return true;
    }

    private boolean validatePhoneNumber() {
        if (mPhoneNumber.isEmpty())
        {
            editTxt_phoneNumber.setError("Required");
            return false;
        }
        else
            return true;
    }

    private void initWidget() {

        // EditText
        editTxt_phoneNumber = findViewById(R.id.editTxt_phoneNumber);
        editTxt_password = findViewById(R.id.editTxt_password);

        // Button
        btn_login = findViewById(R.id.btn_login);
    }
}