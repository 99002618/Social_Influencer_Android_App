package com.example.notificationadvertiser;

//public class customAdapter {
//}

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class customAdapter extends RecyclerView.Adapter<customAdapter.MyViewHolder>{
    ArrayList<String> aName;
    ArrayList<String> aInfluencer;
    ArrayList<String> aCampaign;
    ArrayList<String> aPrice;
    ArrayList<String> aTime;
    Context context;
    public customAdapter(ArrayList<String> cName, ArrayList<String> cInfluencer, ArrayList<String> cCampaign, ArrayList<String> cPrice, ArrayList<String> cTime, Context context) {
        this.aName = cName;
        this.aInfluencer= cInfluencer;
        this.aCampaign = cCampaign;
        this.aPrice = cPrice;
        this.aTime = cTime;

        this.context = context;
    }

    @NonNull
    @Override
    public customAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
        MyViewHolder vh= new MyViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(@NonNull customAdapter.MyViewHolder holder, int position) {
        TextView dName=holder.name;
        TextView dBrand=holder.influencer;
        TextView dCampaign=holder.campaign;
        TextView dPrice=holder.price;
        TextView dTime=holder.time;
//            Button hAccept=holder.Accept;
//            Button hReject=holder.Reject;
        CardView cam_card=holder.card;

        cam_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment campaign=new campaign();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame, campaign).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack("Campagin ID").commit();
            }
        });

//            hvp.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
//                    Fragment campaign=new InfluencerDetails();
//                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame, campaign).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack("Campagin ID").commit();
//                }
//            });

        dName.setText(aName.get(position));
        dCampaign.setText(aCampaign.get(position));
        dBrand.setText(aInfluencer.get(position));
        dPrice.setText(aPrice.get(position));
        dTime.setText(aTime.get(position));
    }

    @Override
    public int getItemCount() {
        return aName.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView influencer;
        TextView campaign;
        TextView price;
        TextView time;
        Button accept;
        Button reject;
        CardView card;

        public MyViewHolder(View v) {
            super(v);
            name=(TextView) v.findViewById(R.id.aNotificationName);
            influencer=(TextView) v.findViewById(R.id.aInfluencer);
            campaign=(TextView) v.findViewById(R.id.aCampaignName);
            price=(TextView) v.findViewById(R.id.aPrice);
            time=(TextView) v.findViewById(R.id.aTime);
            accept=(Button) v.findViewById(R.id.aAccept);
            reject=(Button) v.findViewById(R.id.aReject);
            card=(CardView)v.findViewById(R.id.card);

        }
    }

}