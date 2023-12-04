package com.lans.recipein_mobile.presentation.my_folder

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lans.recipein_mobile.R

class MyFolderFragment : Fragment() {

    companion object {
        fun newInstance() = MyFolderFragment()
    }

    private lateinit var viewModel: MyFolderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_myfolder, container, false)
    }
}