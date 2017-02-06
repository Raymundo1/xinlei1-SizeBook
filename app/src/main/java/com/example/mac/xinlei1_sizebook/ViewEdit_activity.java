package com.example.mac.xinlei1_sizebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * To view all the information of selected_person
 *
 * layout: activity_view_edit_activity.xml
 *
 * menu (res/menu/viewedit_activity_menu.xml)
 *      menu.Edit -> run the Edit_activity to edit selected_person
 *      menu.Delete -> delete the selected_person
 *
 * ViewEdit_activity links with MainActivity and Edit_activity
 */
public class ViewEdit_activity extends AppCompatActivity implements ActivityConstants{

    private static final String FILENAME = "person.txt";

    private ArrayList<person> personList;
    private person selected_person = new person();

    int person_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_edit_activity);

        loadFromFile();
        person_id = getIntent().getIntExtra("selected_id", 0);
        selected_person = personList.get(person_id);

        Log.d("selected_person", selected_person.toString());
        setTextView(selected_person);

    }

    /**
     * put the viewedit_activity_menu.xml(res/menu) into MainActivity menu
     * @param menu
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.viewedit_activity_menu, menu);
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
            case R.id.delete_person:
                Intent intent = new Intent(ViewEdit_activity.this, MainActivity.class);
                intent.putExtra("delete_person_id", person_id);
                intent.putExtra("calling-activity", ActivityConstants.VIEW_ACTIVITY);
                startActivity(intent);
                break;

            case R.id.edit_person:
                Intent intent1 = new Intent(ViewEdit_activity.this, Edit_activity.class);
                intent1.putExtra("edit_person_id", person_id);
                startActivity(intent1);
                break;

            default:
        }

        return true;
    }


    /**
     * Fill the information of selected_person to the screen
     * @param selected_person
     */
    private void setTextView(person selected_person) {

        TextView textView_name = (TextView) findViewById(R.id.view_name);
        TextView textView_date = (TextView) findViewById(R.id.view_date);
        TextView textView_neck = (TextView) findViewById(R.id.view_neck);
        TextView textView_bust = (TextView) findViewById(R.id.view_bust);
        TextView textView_chest = (TextView) findViewById(R.id.view_chest);
        TextView textView_waist = (TextView) findViewById(R.id.view_waist);
        TextView textView_hip = (TextView) findViewById(R.id.view_hip);
        TextView textView_inseam = (TextView) findViewById(R.id.view_inseam);
        TextView textView_comment = (TextView) findViewById(R.id.view_comment);

        String neck = selected_person.getNeck() == 0.0 ? "" : String.valueOf(selected_person.getNeck());
        String bust = selected_person.getBust() == 0.0 ? "" : String.valueOf(selected_person.getBust());
        String chest = selected_person.getChest() == 0.0 ? "" : String.valueOf(selected_person.getChest());
        String waist = selected_person.getWaist() == 0.0 ? "" : String.valueOf(selected_person.getWaist());
        String hip = selected_person.getHip() == 0.0 ? "" : String.valueOf(selected_person.getHip());
        String inseam = selected_person.getInseam() == 0.0 ? "" : String.valueOf(selected_person.getInseam());

        textView_name.setText(selected_person.getName());
        textView_date.setText(selected_person.getDate());
        textView_neck.setText(String.valueOf(neck));
        textView_bust.setText(String.valueOf(bust));
        textView_chest.setText(String.valueOf(chest));
        textView_waist.setText(String.valueOf(waist));
        textView_hip.setText(String.valueOf(hip));
        textView_inseam.setText(String.valueOf(inseam));
        textView_comment.setText(selected_person.getComment());


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
}
