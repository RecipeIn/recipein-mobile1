package com.lans.recipein_mobile.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lans.recipein_mobile.databinding.ItemRecipeBinding
import com.lans.recipein_mobile.domain.model.Recipe

class RecipeAdapter(private val list: List<Recipe>) :
    RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemRecipeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeAdapter.ViewHolder {
        return ViewHolder(
            ItemRecipeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecipeAdapter.ViewHolder, position: Int) {
        val (image, category, recipeTitle, desc, author, time) = list[position]
        holder.binding.ivRecipe.setImageResource(image)
        holder.binding.tvCategory.text = category
        holder.binding.tvRecipeName.text = recipeTitle
        holder.binding.tvRecipeDesc.text = desc
        holder.binding.tvName.text = author
        holder.binding.tvTime.text = time
    }

    override fun getItemCount(): Int {
        return list.size
    }
}