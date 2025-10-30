package com.abdbzkn.superheroinfoappwithkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.abdbzkn.superheroinfoappwithkotlin.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val intent = intent
        //casting yapıyoruz as ile
        //val selectedSuperhero = intent.getSerializableExtra("superhero") as Superhero intent ile almiycam
        val selectedSuperhero = MySingleton.chosenSuperhero

        selectedSuperhero?.let{
            binding.nameText.text = it.name
            binding.infoText.text = it.info
            binding.imageView.setImageResource(it.image)
        }
    }
}