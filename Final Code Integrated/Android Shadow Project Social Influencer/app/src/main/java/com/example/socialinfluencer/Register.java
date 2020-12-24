package com.example.socialinfluencer;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Register#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Register extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    FirebaseAuth fAuth;
    TextView user;
    TextView password;
    Spinner userType;
    TextView cpassword;
    Button register;
    Context thisContext;
    ArrayAdapter<CharSequence> spinnerAdapter;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Register() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Register.
     */
    // TODO: Rename and change types and number of parameters
    public static Register newInstance(String param1, String param2) {
        Register fragment = new Register();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fAuth = FirebaseAuth.getInstance();
        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getContext(),MainActivity.class));
        }
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        user =view.findViewById(R.id.uname);
        password=view.findViewById(R.id.pass);
        cpassword=view.findViewById(R.id.cpass);
        userType= view.findViewById(R.id.userType);
        register=view.findViewById(R.id.registerbtn);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

        view.findViewById(R.id.loadLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new Login());
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        thisContext=container.getContext();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }
    private void loadFragment(Fragment fragment)
    {
        //create a fragment manager
        FragmentManager manager=getFragmentManager();
        //create the fragment transaction
        FragmentTransaction fd=manager.beginTransaction();
        fd.replace(R.id.frame,fragment);
        fd.commit();

    }

    private void register() {



       final String muser = user.getText().toString();
        final String mpassword= password.getText().toString();
        final String mcpassword= cpassword.getText().toString();

        if (TextUtils.isEmpty(muser)) {
            user.setError("Email is Required.");
            return;
        }
        if (TextUtils.isEmpty(mpassword)) {
            password.setError("Password is Required.");
            return;
        }
        if(mpassword.length() < 6) {
            password.setError("Password Must be >= 6 Characters");
            return;
        }

        fAuth.createUserWithEmailAndPassword(muser, mpassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            String user = userType.getSelectedItem().toString();
                            Toast.makeText(thisContext, "User Created.", Toast.LENGTH_LONG).show();
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            String userID = fAuth.getCurrentUser().getUid();
                            DatabaseReference myRef = database.getReference(user);
                            myRef.child(userID).child("username").setValue(muser);
                            myRef.child(userID).child("password").setValue(mpassword);
                            myRef.child(userID).child("token").setValue(FirebaseInstanceId.getInstance().getToken());
                            loadFragment(new Login());
                        }
                        else {
                            Toast.makeText(thisContext, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });



    }
}

