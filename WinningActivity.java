package com.dcm.mp3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.MessageFormat;

public class WinningActivity extends AppCompatActivity {

    private String TeamWinning;
    private int spread_Score;
    private Intent activityShare;

    public static final String EXTRA_WINNER = "com.dcm.mp3.EXTRA_WINNER";
    public static final String EXTRA_SCORE = "com.dcm.mp3.EXTRA_SCORE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winning);

        TextView txt_winner = findViewById(R.id.txt_winner);
        Button startAct_btn = findViewById(R.id.shareAct_btn);
        activityShare = new Intent(this, ShareActivity.class);

        startAct_btn.setOnClickListener(v -> {
            activityShare.putExtra(EXTRA_WINNER, TeamWinning);
            activityShare.putExtra(EXTRA_SCORE, spread_Score);

            startActivity(activityShare);
        });

        Intent intent = getIntent();
        int int_teamOne = intent.getIntExtra(MainActivity.EXTRA_INT1, 0);
        int int_teamTwo = intent.getIntExtra(MainActivity.EXTRA_INT2, 0);
        TeamWinning = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        spread_Score = (Math.max(int_teamOne, int_teamTwo) - Math.min(int_teamOne, int_teamTwo));

        txt_winner.setText(MessageFormat.format("{0} succeeded by: {1}", TeamWinning, spread_Score));

    }
}
