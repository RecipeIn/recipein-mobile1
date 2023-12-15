package com.lans.recipein_mobile.presentation.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.ChipGroup
import com.lans.recipein_mobile.R
import com.lans.recipein_mobile.databinding.FragmentRecipeBinding
import com.lans.recipein_mobile.domain.model.Recipe
import com.lans.recipein_mobile.presentation.adapter.RecipeAdapter
import com.lans.recipein_mobile.utils.SpacesItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeFragment : Fragment() {
    private lateinit var binding: FragmentRecipeBinding
    private val viewModel: RecipeViewModel by viewModels()

    private lateinit var svRecipe: SearchView
    private lateinit var tvAdvancedSearch: TextView
    private lateinit var cgCategory: ChipGroup
    private lateinit var rvRecipe: RecyclerView

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
        setupRecyclerView()
    }

    private fun initializeComponent() {
        svRecipe = binding.svRecipe
        tvAdvancedSearch = binding.tvAdvancedSearch
        cgCategory = binding.cgCategory
        rvRecipe = binding.rvRecipe

    }

    private fun setupRecyclerView() {
        binding.rvRecipe.layoutManager = LinearLayoutManager(requireContext())
        binding.rvRecipe.addItemDecoration(SpacesItemDecoration(20))
        val dataList: MutableList<Recipe> = mutableListOf()
        val title: Array<String> = resources.getStringArray(R.array.tittle_recipe_page)
        val category: Array<String> = resources.getStringArray(R.array.category_recipe_page)
        val author: Array<String> = resources.getStringArray(R.array.author_recipe_page)
        val time: Array<String> = resources.getStringArray(R.array.time_recipe_page)
        val desc: Array<String> = resources.getStringArray(R.array.desc_recipe_page)

        val imageRecipe: List<Int> = listOf(
            R.drawable.img_recipe_page_1,
            R.drawable.img_recipe_page_2,
            R.drawable.img_recipe_page_3,
            R.drawable.img_recipe_page_4,
        )

        title.forEachIndexed{ index, name ->
            dataList.add(
                Recipe(
                    imageRecipe[index],
                    category[index],
                    name,
                    desc[index],
                    author[index],
                    time[index],
                )
            )
        }
        val adapter = RecipeAdapter(dataList)
        binding.rvRecipe.adapter = adapter
    }
}
