package com.example.medicinereminder.lifeshare.Activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.example.medicinereminder.lifeshare.Adapters.ViewPagerAdapter;
import com.example.medicinereminder.lifeshare.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
       {

    ViewPagerAdapter viewPagerAdapter;
    ViewPager viewPager;
    TabLayout tabLayout;
    FloatingActionButton fabDonateBlood, fabRequestBlood;
    DatabaseReference mDatabaseNeeds,mDatabaseUsers;
    String uid,bloodGroup;
    FirebaseAuth mAuth;
    public static final int  REQUEST_CODE_ASK_PERMISSIONS = 1234;
    CircleImageView profileImage;
    TextView userName;
    public static final int GALLERY_INTENT = 111;
    Uri mImageUri;
    StorageReference mStorageImage;
    String imagePath="";
    TextView navUserName;
    TextView navUserEmail;
    String uName,uEmail;
    ViewFlipper viewFlipper;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==REQUEST_CODE_ASK_PERMISSIONS)
        {
            if(grantResults[0]== PackageManager.PERMISSION_GRANTED)
            {
                if ((ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED )||
                        (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                != PackageManager.PERMISSION_GRANTED )
                    ||(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED )) {
                    return;
                }
                else {
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if ((ActivityCompat.checkSelfPermission(HomeActivity.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)||(ActivityCompat.checkSelfPermission(HomeActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)||
                (ActivityCompat.checkSelfPermission(HomeActivity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            {
                ActivityCompat
                        .requestPermissions(this, new String[]
                                {Manifest.permission.CALL_PHONE,
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                        Manifest.permission.ACCESS_FINE_LOCATION},
                                REQUEST_CODE_ASK_PERMISSIONS);
            }
        }

            mAuth = FirebaseAuth.getInstance();
            uid = mAuth.getCurrentUser().getUid().toString();

            mDatabaseNeeds = FirebaseDatabase.getInstance().getReference().child("Needs");
            mDatabaseUsers = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
            mStorageImage = FirebaseStorage.getInstance().getReference().child("Profile_Images");

            mDatabaseNeeds.keepSynced(true);
            mDatabaseUsers.keepSynced(true);
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);




        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);

        profileImage = headerView.findViewById(R.id.userProfileImage);
        navUserName = headerView.findViewById(R.id.userName);
        navUserEmail = headerView.findViewById(R.id.userEmail);

        viewFlipper = findViewById(R.id.viewFlipper);
        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(5500);
        viewFlipper.startFlipping();

        fabRequestBlood = findViewById(R.id.fabRequestBlood);

        mDatabaseUsers.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    imagePath = dataSnapshot.child("image").getValue().toString();
                    uName = dataSnapshot.child("name").getValue().toString();
                    uEmail = dataSnapshot.child("email").getValue().toString();
                    navUserName.setText(uName);
                    navUserEmail.setText(uEmail);
                    if(imagePath.equals("default")) {
                        profileImage.setImageResource(R.drawable.circularclock);
                    }
                    else {
                        Log.d("IMG", "SetOnDataChange: "+imagePath);
                        Glide.with(getApplicationContext()).load(imagePath).into(profileImage);
                    }
                    //profileImage.setImageURI(Uri.parse(" https://firebasestorage.googleapis.com/v0/b/lifeshare-d961b.appspot.com/o/Profile_Images%2Fimage%3A94458?alt=media&token=90cee82b-9e43-45a6-b5f4-ce7440f15417"));
                    Log.d("IMG", "onDataChange: "+imagePath);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            fabRequestBlood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeActivity.this, RequestBloodActivity.class);
                    intent.putExtra("id", uid);
                    startActivity(intent);
                }
            });

            viewPager = findViewById(R.id.viewPager);
            viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
            viewPager.setAdapter(viewPagerAdapter);
            tabLayout = findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(viewPager);

            userName =headerView.findViewById(R.id.userName);

            profileImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent galleryIntent=new Intent();
                    galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                    galleryIntent.setType("image/*");
                    startActivityForResult(galleryIntent,GALLERY_INTENT);

                }
            });

            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if(requestCode==GALLERY_INTENT && resultCode==RESULT_OK)
            {
                mImageUri=data.getData();
                // start picker to get image for cropping and then use the image in cropping activity
                CropImage.activity(mImageUri)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(this);

            }
            if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if (resultCode == RESULT_OK) {
                    Uri resultUri = result.getUri();
                    Log.d("IMG", "onActivityResult: "+resultUri);
                   // profileImage.setImageURI(resultUri);

                    InputStream imageStream = null;
                    try {
                        imageStream = getContentResolver().openInputStream(
                                resultUri);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    Bitmap bmp = BitmapFactory.decodeStream(imageStream);

                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();
                    try {
                        stream.close();
                        stream = null;
                    } catch (IOException e) {

                        e.printStackTrace();
                    }
                    setUpPicture(getImageUri(getApplicationContext(),bmp));
                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                    Exception error = result.getError();
                }
            }
        }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 1, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    private void setUpPicture(Uri mImageUri) {
        Log.d("IMG", "mImageUri: "+mImageUri);
        if(mImageUri!=null)
        {
            //upload image first and then set it to image Uri in database...
            final StorageReference filePath= mStorageImage.child(mImageUri.getLastPathSegment());

            filePath.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    @SuppressWarnings("VisibleForTests")
                    String downloadUri=taskSnapshot.getDownloadUrl().toString();
                    Log.d("IMG", "onSuccess: "+downloadUri);
                    mDatabaseUsers.child("image").setValue(downloadUri);
                    Toast.makeText(HomeActivity.this, "Uploaded successfully.", Toast.LENGTH_SHORT).show();
//                    Intent loginIntent = new Intent(HomeActivity.this, HomeActivity.class);
//                    loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    finish();
//                    startActivity(loginIntent);
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(HomeActivity.this, "Failed To Upload.", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(HomeActivity.this,LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}