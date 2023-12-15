package com.lans.recipein_mobile.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lans.recipein_mobile.databinding.ItemWeeklyRecommendationBinding
import com.lans.recipein_mobile.domain.model.Recipe

class RecommendationRecipeAdapter(
    private val list: List<Recipe>,
) : RecyclerView.Adapter<RecommendationRecipeAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemWeeklyRecommendationBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemWeeklyRecommendationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = list[position]
        holder.binding.tvRecipeName.text = recipe.name
        holder.binding.ratingRecipe.rating = recipe.rating
    }

    override fun getItemCount(): Int {
        return list.size
    }
}