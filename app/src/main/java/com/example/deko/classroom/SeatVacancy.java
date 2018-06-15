package com.example.deko.classroom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;
import android.widget.TextView;


public class SeatVacancy extends AppCompatActivity implements View.OnClickListener{

    String sittingPerson1, sittingPerson2, sittingPerson3, sittingPerson4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_vacancy);


    }

    @Override
    public void onClick(View v) {

        EditText UserName = findViewById(R.id.userName);
        String strUserName = UserName.getText().toString();


        if(TextUtils.isEmpty(strUserName)) {
            UserName.setError("Please enter your name");
        } else {


            switch (v.getId()) {
                case R.id.s1:
                    Toast.makeText(this, "Seat 01 has been selected by " + strUserName, Toast.LENGTH_SHORT).show();
                    Button btn1 = findViewById(R.id.s1);
                    btn1.setEnabled(false);
                    sittingPerson1 = strUserName;
                    TextView showSeat1 =findViewById(R.id.sP1);
                    showSeat1.setText(sittingPerson1);
                    break;
                case R.id.s2:
                    Toast.makeText(this, "Seat 02 has been selected by " + strUserName, Toast.LENGTH_SHORT).show();
                    Button btn2 = findViewById(R.id.s2);
                    btn2.setEnabled(false);
                    sittingPerson2 = strUserName;
                    TextView showSeat2 =findViewById(R.id.sP2);
                    showSeat2.setText(sittingPerson2);
                    break;
                case R.id.s3:
                    Toast.makeText(this, "Seat 03 has been selected by " + strUserName, Toast.LENGTH_SHORT).show();
                    Button btn3 = findViewById(R.id.s3);
                    btn3.setEnabled(false);
                    sittingPerson3 = strUserName;
                    TextView showSeat3 =findViewById(R.id.sP3);
                    showSeat3.setText(sittingPerson3);
                    break;
                case R.id.s4:
                    Toast.makeText(this, "Seat 04 has been selected by " + strUserName, Toast.LENGTH_SHORT).show();
                    Button btn4 = findViewById(R.id.s4);
                    btn4.setEnabled(false);
                    sittingPerson4 = strUserName;
                    TextView showSeat4 =findViewById(R.id.sP4);
                    showSeat4.setText(sittingPerson4);
                    break;

            }

        }

}
}

