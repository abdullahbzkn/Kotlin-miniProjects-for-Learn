package com.abdbzkn.catchthemousewithjava;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView timeText;
    TextView scoreText;
    TextView resultText;
    int score;
    int i;
    int j;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView imageView10;
    ImageView imageView11;
    ImageView imageView12;
    ImageView imageView13;
    ImageView imageView14;
    ImageView imageView15;
    ImageView imageView16;
    ImageView imageView17;
    ImageView imageView18;
    ImageView imageView19;
    ImageView imageView20;
    ImageView imageView21;
    ImageView imageView22;
    ImageView imageView23;
    ImageView imageView24;
    ImageView imageView25;
    ImageView imageView26;
    ImageView imageView27;
    ImageView imageView28;
    ImageView imageView29;
    ImageView imageView30;
    ImageView[] imageArray;
    ImageView[] imageArray2;
    Runnable runnable;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultText = findViewById(R.id.resultText);
        timeText = findViewById(R.id.timeText);
        scoreText = findViewById(R.id.scoreText);

        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);
        imageView10 = findViewById(R.id.imageView10);
        imageView11 = findViewById(R.id.imageView11);
        imageView12 = findViewById(R.id.imageView12);
        imageView13 = findViewById(R.id.imageView13);
        imageView14 = findViewById(R.id.imageView14);
        imageView15 = findViewById(R.id.imageView15);
        imageView16 = findViewById(R.id.imageView16);
        imageView17 = findViewById(R.id.imageView17);
        imageView18 = findViewById(R.id.imageView18);
        imageView19 = findViewById(R.id.imageView19);
        imageView20 = findViewById(R.id.imageView20);
        imageView21 = findViewById(R.id.imageView21);
        imageView22 = findViewById(R.id.imageView22);
        imageView23 = findViewById(R.id.imageView23);
        imageView24 = findViewById(R.id.imageView24);
        imageView25 = findViewById(R.id.imageView25);
        imageView26 = findViewById(R.id.imageView26);
        imageView27 = findViewById(R.id.imageView27);
        imageView28 = findViewById(R.id.imageView28);
        imageView29 = findViewById(R.id.imageView29);
        imageView30 = findViewById(R.id.imageView30);

        imageArray = new ImageView[] { imageView, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9, imageView10, imageView11, imageView12, imageView13, imageView14, imageView15};
        imageArray2 = new ImageView[] { imageView16, imageView17, imageView18, imageView19, imageView20, imageView21, imageView22, imageView23, imageView24, imageView25, imageView26, imageView27, imageView28, imageView29, imageView30};

        score = 0;
        resultText.setVisibility(View.INVISIBLE);
        new CountDownTimer(16000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                timeText.setText("Time : " + millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                timeText.setText("Time is out");
                handler.removeCallbacks(runnable);
                for(ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                for(ImageView image : imageArray2){
                    image.setVisibility(View.INVISIBLE);
                }
                scoreText.setVisibility(View.INVISIBLE);
                resultText.setText("YOUR SCORE IS " + score + "!" );
                resultText.setVisibility(View.VISIBLE);
            }
        }.start();
        hideImages();
    }
    public void takeScore(View view){
        j=i;
        score++;
        scoreText.setText("Score : " + score);
        imageArray[i].setVisibility(View.INVISIBLE);
        new CountDownTimer(80,80){
            @Override
            public void onTick(long millisUntilFinished) {
              imageArray2[j].setVisibility(View.VISIBLE);
            }

            @Override
            public void onFinish() {
                imageArray2[j].setVisibility(View.INVISIBLE);
            }
        }.start();
    }
    public void hideImages(){
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for(ImageView image : imageArray2){ //tüm imagelar görünmez yapıldı
                    image.setVisibility(View.INVISIBLE);
                }
                for(ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                Random random = new Random();
                i = random.nextInt(15);
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this,550);
            }
        };

        handler.post(runnable);

        for(ImageView image : imageArray2){ //tüm imagelar görünmez yapıldı
            image.setVisibility(View.INVISIBLE);
        }
        for(ImageView image : imageArray){
            image.setVisibility(View.INVISIBLE);
        }
    }
}