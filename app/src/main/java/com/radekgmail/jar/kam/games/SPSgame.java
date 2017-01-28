package com.radekgmail.jar.kam.games;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by kam.jar.radek@gmail.com on 2017-01-20.
 */
public class SPSgame extends AppCompatActivity {

    private Symbol[] handSign = {Symbol.SCISSORS, Symbol.PAPER, Symbol.STONE};
    Random random = new Random();
    public boolean gameOver = false;
    Symbol playerMove;
    Symbol computerMove;
    private int numTrials = 0;
    private int numComputerWon = 0;
    private int numPlayerWon = 0;
    private int numTie = 0;
    private TextView display;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sps_activity);
        display = (TextView) findViewById(R.id.SPSdisplay);
        display.setText("Let us begin...");
    }

    private void nextRound() {

        if (!gameOver)
        //Computer Move
        { // random int between 0 (inclusive) and 3 (exclusive)

            computerMakeMove();
            display.setText("My turn: "+ computerMove);

            // Check result
           display.setText(checkWinner());
        }


    }

    private String checkWinner() {
        ++numTrials;
        if (computerMove == playerMove) {
            ++numTie;
            return "Tie!";

        } else if (computerMove == Symbol.SCISSORS && playerMove == Symbol.PAPER) {
            ++numComputerWon;
            return "Scissor cuts paper, I won!";
        } else if (computerMove == Symbol.PAPER && playerMove == Symbol.STONE) {
            ++numComputerWon;
            return "Paper wraps stone, I won!";
        } else if (computerMove == Symbol.STONE && playerMove == Symbol.SCISSORS) {
            ++numComputerWon;
            return "Stone breaks scissor, I won!";
        } else {
            ++numPlayerWon;
            return "I chose "+ computerMove+" So You won!";
        }
    }

    private void computerMakeMove() {
        computerMove = handSign[random.nextInt(3)];
    }

    public void onClick(View view) {
        gameOver = false;
        Button btnTemp = (Button)view;
        String btnTempText = (String) btnTemp.getText();
        playerMove = Symbol.valueOf(btnTempText.toUpperCase());
        nextRound();

    }
    public void onStopClick(View view) {
        gameOver = true;
        display.setText("Number of trials: " + numTrials);
        display.setText("I won: " + numComputerWon + "  You won: " + numPlayerWon +
                " Number of Trials: " + numTrials);
    }
    public void onQCLick(View view) {
        finish();
    }
}