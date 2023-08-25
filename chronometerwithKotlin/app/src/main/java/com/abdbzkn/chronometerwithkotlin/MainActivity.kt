package com.abdbzkn.chronometerwithkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.abdbzkn.chronometerwithkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var sSecond = 0
    var second = 0
    var minute = 0
    var hour = 0
    private lateinit var binding: ActivityMainBinding
    var runnable : Runnable = Runnable {}
    var handler : Handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
    fun start(view: View){
        runnable = object : Runnable{
            override fun run() {
                //sayaç başla
                sSecond++
                if(second<10 && minute<10 && hour<10){
                    binding.textView.text=  "Time :  0$hour : 0$minute : 0$second  : $sSecond"
                } else if(second<10 && minute<10){
                    binding.textView.text=  "Time :  $hour : 0$minute : 0$second  : $sSecond"
                } else if(minute<10 && hour<10){
                    binding.textView.text=  "Time :  0$hour : 0$minute : $second  : $sSecond"
                } else if(second<10 && hour<10){
                    binding.textView.text=  "Time :  0$hour : $minute : 0$second  : $sSecond"
                } else if(second<10){
                    binding.textView.text=  "Time :  $hour : $minute : 0$second  : $sSecond"
                } else if(minute<10){
                    binding.textView.text=  "Time :  $hour : 0$minute : $second  : $sSecond"
                } else if(hour<10){
                    binding.textView.text=  "Time :  0$hour : $minute : $second  : $sSecond"
                } else{
                    binding.textView.text=  "Time :  $hour : $minute : $second  : $sSecond"
                }
                handler.postDelayed(runnable,1000/166)
                if(sSecond == 60){
                    sSecond = 0
                    second++
                }
                if(second == 60){
                    second = 0
                    minute++
                }
                if(minute == 60){
                    minute = 0
                    hour++
                }
            }
        }
        handler.post(runnable)
        binding.button.isEnabled = false
    }
    fun restart(view: View){
        binding.button.isEnabled = true
        handler.removeCallbacks(runnable)
        sSecond = 0
        second = 0
        minute = 0
        hour = 0
        binding.textView.text = "Time :  0$hour : 0$minute : 0$second  : 0$sSecond"
    }
}