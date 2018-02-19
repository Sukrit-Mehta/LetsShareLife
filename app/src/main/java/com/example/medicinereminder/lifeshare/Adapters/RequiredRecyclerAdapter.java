package com.example.medicinereminder.lifeshare.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
        holder.userDetails.setText(thisModel.getUserName() + " requires "+
                        thisModel.getUserUnitsBlood() +" units of "+ thisModel.getUserBloodGroup() +
                        " Blood Group at "+" Ahmedabad, +91 96 2436 0600, A-6 Safal Profitaire,Corporate Road, ABB Ahmedabad office;5th Floor. Bangalore, +91 80 2294 6155, Rajajinagar Industrial Estate, KSSIDC IT/BT park.");
        holder.endDate.setText(thisModel.getUserEndDate());
        holder.linDropIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Replace with your own action", Toast.LENGTH_SHORT).show();
            }
        });
        holder.linRaise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Replace with your own action", Toast.LENGTH_SHORT).show();
            }
        });
        holder.linShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Replace with your own action", Toast.LENGTH_SHORT).show();
            }
        });
        holder.action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Replace with your own action", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class RequiredViewHolder extends RecyclerView.ViewHolder {
        View testView;
        TextView userDetails;
        TextView endDate;
        LinearLayout linDropIn;
        LinearLayout linRaise;
        LinearLayout linShare;
        ImageView action;
        public RequiredViewHolder(View itemView) {
            super(itemView);
            userDetails = itemView.findViewById(R.id.userDetails);
            linDropIn = itemView.findViewById(R.id.linDropIn);
            linRaise = itemView.findViewById(R.id.linRaise);
            linShare = itemView.findViewById(R.id.linShare);
            endDate = itemView.findViewById(R.id.endDate);
            action = itemView.findViewById(R.id.action);
            testView=itemView;
        }
    }
}
