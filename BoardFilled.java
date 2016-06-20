package com.example.jordan.memorysquares;

import android.content.res.Configuration;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;
import android.widget.Button;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.Random;

public class BoardFilled extends AppCompatActivity {
    static ArrayList<Integer> coloredSquares = new ArrayList<>();
    static int gameCount = 0;
    static double time = 10000*Math.pow(.75,gameCount);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_filled);
        setTitle("Watch the Squares");

        GridLayout gridLayout = (GridLayout) findViewById(R.id.image_grid);
        assert gridLayout != null;
        gridLayout.setUseDefaultMargins(true);
        gridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);

        buildBoard(gridLayout);

        runGame(time);
    }

    public void buildBoard(GridLayout gridLayout) {

        coloredSquares = new ArrayList<>();

        for(int i = 0; i < 24; i++){
            coloredSquares.add(i,0);
        }

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
            button.setMinimumWidth(50);
            button.setMinHeight(50);
            button.setHeight(231);
            button.setWidth(245);

            Random rand = new Random();
            int random = rand.nextInt(50 - gameCount);
            if (random < gameCount + 8) {
                button.setBackgroundColor(Color.RED);
                coloredSquares.set(i,1);
            } else {
                button.setBackgroundColor(Color.WHITE);
            }

            if(!coloredSquares.contains(1)){
                button.setBackgroundColor(Color.RED);
                coloredSquares.set(i,1);
            }

            GridLayout.Spec rowspan = GridLayout.spec(currentRow, 1);
            GridLayout.Spec colspan = GridLayout.spec(currentColumn, 1);
            GridLayout.LayoutParams gridLayoutParam = new GridLayout.LayoutParams(rowspan, colspan);
            gridLayout.addView(button, gridLayoutParam);
            currentColumn++;
        }
    }

    public void runGame(double time) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent clickBoardIntent = new Intent(BoardFilled.this, ClickBoard.class);
                startActivity(clickBoardIntent);
                finish();
            }
        }, (long) time);
    }
}