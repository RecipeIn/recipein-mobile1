package com.lans.recipein_mobile.presentation.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.snackbar.Snackbar
import com.lans.recipein_mobile.R
import com.lans.recipein_mobile.databinding.FragmentCategoryBinding
import com.lans.recipein_mobile.presentation.MainActivity
import com.lans.recipein_mobile.presentation.adapter.EmptyAdapter
import com.lans.recipein_mobile.presentation.adapter.RecommendationRecipeAdapter
import com.lans.recipein_mobile.utils.SpacesItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoryFragment : Fragment(), OnClickListener {
    private lateinit var binding: FragmentCategoryBinding
    private val viewModel: CategoryViewModel by viewModels()
    private val args: CategoryFragmentArgs by navArgs()

    private lateinit var rvRecipeCategory: RecyclerView
    private lateinit var adapter: RecommendationRecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (args.categoryId != 0) {
            viewModel.loadCategory(args.categoryId)
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
        rvRecipeCategory = binding.rvRecipeCategory
        rvRecipeCategory.addItemDecoration(SpacesItemDecoration(6))
        binding.btnBack.setOnClickListener(this)
    }

    private suspend fun observe() {
        viewModel.state.collect { result ->
            (requireActivity() as MainActivity).showLoading(result.isLoading)

            val category = result.category
            if (category != null) {
                if (category.image != null) {
                    binding.ivCategory.load(category.image)
                } else {
                    binding.ivCategory.load(R.drawable.img_background1)
                }
                binding.tvCategory.text = category.name
                binding.tvCategoryDesc.text = category.description

                if (result.recipe.isNotEmpty()) {
                    val recipe = result.recipe
                    adapter = RecommendationRecipeAdapter(recipe)
                    rvRecipeCategory.layoutManager = GridLayoutManager(requireContext(), 2)
                    rvRecipeCategory.adapter = adapter
                    rvRecipeCategory.adapter = adapter
                }
            } else {
                rvRecipeCategory.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                rvRecipeCategory.adapter = EmptyAdapter()
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