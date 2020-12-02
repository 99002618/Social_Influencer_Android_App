package com.example.profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class customAdapter extends RecyclerView.Adapter<com.example.profile.customAdapter.MyViewHolder>{

    ArrayList<String> cTitle;
    ArrayList<String> cDescription;
    ArrayList<String> cCategory;
    ArrayList<String> cDate;
    Integer[] arr;
    Context context;

    public customAdapter(ArrayList<String> cTitle, ArrayList<String> cDescription, ArrayList<String> cCategory, ArrayList<String> cDate, Integer[] arr, Context context) {
        this.cTitle = cTitle;
        this.cDescription = cDescription;
        this.cCategory = cCategory;
        this.cDate = cDate;
        this.arr = arr;
        this.context = context;
    }

    @NonNull
    @Override
    public com.example.profile.customAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
        MyViewHolder vh= new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.profile.customAdapter.MyViewHolder holder, int position) {
        TextView htitle=holder.title;
        TextView hdescription=holder.description;
        TextView hcat=holder.cat;
        TextView hdate=holder.date;
        CardView card_D=holder.card;
        card_D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Hello hi",Toast.LENGTH_LONG).show();
            }
        });
        Button hknwMore=holder.knwMore;
        hknwMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Hello hi",Toast.LENGTH_LONG).show();
            }
        });
        ImageView himage=holder.image;
        htitle.setText(cTitle.get(position));
        hdescription.setText(cDescription.get(position));
        hcat.setText(cCategory.get(position));
        hdate.setText("Apply by "+cDate.get(position));
        himage.setImageResource(arr[position]);
       //button on click
        //card on click
    }

    @Override
    public int getItemCount() {
        return cTitle.size();
        
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
            card=(CardView) v.findViewById(R.id.card);
            knwMore=(Button) v.findViewById(R.id.knw);
            image=(ImageView)v.findViewById(R.id.image);
        }
    }
}
