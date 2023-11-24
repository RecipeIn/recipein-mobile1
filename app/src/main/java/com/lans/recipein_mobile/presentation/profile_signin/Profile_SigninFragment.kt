package com.lans.recipein_mobile.presentation.profile_signin

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lans.recipein_mobile.R

class Profile_SigninFragment : Fragment() {

    companion object {
        fun newInstance() = Profile_SigninFragment()
    }

    private lateinit var viewModel: ProfileSigninViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile__signin, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileSigninViewModel::class.java)
        // TODO: Use the ViewModel
    }

}