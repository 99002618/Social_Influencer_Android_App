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
import com.example.socialinfluencer.DataModels.Facebook;
import com.example.socialinfluencer.DataModels.InfluencerProfileData;
import com.example.socialinfluencer.DataModels.Instagram;
import com.example.socialinfluencer.DataModels.Youtube;
import com.example.socialinfluencer.R;

import java.util.ArrayList;
import java.util.List;

public class InfluencerCustomAdapter extends RecyclerView.Adapter<InfluencerCustomAdapter.MyViewHolder>{


    private List<InfluencerProfileData> listData;
    private List<String> InfluencerID;
    Context context;

    public InfluencerCustomAdapter(List<InfluencerProfileData> listData, List<String> influencerID, Context context) {
        this.listData = listData;
        InfluencerID = influencerID;
        this.context = context;
    }

    @NonNull
        @Override
        public InfluencerCustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardadvertiser,parent,false);
            MyViewHolder vh= new MyViewHolder(v);
            return vh;
        }


        @Override
        public void onBindViewHolder(@NonNull InfluencerCustomAdapter.MyViewHolder holder, final int position) {
           InfluencerProfileData Ifd= listData.get(position);
            TextView hname=holder.iname;
            //TextView hpricetxt=holder.ipricetext;
            TextView hprice=holder.iprice;
            //TextView hfollowerstxt=holder.ifollowerstxt;
            TextView hinstafollowers=holder.instafollowers;
           // TextView hffollowerstxt=holder.ffollowerstxt;
            TextView hfbfollowers=holder.fbfollowers;
            TextView hyoutubesubscribers=holder.youtubesubscribers;
           // TextView hysubscriberstxt=holder.ysubscriberstxt;
           // TextView hcategory=holder.icat;
            TextView hcat1=holder.icat1;
            TextView hcat2=holder.icat2;

            Button hvp=holder.vp;
            Button hrq=holder.rq;

            ImageView himage=holder.image;
            CardView cam_card=holder.card;
            cam_card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    Fragment InfluencerD=new InfluencerDetails();
                    Bundle bundle = new Bundle();
                    bundle.putString("InfluencerID", InfluencerID.get(position));
                    InfluencerD.setArguments(bundle);
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame,InfluencerD).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack("Campagin ID").commit();
                }
            });
            hvp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    Fragment InfluencerD=new InfluencerDetails();
                    Bundle bundle = new Bundle();
                    bundle.putString("InfluencerID", InfluencerID.get(position));
                    InfluencerD.setArguments(bundle);
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame,InfluencerD).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack("Campagin ID").commit();
                }
            });

            hrq.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    Fragment InfluencerD=new CampaignSelection();
                    Bundle bundle = new Bundle();
                    bundle.putString("InfluencerID", InfluencerID.get(position));
                    InfluencerD.setArguments(bundle);
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame,InfluencerD).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack("Campagin ID").commit();

                }
            });
            hname.setText(Ifd.getInfluncer_Name());
            //hpricetxt.setText(cpricetxt.get(position));

            hprice.setText(String.valueOf(Ifd.getInflucencer_Price()));
           // hcategory.setText(cCategory.get(position));
            List<String> Cat=Ifd.getCategories();

            hcat1.setText(Cat.get(0));
            hcat2.setText(Cat.get(1));

           // hfollowerstxt.setText(cfollowerstxt.get(position));
            String sIfollowers=String.valueOf(Ifd.getInstagram().getFollowers());
  String sFfollowers=String.valueOf(Ifd.getFacebook().getFollowers());
            String sSubscribers=String.valueOf(Ifd.getYoutube().getSubscribers());
            hinstafollowers.setText(sIfollowers);
            hyoutubesubscribers.setText(sSubscribers);
//           // hysubscriberstxt.setText(cysubscriberstxt.get(position));
              hfbfollowers.setText(sFfollowers);
           // hffollowerstxt.setText(cffollowerstxt.get(position));
            Glide.with(context)
                                   .load(Ifd.getProfilePhoto_Link())
                    .into(holder.image);
          //  himage.setImageResource(arr1[position]);
          //  himage.setImageResource(arr2[position]);
          //  himage.setImageResource(arr3[position]);
            //button on click
            //card on click
        }

        @Override
        public int getItemCount() {
            return listData.size();

        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView iname;
            TextView ipricetext;
            TextView iprice;
            TextView ifollowerstxt;
            TextView instafollowers;
            TextView ysubscriberstxt;
            TextView youtubesubscribers;
            TextView ffollowerstxt;
            TextView fbfollowers;
            TextView icat;
            TextView icat1;
            TextView icat2;

            Button vp;
            Button rq;

            ImageView image;
            ImageView instaimg;
            ImageView ytimg;
            ImageView fbimg;

            CardView card;

            public MyViewHolder(View v) {
                super(v);
                iname=(TextView) v.findViewById(R.id.name);
               // ipricetext=(TextView) v.findViewById(R.id.pricetxt);
                iprice=(TextView) v.findViewById(R.id.price);
               // icat=(TextView) v.findViewById(R.id.categories);
                icat1=(TextView) v.findViewById(R.id.cat1);
                icat2=(TextView) v.findViewById(R.id.cat2);
             //   ifollowerstxt=(TextView) v.findViewById(R.id.ifollowers);
              // ysubscriberstxt=(TextView) v.findViewById(R.id.ysubscribers);
               // ffollowerstxt=(TextView) v.findViewById(R.id.ffollowers);
                instafollowers=(TextView) v.findViewById(R.id.insta);
                youtubesubscribers=(TextView) v.findViewById(R.id.youtube);
                fbfollowers=(TextView) v.findViewById(R.id.facebook);

                vp=(Button) v.findViewById(R.id.viewprofile);
                rq=(Button) v.findViewById(R.id.request);

                image=(ImageView)v.findViewById(R.id.image);
               // instaimg=(ImageView)v.findViewById(R.id.iimage);
               // ytimg=(ImageView)v.findViewById(R.id.yimage);
               // fbimg=(ImageView)v.findViewById(R.id.fimage);
                card=(CardView)v.findViewById(R.id.card);

            }
        }

    }

