package com.example.deko.classroom;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.ValueEventListener;


public class Profile extends AppCompatActivity {

    TextView readName;
    TextView readEmail;
    TextView readYear;
    TextView readSpecialization;

    FirebaseAuth mAuth;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        readName = findViewById(R.id.displayName);
        readEmail = findViewById(R.id.displayEmail);
        readYear = findViewById(R.id.displayYear);
        readSpecialization = findViewById(R.id.displaySpecialization);


        mAuth = FirebaseAuth.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference(mAuth.getUid());

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);

                readName.setText("Name: " + user.getStudentName());

                readEmail.setText("Email: " + user.getStudentEmail());

                readYear.setText("Year: " + user.getStudentYear());

                readSpecialization.setText("Specialization: " + user.getStudentSpecialization());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.profile_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.seats:
                Intent seats = new Intent(Profile.this, SeatVacancy.class);
                startActivity(seats);
                return true;
            case R.id.logout:
                Intent logout = new Intent(Profile.this, SignInPage.class);
                startActivity(logout);
                finish();
                mAuth.signOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
