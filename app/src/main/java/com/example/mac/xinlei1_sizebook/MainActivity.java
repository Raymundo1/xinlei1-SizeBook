package com.example.mac.xinlei1_sizebook;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * The MAIN ACTIVITY which firstly shows after launching app
 * The list shows all the record
 * Add item in menu to add the new record
 *
 * layout: activity_main.xml
 *
 * <pre>
 *     pre-formatted text: <br>
 *         File Explorer -> data -> date -> com.example.mac.xinlei1-SizeBook -> files -> person.txt
 *         File Explorer -> data -> date -> com.example.mac.xinlei1-SizeBook -> sharedpreference -> Add_activity.xml
 * </pre>
 *
 * @ author Xinlei Chen
 * @ version 1.0.0
 */
public class MainActivity extends AppCompatActivity implements ActivityConstants{

    /**
     * the file thatt all the people are saved there
     * The format of the file is JSON.
     * @see #loadFromFile()
     * @see #saveInFile()
     */

    private static final String FILENAME = "person.txt";

    private ArrayList<String> personNameList = new ArrayList<>();
    private ArrayList<person> personList = new ArrayList<>();

    /**
     * Called when the activity is first created
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Called when the activity is becoming visible to user
     */
    @Override
    protected void onStart() {
        super.onStart();

        // load the person_list from JSON FILE
        loadFromFile();


        // Judging which activity calls the MainActivity and do the corresponding action
        if (getIntent().getExtras() != null) {
            int callingActivity = getIntent().getIntExtra("calling-activity", 0);
            switch (callingActivity) {
                case ActivityConstants.ADD_ACTIVITY:
                    person new_person = (person) getIntent().getSerializableExtra("new_person");

                    if ( personList.isEmpty() ) {
                        Log.d("new_person", new_person.toString());
                        personList.add(new_person);
                    } else {
                        if (!personList.get(personList.size() - 1).equals(new_person)) {
                            personList.add(new_person);
                        }
                    }

                    saveInFile();
                    getIntent().putExtra("calling-activity", ActivityConstants.MAIN_ACTIVITY);
                    break;

                case ActivityConstants.VIEW_ACTIVITY:
                    // we should saveInFile when we edit in ViewActivity
                    int delete_person_id = getIntent().getIntExtra("delete_person_id", 0);
                    personList.remove(delete_person_id);

                    int order = 0;
                    for (person item : personList) {
                        item.setId(order);
                        order = order + 1;
                    }

                    SharedPreferences.Editor editor = getSharedPreferences("Add_activity", MODE_PRIVATE).edit();
                    editor.putInt("count_person", personList.size()-1);
                    editor.apply();

                    saveInFile();
                    getIntent().putExtra("calling-activity", ActivityConstants.MAIN_ACTIVITY);
                    break;
            }
        }


        personNameList = TransferPersonListToNameList(personList, personNameList);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter
        RecyclerView.Adapter mAdapter = new PersonNameAdapter(personNameList);
        mRecyclerView.setAdapter(mAdapter);

        // add a decoration of list
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, null));
        //mRecyclerView.addItemDecoration(new DividerItemDecoration(ContextCompat.getDrawable(this, R.drawable.line), true, true));


        // set the TextView which shows the number of records
        TextView count = (TextView) findViewById(R.id.view_count);
        String content = Integer.toString(personList.size()) + " record(s)";
        count.setText(content);


    }

    /**
     * put the book_menu.xml(res/menu) into MainActivity menu
     * @param menu
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.book_menu, menu);
        return true;
    }

    /**
     * set the action if clicked the item which in Activity menu
     * @param item
     * @return true
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_person:
                saveInFile();
                Intent intent = new Intent(MainActivity.this, Add_activity.class);
                startActivity(intent);
                break;

            default:
        }
        return true;
    }


    /**
     * Loads personList from file.
     * @throws FileNotFoundException if the file is not created
     * @throws IOException to Trace the Error
     */
    private void loadFromFile() {
        try {

            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            personList = gson.fromJson(in, new TypeToken<List<person>>(){}.getType());

            fis.close();

        } catch (FileNotFoundException e) {
            personList = new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Saves person in file in JSON format.
     * @throws FileNotFoundException if folder not exists
     */
    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            Log.d("PersonList", personList.toString());
            gson.toJson(personList, out);
            out.flush();

            fos.close();

        } catch (FileNotFoundException e){
            throw new RuntimeException();
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    /**
     * get the name of every item in personList and create personNameList
     * @param personList
     * @param personNameList
     * @return personNameList
     */
    private ArrayList<String> TransferPersonListToNameList(ArrayList<person> personList, ArrayList<String> personNameList){
        String tem_name;

        if (!personNameList.isEmpty()) {
            personNameList.clear();
        }

        for (person person_item : personList) {
            tem_name = person_item.getName();
            personNameList.add(tem_name);
        }

        return personNameList;
    }
    

}
