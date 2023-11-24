package com.lans.recipein_mobile.presentation.profile_signout

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lans.recipein_mobile.R

class Profile_SignoutFragment : Fragment() {

    companion object {
        fun newInstance() = Profile_SignoutFragment()
    }

    private lateinit var viewModel: ProfileSignoutViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile__signout, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileSignoutViewModel::class.java)
        // TODO: Use the ViewModel
    }

}