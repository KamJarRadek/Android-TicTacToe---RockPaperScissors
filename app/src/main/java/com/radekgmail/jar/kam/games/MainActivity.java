package com.radekgmail.jar.kam.games;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onQuiteCLick(View view) {
        finish();
    }


    public void onTTTClick(View view) {
        Intent TTTGame = new Intent(this, TTTgame.class);
        startActivity(TTTGame);
    }
    private static final String TAG = "MyActivity";
    public void onSPSClick(View view) {

       // Log.v(TAG, "indexggggggg");
        Intent SPSGame = new Intent(this, SPSgame.class);
        startActivity(SPSGame);
    }
}
