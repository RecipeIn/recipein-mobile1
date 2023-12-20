package com.lans.recipein_mobile.presentation.recipe.add_recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.lans.recipein_mobile.databinding.FragmentRecipeAddBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddRecipeFragment : Fragment() {
    private lateinit var binding: FragmentRecipeAddBinding
    private val viewodel: AddRecipeViewodel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentRecipeAddBinding.inflate(layoutInflater)
        return binding.root
    }
}