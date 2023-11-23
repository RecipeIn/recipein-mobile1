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
import androidx.navigation.ui.setupWithNavController
import com.lans.recipein_mobile.R
import com.lans.recipein_mobile.databinding.ActivityMainBinding
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
        hideBottomNavigation()
        setContentView(binding.root)

        setupProgressBar()
        setupNavController()
        setupBottomNavigation()
    }

    private fun setupNavController() {
        controller =
            (supportFragmentManager.findFragmentById(binding.navHost.id) as NavHostFragment).navController

        binding.bottomNavigation.setupWithNavController(controller)

        lifecycleScope.launch {
            viewModel.session.collect { session ->
                val navGraph = controller.navInflater.inflate(R.navigation.nav_graph)
                if (session) {
                    controller.navigate(R.id.homeFragment)
                }
                controller.graph = navGraph
            }
        }

        controller.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> {
                    showBottomNavigation()
                }

                R.id.recipeFragment -> {
                    showBottomNavigation()
                }

                R.id.favoriteFragment -> {
                    showBottomNavigation()
                }

                R.id.profileFragment -> {
                    showBottomNavigation()
                }

                else -> hideBottomNavigation()
            }
        }
    }

    private fun setupBottomNavigation() {
    }

    private fun setupProgressBar() {
        progressBar = binding.progressBar
    }

    private fun showBottomNavigation() {
        binding.bottomNavigation.visibility = View.VISIBLE
    }

    private fun hideBottomNavigation() {
        binding.bottomNavigation.visibility = View.GONE
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