package com.example.socialinfluencer.Advertiser;

//public class customAdapter {
//}

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
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.socialinfluencer.Campaign.campaign1;
import com.example.socialinfluencer.DataModels.AdvertiserNotification;
import com.example.socialinfluencer.DataModels.AdvertiserProfile;
import com.example.socialinfluencer.DataModels.Campaign_Data_Model;
import com.example.socialinfluencer.DataModels.InfluencerProfileData;
import com.example.socialinfluencer.Influencer.NotificationcustomAdapter;
import com.example.socialinfluencer.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//import static com.example.notificationadvertiser.ItemClass.card;
//import static com.example.notificationadvertiser.ItemClass.card1;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.socialinfluencer.Advertiser.ItemClass.card1;
import static com.example.socialinfluencer.Advertiser.ItemClass.card;
import static com.example.socialinfluencer.Influencer.customAdapter.JSON;
import static com.example.socialinfluencer.Influencer.customAdapter.getCountOfDays;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CustomNotificationAdapter extends RecyclerView.Adapter {
    private List<ItemClass> itemClassList;
    int card1Position=0;
    int card2Position=0;
    AdvertiserNotification adt;
    String aPrice;
    String aTime;
    String aname;
    String aCampaign;
    String aInfluencer;

    public CustomNotificationAdapter(List<ItemClass> itemClassList) {
        this.itemClassList = itemClassList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case card:
                View layoutOne
                        = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.advertisernotificationcard1, parent,
                                false);
                return new cardViewHolder(layoutOne);
            case card1:
                View layoutTwo
                        = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.advertisernotificationcard2, parent,
                                false);
                return new card1ViewHolder(layoutTwo);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        switch (itemClassList.get(position).getViewType()) {
            case ItemClass.card:
                adt= itemClassList.get(position).getNtf();
                final int pos=card1Position;
                DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Campaigns/"+adt.getCampaignID());
                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        adt= itemClassList.get(position).getNtf();
                        aname = adt.getNotificaionType();
                        aCampaign = adt.getCampaign_Name();
                        aInfluencer = adt.getInfluencerName() +" has applied to the Campaign "+ aCampaign;
                        Campaign_Data_Model cd=snapshot.getValue(Campaign_Data_Model.class);
                        aPrice = String.valueOf(cd.getBudget());
                        aTime = String.valueOf((int)getCountOfDays(cd.getStart_Date(), cd.getEnd_Date()) );
                        ((cardViewHolder) holder).setViews(aname, aInfluencer, aCampaign, aPrice, aTime);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                ((cardViewHolder)holder).reject.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        removeItem(itemClassList.get(position),itemClassList.get(position).getNotificationID());
                        notifyDataSetChanged();

                    }
                });
                ((cardViewHolder)holder).card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AppCompatActivity activity = (AppCompatActivity) v.getContext();
                        Fragment CampaignD=new influencerdetails1();
                        Bundle bundle = new Bundle();
                        bundle.putString("InfluencerID", adt.getFrom());
                        CampaignD.setArguments(bundle);
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame,CampaignD).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack("Campagin ID").commit();

                    }
                });
                ((cardViewHolder)holder).accept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FirebaseDatabase db = FirebaseDatabase.getInstance();
                        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Influencer/" + adt.getFrom());
                        ref.child("Campaigns").child(adt.getCampaignID()).child("Status").setValue("Accepted");

                        FirebaseDatabase.getInstance().getReference("Advertiser/" + FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                final AdvertiserProfile data = snapshot.child("Profile").getValue(AdvertiserProfile.class);
                                final String Message = data.getBrandName() + " has accepted your application of " + adt.getCampaign_Name();

                                FirebaseDatabase.getInstance().getReference("Influencer").child(adt.getFrom()).child("token").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        String token = snapshot.getValue(String.class);
                                        String Notification = FirebaseDatabase.getInstance().getReference("Influencer").child(adt.getFrom()).push().getKey();
                                        FirebaseDatabase.getInstance().getReference("Influencer").child(adt.getFrom()).child("Notification").child("Notification" + Notification).child("CampaignID").setValue(adt.getCampaignID());
                                        FirebaseDatabase.getInstance().getReference("Influencer").child(adt.getFrom()).child("Notification").child("Notification" + Notification).child("AdvertiserName").setValue(data.getBrandName());
                                        FirebaseDatabase.getInstance().getReference("Influencer").child(adt.getFrom()).child("Notification").child("Notification" + Notification).child("NotificaionType").setValue("Campaign Acceptance");
                                        FirebaseDatabase.getInstance().getReference("Influencer").child(adt.getFrom()).child("Notification").child("Notification" + Notification).child("Campaign_Name").setValue(adt.getCampaign_Name());
                                        FirebaseDatabase.getInstance().getReference("Influencer").child(adt.getFrom()).child("Notification").child("Notification" + Notification).child("From").setValue(FirebaseAuth.getInstance().getUid());
                                        try {
                                            sendNotificationToPatner(token, "Campaign Acceptance", Message);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        removeItem(itemClassList.get(position),itemClassList.get(position).getNotificationID());
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
                AdvertiserNotification adt= itemClassList.get(position).getAdt();
               String bCampaign = adt.getCampaign_Name();
               String bInfluencer = adt.getInfluencerName();
                ((card1ViewHolder)holder).setViews(adt.getNotificaionType(),bInfluencer+" has accepted your application of "+bCampaign);
                ((card1ViewHolder)holder).img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    removeItem(itemClassList.get(position),itemClassList.get(position).getNoti());
                    notifyDataSetChanged();
                    }
                });
                card2Position+=1;

                break;
        }
    }
    private void removeItem(ItemClass itemClass,String nID) {

        int curpostiion=itemClassList.indexOf(itemClass);
        itemClassList.remove(curpostiion);
        DatabaseReference ref=FirebaseDatabase.getInstance().getReference("Advertiser/"+FirebaseAuth.getInstance().getUid());
        ref.child("Notification").child(nID).removeValue();
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

    class cardViewHolder extends ViewHolder {
        TextView name;
        TextView influencer;
        TextView campaign;
        TextView price;
        TextView time;
        Button accept;
        Button reject;
        CardView card;

        public cardViewHolder(@NonNull View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.NotificationName);
            influencer = (TextView) v.findViewById(R.id.Influencer);
//            campaign = (TextView) v.findViewById(R.id.CampaignName);
            price = (TextView) v.findViewById(R.id.aPrice);
            time = (TextView) v.findViewById(R.id.aTime);
            accept = (Button) v.findViewById(R.id.aAccept);
            reject = (Button) v.findViewById(R.id.aReject);
            card = (CardView) v.findViewById(R.id.card);
        }

        private void setViews(String cName, String cInfluencer, String cCampaign, String cPrice, String cTime) {
            name.setText((CharSequence) cName);
            influencer.setText((CharSequence) cInfluencer);
//            campaign.setText((CharSequence) cCampaign);
            price.setText((CharSequence) cPrice);
            time.setText((CharSequence) cTime);

        }
    }


    class card1ViewHolder extends ViewHolder {
        TextView namE;
        TextView influenceR;
        TextView campaigN;
        CardView card1;
        ImageView img;

        public card1ViewHolder(@NonNull View v) {
            super(v);
            namE = (TextView) v.findViewById(R.id.NotificationName);
            influenceR = (TextView) v.findViewById(R.id.Influencer);
//            campaigN = (TextView) v.findViewById(R.id.CampaignName);
            card1 = (CardView) v.findViewById(R.id.card);
            img=(ImageView)v.findViewById(R.id.delete);
        }

        private void setViews(String dName, String dInfluencer) {
            namE.setText((CharSequence) dName);
            influenceR.setText((CharSequence) dInfluencer);
//            campaigN.setText((CharSequence) dCampaign);
        }
    }



}

