package com.lans.recipein_mobile.presentation.recipe.recipe_page

import android.annotation.SuppressLint
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
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.snackbar.Snackbar
import com.lans.recipein_mobile.R
import com.lans.recipein_mobile.databinding.FragmentRecipePageBinding
import com.lans.recipein_mobile.presentation.MainActivity
import com.lans.recipein_mobile.presentation.adapter.IngredientAdapter
import com.lans.recipein_mobile.presentation.adapter.InstructionAdapter
import com.lans.recipein_mobile.presentation.adapter.NutritionAdapter
import com.lans.recipein_mobile.utils.SpacesItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecipePageFragment : Fragment(), OnClickListener {
    private lateinit var binding: FragmentRecipePageBinding
    private val viewModel: RecipePageViewModel by viewModels()
    private val args: RecipePageFragmentArgs by navArgs()

    private lateinit var rvIngredient: RecyclerView
    private lateinit var rvInstruction: RecyclerView
    private lateinit var rvNutrision: RecyclerView
    private lateinit var ingredientAdapter: IngredientAdapter
    private lateinit var instructionAdapter: InstructionAdapter
    private lateinit var nutritionAdapter: NutritionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentRecipePageBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (args.recipeId != 0) {
            viewModel.loadRecipe(args.recipeId)
        }
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
        rvIngredient = binding.rvIngredients
        rvIngredient.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rvIngredient.addItemDecoration(SpacesItemDecoration(10))
        rvInstruction = binding.rvInstructions
        rvInstruction.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rvInstruction.addItemDecoration(SpacesItemDecoration(10))
        rvNutrision = binding.rvNutrisions
        rvNutrision.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rvNutrision.addItemDecoration(SpacesItemDecoration(10))
        binding.btnBack.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    private suspend fun observe() {
        viewModel.state.collect { result ->
            (requireActivity() as MainActivity).showLoading(result.isLoading)

            val recipe = result.recipe
            if (recipe != null) {
                val favorite = result.favorite
                if (favorite != null) {
                    recipe.isLiked = favorite.contains(recipe.id)
                }

                if (recipe.image != null) {
                    binding.ivRecipePage.load(recipe.image)
                } else {
                    binding.ivRecipePage.load(R.drawable.img_background1)
                }

                if (recipe.isLiked) {
                    binding.btnFavorite.setImageResource(R.drawable.ic_favorite)
                } else {
                    binding.btnFavorite.setImageResource(R.drawable.ic_love)
                }

                binding.btnFavorite.setOnClickListener {
                    viewModel.insertFavorite(recipe.id)
                }

                binding.tvTitleRecipePage.text = recipe.name
                binding.tvAuthorRecipePage.text = "Resep oleh ${recipe.username}"
                binding.tvDescRecipePage.text = recipe.description
                binding.rbRecipePage.rating = recipe.rating
                binding.tvRatingCount.text = recipe.rating.toString()
                binding.tvTimePreparation.text = "Persiapan ${recipe.preparationTime} menit"
                binding.tvTimeCooking.text = "Memasak ${recipe.cookingTime} menit"
                binding.tvTimeTotal.text =
                    "Total ${recipe.preparationTime * recipe.cookingTime} menit"

                if (result.recipeIngredients.isNotEmpty()) {
                    val ingredients = result.recipeIngredients.filter { ingredient ->
                        ingredient.recipeId == recipe.id
                    }
                    ingredientAdapter = IngredientAdapter(ingredients)
                    rvIngredient.adapter = ingredientAdapter
                }

                if (result.recipeInstructions.isNotEmpty()) {
                    val instructions = result.recipeInstructions.filter { instruction ->
                        instruction.recipeId == recipe.id
                    }
                    instructionAdapter = InstructionAdapter(instructions)
                    rvInstruction.adapter = instructionAdapter
                }

                if (result.recipeNutrisions.isNotEmpty()) {
                    val nutritions = result.recipeNutrisions.filter { nutrition ->
                        nutrition.recipeId == recipe.id
                    }
                    nutritionAdapter = NutritionAdapter(nutritions)
                    rvNutrision.adapter = nutritionAdapter
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
}