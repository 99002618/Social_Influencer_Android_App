package com.example.homeinfluencer;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {

    ArrayList<String> title= new ArrayList<>(Arrays.asList("WoodEssence","Good Looks","Canine Crew","Peak Sports","Dream Shades","Creative Tree","Fur Life","Homemakers Furniture","Magic Drop","Sports Zone","Magic Brush","WoodBling","Team Sweep","Heavenly Clothes"));
    ArrayList<String> description= new ArrayList<>(Arrays.asList("Shop the luxury furniture you need","Fashion friendly clothes for fashion enthusiasts","For wagging tails and more","When you feel down and out choose to play a sport","Beauty is Whatever Brings Perfect","Adding Creativity Through Brush \n" +
            "\n" +
            "A Store that is full of Surprise Fur beings","Decorate your home with Furniture ","Bringing Joy Of Creativity"," Talk with your feet, play with your heart","Adding Beauty Through Brush","Crafted with excellence","Sports are great at relieving stress!","We provide clothes more than just stitched fabric"));
    ArrayList<String> category= new ArrayList<>(Arrays.asList("Furniture","Clothing","Pets","Sports","Cosmetics","Arts","Pets","Furniture","Arts","Sports","Cosmetics","Furniture","Sports","Clothing"));
    ArrayList<String> endDate= new ArrayList<>(Arrays.asList("20-12-20","16-12-20","18-12-20","20-12-20","15-12-20","28-12-20","1-12-20","8-12-20","10-12-20","12-12-20","17-12-20","23-12-20","17-12-20","23-12-20"));
    Integer[] ar={R.drawable.fur1,R.drawable.cloth4,R.drawable.pet1,R.drawable.sport3,R.drawable.cos3,R.drawable.art1,R.drawable.pet2,R.drawable.fur4,R.drawable.art3,R.drawable.sport1,R.drawable.cos2,R.drawable.fur3,R.drawable.sport2,R.drawable.cloth2};
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Home() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView campaignList=view.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager((getContext()));
        linearLayoutManager.setAutoMeasureEnabled(true);
        campaignList.setLayoutManager(linearLayoutManager);
        campaignList.setNestedScrollingEnabled(false);
        customAdapter customeAdapter =new customAdapter(title,description,category,endDate,ar,getContext());
        campaignList.setAdapter(customeAdapter);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home.
     */
    // TODO: Rename and change types and number of parameters
    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        CardView camp=(CardView)findViewById(R.id.card);
        final Intent intent=new Intent(this,campaign.class);
        replaceFragment(new Home());
        camp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra(NAME,"area");
                startActivity(intent);
            }
        });

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}