package com.lans.recipein_mobile.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lans.recipein_mobile.databinding.ItemCarouselBinding
import com.lans.recipein_mobile.domain.model.Carousel

class CarouselAdapter(
    private val items: List<Carousel>
): RecyclerView.Adapter<CarouselAdapter.ImageViewHolder>() {

    inner class ImageViewHolder(itemView: ItemCarouselBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        private val binding = itemView
        fun bind(data: Carousel) {
            with(binding) {
                Glide.with(itemView)
                    .load(data.imageUrl)
                    .into(ivCarousel)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            ItemCarouselBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}