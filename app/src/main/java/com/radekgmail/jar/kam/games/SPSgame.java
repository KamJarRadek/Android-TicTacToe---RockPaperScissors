package com.radekgmail.jar.kam.games;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by kam.jar.radek@gmail.com on 2017-01-20.
 */
public class SPSgame extends AppCompatActivity {

    private String[] handSign = {"SCISSOR", "PAPER", "STONE"};
    Random random = new Random();
    public boolean gameOver = false;
    String playerMove;
    String computerMove;
    static int numTrials = 0;
    static int numComputerWon = 0;
    static int numPlayerWon = 0;
    static int numTie = 0;
    TextView display;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sps_activity);
        display = (TextView) findViewById(R.id.SPSdisplay);
        display.setText("Let us begin...");
        initialize();
    }

    private void initialize() {

        if (!gameOver)
        //Computer Move
        {
            int aRandomNumber = random.nextInt(3); // random int between 0 (inclusive) and 3 (exclusive)
            if (aRandomNumber == 0) {
                computerMove = handSign[0];
                display.setText("   My turn: SCISSOR");
            } else if (aRandomNumber == 0) {
                computerMove = handSign[1];
                display.setText("   My turn: PAPER");
            } else {
                computerMove = handSign[2];
                display.setText("   My turn: STONE");
            }

            // Check result
            if (computerMove == playerMove) {
                display.setText("   Tie!");
                ++numTie;
            } else if (computerMove == handSign[0] && playerMove == handSign[1]) {
                display.setText("   Scissor cuts paper, I won!");
                ++numComputerWon;
            } else if (computerMove == handSign[1] && playerMove == handSign[2]) {
                display.setText("   Paper wraps stone, I won!");
                ++numComputerWon;
            } else if (computerMove == handSign[2] && playerMove == handSign[0]) {
                display.setText("   Stone breaks scissor, I won!");
                ++numComputerWon;
            } else {
                display.setText("   You won!");
                ++numPlayerWon;
            }
            ++numTrials;
        }


    }

    public void onStoneClick(View view) {

        playerMove = handSign[2];
        initialize();

    }

    public void onQCLick(View view) {
        finish();
    }


    public void onScissorsClick(View view) {
        playerMove = handSign[0];
        initialize();

    }

    public void onPaperClick(View view) {
        playerMove = handSign[1];
        initialize();
    }

    public void onStopClick(View view) {
        gameOver = true;
        display.setText("Number of trials: " + numTrials);
        display.setText("I won: " + numComputerWon + "  You won: " + numPlayerWon +
                " Number of Trials: " + numTrials);

    }
}



