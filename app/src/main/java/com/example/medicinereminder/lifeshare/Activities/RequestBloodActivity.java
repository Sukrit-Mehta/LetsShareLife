package com.example.medicinereminder.lifeshare.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.medicinereminder.lifeshare.Models.Needs;
import com.example.medicinereminder.lifeshare.Models.Request;
import com.example.medicinereminder.lifeshare.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RequestBloodActivity extends AppCompatActivity {

    EditText etName,etAddress,etNumber,etHospitalAddress,etBloodUnits;
    Button btnSubmit;
    String name,address,number,hospitalAddress,bloodUnits,uid,bloodGroup,datePosted,endDate;
    Spinner spBloodGroup;

    DatabaseReference mDatabaseNeeds,mDatabaseUsers;
    Needs needs;
    Request requestModel;

    android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_blood);

        toolbar = findViewById(R.id.requestToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Request Blood");

        mDatabaseNeeds = FirebaseDatabase.getInstance().getReference().child("Needs");
        mDatabaseUsers = FirebaseDatabase.getInstance().getReference().child("Users");
        uid=FirebaseAuth.getInstance().getCurrentUser().getUid().toString();

        initializeViews();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = etName.getText().toString();
                address = etAddress.getText().toString();
                number = etNumber.getText().toString();
                hospitalAddress = etHospitalAddress.getText().toString();
                bloodUnits= etBloodUnits.getText().toString();
                bloodGroup="B+";

                needs = new Needs(name,address,number,bloodGroup,hospitalAddress,bloodUnits,uid,datePosted,endDate);
                requestModel = new Request(bloodUnits,bloodGroup,"20-06-2018","false");

                mDatabaseNeeds.push().setValue(needs)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful())
                                        {
                                            Toast.makeText(RequestBloodActivity.this, "Request Uploaded Successfully", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(RequestBloodActivity.this,HomeActivity.class));
                                        }
                                    }
                                });
                mDatabaseUsers.child(uid).child("requests").push().setValue(requestModel);
            }
        });
    }







    public void initializeViews()
    {
        etName = findViewById(R.id.etName);
        etAddress = findViewById(R.id.etAddress);
        etNumber = findViewById(R.id.etNumber);
        etHospitalAddress = findViewById(R.id.etHospitalAddress);
        etBloodUnits = findViewById(R.id.etBloodUnits);
        btnSubmit = findViewById(R.id.btnSubmit);
    }
}
