package com.lans.recipein_mobile.presentation.my_recipe.folder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.lans.recipein_mobile.databinding.FragmentMyrecipeFolderBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyRecipeFolderFragment : Fragment() {
    private lateinit var binding: FragmentMyrecipeFolderBinding
    private val viewModel: MyRecipeFolderViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMyrecipeFolderBinding.inflate(layoutInflater)
        return binding.root
    }
}