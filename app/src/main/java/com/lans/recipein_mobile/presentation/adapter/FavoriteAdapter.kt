package com.lans.recipein_mobile.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.lans.recipein_mobile.R
import com.lans.recipein_mobile.databinding.ItemFavoriteBinding
import com.lans.recipein_mobile.domain.model.Recipe

class FavoriteAdapter(
    var list: List<Recipe>,
) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {
    private lateinit var listener: AdapterListener

    interface AdapterListener {
        fun onClick(position: Int)
    }

    fun setItemClick(listener: AdapterListener) {
        this.listener = listener
    }

    inner class ViewHolder(val binding: ItemFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                listener.onClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemFavoriteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = list[position]
        if (recipe.image != null) {
            holder.binding.imgRecipeRecommendation.load(recipe.image)
        } else {
            holder.binding.imgRecipeRecommendation.load(R.drawable.img_background1)
        }
        holder.binding.tvCategory.text = recipe.category!!.name
        holder.binding.tvRecipeName.text = recipe.name
        holder.binding.tvName.text = recipe.username
        holder.binding.tvTime.text = "${recipe.preparationTime + recipe.cookingTime} Menit"
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