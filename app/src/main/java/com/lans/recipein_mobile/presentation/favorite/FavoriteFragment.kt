package com.lans.recipein_mobile.presentation.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.lans.recipein_mobile.databinding.FragmentFavoriteBinding
import com.lans.recipein_mobile.domain.model.Recipe
import com.lans.recipein_mobile.presentation.MainActivity
import com.lans.recipein_mobile.presentation.adapter.EmptyAdapter
import com.lans.recipein_mobile.presentation.adapter.FavoriteAdapter
import com.lans.recipein_mobile.utils.SpacesItemDecoration
import com.lans.recipein_mobile.utils.safeNavigate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Locale

@AndroidEntryPoint
class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding
    private val viewModel: FavoriteViewModel by viewModels()

    private var favoriteData = emptyList<Recipe>()
    private lateinit var svFavorite: SearchView
    private lateinit var rvFavorite: RecyclerView
    private lateinit var favoriteAdapter: FavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFavoriteBinding.inflate(layoutInflater)
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
//        binding.vpFavorite.adapter = FavoritePagerAdapter(childFragmentManager, 2)
//        binding.vpFavorite.offscreenPageLimit = 2
//        binding.tabFavorite.setupWithViewPager(binding.vpFavorite)
        svFavorite = binding.svFavorite
        svFavorite.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(text: String?): Boolean {
                try {
                    if (!text.isNullOrBlank()) {
                        filter(text)
                    } else {
                        favoriteAdapter.filter(favoriteData)
                    }
                } catch (ex: Exception) {
                    return false
                }
                return false
            }
        })
        rvFavorite = binding.rvFavorite
        rvFavorite.layoutManager = GridLayoutManager(requireContext(), 2)
        rvFavorite.addItemDecoration(SpacesItemDecoration(20))
    }

    private suspend fun observe() {
        viewModel.state.collect { result ->
            (requireActivity() as MainActivity).showLoading(result.isLoading)

            if (result.favorite.isNotEmpty()) {
                if (result.recipes.isNotEmpty()) {
                    favoriteData = result.recipes.filter { recipe ->
                        recipe.id in result.favorite
                    }
                        favoriteAdapter = FavoriteAdapter(favoriteData)
                    favoriteAdapter.setItemClick(object : FavoriteAdapter.AdapterListener {
                        override fun onClick(position: Int) {
                            val action =
                                FavoriteFragmentDirections.actionFavoriteFragmentToRecipePageFragment(
                                    favoriteAdapter.list[position].id
                                )
                            findNavController().safeNavigate(action)
                        }
                    })
                    rvFavorite.layoutManager = GridLayoutManager(requireContext(), 2)
                    rvFavorite.adapter = favoriteAdapter
                }
            } else {
                rvFavorite.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                rvFavorite.adapter = EmptyAdapter()
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
        val filter = favoriteData.filter {
            it.name.lowercase(Locale.getDefault()).contains(
                recipe.lowercase(
                    Locale.getDefault()
                )
            )
        }
        favoriteAdapter.filter(filter)
    }
}