package com.abdbzkn.chronometerwihjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button button;
    TextView textView;
    int sSecond;
    int second;
    int minute;
    int hour;
    Runnable runnable;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sSecond = 0;
        second = 0;
        minute = 0;
        hour = 0;
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
    }
    public void start(View view){
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {

                sSecond++;

                //Zaman birimlerimizin 10 olmadan önce başında 0 olarak yazdırılması için oluşturdum.
                if(second<10 && minute<10 && hour<10){
                    textView.setText( "Time : 0"+ hour + ":0" + minute + ":0" + second + "." + sSecond);
                } else if(second<10 && minute<10){
                    textView.setText( "Time : "+ hour + ":0" + minute + ":0" + second + "." + sSecond);
                } else if(minute<10 && hour<10){
                    textView.setText( "Time : 0"+ hour + ":0" + minute + ":" + second + "." + sSecond);
                } else if(second<10 && hour<10){
                    textView.setText( "Time : 0"+ hour + ":" + minute + ":0" + second + "." + sSecond);
                } else if(second<10){
                    textView.setText( "Time : "+ hour + ":" + minute + ":0" + second + "." + sSecond);
                } else if(minute<10){
                    textView.setText( "Time : "+ hour + ":0" + minute + ":" + second + "." + sSecond);
                } else if(hour<10){
                    textView.setText( "Time : 0"+ hour + ":" + minute + ":" + second + "." + sSecond);
                } else{
                    textView.setText( "Time : "+ hour + ":" + minute + ":" + second + "." + sSecond);
                }

                handler.postDelayed(runnable,1000/166); //

                if(sSecond == 60){
                    sSecond = 0;
                    second++;
                }
                if(second == 60){
                    second = 0;
                    minute++;
                }
                if(minute == 60){
                    minute = 0;
                    hour++;
                }
            }
        };
        handler.post(runnable);
        button.setEnabled(false);
    }
    public void restart(View view){
        button.setEnabled(true);
        handler.removeCallbacks(runnable);
        sSecond=0;
        second=0;
        minute=0;
        hour=0;
        textView.setText( "Time : 0"+ hour + ":0" + minute + ":0" + second + ".0" + sSecond);
    }
}