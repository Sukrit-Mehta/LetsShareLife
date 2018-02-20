package com.example.medicinereminder.lifeshare.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.medicinereminder.lifeshare.Adapters.RequiredRecyclerAdapter;
import com.example.medicinereminder.lifeshare.Models.RequiredModel;
import com.example.medicinereminder.lifeshare.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {

    RecyclerView firstRV;
    View fragmentRootView;

    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentRootView = inflater.inflate(R.layout.fragment_first, container, false);
        firstRV = fragmentRootView.findViewById(R.id.recycler_view_first);
        ArrayList<RequiredModel> arrayList = new ArrayList<>();
        for(int i=0;i<10;i++)
        {
            arrayList.add(new RequiredModel("Name "+(i+1),"Address",
                    "Hospital","898088089",
                    "B+","test"+(i+1)+"@gmail.com",(i+1)+"","02-03-2018",
                    "08-03-2018","password"));
        }
        setRecyclerView(arrayList);
        return fragmentRootView;
    }
    public void setRecyclerView(ArrayList<RequiredModel> arrayList)
    {
        firstRV.setLayoutManager(new LinearLayoutManager(getContext()));
        firstRV.setAdapter(new RequiredRecyclerAdapter(arrayList,getContext()));
    }

}
