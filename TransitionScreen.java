package com.example.jordan.memorysquares;

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
            tv.setText("You beat the game!");
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
        else if(countPress == 2 || BoardFilled.gameCount == 22){
            Intent playAgainIntent = new Intent(TransitionScreen.this, BoardFilled.class);
            startActivity(playAgainIntent);
            finish();
        }
    }
}
