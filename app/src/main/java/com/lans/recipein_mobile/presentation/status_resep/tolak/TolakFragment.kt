package com.lans.recipein_mobile.presentation.status_resep.tolak

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.lans.recipein_mobile.R
import com.lans.recipein_mobile.databinding.FragmentFolderBinding
import com.lans.recipein_mobile.databinding.FragmentTolakBinding
import com.lans.recipein_mobile.presentation.favorite.folder.FolderViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TolakFragment : Fragment() {
    private lateinit var binding: FragmentTolakBinding
    private val viewModel: TolakViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentTolakBinding.inflate(layoutInflater)
        return binding.root
    }

}