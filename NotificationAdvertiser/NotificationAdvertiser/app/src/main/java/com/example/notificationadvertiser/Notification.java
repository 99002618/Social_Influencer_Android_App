package com.example.notificationadvertiser;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import static com.example.notificationadvertiser.ItemClass.card;
//import static com.example.notificationadvertiser.ItemClass.card1;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Notification#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Notification extends Fragment {
    ArrayList<String> name= new ArrayList<>(Arrays.asList("Campaign Application", "Campaign Application","Campaign Application","Campaign Application","Campaign Application","Campaign Application"));
    ArrayList<String> influencer= new ArrayList<>(Arrays.asList("Clay","Ankitha","Girish","Sudarshana","Chethan","Thrinath"));
    ArrayList<String> campaign= new ArrayList<>(Arrays.asList("Sports Zone","Cannie Crew","Good Looks","Wood Bling","Creative Tree","Magic Brush"));
    ArrayList<String> price= new ArrayList<>(Arrays.asList("44331","43422","55444","33793","27949","72223"));
    ArrayList<String> time= new ArrayList<>(Arrays.asList("9","7","3","4","10","15"));

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    ArrayList<String> namE= new ArrayList<>(Arrays.asList("Campaign Acceptance", "Campaign Acceptance","Campaign Acceptance","Campaign Acceptance","Campaign Acceptance","Campaign Acceptance"));
    ArrayList<String> influenceR= new ArrayList<>(Arrays.asList("Clay","Ankitha","Girish","Sudarshana","Chethan","Thrinath"));
    ArrayList<String> campaigN= new ArrayList<>(Arrays.asList("Cannie Crew","Good Looks","Wood Bling","Sports Zone","Creative Tree","Magic Brush"));

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Notification() {
        // Required empty public constructor
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView campaignList = view.findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager((getContext()));
        linearLayoutManager.setAutoMeasureEnabled(true);
        campaignList.setLayoutManager(linearLayoutManager);
        campaignList.setNestedScrollingEnabled(false);

        List<ItemClass> itemClassList = new ArrayList<>();

        //itemClassList.add(new ItemClass(card,"Campaign Acceptance"));
        //itemClassList.add(new ItemClass(card1, "Campaign Application"));
        itemClassList.add(new ItemClass(ItemClass.card,name,influencer,campaign,price,time));
        itemClassList.add(new ItemClass(ItemClass.card1,namE,influenceR,campaigN));
        itemClassList.add(new ItemClass(ItemClass.card1,namE,influenceR,campaigN));
        itemClassList.add(new ItemClass(ItemClass.card1,namE,influenceR,campaigN));
        itemClassList.add(new ItemClass(ItemClass.card,name,influencer,campaign,price,time));

        customAdapter customeAdapter =new customAdapter(itemClassList);
        campaignList.setAdapter(customeAdapter);

        customAdapter adapterClass
                = new  customAdapter(itemClassList);

//        customAdapter adapter
//                = new  customAdapter(itemClassList);
        campaignList.setAdapter(adapterClass);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Notification.
     */
    // TODO: Rename and change types and number of parameters
    public static Notification newInstance(String param1, String param2) {
        Notification fragment = new Notification();
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
        return inflater.inflate(R.layout.fragment_notification, container, false);
    }
}