package com.lans.recipein_mobile.presentation.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.lans.recipein_mobile.R
import com.lans.recipein_mobile.databinding.FragmentSignUpBinding
import com.lans.recipein_mobile.presentation.signin.SignInFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment(), OnClickListener {
    private lateinit var binding: FragmentSignUpBinding
    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeComponent()
    }

    override fun onClick(v: View?) {
        when(v!!.id) {
            R.id.btnSignIn -> {

            }
            R.id.tvSignIn -> {
                val action = SignUpFragmentDirections.actionSignUpFragmentToSignInFragment()
                findNavController().navigate(action)
            }
        }
    }

    private fun initializeComponent() {
        binding.btnSignUp.setOnClickListener(this)
        binding.tvSignIn.setOnClickListener(this)
    }
}