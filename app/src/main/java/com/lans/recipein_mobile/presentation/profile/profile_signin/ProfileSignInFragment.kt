package com.lans.recipein_mobile.presentation.profile.profile_signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.lans.recipein_mobile.R
import com.lans.recipein_mobile.databinding.FragmentProfileSigninBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileSignInFragment : Fragment(), OnClickListener {
    private lateinit var binding: FragmentProfileSigninBinding
    private val viewModel: ProfileSignInViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentProfileSigninBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeComponent()
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnSetting -> {
                val action =
                    ProfileSignInFragmentDirections.actionProfileSignInFragmentSettingFragment()
                findNavController().navigate(action)
            }
        }
    }

    private fun initializeComponent() {
        binding.btnSetting.setOnClickListener(this)
    }
}