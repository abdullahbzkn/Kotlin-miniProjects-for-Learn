package com.abdbzkn.superheroinfoappwithkotlin

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abdbzkn.superheroinfoappwithkotlin.databinding.RecyclerRowBinding

class SuperheroAdapter (val superheroList : ArrayList<Superhero>): RecyclerView.Adapter<SuperheroAdapter.SuperheroHolder>() {
    class SuperheroHolder(val binding : RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root){
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperheroHolder {
       //layout ile bağlama viewBinding kullanarak
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SuperheroHolder(binding)
    }
    override fun getItemCount(): Int {
       //kaç tane olucak
        return superheroList.size
    }
    override fun onBindViewHolder(holder: SuperheroHolder, position: Int) {
        //baplandıktan sonra ne olucak
        holder.binding.recyclerViewTextView.text = superheroList.get(position).name

        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context,DetailsActivity::class.java)
            MySingleton.chosenSuperhero = superheroList.get(position)
            //intent.putExtra("superhero", superheroList.get(position)) intent ile göndermiycem
            holder.itemView.context.startActivity(intent)
        }
    }
}