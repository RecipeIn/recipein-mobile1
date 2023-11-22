package com.lans.recipein_mobile.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lans.recipein_mobile.databinding.ItemCategoryBinding
import com.lans.recipein_mobile.databinding.ItemWeeklyRekomBinding
import com.lans.recipein_mobile.domain.model.Category
import com.lans.recipein_mobile.domain.model.RecipeRecomendation

class RecomendationRecipeAdapter(private val list: List<RecipeRecomendation>): RecyclerView.Adapter<RecomendationRecipeAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemWeeklyRekomBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemWeeklyRekomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (image, category, recipe_tittle, author, time,) = list[position]
        holder.binding.imgRecipeRekom.setImageResource(image)
        holder.binding.tvCategory.text = category
        holder.binding.tvRecipeName.text = recipe_tittle
        holder.binding.tvAuthor.text = author
        holder.binding.tvTime.text = time
    }
}