package com.example.deko.classroom;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class SeatAdapter extends RecyclerView.Adapter<SeatAdapter.UserViewHolder> {

    private Context conText;

    private List<Seat> seatList;

    public SeatAdapter(Context conText, List<Seat> userList) {
        this.conText = conText;
        this.seatList = userList;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater cardView = LayoutInflater.from(conText);
        View view = cardView.inflate(R.layout.layout_seat_list, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        Seat seat = seatList.get(position);

        holder.showSeat.setText("Seat " + seat.getNumber());

    }


    @Override
    public int getItemCount() {
        return seatList.size();
    }


    class UserViewHolder extends RecyclerView.ViewHolder {

        TextView showSeat;

        public UserViewHolder(View itemView) {
            super(itemView);

            showSeat = itemView.findViewById(R.id.showSeat);
        }
    }

}
