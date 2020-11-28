package com.example.homeinfluencer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class customAdapter extends RecyclerView.Adapter<customAdapter.MyViewHolder>{

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
    public customAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
        MyViewHolder vh= new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull customAdapter.MyViewHolder holder, int position) {
        TextView htitle=holder.title;
        TextView hdescription=holder.description;
        TextView hcat=holder.cat;
        TextView hdate=holder.date;
        Button happly=holder.apply;
        Button hknwMore=holder.knwMore;
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

        public MyViewHolder(View v) {
            super(v);
            title=(TextView) v.findViewById(R.id.title);
            description=(TextView) v.findViewById(R.id.descrpt);
            cat=(TextView) v.findViewById(R.id.cat);
            date=(TextView) v.findViewById(R.id.date);
            apply=(Button) v.findViewById(R.id.apply);
            knwMore=(Button) v.findViewById(R.id.knw);
            image=(ImageView)v.findViewById(R.id.image);
        }
    }
}
