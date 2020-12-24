package com.example.socialinfluencer.Influencer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.socialinfluencer.DataModels.AdvertiserNotification;
import com.example.socialinfluencer.DataModels.InfluencerNotification;
import com.example.socialinfluencer.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * *hii
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class Notification extends Fragment {
    String sstat=" requests you to join ";
    String fstat=" has accepted your application of ";
    RecyclerView campaignList;
//    ArrayList<String> name = new ArrayList<>(Arrays.asList("Campaign Invitation", "Campaign Invitation", "Campaign Invitation", "Campaign Invitation", "Campaign Invitation", "Campaign Invitation"));
//    ArrayList<String> campaign = new ArrayList<>(Arrays.asList("Sports Zone", "Cannie Crew", "Good Looks", "Wood Bling", "Creative Tree", "Magic Brush"));
//    ArrayList<String> brand = new ArrayList<>(Arrays.asList("Sporty"+sstat+"Sports Zone", "Petso"+sstat+campaign.get(1), "Fabrico"+sstat+campaign.get(2), "Home Makers"+sstat+campaign.get(3), "Creative Minds"+sstat+campaign.get(4), "Make Up"+sstat+campaign.get(5)));
//
//    ArrayList<String> price = new ArrayList<>(Arrays.asList("44331", "43422", "55444", "33793", "27949", "72223"));
//    ArrayList<String> time = new ArrayList<>(Arrays.asList("9", "7", "3", "4", "10", "15"));
//
//    ArrayList<String> campaigN = new ArrayList<>(Arrays.asList("Sports Zone", "Cannie Crew", "Good Looks", "Wood Bling", "Creative Tree", "Magic Brush"));
//    ArrayList<String> namE = new ArrayList<>(Arrays.asList("Campaign Acceptence", "Campaign Acceptence", "Campaign Acceptence", "Campaign Acceptence", "Campaign Acceptence", "Campaign Acceptence"));
//    ArrayList<String> branD = new ArrayList<>(Arrays.asList("Sporty"+fstat+campaigN.get(0), "Petso"+fstat+campaigN.get(1), "Fabrico"+fstat+campaigN.get(2), "Home Makers"+fstat+campaigN.get(3), "Creative Minds"+fstat+campaigN.get(4), "Make Up"+fstat+campaigN.get(5)));

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    List<ItemClass> itemClassList;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment Notification.
     */
    // TODO: Rename and change types and number of parameters

    public Notification() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        campaignList = view.findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager((getContext()));
        linearLayoutManager.setAutoMeasureEnabled(true);
        campaignList.setLayoutManager(linearLayoutManager);
        campaignList.setNestedScrollingEnabled(false);
        itemClassList = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference("Influencer/"+ FirebaseAuth.getInstance().getUid()).child("Notification").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                AdvertiserNotification anf = null;
                for(DataSnapshot data:snapshot.getChildren())
                {
                    String key=data.getKey();
                    InfluencerNotification itf=data.getValue(InfluencerNotification.class);
                    if(itf.getNotificaionType().indexOf("Campaign Invitation")>-1)
                    {
                         itemClassList.add(new ItemClass(ItemClass.card, itf,key));
                    }
                    else
                    {
                        itemClassList.add(new ItemClass(ItemClass.card1, itf,0,key));
                    }
                }
                NotificationcustomAdapter customeAdapter = new NotificationcustomAdapter(itemClassList);
                campaignList.setAdapter(customeAdapter);

                NotificationcustomAdapter adapterClass
                        = new NotificationcustomAdapter(itemClassList);

//        customAdapter adapter
//                = new  customAdapter(itemClassList);
                campaignList.setAdapter(adapterClass);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //itemClassList.add(new ItemClass(card,"Campaign Acceptance"));
        //itemClassList.add(new ItemClass(card1, "Campaign Application"));
//        itemClassList.add(new ItemClass(ItemClass.card, name, brand, price, time));
//        itemClassList.add(new ItemClass(ItemClass.card1, namE, branD ));
//        itemClassList.add(new ItemClass(ItemClass.card1, namE, branD ));
//        itemClassList.add(new ItemClass(ItemClass.card1, namE, branD));
//        itemClassList.add(new ItemClass(ItemClass.card, name, branD, price, time));



    }
    public static Notification newInstance (String param1, String param2){
        Notification fragment = new Notification();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate (Bundle savedInstanceState){
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