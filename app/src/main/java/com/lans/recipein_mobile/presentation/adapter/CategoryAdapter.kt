package com.lans.recipein_mobile.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.lans.recipein_mobile.databinding.ItemCategoryBinding
import com.lans.recipein_mobile.domain.model.Category

class CategoryAdapter(val list: List<Category>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    private lateinit var listener: AdapterListener

    interface AdapterListener {
        fun onClick(position: Int)
    }

    fun setItemClick(listener: AdapterListener) {
        this.listener = listener
    }

    inner class ViewHolder(val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                listener.onClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (_, name, image) = list[position]
        if (image != null) {
            holder.binding.ivCategory.load(image)
        }
        holder.binding.tvCategory.text = name
    }

    override fun getItemCount(): Int {
        return list.size
    }
}