package com.lans.recipein_mobile.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lans.recipein_mobile.databinding.ItemWeeklyRecommendationBinding
import com.lans.recipein_mobile.domain.model.RecipeRecomendation

class RecommendationRecipeAdapter(
    private val list: List<RecipeRecomendation>
) : RecyclerView.Adapter<RecommendationRecipeAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemWeeklyRecommendationBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemWeeklyRecommendationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (image, category, recipeTitle, author, time) = list[position]
        holder.binding.imgRecipeRecommendation.setImageResource(image)
        holder.binding.tvCategory.text = category
        holder.binding.tvRecipeName.text = recipeTitle
        holder.binding.tvAuthor.text = author
        holder.binding.tvTime.text = time
    }

    override fun getItemCount(): Int {
        return list.size
    }
}