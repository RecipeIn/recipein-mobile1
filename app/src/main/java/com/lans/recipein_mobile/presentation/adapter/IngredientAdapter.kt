package com.lans.recipein_mobile.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lans.recipein_mobile.databinding.ItemIngredientBinding
import com.lans.recipein_mobile.domain.model.RecipeIngredient

class IngredientAdapter(private val list: List<RecipeIngredient>) :
    RecyclerView.Adapter<IngredientAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemIngredientBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemIngredientBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipeIngredient = list[position]
        holder.binding.tvIngredientName.text = recipeIngredient.ingredientName
        holder.binding.tvIngredientQty.text = "${recipeIngredient.qty} ${recipeIngredient.unitName}"
    }

    override fun getItemCount(): Int {
        return list.size
    }
}