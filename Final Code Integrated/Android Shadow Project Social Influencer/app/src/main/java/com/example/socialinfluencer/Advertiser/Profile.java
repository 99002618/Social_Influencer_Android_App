package com.example.socialinfluencer.Advertiser;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.socialinfluencer.DataModels.AdvertiserProfile;
import com.example.socialinfluencer.DataModels.Campaign_Data_Model;
import com.example.socialinfluencer.DataModels.InfluencerCampaigns;
import com.example.socialinfluencer.DataModels.InfluencerProfileData;
import com.example.socialinfluencer.Influencer.AdapterProfileCampaigns;
import com.example.socialinfluencer.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Profile extends Fragment {
//    ArrayList<String> title= new ArrayList<>(Arrays.asList("WoodEssence","Good Looks","Homemakers","WoodBling","Heavenly Clothes"));
//    ArrayList<String> description= new ArrayList<>(Arrays.asList("Shop the luxury furniture you need","Fashion friendly clothes for fashion enthusiasts",
//            "\n" +
//                    "Decorate your home with Furniture ","Crafted with excellence","We provide clothes more than just stitched fabric"));
//    ArrayList<String> category= new ArrayList<>(Arrays.asList("Furniture","Clothing","Furniture","Furniture","Clothing"));
//    ArrayList<String> endDate= new ArrayList<>(Arrays.asList("Running","Past","Running","Past","Running"));
//    Integer[] ar={R.drawable.fur1, R.drawable.cloth4,R.drawable.fur4,R.drawable.fur3,R.drawable.cloth2};

    AdvertiserProfile Advertiser;
    FirebaseAuth fAuth;
    String UserID;
    DatabaseReference ref;
    List<String> ICampaignID;
    List<InfluencerCampaigns> InfluencerC;
    List<Campaign_Data_Model> Camapign;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Profile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Profile.
     */
    // TODO: Rename and change types and number of parameters
    public static Profile newInstance(String param1, String param2) {
        Profile fragment = new Profile();
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
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final TextView brand=view.findViewById(R.id.brandname);
        final TextView Price=view.findViewById(R.id.pricenew);
        final TextView Cat1=view.findViewById(R.id.catA1);
        final TextView Cat2=view.findViewById(R.id.CatA2);
        final TextView Des=view.findViewById(R.id.Des);
        final ImageView image=view.findViewById(R.id.imageView2);
        RecyclerView campaignList=view.findViewById(R.id.recyclerView);
        FirebaseAuth fAuth =FirebaseAuth.getInstance();
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Advertiser/"+fAuth.getCurrentUser().getUid());
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child("Profile").exists())
                {
                    Advertiser=snapshot.child("Profile").getValue(AdvertiserProfile.class);
                    AdvertiserProfile profile= snapshot.child("Profile").getValue(AdvertiserProfile.class);
                    brand.setText(profile.getBrandName());
                    Price.setText(String.valueOf(profile.getTotal_Campaigns()));
                    List<String> Cat=profile.getCategories();
                    Cat1.setText(Cat.get(0));
                    Cat2.setText(Cat.get(1));
                    Des.setText(profile.getDescription());
                    Glide.with(getContext())
                            .load(profile.getProfilePhotoLink())
                            .into(image);
                }
                else
                {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ICampaignID=new ArrayList<>();
        InfluencerC=new ArrayList<>();
        Camapign=new ArrayList<>();

        DatabaseReference Cref=FirebaseDatabase.getInstance().getReference("Advertiser/"+fAuth.getCurrentUser().getUid()+"/Campaigns");
        Cref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    for(DataSnapshot datas: snapshot.getChildren()) {
                        ICampaignID.add(datas.getKey());
                        InfluencerCampaigns Camp = datas.getValue(InfluencerCampaigns.class);
                        InfluencerC.add(Camp);
                    }
                    for(int i=0;i<ICampaignID.size();i++)
                    {
                        DatabaseReference ref=FirebaseDatabase.getInstance().getReference("Campaigns/"+ICampaignID.get(i));
                        ref.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                Campaign_Data_Model scd= snapshot.getValue(Campaign_Data_Model.class);
                                Camapign.add(scd);
                                RecyclerView campaignList=view.findViewById(R.id.recyclerView);
                                LinearLayoutManager linearLayoutManager= new LinearLayoutManager((getContext()));
                                linearLayoutManager.setAutoMeasureEnabled(true);
                                campaignList.setLayoutManager(linearLayoutManager);
                                campaignList.setNestedScrollingEnabled(false);
                                AdapterAProfileCampaigns customeAdapter =new AdapterAProfileCampaigns(getContext(),ICampaignID,InfluencerC,Camapign);
                                campaignList.setAdapter(customeAdapter);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }

                        });
                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//        LinearLayoutManager linearLayoutManager= new LinearLayoutManager((getContext()));
//        linearLayoutManager.setAutoMeasureEnabled(true);
//        campaignList.setLayoutManager(linearLayoutManager);
//        campaignList.setNestedScrollingEnabled(false);
//        customAdapter customeAdapter =new customAdapter(title,description,category,endDate,ar,getContext());
//        campaignList.setAdapter(customeAdapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile2, container, false);
    }
}