package com.example.socialinfluencer.Advertiser;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.socialinfluencer.Campaign.campaign;
import com.example.socialinfluencer.Campaign.campaign1;
import com.example.socialinfluencer.DataModels.Campaign_Data_Model;
import com.example.socialinfluencer.DataModels.InfluencerCampaigns;
import com.example.socialinfluencer.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class AdapterAProfileCampaigns extends RecyclerView.Adapter<AdapterAProfileCampaigns.MyViewHolder>{

//    ArrayList<String> cTitle;
//    ArrayList<String> cDescription;
//    ArrayList<String> cCategory;
//    ArrayList<String> cDate;
//    Integer[] arr;
    Context context;
    List<String> CampaignID;
    List<InfluencerCampaigns> campaignsStatus;
    FirebaseAuth fauth;

    private List<Campaign_Data_Model> details;

    public AdapterAProfileCampaigns(Context context, List<String> campaignID, List<InfluencerCampaigns> campaigns, List<Campaign_Data_Model> details) {
        this.context = context;
        this.CampaignID = campaignID;
        this.campaignsStatus = campaigns;
        this.details = details;
    }

//    public customAdapter(ArrayList<String> cTitle, ArrayList<String> cDescription, ArrayList<String> cCategory, ArrayList<String> cDate, Integer[] arr, Context context) {
//        this.cTitle = cTitle;
//        this.cDescription = cDescription;
//        this.cCategory = cCategory;
//        this.cDate = cDate;
//        this.arr = arr;
//        this.context = context;
//    }

    @NonNull
    @Override
    public AdapterAProfileCampaigns.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardprofileadvertiser,parent,false);
        MyViewHolder vh= new MyViewHolder(v);
        return vh;
    }
//

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        Campaign_Data_Model cd = details.get(position);
        InfluencerCampaigns icd=campaignsStatus.get(position);
        String CampID= CampaignID.get(position);
        fauth=FirebaseAuth.getInstance();
        TextView htitle = holder.title;
        htitle.setText(cd.getCampaign_Name());
        TextView hdescription = holder.description;
        hdescription.setText(cd.getDescripton());
        TextView hcat = holder.cat;
        List<String> cat=cd.getCategories();
        hcat.setText(cat.get(0));
        Button hknwMore = holder.knwMore;
        hknwMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment campaign = new campaign1();
                Bundle bundle = new Bundle();
                bundle.putString("CampaignID", CampaignID.get(position));
                campaign.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame, campaign).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack("Campagin ID").commit();

            }
        });
//        TextView status= holder.status;
//        status.setText(icd.getStatus());

        ImageView himage = holder.image;
        Glide.with(context)
                .load(cd.getCampaign_image())
                .into(himage);
        CardView cam_card = holder.card;
        cam_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment campaign = new campaign1();
                Bundle bundle = new Bundle();
                bundle.putString("CampaignID", CampaignID.get(position));
                campaign.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame, campaign).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack("Campagin ID").commit();


            }
        });



    }

//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
//        Campaign_Data_Model cd = details.get(position);
//        TextView htitle = holder.title;
//        TextView hdescription = holder.description;
//        TextView hcat = holder.cat;
//        TextView hdate = holder.date;
//        Button happly = holder.apply;
//        Button hknwMore = holder.knwMore;
//        fauth=FirebaseAuth.getInstance();
//
//        ImageView himage = holder.image;
//        CardView cam_card = holder.card;
//        cam_card.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AppCompatActivity activity = (AppCompatActivity) v.getContext();
//                Fragment campaign = new campaign();
//                Bundle bundle = new Bundle();
//                bundle.putString("CampaignID", CampaignID.get(position));
//                campaign.setArguments(bundle);
//                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame, campaign).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack("Campagin ID").commit();
//            }
//        });
//        hknwMore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AppCompatActivity activity = (AppCompatActivity) v.getContext();
//                Fragment campaign = new campaign();
//                Bundle bundle = new Bundle();
//                bundle.putString("CampaignID", CampaignID.get(position));
//                campaign.setArguments(bundle);
//                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame, campaign).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(campaign.toString()).commit();
//            }
//        });
//
//        htitle.setText(cd.getCampaign_Name());
//        hdescription.setText(cd.getDescripton());
//        List<String> Cat = cd.getCategories();
//        hcat.setText(Cat.get(0));
//      happly.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(context,"Applied Successfully",Toast.LENGTH_SHORT).show();
//                    DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Influencer/"+fauth.getUid());
//                    ref.child("Campaign").child(CampaignID.get(position)).child("Status").setValue("Applied");
////        hdate.setText("Apply by "+cd.getEnd_Date());
//        Glide.with(context)
//                .load(cd.getCampaign_image())
//                .into(holder.image);
//    }

    @Override
    public int getItemCount() {
        return details.size();
        
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;
        TextView cat;
        Button knwMore;
        ImageView image;
        CardView card;
        TextView status;

        public MyViewHolder(View v) {
            super(v);
            title=(TextView) v.findViewById(R.id.title);
            description=(TextView) v.findViewById(R.id.descrpt);
            cat=(TextView) v.findViewById(R.id.cat);
            knwMore=(Button) v.findViewById(R.id.knw);
            image=(ImageView)v.findViewById(R.id.image);
            card=(CardView)v.findViewById(R.id.card);
        }
    }


}
