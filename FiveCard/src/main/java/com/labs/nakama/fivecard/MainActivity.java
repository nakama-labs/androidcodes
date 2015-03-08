package com.labs.nakama.fivecard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

                // Let's create the missing ImageView
                ImageView image = new ImageView(this);

                // Now the layout parameters, these are a little tricky at first
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

                image.setScaleType(ImageView.ScaleType.MATRIX);
                image.setImageResource(R.drawable.main);

                // Let's get the root layout and add our ImageView
                RelativeLayout layout = (RelativeLayout) findViewById(R.id.main);
                layout.addView(image, 0, params);

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

    /**
     * Called when the user clicks the Send button
     */
    public void startPlay(View view) {
        Intent intent = new Intent(this, PlayActivity.class);
        startActivity(intent);
    }

    /**
     * Called when the user clicks the Send button
     */
    public void about(View view) {
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }

    /**
     * Called when the user clicks the Send button
     */
    public void settings(View view) {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
}
