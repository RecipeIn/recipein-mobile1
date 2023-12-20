package com.lans.recipein_mobile.data.source.network.dto

import com.lans.recipein_mobile.domain.model.RecipeInstruction
import com.squareup.moshi.Json

data class RecipeInstructionResponseDto(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "recipe_id")
    val recipeId: Int,
    @field:Json(name = "instruction_id")
    val instructionId: Int,
    @field:Json(name = "instruction_description")
    val instructionDescription: String
)

fun RecipeInstructionResponseDto.toDomain() = RecipeInstruction(
    id = id,
    recipeId = recipeId,
    instructionId = instructionId,
    instructionDescription = instructionDescription
)