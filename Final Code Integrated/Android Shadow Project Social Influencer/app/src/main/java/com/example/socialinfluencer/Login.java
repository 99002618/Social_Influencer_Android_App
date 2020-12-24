package com.example.socialinfluencer;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.socialinfluencer.Advertiser.HomeA;
import com.example.socialinfluencer.Influencer.HomeI;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Login#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Login extends Fragment {
    EditText username;
    EditText pass;
    ProgressBar progressBar;
    static FirebaseAuth fAuth;
    TextView registerpage;
    Context thisContext;
    public static GoogleSignInClient mGoogleSignInClient;
    Button google;
    private static final int RC_SIGN_IN = 9001;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Login() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Login.
     */
    // TODO: Rename and change types and number of parameters
    public static Login newInstance(String param1, String param2) {
        Login fragment = new Login();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = view.findViewById(R.id.progressBar1);
        username = (EditText) view.findViewById(R.id.uname);
        pass = (EditText) view.findViewById(R.id.pass);
        registerpage=(TextView)view.findViewById(R.id.register);
        google=view.findViewById(R.id.googleloginbtn);
        final Button login = (Button) view.findViewById(R.id.registerbtn);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
         mGoogleSignInClient = GoogleSignIn.getClient(getContext(), gso);



//        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        fAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUserAccount();
            }
        });

        registerpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new Register());
            }
        });

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onStart() {
        super.onStart();
        if(fAuth != null) {
            FirebaseUser currentUser = fAuth.getCurrentUser();
        }
//        Intent intent = new Intent(getContext(), Home.class);
//        startActivity(intent);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        thisContext=container.getContext();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
//                Toast.makeText(getContext(), "Login Successfull", Toast.LENGTH_SHORT).show();

//                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
//                Log.w(TAG, "Google sign in failed", e);
//                Toast.makeText(getContext(), "Login Failed: "+e, Toast.LENGTH_SHORT).show();
                // [START_EXCLUDE]
                //
                // [END_EXCLUDE]
            }
        }
    }
//    https://www.codeproject.com/articles/1113772/adding-google-login-to-android-app
    private void firebaseAuthWithGoogle(String idToken) {
        // [START_EXCLUDE silent]

        // [END_EXCLUDE]
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        fAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                final String userID = fAuth.getCurrentUser().getUid();
                                database.getReference().addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if(snapshot.child("Influencer").child(userID).exists())
                                        {

                                            if(getActivity()!=null) {
                                                Intent intent = new Intent(thisContext, HomeI.class);
                                                startActivity(intent);
                                                getActivity().finish();
                                            }
                                        }
                                        else if(snapshot.child("Advertiser").child(userID).exists())
                                        {
                                            if(getActivity()!=null) {
                                                Intent intent = new Intent(thisContext, HomeA.class);

                                                startActivity(intent);
                                                getActivity().finish();
                                            }
                                        }
                                        else
                                        {
                                            if(getActivity()!=null) {
                                                Intent intent = new Intent(thisContext, RegisterWithUS.class);
                                                startActivity(intent);
                                                getActivity().finish();
                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

//                           } getActivity().finish();

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(thisContext, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }

                        // [START_EXCLUDE]

                        // [END_EXCLUDE]
                    }
                });
    }

    private void loginUserAccount() {
        progressBar.setVisibility(View.VISIBLE);

        String email, password;
        email = username.getText().toString();
        password = pass.getText().toString();

        if (TextUtils.isEmpty(email)) {
            username.setError("Email is Required.");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            pass.setError("Password is Required.");
            return;
        }
        if (password.length() < 6) {
            pass.setError("Password Must be >= 6 Characters");
            return;
        }

//        fAuth.signInWithCredential(credential)
//        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>(){
//
//                }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        fAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getContext(), "Login successful!", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            final String userID = fAuth.getCurrentUser().getUid();
                            Log.d("TAGD: USERID",userID);
                             database.getReference("Advertiser").addListenerForSingleValueEvent(new ValueEventListener() {
                                 @Override
                                 public void onDataChange(@NonNull DataSnapshot snapshot) {
                                   if(snapshot.child(userID).exists())
                                   {
                                       Intent intent= new Intent(thisContext,HomeA.class);
                                       startActivity(intent);
                                       getActivity().finish();


                                   }
                                   else
                                   {
                                       Intent intent= new Intent(thisContext,HomeI.class);
                                       startActivity(intent);
                                       getActivity().finish();

                                   }
                                 }

                                 @Override
                                 public void onCancelled(@NonNull DatabaseError error) {

                                 }
                             });


                        } else {
                            Toast.makeText(getContext(), "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            // Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            // startActivity(intent);
                        }
                    }
                });


    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void loadFragment(Fragment fragment)
    {
        //create a fragment manager
        FragmentManager manager=getFragmentManager();
        //create the fragment transaction
        FragmentTransaction fd=manager.beginTransaction();
        fd.replace(R.id.frame,fragment);
        fd.commit();

    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
}