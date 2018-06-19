package com.example.deko.classroom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;


public class SignInPage extends AppCompatActivity {

    EditText Name;
    EditText Password;
    Button Login;
    Button Test; // part of the test

    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_page);

        Name = findViewById(R.id.userName);
        Password = findViewById(R.id.passWord);
        Login = findViewById(R.id.signIn);
        Test = findViewById(R.id.test); // part of the test

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Name.getText().toString(), Password.getText().toString());
            }
        });

        Test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent test = new Intent(SignInPage.this, Register.class);
                startActivity(test);
                finish(); // part of the test
            }
        });

    }

    public void validate(String userName, String userPassword) {



            if (TextUtils.isEmpty(userName)) {
                Name.setError("Please enter your name");
                Name.requestFocus();

            } else if (TextUtils.isEmpty(userPassword)) {
                Password.setError("Please enter your password");
                Password.requestFocus();

            } else if (((userName.equals("Mike")) && (userPassword.equals("Tuna"))) || ((userName.equals("Joe")) && (userPassword.equals("Swordfish"))) || ((userName.equals("Becca")) && (userPassword.equals("HammerShark")))) {
                Intent intent = new Intent(getApplicationContext(), SeatVacancy.class);
                intent.putExtra("NAME", userName);
                startActivity(intent);
                finish();

            } else {
                    {
                        counter--;

                        Toast.makeText(this, "Please enter the correct Username or Password. You have " + counter + " attempts remaining.", Toast.LENGTH_LONG).show();

                        if (counter == 0) {
                            Login.setEnabled(false);
                        }
                    }
            }
    }
}

