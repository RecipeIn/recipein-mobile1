package com.lans.recipein_mobile.presentation.new_password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.textfield.TextInputLayout
import com.lans.recipein_mobile.R
import com.lans.recipein_mobile.databinding.FragmentNewPasswordBinding
import com.lans.recipein_mobile.presentation.get_started.GetStartedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewPasswordFragment : Fragment(), OnClickListener {
    private lateinit var binding: FragmentNewPasswordBinding
    private val viewModel: GetStartedViewModel by viewModels()

    private lateinit var passwordLayout: TextInputLayout
    private lateinit var confirmPasswordLayout: TextInputLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentNewPasswordBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeComponent()
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnSend -> {

            }
        }
    }

    private fun initializeComponent() {
        passwordLayout = binding.etPasswordLayout
        confirmPasswordLayout = binding.etConfirmPasswordLayout
        binding.btnSave.setOnClickListener(this)
    }
}