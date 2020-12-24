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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.socialinfluencer.DataModels.AdvertiserProfile;
import com.example.socialinfluencer.DataModels.Campaign_Data_Model;
import com.example.socialinfluencer.Campaign.campaign;
import com.example.socialinfluencer.DataModels.InfluencerProfileData;
import com.example.socialinfluencer.R;
import com.example.socialinfluencer.SendNotification.APIService;
import com.example.socialinfluencer.SendNotification.Client;
import com.example.socialinfluencer.SendNotification.Data;
import com.example.socialinfluencer.SendNotification.MyResponse;
import com.example.socialinfluencer.SendNotification.NotificationSender;
import com.example.socialinfluencer.SendNotification.RequestNotificaton;
import com.example.socialinfluencer.SendNotification.SendNotificationModel;
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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

import java.util.Locale;

public class customAdapter extends RecyclerView.Adapter<customAdapter.MyViewHolder>{
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    ArrayList<String> cTitle;
    ArrayList<String> cDescription;
    ArrayList<String> cCategory;
    ArrayList<String> cDate;
    Integer[] arr;
    Context context;
    List<String> CampaignID;
    FirebaseAuth fauth;
    private  APIService apiService = Client.getClient().create(APIService.class); ;
    private List<Campaign_Data_Model> details;

    public customAdapter(List<Campaign_Data_Model> details, List<String> CampaignID, Context ctx) {
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
        final Campaign_Data_Model cd = details.get(position);
        TextView htitle = holder.title;
        TextView hdescription = holder.description;
        TextView hcat = holder.cat;
        final TextView hdate = holder.date;
        final Button happly = holder.apply;
        Button hknwMore = holder.knwMore;
        fauth=FirebaseAuth.getInstance();

        ImageView himage = holder.image;
        CardView cam_card = holder.card;
        cam_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment campaign = new campaign();
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
                Fragment campaign = new campaign();
                Bundle bundle = new Bundle();
                bundle.putString("CampaignID", CampaignID.get(position));
                campaign.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame, campaign).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(campaign.toString()).commit();
            }
        });

        htitle.setText(cd.getCampaign_Name());
        hdescription.setText(cd.getDescripton());
        List<String> Cat = cd.getCategories();
        hcat.setText(Cat.get(0));
        if (getCountOfDays(cd.getStart_Date(), cd.getEnd_Date()) > 0) {
            FirebaseDatabase.getInstance().getReference("Influencer/"+fauth.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(!snapshot.child("Campaigns").child(CampaignID.get(position)).exists())
                    {
                        hdate.setText("Apply by " + cd.getEnd_Date());
                        happly.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(context,"Applied Successfully",Toast.LENGTH_SHORT).show();
                                FirebaseDatabase db=FirebaseDatabase.getInstance();
                                final DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Influencer/"+fauth.getUid());
                                ref.child("Campaigns").child(CampaignID.get(position)).child("Status").setValue("Applied");

                                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        final InfluencerProfileData data= snapshot.child("Profile").getValue(InfluencerProfileData.class);
                                        final String Message= data.getInfluncer_Name()+" has applied for the  Campaign "+ cd.getCampaign_Name();

                                        FirebaseDatabase.getInstance().getReference("Advertiser").child(cd.getAdvertiser_ID()).child("token").addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                String token=snapshot.getValue(String.class);
                                                String Notification=FirebaseDatabase.getInstance().getReference("Advertiser").child(cd.getAdvertiser_ID()).push().getKey();
                                                FirebaseDatabase.getInstance().getReference("Advertiser").child(cd.getAdvertiser_ID()).child("Notification").child("Notification"+Notification).child("CampaignID").setValue(CampaignID.get(position));
                                                FirebaseDatabase.getInstance().getReference("Advertiser").child(cd.getAdvertiser_ID()).child("Notification").child("Notification"+Notification).child("InfluencerName").setValue(data.getInfluncer_Name());
                                                FirebaseDatabase.getInstance().getReference("Advertiser").child(cd.getAdvertiser_ID()).child("Notification").child("Notification"+Notification).child("NotificaionType").setValue("Campaign Application");
                                                FirebaseDatabase.getInstance().getReference("Advertiser").child(cd.getAdvertiser_ID()).child("Notification").child("Notification"+Notification).child("Campaign_Name").setValue(cd.getCampaign_Name());
//                                   FirebaseDatabase.getInstance().getReference("Advertiser").child(cd.getAdvertiser_ID()).child("Notification").child("Notification"+Notification).child("From").setValue(cd.getAdvertiser_ID());
                                                FirebaseDatabase.getInstance().getReference("Advertiser").child(cd.getAdvertiser_ID()).child("Notification").child("Notification"+Notification).child("From").setValue(FirebaseAuth.getInstance().getUid());
                                                try {
                                                    sendNotificationToPatner(token,"Campaign Application",Message);
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
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
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        } else if (getCountOfDays(cd.getStart_Date(), cd.getEnd_Date()) == 0)
        {
            hdate.setText("Campaign Ends Today " + cd.getEnd_Date());
            happly.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(context,"Applied Successfully",Toast.LENGTH_SHORT).show();
                    FirebaseDatabase db=FirebaseDatabase.getInstance();
                    final DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Influencer/"+fauth.getUid());
                    ref.child("Campaigns").child(CampaignID.get(position)).child("Status").setValue("Applied");

                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            final InfluencerProfileData data= snapshot.child("Profile").getValue(InfluencerProfileData.class);
                            final String Message= data.getInfluncer_Name()+" has applied for the  Campaign "+ cd.getCampaign_Name();

                            FirebaseDatabase.getInstance().getReference("Advertiser").child(cd.getAdvertiser_ID()).child("token").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    String token=snapshot.getValue(String.class);
                                    String Notification=FirebaseDatabase.getInstance().getReference("Advertiser").child(cd.getAdvertiser_ID()).push().getKey();
                                    FirebaseDatabase.getInstance().getReference("Advertiser").child(cd.getAdvertiser_ID()).child("Notification").child("Notification"+Notification).child("CampaignID").setValue(CampaignID.get(position));
                                    FirebaseDatabase.getInstance().getReference("Advertiser").child(cd.getAdvertiser_ID()).child("Notification").child("Notification"+Notification).child("InfluencerName").setValue(data.getInfluncer_Name());
                                    FirebaseDatabase.getInstance().getReference("Advertiser").child(cd.getAdvertiser_ID()).child("Notification").child("Notification"+Notification).child("NotificaionType").setValue("Campaign Application");
                                    FirebaseDatabase.getInstance().getReference("Advertiser").child(cd.getAdvertiser_ID()).child("Notification").child("Notification"+Notification).child("Campaign_Name").setValue(cd.getCampaign_Name());
                                    FirebaseDatabase.getInstance().getReference("Advertiser").child(cd.getAdvertiser_ID()).child("Notification").child("Notification"+Notification).child("From").setValue(FirebaseAuth.getInstance().getUid());
                                    try {
                                        sendNotificationToPatner(token,"Campaign Application",Message);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
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

        }
        else
        {
            hdate.setText("Campaign Ended on " + cd.getEnd_Date());
            happly.setEnabled(false);
        }
//        hdate.setText("Apply by "+cd.getEnd_Date());
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

//    public void sendNotifications(String usertoken, String title, String message) {
//        Data data = new Data(title, message);
//        NotificationSender sender = new NotificationSender("ebLeFCVmTfKyDqZHXnBhmS:APA91bFVi2F_eXGPYDtdepk9Ossdzv8i5FCZhaiac2bcOTtw3HVJ0TvTewbfIGerKv9Ib0Xbxxxa3Cmup9PMidzYXNJUtoenxgkfafuGD4vgM_Hn89dGP3-Qz4950FADsZsgk4D2M-Qx",data);
////        Toast.makeText(context, "Entered", Toast.LENGTH_LONG).show();
//        apiService.sendNotifcation(sender).enqueue(new Callback<MyResponse>() {
//            @Override
//            public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
//                Toast.makeText(context, "Response Recived", Toast.LENGTH_LONG).show();
//                if (response.code() == 200) {
//                    if (response.body().success != 1) {
//                        Toast.makeText(context, "Failed"+response.body().success, Toast.LENGTH_LONG).show();
//                    }
//                    else
//                    {
//                        Toast.makeText(context, "Sucess", Toast.LENGTH_LONG).show();
//                    }
//                }
//                else
//                {
//                    Toast.makeText(context, "Completely Failed" +response.code(), Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MyResponse> call, Throwable t) {
//                    Toast.makeText(context, "Failed "+t, Toast.LENGTH_LONG).show();
//
//            }
//        });
//    }

    public static float getCountOfDays(String createdDateString, String expireDateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());

        Date createdConvertedDate = null, expireCovertedDate = null, todayWithZeroTime = null;
        try {
            createdConvertedDate = dateFormat.parse(createdDateString);
            expireCovertedDate = dateFormat.parse(expireDateString);

            Date today = new Date();

            todayWithZeroTime = dateFormat.parse(dateFormat.format(today));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int cYear = 0, cMonth = 0, cDay = 0;

        if (createdConvertedDate.after(todayWithZeroTime)) {
            Calendar cCal = Calendar.getInstance();
            cCal.setTime(createdConvertedDate);
            cYear = cCal.get(Calendar.YEAR);
            cMonth = cCal.get(Calendar.MONTH);
            cDay = cCal.get(Calendar.DAY_OF_MONTH);

        } else {
            Calendar cCal = Calendar.getInstance();
            cCal.setTime(todayWithZeroTime);
            cYear = cCal.get(Calendar.YEAR);
            cMonth = cCal.get(Calendar.MONTH);
            cDay = cCal.get(Calendar.DAY_OF_MONTH);
        }


    /*Calendar todayCal = Calendar.getInstance();
    int todayYear = todayCal.get(Calendar.YEAR);
    int today = todayCal.get(Calendar.MONTH);
    int todayDay = todayCal.get(Calendar.DAY_OF_MONTH);
    */

        Calendar eCal = Calendar.getInstance();
        eCal.setTime(expireCovertedDate);

        int eYear = eCal.get(Calendar.YEAR);
        int eMonth = eCal.get(Calendar.MONTH);
        int eDay = eCal.get(Calendar.DAY_OF_MONTH);

        Calendar date1 = Calendar.getInstance();
        Calendar date2 = Calendar.getInstance();

        date1.clear();
        date1.set(cYear, cMonth, cDay);
        date2.clear();
        date2.set(eYear, eMonth, eDay);

        long diff = date2.getTimeInMillis() - date1.getTimeInMillis();

        float dayCount = (float) diff / (24 * 60 * 60 * 1000);
        return dayCount;
    }
}
