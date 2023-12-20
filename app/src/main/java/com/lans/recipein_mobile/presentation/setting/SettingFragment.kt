package com.lans.recipein_mobile.presentation.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.lans.recipein_mobile.R
import com.lans.recipein_mobile.databinding.FragmentSettingBinding
import com.lans.recipein_mobile.utils.safeNavigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : Fragment(), OnClickListener {
    private lateinit var binding: FragmentSettingBinding
    private val viewModel: SettingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSettingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeComponent()
    }

    private fun initializeComponent() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack()
                }
            })
        binding.btnBack.setOnClickListener(this)
        binding.tvMyProfile.setOnClickListener(this)
        binding.tvChangePassword.setOnClickListener(this)
        binding.tvLanguage.setOnClickListener(this)
        binding.tvAboutUs.setOnClickListener(this)
        binding.tvTermsOfService.setOnClickListener(this)
        binding.tvPrivacyPolicy.setOnClickListener(this)
        binding.tvContact.setOnClickListener(this)
        binding.tvFAQ.setOnClickListener(this)
        binding.tvLogout.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnBack -> {
                findNavController().popBackStack()
            }

            R.id.tvMyProfile -> {
                findNavController().popBackStack()
            }

            R.id.tvChangePassword -> {
                val action =
                    SettingFragmentDirections.actionSettingFragmentToChangePasswordFragment()
                findNavController().safeNavigate(action)
            }

            R.id.tvLogout -> {
                viewModel.signOut()
            }
        }
    }
}