package com.abdbzkn.catchthemousewithjava;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import com.abdbzkn.catchthemousewithjava.databinding.ActivityMainBinding;
import java.util.Random;
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    int score; int i; int j;
    ImageView[] imageArray; ImageView[] imageArray2;
    Runnable runnable; Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        imageArray = new ImageView[] { binding.imageView, binding.imageView2, binding.imageView3, binding.imageView4, binding.imageView5, binding.imageView6, binding.imageView7, binding.imageView8, binding.imageView9, binding.imageView10, binding.imageView11, binding.imageView12, binding.imageView13, binding.imageView14, binding.imageView15};
        imageArray2 = new ImageView[] { binding.imageView16, binding.imageView17, binding.imageView18, binding.imageView19, binding.imageView20, binding.imageView21, binding.imageView22, binding.imageView23, binding.imageView24, binding.imageView25, binding.imageView26, binding.imageView27, binding.imageView28, binding.imageView29, binding.imageView30};
        score = 0;
        binding.resultText.setVisibility(View.INVISIBLE);
        new CountDownTimer(15300,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                binding.timeText.setText("Time : " + millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                binding.timeText.setText("Time is out");
                handler.removeCallbacks(runnable);
                for(ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                for(ImageView image : imageArray2){
                    image.setVisibility(View.INVISIBLE);
                }
                binding.scoreText.setVisibility(View.INVISIBLE);
                binding.resultText.setText("YOUR SCORE IS " + score + "!" );
                binding.resultText.setVisibility(View.VISIBLE);
            }
        }.start();
        hideImages();
    }
    public void takeScore(View view){
        j=i;
        score++;
        binding.scoreText.setText("Score : " + score);
        imageArray[j].setVisibility(View.INVISIBLE);
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