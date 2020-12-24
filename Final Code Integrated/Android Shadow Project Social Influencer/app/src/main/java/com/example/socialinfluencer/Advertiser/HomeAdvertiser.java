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



import com.example.socialinfluencer.DataModels.InfluencerProfileData;
import com.example.socialinfluencer.R;
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
 * Use the {@link HomeAdvertiser#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeAdvertiser extends Fragment {
//    ArrayList<String> iname= new ArrayList<>(Arrays.asList("Rosy","Ram","John","Johnny","Jimmy","Clay","Bob","Ankitha","Girish","Sudarshana","Chethan","Thrinath"));
//    ArrayList<String> iprice= new ArrayList<>(Arrays.asList("20000","30000","34000","20000","10000","14000","50000","45000","36000","20600","60000","74000"));
//    ArrayList<String> icat1= new ArrayList<>(Arrays.asList("Furniture","Clothing","Pets","Sports","Cosmetics","Arts","Pets","Furniture","Arts","Sports","Cosmetics","Furniture"));
//    ArrayList<String> icat2= new ArrayList<>(Arrays.asList("Pets","Furniture","Arts","Sports","Cosmetics","Furniture","Furniture","Clothing","Pets","Sports","Cosmetics","Arts"));
//    ArrayList<String> instafollwers= new ArrayList<>(Arrays.asList("1000","1200","3000","4000","3478","2333","2000","2200","2300","2020","4478","2343"));
//    ArrayList<String> youtubesubscribers= new ArrayList<>(Arrays.asList("3331","4322","5544","3373","2799","7222","6000","5200","5000","4050","3458","5333"));
//    ArrayList<String> fbfollowers= new ArrayList<>(Arrays.asList("3231","4322","3544","7373","6799","7622","7800","8200","5080","4030","2458","4353"));
//    Integer[] ar={R.drawable.fur1,R.drawable.cloth4,R.drawable.pet1,R.drawable.sport3,R.drawable.cos3,R.drawable.art1,R.drawable.pet2,R.drawable.fur4,R.drawable.art3,R.drawable.sport1,R.drawable.cos2,R.drawable.fur3};
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<InfluencerProfileData> listData;
    private List<String> InfluencerID;

    public HomeAdvertiser() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeAdvertiser.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeAdvertiser newInstance(String param1, String param2) {
        HomeAdvertiser fragment = new HomeAdvertiser();
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        final RecyclerView InfluencerList=view.findViewById(R.id.recyclerViewa);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager((getContext()));
        linearLayoutManager.setAutoMeasureEnabled(true);
        InfluencerList.setLayoutManager(linearLayoutManager);
        InfluencerList.setNestedScrollingEnabled(false);
        listData=new ArrayList<>();
        InfluencerID=new ArrayList<>();
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Influencer");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot datas: dataSnapshot.getChildren()){

                    if(datas.hasChild("Profile")) {
                        InfluencerID.add(datas.getKey());
                        InfluencerProfileData Icd = datas.child("Profile").getValue(InfluencerProfileData.class);
//                    Log.d("/outputAdd",scd.getAdvertiserID());
                        listData.add(Icd);
                    }
                        InfluencerCustomAdapter customeAdapter = new InfluencerCustomAdapter(listData, InfluencerID, getContext());
                        InfluencerList.setAdapter(customeAdapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_advertiser, container, false);
    }
}