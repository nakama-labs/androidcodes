package com.labs.nakama.fivecard;

import com.labs.nakama.fivecard.MyService.LocalBinder;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.MenuItem;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.View;
import android.widget.EditText;

public class TestActivity1 extends ActionBarActivity {
	
	MyService mService;
    boolean mBound = false;
    AI_Difficulty ai_obj;
    Cards card_obj;
    Human h;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);
        ai_obj = new AI_Difficulty(1,2);
      //  Log.e("AI Diff",AI_Difficulty.val);
        ai_obj.setnewval("hai");
    }
    

    @Override
    protected void onStart() {
        super.onStart();
        // Bind to LocalService
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        Log.e("Onstart", "called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Unbind from the service
        if (mBound) {
            unbindService(mConnection);
            Log.e("Onstop", "called");
            mBound = false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void pickDeck(View view)
    {
        try
        {
            int card = h.play_card(0);
            int deck_card = card_obj.draw_card_from_deck();
            card_obj.set_open_card(card);
            h.set_my_cards(deck_card);
        }
        catch(Exception ex)
        {
            Log.e("Pick Deck",ex.toString());
        }
    }

    public void pickOpen(View view)
    {
        Log.e("Main act","pick open called");
    }

 // Method to start the service
    public void startService(View view) {
       startService(new Intent(getBaseContext(), MyService.class));
       if(mBound)
       {
    	   String ip = mService.wifiIpAddress(getApplicationContext());
    	   Log.e("IP is ",ip);
    	   EditText et = (EditText) findViewById(R.id.local_ip);
    	   et.setText(ip);
    	   
    	   /* Game init is done here for time being.. */
    	   card_obj = new Cards();
    	   card_obj.build_deck();
    	   card_obj.shuffle_deck();
       }
    }

    // Method to stop the service
    public void stopService(View view) {
       stopService(new Intent(getBaseContext(), MyService.class));
       EditText et = (EditText) findViewById(R.id.local_ip);
	   et.setText("Service Stopped");
    //   Log.e("AI diff",newstr);
       h = new Human(1,2);
       for(int i=0;i<5;i++)
       {
    	   int card = card_obj.deal_card();
    	   h.set_my_cards(card);
       }
       h.print_my_cards();
       card_obj.init_open_card();
    }
    
    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            LocalBinder binder = (LocalBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };

}
