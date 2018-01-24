package com.nemanja.userapp.ui.volume;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import com.nemanja.userapp.R;
import com.nemanja.userapp.ui.database.DatabaseActivity;
import com.nemanja.userapp.ui.movies.MoviesActivity;
import com.nemanja.userapp.ui.service.ServiceActivity;

public class VolumeActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    int activeButton = 1;

    AudioManager audioManager;

    RadioGroup radioGroup;
    Button smanji;
    Button pojacaj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume);

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        radioGroup = findViewById(R.id.search_radio_group);
        radioGroup.check(R.id.rb_phone);
        smanji = findViewById(R.id.smanji);
        pojacaj = findViewById(R.id.pojacaj);
        radioGroup.setOnCheckedChangeListener(this);
        smanji.setOnClickListener(this);
        pojacaj.setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_phone:
                activeButton = 1;
                break;
            case R.id.rb_video:
                activeButton = 2;
                break;
            case R.id.rb_alarm:
                activeButton = 3;
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.smanji:
                if (activeButton == 1) {
                    audioManager.adjustStreamVolume(AudioManager.STREAM_RING, AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);
                }
                if (activeButton == 2) {
                    audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);
                }
                if (activeButton == 3) {
                    audioManager.adjustStreamVolume(AudioManager.STREAM_ALARM, AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);
                }
                break;
            case R.id.pojacaj:
                if (activeButton == 1) {
                    audioManager.adjustStreamVolume(AudioManager.STREAM_RING, AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
                }
                if (activeButton == 2) {
                    audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
                }
                if (activeButton == 3) {
                    audioManager.adjustStreamVolume(AudioManager.STREAM_ALARM, AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
                }
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.service_menu_item:
                Intent i = new Intent(this, ServiceActivity.class);
                startActivity(i);
                return true;
            case R.id.database_menu_item:
                Intent intent = new Intent(this, DatabaseActivity.class);
                startActivity(intent);
                return true;
            case R.id.volume:
                Intent intent2 = new Intent(this, VolumeActivity.class);
                startActivity(intent2);
                return true;
            case R.id.movies:
                Intent intent3 = new Intent(this, MoviesActivity.class);
                startActivity(intent3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
