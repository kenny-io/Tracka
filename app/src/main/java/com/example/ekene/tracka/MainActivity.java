package com.example.ekene.tracka;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0;

    boolean gameIsActive = true;

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winingPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {2, 4, 6}, {0, 4, 8}};


    public void dropIn(View view) {

        ImageView tracka = (ImageView) view;

        int tappedCounter = Integer.parseInt(tracka.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameIsActive) {

            gameState[tappedCounter] = activePlayer;

            tracka.setTranslationY(-1000f);

            if (activePlayer == 0) {    //0 == green

                tracka.setImageResource(R.drawable.green);
                tracka.animate().translationYBy(1000f);

                activePlayer = 1;   // 1 == red
            } else {
                tracka.setImageResource(R.drawable.red);
                tracka.animate().translationYBy(1000f);
                activePlayer = 0;

            }

            for (int[] winingPosition : winingPositions) {



// someone has won !!
                if (gameState[winingPosition[0]] == gameState[winingPosition[1]] &&
                        gameState[winingPosition[1]] == gameState[winingPosition[2]] &&
                        gameState[winingPosition[0]] != 2) {

                    gameIsActive = false;

                    String winner = "red";

                    if (gameState[winingPosition[0]] == 0) {

                        winner = "green";
                    } else {
                        winner = "red";
                    }

                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                    winnerMessage.setText(winner +  " has won !!");


                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    layout.setVisibility(view.VISIBLE);

                }else{

                    boolean gameIsOver = true;

                    for (int counterState : gameState){

                        if (counterState == 2) gameIsOver = false;
                    }

                    if (gameIsOver){


                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                        winnerMessage.setText("it's a draw");


                        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                        layout.setVisibility(view.VISIBLE);
                    }


                }

            }
        }


    }
    public void playAgainButton(View view) {

        gameIsActive = true;

        // empty the entire gameState to start afresh
        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
        layout.setVisibility(view.INVISIBLE);

        int activePlayer = 0;

        for (int i = 0; i < gameState.length; i++) { // loops through the gamestate and resets it to empty

            gameState[i] = 2;
        }

        // empty the entire layout to start afresh
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayoutView);

        for (int i = 0; i < gridLayout.getChildCount(); i++){

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }


    @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        }
    }


