package com.lans.recipein_mobile.presentation.verification_code

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.lans.recipein_mobile.R
import com.lans.recipein_mobile.databinding.FragmentVerificationCodeBinding
import com.lans.recipein_mobile.presentation.signin.SignInFragmentDirections

class VerificationCodeFragment : Fragment() {
    private lateinit var binding: FragmentVerificationCodeBinding
    private val viewModel: VerificationCodeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVerificationCodeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}