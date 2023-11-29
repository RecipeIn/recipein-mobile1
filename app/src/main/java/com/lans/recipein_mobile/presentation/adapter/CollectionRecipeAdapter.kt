package com.lans.recipein_mobile.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lans.recipein_mobile.databinding.ItemRecipeCollectionBinding
import com.lans.recipein_mobile.domain.model.CollectionRecipe

class CollectionRecipeAdapter(
    private val list: List<CollectionRecipe>,
) : RecyclerView.Adapter<CollectionRecipeAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemRecipeCollectionBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRecipeCollectionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (image, title, description, author) = list[position]
        holder.binding.ivCollectionRecipe.setImageResource(image)
        holder.binding.tvCollectionTitle.text = title
        holder.binding.tvCollectionDesc.text = description
        holder.binding.tvName.text = author
    }

    override fun getItemCount(): Int {
        return list.size
    }
}