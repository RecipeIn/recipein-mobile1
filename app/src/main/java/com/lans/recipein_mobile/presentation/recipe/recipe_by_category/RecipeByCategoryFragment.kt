package com.lans.recipein_mobile.presentation.recipe.recipe_by_category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.lans.recipein_mobile.R
import com.lans.recipein_mobile.databinding.FragmentRecipeByCategoryBinding
import com.lans.recipein_mobile.databinding.FragmentRecipePageBinding
import com.lans.recipein_mobile.presentation.recipe.recipepage.RecipePageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeByCategoryFragment : Fragment() {

    private lateinit var binding: FragmentRecipeByCategoryBinding
    private val viewModel: RecipeByCategoryViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipeByCategoryBinding.inflate(layoutInflater)
        return binding.root
    }

}