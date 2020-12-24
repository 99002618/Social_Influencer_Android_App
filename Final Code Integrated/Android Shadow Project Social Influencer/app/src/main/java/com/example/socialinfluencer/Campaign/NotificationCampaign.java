package com.example.socialinfluencer.Campaign;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.socialinfluencer.DataModels.Campaign_Data_Model;
import com.example.socialinfluencer.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link campaign#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationCampaign extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NotificationCampaign() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment campaign.
     */
    // TODO: Rename and change types and number of parameters
    public static campaign newInstance(String param1, String param2) {
        campaign fragment = new campaign();
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
        Bundle bundle = this.getArguments();
        String CamID = bundle.getString("CampaignID", "No-Campaign");
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Campaigns").child(CamID);
        final TextView CampName = view.findViewById(R.id.Camptitle);
        final TextView CampDes=view.findViewById(R.id.CampDescription);
        final TextView CampBudget=view.findViewById(R.id.Campbudget);
        final TextView CampAdvertiser=view.findViewById(R.id.CampAdvertiser);
        final TextView CampCat=view.findViewById(R.id.CampCategory);
        final TextView CampStartDate=view.findViewById(R.id.CampStartDate);
        final TextView CampEndDate=view.findViewById(R.id.CampEndDate);
        final TextView CampDaysRemaining=view.findViewById(R.id.DaysLeft);
        final ImageView img=view.findViewById(R.id.CampImg);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Campaign_Data_Model scd=snapshot.getValue(Campaign_Data_Model.class);
                CampName.setText(scd.getCampaign_Name());
                CampDes.setText(scd.getDescripton());
                CampAdvertiser.setText(scd.getAdvertiser_Name());
                String budget_C=String.valueOf(scd.getBudget());
                CampBudget.setText(budget_C);
                CampDaysRemaining.setText("5");
                CampEndDate.setText(scd.getEnd_Date());
                CampStartDate.setText(scd.getStart_Date());
                List<String> Cat=scd.getCategories();
                CampCat.setText(Cat.get(0));
                Glide.with(getContext())
                        .load(scd.getCampaign_image())
                        .into(img);

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
        return inflater.inflate(R.layout.fragment_campaign1, container, false);
    }
}