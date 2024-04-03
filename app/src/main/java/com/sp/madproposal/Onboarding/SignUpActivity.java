package com.sp.madproposal.Onboarding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.Firebase;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    private EditText editTxt_fatherName, editTxt_fatherNumber, editTxt_motherName,
            editTxt_motherNumber, editTxt_childName, editTxt_childNumber, editTxt_email,
            editTxt_password;

    private CheckBox cb_verification;

    private Button btn_signUp;

    private String fatherName, fatherNumber, motherName, motherNumber, childName, childNumber,
    mEmail, mPassword;

    private Boolean isVerified = false;

    FirebaseDatabase database = FirebaseDatabase.getInstance("Your firebase url");
    DatabaseReference databaseReference  = database.getReference().child("users");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initWidget();

        pageDirectories();

    }

    private void pageDirectories() {

        cb_verification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    isVerified = true;
                else
                    isVerified = false;
            }
        });

        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fatherName = editTxt_fatherName.getText().toString();
                fatherNumber = editTxt_fatherNumber.getText().toString();
                motherName = editTxt_motherName.getText().toString();
                motherNumber = editTxt_motherNumber.getText().toString();
                childName = editTxt_childName.getText().toString();
                childNumber = editTxt_childNumber.getText().toString();
                mEmail = editTxt_email.getText().toString();
                mPassword = editTxt_password.getText().toString();

                validateFatherName();
                validateFatherNumber();
                validateMotherName();
                validateMotherNumber();
                validateChildName();
                validateChildNumber();
                validateEmail();
                validatePassword();
                validateVerification();
                validateInput();
            }
        });
    }

    private void validateInput() {

        if (!validateFatherName() | !validateFatherNumber() | !validateMotherName() | !validateMotherNumber() | !validateChildName() | !validateChildNumber() | !validateEmail() | !validatePassword() | !validateVerification())
        {
            Toast.makeText(this, "Input validation failed", Toast.LENGTH_SHORT).show();
            return;
        } else {
            // Add and store data inside firebase realtime db

            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    // CHecking if phone number is not registered before

                    if (snapshot.hasChild(fatherNumber)) {
                        // Ask user if he/she wants to log in to existing account
                        AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                        builder.setTitle("Habits Hero");
                        builder.setMessage("Hey there, it seems like there's an existing account with the same number");
                        builder.setNegativeButton("Register a new account", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
                            }
                        });
                        builder.setPositiveButton("Log in to existing account", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startActivity(new Intent(getApplicationContext(), SignInActivity.class));
                            }
                        });
                        builder.create().show();
                    } else {
                        // Add user's info into firebase
                        databaseReference.child(fatherNumber).child("User's Information").child(Constants.KEY_FATHER_NAME).setValue(fatherName);
                        databaseReference.child(fatherNumber).child("User's Information").child(Constants.KEY_FATHER_NUMBER).setValue(fatherNumber);
                        databaseReference.child(fatherNumber).child("User's Information").child(Constants.KEY_MOTHER_NAME).setValue(motherName);
                        databaseReference.child(fatherNumber).child("User's Information").child(Constants.KEY_MOTHER_NUMBER).setValue(motherNumber);
                        databaseReference.child(fatherNumber).child("User's Information").child(Constants.KEY_CHILD_NAME).setValue(childName);
                        databaseReference.child(fatherNumber).child("User's Information").child(Constants.KEY_CHILD_NUMBER).setValue(childNumber);
                        databaseReference.child(fatherNumber).child("User's Information").child(Constants.KEY_EMAIL).setValue(mEmail);
                        databaseReference.child(fatherNumber).child("User's Information").child(Constants.KEY_PASSWORD).setValue(mPassword);

                        Toast.makeText(SignUpActivity.this, "Sign Up Successfully", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("FatherNumber", fatherNumber);
                        startActivity(intent);

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    private boolean validateVerification() {
        return isVerified;
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

    private boolean validateEmail() {
        if (mEmail.isEmpty())
        {
            editTxt_email.setError("Required");
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(mEmail).matches())
        {
            editTxt_email.setError("Invalid Email");
            return false;
        }
        else
            return true;
    }

    private boolean validateChildNumber() {
        if (childNumber.isEmpty()) {
            editTxt_childNumber.setError("Required");
            return false;
        } else
            return true;
    }

    private boolean validateChildName() {
        if (childName.isEmpty()) {
            editTxt_childName.setError("Required");
            return false;
        } else
            return true;
    }

    private boolean validateMotherNumber() {
        if (motherNumber.isEmpty()) {
            editTxt_motherNumber.setError("Required");
            return false;
        } else
            return true;
    }

    private boolean validateMotherName() {
        if (motherName.isEmpty()) {
            editTxt_motherName.setError("Required");
            return false;
        } else
            return true;
    }

    private boolean validateFatherNumber() {
        if (fatherNumber.isEmpty()) {
            editTxt_fatherNumber.setError("Required");
            return false;
        } else
            return true;
    }

    private boolean validateFatherName() {
        if (fatherName.isEmpty()) {
            editTxt_fatherName.setError("Required");
            return false;
        } else
            return true;
    }

    private void initWidget() {

        // EditText
        editTxt_fatherName = findViewById(R.id.editTxt_fatherName);
        editTxt_fatherNumber = findViewById(R.id.editTxt_fatherNumber);
        editTxt_motherName = findViewById(R.id.editTxt_motherName);
        editTxt_motherNumber = findViewById(R.id.editTxt_motherNumber);
        editTxt_childName = findViewById(R.id.editTxt_childName);
        editTxt_childNumber = findViewById(R.id.editTxt_childNumber);
        editTxt_email = findViewById(R.id.editTxt_email);
        editTxt_password = findViewById(R.id.editTxt_password);

        // Checkbox
        cb_verification = findViewById(R.id.cb_verification);

        // Button
        btn_signUp = findViewById(R.id.btn_signUp);

    }
}