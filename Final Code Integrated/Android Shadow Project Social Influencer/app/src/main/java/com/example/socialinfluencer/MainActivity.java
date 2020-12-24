package com.example.socialinfluencer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.socialinfluencer.Advertiser.HomeA;
import com.example.socialinfluencer.Influencer.HomeI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.socialinfluencer.Advertiser.HomeA.HomeAdvertiser;
import static com.example.socialinfluencer.Influencer.HomeI.HomeInfluencer;

public class MainActivity extends AppCompatActivity {

    Button First,Second;
    Fragment f;
    FirebaseAuth fAuth ;
    public static boolean MainActivityRunning;
    public static String usertype;
    @Override
    protected void onStart() {
        super.onStart();
        MainActivityRunning=true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        MainActivityRunning=false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            fAuth=FirebaseAuth.getInstance();
            final FirebaseDatabase database = FirebaseDatabase.getInstance();
            final FirebaseUser currentUser = fAuth.getCurrentUser();
            final Boolean[] userExits = {false};
            if (currentUser!= null){
//                Intent intent=new Intent(this, HomeA.class);
//                startActivity(intent);
//                fAuth.signOut();
            database.getReference("Advertiser").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.child(currentUser.getUid()).exists())
                        {
                            usertype="Advertiser";
                            if(!HomeAdvertiser) {
                                userExits[0] = true;
                                Intent intent = new Intent(MainActivity.this, HomeA.class);
                                startActivity(intent);
                                HomeAdvertiser=true;
                                finish();
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                database.getReference("Influencer").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.child(currentUser.getUid()).exists())
                        {
                            usertype="Influencer";
                            if(!HomeInfluencer) {
                                userExits[0] = true;
                                Intent intent = new Intent(MainActivity.this, HomeI.class);
                                startActivity(intent);
                                HomeInfluencer=true;
                                finish();
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }

            else {
                loadFragment(new Login());
            }


    }

    private void loadFragment(Fragment fragment)
    {
        //create a fragment manager
        FragmentManager manager=getFragmentManager();
        //create the fragment transaction
        FragmentTransaction fd=manager.beginTransaction();
        fd.replace(R.id.frame,fragment);
        fd.commit();

    }

    @Override




    
    protected void onResume() {
        super.onResume();
        if(FirebaseAuth.getInstance().getCurrentUser()!=null)
        {

        }
        else
        loadFragment(new Login());
    }
}