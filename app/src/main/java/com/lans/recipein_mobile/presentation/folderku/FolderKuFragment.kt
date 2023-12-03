package com.lans.recipein_mobile.presentation.folderku

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lans.recipein_mobile.R

class FolderKuFragment : Fragment() {

    companion object {
        fun newInstance() = FolderKuFragment()
    }

    private lateinit var viewModel: FolderKuViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_folder_ku, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FolderKuViewModel::class.java)
        // TODO: Use the ViewModel
    }

}