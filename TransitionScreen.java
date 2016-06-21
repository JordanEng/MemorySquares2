package com.example.jordan.memorysquares;

// TransitionScreen.java
//    If the user clicks all the right squares, redirected to this activity. If the number of games
//    is less than 22, then it'll trigger more games. If the number of games is 22, then it says
//    you win and starts over from Test.java.

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class TransitionScreen extends AppCompatActivity {
    int countPress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_screen);

        if(BoardFilled.gameCount == 22){
            TextView tv = (TextView) findViewById(R.id.textView1);
            tv.setText("You beat the game! All 22 levels complete :)");
        }
    }

    public void buttonClick(View view) {
        countPress++;

        if(countPress == 1 && BoardFilled.gameCount < 22) {
            TextView tv = (TextView) findViewById(R.id.textView1);
            assert tv != null;
            BoardFilled.gameCount = BoardFilled.gameCount + 1;
            BoardFilled.time = 10000 * Math.pow(.75, BoardFilled.gameCount);

            DecimalFormat df = new DecimalFormat("#.##");
            df.setRoundingMode(RoundingMode.CEILING);
            String currentTime = df.format(BoardFilled.time/1000);

            tv.setText("Get ready for the next level. You'll have " + currentTime + " seconds.");
        }
        else if(countPress == 2 && BoardFilled.gameCount < 22){
            BoardFilled.gameCount = BoardFilled.gameCount + 1;
            BoardFilled.time = 10000 * Math.pow(.75, BoardFilled.gameCount);

            Intent playAgainIntent = new Intent(TransitionScreen.this, BoardFilled.class);
            startActivity(playAgainIntent);
            finish();
        }

        if(BoardFilled.gameCount == 22){
            Intent playAgainIntent = new Intent(TransitionScreen.this, Test.class);
            startActivity(playAgainIntent);
            finish();
        }
    }
}
