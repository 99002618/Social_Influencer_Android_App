package com.example.homeinfluencer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

    ArrayList<String> title= new ArrayList<>(Arrays.asList("Pet Dogs","Classmate","Creative Hub","Pet Dogs","Classmate","Creative Hub"));
    ArrayList<String> description= new ArrayList<>(Arrays.asList("Dogs need home","Perfect book store","Be creative at our hub","Dogs need home","Perfect book store","Be creative at our hub"));
    ArrayList<String> category= new ArrayList<>(Arrays.asList("Pets","Stationary","Arts","Pets","Stationary","Arts"));
    ArrayList<String> endDate= new ArrayList<>(Arrays.asList("20-12-20","16-12-20","18-12-20","20-12-20","16-12-20","18-12-20"));
    Integer[] ar={R.drawable.a1,R.drawable.a2,R.drawable.a3,R.drawable.a1,R.drawable.a2,R.drawable.a3};
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}