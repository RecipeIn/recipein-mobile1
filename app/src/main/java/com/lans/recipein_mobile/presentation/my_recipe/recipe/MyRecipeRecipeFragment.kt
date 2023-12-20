package com.lans.recipein_mobile.presentation.my_recipe.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.lans.recipein_mobile.databinding.FragmentMyrecipeRecipeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyRecipeRecipeFragment : Fragment() {
    private lateinit var binding: FragmentMyrecipeRecipeBinding
    private val viewModel: MyRecipeRecipeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMyrecipeRecipeBinding.inflate(layoutInflater)
        return binding.root
    }
}