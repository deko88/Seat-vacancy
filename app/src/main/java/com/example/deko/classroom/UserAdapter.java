package com.example.deko.classroom;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private Context conText;

    private List<User> userList;

    public UserAdapter(Context conText, List<User> userList) {
        this.conText = conText;
        this.userList = userList;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater cardView = LayoutInflater.from(conText);
        View view = cardView.inflate(R.layout.layout_seat_list, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
       // User user = userList.get(position);

       // holder.showName.setText(user.getStudentName());

    }


    @Override
    public int getItemCount() {
        return 20;
    }


    class UserViewHolder extends RecyclerView.ViewHolder {

        TextView showName;

        public UserViewHolder(View itemView) {
            super(itemView);

            showName = itemView.findViewById(R.id.showName);
        }
    }

}
