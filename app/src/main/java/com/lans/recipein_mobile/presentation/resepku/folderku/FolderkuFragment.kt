package com.lans.recipein_mobile.presentation.resepku.folderku

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.lans.recipein_mobile.databinding.FragmentFolderkuBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FolderkuFragment : Fragment() {
    private lateinit var binding: FragmentFolderkuBinding
    private val viewModel: FolderkuViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFolderkuBinding.inflate(layoutInflater)
        return binding.root
    }

}