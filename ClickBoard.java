package com.example.jordan.memorysquares;

// ClickBoard.java
// Creates empty 4 x 6. If button clicked, checks with BoardFilled ArrayList to see if correct.
//    If value in ArrayList = 0, turns button white, if ArrayList = 0, turns button red.
//    If value is wrongly clicked, button turns black.

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.Random;

public class ClickBoard extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_board);
        setTitle("What was the pattern?");

        GridLayout gridLayout = (GridLayout) findViewById(R.id.click_grid);
        assert gridLayout != null;
        gridLayout.setUseDefaultMargins(true);
        gridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);

        int total = 24;
        int column = 4;
        int row = total / column;
        gridLayout.setColumnCount(column);
        gridLayout.setRowCount(row);
        int currentRow = 0;
        int currentColumn = 0;

        for (int i = 0; i < total; i++) {
            if (currentColumn == column) {
                currentColumn = 0;
                currentRow++;
            }
            Button button = new Button(this);
            button.setBackgroundColor(Color.WHITE);
            button.setId(i);
            button.setOnClickListener(getOnClickDoSomething(button));
            button.setMinimumWidth(50);
            button.setMinHeight(50);
            button.setHeight(231);
            button.setWidth(245);

            GridLayout.Spec rowspan = GridLayout.spec(currentRow, 1);
            GridLayout.Spec colspan = GridLayout.spec(currentColumn, 1);
            GridLayout.LayoutParams gridLayoutParam = new GridLayout.LayoutParams(rowspan, colspan);
            gridLayout.addView(button, gridLayoutParam);
            currentColumn++;
        }
    }

    View.OnClickListener getOnClickDoSomething(final Button button) {
        return new View.OnClickListener() {
            public void onClick(View v) {
                if (BoardFilled.coloredSquares.get(button.getId()).equals(1)) {
                    button.setBackgroundColor(Color.RED);
                    BoardFilled.coloredSquares.remove(button.getId());
                    BoardFilled.coloredSquares.add(button.getId(), 0);
                    button.setOnClickListener(null);

                    if(!BoardFilled.coloredSquares.contains(1)){
                        setTitle("You good");
                        //this part is what doesn't work the second time around

                        Intent transitionScreen = new Intent(ClickBoard.this, TransitionScreen.class);
                        startActivity(transitionScreen);
                        finish();
                    }
                } else {
                    button.setBackgroundColor(Color.BLACK);

                    Intent failScreenIntent = new Intent(ClickBoard.this, FailScreen.class);
                    startActivity(failScreenIntent);
                    finish();
                }
            }
        };
    }

    @Override
    public void onClick(View v) {

    }
}