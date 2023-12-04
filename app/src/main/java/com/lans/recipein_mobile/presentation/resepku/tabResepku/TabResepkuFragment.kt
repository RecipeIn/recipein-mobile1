package com.lans.recipein_mobile.presentation.resepku.tabResepku

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.lans.recipein_mobile.databinding.FragmentTabResepkuBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TabResepkuFragment : Fragment() {
    private lateinit var binding: FragmentTabResepkuBinding
    private val viewModel: TabResepkuViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentTabResepkuBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}