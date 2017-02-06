package com.example.mac.xinlei1_sizebook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
 * Run by clicking edit icon in menu of ViewEdit_activity
 * To Edit the person's information
 *
 * Button DONE on the bottom of Screen which be used to finish editing
 *
 * Edit_activity links with MainActivity
 */
public class Edit_activity extends AppCompatActivity {

    private static final String FILENAME = "person.txt";

    private ArrayList<person> personList;
    private person selected_person = new person();

    private EditText textView_name;
    private EditText textView_date;
    private EditText textView_neck;
    private EditText textView_bust;
    private EditText textView_chest;
    private EditText textView_waist;
    private EditText textView_hip;
    private EditText textView_inseam;
    private EditText textView_comment;

    int person_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_activity);

        loadFromFile();
        person_id = getIntent().getIntExtra("edit_person_id", 0);
        selected_person = personList.get(person_id);

        setEditTextView(selected_person);

        // Using button DONE to finish editing selected person
        //  and send the selected_person's id,
        //  and equip the selected_person with new information
        Button button = (Button) findViewById(R.id.edit_finish);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected_person = getEditPerson(selected_person);
                personList.set(person_id, selected_person);
                saveInFile();
                Intent intent = new Intent(Edit_activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * set the view of EditText
     * put the information of selected_person
     * @param selected_person
     */
    private void setEditTextView(person selected_person) {

        textView_name = (EditText) findViewById(R.id.edit_name);
        textView_date = (EditText) findViewById(R.id.edit_date);
        textView_neck = (EditText) findViewById(R.id.edit_neck);
        textView_bust = (EditText) findViewById(R.id.edit_bust);
        textView_chest = (EditText) findViewById(R.id.edit_chest);
        textView_waist = (EditText) findViewById(R.id.edit_waist);
        textView_hip = (EditText) findViewById(R.id.edit_hip);
        textView_inseam = (EditText) findViewById(R.id.edit_inseam);
        textView_comment = (EditText) findViewById(R.id.edit_comment);

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
     * Put all the edited information to selected_person
     * @param selected_person
     * @return selected_person
     */
    private person getEditPerson(person selected_person) {

        selected_person.setName(textView_name.getText().toString());
        selected_person.setDate(textView_date.getText().toString());
        selected_person.setNeck(textView_neck.getText().toString());
        selected_person.setBust(textView_bust.getText().toString());
        selected_person.setChest(textView_chest.getText().toString());
        selected_person.setWaist(textView_waist.getText().toString());
        selected_person.setHip(textView_hip.getText().toString());
        selected_person.setInseam(textView_inseam.getText().toString());
        selected_person.setComment(textView_comment.getText().toString());

        return selected_person;
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
}
