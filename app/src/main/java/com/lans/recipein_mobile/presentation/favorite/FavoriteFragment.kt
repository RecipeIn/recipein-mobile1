package com.lans.recipein_mobile.presentation.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lans.recipein_mobile.R
import com.lans.recipein_mobile.databinding.FragmentFavoriteBinding
import com.lans.recipein_mobile.presentation.adapter.FavoritePagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupTabLayout()
    }

    private fun setupTabLayout() {
        binding.viewPager.adapter = FavoritePagerAdapter(childFragmentManager,2)
        binding.viewPager.offscreenPageLimit = 2
        binding.tabs.setupWithViewPager(binding.viewPager)
    }
}