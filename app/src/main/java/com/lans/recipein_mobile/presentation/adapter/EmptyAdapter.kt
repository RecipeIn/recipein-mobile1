package com.lans.recipein_mobile.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lans.recipein_mobile.databinding.ItemEmptyBinding

class EmptyAdapter :
    RecyclerView.Adapter<EmptyAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemEmptyBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemEmptyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvEmpty.text = "Empty"
    }

    override fun getItemCount(): Int = 1
}