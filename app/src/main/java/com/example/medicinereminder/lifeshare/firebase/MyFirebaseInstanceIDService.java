package com.example.medicinereminder.lifeshare.firebase;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by vijay on 11/14/2016.
 */
public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        Log.e("Refreshed token: " , refreshedToken);

        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String token) {
        try {

            // TODO: Implement this method to send token to your app server.
        }
        catch (Exception e)
        {
            Log.e("UPDATE TOKEN EXC", e.getMessage());
        }
    }
}