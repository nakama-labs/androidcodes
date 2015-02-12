package com.example.helloservice;

import com.example.helloservice.MyService.LocalBinder;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
	
	MyService mService;
    boolean mBound = false;
    AI_Difficulty ai_obj;
    Cards card_obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ai_obj = new AI_Difficulty(1,2);
      //  Log.e("AI Diff",AI_Difficulty.val);
        ai_obj.setnewval("hai");
        TextView tv = (TextView) findViewById(R.id.random_str);
 	    tv.setText(AI_Difficulty.val);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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
    
 // Method to start the service
    public void startService(View view) {
       startService(new Intent(getBaseContext(), MyService.class));
       if(mBound)
       {
    	   String ip = mService.wifiIpAddress(getApplicationContext());
    	   Log.e("IP is ",ip);
    	   EditText et = (EditText) findViewById(R.id.local_ip);
    	   et.setText(ip);
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
	   String newstr = ai_obj.getnewval();
    //   Log.e("AI diff",newstr);
       TextView tv = (TextView) findViewById(R.id.random_str);
       tv.setText(newstr); 
       Human h = new Human(1,2);
       for(int i=0;i<5;i++)
       {
    	   int card = card_obj.deal_card();
    	   h.set_my_cards(card);
       }
       h.print_my_cards();
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
