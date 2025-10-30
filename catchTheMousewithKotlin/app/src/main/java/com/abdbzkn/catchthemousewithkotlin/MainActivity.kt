package com.abdbzkn.catchthemousewithkotlin

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.abdbzkn.catchthemousewithkotlin.databinding.ActivityMainBinding
import java.util.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var score = 0
    val mouseimageArray = ArrayList<ImageView>()
    var runnable = Runnable{}
    var handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        score = 0
        mouseimageArray.add(binding.imageView)
        mouseimageArray.add(binding.imageView2)
        mouseimageArray.add(binding.imageView3)
        mouseimageArray.add(binding.imageView4)
        mouseimageArray.add(binding.imageView5)
        mouseimageArray.add(binding.imageView6)
        mouseimageArray.add(binding.imageView7)
        mouseimageArray.add(binding.imageView8)
        mouseimageArray.add(binding.imageView9)
        mouseimageArray.add(binding.imageView10)
        mouseimageArray.add(binding.imageView11)
        mouseimageArray.add(binding.imageView12)
        hideImages()
        object : CountDownTimer(15300,1000){
            override fun onTick(millisUntilFinished: Long) {
                binding.timeText.text = "Time : ${millisUntilFinished/1000}"
            }
            override fun onFinish() {
                binding.timeText.text = "Time : 0"
                for(image in mouseimageArray){
                    image.visibility = View.INVISIBLE
                }
                handler.removeCallbacks(runnable)

                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over")
                alert.setMessage("Restart The Game?")
                alert.setPositiveButton("Yes", DialogInterface.OnClickListener{ dialogInterface , i ->
                    //baştan başlatıcam
                    val intentFromMain = intent
                    finish()
                    startActivity(intentFromMain)
                })
                alert.setNegativeButton("No", DialogInterface.OnClickListener{ dialogInterface , i ->
                    //toast göstericmem
                    Toast.makeText(this@MainActivity,"Game Over",Toast.LENGTH_LONG).show()
                })
                alert.show()
            }
        }.start()
    }
    fun increaseScore(view : View){
        score++
        /* aynı konumda birden fazla score alınmaması için cok kısa bi süre engelledim
        object : CountDownTimer(90,10){
            override fun onTick(millisUntilFinished: Long) {
                for(image in mouseimageArray){
                    image.isEnabled = false
                }
            }
            override fun onFinish() {
                for(image in mouseimageArray){
                    image.isEnabled = true
                }
            }
        }.start()
        */
        binding.scoreText.text = "Score : ${score}"
    }
    fun hideImages(){
        runnable = object : Runnable{
            override fun run() {

                for ( image in mouseimageArray){
                    image.visibility = View.INVISIBLE
                }
                val random = Random()
                val randomIndex = random.nextInt(12)
                mouseimageArray[randomIndex].visibility = View.VISIBLE
                handler.postDelayed(runnable,400,)
            }
        }
        handler.post(runnable)
    }
}