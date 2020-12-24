package com.example.socialinfluencer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

import com.example.socialinfluencer.Advertiser.HomeA;
import com.example.socialinfluencer.Influencer.HomeI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal;

import static com.example.socialinfluencer.Advertiser.HomeA.HomeAdvertiser;
import static com.example.socialinfluencer.Influencer.HomeI.HomeInfluencer;

public class RegisterWithUS extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_with_u_s);
        final FirebaseAuth fAuth= FirebaseAuth.getInstance();
        final Spinner userType=findViewById(R.id.userType);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        findViewById(R.id.registerbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID=fAuth.getCurrentUser().getUid();
                String userName=fAuth.getCurrentUser().getEmail();
                String user= userType.getSelectedItem().toString();
                DatabaseReference myRef = database.getReference(user);
                myRef.child(userID).child("username").setValue(userName);
                myRef.child("token").setValue(FirebaseInstanceId.getInstance().getToken());
                if(user.equalsIgnoreCase("Influencer")) {
                    if(!HomeInfluencer) {
                        Intent intent = new Intent(RegisterWithUS.this, HomeI.class);
                        startActivity(intent);
                        finish();
                        HomeInfluencer=true;
                    }
                }
                else
                {
                    if(!HomeAdvertiser) {
                        Intent intent = new Intent(RegisterWithUS.this, HomeA.class);
                        startActivity(intent);
                        finish();
                        HomeAdvertiser=true;
                    }
                }
            }
        });
    }
}