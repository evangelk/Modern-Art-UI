package com.example.vag.lab4b_modernartui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private SeekBar colorSeekBar;
    private int seekBarProgress;
    private LinearLayout boxes_left, boxes_right;
    private SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            Log.v(TAG, "Progress changed...");
            seekBarProgress = seekBar.getProgress();
            final int childCountLeft = boxes_left.getChildCount();
            for (int i = 0; i < childCountLeft; i++) {
                LinearLayout box = (LinearLayout) boxes_left.getChildAt(i);
                int color = ((ColorDrawable) box.getBackground()).getColor();
                if (color != Color.WHITE && color != Color.LTGRAY) {
                    box.setBackgroundColor(color + seekBarProgress * 0x1d);
                }
            }
            final int childCountRight = boxes_right.getChildCount();
            for (int i = 0; i < childCountRight; i++) {
                LinearLayout box = (LinearLayout) boxes_right.getChildAt(i);
                int color = ((ColorDrawable) box.getBackground()).getColor();
                if (color != Color.WHITE && color != Color.LTGRAY) {
                    box.setBackgroundColor(color + seekBarProgress * 0x3c);
                }
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boxes_left = (LinearLayout) findViewById(R.id.column_left);
        boxes_right = (LinearLayout) findViewById(R.id.column_right);
        colorSeekBar = (SeekBar) findViewById(R.id.seek_bar);
        colorSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.items, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()){
            case R.id.info:
                InformationWindow infoWindow = new InformationWindow ();
                infoWindow.show(getFragmentManager(),null);
                break;
        }
        return true;
    }
}
