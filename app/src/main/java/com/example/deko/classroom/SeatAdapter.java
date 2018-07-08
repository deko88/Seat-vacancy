package com.example.deko.classroom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class SeatAdapter extends RecyclerView.Adapter<SeatAdapter.SeatViewHolder> {

    FirebaseAuth mAuth;
    DatabaseReference myRef, test;

    private Context conText;

    private List<Seat> seatList;

    public SeatAdapter(Context conText, List<Seat> seatList) {
        this.conText = conText;
        this.seatList = seatList;
    }

    @Override
    public SeatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater cardView = LayoutInflater.from(conText);
        View view = cardView.inflate(R.layout.layout_seat_list, parent, false);
        return new SeatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SeatViewHolder holder, int position) {
        Seat seat = seatList.get(position);

        holder.showSeat.setText("Seat " + seat.getNumber());
    }


    @Override
    public int getItemCount() {
        return seatList.size();
    }


    class SeatViewHolder extends RecyclerView.ViewHolder {

        TextView showName;
        TextView showSeat;

        public SeatViewHolder(final View itemView) {
            super(itemView);

            showName = itemView.findViewById(R.id.showName);
            showSeat = itemView.findViewById(R.id.showSeat);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAuth = FirebaseAuth.getInstance();
                    myRef = FirebaseDatabase.getInstance().getReference(mAuth.getUid());

                    test = FirebaseDatabase.getInstance().getReference("Classroom").child("Seat01"); // need to find a way to reference the corresponding field in the database


                    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            User user = dataSnapshot.getValue(User.class);

                            showName.setText("Name: " + user.getStudentName());

                            String nameTest = String.valueOf(user.getStudentName());

                            test.child("studentName").setValue(nameTest);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }
            });

        }
    }

}
