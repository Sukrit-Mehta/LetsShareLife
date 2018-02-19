package com.example.medicinereminder.lifeshare.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.medicinereminder.lifeshare.Models.RequiredModel;
import com.example.medicinereminder.lifeshare.R;

import java.util.ArrayList;

/**
 * Created by sukrit on 20/2/18.
 */

public class RequiredRecyclerAdapter extends RecyclerView.Adapter<RequiredRecyclerAdapter.RequiredViewHolder> {

    ArrayList<RequiredModel> arrayList;
    Context context;

    public RequiredRecyclerAdapter(ArrayList<RequiredModel> arrayList, Context context) {
        this.arrayList=arrayList;
        this.context=context;
    }

    @Override
    public RequiredViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        int layoutType = R.layout.required_list_item;
        View itemView  = layoutInflater.inflate(layoutType,parent,false);
        return new RequiredViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RequiredViewHolder holder, int position) {
        RequiredModel thisModel = arrayList.get(position);
        holder.userName.setText(thisModel.getUserName());
        holder.userAddress.setText(thisModel.getUserAddress());
        holder.userBloodGroup.setText(thisModel.getUserBloodGroup());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class RequiredViewHolder extends RecyclerView.ViewHolder {
        View testView;
        TextView userName;
        TextView userAddress;
        TextView userBloodGroup;
        public RequiredViewHolder(View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.tvPersonName);
            userAddress  = itemView.findViewById(R.id.tvPersonAddress);
            userBloodGroup = itemView.findViewById(R.id.tvBloodGroup);
            testView=itemView;
        }
    }
}
