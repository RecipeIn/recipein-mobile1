package com.lans.recipein_mobile.presentation.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.snackbar.Snackbar
import com.lans.recipein_mobile.R
import com.lans.recipein_mobile.databinding.FragmentHomeBinding
import com.lans.recipein_mobile.presentation.MainActivity
import com.lans.recipein_mobile.presentation.adapter.CarouselAdapter
import com.lans.recipein_mobile.presentation.adapter.CategoryAdapter
import com.lans.recipein_mobile.presentation.adapter.EmptyAdapter
import com.lans.recipein_mobile.presentation.adapter.RecommendationRecipeAdapter
import com.lans.recipein_mobile.utils.SpacesItemDecoration
import com.lans.recipein_mobile.utils.safeNavigate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    private lateinit var adapter: CarouselAdapter
    private lateinit var dots: ArrayList<TextView>
    private lateinit var rvCategory: RecyclerView
    private lateinit var rvFoodRecommendation: RecyclerView
    private lateinit var rvDrinkRecommendation: RecyclerView

    //    private lateinit var rvRecipeCollection: RecyclerView
    private lateinit var rvNewRecipe: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeComponent()
        setupCarousel()
        lifecycleScope.launch {
            observe()
        }
    }

    private fun initializeComponent() {
        rvCategory = binding.rvCategory
        rvCategory.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvCategory.addItemDecoration(SpacesItemDecoration(6))
        rvFoodRecommendation = binding.rvFoodRecommendation
        rvFoodRecommendation.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvFoodRecommendation.addItemDecoration(SpacesItemDecoration(6))
        rvDrinkRecommendation = binding.rvDrinkRecommendation
        rvDrinkRecommendation.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvDrinkRecommendation.addItemDecoration(SpacesItemDecoration(6))
//        rvRecipeCollection = binding.rvCollection
//        rvRecipeCollection.layoutManager =
//            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
//        rvRecipeCollection.addItemDecoration(SpacesItemDecoration(6))
        rvNewRecipe = binding.rvNewRecipe
        rvNewRecipe.addItemDecoration(SpacesItemDecoration(6))
    }

    @Suppress("NAME_SHADOWING")
    private fun setupCarousel() {
        val imageList: MutableList<Int> = mutableListOf()
        val image: List<Int> = listOf(
            R.drawable.image_carousel_1,
            R.drawable.image_carousel_2,
            R.drawable.image_carousel_3,
        )

        image.forEachIndexed { _, image ->
            imageList.add(image)
        }

        adapter = CarouselAdapter(imageList)
        binding.vpCarousel.adapter = adapter
        dots = ArrayList()
        setIndicator()
        binding.vpCarousel.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                selectedDot(position)
                super.onPageSelected(position)
            }
        })
    }

    private fun selectedDot(position: Int) {
        for (i in 1..2) {
            if (1 == position)
                dots[i].setTextColor(ContextCompat.getColor(requireActivity(), R.color.neutral50))
            else
                dots[i].setTextColor(ContextCompat.getColor(requireActivity(), R.color.secondary70))
        }
    }

    private fun setIndicator() {
        for (i in 0..2) {
            dots.add(TextView(requireContext()))
            dots[i].text = Html.fromHtml("&#9879", Html.FROM_HTML_MODE_LEGACY).toString()
            dots[i].textSize = 18f
            binding.dotsIndicator.addView(dots[i])
        }
    }

    @SuppressLint("SetTextI18n")
    private suspend fun observe() {
        viewModel.state.collect { result ->
            (requireActivity() as MainActivity).showLoading(result.isLoading)

            if (result.name.isNotBlank()) {
                binding.tvGreeting.text = "Hai, ${result.name}"
            }

            if (result.categories.isNotEmpty()) {
                val categories = result.categories
                val adapter = CategoryAdapter(categories)
                adapter.setItemClick(object : CategoryAdapter.AdapterListener {
                    override fun onClick(position: Int) {
                        val action =
                            HomeFragmentDirections.actionHomeFragmentToCategoryFragment(
                                adapter.list[position].id
                            )
                        findNavController().safeNavigate(action)
                    }
                })
                rvCategory.adapter = adapter
            } else {
                rvCategory.adapter = EmptyAdapter()
            }

            if (result.weeklyFoods.isNotEmpty()) {
                val weeklyFoods = result.weeklyFoods
                weeklyFoods.map { recipe ->
                    recipe.isLiked = result.favorite.contains(recipe.id)
                }
                val adapter = RecommendationRecipeAdapter(weeklyFoods)
                adapter.setItemClick(object : RecommendationRecipeAdapter.AdapterListener {
                    override fun onCardClick(position: Int) {
                        val action =
                            HomeFragmentDirections.actionHomeFragmentToRecipePageFragment(
                                adapter.list[position].id
                            )
                        findNavController().safeNavigate(action)
                    }

                    override fun onFavoriteClick(position: Int) {
                        viewModel.insertFavorite(adapter.list[position].id)
                    }
                })
                rvFoodRecommendation.adapter = adapter
            } else {
                rvFoodRecommendation.adapter = EmptyAdapter()
            }

            if (result.weeklyDrinks.isNotEmpty()) {
                val weeklyDrinks = result.weeklyDrinks
                weeklyDrinks.map { recipe ->
                    recipe.isLiked = result.favorite.contains(recipe.id)
                }
                val adapter = RecommendationRecipeAdapter(weeklyDrinks)
                adapter.setItemClick(object : RecommendationRecipeAdapter.AdapterListener {
                    override fun onCardClick(position: Int) {
                        val action =
                            HomeFragmentDirections.actionHomeFragmentToRecipePageFragment(
                                adapter.list[position].id
                            )
                        findNavController().safeNavigate(action)
                    }

                    override fun onFavoriteClick(position: Int) {
                        viewModel.insertFavorite(adapter.list[position].id)
                    }
                })
                rvDrinkRecommendation.adapter = adapter
            } else {
                rvDrinkRecommendation.adapter = EmptyAdapter()
            }

//            if (result.recipeCollections.isNotEmpty()) {
//
//            } else {
//                rvRecipeCollection.adapter = EmptyAdapter()
//            }

            if (result.newRecipes.isNotEmpty()) {
                val newRecipes = result.newRecipes.take(4)
                newRecipes.map { recipe ->
                    recipe.isLiked = result.favorite.contains(recipe.id)
                }
                val adapter = RecommendationRecipeAdapter(newRecipes)
                adapter.setItemClick(object : RecommendationRecipeAdapter.AdapterListener {
                    override fun onCardClick(position: Int) {
                        val action =
                            HomeFragmentDirections.actionHomeFragmentToRecipePageFragment(
                                adapter.list[position].id
                            )
                        findNavController().safeNavigate(action)
                    }

                    override fun onFavoriteClick(position: Int) {
                        viewModel.insertFavorite(adapter.list[position].id)
                    }
                })
                rvNewRecipe.layoutManager = GridLayoutManager(requireContext(), 2)
                rvNewRecipe.adapter = adapter
            } else {
                rvNewRecipe.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                rvNewRecipe.adapter = EmptyAdapter()
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