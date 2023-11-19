package com.lans.recipein_mobile.presentation.konfirmasicode

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lans.recipein_mobile.R

class KonfirmasiCodeFragment : Fragment() {

    companion object {
        fun newInstance() = KonfirmasiCodeFragment()
    }

    private lateinit var viewModel: KonfirmasiCodeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_konfirmasi_code, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(KonfirmasiCodeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}