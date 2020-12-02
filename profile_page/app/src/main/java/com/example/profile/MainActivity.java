package com.example.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> title= new ArrayList<>(Arrays.asList("Pet Dogs","Classmate","Creative Hub","Pet Dogs","Classmate","Creative Hub"));
    ArrayList<String> description= new ArrayList<>(Arrays.asList("Dogs need home","Perfect book store","Be creative at our hub","Dogs need home","Perfect book store","Be creative at our hub"));
    ArrayList<String> category= new ArrayList<>(Arrays.asList("Pets","Stationary","Arts","Pets","Stationary","Arts"));
    ArrayList<String> endDate= new ArrayList<>(Arrays.asList("20-12-20","16-12-20","18-12-20","20-12-20","16-12-20","18-12-20"));
    Integer[] ar={R.drawable.a1,R.drawable.a2,R.drawable.a3,R.drawable.a1,R.drawable.a2,R.drawable.a3};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView campaignList=findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(MainActivity.this);
        linearLayoutManager.setAutoMeasureEnabled(true);
        campaignList.setLayoutManager(linearLayoutManager);
        campaignList.setNestedScrollingEnabled(false);
        customAdapter customeAdapter =new customAdapter(title,description,category,endDate,ar,MainActivity.this);
        campaignList.setAdapter(customeAdapter);
    }

    }
