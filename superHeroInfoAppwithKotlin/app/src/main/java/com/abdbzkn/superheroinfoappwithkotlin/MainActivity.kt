package com.abdbzkn.superheroinfoappwithkotlin
//veriyi hem intent ile hem singleton ile aktardım intenti yorum satırına aldım
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.abdbzkn.superheroinfoappwithkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var superheroList : ArrayList<Superhero>

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        superheroList = ArrayList<Superhero>()
        //Data
        val batman = Superhero("Batman","Gotham City",R.drawable.batman)
        val superman = Superhero("Superman","Kansas City",R.drawable.superman)
        val ironman = Superhero("Ironman","Manhattan / New York",R.drawable.ironman)
        val spiderman = Superhero("Spiderman","Queens / New York",R.drawable.spiderman)
        val thor = Superhero("Thor","Asgard",R.drawable.thor)

        //val batmanBitmap = BitmapFactory.decodeResource(resources,R.drawable.batman)

        superheroList.add(batman)
        superheroList.add(superman)
        superheroList.add(ironman)
        superheroList.add(spiderman)
        superheroList.add(thor)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val superheroAdapter = SuperheroAdapter(superheroList)
        binding.recyclerView.adapter = superheroAdapter
/*
        //Adapter : Layout yani xml ile data yı birbirine bağlar
        //Mapping veriyi benzetmeye yarayan yapı
        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,superheroList.map{superhero -> superhero.name })
        binding.listView.adapter = adapter
        binding.listView.onItemClickListener = AdapterView.OnItemClickListener{parent, view, position, id ->
           val intent = Intent(MainActivity@this,DetailsActivity::class.java)
            intent.putExtra("superhero",superheroList.get(position))
            startActivity(intent)
        }
 */
    }
}