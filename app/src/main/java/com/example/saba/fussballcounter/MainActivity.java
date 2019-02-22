package com.example.saba.fussballcounter;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    final int gameDuraton = 90;
    int scoreTeamA = 0;
    int scoreTeamB = 0;
    int faulTeamA = 0;
    int faulTeamB = 0;
    long startTimeSeconds = 0;
    long timeMinutes = 0;
    CountDownTimer count = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayScoreTeamA(0);
        displayScoreTeamB(0);
    }

    public void displayScoreTeamA(int score) {
        TextView t = (TextView) findViewById(R.id.team_a_score);
        scoreTeamA += score;
        t.setText("" + scoreTeamA);
    }

    public void displayScoreTeamB(int score) {
        TextView t = (TextView) findViewById(R.id.team_b_score);
        scoreTeamB += score;
        t.setText("" + scoreTeamB);


    }

    public void displayFaulTeamA(int faul) {
        TextView t = (TextView) findViewById(R.id.faulTeamA);
        faulTeamA += faul;
        t.setText("Faul: " + faulTeamA);
    }

    public void displayFaulTeamB(int faul) {
        TextView t = (TextView) findViewById(R.id.faulTeamB);
        faulTeamB += faul;
        t.setText("Faul: " + faulTeamB);
    }

    public void addPointA(View view) {
        if (count != null)
            displayScoreTeamA(1);
    }

    public void addFaulA(View view) {
        if (count != null)
            displayFaulTeamA(1);
    }

    public void addPointB(View view) {
        if (count != null)
            displayScoreTeamB(1);
    }

    public void addFaulB(View view) {
        if (count != null)
            displayFaulTeamB(1);
    }


    public void resetScore(View view) {
        TextView textButton = (TextView) findViewById(R.id.mainButton);
        if (count == null) {
            count = new CountDownTimer(5400000, 1000) {

                public void onTick(long millisUntilFinished) {
                    String formattedSec = String.format("%02d", (60 - (millisUntilFinished / 1000) % 60));
                    TextView timer = findViewById(R.id.timer);
                    timer.setText("" + (89 - (millisUntilFinished / 60000)) + ":" + formattedSec + "");
                    System.out.println("" + millisUntilFinished / 1000 + " " + (millisUntilFinished / 1000) % 60);
                }

                public void onFinish() {
                    TextView timer = findViewById(R.id.timer);
                    timer.setText("Done");
                }
            }.start();
            textButton.setText("Reset");
        } else {
            count.cancel();
            count = null;
            TextView timer = findViewById(R.id.timer);
            timer.setText("0:00");
            scoreTeamA = 0;
            scoreTeamB = 0;
            faulTeamA = 0;
            faulTeamB = 0;
            displayScoreTeamA(0);
            displayScoreTeamB(0);
            displayFaulTeamA(0);
            displayFaulTeamB(0);
            textButton.setText("Start");
        }
    }
}