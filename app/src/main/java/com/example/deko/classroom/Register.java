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

public class Register extends AppCompatActivity {

    Button Register;
    EditText addEmail;
    EditText addPassword;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        addEmail = findViewById(R.id.addEmail);
        addPassword = findViewById(R.id.addPassword);
        Register = findViewById(R.id.register);

        mAuth = FirebaseAuth.getInstance();


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userInfo(addEmail.getText().toString().trim(), addPassword.getText().toString().trim());
            }
        });

    }

    private void userInfo(String email, String password) {


        if (TextUtils.isEmpty(email)) {
            addEmail.setError("Email is required");
            addEmail.requestFocus();
        }

        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            addEmail.setError("Please enter a valid email");
            addEmail.requestFocus();
        }

        else if (TextUtils.isEmpty(password)) {
            addPassword.setError("Password is required");
            addPassword.requestFocus();
        }

        else if (password.length() < 8) {
            addPassword.setError("Minimum length of password should be 8");
            addPassword.requestFocus();
        }


        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent intent = new Intent(getApplicationContext(), SeatVacancy.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Unexpected error!", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

}






