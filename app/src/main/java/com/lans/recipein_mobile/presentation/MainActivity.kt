package com.lans.recipein_mobile.presentation

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.lans.recipein_mobile.R
import com.lans.recipein_mobile.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
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