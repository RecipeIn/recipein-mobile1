package com.lans.recipein_mobile.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lans.recipein_mobile.databinding.ItemRecipeBinding
import com.lans.recipein_mobile.domain.model.Recipe

class RecipeAdapter(private var list: List<Recipe>) :
    RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemRecipeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRecipeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = list[position]
        holder.binding.tvRecipeName.text = recipe.name
        holder.binding.tvRecipeDesc.text = recipe.description
        holder.binding.ratingRecipe.rating = recipe.rating
        holder.binding.tvRating.text = "${recipe.rating.toString().removeSuffix(".0")}/5"
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filter(filterList: List<Recipe>) {
        list = filterList
        notifyDataSetChanged()
    }

}