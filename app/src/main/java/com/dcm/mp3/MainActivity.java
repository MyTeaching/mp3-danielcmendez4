package com.dcm.mp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //to declare the variables
    private Button button_nyKnicks, button_laLakers;
    private TextView textView_nyKnicks;
    private TextView textView_laLakers;
    private int nyKnicks_int = 0, laLakers_int = 0;
    private String team_winning;

    // to declare the strings for the passing of intent
    public static final String EXTRA_MESSAGE = "com.dcm.mp3.EXTRA_MESSAGE";
    public static final String EXTRA_INT1 = "com.dcm.mp3.EXTRA_INT1";
    public static final String EXTRA_INT2 = "com.dcm.mp3.EXTRA_INT2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //to find the views of casting
        button_nyKnicks = (Button)findViewById(R.id.button_one_text);
        button_laLakers = (Button)findViewById(R.id.button_two_text);
        textView_nyKnicks = (TextView)findViewById(R.id.newYorkKnicks_scoreDisplay);
        textView_laLakers = (TextView)findViewById(R.id.LosAngelesLakers_scoreDisplay);

        //to begin a team scores
        textView_nyKnicks.setText("Score: " + String.valueOf(nyKnicks_int));
        textView_laLakers.setText("Score: " + String.valueOf(laLakers_int));

        //to set the listener of the onclick
        button_nyKnicks.setOnClickListener((View.OnClickListener)this);
        button_laLakers.setOnClickListener((View.OnClickListener)this);
    }

    @Override
    public void onClick(View v) {
        if(button_nyKnicks.equals(v)) {
            nyKnicks_int += 1;
            textView_nyKnicks.setText("Score: " + String.valueOf(nyKnicks_int));
            if(nyKnicks_int == 5) {
                gameIsOver();
            }
        }
        else if(button_laLakers.equals(v)) {
            laLakers_int += 1;
            textView_laLakers.setText("Score: " + String.valueOf(laLakers_int));
            if(laLakers_int == 5) {
                gameIsOver();
            }
        }

    }

    private void gameIsOver() {
        if (nyKnicks_int == Math.max(nyKnicks_int, laLakers_int)) {
            team_winning = "NEW YORK KNICKS";
        }
        else {
            team_winning = "LOS ANGELES LAKERS";
        }

        //to convert the data from main to winner activities; i means intent
        Intent i = new Intent(this, WinningActivity.class);
        i.putExtra(EXTRA_MESSAGE, team_winning);
        i.putExtra(EXTRA_INT1, nyKnicks_int);
        i.putExtra(EXTRA_INT2, laLakers_int);
        startActivity(i);
    }


}