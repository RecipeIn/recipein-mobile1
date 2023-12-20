package com.lans.recipein_mobile.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lans.recipein_mobile.databinding.ItemInstructionBinding
import com.lans.recipein_mobile.domain.model.RecipeInstruction

class InstructionAdapter(private val list: List<RecipeInstruction>) :
    RecyclerView.Adapter<InstructionAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemInstructionBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemInstructionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.rvInstruction.text = list[position].instructionDescription
    }

    override fun getItemCount(): Int {
        return list.size
    }
}