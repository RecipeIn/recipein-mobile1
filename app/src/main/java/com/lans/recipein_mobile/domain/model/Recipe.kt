package com.lans.recipein_mobile.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipe(
    val image: Int,
    val category: String,
    val recipe_tittle: String,
    val description: String,
    val author: String,
    val time: String,
    val steps: String,
    val foodMaterialList: List<FoodMaterial>,
    val nutrition: List<Nutrition>
): Parcelable

@Parcelize
data class FoodMaterial(
    val name: String,
    val quantity: String,
): Parcelable

@Parcelize
data class Nutrition(
    val name: String,
    val quantity: String,
): Parcelable