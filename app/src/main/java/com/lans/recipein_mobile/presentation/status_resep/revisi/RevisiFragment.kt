package com.lans.recipein_mobile.presentation.status_resep.revisi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.lans.recipein_mobile.databinding.FragmentRevisiBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RevisiFragment : Fragment() {
    private lateinit var binding: FragmentRevisiBinding
    private val viewModel: RevisiViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentRevisiBinding.inflate(layoutInflater)
        return binding.root
    }

}