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

    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_page);

        Name = findViewById(R.id.userName);
        Password = findViewById(R.id.passWord);
        Login = findViewById(R.id.signIn);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Name.getText().toString(), Password.getText().toString());
            }
        });
    }

    public void validate(String userName, String userPassword) {



            if (TextUtils.isEmpty(userName)) {
                Name.setError("Please enter your name");

            } else if (TextUtils.isEmpty(userPassword)) {
                Password.setError("Please enter your password");

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