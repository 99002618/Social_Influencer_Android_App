package com.example.socialinfluencer.Advertiser;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;
import android.view.View;



import com.example.socialinfluencer.MainActivity;

import com.example.socialinfluencer.R;
import com.example.socialinfluencer.SendNotification.APIService;
import com.example.socialinfluencer.SendNotification.Client;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;


import static com.example.socialinfluencer.Login.mGoogleSignInClient;
import static com.example.socialinfluencer.MainActivity.MainActivityRunning;

public class HomeA extends AppCompatActivity {
    FirebaseAuth fAuth ;
    public static boolean HomeAdvertiser;
    BottomNavigationView bottomNavigationView;
   public APIService apiService = Client.getClient().create(APIService.class); ;
    FragmentManager fragmentManager = getSupportFragmentManager();
    @Override
    protected void onStart() {
        super.onStart();
        HomeAdvertiser=true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        HomeAdvertiser=false;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_i);
        //replaceFragment(new HomeInfluencer());
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //your codes here

        }
        fAuth= FirebaseAuth.getInstance();
        bottomNavigationView= (BottomNavigationView)
                findViewById(R.id.nav);
        UpdateToken();
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                if(getSupportFragmentManager().getBackStackEntryCount()>0)
                                {
                                    getSupportFragmentManager().popBackStackImmediate();
                                }
                                replaceFragment(new HomeAdvertiser());
                                break;
                            case R.id.navigation_profile:
//                                Intent intent=new Intent(MainActivity.this,Search.class);
//                                startActivity(intent);
                                if(getSupportFragmentManager().getBackStackEntryCount()>0)
                                {
                                    getSupportFragmentManager().popBackStackImmediate();
                                }
                                replaceFragment(new Profile());
                                break;
                            case R.id.navigation_search:
                                if(getSupportFragmentManager().getBackStackEntryCount()>0)
                                {
                                    getSupportFragmentManager().popBackStackImmediate();
                                }
                                replaceFragment(new Search());
                                break;
                            case R.id.navigation_notification:
                                if(getSupportFragmentManager().getBackStackEntryCount()>0)
                                {
                                    getSupportFragmentManager().popBackStackImmediate();
                                }
                                replaceFragment(new Notification());
                                break;
                            case R.id.navigation_Logout:
                                if(fAuth!=null) {
                                    fAuth.signOut();
                                    // mGoogleSignInClient.signOut();
                                    finish();
                                    if(mGoogleSignInClient!=null)
                                    {
                                        mGoogleSignInClient.signOut();
                                    }
                                    if(!MainActivityRunning)
                                    {
                                        Intent intent=new Intent(HomeA.this, MainActivity.class);
                                        startActivity(intent);
                                        MainActivityRunning=true;
                                    }
                                }
                                break;

                        }
                        return true;
                    }
                });
        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.navigation_home); // change to whichever id should be default
        }

//        findViewById(R.id.signOut).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(fAuth!=null) {
//                    fAuth.signOut();
//                    //mGoogleSignInClient.signOut();
//                    finish();
//                    if(mGoogleSignInClient!=null)
//                    {
//                        mGoogleSignInClient.signOut();
//
//                    }
//                    if(!MainActivityRunning)
//                    {
//                        Intent intent=new Intent(HomeI.this,MainActivity.class);
//                        startActivity(intent);
//                    }
//                }
//
//            }
//        });
    }
    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.addToBackStack(fragment.toString());
        if(getSupportFragmentManager().getBackStackEntryCount()>0)
        {
            getSupportFragmentManager().popBackStackImmediate();
        }
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (getSupportFragmentManager().getBackStackEntryCount()==0)
        {
            int a=bottomNavigationView.getSelectedItemId();
            this.moveTaskToBack(true);
            bottomNavigationView.setSelectedItemId(a);
            if(a==R.id.navigation_home) {
                bottomNavigationView.getMenu().getItem(0).setChecked(true);
            }
            if(a==R.id.navigation_search)
            {
                bottomNavigationView.getMenu().getItem(1).setChecked(true);
            }
            if(a==R.id.navigation_profile)
            {
                bottomNavigationView.getMenu().getItem(2).setChecked(true);
            }
            if(a==R.id.navigation_notification)
            {
                bottomNavigationView.getMenu().getItem(3).setChecked(true);
            }
        }
        else
        {

        }
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }
    private void UpdateToken(){

        final FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        final String refreshToken= FirebaseInstanceId.getInstance().getToken();
        FirebaseDatabase.getInstance().getReference("Advertiser/"+firebaseUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    FirebaseDatabase.getInstance().getReference("Advertiser/"+firebaseUser.getUid()).child("token").setValue(refreshToken);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
           }
}