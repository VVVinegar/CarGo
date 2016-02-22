package com.example.vin.cargo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Music.play(this, R.raw.bgm);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        Music.stop(this);
    }


    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        Music.play(this, R.raw.bgm);
    }
}
