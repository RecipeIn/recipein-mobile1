package com.lans.recipein_mobile.presentation

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lans.recipein_mobile.R
import com.lans.recipein_mobile.databinding.ActivityMainBinding
import com.lans.recipein_mobile.presentation.favorite.FavoriteFragment
import com.lans.recipein_mobile.presentation.home.HomeFragment
import com.lans.recipein_mobile.presentation.profile.ProfileFragment
import com.lans.recipein_mobile.presentation.recipe.RecipeFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var controller: NavController
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        controller =
            (supportFragmentManager.findFragmentById(binding.navHost.id) as NavHostFragment).navController
        progressBar = binding.progressBar

        lifecycleScope.launch {
            viewModel.session.collect { session ->
                val navGraph = controller.navInflater.inflate(R.navigation.nav_graph)
                if (session) {
                    navGraph.setStartDestination(R.id.homeFragment)
                } else {
                    navGraph.setStartDestination(R.id.getStartedFragment)
                }
                controller.graph = navGraph
            }
        }

        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        binding.navigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.navHost, HomeFragment(), null)
                        .commit()
                    true
                }

                R.id.nav_recipe -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.navHost, RecipeFragment(), null)
                        .commit()
                    true
                }

                R.id.nav_favorite -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.navHost, FavoriteFragment(), null)
                        .commit()
                    true
                }

                R.id.nav_profile -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.navHost, ProfileFragment(), null)
                        .commit()
                    true
                }

                else -> false
            }
        }
    }

    fun showLoading(isLoading: Boolean) {
        progressBar.visibility = if (isLoading) {
            View.VISIBLE
        } else {
            View.GONE
        }

        binding.root.backgroundTintMode = if (isLoading) {
            PorterDuff.Mode.MULTIPLY
        } else {
            PorterDuff.Mode.SRC_IN
        }

        binding.root.backgroundTintList = if (isLoading) {
            ContextCompat.getColorStateList(this, R.color.neutral10)
        } else {
            ContextCompat.getColorStateList(this, R.color.white)
        }
    }
}