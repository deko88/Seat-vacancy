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

    String userName, email, password, year, specialization;

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


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userInfo(addName.getText().toString(), addEmail.getText().toString().trim(), addPassword.getText().toString().trim(), addYear.getText().toString(), addSpecialization.getText().toString());
            }
        });

    }

    private void userInfo(String userName, String email, String password, String year, String specialization) {


        if (TextUtils.isEmpty(userName)) {
            addName.setError("Your name is required");
            addName.requestFocus();

        } else if (TextUtils.isEmpty(email)) {
            addEmail.setError("Email is required");
            addEmail.requestFocus();

        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            addEmail.setError("Please enter a valid email");
            addEmail.requestFocus();

        } else if (TextUtils.isEmpty(password)) {
            addPassword.setError("Password is required");
            addPassword.requestFocus();

        } else if (password.length() < 8) {
            addPassword.setError("Too short, it should be at least 8 characters");
            addPassword.requestFocus();


        }else if (TextUtils.isEmpty(year)) {
            addYear.setError("School year is required");
            addYear.requestFocus();

        }else if (TextUtils.isEmpty(specialization)) {
            addSpecialization.setError("Specialization is required");
            addSpecialization.requestFocus();

        } else {

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("test");
            User user = new User(userName, email, year, specialization);
            myRef.setValue(user);

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Intent signIn = new Intent(getApplicationContext(), SignInPage.class);
                        startActivity(signIn);
                        //userDetails();
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

}







