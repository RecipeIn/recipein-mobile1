package com.lans.recipein_mobile.presentation.new_password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.lans.recipein_mobile.databinding.FragmentNewPasswordBinding
import com.lans.recipein_mobile.presentation.get_started.GetStartedViewModel

class NewPasswordFragment : Fragment() {
    private lateinit var binding: FragmentNewPasswordBinding
    private val viewModel: GetStartedViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewPasswordBinding.inflate(layoutInflater)
        return binding.root
    }
}