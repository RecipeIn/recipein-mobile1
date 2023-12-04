package com.lans.recipein_mobile.presentation.recipe.recipepage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.lans.recipein_mobile.R
import com.lans.recipein_mobile.databinding.FragmentRecipeBinding
import com.lans.recipein_mobile.databinding.FragmentRecipePageBinding
import com.lans.recipein_mobile.presentation.recipe.RecipeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipePageFragment : Fragment() {
    private lateinit var binding: FragmentRecipePageBinding
    private val viewModel: RecipePageViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipePageBinding.inflate(layoutInflater)
        return binding.root
    }


}