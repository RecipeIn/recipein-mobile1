package com.lans.recipein_mobile.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lans.recipein_mobile.databinding.ItemRecipeCollectionBinding
import com.lans.recipein_mobile.databinding.ListMaterialBinding
import com.lans.recipein_mobile.domain.model.CollectionRecipe
import com.lans.recipein_mobile.domain.model.FoodMaterial

class RecipePageAdapter(
    private val list: List<FoodMaterial>,
) : RecyclerView.Adapter<RecipePageAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ListMaterialBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListMaterialBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (name, quantity) = list[position]
        holder.binding.tvTittle.text = name
        holder.binding.tvQuantity.text = quantity
    }

    override fun getItemCount(): Int {
        return list.size
    }
}