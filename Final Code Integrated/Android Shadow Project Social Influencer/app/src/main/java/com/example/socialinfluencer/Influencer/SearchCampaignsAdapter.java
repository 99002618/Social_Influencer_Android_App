package com.example.socialinfluencer.Influencer;

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
import com.example.socialinfluencer.DataModels.Campaign_Data_Model;
import com.example.socialinfluencer.Campaign.campaign;
import com.example.socialinfluencer.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.List;

public class SearchCampaignsAdapter extends FirebaseRecyclerAdapter<Campaign_Data_Model,SearchCampaignsAdapter.MyViewHolder> {

     Context context;
     List<String> CampaignID;
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public SearchCampaignsAdapter(@NonNull FirebaseRecyclerOptions<Campaign_Data_Model> options,Context context,List<String> campID) {
        super(options);
        this.context=context;
        this.CampaignID=campID;
    }

    @Override
    protected void onBindViewHolder(@NonNull SearchCampaignsAdapter.MyViewHolder holder, final int position, @NonNull Campaign_Data_Model model) {
        Campaign_Data_Model cd=model;
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
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame, campaign).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(campaign.toString()).commit();
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

    @NonNull
    @Override
    public SearchCampaignsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
        SearchCampaignsAdapter.MyViewHolder vh= new SearchCampaignsAdapter.MyViewHolder(v);
        return vh;
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
