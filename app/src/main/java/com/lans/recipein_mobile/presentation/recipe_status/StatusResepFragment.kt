package com.lans.recipein_mobile.presentation.recipe_status

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.lans.recipein_mobile.databinding.FragmentRecipeStatusBinding
import com.lans.recipein_mobile.presentation.adapter.FavoritePagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StatusResepFragment : Fragment() {
    private lateinit var binding: FragmentRecipeStatusBinding
    private val viewModel: StatusResepViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentRecipeStatusBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTabLayout()
    }

    private fun setupTabLayout() {
        binding.vpRecipeStatus.adapter = FavoritePagerAdapter(childFragmentManager, 5)
        binding.vpRecipeStatus.offscreenPageLimit = 5
        binding.tabRecipeStatus.setupWithViewPager(binding.vpRecipeStatus)
    }
}