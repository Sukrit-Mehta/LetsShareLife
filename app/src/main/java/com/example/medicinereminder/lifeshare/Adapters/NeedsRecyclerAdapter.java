package com.example.medicinereminder.lifeshare.Adapters;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicinereminder.lifeshare.Models.Needs;
import com.example.medicinereminder.lifeshare.R;

import java.util.ArrayList;

/**
 * Created by sukrit on 20/2/18.
 */

public class NeedsRecyclerAdapter extends RecyclerView.Adapter<NeedsRecyclerAdapter.RequiredViewHolder> {

    ArrayList<Needs> arrayList;
    Context context;
    String textLine = "";

    public NeedsRecyclerAdapter(ArrayList<Needs> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public RequiredViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        int layoutType = R.layout.required_list_item;
        View itemView = layoutInflater.inflate(layoutType, parent, false);
        return new RequiredViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RequiredViewHolder holder, int position) {
        final Needs thisModel = arrayList.get(position);
        if (thisModel.getGender().equalsIgnoreCase("male")) {
            textLine = "Mr." + thisModel.getName() + " is requesting blood for " + thisModel.getRequestFor() + ".";
            holder.requesterText.setText(textLine);
        } else {
            textLine = "Ms." + thisModel.getName() + " is requesting blood for " + thisModel.getRequestFor() + ".";
            holder.requesterText.setText(textLine);
        }
        holder.userNumber.setText(thisModel.getNumber());
        holder.userAddress.setText(thisModel.getAddress());
        holder.endDate.setText(thisModel.getEndDate());
        holder.linDropIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Replace with your own action", Toast.LENGTH_SHORT).show();
            }
        });
        holder.linRaise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, textLine + "Let's share more so that one life can be saved by all of us.");
                sendIntent.setType("text/plain");
                context.startActivity(sendIntent);
            }
        });
        holder.userCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + thisModel.getNumber()));
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                context.startActivity(intent);
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
        TextView userNumber,userAddress,userMessage,requesterText;
        ImageView imageBloodGroup;
        ImageView userCall;

        public RequiredViewHolder(View itemView) {
            super(itemView);
            linDropIn = itemView.findViewById(R.id.linDropIn);
            linRaise = itemView.findViewById(R.id.linRaise);
            endDate = itemView.findViewById(R.id.endDate);
            userNumber = itemView.findViewById(R.id.userNumber);
            userAddress = itemView.findViewById(R.id.userAddress);
            requesterText = itemView.findViewById(R.id.tvRequesterText);
            imageBloodGroup = itemView.findViewById(R.id.imageBloodGroup);
            userMessage = itemView.findViewById(R.id.userMessage);
            userCall = itemView.findViewById(R.id.userCall);
            testView=itemView;
        }
    }
}
