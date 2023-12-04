package com.lans.recipein_mobile.presentation.recipe.edit_recipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.lans.recipein_mobile.R
import com.lans.recipein_mobile.databinding.FragmentEditRecipeBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class EditRecipeFragment : Fragment() {
    private lateinit var binding: FragmentEditRecipeBinding
    private val viewModel: EditRecipeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditRecipeBinding.inflate(layoutInflater)
        return binding.root
    }


}