package com.example.deko.classroom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;


public class SeatVacancy extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_vacancy);


    }

    @Override
    public void onClick(View v) {

        Button btn1 = findViewById(R.id.seat1);
        Button btn2 = findViewById(R.id.seat2);
        Button btn3 = findViewById(R.id.seat3);
        Button btn4 = findViewById(R.id.seat4);


            switch (v.getId()) {
                case R.id.seat1:
                    Toast.makeText(this, "Seat 01 has been selected by " + getIntent().getStringExtra("NAME"), Toast.LENGTH_SHORT).show();
                    btn1.setEnabled(false);
                    btn2.setEnabled(false);
                    btn3.setEnabled(false);
                    btn4.setEnabled(false);
                    break;
                case R.id.seat2:
                    Toast.makeText(this, "Seat 02 has been selected by " + getIntent().getStringExtra("NAME"), Toast.LENGTH_SHORT).show();
                    btn1.setEnabled(false);
                    btn2.setEnabled(false);
                    btn3.setEnabled(false);
                    btn4.setEnabled(false);
                    break;
                case R.id.seat3:
                    Toast.makeText(this, "Seat 03 has been selected by " + getIntent().getStringExtra("NAME"), Toast.LENGTH_SHORT).show();
                    btn1.setEnabled(false);
                    btn2.setEnabled(false);
                    btn3.setEnabled(false);
                    btn4.setEnabled(false);
                    break;
                case R.id.seat4:
                    Toast.makeText(this, "Seat 04 has been selected by " + getIntent().getStringExtra("NAME"), Toast.LENGTH_SHORT).show();
                    btn1.setEnabled(false);
                    btn2.setEnabled(false);
                    btn3.setEnabled(false);
                    btn4.setEnabled(false);
                    break;

            }

        }

}

