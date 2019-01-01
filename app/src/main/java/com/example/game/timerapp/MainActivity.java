package com.example.game.timerapp;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar timerSeekBar;
    TextView timerview;
    boolean counteractive = false;
    Button b1;
    CountDownTimer ct ;


    public void resettimer () {
        timerview.setText("00:30");
        timerSeekBar.setProgress(30);
        ct.cancel();
        b1.setText("Go!");
        counteractive = false;
        timerSeekBar.setEnabled(true);
    }

    public  void updatetimer(int secondsleft){

        int minutes = (int) secondsleft / 60 ;
        int seconds = secondsleft  - minutes * 60;

        String secondstring  = Integer.toString(seconds);
        if(secondstring.length() == 1){
            secondstring = "0" + secondstring;
        }
        String mintesstring = Integer.toString(minutes);
        if(mintesstring.length() == 1){
            mintesstring = "0" + mintesstring;
        }

        timerview.setText(mintesstring + ":" + secondstring);


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timerSeekBar = (SeekBar)findViewById(R.id.timerseek);
        timerview = (TextView) findViewById(R.id.textView);
        b1 = (Button) findViewById(R.id.button);

        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updatetimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void controller(View view) {
        if (counteractive == false) {
            counteractive = true;
            timerSeekBar.setEnabled(false);
            b1.setText("Stop");
            ct =  new CountDownTimer(timerSeekBar.getProgress() * 1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    updatetimer((int) (millisUntilFinished / 1000));
                }

                @Override
                public void onFinish() {
                    resettimer();
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext() , R.raw.sound);
                    mplayer.start();

                }
            }.start();
        } else {
            resettimer();

        }
    }
}
