package com.lans.recipein_mobile.presentation.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.ChipGroup
import com.google.android.material.snackbar.Snackbar
import com.lans.recipein_mobile.databinding.FragmentRecipeBinding
import com.lans.recipein_mobile.domain.model.Recipe
import com.lans.recipein_mobile.presentation.MainActivity
import com.lans.recipein_mobile.presentation.adapter.EmptyAdapter
import com.lans.recipein_mobile.presentation.adapter.RecipeAdapter
import com.lans.recipein_mobile.utils.SpacesItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Locale

@AndroidEntryPoint
class RecipeFragment : Fragment() {
    private lateinit var binding: FragmentRecipeBinding
    private val viewModel: RecipeViewModel by viewModels()

    private var recipeData = emptyList<Recipe>()
    private lateinit var svRecipe: SearchView
    private lateinit var tvAdvancedSearch: TextView
    private lateinit var cgCategory: ChipGroup
    private lateinit var rvRecipe: RecyclerView
    private lateinit var recipeAdapter: RecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentRecipeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeComponent()
        lifecycleScope.launch {
            observe()
        }
    }

    private fun initializeComponent() {
        svRecipe = binding.svRecipe
        svRecipe.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(text: String?): Boolean {
                if (!text.isNullOrBlank()) {
                    filter(text)
                } else {
                    recipeAdapter.filter(recipeData)
                }
                return false
            }
        })
        tvAdvancedSearch = binding.tvAdvancedSearch
        cgCategory = binding.cgCategory
        rvRecipe = binding.rvRecipe
        rvRecipe.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rvRecipe.addItemDecoration(SpacesItemDecoration(20))
    }

    private suspend fun observe() {
        viewModel.state.collect { result ->
            (requireActivity() as MainActivity).showLoading(result.isLoading)

            if (result.recipes.isNotEmpty()) {
                recipeData = result.recipes
                recipeAdapter = RecipeAdapter(recipeData)
                rvRecipe.adapter = recipeAdapter
            } else {
                rvRecipe.adapter = EmptyAdapter()
            }

            if (result.error.isNotBlank()) {
                Snackbar.make(
                    binding.root,
                    result.error,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun filter(recipe: String) {
        val filter = recipeData.filter {
            it.name.lowercase(Locale.getDefault()).contains(
                recipe.lowercase(
                    Locale.getDefault()
                )
            )
        }
        recipeAdapter.filter(filter)
    }
}
