package com.lans.recipein_mobile.presentation.recipe.recipe_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.lans.recipein_mobile.databinding.FragmentRecipePageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipePageFragment : Fragment() {
    private lateinit var binding: FragmentRecipePageBinding
    private val viewModel: RecipePageViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentRecipePageBinding.inflate(layoutInflater)
        return binding.root
    }
}