package com.lans.recipein_mobile.presentation.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.lans.recipein_mobile.R
import com.lans.recipein_mobile.databinding.FragmentSignInBinding
import com.lans.recipein_mobile.presentation.MainActivity
import com.lans.recipein_mobile.utils.hideKeyboard
import com.lans.recipein_mobile.utils.safeNavigate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignInFragment : Fragment(), OnClickListener {
    private lateinit var binding: FragmentSignInBinding
    private val viewModel: SignInViewModel by viewModels()

    private lateinit var emailLayout: TextInputLayout
    private lateinit var passwordLayout: TextInputLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSignInBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeComponent()

        lifecycleScope.launch {
            observeSignIn()
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnSignIn -> {
                lifecycleScope.launch {
                    val email = emailLayout.editText!!.text
                    val password = passwordLayout.editText!!.text

                    val isEmailValid = viewModel.validateEmail(email.toString())
                    val isPasswordValid = viewModel.validatePassword(password.toString())

                    if (isEmailValid && isPasswordValid) {
                        viewModel.signin(email.toString(), password.toString())
                    }
                }
            }

            R.id.tvForgotPassword -> {
                val action = SignInFragmentDirections.actionSignInFragmentToForgotPasswordFragment()
                findNavController().safeNavigate(action)
            }

            R.id.tvSignUp -> {
                val action = SignInFragmentDirections.actionSignInFragmentToSignUpFragment()
                findNavController().safeNavigate(action)
            }
        }
    }

    private fun initializeComponent() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack()
                }
            })
        emailLayout = binding.etEmailLayout
        passwordLayout = binding.etPasswordLayout
        binding.btnSignIn.setOnClickListener(this)
        binding.tvForgotPassword.setOnClickListener(this)
        binding.tvSignUp.setOnClickListener(this)
    }

    private suspend fun observeSignIn() {
        viewModel.state.collect { result ->
            (requireActivity() as MainActivity).showLoading(result.isLoading)

            if (result.isLoggedIn) {
                requireActivity().hideKeyboard(binding.root)
            }

            if (result.error.isNotBlank()) {
                Snackbar.make(
                    binding.root,
                    result.error,
                    Snackbar.LENGTH_SHORT
                ).show()
                result.error = ""
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
        }
    }
}