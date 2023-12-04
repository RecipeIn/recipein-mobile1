package com.lans.recipein_mobile.presentation.weekly_menu

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lans.recipein_mobile.R

class WeeklyMenuFragment : Fragment() {

    companion object {
        fun newInstance() = WeeklyMenuFragment()
    }

    private lateinit var viewModel: WeeklyMenuViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weekly_menu, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(WeeklyMenuViewModel::class.java)
        // TODO: Use the ViewModel
    }

}