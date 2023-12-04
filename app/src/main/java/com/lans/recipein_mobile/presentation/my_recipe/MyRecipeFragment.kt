package com.lans.recipein_mobile.presentation.my_recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.lans.recipein_mobile.databinding.FragmentMyrecipeBinding
import com.lans.recipein_mobile.presentation.adapter.FavoritePagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyRecipeFragment : Fragment() {
    private lateinit var binding: FragmentMyrecipeBinding
    private val viewModel: MyRecipeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMyrecipeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTabLayout()
    }

    private fun setupTabLayout() {
        binding.vpRecipe.adapter = FavoritePagerAdapter(childFragmentManager, 2)
        binding.vpRecipe.offscreenPageLimit = 2
        binding.tabRecipe.setupWithViewPager(binding.vpRecipe)
    }
}