package com.radekgmail.jar.kam.games;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kam.jar.radek@gmail.com on 2017-01-20.
 */

public class TTTgame extends AppCompatActivity {
    private static final String TAG = "AppCompatActivity";
    private int noOfFields = 9;
    public int size = (int) Math.sqrt(noOfFields);
    public static final String CROSS = "X";
    public static final String CIRCLE = "O";
    private String currentSymbol = CROSS;
    private String messagge = "";
    private TextView display;
    private int[][] crossClicks;
    private int[][] circleClicks;
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8;
    public ArrayList<Button> btns = new ArrayList<>();
    public int noOfClicks;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ttt_activity);

        display = (TextView) findViewById(R.id.display);
        display.setText("Push the Button");

        btn0 = (Button) findViewById(R.id.btn0);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);

        btn0.setImeActionLabel("0", 0);
        btn1.setImeActionLabel("1", 1);
        btn2.setImeActionLabel("2", 2);
        btn3.setImeActionLabel("3", 3);
        btn4.setImeActionLabel("4", 4);
        btn5.setImeActionLabel("5", 5);
        btn6.setImeActionLabel("6", 6);
        btn7.setImeActionLabel("7", 7);
        btn8.setImeActionLabel("8", 8);
        btns.add(btn0);
        btns.add(btn1);
        btns.add(btn2);
        btns.add(btn3);
        btns.add(btn4);
        btns.add(btn5);
        btns.add(btn6);
        btns.add(btn7);
        btns.add(btn8);
        setClicks();
    }


    private boolean isWinner(String symbolToCheck) {
        int[][] currentClick;
       // noOfClicks = 0;
        if (symbolToCheck.equals(CROSS)) {
            currentClick = crossClicks;
        } else {
            currentClick = circleClicks;
        }
        int sumD1 = 0;
        int sumD2 = 0;
        for (int i = 0; i < size; i++) {
            int sumHor = 0;
            int sumVer = 0;
            for (int j = 0; j < size; j++) {
                sumHor += currentClick[i][j];
                sumVer += currentClick[j][i];
            }
            sumD1 += currentClick[i][i];
            sumD2 += currentClick[i][size - 1 - i];

            if (sumHor == size || sumVer == size || sumD1 == size || sumD2 == size) {
                return true;
            }
        }

        return false;
    }

    private void tttAction(Button currentBtn) {
        currentBtn.setText(currentSymbol);
        int currIndex = currentBtn.getImeActionId();
        if (currentSymbol.equals(CROSS)) {
            crossClicks[currIndex / size][currIndex % size] = 1;
        } else {
            circleClicks[currIndex / size][currIndex % size] = 1;
        }
        if (isWinner(currentSymbol)) {
            messagge = "The winner is " + currentSymbol;
            display.setText(messagge);
            setClicks();
            clearBtn();
        }
        if (noOfClicks == 9) {
            messagge = "Nobody won. \nTry again";
            display.setText(messagge);
        }
        switchCurrSymbol();
        noOfClicks++;
    }


    private void setClicks() {
        circleClicks = new int[size][size];
        crossClicks = new int[size][size];
        noOfClicks = 0;

    }

    private void clearBtn() {
//
//        for (widok in  widokAplikacji.views){
//
//
//        }

        for (int i = 0; i < btns.size(); i++) {
            Button temp = btns.get(i);
            temp.setText("");
        }
        messagge += " \n To start a game press any of buttons";
        display.setText(messagge);
    }

    private void switchCurrSymbol() {
        if (currentSymbol.equals(CROSS)) {
            currentSymbol = CIRCLE;
        } else {
            currentSymbol = CROSS;
        }
    }


    public void onClick(View view) {
        Log.d(TAG, "onClick: ");
        Log.d(TAG, view.toString());
        if (view instanceof Button){
            Button b = (Button) view;
            if (b.getText().equals("")) {
                tttAction(b);
                Log.d(TAG, b.getImeActionLabel().toString());


            }
        }

   }

    public void onQCLick(View view) {
        finish();
    }
}
