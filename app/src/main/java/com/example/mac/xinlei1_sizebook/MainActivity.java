package com.example.mac.xinlei1_sizebook;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ActivityConstants{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<String> personNameList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getIntent().getExtras() != null) {
            int callingActivity = getIntent().getIntExtra("calling-activity", 0);
            switch (callingActivity) {
                case ActivityConstants.ADD_ACTIVITY:
                    person new_person = (person) getIntent().getSerializableExtra("new_person");
                    Log.d("new_person", new_person.toString());

                    break;

                case ActivityConstants.VIEW_ACTIVITY:
                    break;
            }
        }

        initPersonName();

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter
        mAdapter = new PersonNameAdapter(personNameList);
        mRecyclerView.setAdapter(mAdapter);

        // add a decoration of list
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, null));
        //mRecyclerView.addItemDecoration(new DividerItemDecoration(ContextCompat.getDrawable(this, R.drawable.line), true, true));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.book_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_person:
                Intent intent = new Intent(MainActivity.this, Add_activity.class);
                startActivity(intent);
                break;

            default:
        }
        return true;
    }

    public void initPersonName() {
        personNameList.add("John");
        personNameList.add("Kevin");
        personNameList.add("Ray");
        personNameList.add("Tim");
    }
    

}
