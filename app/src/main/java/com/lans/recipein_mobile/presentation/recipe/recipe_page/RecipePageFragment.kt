package com.lans.recipein_mobile.presentation.recipe.recipe_page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lans.recipein_mobile.R
import com.lans.recipein_mobile.databinding.FragmentRecipePageBinding
import com.lans.recipein_mobile.domain.model.FoodMaterial
import com.lans.recipein_mobile.domain.model.Nutrition
import com.lans.recipein_mobile.domain.model.Recipe
import com.lans.recipein_mobile.presentation.adapter.NutritionAdapter
import com.lans.recipein_mobile.presentation.adapter.RecipePageAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipePageFragment : Fragment() {
    private lateinit var binding: FragmentRecipePageBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentRecipePageBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupDetailData()
        setupIngredientsRecyclerView()
        setupNutritionRecyclerView()
    }

    private fun setupDetailData() {
        val data = arguments?.getParcelable<Recipe>("recipe_data")
        data.let {
            binding.ivRecipePage.setImageResource(it!!.image)
            binding.tvTittleRecipePage.text = it.recipe_tittle
            binding.tvAuthorRecipePage.text= it.author
            binding.tvDescRecipePage.text = it.description
            binding.tvSteps.text = it.steps
        }

        binding.btnBack.setOnClickListener {
            val bundle = Bundle()
            findNavController().navigate(R.id.action_recipePageFragment_to_recipeFragment, bundle)
        }
    }

    private fun setupIngredientsRecyclerView() {
        val ingredients = arguments?.getParcelableArrayList<FoodMaterial>("ingredients_data")

        val ingredientsAdapter = RecipePageAdapter(ingredients!!)
        binding.rvIngredients.adapter = ingredientsAdapter
        binding.rvIngredients.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setupNutritionRecyclerView() {
        val nutrition = arguments?.getParcelableArrayList<Nutrition>("nutrition_data")

        val ingredientsAdapter = NutritionAdapter(nutrition!!)
        binding.listNutrition.adapter = ingredientsAdapter
        binding.listNutrition.layoutManager = LinearLayoutManager(requireContext())
    }
}