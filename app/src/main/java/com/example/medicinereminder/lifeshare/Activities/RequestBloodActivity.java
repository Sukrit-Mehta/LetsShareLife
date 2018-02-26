package com.example.medicinereminder.lifeshare.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
    RadioGroup radioGroup1,radioGroup2;
    String bloodFor,gender;
    RadioButton rb1,rb2;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_blood);

        toolbar = findViewById(R.id.requestToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Request Blood");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Spinner spinner = findViewById(R.id.spinnerBloodGroup);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.blood_group_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    bloodGroup = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            mDatabaseNeeds = FirebaseDatabase.getInstance().getReference().child("Needs");
            mDatabaseUsers = FirebaseDatabase.getInstance().getReference().child("Users");

            mDatabaseNeeds.keepSynced(true);
            mDatabaseUsers.keepSynced(true);

            uid = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();

            initializeViews();

            radioGroup1 = (RadioGroup) findViewById(R.id.radioGroup1);
            radioGroup2 = (RadioGroup) findViewById(R.id.radioGroup2);

            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int selectedId1 = radioGroup1.getCheckedRadioButtonId();
                    if (selectedId1 > -1) {
                        rb1 = findViewById(selectedId1);
                    }

                    int selectedId2 = radioGroup2.getCheckedRadioButtonId();
                    if (selectedId2 > -1) {
                        rb2 = findViewById(selectedId2);
                    }

                    if (rb1 != null) {
                        bloodFor = rb1.getText().toString();
                    }

                    if (rb2 != null) {
                        gender = rb2.getText().toString();
                    }
                    name = etName.getText().toString();
                    address = etAddress.getText().toString();
                    number = etNumber.getText().toString();
                    hospitalAddress = etHospitalAddress.getText().toString();
                    bloodUnits = etBloodUnits.getText().toString();

                    Log.d("TTT", "onClick: bloodFor = " + bloodFor + "\n" +
                            "bloodGroup = " + bloodGroup + "\n" +
                            "bloodUnits = " + bloodUnits + "\n" +
                            "name = " + name + "\n" +
                            "number = " + number + "\n" +
                            "hospitalAddress = " + hospitalAddress + "\n" +
                            "gender = " + gender + "\n" +
                            "address = " + address + "\n" +
                            "rb1 = " + rb1 + "\n" +
                            "rb2 = " + rb2 + "\n");

                    if (TextUtils.isEmpty(bloodFor) ||
                            TextUtils.isEmpty(bloodGroup) ||
                            TextUtils.isEmpty(bloodUnits) ||
                            TextUtils.isEmpty(name) ||
                            TextUtils.isEmpty(number) ||
                            TextUtils.isEmpty(hospitalAddress) ||
                            TextUtils.isEmpty(gender) ||
                            TextUtils.isEmpty(address) ||
                            rb1 == null ||
                            rb2 == null) {
                        Toast.makeText(RequestBloodActivity.this, "All details Required !!", Toast.LENGTH_SHORT).show();
                    } else {
                        bloodFor = rb1.getText().toString();
                        gender = rb2.getText().toString();
                        needs = new Needs(name, address, number, bloodGroup, hospitalAddress, bloodUnits, uid, datePosted, endDate, "message", gender, bloodFor);
                        requestModel = new Request(bloodUnits, bloodGroup, "20-06-2018", "false", bloodFor, gender);

                        mDatabaseNeeds.push().setValue(needs)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(RequestBloodActivity.this, "Request Uploaded Successfully", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(RequestBloodActivity.this, HomeActivity.class));
                                        }
                                    }
                                });
                        mDatabaseUsers.child(uid).child("requests").push().setValue(requestModel);
                    }
                }
            });
    }


    public void initializeViews()
    {
        etName = findViewById(R.id.etName);
        etAddress = findViewById(R.id.etAddress);
        etNumber = findViewById(R.id.etNumber);
        etHospitalAddress = findViewById(R.id.etHospitalAddress);
        btnSubmit = findViewById(R.id.btnSubmit);
        etBloodUnits = findViewById(R.id.etBloodUnits);
    }
}
