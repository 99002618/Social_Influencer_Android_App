package com.example.homeinfluencer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class customAdapter extends RecyclerView.Adapter<customAdapter.MyViewHolder>{

    ArrayList<String> cTitle;
    ArrayList<String> cDescription;
    ArrayList<String> cCategory;
    ArrayList<String> cDate;
    Integer[] arr;
    Context context;
    List<String> CampaignID;

    private List<Campaign_Details> details;

    public customAdapter(List<Campaign_Details> details,List<String> CampaignID,Context ctx) {
        this.details = details;
        this.CampaignID=CampaignID;
        this.context=ctx;
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
    public customAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
        MyViewHolder vh= new MyViewHolder(v);
        return vh;
    }
//
    @Override
    public void onBindViewHolder(@NonNull customAdapter.MyViewHolder holder, final int position) {
        Campaign_Details cd=details.get(position);
        TextView htitle=holder.title;
        TextView hdescription=holder.description;
        TextView hcat=holder.cat;
        TextView hdate=holder.date;
        Button happly=holder.apply;
        Button hknwMore=holder.knwMore;

        ImageView himage=holder.image;
        CardView cam_card=holder.card;
        cam_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment campaign=new campaign();
                Bundle bundle = new Bundle();
                bundle.putString("CampaignID", CampaignID.get(position));
                campaign.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame, campaign).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack("Campagin ID").commit();
            }
        });
        hknwMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment campaign=new campaign();
                Bundle bundle = new Bundle();
                bundle.putString("CampaignID", CampaignID.get(position));
                campaign.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame, campaign).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack("Campagin ID").commit();
            }
        });
        
        htitle.setText(cd.getCampaign_Name());
        hdescription.setText(cd.getDescripton());
        List<String> Cat=cd.getCategories();
       hcat.setText(Cat.get(0));
        hdate.setText("Apply by "+cd.getEnd_Date());
        Glide.with(context)
                .load(cd.getCampaign_image())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return details.size();
        
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;
        TextView cat;
        TextView date;
        Button apply;
        Button knwMore;
        ImageView image;
        CardView card;

        public MyViewHolder(View v) {
            super(v);
            title=(TextView) v.findViewById(R.id.title);
            description=(TextView) v.findViewById(R.id.descrpt);
            cat=(TextView) v.findViewById(R.id.cat);
            date=(TextView) v.findViewById(R.id.date);
            apply=(Button) v.findViewById(R.id.apply);
            knwMore=(Button) v.findViewById(R.id.knw);
            image=(ImageView)v.findViewById(R.id.image);
            card=(CardView)v.findViewById(R.id.card);

        }
    }


}
