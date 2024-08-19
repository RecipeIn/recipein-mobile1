package com.lans.recipein_mobile.presentation.recipe

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.snackbar.Snackbar
import com.lans.recipein_mobile.R
import com.lans.recipein_mobile.databinding.FragmentRecipeBinding
import com.lans.recipein_mobile.domain.model.Recipe
import com.lans.recipein_mobile.presentation.MainActivity
import com.lans.recipein_mobile.presentation.adapter.EmptyAdapter
import com.lans.recipein_mobile.presentation.adapter.RecipeAdapter
import com.lans.recipein_mobile.utils.SpacesItemDecoration
import com.lans.recipein_mobile.utils.safeNavigate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Locale

@AndroidEntryPoint
class RecipeFragment : Fragment(), OnClickListener {
    private lateinit var binding: FragmentRecipeBinding
    private val viewModel: RecipeViewModel by viewModels()

    private var recipeData = emptyList<Recipe>()
    private lateinit var svRecipe: SearchView
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

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.tvAdvancedSearch -> {
                val action = RecipeFragmentDirections.actionRecipeFragmentToAdvancedSearchFragment()
                findNavController().safeNavigate(action)
            }
        }
    }

    private fun initializeComponent() {
        svRecipe = binding.svRecipe
        svRecipe.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(text: String?): Boolean {
                try {
                    if (!text.isNullOrBlank()) {
                        filter(text)
                    } else {
                        recipeAdapter.filter(recipeData)
                    }
                } catch (ex: Exception) {
                    return false
                }
                return false
            }
        })
        cgCategory = binding.cgCategory
        cgCategory.setOnCheckedStateChangeListener { _, checkedIds ->
            if (checkedIds.isNotEmpty()) {
                viewModel.loadRecipesByCategoryId(checkedIds[0])
            } else {
                viewModel.loadRecipes()
            }
        }
        rvRecipe = binding.rvRecipe
        rvRecipe.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rvRecipe.addItemDecoration(SpacesItemDecoration(20))
        binding.tvAdvancedSearch.setOnClickListener(this)
    }

    private suspend fun observe() {
        viewModel.state.collect { result ->
            (requireActivity() as MainActivity).showLoading(result.isLoading)

            if (result.recipes.isNotEmpty()) {
                recipeData = result.recipes
                recipeAdapter = RecipeAdapter(recipeData)
                recipeAdapter.setItemClick(object : RecipeAdapter.AdapterListener {
                    override fun onCardClick(position: Int) {
                        val action =
                            RecipeFragmentDirections.actionRecipeFragmentToRecipePageFragment(
                                recipeAdapter.list[position].id
                            )
                        findNavController().safeNavigate(action)
                    }

                    override fun onOptionClick(position: Int) {
                        val builder: AlertDialog.Builder = AlertDialog.Builder(requireActivity())
                        builder.setTitle(recipeData[position].name)
                            .setItems(
                                arrayOf("Favorite", "Share")
                            ) { _, which ->
                                when (which) {
                                    0 -> {
                                        viewModel.insertFavorite(recipeAdapter.list[position].id)
                                    }
                                }
                            }
                        val dialog: AlertDialog = builder.create()
                        dialog.show()
                    }
                })
                rvRecipe.adapter = recipeAdapter
            } else {
                rvRecipe.adapter = EmptyAdapter()
            }

            if (result.categories.isNotEmpty()) {
                if (cgCategory.childCount == 0) {
                    result.categories.forEach { data ->
                        val chip = Chip(requireActivity())
                        chip.id = data.id
                        chip.text = data.name
                        chip.isCheckable = true
                        chip.isFocusable = true
                        chip.isClickable = true
                        cgCategory.addView(chip)
                    }
                }
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
