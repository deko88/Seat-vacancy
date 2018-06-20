package com.example.deko.classroom;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
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
                validate(Email.getText().toString(), Password.getText().toString());
            }
        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent test = new Intent(SignInPage.this, Register.class);
                startActivity(test);
                finish();
            }
        });

    }

    public void validate(final String email, String password) {



        if (TextUtils.isEmpty(email)) {
            Email.setError("Email is required");
            Email.requestFocus();
        }

        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Email.setError("Please enter a valid email");
            Email.requestFocus();
        }

        else if (TextUtils.isEmpty(password)) {
            Password.setError("Password is required");
            Password.requestFocus();
        }

        else if (password.length() < 8) {
            Password.setError("Too short, it should be at least 8 characters");
            Password.requestFocus();
        }

        else {

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Intent intent = new Intent(getApplicationContext(), SeatVacancy.class);
                        intent.putExtra("NAME", email);
                        startActivity(intent);
                        finish();
                    } else {
                        counter--;

                        Toast.makeText(getApplicationContext(), "Please enter the correct Username or Password. You have " + counter + " attempts remaining.", Toast.LENGTH_LONG).show();

                        if (counter == 0) {
                            Login.setEnabled(false);
                        }

                    }

                    }
            });

        }

    }

}


