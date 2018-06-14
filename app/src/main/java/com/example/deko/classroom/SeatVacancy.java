package com.example.deko.classroom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SeatVacancy extends AppCompatActivity implements View.OnClickListener{


    public String strUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_vacancy);

        Button button1 = findViewById(R.id.s1);
        Button button2 = findViewById(R.id.s2);
        Button button3 = findViewById(R.id.s3);
        Button button4 = findViewById(R.id.s4);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);



        EditText UserName = findViewById(R.id.userName);
        String strUserName = UserName.getText().toString();


        if(TextUtils.isEmpty(strUserName)) {
            UserName.setError("Please enter your name");
            return;
        }


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.s1:
                Toast.makeText(this, "Seat 01 has been selected by" + strUserName, Toast.LENGTH_SHORT).show();
                break;
            case R.id.s2:
                Toast.makeText(this, "Seat 02 has been selected by" + strUserName, Toast.LENGTH_SHORT).show();
                break;
            case R.id.s3:
                Toast.makeText(this, "Seat 03 has been selected by" + strUserName, Toast.LENGTH_SHORT).show();
                break;
            case R.id.s4:
                Toast.makeText(this, "Seat 04 has been selected by" + strUserName, Toast.LENGTH_SHORT).show();
                break;

    }
}
}

