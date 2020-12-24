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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;

import com.example.socialinfluencer.DataModels.Campaign_Data_Model;
import com.example.socialinfluencer.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Search#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Search extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    String s;
    private List<String> CampID;
    SearchCampaignsAdapter adapter;
    List<Campaign_Data_Model> listData;
    RecyclerView Campaigns;
    String querySearch;
    private List<Campaign_Data_Model> lcampaignsistData;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Search() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Search.
     */
    // TODO: Rename and change types and number of parameters
    public static Search newInstance(String param1, String param2) {
        Search fragment = new Search();
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

        querySearch="";

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Campaigns = view.findViewById(R.id.SearchResults);
        final SearchView search=view.findViewById(R.id.influencerSearch);
        Spinner spin = view.findViewById(R.id.spinner2);
        //Spinner spin = (Spinner) view.findViewById(R.id.spinner2);
        querySearch=" ";
        String[] items = new String[]{"--- CATEGORY ---","Sports", "Pets", "Cosmetics","Furniture","Clothing","Arts","---- PRICE ----","10000-15000","16000-20000","21000-30000"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,items);
        //ArrayAdapter adapter = new ArrayAdapter(context,android.R.layout.simple_spinner_item,items);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter1);

        String text = spin.getSelectedItem().toString();

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchProcess(query);
                querySearch=query;
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchProcess(newText);
                querySearch=newText;
                return false;
            }
        });
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){

                    case 0:
//                        searchCategory1(">>>Filter>>>");
//                        Toast.makeText(getContext(),"display somthig 00000",
//                                Toast.LENGTH_SHORT).show();
                        break;


                    case 1:
                        searchCategory1("Sports");

//                        Toast.makeText(getContext(),"display somthig 00000",
//                                Toast.LENGTH_SHORT).show();

                        break;
                    case 2:
                        searchCategory1("Pets");
//                        Toast.makeText(getContext(),"display somthig  1111",
//                                Toast.LENGTH_SHORT).show();

                        break;
//                    case 2:
//                        Toast.makeText(getContext(),"display somthig 22222",
//                                Toast.LENGTH_SHORT).show();

                    // break;
                    case 3:
                        searchCategory1("Cosmetics");
//                        Toast.makeText(getContext(),"display somthig 00000",
//                                Toast.LENGTH_SHORT).show();

                        break;
                    case 4:
                        searchCategory1("Furniture");
//                        Toast.makeText(getContext(),"display somthig 00000",
//                                Toast.LENGTH_SHORT).show();

                        break;
                    case 5:
                        searchCategory1("Clothing");
//                        Toast.makeText(getContext(),"display somthig 00000",
//                                Toast.LENGTH_SHORT).show();

                        break;
                    case 6:
                        searchCategory1("Arts");
//                        Toast.makeText(getContext(),"display somthig 00000",
//                                Toast.LENGTH_SHORT).show();

                        break;
                    case 7:
//                        searchCategory1(">>>Price>>>");
//                        Toast.makeText(getContext(),"display somthig 00000",
//                                Toast.LENGTH_SHORT).show();
                        break;


                    case 8:
                        searchCategoryprice("10000-15000");


                        break;
                    case 9:
                        searchCategoryprice("16000-20000");


                        break;
                    case 10:
                        searchCategoryprice("21000-30000");


                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchProcess(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchProcess(newText);
                return false;
            }
        });
        searchProcess("");
//        DatabaseReference CamapignsRef = FirebaseDatabase.getInstance().getReference("Campaigns");
//        Query CampaignsQuery = CamapignsRef.orderByKey();
//        Campaigns.setLayoutManager(new LinearLayoutManager(getContext()));
//        customAdapter adapter = new customAdapter(listData,  CampID,getContext());
//        Campaigns.setAdapter(adapter);

    }
    public void searchProcess(String s)
    {

        DatabaseReference CamapignsRef = FirebaseDatabase.getInstance().getReference("Campaigns");
        Query CampaignsQuery = CamapignsRef.orderByChild("Campaign_Name").startAt(s).endAt(s+"\uff8f");
        Campaigns.setLayoutManager(new LinearLayoutManager(getContext()));
        searchCategory(s);
//        customAdapter adapter = new customAdapter(listData,  CampID,getContext());
//        Campaigns.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public void onStop() {
        super.onStop();

    }

    public void searchCategoryprice(final String s)
    {
        CampID=new ArrayList<>();
        listData=new ArrayList<>();
        CampID=new ArrayList<>();
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Campaigns");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot datas: dataSnapshot.getChildren()){
                    Campaign_Data_Model scd=datas.getValue(Campaign_Data_Model.class);
                    long price = scd.getBudget();
                    String[] numberss=s.split("-");
                    int lower=Integer.valueOf(numberss[0]);
                    int upper=Integer.valueOf(numberss[1]);

                    //List<String> Cat=scd.getBudget();
                    if(scd.getCampaign_Name().toUpperCase().indexOf(querySearch.toUpperCase())!=-1) {
                        if(scd.getBudget()>=lower && scd.getBudget()<=upper)
                        {   listData.add(scd);CampID.add(datas.getKey());}

                    }
//                    Toast.makeText(getContext(),querySearch,
//                            Toast.LENGTH_SHORT).show();
//                    Log.d("/outputAdd",scd.getAdvertiserID());
                    customAdapter adapter = new customAdapter(listData,  CampID,getContext());
                    Campaigns.setAdapter(adapter);
                    //get other items
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void searchCategory1(final String s)
    {

        CampID=new ArrayList<>();
        listData=new ArrayList<>();
        CampID=new ArrayList<>();
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Campaigns");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot datas: dataSnapshot.getChildren()){

                    CampID.add(datas.getKey());
                    Campaign_Data_Model scd=datas.getValue(Campaign_Data_Model.class);
                    List<String> Cat=scd.getCategories();
                    if(scd.getCampaign_Name().toUpperCase().indexOf(querySearch.toUpperCase())!=-1&& Cat.get(0).toUpperCase().indexOf(s.toUpperCase())!=-1)
                        listData.add(scd);
//                    Toast.makeText(getContext(),querySearch,
//                            Toast.LENGTH_SHORT).show();
//                    Log.d("/outputAdd",scd.getAdvertiserID());
                    customAdapter adapter = new customAdapter(listData,  CampID,getContext());
                    Campaigns.setAdapter(adapter);
                    //get other items
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



    public void searchCategory(final String s)
    {
        CampID=new ArrayList<>();
        listData=new ArrayList<>();
        CampID=new ArrayList<>();

        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Campaigns");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot datas: dataSnapshot.getChildren()){
                    Campaign_Data_Model scd=datas.getValue(Campaign_Data_Model.class);
                  List<String> Cat=scd.getCategories();
                    if(scd.getCampaign_Name().toUpperCase().indexOf(s.toUpperCase())!=-1)
                    {listData.add(scd);CampID.add(datas.getKey());}
//                    Log.d("/outputAdd",scd.getAdvertiserID());
                    customAdapter adapter = new customAdapter(listData,CampID,getContext());
                    Campaigns.setAdapter(adapter);
                    //get other items
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}


