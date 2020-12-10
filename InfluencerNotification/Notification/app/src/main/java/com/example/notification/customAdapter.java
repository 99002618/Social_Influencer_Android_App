package com.example.notification;

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
    ArrayList<String> cName;
    ArrayList<String> cBrand;
    ArrayList<String> cCampaign;
    ArrayList<String> cPrice;
    ArrayList<String> cTime;
    Context context;
    public customAdapter(ArrayList<String> cName, ArrayList<String> cBrand, ArrayList<String> cCampaign, ArrayList<String> cPrice, ArrayList<String> cTime, Context context) {
        this.cName = cName;
        this.cBrand= cBrand;
        this.cCampaign = cCampaign;
        this.cPrice = cPrice;
        this.cTime = cTime;

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
        TextView hName=holder.Name;
        TextView hBrand=holder.Brand;
        TextView hCampaign=holder.Campaign;
        TextView hPrice=holder.Price;
        TextView hTime=holder.Time;
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

        hName.setText(cName.get(position));
        hCampaign.setText(cCampaign.get(position));
        hBrand.setText(cBrand.get(position));
        hPrice.setText(cPrice.get(position));
        hTime.setText(cTime.get(position));
    }

    @Override
    public int getItemCount() {
        return cName.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView Name;
        TextView Brand;
        TextView Campaign;
        TextView Price;
        TextView Time;
        Button Accept;
        Button Reject;
        CardView card;

        public MyViewHolder(View v) {
            super(v);
            Name=(TextView) v.findViewById(R.id.notificationName);
            Brand=(TextView) v.findViewById(R.id.brandName);
            Campaign=(TextView) v.findViewById(R.id.campaignName);
            Price=(TextView) v.findViewById(R.id.price);
            Time=(TextView) v.findViewById(R.id.time);
            Accept=(Button) v.findViewById(R.id.accept);
            Reject=(Button) v.findViewById(R.id.reject);
            card=(CardView)v.findViewById(R.id.card);

        }
    }

}


