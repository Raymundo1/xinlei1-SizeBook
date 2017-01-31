package com.example.mac.xinlei1_sizebook;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class Add_activity extends AppCompatActivity implements ActivityConstants {

    private EditText editText_name;
    private EditText editText_date;
    private EditText editText_neck;
    private EditText editText_bust;
    private EditText editText_chest;
    private EditText editText_waist;
    private EditText editText_hip;
    private EditText editText_inseam;
    private EditText editText_comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_activity);

        // get all edit_text
        editText_name = (EditText) findViewById(R.id.add_name);
        editText_date = (EditText) findViewById(R.id.add_date);
        editText_neck = (EditText) findViewById(R.id.add_neck);
        editText_bust = (EditText) findViewById(R.id.add_bust);
        editText_chest = (EditText) findViewById(R.id.add_chest);
        editText_waist = (EditText) findViewById(R.id.add_waist);
        editText_hip = (EditText) findViewById(R.id.add_hip);
        editText_inseam = (EditText) findViewById(R.id.add_inseam);
        editText_comment = (EditText) findViewById(R.id.add_comment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_acitivity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_person_done:
                try {
                    // store the information in person class
                    if (editText_name.getText().toString().length() == 0) {
                        AlertDialog.Builder builder2 = new AlertDialog.Builder(Add_activity.this);
                        builder2.setMessage("Name can not be empty")
                                .setTitle("Warning")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                        builder2.show();
                    } else {

                        person new_person = new person();
                        new_person.setName(editText_name.getText().toString());
                        new_person.setDate(editText_date.getText().toString());
                        new_person.setNeck(editText_neck.getText().toString());
                        new_person.setBust(editText_bust.getText().toString());
                        new_person.setChest(editText_chest.getText().toString());
                        new_person.setWaist(editText_waist.getText().toString());
                        new_person.setHip(editText_hip.getText().toString());
                        new_person.setInseam(editText_inseam.getText().toString());
                        new_person.setComment(editText_comment.getText().toString());


                        Log.d("Add_person type", new_person.getClass().getName());
                        Log.d("Add_person", new_person.toString());

                        Intent intent1 = new Intent(Add_activity.this, MainActivity.class);
                        intent1.putExtra("new_person", new_person);
                        intent1.putExtra("calling-activity", ActivityConstants.ADD_ACTIVITY);
                        startActivity(intent1);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;

            case R.id.add_person_cancel:
                // URL : http://developer.android.com/guide/topics/ui/dialogs.html
                AlertDialog.Builder builder1 = new AlertDialog.Builder(Add_activity.this);
                builder1.setTitle("Alert for canceling")
                        .setMessage("Do you want to cancel this item ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent2 = new Intent(Add_activity.this, MainActivity.class);
                                startActivity(intent2);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Do nothing
                            }
                        });
                builder1.show();
                break;

            default:
        }
        return true;
    }
}
