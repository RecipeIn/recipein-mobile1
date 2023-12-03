package com.lans.recipein_mobile.presentation.verification_code

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.textfield.TextInputLayout
import com.lans.recipein_mobile.R
import com.lans.recipein_mobile.databinding.FragmentVerificationCodeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VerificationCodeFragment : Fragment(), OnClickListener {
    private lateinit var binding: FragmentVerificationCodeBinding
    private val viewModel: VerificationCodeViewModel by viewModels()

    private lateinit var verificationCodeLayout: TextInputLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentVerificationCodeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeComponent()
    }

    override fun onClick(v: View?) {
        when(v!!.id) {
            R.id.btnSend -> {

            }
        }
    }

    private fun initializeComponent() {
        verificationCodeLayout = binding.etVerificationCodeLayout
        binding.btnSend.setOnClickListener(this)
    }
}