package com.lans.recipein_mobile.presentation.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.ChipGroup
import com.lans.recipein_mobile.databinding.FragmentRecipeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeFragment : Fragment() {
    private lateinit var binding: FragmentRecipeBinding
    private val viewModel: RecipeViewModel by viewModels()

    private lateinit var svRecipe: SearchView
    private lateinit var tvAdvancedSearch: TextView
    private lateinit var cgCategory: ChipGroup
    private lateinit var rvRecipe: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentRecipeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeComponent()
    }

    private fun initializeComponent() {
        svRecipe = binding.svRecipe
        tvAdvancedSearch = binding.tvAdvancedSearch
        cgCategory = binding.cgCategory
        rvRecipe = binding.rvRecipe
    }
}
