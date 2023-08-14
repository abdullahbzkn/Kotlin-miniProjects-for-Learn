package com.abdbzkn.superheroinfoappwithjava;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.abdbzkn.superheroinfoappwithjava.databinding.RecyclerRowBinding;
import java.util.ArrayList;
public class SuperheroAdapter extends RecyclerView.Adapter<SuperheroAdapter.SuperheroHolder> {
    ArrayList<Superhero> superheroArrayList;
    public SuperheroAdapter(ArrayList<Superhero> superheroArrayList) {
        this.superheroArrayList = superheroArrayList;
    }
    public class SuperheroHolder extends RecyclerView.ViewHolder{
        private RecyclerRowBinding binding;
        public SuperheroHolder(RecyclerRowBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public SuperheroHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerRowBinding recyclerRowBinding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new SuperheroHolder(recyclerRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull SuperheroHolder holder, int position) {
        holder.binding.recyclerViewTextView.setText(superheroArrayList.get(position).name);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(),DetailsActivity.class);
                intent.putExtra("superhero",superheroArrayList.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return superheroArrayList.size();
    }
}