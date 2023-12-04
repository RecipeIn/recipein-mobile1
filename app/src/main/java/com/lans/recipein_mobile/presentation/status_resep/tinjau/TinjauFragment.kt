package com.lans.recipein_mobile.presentation.status_resep.tinjau

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.lans.recipein_mobile.R
import com.lans.recipein_mobile.databinding.FragmentFolderBinding
import com.lans.recipein_mobile.databinding.FragmentTinjauBinding
import com.lans.recipein_mobile.presentation.favorite.folder.FolderViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TinjauFragment : Fragment() {
    private lateinit var binding: FragmentTinjauBinding
    private val viewModel: TinjauViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentTinjauBinding.inflate(layoutInflater)
        return binding.root
    }

}