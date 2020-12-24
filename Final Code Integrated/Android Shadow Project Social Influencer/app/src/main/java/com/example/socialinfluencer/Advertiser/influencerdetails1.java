package com.example.socialinfluencer.Advertiser;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.socialinfluencer.DataModels.InfluencerProfileData;
import com.example.socialinfluencer.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InfluencerDetails#newInstance} factory method to
 * create an instance of this fragment.
 */
public class influencerdetails1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public influencerdetails1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InfluencerDetails.
     */
    // TODO: Rename and change types and number of parameters
    public static InfluencerDetails newInstance(String param1, String param2) {
        InfluencerDetails fragment = new InfluencerDetails();
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
        String InfluencerID = bundle.getString("InfluencerID", "No-Influencer");
//        Toast.makeText(getContext(), InfluencerID, Toast.LENGTH_SHORT).show();
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Influencer").child(InfluencerID);
        final TextView Name=view.findViewById(R.id.textView21);
        final TextView Cat1=view.findViewById(R.id.textView);
        final TextView Cat2=view.findViewById(R.id.textView2);
        final TextView Price=view.findViewById(R.id.textView9);
        final TextView IFollowing=view.findViewById(R.id.intsaFollowing_number);
        final TextView IFollowers=view.findViewById(R.id.instaFollower_number);
        final TextView IPosts=view.findViewById(R.id.instaPosts_Number);
        final TextView IEngagement=view.findViewById(R.id.instaEngagement_percent);
        final TextView FFollowing=view.findViewById(R.id.instagramFollowing);
        final TextView FFollowers=view.findViewById(R.id.instagramFollower);
        final TextView FPosts=view.findViewById(R.id.instagramPosts);
        final TextView FEngagement=view.findViewById(R.id.InstaEngagement_percent);
        final TextView YSubscribers=view.findViewById(R.id.ytSubscribers_number);
        final TextView YWatchHours=view.findViewById(R.id.ytWatchHours_number);
        final TextView YPosts=view.findViewById(R.id.ytPosts_Number);
        final TextView YImpressions=view.findViewById(R.id.ytImpressionst_percent);
        final ImageView profile=view.findViewById(R.id.imageView3);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                InfluencerProfileData Influencer=snapshot.child("Profile").getValue(InfluencerProfileData.class);
                Name.setText(Influencer.getInfluncer_Name());
                List<String> Cat=Influencer.getCategories();
                Cat1.setText(Cat.get(0));
                Cat2.setText(Cat.get(1));
                Price.setText(String.valueOf(Influencer.getInflucencer_Price()));
                IFollowing.setText(String.valueOf(Influencer.getInstagram().getFollowing()));
                IFollowers.setText(String.valueOf(Influencer.getInstagram().getFollowers()));
                IPosts.setText(String.valueOf(Influencer.getInstagram().getPosts()));
                IEngagement.setText(Influencer.getInstagram().getEngagement());
                FFollowing.setText(String.valueOf(Influencer.getFacebook().getFollowing()));
                FFollowers.setText(String.valueOf(Influencer.getFacebook().getFollowers()));
                FPosts.setText(String.valueOf(Influencer.getFacebook().getPosts()));
                FEngagement.setText(Influencer.getFacebook().getEngagement());
                YSubscribers.setText(String.valueOf(Influencer.getYoutube().getSubscribers()));
                YImpressions.setText(String.valueOf(Influencer.getYoutube().getImpressions()));
                YWatchHours.setText(String.valueOf(Influencer.getYoutube().getWatchHours()));
                YPosts.setText(String.valueOf(Influencer.getYoutube().getPosts()));
                Glide.with(getContext())
                        .load(Influencer.getProfilePhoto_Link())
                        .into(profile);
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
        return inflater.inflate(R.layout.fragment_influencerdetails1, container, false);
    }
}