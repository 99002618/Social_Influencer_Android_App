package com.example.socialinfluencer.Influencer;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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

import com.example.socialinfluencer.Advertiser.CustomNotificationAdapter;
import com.example.socialinfluencer.Advertiser.InfluencerDetails;
import com.example.socialinfluencer.Advertiser.influencerdetails1;
import com.example.socialinfluencer.Campaign.campaign1;
import com.example.socialinfluencer.DataModels.AdvertiserNotification;
import com.example.socialinfluencer.DataModels.AdvertiserProfile;
import com.example.socialinfluencer.DataModels.Campaign_Data_Model;
import com.example.socialinfluencer.DataModels.InfluencerNotification;
import com.example.socialinfluencer.DataModels.InfluencerProfileData;
import com.example.socialinfluencer.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.socialinfluencer.Influencer.ItemClass.card1;
import static com.example.socialinfluencer.Influencer.ItemClass.card;
import static com.example.socialinfluencer.Influencer.customAdapter.JSON;
import static com.example.socialinfluencer.Influencer.customAdapter.getCountOfDays;


public class NotificationcustomAdapter extends RecyclerView.Adapter {
    private List<ItemClass> itemClassList;
    int card1Position=0;
    int card2Position=0;
    InfluencerNotification adt;
    String aPrice;
    String aTime;
    String aname;
    String aCampaign;
    String aBrand;
    public NotificationcustomAdapter(List<ItemClass> itemClassList) {
        this.itemClassList = itemClassList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case card:
                View layoutOne
                        = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.influencernotificationcard1, parent,
                                false);
                return new cardViewHolder(layoutOne);
            case card1:
                View layoutTwo
                        = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.influencernotificationcard2, parent,
                                false);
                return new card1ViewHolder(layoutTwo);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {

        switch (itemClassList.get(position).getViewType()) {
            case card:
                adt= itemClassList.get(position).getNtf();
                DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Campaigns/"+adt.getCampaignID());
                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        adt= itemClassList.get(position).getNtf();
                        aname = adt.getNotificaionType();
                        aCampaign = adt.getCampaign_Name();
                        aBrand = adt.getAdvertiserName() +" has requests you to join "+ aCampaign;
                        Campaign_Data_Model cd=snapshot.getValue(Campaign_Data_Model.class);
                        aPrice = String.valueOf(cd.getBudget());
                        aTime = String.valueOf((int)getCountOfDays(cd.getStart_Date(), cd.getEnd_Date()) );
                        ((cardViewHolder)holder).setViews(aname,aBrand,aPrice,aTime);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                ((cardViewHolder)holder).reject.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        removeItem(itemClassList.get(position),itemClassList.get(position).getNotifi());
                        notifyDataSetChanged();
                    }
                });
                ((cardViewHolder)holder).card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AppCompatActivity activity = (AppCompatActivity) v.getContext();
                        Fragment InfluencerD=new campaign1();
                        Bundle bundle = new Bundle();
                        bundle.putString("CampaignID", adt.getCampaignID());
                        InfluencerD.setArguments(bundle);
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame,InfluencerD).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack("Campagin ID").commit();

                    }
                });
                ((cardViewHolder)holder).accept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FirebaseDatabase db = FirebaseDatabase.getInstance();
                        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Influencer/" + FirebaseAuth.getInstance().getUid());
                        ref.child("Campaigns").child(adt.getCampaignID()).child("Status").setValue("Accepted");

                        FirebaseDatabase.getInstance().getReference("Influencer/" + FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                final InfluencerProfileData data = snapshot.child("Profile").getValue(InfluencerProfileData.class);
                                final String Message = data.getInfluncer_Name() + " has accepted your application of " + adt.getCampaign_Name();

                                FirebaseDatabase.getInstance().getReference("Advertiser").child(adt.getFrom()).child("token").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        String token = snapshot.getValue(String.class);
                                        String Notification = FirebaseDatabase.getInstance().getReference("Advertiser").child(adt.getFrom()).push().getKey();
                                        FirebaseDatabase.getInstance().getReference("Advertiser").child(adt.getFrom()).child("Notification").child("Notification" + Notification).child("CampaignID").setValue(adt.getCampaignID());
                                        FirebaseDatabase.getInstance().getReference("Advertiser").child(adt.getFrom()).child("Notification").child("Notification" + Notification).child("InfluencerName").setValue(data.getInfluncer_Name());
                                        FirebaseDatabase.getInstance().getReference("Advertiser").child(adt.getFrom()).child("Notification").child("Notification" + Notification).child("NotificaionType").setValue("Campaign Acceptance");
                                        FirebaseDatabase.getInstance().getReference("Advertiser").child(adt.getFrom()).child("Notification").child("Notification" + Notification).child("Campaign_Name").setValue(adt.getCampaign_Name());
                                        FirebaseDatabase.getInstance().getReference("Advertiser").child(adt.getFrom()).child("Notification").child("Notification" + Notification).child("From").setValue(FirebaseAuth.getInstance().getUid());
                                        try {
                                            sendNotificationToPatner(token, "Campaign Acceptance", Message);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        removeItem(itemClassList.get(position),itemClassList.get(position).getNotifi());
                                        notifyDataSetChanged();
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    }

                });

                card1Position+=1;
                break;
            case card1:
                InfluencerNotification ntf= itemClassList.get(position).getNdf();
//                String bCampaign = itemClassList.get(position).getbCampaign().get(card2Position);
                String bInfluencer = ntf.getAdvertiserName()+" has accepted your application of "+ntf.getCampaign_Name();
                ((card1ViewHolder)holder).setViews(ntf.getNotificaionType(),bInfluencer);
//                card2Position+=1;
                ((card1ViewHolder)holder).img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        removeItem(itemClassList.get(position),itemClassList.get(position).getNotific());
                        notifyDataSetChanged();
                    }
                });
                break;
        }


    }

    private void removeItem(ItemClass itemClass,String nID) {
        int curpostiion=itemClassList.indexOf(itemClass);
        itemClassList.remove(curpostiion);
        DatabaseReference ref=FirebaseDatabase.getInstance().getReference("Influencer/"+FirebaseAuth.getInstance().getUid());
        ref.child("Notification").child(nID).removeValue();
    }


    public int getItemViewType(int position) {
        switch (itemClassList.get(position).getViewType()) {
            case 0:
                return card;
            case 1:
                return card1;
            default:
                return -1;
        }
    }

    @Override
    public int getItemCount() {
        return itemClassList.size();
    }

    class cardViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView brand;
        TextView campaign;
        TextView price;
        TextView time;
        Button accept;
        Button reject;
        CardView card;

        public cardViewHolder(@NonNull View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.notificationName);
            brand = (TextView) v.findViewById(R.id.brandName);
//            campaign = (TextView) v.findViewById(R.id.campaignName);
            price = (TextView) v.findViewById(R.id.price);
            time = (TextView) v.findViewById(R.id.time);
            accept = (Button) v.findViewById(R.id.accept);
            reject = (Button) v.findViewById(R.id.reject);
            card = (CardView) v.findViewById(R.id.card);
        }

        private void setViews(String cName, String cInfluencer, String cPrice, String cTime) {
            name.setText((CharSequence) cName);
            brand.setText((CharSequence) cInfluencer);
//            campaign.setText((CharSequence) cCampaign);
            price.setText((CharSequence) cPrice);
            time.setText((CharSequence) cTime);

        }

    }


    class card1ViewHolder extends RecyclerView.ViewHolder {
        TextView namE;
        TextView branD ;
        TextView campaigN;
        CardView card1;
        ImageView img;

        public card1ViewHolder(@NonNull View v) {
            super(v);
            namE = (TextView) v.findViewById(R.id.NotificationName);
            branD = (TextView) v.findViewById(R.id.Brand);
//            campaigN = (TextView) v.findViewById(R.id.CampaignName);
            card1 = (CardView) v.findViewById(R.id.card);
            img=(ImageView)v.findViewById(R.id.delete);
        }

        private void setViews(String dName, String dInfluencer) {
            namE.setText((CharSequence) dName);
            branD.setText((CharSequence) dInfluencer);
//            campaigN.setText((CharSequence) dCampaign);
        }
    }
    private void sendNotificationToPatner(String token,String title, String message) throws IOException, JSONException {

        OkHttpClient client = new OkHttpClient();
        JSONObject json = new JSONObject();
        JSONObject notifJson = new JSONObject();
        JSONObject dataJson = new JSONObject();
        notifJson.put("body", message);
        notifJson.put("title", title);
        notifJson.put("priority", "high");
        dataJson.put("customId", "02");
        dataJson.put("badge", 1);
        dataJson.put("alert", "Alert");
        json.put("notification", notifJson);
        json.put("data", notifJson);
        JSONArray array = new JSONArray();
        array.put(token);
        json.put("registration_ids", array);
        RequestBody body = RequestBody.create(JSON, json.toString());
        Request request = new Request.Builder()
                .header("Authorization", "key=AAAAwifLiqQ:APA91bF6UFj2VCsOI_wxbx1NwYGorywJTk-NS9Zb3VbcMFXkz-SrMt_47RGpkuZKqJ813OkdxRZQO7YPC3n2bL1hhJLXhWCeTBbkzTpiUMgm7lyIbnwW4Vbwl6ZXJnln2t9tEXU4yVoc")
                .url("https://fcm.googleapis.com/fcm/send")
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        String finalResponse = response.body().string();
        Log.i("kunwar", finalResponse);
    }
}


