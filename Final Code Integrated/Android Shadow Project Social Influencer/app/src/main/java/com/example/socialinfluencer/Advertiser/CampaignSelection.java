package com.example.socialinfluencer.Advertiser;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.socialinfluencer.DataModels.AdvertiserProfile;
import com.example.socialinfluencer.DataModels.Campaign_Data_Model;
import com.example.socialinfluencer.DataModels.InfluencerCampaigns;
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
import java.util.Arrays;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.socialinfluencer.Influencer.customAdapter.JSON;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CampaignSelection#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CampaignSelection extends Fragment {
    List<String> ICampaignID;
    ArrayList<String> ICampaignString;
    List<InfluencerCampaigns> InfluencerC;
    List<Campaign_Data_Model> Camapign;
    ArrayList<String> camp;
    String campaign;
    Spinner spin;
    AdvertiserProfile adt;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CampaignSelection() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CampaignSelection.
     */
    // TODO: Rename and change types and number of parameters
    public static CampaignSelection newInstance(String param1, String param2) {
        CampaignSelection fragment = new CampaignSelection();
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
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);String a[] = new String[]{"abc","klm","xyz","pqr"};
        spin=view.findViewById(R.id.campaigns);
        Button request=view.findViewById(R.id.Request);
        Bundle bundle = this.getArguments();
        final String InfluencerID = bundle.getString("InfluencerID", "No-Influencer");
        camp= new ArrayList<String>();
        ICampaignID=new ArrayList<>();
        ICampaignString=new ArrayList<>(Arrays.asList("Click to select Campaign"));
        final String cam[] = new String[]{};
        InfluencerC=new ArrayList<>();
        Camapign=new ArrayList<>();
        List<String> iname= new ArrayList<>(Arrays.asList("Rosy","Ram","John","Johnny","Jimmy","Clay","Bob","Ankitha","Girish","Sudarshana","Chethan","Thrinath"));
        final FirebaseAuth fAuth= FirebaseAuth.getInstance();
        String Brandname;
        DatabaseReference Cref= FirebaseDatabase.getInstance().getReference("Advertiser/"+fAuth.getCurrentUser().getUid()+"/Campaigns");
        Cref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    for(DataSnapshot datas: snapshot.getChildren()) {

                        ICampaignID.add(datas.getKey());
                        InfluencerCampaigns Camp = datas.getValue(InfluencerCampaigns.class);
                        InfluencerC.add(Camp);
                        camp.add(datas.getKey());

                    }
                    for(int i=0;i<ICampaignID.size();i++)
                    {   final String id=ICampaignID.get(i);
                        DatabaseReference ref=FirebaseDatabase.getInstance().getReference("Campaigns/"+ICampaignID.get(i));
                        ref.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                Campaign_Data_Model scd= snapshot.getValue(Campaign_Data_Model.class);
                                Camapign.add(scd);
                                ICampaignString.add(id+":"+scd.getCampaign_Name());
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }

                        });
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, ICampaignString);
// Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// attaching data adapter to spinner
        spin.setAdapter(dataAdapter);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                Object item = adapterView.getItemAtPosition(position);
                if (item != null) {
//                    Toast.makeText(getContext(), item.toString(),
//                            Toast.LENGTH_SHORT).show();
               }
//                Toast.makeText(getContext(), "Selected",
//                        Toast.LENGTH_SHORT).show();
                    campaign=item.toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // TODO Auto-generated method stub

            }
        });
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                                    Toast.makeText(getContext(), campaign,
                            Toast.LENGTH_SHORT).show();
                                    if(campaign.indexOf("Click to select Campaign")==-1) {
                                        final String campaignD[] = campaign.split(":");
//                                        Toast.makeText(getContext(), campaignD[1],
//                                                Toast.LENGTH_SHORT).show();

                                        final FirebaseAuth fauth=FirebaseAuth.getInstance();
                                        DatabaseReference Cref= FirebaseDatabase.getInstance().getReference("Advertiser/"+fauth.getCurrentUser().getUid()+"/Profile");
                                        Cref.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                adt=snapshot.getValue(AdvertiserProfile.class);
                                                DatabaseReference iref=FirebaseDatabase.getInstance().getReference("Influencer/"+InfluencerID+"/Notification");
                                                String key=iref.push().getKey();
                                                FirebaseDatabase.getInstance().getReference("Influencer").child(InfluencerID).child("Notification").child("Notification" + key).child("CampaignID").setValue(campaignD[0]);
                                                FirebaseDatabase.getInstance().getReference("Influencer").child(InfluencerID).child("Notification").child("Notification" + key).child("AdvertiserName").setValue(adt.getBrandName());
                                                FirebaseDatabase.getInstance().getReference("Influencer").child(InfluencerID).child("Notification").child("Notification" + key).child("NotificaionType").setValue("Campaign Invitation");
                                                FirebaseDatabase.getInstance().getReference("Influencer").child(InfluencerID).child("Notification").child("Notification" + key).child("Campaign_Name").setValue(campaignD[1]);
                                                FirebaseDatabase.getInstance().getReference("Influencer").child(InfluencerID).child("Notification").child("Notification" + key).child("From").setValue(FirebaseAuth.getInstance().getUid());
                                                FirebaseDatabase.getInstance().getReference("Influencer/"+InfluencerID).addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                        String token=snapshot.child("token").getValue(String.class);
                                                        try {
                                                            sendNotificationToPatner(token, "Campaign Invitation", adt.getBrandName()+" has requested you to join "+campaignD[1]);
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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_campaign_selection, container, false);
    }


}