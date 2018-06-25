package com.example.deko.classroom;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SignInPage extends AppCompatActivity {

    EditText Email;
    EditText Password;
    Button Login;
    TextView SignUp;

    private FirebaseAuth mAuth;

    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_page);


        Email = findViewById(R.id.email);
        Password = findViewById(R.id.passWord);
        Login = findViewById(R.id.signIn);
        SignUp = findViewById(R.id.signUp);

        mAuth = FirebaseAuth.getInstance();

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUP = new Intent(SignInPage.this, Register.class);
                startActivity(signUP);
                finish();
            }
        });


    }

    public void validate() {


        if (hasEmailValue() && hasPasswordValue()) {

            final String email = Email.getText().toString();
            final String password = Password.getText().toString();

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Intent seat = new Intent(getApplicationContext(), SeatVacancy.class);
                        startActivity(seat);
                        finish();
                    } else {
                        counter--;

                        Toast.makeText(getApplicationContext(), "Please enter the correct Email or Password. You have " + counter + " attempts remaining.", Toast.LENGTH_LONG).show();

                        if (counter == 0) {
                            Login.setEnabled(false);
                        }

                    }

                }
            });

        }

    }

    private boolean hasEmailValue() {
        if (TextUtils.isEmpty(Email.getText().toString())) {
            Email.setError("Email is required");
            Email.requestFocus();

            return false;

        }
        return true;
    }


    private boolean hasPasswordValue() {
        if (TextUtils.isEmpty(Password.getText().toString())) {
            Password.setError("Password is required");
            Password.requestFocus();

            return false;
        }
        return true;
    }


}


