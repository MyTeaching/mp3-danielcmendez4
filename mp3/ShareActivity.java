package com.dcm.mp3;

import android.Manifest;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.core.app.ActivityCompat;

import java.net.URI;

public class ShareActivity extends Activity {
    public static final int SOLICIT_CALL_PHONE = 1;
    public static final int SOLICIT_MESSAGE_PHONE = 2;

    //to declare the widgets
    private EditText PhoneNumber;
    private Button createCall, MsgSent, Look;
    private String callTheNumber, SendTheNumber, TeamWinning;
    private int Score;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_share);

        //to discover the widgets
        PhoneNumber = (EditText) findViewById(R.id.editText_getPhoneNumber);
        createCall = (Button) findViewById(R.id.button_createCall);
        MsgSent = (Button) findViewById(R.id.button_sendMessage);
        Look = (Button) findViewById(R.id.button_search_BB);

        //obtain intent and value for the winning actvity
        Intent intentShare = getIntent();
        Score = intentShare.getIntExtra(WinningActivity.SCORE_EXTRA, 0);
        TeamWinning = intentShare.getStringExtra(WinningActivity.WINNING_EXTRA);

        //from createCall to friendMSG
        createCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                friendCall();
            }
        });

        MsgSent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                friendMSG();
            }
        });

        Look.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lookBB();
            }
        });
    }

    private void friendCall() {
        callTheNumber = "Telephone:" + PhoneNumber.getText().toString();
        Intent intentCall = new Intent(Intent.ACTION_CALL);
        intentCall.setData(Uri.parse(callTheNumber));

        if (intentCall.resolveActivity(getPackageManager()) != null) ;
        {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.CALL_PHONE}, SOLICIT_MESSAGE_PHONE);

            } else {
                startActivity(intentCall);
            }
        }
    }

    private void friendMSG() {
        SendTheNumber = "sms:" + PhoneNumber.getText().toString();

        String msg = TeamWinning + " won at ScoreCount by: " + Score;

        Intent msgIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse(SendTheNumber));
        msgIntent.putExtra("SMS_BODY", msg);
        startActivity(msgIntent);

        if (msgIntent.resolveActivity(getPackageManager()) != null) ;
        {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.SEND_SMS}, SOLICIT_CALL_PHONE);
            } else {
                startActivity(msgIntent);
            }
        }

    }


    private void lookBB() {
        Uri search = Uri.parse("Geo:0,0?q=basketball bear me!");

        Intent searchIntent = new Intent(Intent.ACTION_VIEW, search);
    }
}