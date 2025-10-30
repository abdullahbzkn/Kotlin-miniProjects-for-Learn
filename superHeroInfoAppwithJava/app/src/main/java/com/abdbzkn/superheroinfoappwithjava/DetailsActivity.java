package com.abdbzkn.superheroinfoappwithjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.abdbzkn.superheroinfoappwithjava.databinding.ActivityDetailsBinding;
import com.abdbzkn.superheroinfoappwithjava.databinding.ActivityMainBinding;
import org.w3c.dom.Text;

public class DetailsActivity extends AppCompatActivity {

    private ActivityDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding = ActivityDetailsBinding.inflate(getLayoutInflater());
       View view = binding.getRoot();
       setContentView(view);

       Intent intent = getIntent();
       Superhero selectedSuperhero = (Superhero) intent.getSerializableExtra("superhero");
       binding.superHeroNameText.setText(selectedSuperhero.name);
       binding.superHeroInfoText.setText(selectedSuperhero.info);
       binding.imageView.setImageResource(selectedSuperhero.image);
    }
}