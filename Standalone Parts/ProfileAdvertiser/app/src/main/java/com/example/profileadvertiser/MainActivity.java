package com.example.profileadvertiser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        replaceFragment(new Home());
//        RecyclerView campaignList=findViewById(R.id.recyclerView);
        bottomNavigationView= (BottomNavigationView)
                findViewById(R.id.nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                replaceFragment(new Home());
                                break;
                            case R.id.navigation_profile:
//                                Intent intent=new Intent(MainActivity.this,Search.class);
//                                startActivity(intent);
                                replaceFragment(new Profile());
                                break;
                            case R.id.navigation_search:
                                replaceFragment(new Search());
                                break;
                            case R.id.navigation_notification:
                                replaceFragment(new Notification());
                                break;

                        }
                        return true;
                    }
                });
//        LinearLayoutManager linearLayoutManager= new LinearLayoutManager((getApplicationContext()));
//        linearLayoutManager.setAutoMeasureEnabled(true);
//        campaignList.setLayoutManager(linearLayoutManager);
//        campaignList.setNestedScrollingEnabled(false);
//
//        customAdapter customeAdapter =new customAdapter(title,description,category,endDate,ar,MainActivity.this);
//        campaignList.setAdapter(customeAdapter);
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.addToBackStack(fragment.toString());
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }

}