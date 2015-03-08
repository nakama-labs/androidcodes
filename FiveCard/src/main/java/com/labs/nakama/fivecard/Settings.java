package com.labs.nakama.fivecard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;


public class Settings extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        new Thread(new Runnable() {
            public void run() {

                // Find the ListView resource.
                ListView settingsListView = (ListView) findViewById(R.id.settingsListView);

                // Create and populate a List of planet names.
                String[] players = new String[] { "Change My Name", "Music"};
                ArrayList<String> playersList = new ArrayList<>();
                playersList.addAll( Arrays.asList(players) );

                // Create ArrayAdapter using the planet list.
                ArrayAdapter<String> listAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.settings_row, playersList);

                // Add more planets. If you passed a String[] instead of a List<String>
                // into the ArrayAdapter constructor, you must not add more items.
                // Otherwise an exception will occur.
                listAdapter.add("Test");
                listAdapter.add("Player 4");

                // Set the ArrayAdapter as the ListView's adapter.
                settingsListView.setAdapter(listAdapter);

                // When item is tapped, toggle checked properties of CheckBox and Planet.
                settingsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View item,
                                            int position, long id) {

                        Intent intent;
                        switch (position) {
                            case 0:
                                intent = new Intent(getApplicationContext(), ChangeName.class);
                                startActivity(intent);
                                break;
                            case 1:
                                break;
                            case 2:
                                intent = new Intent(getApplicationContext(), TestActivity.class);
                                startActivity(intent);
                            default:
                        }
                    }
                });
            }
        }).start();




    }

}