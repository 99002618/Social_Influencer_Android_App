package com.example.homeinfluencer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
//    ArrayList<String> title= new ArrayList<>(Arrays.asList("Pet Dogs","Classmate","Creative Hub","Pet Dogs","Classmate","Creative Hub"));
//    ArrayList<String> description= new ArrayList<>(Arrays.asList("Dogs need home","Perfect book store","Be creative at our hub","Dogs need home","Perfect book store","Be creative at our hub"));
//    ArrayList<String> category= new ArrayList<>(Arrays.asList("Pets","Stationary","Arts","Pets","Stationary","Arts"));
//    ArrayList<String> endDate= new ArrayList<>(Arrays.asList("20-12-20","16-12-20","18-12-20","20-12-20","16-12-20","18-12-20"));
//    Integer[] ar={R.drawable.a1,R.drawable.a2,R.drawable.a3,R.drawable.a1,R.drawable.a2,R.drawable.a3};
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