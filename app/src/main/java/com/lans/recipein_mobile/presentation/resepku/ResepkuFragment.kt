package com.lans.recipein_mobile.presentation.resepku

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.lans.recipein_mobile.databinding.FragmentResepkuBinding
import com.lans.recipein_mobile.presentation.adapter.FavoritePagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResepkuFragment : Fragment() {
    private lateinit var binding: FragmentResepkuBinding
    private val viewModel: ResepkuViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentResepkuBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTabLayout()
    }

    private fun setupTabLayout() {
        binding.vpResepku.adapter = FavoritePagerAdapter(childFragmentManager, 2)
        binding.vpResepku.offscreenPageLimit = 2
        binding.tabResepku.setupWithViewPager(binding.vpResepku)
    }
}