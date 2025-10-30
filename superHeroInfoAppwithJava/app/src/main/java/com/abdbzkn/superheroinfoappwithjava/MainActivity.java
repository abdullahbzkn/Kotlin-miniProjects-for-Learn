package com.abdbzkn.superheroinfoappwithjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.os.Bundle;
import android.view.View;
import com.abdbzkn.superheroinfoappwithjava.databinding.ActivityMainBinding;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    ArrayList<Superhero> superheroArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        superheroArrayList = new ArrayList<>();

        Superhero batman = new Superhero("Batman","Gotham City",R.drawable.batman);
        Superhero ironman = new Superhero("Ironman","Manhattan / New York",R.drawable.ironman);
        Superhero spiderman = new Superhero("Spiderman","Queens / New York",R.drawable.spiderman);
        Superhero superman = new Superhero("Superman","Kansas City",R.drawable.superman);
        Superhero thor = new Superhero("Thor","Asgard",R.drawable.thor);

        superheroArrayList.add(batman);
        superheroArrayList.add(ironman);
        superheroArrayList.add(spiderman);
        superheroArrayList.add(superman);
        superheroArrayList.add(thor);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SuperheroAdapter superheroAdapter = new SuperheroAdapter(superheroArrayList);
        binding.recyclerView.setAdapter(superheroAdapter);
    }
}