package com.example.socialinfluencer.Campaign;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.socialinfluencer.DataModels.Campaign_Data_Model;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.socialinfluencer.Influencer.customAdapter.JSON;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link campaign#newInstance} factory method to
 * create an instance of this fragment.
 */
public class campaign extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Campaign_Data_Model scd;
    public campaign() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment campaign.
     */
    // TODO: Rename and change types and number of parameters
    public static campaign newInstance(String param1, String param2) {
        campaign fragment = new campaign();
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
        final String CamID = bundle.getString("CampaignID", "No-Campaign");
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Campaigns").child(CamID);
        final TextView CampName = view.findViewById(R.id.Camptitle);
        final TextView CampDes=view.findViewById(R.id.CampDescription);
        final TextView CampBudget=view.findViewById(R.id.Campbudget);
        final TextView CampAdvertiser=view.findViewById(R.id.CampAdvertiser);
        final TextView CampCat=view.findViewById(R.id.CampCategory);
        final TextView CampStartDate=view.findViewById(R.id.CampStartDate);
        final TextView CampEndDate=view.findViewById(R.id.CampEndDate);
        final TextView CampDaysRemaining=view.findViewById(R.id.DaysLeft);
        final ImageView img=view.findViewById(R.id.CampImg);
         final Button btn=view.findViewById(R.id.CampApply);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                scd=snapshot.getValue(Campaign_Data_Model.class);
                CampName.setText(scd.getCampaign_Name());
                CampDes.setText(scd.getDescripton());
                CampAdvertiser.setText(scd.getAdvertiser_Name());
                String budget_C=String.valueOf(scd.getBudget());
                CampBudget.setText(budget_C);
                CampDaysRemaining.setText(String.valueOf((int)getCountOfDays(scd.getStart_Date(),scd.getEnd_Date())));
                CampEndDate.setText(scd.getEnd_Date());
                CampStartDate.setText(scd.getStart_Date());
                List<String> Cat=scd.getCategories();
                CampCat.setText(Cat.get(0));
                Glide.with(getContext())
                        .load(scd.getCampaign_image())
                        .into(img);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getCountOfDays(scd.getStart_Date(),scd.getEnd_Date())>=0)
                {
                Toast.makeText(getContext(), "Applied Successfully", Toast.LENGTH_SHORT).show();
                FirebaseDatabase db = FirebaseDatabase.getInstance();
                final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Influencer/" + FirebaseAuth.getInstance().getUid());
                ref.child("Campaigns").child(CamID).child("Status").setValue("Applied");
                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        final InfluencerProfileData data = snapshot.child("Profile").getValue(InfluencerProfileData.class);
                        final String Message = data.getInfluncer_Name() + " has applied for the  Campaign " + scd.getCampaign_Name();

                        FirebaseDatabase.getInstance().getReference("Advertiser").child(scd.getAdvertiser_ID()).child("token").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String token = snapshot.getValue(String.class);
                                String Notification = FirebaseDatabase.getInstance().getReference("Advertiser").child(scd.getAdvertiser_ID()).push().getKey();
                                FirebaseDatabase.getInstance().getReference("Advertiser").child(scd.getAdvertiser_ID()).child("Notification").child("Notification" + Notification).child("CampaignID").setValue(CamID);
                                FirebaseDatabase.getInstance().getReference("Advertiser").child(scd.getAdvertiser_ID()).child("Notification").child("Notification" + Notification).child("InfluencerName").setValue(data.getInfluncer_Name());
                                FirebaseDatabase.getInstance().getReference("Advertiser").child(scd.getAdvertiser_ID()).child("Notification").child("Notification" + Notification).child("NotificaionType").setValue("Campaign Application");
                                FirebaseDatabase.getInstance().getReference("Advertiser").child(scd.getAdvertiser_ID()).child("Notification").child("Notification" + Notification).child("Campaign_Name").setValue(scd.getCampaign_Name());
                                FirebaseDatabase.getInstance().getReference("Advertiser").child(scd.getAdvertiser_ID()).child("Notification").child("Notification" + Notification).child("From").setValue(scd.getAdvertiser_ID());
                                try {
                                    sendNotificationToPatner(token, "Campaign Application", Message);
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
            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_camapign, container, false);
    }
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