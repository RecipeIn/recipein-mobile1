package com.lans.recipein_mobile.presentation.home

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.lans.recipein_mobile.R
import com.lans.recipein_mobile.adapter.CarouselAdapter
import com.lans.recipein_mobile.adapter.CategoryAdapter
import com.lans.recipein_mobile.adapter.CollectionRecipeAdapter
import com.lans.recipein_mobile.adapter.RecomendationRecipeAdapter
import com.lans.recipein_mobile.databinding.FragmentHomeBinding
import com.lans.recipein_mobile.domain.model.Carousel
import com.lans.recipein_mobile.domain.model.Category
import com.lans.recipein_mobile.domain.model.CollectionRecipe
import com.lans.recipein_mobile.domain.model.RecipeRecomendation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), OnClickListener {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapter: CarouselAdapter
    private lateinit var dots: ArrayList<TextView>

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
        foodRecomendation()
        drinkRecomendation()
        recipeCollection()
        newRecipe()
    }

    private fun newRecipe(){
        binding.rvNewRecipe.layoutManager = GridLayoutManager(requireContext(), 2)
        val dataList: MutableList<RecipeRecomendation> = mutableListOf()
        val tittle_recipe: Array<String> = resources.getStringArray(R.array.title_new_recipe)
        val category: Array<String> = resources.getStringArray(R.array.category_new_recipe)
        val author: Array<String> = resources.getStringArray(R.array.author_new_recipe)
        val time: Array<String> = resources.getStringArray(R.array.time_new_recipe)

        val imageCollection : List<Int> = listOf(
            R.drawable.img_new_recipe_1,
            R.drawable.img_new_recipe_2,
            R.drawable.img_new_recipe_3,
            R.drawable.img_new_recipe_4,
        )

        tittle_recipe.forEachIndexed { index, name ->
            dataList.add(RecipeRecomendation(imageCollection[index], name, category[index], author[index], time[index]))
        }

        val adapter = RecomendationRecipeAdapter(dataList)
        binding.rvNewRecipe.adapter = adapter
    }

    private fun recipeCollection() {
        binding.rvCollection.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val dataList: MutableList<CollectionRecipe> = mutableListOf()
        val title: Array<String> = resources.getStringArray(R.array.title_recipe_colection)
        val desc: Array<String> = resources.getStringArray(R.array.description_collection)
        val author: Array<String> = resources.getStringArray(R.array.author_collection)

        val imageCollection : List<Int> = listOf(
            R.drawable.img_collection_1,
            R.drawable.img_collection_2,
        )

        title.forEachIndexed { index, name ->
            dataList.add(CollectionRecipe(imageCollection[index], name, desc[index], author[index]))
        }

        val adapter = CollectionRecipeAdapter(dataList)
        binding.rvCollection.adapter = adapter
    }

    private fun drinkRecomendation() {
        binding.rvDrinkRecomendation.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val dataList: MutableList<RecipeRecomendation> = mutableListOf()
        val tittle_recipe: Array<String> = resources.getStringArray(R.array.title_recipe_drink)
        val category: Array<String> = resources.getStringArray(R.array.category_drink)
        val author: Array<String> = resources.getStringArray(R.array.author_drink)
        val time: Array<String> = resources.getStringArray(R.array.time_drink)

        val imageDrinkRecomen : List<Int> = listOf(
            R.drawable.img_drink_recomen_1,
            R.drawable.img_drink_recomen_2,
            R.drawable.img_drink_recomen_3,
            R.drawable.img_drink_recomen_4,
        )

        tittle_recipe.forEachIndexed { index, name ->
            dataList.add(RecipeRecomendation(imageDrinkRecomen[index], name, category[index], author[index], time[index]))
        }

        val adapter = RecomendationRecipeAdapter(dataList)
        binding.rvDrinkRecomendation.adapter = adapter
    }

    private fun foodRecomendation() {
        binding.rvFoodRecomendation.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val dataList: MutableList<RecipeRecomendation> = mutableListOf()
        val tittle_recipe: Array<String> = resources.getStringArray(R.array.title_recipe)
        val category: Array<String> = resources.getStringArray(R.array.category)
        val author: Array<String> = resources.getStringArray(R.array.author)
        val time: Array<String> = resources.getStringArray(R.array.time)

        val imageFoodRecomen : List<Int> = listOf(
            R.drawable.img_weekly_recomen_1,
            R.drawable.img_weekly_recomen_2,
            R.drawable.img_weekly_recomen_3,
            R.drawable.img_weekly_recomen_4,
        )

        tittle_recipe.forEachIndexed { index, name ->
            dataList.add(RecipeRecomendation(imageFoodRecomen[index], name, category[index], author[index], time[index]))
        }

        val adapter = RecomendationRecipeAdapter(dataList)
        binding.rvFoodRecomendation.adapter = adapter
    }

    private fun setupCategory() {
        binding.rvCategory.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val dataList: MutableList<Category> = mutableListOf()
        val flashSaleTitle: Array<String> = resources.getStringArray(R.array.title_category)

        val imageCategory : List<Int> = listOf(
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
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                selectedDot(position)
                super.onPageSelected(position)
            }
        })
    }

    private fun selectedDot(position: Int) {
        for (i in 1..2){
            if (1 == position)
                dots[i].setTextColor(resources.getColor(R.color.neutral50))
            else
                dots[i].setTextColor(resources.getColor(R.color.secondary70))
        }
    }

    private fun setIndicator() {
        for (i in 0..2){
            dots.add(TextView(requireContext()))
            dots[i].text = Html.fromHtml("&#9879", Html.FROM_HTML_MODE_LEGACY).toString()
            dots[i].textSize = 18f
            binding.dotsIndicator.addView(dots[i])
        }

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
//            R.id.tvLogout -> {
//                viewModel.signOut()
//            }
        }
    }

    private fun initializeComponent() {
        //binding.tvLogout.setOnClickListener(this)
    }
}