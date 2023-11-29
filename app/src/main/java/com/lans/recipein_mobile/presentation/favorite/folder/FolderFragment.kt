package com.lans.recipein_mobile.presentation.favorite.folder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.lans.recipein_mobile.databinding.FragmentFolderBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FolderFragment : Fragment() {
    private lateinit var binding: FragmentFolderBinding
    private val viewModel: FolderViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFolderBinding.inflate(layoutInflater)
        return binding.root
    }

}