package com.lans.recipein_mobile.presentation.status_resep.kirim

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.lans.recipein_mobile.databinding.FragmentKirimBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KirimFragment : Fragment() {
    private lateinit var binding: FragmentKirimBinding
    private val viewModel: KirimViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentKirimBinding.inflate(layoutInflater)
        return binding.root
    }

}