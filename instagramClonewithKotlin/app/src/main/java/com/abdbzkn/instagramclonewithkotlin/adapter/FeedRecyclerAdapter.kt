package com.abdbzkn.instagramclonewithkotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abdbzkn.instagramclonewithkotlin.databinding.RecyclerRowBinding
import com.abdbzkn.instagramclonewithkotlin.model.Posts
import com.squareup.picasso.Picasso

class FeedRecyclerAdapter(private val postList: ArrayList<Posts>) : RecyclerView.Adapter<FeedRecyclerAdapter.PostHolder>(){
    class PostHolder(val binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PostHolder(binding)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        holder.binding.recyclerEmailText.text = postList.get(position).email
        holder.binding.recyclerCommentText.text = postList.get(position).comment
        Picasso.get().load(postList.get(position).downloadUrl).into(holder.binding.recyclerImageView)
    }
}