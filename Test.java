package com.example.jordan.memorysquares;

// Test.java
// Intro Activity. First Screen, displays rules on click.

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.*;

public class Test extends AppCompatActivity {
    int countPress = 0;
    ArrayList<String> intro = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test);
        intro.add(0, "In this game, you need to remember the " +
                "placement of the red squares and try to replicate them");
        intro.add(1,"The amount of time you have to memorize decreases " +
                "by 25% every round you succeed until you lose");
        intro.add(2, "Good Luck! You have 10.0 seconds to start");
    }

    public void buttonClick(View view) {
        TextView tv = (TextView) findViewById(R.id.textView1);

        if (countPress >= 3) {
            Intent boardFilledIntent = new Intent(this, BoardFilled.class);
            startActivity(boardFilledIntent);
            finish();
        } else {
            assert tv != null;
            tv.setText(intro.get(countPress));
            countPress++;
        }
    }
}
