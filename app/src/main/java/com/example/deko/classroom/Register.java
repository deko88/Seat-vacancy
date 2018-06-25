package com.example.deko.classroom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Patterns;
import android.support.annotation.NonNull;


import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    Button Register;
    EditText addName;
    EditText addEmail;
    EditText addPassword;
    EditText addYear;
    EditText addSpecialization;

    private FirebaseAuth mAuth;

    FirebaseUser fuser;
    DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        addName = findViewById(R.id.addName);
        addEmail = findViewById(R.id.addEmail);
        addPassword = findViewById(R.id.addPassword);
        addYear = findViewById(R.id.addYear);
        addSpecialization = findViewById(R.id.addSpecialization);
        Register = findViewById(R.id.register);

        mAuth = FirebaseAuth.getInstance();

        myRef = FirebaseDatabase.getInstance().getReference();


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userInfo();
            }
        });

    }

    private void userInfo() {

        if (isNameValid() && isEmailValid() && isPasswordValid() && isYearValid() && isSpecializationValid()) {


            final String name = addName.getText().toString();
            final String email = addEmail.getText().toString().trim();
            final String password = addPassword.getText().toString().trim();
            final String year = addYear.getText().toString();
            final String specialization = addSpecialization.getText().toString();

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        fuser = mAuth.getCurrentUser();

                        User user = new User(name, email, year, specialization);
                        myRef.child(fuser.getUid()).setValue(user);


                        Intent signIn = new Intent(getApplicationContext(), SignInPage.class);
                        startActivity(signIn);
                        finish();
                    } else if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getApplicationContext(), "The given user is already registered.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }

    }

    private boolean isNameValid() {
        if (TextUtils.isEmpty(addName.getText().toString())) {
            addName.setError("Your name is required");
            addName.requestFocus();

            return false;
        }

        return true;
    }

    private boolean isEmailValid() {
        return hasEmailValue() && isValidEmailAddress();
    }

    private boolean hasEmailValue() {
        if (TextUtils.isEmpty(addEmail.getText().toString().trim())) {
            addEmail.setError("Email is required");
            addEmail.requestFocus();

            return false;

        }
        return true;
    }

    private boolean isValidEmailAddress() {
        if (!Patterns.EMAIL_ADDRESS.matcher(addEmail.getText().toString().trim()).matches()) {
            addEmail.setError("Please enter a valid email");
            addEmail.requestFocus();

            return false;
        }

        return true;
    }


    private boolean isPasswordValid() {
        return hasPasswordValue() && isPasswordLongEnough();
    }

    private boolean hasPasswordValue() {
        if (TextUtils.isEmpty(addPassword.getText().toString().trim())) {
            addPassword.setError("Password is required");
            addPassword.requestFocus();

            return false;
        }

        return true;
    }

    private boolean isPasswordLongEnough() {
        if (addPassword.getText().toString().trim().length() < 8) {
            addPassword.setError("Too short, it should be at least 8 characters");
            addPassword.requestFocus();

            return false;
        }

        return true;
    }

    private boolean isYearValid() {
        if (TextUtils.isEmpty(addYear.getText().toString())) {
            addYear.setError("School year is required");
            addYear.requestFocus();

            return false;
        }

        return true;
    }

    private boolean isSpecializationValid() {
        if (TextUtils.isEmpty(addSpecialization.getText().toString())) {
            addSpecialization.setError("Specialization is required");
            addSpecialization.requestFocus();

            return false;
        }

        return true;
    }

}









