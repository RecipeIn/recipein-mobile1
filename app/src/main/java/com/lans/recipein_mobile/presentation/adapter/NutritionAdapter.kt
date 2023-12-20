package com.lans.recipein_mobile.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lans.recipein_mobile.databinding.ItemNutritionBinding
import com.lans.recipein_mobile.domain.model.RecipeNutrition

class NutritionAdapter(private val list: List<RecipeNutrition>) :
    RecyclerView.Adapter<NutritionAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemNutritionBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNutritionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipeNutrition = list[position]
        holder.binding.tvNutritionName.text = recipeNutrition.nutritionDescription
        holder.binding.tvNutritionQty.text = recipeNutrition.qty.toString()
    }

    override fun getItemCount(): Int {
        return list.size
    }
}