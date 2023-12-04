package com.lans.recipein_mobile.presentation.advanced_search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.lans.recipein_mobile.databinding.FragmentAdvancedSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdvancedSearchFragment : Fragment() {
    private lateinit var binding: FragmentAdvancedSearchBinding
    private val viewModel: AdvancedSearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return binding.root
    }
}