package com.lans.recipein_mobile.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lans.recipein_mobile.databinding.ItemCarouselBinding

class CarouselAdapter(
    private val items: List<Int>,
) : RecyclerView.Adapter<CarouselAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemCarouselBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Int) {
            with(binding) {
                Glide.with(itemView)
                    .load(data)
                    .into(ivCarousel)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCarouselBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}