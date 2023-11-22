package com.lans.recipein_mobile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lans.recipein_mobile.databinding.ItemRecipeCollectionBinding
import com.lans.recipein_mobile.databinding.ItemWeeklyRekomBinding
import com.lans.recipein_mobile.domain.model.CollectionRecipe
import com.lans.recipein_mobile.domain.model.RecipeRecomendation

class CollectionRecipeAdapter(private val list: List<CollectionRecipe>): RecyclerView.Adapter<CollectionRecipeAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemRecipeCollectionBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecipeCollectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CollectionRecipeAdapter.ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (image, title, description, author) = list[position]
        holder.binding.imgCollectionRecipe.setImageResource(image)
        holder.binding.tvTittleCollection.text = title
        holder.binding.tvDescriptionCollection.text = description
        holder.binding.tvAuthorCollection.text = author
    }
}