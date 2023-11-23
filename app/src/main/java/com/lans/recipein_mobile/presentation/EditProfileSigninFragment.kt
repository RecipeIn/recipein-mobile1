package com.lans.recipein_mobile.presentation

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lans.recipein_mobile.R

class EditProfileSigninFragment : Fragment() {

    companion object {
        fun newInstance() = EditProfileSigninFragment()
    }

    private lateinit var viewModel: EditProfileSigninViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_profile_signin, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EditProfileSigninViewModel::class.java)
        // TODO: Use the ViewModel
    }

}