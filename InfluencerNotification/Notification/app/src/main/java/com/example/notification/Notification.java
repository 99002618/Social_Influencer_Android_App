package com.example.notification;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Notification#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Notification extends Fragment {
    ArrayList<String> Name= new ArrayList<>(Arrays.asList("Campaign Invitation", "Campaign Invitation","Campaign Invitation","Campaign Invitation","Campaign Invitation","Campaign Invitation"));
    ArrayList<String> Brand= new ArrayList<>(Arrays.asList("Sporty","Petso","Fabrico","Home Makers", "Creative Minds", "Make Up"));
    ArrayList<String> Campaign= new ArrayList<>(Arrays.asList("Sports Zone","Cannie Crew","Good Looks","Wood Bling","Creative Tree","Magic Brush"));
    ArrayList<String> Price= new ArrayList<>(Arrays.asList("44331","43422","55444","33793","27949","72223"));
    ArrayList<String> Time= new ArrayList<>(Arrays.asList("9","7","3","4","10","15"));

<<<<<<< HEAD:Notification/app/src/main/java/com/example/notification/Notification.java
=======
    ArrayList<String> iname= new ArrayList<>(Arrays.asList("Rosy","Ram","John","Johnny","Jimmy","Clay","Bob","Ankitha","Girish","Sudarshana","Chethan","Thrinath"));
    ArrayList<String> iprice= new ArrayList<>(Arrays.asList("20000","30000","34000","20000","10000","14000","50000","45000","36000","20600","60000","74000"));
    ArrayList<String> icat1= new ArrayList<>(Arrays.asList("Furniture","Clothing","Pets","Sports","Cosmetics","Arts","Pets","Furniture","Arts","Sports","Cosmetics","Furniture"));
    ArrayList<String> icat2= new ArrayList<>(Arrays.asList("Pets","Furniture","Arts","Sports","Cosmetics","Furniture","Furniture","Clothing","Pets","Sports","Cosmetics","Arts"));
    ArrayList<String> instafollwers= new ArrayList<>(Arrays.asList("1000","1200","3000","4000","3478","2333","2000","2200","2300","2020","4478","2343"));
    ArrayList<String> youtubesubscribers= new ArrayList<>(Arrays.asList("3331","4322","5544","3373","2799","7222","6000","5200","5000","4050","3458","5333"));
    ArrayList<String> fbfollowers= new ArrayList<>(Arrays.asList("3231","4322","3544","7373","6799","7622","7800","8200","5080","4030","2458","4353"));
    Integer[] ar={R.drawable.fur1,R.drawable.cloth4,R.drawable.pet1,R.drawable.sport3,R.drawable.cos3,R.drawable.art1,R.drawable.pet2,R.drawable.fur4,R.drawable.art3,R.drawable.sport1,R.drawable.cos2,R.drawable.fur3};
>>>>>>> ca69c26eeb7dfabc40777c24c29d0854d747f835:ankitha-main/app/src/main/java/com/example/homenotiadvertiser/Home.java
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Notification() {
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
<<<<<<< HEAD:Notification/app/src/main/java/com/example/notification/Notification.java
        customAdapter customeAdapter =new customAdapter(Name,Brand,Campaign,Price,Time,getContext());
=======
        customAdapter customeAdapter =new customAdapter(iname,iprice,icat1,icat2,instafollwers,youtubesubscribers,fbfollowers,ar,getContext());
>>>>>>> ca69c26eeb7dfabc40777c24c29d0854d747f835:ankitha-main/app/src/main/java/com/example/homenotiadvertiser/Home.java
        campaignList.setAdapter(customeAdapter);
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