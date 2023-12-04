package com.lans.recipein_mobile.presentation.profile.show_profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.lans.recipein_mobile.databinding.FragmentShowProfileBinding

class ShowProfile : Fragment() {
    private lateinit var binding: FragmentShowProfileBinding
    private val viewModel: ShowProfileViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return binding.root
    }
}