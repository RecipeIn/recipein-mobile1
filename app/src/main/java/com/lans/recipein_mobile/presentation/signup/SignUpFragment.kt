package com.lans.recipein_mobile.presentation.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.lans.recipein_mobile.R
import com.lans.recipein_mobile.databinding.FragmentSignUpBinding
import com.lans.recipein_mobile.presentation.MainActivity
import com.lans.recipein_mobile.utils.hideKeyboard
import com.lans.recipein_mobile.utils.safeNavigate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignUpFragment : Fragment(), OnClickListener {
    private lateinit var binding: FragmentSignUpBinding
    private val viewModel: SignUpViewModel by viewModels()

    private lateinit var usernameLayout: TextInputLayout
    private lateinit var emailLayout: TextInputLayout
    private lateinit var passwordLayout: TextInputLayout
    private lateinit var confirmPasswordLayout: TextInputLayout
    private lateinit var cbTerms: MaterialCheckBox

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSignUpBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeComponent()

        lifecycleScope.launch {
            observeSignUp()
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnSignUp -> {
                lifecycleScope.launch {
                    val username = usernameLayout.editText!!.text
                    val email = emailLayout.editText!!.text
                    val password = passwordLayout.editText!!.text
                    val confirmPassword = confirmPasswordLayout.editText!!.text

                    val isUsernameValid = viewModel.validateUsername(username.toString())
                    val isEmailValid = viewModel.validateEmail(email.toString())
                    val isPasswordValid = viewModel.validatePassword(password.toString())
                    val isConfirmPassword = viewModel.validateConfirmPassword(
                        password.toString(),
                        confirmPassword.toString()
                    )

                    if (isEmailValid && isUsernameValid && isPasswordValid && isConfirmPassword) {
                        viewModel.signup(email.toString(), username.toString(), password.toString())
                    }
                }
            }

            R.id.tvSignIn -> {
                val action = SignUpFragmentDirections.actionSignUpFragmentToSignInFragment()
                findNavController().navigate(action)
            }
        }
    }

    private fun initializeComponent() {
        usernameLayout = binding.etUsernameLayout
        emailLayout = binding.etEmailLayout
        passwordLayout = binding.etPasswordLayout
        confirmPasswordLayout = binding.etConfirmPasswordLayout
        cbTerms = binding.cbAccTerms
        binding.btnSignUp.setOnClickListener(this)
        binding.tvSignIn.setOnClickListener(this)
    }

    private suspend fun observeSignUp() {
        viewModel.state.collect { result ->
            (requireActivity() as MainActivity).showLoading(result.isLoading)

            if (result.isSuccess) {
                val action = SignUpFragmentDirections.actionSignUpFragmentToSignInFragment()
                findNavController().safeNavigate(action)
                requireActivity().hideKeyboard(binding.root)
            }

            if (result.error.isNotBlank()) {
                Snackbar.make(
                    binding.root,
                    result.error,
                    Snackbar.LENGTH_SHORT
                ).show()
            }

            if (!result.usernameError.isNullOrBlank()) {
                usernameLayout.error = result.usernameError
            } else {
                usernameLayout.error = null
            }

            if (!result.emailError.isNullOrBlank()) {
                emailLayout.error = result.emailError
            } else {
                emailLayout.error = null
            }

            if (!result.passwordError.isNullOrBlank()) {
                passwordLayout.error = result.passwordError
            } else {
                passwordLayout.error = null
            }

            if (!result.confirmPasswordError.isNullOrBlank()) {
                confirmPasswordLayout.error = result.confirmPasswordError
            } else {
                confirmPasswordLayout.error = null
            }
        }
    }
}