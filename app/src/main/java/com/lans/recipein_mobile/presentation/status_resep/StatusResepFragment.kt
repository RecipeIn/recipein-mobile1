package com.lans.recipein_mobile.presentation.status_resep

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.lans.recipein_mobile.databinding.FragmentStatusResepBinding
import com.lans.recipein_mobile.presentation.adapter.FavoritePagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StatusResepFragment : Fragment() {
    private lateinit var binding: FragmentStatusResepBinding
    private val viewModel: StatusResepViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentStatusResepBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTabLayout()
    }

    private fun setupTabLayout() {
        binding.vpStatusResep.adapter = FavoritePagerAdapter(childFragmentManager, 5)
        binding.vpStatusResep.offscreenPageLimit = 5
        binding.tabStatusResep.setupWithViewPager(binding.vpStatusResep)
    }
}