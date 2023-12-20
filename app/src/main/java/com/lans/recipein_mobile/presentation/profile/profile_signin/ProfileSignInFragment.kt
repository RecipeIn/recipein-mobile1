package com.lans.recipein_mobile.presentation.profile.profile_signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil.load
import com.google.android.material.snackbar.Snackbar
import com.lans.recipein_mobile.R
import com.lans.recipein_mobile.databinding.FragmentProfileSigninBinding
import com.lans.recipein_mobile.domain.enum.Activity
import com.lans.recipein_mobile.domain.enum.Gender
import com.lans.recipein_mobile.domain.model.User
import com.lans.recipein_mobile.domain.model.UserNutrition
import com.lans.recipein_mobile.presentation.MainActivity
import com.lans.recipein_mobile.utils.safeNavigate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileSignInFragment : Fragment(), OnClickListener {
    private lateinit var binding: FragmentProfileSigninBinding
    private val viewModel: ProfileSignInViewModel by viewModels()

    private var profile: User = User()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentProfileSigninBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeComponent()
        lifecycleScope.launch {
            observe()
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnSetting -> {
                val action =
                    ProfileSignInFragmentDirections.actionProfileSignInFragmentSettingFragment()
                findNavController().safeNavigate(action)
            }

            R.id.btnUpdateNutrition -> {
                val age = binding.etAgeValue.text.toString()
                val height = binding.etHeightValue.text.toString()
                val weight = binding.etWeightValue.text.toString()
                val gender = when (binding.spGender.selectedItemPosition) {
                    0 -> Gender.MALE
                    else -> Gender.FEMALE
                }
                val activity = when (binding.spActivity.selectedItemPosition) {
                    0 -> Activity.LOW
                    1 -> Activity.MEDIUM
                    2 -> Activity.HIGH
                    else -> Activity.VERY_HIGH
                }

                if (age == "0") {
                    return Snackbar.make(
                        binding.root,
                        "Please enter your age",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                if (height == "0") {
                    return Snackbar.make(
                        binding.root,
                        "Please enter your height",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                if (weight == "0") {
                    return Snackbar.make(
                        binding.root,
                        "Please enter your weight",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }

                val calories =
                    calculateCalories(height.toInt(), weight.toInt(), age.toInt(), gender, activity)
                val protein = (15.0 / 100) * calories / 4
                val carbohydrate = (65.0 / 100) * calories / 4
                val fat = (20.0 / 100) * calories / 4
                val fiber = calories / 1000 * 14

                lifecycleScope.launch {
                    viewModel.updateProfileAndNutrition(
                        profile.copy(
                            age = age.toInt(),
                            height = height.toInt(),
                            weight = weight.toInt(),
                            gender = gender,
                            activity = activity
                        ),
                        UserNutrition(
                            profile.userNutrition?.id ?: 0,
                            profile.id,
                            calories,
                            protein.toInt(),
                            carbohydrate.toInt(),
                            fiber,
                            fat.toInt()
                        )
                    )
                }
            }
        }
    }

    private fun initializeComponent() {
        binding.btnSetting.setOnClickListener(this)
        binding.btnUpdateNutrition.setOnClickListener(this)
    }

    private suspend fun observe() {
        viewModel.state.collect { result ->
            (requireActivity() as MainActivity).showLoading(result.isLoading)

            if (result.profile != null) {
                profile = result.profile!!

                if (profile.avatar.isNotBlank()) {
                    binding.ivProfilePicture.load(profile.avatar)
                } else {
                    binding.ivProfilePicture.load(R.drawable.ic_profile)
                }
                binding.tvName.text = profile.username
                binding.etAgeValue.setText(profile.age.toString())
                binding.etHeightValue.setText(profile.height.toString())
                binding.etWeightValue.setText(profile.weight.toString())

                when (profile.activity) {
                    Activity.LOW ->
                        binding.spActivity.setSelection(0)

                    Activity.MEDIUM ->
                        binding.spActivity.setSelection(1)

                    Activity.HIGH ->
                        binding.spActivity.setSelection(2)

                    Activity.VERY_HIGH ->
                        binding.spActivity.setSelection(3)
                }

                when (profile.gender) {
                    Gender.MALE -> binding.spGender.setSelection(0)
                    Gender.FEMALE -> binding.spGender.setSelection(1)
                }

                if (profile.userNutrition != null) {
                    binding.tvCalorieValue.text = profile.userNutrition?.calories.toString()
                    binding.tvCarbohydrateValue.text =
                        profile.userNutrition?.carbohydrate.toString()
                    binding.tvProteinValue.text = profile.userNutrition?.protein.toString()
                    binding.tvFiberValue.text = profile.userNutrition?.fiber.toString()
                    binding.tvFatValue.text = profile.userNutrition?.fat.toString()
                }
            }

            if (result.isUpdated) {
                Snackbar.make(
                    binding.root,
                    "Nutrition updated",
                    Snackbar.LENGTH_SHORT
                ).show()
                result.isUpdated = false
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

    private fun calculateCalories(
        height: Int,
        weight: Int,
        age: Int,
        gender: Gender,
        activity: Activity
    ): Int {
        if (gender == Gender.MALE) {
            val bmr = (66.5 + (13.75 * weight) + (5.003 * height) - (6.75 * age))
            return when (activity) {
                Activity.LOW -> (bmr * 1.375).toInt()
                Activity.MEDIUM -> (bmr * 1.55).toInt()
                Activity.HIGH -> (bmr * 1.725).toInt()
                Activity.VERY_HIGH -> (bmr * 1.9).toInt()
            }
        }

        val bmr = (655.1 + (9.563 * weight) + (1.850 * height) - (4.676 * age))
        return when (activity) {
            Activity.LOW -> (bmr * 1.375).toInt()
            Activity.MEDIUM -> (bmr * 1.55).toInt()
            Activity.HIGH -> (bmr * 1.725).toInt()
            Activity.VERY_HIGH -> (bmr * 1.9).toInt()
        }
    }
}