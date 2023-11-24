package com.lans.recipein_mobile.presentation.home

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.lans.recipein_mobile.R
import com.lans.recipein_mobile.databinding.FragmentHomeBinding
import com.lans.recipein_mobile.domain.model.Carousel
import com.lans.recipein_mobile.domain.model.Category
import com.lans.recipein_mobile.domain.model.CollectionRecipe
import com.lans.recipein_mobile.domain.model.RecipeRecomendation
import com.lans.recipein_mobile.presentation.adapter.CarouselAdapter
import com.lans.recipein_mobile.presentation.adapter.CategoryAdapter
import com.lans.recipein_mobile.presentation.adapter.CollectionRecipeAdapter
import com.lans.recipein_mobile.presentation.adapter.RecommendationRecipeAdapter
import com.lans.recipein_mobile.utils.SpacesItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), OnClickListener {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    private lateinit var rvFoodRecommendation: RecyclerView
    private lateinit var rvDrinkRecommendation: RecyclerView
    private lateinit var rvRecipeCollection: RecyclerView
    private lateinit var rvNewRecipe: RecyclerView
    private lateinit var dots: ArrayList<TextView>
    private lateinit var adapter: CarouselAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeComponent()
        setupCarousel()
        setupCategory()
        foodRecommendation()
        drinkRecommendation()
        recipeCollection()
        newRecipe()
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
        }
    }

    private fun initializeComponent() {
        rvFoodRecommendation = binding.rvFoodRecommendation
        rvFoodRecommendation.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvFoodRecommendation.addItemDecoration(SpacesItemDecoration(6))
        rvDrinkRecommendation = binding.rvDrinkRecommendation
        rvDrinkRecommendation.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvDrinkRecommendation.addItemDecoration(SpacesItemDecoration(6))
        rvRecipeCollection = binding.rvCollection
        rvRecipeCollection.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvRecipeCollection.addItemDecoration(SpacesItemDecoration(6))
        rvNewRecipe = binding.rvNewRecipe
        rvNewRecipe.layoutManager = GridLayoutManager(requireContext(), 2)
        rvNewRecipe.addItemDecoration(SpacesItemDecoration(6))
    }

    private fun foodRecommendation() {
        val dataList: MutableList<RecipeRecomendation> = mutableListOf()
        val titleRecipe: Array<String> = resources.getStringArray(R.array.title_recipe)
        val category: Array<String> = resources.getStringArray(R.array.category)
        val author: Array<String> = resources.getStringArray(R.array.author)
        val time: Array<String> = resources.getStringArray(R.array.time)

        val imageFood: List<Int> = listOf(
            R.drawable.img_weekly_recomen_1,
            R.drawable.img_weekly_recomen_2,
            R.drawable.img_weekly_recomen_3,
            R.drawable.img_weekly_recomen_4,
        )

        titleRecipe.forEachIndexed { index, name ->
            dataList.add(
                RecipeRecomendation(
                    imageFood[index],
                    name,
                    category[index],
                    author[index],
                    time[index]
                )
            )
        }

        val adapter = RecommendationRecipeAdapter(dataList)
        binding.rvFoodRecommendation.adapter = adapter
    }

    private fun drinkRecommendation() {
        val dataList: MutableList<RecipeRecomendation> = mutableListOf()
        val titleRecipe: Array<String> = resources.getStringArray(R.array.title_recipe_drink)
        val category: Array<String> = resources.getStringArray(R.array.category_drink)
        val author: Array<String> = resources.getStringArray(R.array.author_drink)
        val time: Array<String> = resources.getStringArray(R.array.time_drink)

        val imageDrink: List<Int> = listOf(
            R.drawable.img_drink_recomen_1,
            R.drawable.img_drink_recomen_2,
            R.drawable.img_drink_recomen_3,
            R.drawable.img_drink_recomen_4,
        )

        titleRecipe.forEachIndexed { index, name ->
            dataList.add(
                RecipeRecomendation(
                    imageDrink[index],
                    name,
                    category[index],
                    author[index],
                    time[index]
                )
            )
        }

        val adapter = RecommendationRecipeAdapter(dataList)
        binding.rvDrinkRecommendation.adapter = adapter
    }

    private fun recipeCollection() {
        val dataList: MutableList<CollectionRecipe> = mutableListOf()
        val title: Array<String> = resources.getStringArray(R.array.title_recipe_colection)
        val desc: Array<String> = resources.getStringArray(R.array.description_collection)
        val author: Array<String> = resources.getStringArray(R.array.author_collection)

        val imageCollection: List<Int> = listOf(
            R.drawable.img_collection_1,
            R.drawable.img_collection_2,
        )

        title.forEachIndexed { index, name ->
            dataList.add(CollectionRecipe(imageCollection[index], name, desc[index], author[index]))
        }

        val adapter = CollectionRecipeAdapter(dataList)
        binding.rvCollection.adapter = adapter
    }

    private fun newRecipe() {
        val dataList: MutableList<RecipeRecomendation> = mutableListOf()
        val titleRecipe: Array<String> = resources.getStringArray(R.array.title_new_recipe)
        val category: Array<String> = resources.getStringArray(R.array.category_new_recipe)
        val author: Array<String> = resources.getStringArray(R.array.author_new_recipe)
        val time: Array<String> = resources.getStringArray(R.array.time_new_recipe)

        val imageCollection: List<Int> = listOf(
            R.drawable.img_new_recipe_1,
            R.drawable.img_new_recipe_2,
            R.drawable.img_new_recipe_3,
            R.drawable.img_new_recipe_4,
        )

        titleRecipe.forEachIndexed { index, name ->
            dataList.add(
                RecipeRecomendation(
                    imageCollection[index],
                    name,
                    category[index],
                    author[index],
                    time[index]
                )
            )
        }

        val adapter = RecommendationRecipeAdapter(dataList)
        binding.rvNewRecipe.adapter = adapter
    }

    private fun setupCategory() {
        binding.rvCategory.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val dataList: MutableList<Category> = mutableListOf()
        val flashSaleTitle: Array<String> = resources.getStringArray(R.array.title_category)

        val imageCategory: List<Int> = listOf(
            R.drawable.img_category_1,
            R.drawable.img_category_2,
            R.drawable.img_category_3,
            R.drawable.img_category_4,
            R.drawable.img_category_5,
            R.drawable.img_category_6,
        )

        flashSaleTitle.forEachIndexed { index, name ->
            dataList.add(Category(imageCategory[index], name))
        }

        val adapter = CategoryAdapter(dataList)
        binding.rvCategory.adapter = adapter
    }

    @Suppress("NAME_SHADOWING")
    private fun setupCarousel() {
        val imageList: MutableList<Carousel> = mutableListOf()
        val image: List<Int> = listOf(
            R.drawable.image_carousel_1,
            R.drawable.image_carousel_2,
            R.drawable.image_carousel_3,
        )

        image.forEachIndexed { _, image ->
            imageList.add(Carousel(image))
        }

        adapter = CarouselAdapter(imageList)
        binding.viewPager.adapter = adapter
        dots = ArrayList()
        setIndicator()
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
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
}