package com.example.mac.xinlei1_sizebook;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mac on 2017/1/23.
 */

public class PersonNameAdapter extends RecyclerView.Adapter<PersonNameAdapter.ViewHolder> {

    // we need a list of person names
    private List<String> mPersonNameList;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class ViewHolder extends RecyclerView.ViewHolder {
        // each person display a name in MainActivity
        TextView personNamev;

        ViewHolder(View view) {
            super(view);
            personNamev = (TextView) view.findViewById(R.id.person_name);
        }
    }

    public PersonNameAdapter(List<String> PersonNameList) {
        mPersonNameList = PersonNameList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.person_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d("PersonNameJava", Integer.toString(position));
        String PersonName = mPersonNameList.get(position);
        holder.personNamev.setText(PersonName);
    }

    // Return the size of your List
    @Override
    public int getItemCount() {
        return mPersonNameList.size();
    }
}

