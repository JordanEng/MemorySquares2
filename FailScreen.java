package com.example.jordan.memorysquares;

// FailScreen.java
// If the user clicks an incorrect box in ClickBoard.java, it starts this activity. Text displays
//    score. On the second button click, values from BoardFilled.java, such as gameCount and time, are reset
//    Back to the beginning of the game.

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class FailScreen extends AppCompatActivity {
    int countPress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fail_screen);
    }

    public void buttonClick(View view) {
        countPress++;
        if(countPress == 1) {
            TextView tv = (TextView) findViewById(R.id.youLoseText);
            tv.setText("Your score: " + (BoardFilled.gameCount));
        }

        else if (countPress == 2) {
            BoardFilled.gameCount = 0;
            BoardFilled.time = 10000*Math.pow(.75,BoardFilled.gameCount);

            Intent replayIntent = new Intent(FailScreen.this, Test.class);
            startActivity(replayIntent);
            finish();
        }
    }
}
