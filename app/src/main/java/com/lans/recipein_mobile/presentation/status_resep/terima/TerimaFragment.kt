package com.lans.recipein_mobile.presentation.status_resep.terima

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.lans.recipein_mobile.databinding.FragmentTerimaBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TerimaFragment : Fragment() {
    private lateinit var binding: FragmentTerimaBinding
    private val viewModel: TerimaViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentTerimaBinding.inflate(layoutInflater)
        return binding.root
    }

}