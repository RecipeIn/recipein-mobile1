package com.lans.recipein_mobile.presentation.advanced_search.result

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar
import com.lans.recipein_mobile.R
import com.lans.recipein_mobile.databinding.FragmentAdvancedSearchResultBinding
import com.lans.recipein_mobile.presentation.MainActivity
import com.lans.recipein_mobile.presentation.adapter.EmptyAdapter
import com.lans.recipein_mobile.presentation.adapter.RecipeAdapter
import com.lans.recipein_mobile.presentation.advanced_search.AdvancedSearchFragment.Companion.excludeIngredients
import com.lans.recipein_mobile.presentation.advanced_search.AdvancedSearchFragment.Companion.includeIngredients
import com.lans.recipein_mobile.presentation.advanced_search.AdvancedSearchFragment.Companion.recipeName
import com.lans.recipein_mobile.utils.SpacesItemDecoration
import com.lans.recipein_mobile.utils.safeNavigate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Locale

@AndroidEntryPoint
class AdvancedSearchResultFragment : Fragment(), OnClickListener {
    private lateinit var binding: FragmentAdvancedSearchResultBinding
    private val viewModel: AdvancedSearchResultViewModel by viewModels()

    private lateinit var rvSearch: RecyclerView
    private lateinit var recipeAdapter: RecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAdvancedSearchResultBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeComponent()
        lifecycleScope.launch {
            observe()
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnBack -> {
                findNavController().popBackStack()
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
        rvSearch = binding.rvSearch
        rvSearch.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rvSearch.addItemDecoration(SpacesItemDecoration(20))
        binding.btnBack.setOnClickListener(this)
    }

    @SuppressLint("ResourceAsColor")
    private suspend fun observe() {
        viewModel.state.collect { result ->
            (requireActivity() as MainActivity).showLoading(result.isLoading)

            if (result.recipes.isNotEmpty()) {
                val filteredIncludeIngredient =
                    result.recipeIngredients.filter { it.ingredientName in includeIngredients }

                val filteredExcludeIngredient =
                    result.recipeIngredients.filter { it.ingredientName in excludeIngredients }

                val filteredRecipes = result.recipes.filter { recipe ->
                    recipe.name.lowercase(Locale.getDefault()).contains(
                        recipeName.lowercase(
                            Locale.getDefault()
                        )
                    )
                }

                val includeFilteredRecipes = if (includeIngredients.isNotEmpty()) {
                    filteredRecipes.filter { recipe ->
                        recipe.id in filteredIncludeIngredient.map { it.recipeId }
                    }
                } else {
                    filteredRecipes
                }

                val excludeFilteredRecipes = if (excludeIngredients.isNotEmpty()) {
                    includeFilteredRecipes.filterNot { recipe ->
                        recipe.id in filteredExcludeIngredient.map { it.recipeId }
                    }
                } else {
                    includeFilteredRecipes
                }

                includeIngredients.forEach { ingredient ->
                    binding.cgSearch.removeAllViews()
                    val chip = Chip(requireActivity())
                    chip.text = ingredient
                    chip.isCloseIconVisible = true
                    chip.setOnCloseIconClickListener {
                        binding.cgSearch.removeView(chip)
                    }
                    binding.cgSearch.addView(chip)
                }

                excludeIngredients.forEach { ingredient ->
                    val chip = Chip(requireActivity())
                    chip.text = ingredient
                    chip.setTextColor(R.color.white)
                    chip.setChipBackgroundColorResource(R.color.error)
                    chip.isCloseIconVisible = true
                    chip.setOnCloseIconClickListener {
                        binding.cgSearch.removeView(chip)
                    }
                    binding.cgSearch.addView(chip)
                }

                recipeAdapter = RecipeAdapter(excludeFilteredRecipes)
                recipeAdapter.setItemClick(object : RecipeAdapter.AdapterListener {
                    override fun onCardClick(position: Int) {
                        val action =
                            AdvancedSearchResultFragmentDirections.actionAdvancedSearchResultFragmentToRecipePageFragment(
                                recipeAdapter.list[position].id
                            )
                        findNavController().safeNavigate(action)
                    }

                    override fun onOptionClick(position: Int) {
                        val builder: AlertDialog.Builder = AlertDialog.Builder(requireActivity())
                        builder.setTitle(recipeName)
                            .setItems(arrayOf("Favorite", "Share")) { dialog, which ->

                            }
                        val dialog: AlertDialog = builder.create()
                        dialog.show()
                    }
                })
                rvSearch.adapter = recipeAdapter
            } else {
                rvSearch.adapter = EmptyAdapter()
            }

            if (result.error.isNotBlank()) {
                Snackbar.make(
                    binding.root,
                    result.error,
                    Snackbar.LENGTH_SHORT
                ).show()
                result.error = ""
            }
        }
    }
}