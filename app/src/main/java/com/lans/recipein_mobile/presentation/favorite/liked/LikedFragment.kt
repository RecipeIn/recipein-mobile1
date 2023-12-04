package com.lans.recipein_mobile.presentation.favorite.liked

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.lans.recipein_mobile.databinding.FragmentFavoriteLikedBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LikedFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteLikedBinding
    private val viewModel: LikedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFavoriteLikedBinding.inflate(layoutInflater)
        return binding.root
    }
}