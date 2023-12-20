package com.lans.recipein_mobile.presentation.advanced_search

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import com.lans.recipein_mobile.R
import com.lans.recipein_mobile.databinding.FragmentAdvancedSearchBinding
import com.lans.recipein_mobile.utils.safeNavigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdvancedSearchFragment : Fragment(), OnClickListener {
    private lateinit var binding: FragmentAdvancedSearchBinding
    private val viewModel: AdvancedSearchViewModel by viewModels()

    companion object {
        var recipeName = ""
        var includeIngredients = mutableListOf<String>()
        var excludeIngredients = mutableListOf<String>()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAdvancedSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeComponent()
    }

    override fun onResume() {
        super.onResume()
        recipeName = ""
        includeIngredients.clear()
        excludeIngredients.clear()
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnBack -> {
                findNavController().popBackStack()
            }

            R.id.btnSearch -> {
                if (binding.etMenuName.text.isNotBlank() && includeIngredients.isNotEmpty() || excludeIngredients.isNotEmpty()) {
                    recipeName = binding.etMenuName.text.toString()
                    val action =
                        AdvancedSearchFragmentDirections.actionAdvancedSearchFragmentToAdvancedSearchResultFragment()
                    findNavController().safeNavigate(action)
                }
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
        binding.etInclude.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                val trimmed = s.toString().trim { it <= ' ' }
                if (trimmed.length > 1 && trimmed.endsWith(",")) {
                    val chip = Chip(requireActivity())
                    val text = trimmed.substring(0, trimmed.length - 1)
                    includeIngredients.add(text)
                    chip.text = text
                    chip.isCloseIconVisible = true
                    chip.setOnCloseIconClickListener {
                        binding.cgInclude.removeView(chip)
                    }
                    binding.cgInclude.addView(chip)
                    s?.clear()
                }
            }

        })
        binding.etInclude.setOnKeyListener { _, _, event ->
            if (event != null && event.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_DEL) {
                if (binding.etInclude.length() == 0 && binding.cgInclude.childCount > 0) {
                    val index = binding.cgInclude.childCount - 1
                    val chip =
                        binding.cgInclude.getChildAt(index) as Chip
                    includeIngredients.removeAt(index)
                    binding.cgInclude.removeView(chip)
                }
            }
            false
        }
        binding.etExclude.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            @SuppressLint("ResourceAsColor")
            override fun afterTextChanged(s: Editable?) {
                val trimmed = s.toString().trim { it <= ' ' }
                if (trimmed.length > 1 && trimmed.endsWith(",")) {
                    val chip = Chip(requireActivity())
                    val text = trimmed.substring(0, trimmed.length - 1)
                    excludeIngredients.add(text)
                    chip.text = text
                    chip.setTextColor(R.color.white)
                    chip.setChipBackgroundColorResource(R.color.error)
                    chip.isCloseIconVisible = true
                    chip.setOnCloseIconClickListener {
                        binding.cgExclude.removeView(chip)
                    }
                    binding.cgExclude.addView(chip)
                    s?.clear()
                }
            }

        })
        binding.etExclude.setOnKeyListener { _, _, event ->
            if (event != null && event.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_DEL) {
                if (binding.etExclude.length() == 0 && binding.cgExclude.childCount > 0) {
                    val index = binding.cgExclude.childCount - 1
                    val chip =
                        binding.cgExclude.getChildAt(index) as Chip
                    excludeIngredients.removeAt(index)
                    binding.cgExclude.removeView(chip)
                }
            }
            false
        }
        binding.btnBack.setOnClickListener(this)
        binding.btnSearch.setOnClickListener(this)
    }
}