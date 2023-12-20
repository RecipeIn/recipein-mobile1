package com.lans.recipein_mobile.presentation.get_started

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.lans.recipein_mobile.R
import com.lans.recipein_mobile.databinding.FragmentGetStartedBinding
import com.lans.recipein_mobile.utils.safeNavigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GetStartedFragment : Fragment(), OnClickListener {
    private lateinit var binding: FragmentGetStartedBinding
    private val viewModel: GetStartedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentGetStartedBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeComponent()
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnSignIn -> {
                val action = GetStartedFragmentDirections.actionGetStartedFragmentToSignInFragment()
                findNavController().safeNavigate(action)
            }

            R.id.btnContinueGuest -> {
                val action = GetStartedFragmentDirections.actionGetStartedFragmentToHomeFragment()
                findNavController().safeNavigate(action)
            }
        }
    }

    private fun initializeComponent() {
        binding.btnSignIn.setOnClickListener(this)
        binding.btnContinueGuest.setOnClickListener(this)
    }
}