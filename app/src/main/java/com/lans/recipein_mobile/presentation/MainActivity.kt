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
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.snackbar.Snackbar
import com.lans.recipein_mobile.R
import com.lans.recipein_mobile.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var progressBar: ProgressBar
    private lateinit var controller: NavController

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

    private fun setupProgressBar() {
        progressBar = binding.progressBar
    }

    private fun setupNavController() {
        controller =
            (supportFragmentManager.findFragmentById(binding.navHost.id) as NavHostFragment).navController

        val navGraph = controller.navInflater.inflate(R.navigation.nav_graph)

        lifecycleScope.launch {
            viewModel.session.collect { result ->
                showLoading(viewModel.loading.first())

                if(result != null) {
                    if (result != false) {
                        navGraph.setStartDestination(R.id.homeFragment)
                        controller.graph = navGraph
                        controller.navigate(R.id.homeFragment)
                    } else {
                        navGraph.setStartDestination(R.id.getStartedFragment)
                        controller.graph = navGraph
                        controller.navigate(R.id.getStartedFragment)
                    }

                    val profileGraph = navGraph.findNode(R.id.navProfile) as NavGraph
                    if (result != false) {
                        profileGraph.setStartDestination(R.id.profileSignInFragment)
                    } else {
                        profileGraph.setStartDestination(R.id.profileSignOutFragment)
                    }
                }
            }

            viewModel.error.collect { result ->
                if (!result.isNullOrBlank()) {
                    Snackbar.make(
                        binding.root,
                        result.toString(),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun setupBottomNavigation() {
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

                R.id.profileSignInFragment -> {
                    showBottomNavigation()
                }

                R.id.profileSignOutFragment -> {
                    showBottomNavigation()
                }

                else -> hideBottomNavigation()
            }
        }

        binding.bottomNavigation.setupWithNavController(controller)
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