package com.example.pianotiles;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.Random;
import java.util.Vector;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {

    protected Vector<Vector<Button>> ButtonGrid = new Vector<Vector<Button>>();
    protected PopupWindow Restart;
    protected TextView HighScoreKeeper;
    protected TextView ScoreKeeper;
    protected Button RestartButton;
    protected int HighScore;
    protected int Score = 0;
    protected Boolean InPlay = true;
    protected Activity Current = this;

    protected static final String HighScoreKey = "HIGH_SCORE" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);

        ScoreKeeper = (TextView) findViewById(R.id.ScoreKeeper);
        HighScoreKeeper = (TextView) findViewById(R.id.Hi_ScoreKeeper);
        Restart = new PopupWindow(this);
        //Restart.setContentView(findViewById(R.id.linearLayout));

        if (savedInstanceState == null) {
            HighScore = 0;
        }
        else {
            HighScore = savedInstanceState.getInt(HighScoreKey);
        }

        RestartButton = (Button) findViewById(R.id.buttonRestart);
        RestartButton.setOnClickListener(buttonRestartListener);

        Vector<Button> buttonRow5 = new Vector<>();
        Button b1 = (Button) findViewById(R.id.button15);
        b1.setOnClickListener(buttonListener);
        Button b2 = (Button) findViewById(R.id.button25);
        b2.setOnClickListener(buttonListener);
        Button b3 = (Button) findViewById(R.id.button35);
        b3.setOnClickListener(buttonListener);
        Button b4 = (Button) findViewById(R.id.button45);
        b4.setOnClickListener(buttonListener);
        buttonRow5.add(b1);
        buttonRow5.add(b2);
        buttonRow5.add(b3);
        buttonRow5.add(b4);
        ButtonGrid.add(buttonRow5);
        Vector<Button> buttonRow4 = new Vector<>();
        b1 = (Button) findViewById(R.id.button14);
        b1.setOnClickListener(buttonListener);
        b2 = (Button) findViewById(R.id.button24);
        b2.setOnClickListener(buttonListener);
        b3 = (Button) findViewById(R.id.button34);
        b3.setOnClickListener(buttonListener);
        b4 = (Button) findViewById(R.id.button44);
        b4.setOnClickListener(buttonListener);
        buttonRow4.add(b1);
        buttonRow4.add(b2);
        buttonRow4.add(b3);
        buttonRow4.add(b4);
        ButtonGrid.add(buttonRow4);
        Vector<Button> buttonRow3 = new Vector<>();
        b1 = (Button) findViewById(R.id.button13);
        b1.setOnClickListener(buttonListener);
        b2 = (Button) findViewById(R.id.button23);
        b2.setOnClickListener(buttonListener);
        b3 = (Button) findViewById(R.id.button33);
        b3.setOnClickListener(buttonListener);
        b4 = (Button) findViewById(R.id.button43);
        b4.setOnClickListener(buttonListener);
        buttonRow3.add(b1);
        buttonRow3.add(b2);
        buttonRow3.add(b3);
        buttonRow3.add(b4);
        ButtonGrid.add(buttonRow3);
        Vector<Button> buttonRow2 = new Vector<>();
        b1 = (Button) findViewById(R.id.button12);
        b1.setOnClickListener(buttonListener);
        b2 = (Button) findViewById(R.id.button22);
        b2.setOnClickListener(buttonListener);
        b3 = (Button) findViewById(R.id.button32);
        b3.setOnClickListener(buttonListener);
        b4 = (Button) findViewById(R.id.button42);
        b4.setOnClickListener(buttonListener);
        buttonRow2.add(b1);
        buttonRow2.add(b2);
        buttonRow2.add(b3);
        buttonRow2.add(b4);
        ButtonGrid.add(buttonRow2);
        Vector<Button> buttonRow1 = new Vector<>();
        b1 = (Button) findViewById(R.id.button11);
        b1.setOnClickListener(buttonListener);
        b2 = (Button) findViewById(R.id.button21);
        b2.setOnClickListener(buttonListener);
        b3 = (Button) findViewById(R.id.button31);
        b3.setOnClickListener(buttonListener);
        b4 = (Button) findViewById(R.id.button41);
        b4.setOnClickListener(buttonListener);
        buttonRow1.add(b1);
        buttonRow1.add(b2);
        buttonRow1.add(b3);
        buttonRow1.add(b4);
        ButtonGrid.add(buttonRow1);
        Log.i("PianoTiles", "ButtonGrid size: " + Integer.toString(ButtonGrid.size()));
        for(Vector<Button> r : ButtonGrid){
            Log.i("PianoTiles", Integer.toString(r.size()));
            r.elementAt(new Random().nextInt(r.size())).setBackgroundResource(R.drawable.black_button_border);
        }

        updateScore();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // Always do this
        super.onSaveInstanceState(outState)  ;

        // Save the counter's state
        outState.putInt(HighScoreKey, HighScore);
    }

    public View.OnClickListener buttonListener = new View.OnClickListener() {

        // Called when address button is selected
        @Override
        public void onClick(View v) {
            Log.i("PianoTiles", "Button clicked");
            if(InPlay) {
                if (ButtonGrid.elementAt(4).contains(v)) {
                    Log.i("PianoTiles", "Button is in bottom row");
                    if (v.getBackground().getConstantState() == getResources().getDrawable(R.drawable.black_button_border).getConstantState()) {
                        Log.i("PianoTiles", "Button is in bottom row and is black");
                        Score++;
                        shiftDown();
                    }
                    else{
                        v.setBackgroundResource(R.drawable.red_button_border);
                        InPlay = false;
                    }
                } else {
                    v.setBackgroundResource(R.drawable.red_button_border);
                    InPlay = false;
                    /*
                    Restart.showAtLocation(findViewById(R.id.linearLayout), Gravity.CENTER, 0, 0);
                    Score = 0;
                    updateScore();
                    */
                }
                updateScore();
            /*
            if(v.getBackground().getConstantState() == getResources().getDrawable(R.drawable.white_button_border).getConstantState()){
                v.setBackgroundResource(R.drawable.black_button_border);
            }
            else if(v.getBackground().getConstantState() == getResources().getDrawable(R.drawable.black_button_border).getConstantState()){
                v.setBackgroundResource(R.drawable.red_button_border);
            }
            else if(v.getBackground().getConstantState() == getResources().getDrawable(R.drawable.red_button_border).getConstantState()){
                v.setBackgroundResource(R.drawable.white_button_border);
            }
            */
            }
        }
    };

    public View.OnClickListener buttonRestartListener = new View.OnClickListener() {

        // Called when address button is selected
        @Override
        public void onClick(View v) {
            Current.recreate();
            /*
            Intent i = new Intent(FullscreenActivity.this, FullscreenActivity.class);
            finish();
            startActivity(i);
            */
        }
    };

    public void shiftDown (){
        for(int i = ButtonGrid.size()-1; i >= 0; i--){
            if(i == 0){
                Vector<Button> row = ButtonGrid.elementAt(i);
                for(Button b : row){
                    b.setBackgroundResource(R.drawable.white_button_border);
                }
                row.elementAt(new Random().nextInt(row.size())).setBackgroundResource(R.drawable.black_button_border);
            }
            else{
                Vector<Button> rowPrev = ButtonGrid.elementAt(i-1);
                Vector<Button> rowCurr = ButtonGrid.elementAt(i);
                for(int j = 0; j < rowCurr.size(); j++){
                    rowCurr.elementAt(j).setBackground(rowPrev.elementAt(j).getBackground());
                }
            }
        }
    }

    protected void updateScore() {

        // update content of edit text field
        HighScore = HighScore < Score ? Score:HighScore;
        ScoreKeeper.setText("Score: " + String.format("%d", Score));
        HighScoreKeeper.setText("High Score: " + String.format("%d", HighScore));
    }
}
