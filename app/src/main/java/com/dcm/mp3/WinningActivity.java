package com.dcm.mp3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WinningActivity extends Activity {

    //to declare variables
    private TextView tV_winner;
    private Button shareStart;
    private String TeamWinning;
    private int spread_Score;
    private Intent activityShare;

    public static final String SCORE_EXTRA = "con.dcm.mp3.SCORE_EXTRA";
    public static final String WINNING_EXTRA = "con.dcm.mp3.WINNING_EXTRA";

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_winner);

        //to discover textview and button
        tV_winner = (TextView)findViewById(R.id.text_winner);
        shareStart = (Button)findViewById(R.id.button_shareTheAct);
        activityShare = new Intent(this, ShareActivity.class);

        //upgrade and override onClick
        shareStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityShare.putExtra(WINNING_EXTRA, TeamWinning);
                activityShare.putExtra(SCORE_EXTRA, spread_Score);
            }
        });

        //obtain data for the intent
        Intent i = getIntent();
        int nyKnicks_int = i.getIntExtra(MainActivity.EXTRA_INT1, 0);
        int laLakers_int = i.getIntExtra(MainActivity.EXTRA_INT2, 0);
        TeamWinning = i.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // to calculate the score spread
        spread_Score = (Math.max(nyKnicks_int,laLakers_int) - Math.min(nyKnicks_int,laLakers_int));

        // to show and print the spread and winner
        tV_winner.setText(TeamWinning + " won by: " + spread_Score);


    }

}
