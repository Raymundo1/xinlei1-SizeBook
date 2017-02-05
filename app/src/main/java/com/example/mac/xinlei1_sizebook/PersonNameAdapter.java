package com.example.mac.xinlei1_sizebook;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac on 2017/1/23.
 * Resource: http://developer.android.com/training/material/lists-cards.html
 */



public class PersonNameAdapter extends RecyclerView.Adapter<PersonNameAdapter.ViewHolder> {

    // we need a list of person names
    private ArrayList<String> mPersonNameList = new ArrayList<>();

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class ViewHolder extends RecyclerView.ViewHolder {
        // each person display a name and a icon in MainActivity
        TextView personNamev;
        ImageView personIconv;

        ViewHolder(View view) {
            super(view);
            personNamev = (TextView) view.findViewById(R.id.person_name);
            personIconv = (ImageView) view.findViewById(R.id.people_icon);
        }
    }

    // Provide a suitable constructor
    public PersonNameAdapter(ArrayList<String> PersonNameList) {
        mPersonNameList = PersonNameList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.person_item, parent, false);

        final ViewHolder vh = new ViewHolder(v);
        vh.personNamev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = vh.getAdapterPosition();

                //String name = mPersonNameList.get(position);

                Intent intent = new Intent(v.getContext(), ViewEdit_activity.class);
                intent.putExtra("selected_id", position);
                Log.d("Person_selected_pos", Integer.toString(position));
                v.getContext().startActivity(intent);
            }
        });
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String PersonName = mPersonNameList.get(position);
        holder.personNamev.setText(PersonName);
        holder.personIconv.setImageResource(R.drawable.people);
    }

    // Return the size of your List
    @Override
    public int getItemCount() {
        return mPersonNameList.size();
    }
}

