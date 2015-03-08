package com.labs.nakama.fivecard;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Arrays;


public class HostActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);


        new Thread(new Runnable() {
            public void run() {

        // Find the ListView resource.
        ListView mainListView = (ListView) findViewById(R.id.mainListView);

        // Create and populate a List of planet names.
        String[] players = new String[] { "Player 1", "Player 2"};
        ArrayList<String> playersList = new ArrayList<>();
        playersList.addAll( Arrays.asList(players) );

        // Create ArrayAdapter using the planet list.
        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.row, playersList);

        // Add more planets. If you passed a String[] instead of a List<String>
        // into the ArrayAdapter constructor, you must not add more items.
        // Otherwise an exception will occur.
        listAdapter.add("Player 3");
        listAdapter.add("Player 4");

        // Set the ArrayAdapter as the ListView's adapter.
        mainListView.setAdapter(listAdapter);

            }
        }).start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
