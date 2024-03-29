package com.example.medicinereminder.lifeshare.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.medicinereminder.lifeshare.Models.User;
import com.example.medicinereminder.lifeshare.R;

import java.util.ArrayList;

/**
 * Created by sukrit on 20/2/18.
 */

public class UsersRecyclerAdapter extends RecyclerView.Adapter<UsersRecyclerAdapter.UsersViewHolder> {

    ArrayList<User> arrayList;
    Context context;

    public UsersRecyclerAdapter(ArrayList<User> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        int layoutType =  R.layout.user_list_item;
        View itemView = layoutInflater.inflate(layoutType,parent,false);
        return new UsersViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UsersViewHolder holder, int position) {
        holder.userDonations.setText(arrayList.get(position).getUserDonations());
        holder.userName.setText(arrayList.get(position).getUserName());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class UsersViewHolder extends RecyclerView.ViewHolder
    {
        View testView;
        TextView userName;
        TextView userDonations;
        public UsersViewHolder(View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.name);
            userDonations = itemView.findViewById(R.id.userDonations);
            testView = itemView;
        }
    }
}
