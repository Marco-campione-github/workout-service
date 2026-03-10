package org.workout.ws.api.model

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class ApiExerciseRequest(
    @field:NotBlank(message = "must not be blank")
    @field:Size(min = 2, max = 80, message = "length must be between {min} and {max}")
    val name: String,

    @field:NotBlank(message = "must not be blank")
    @field:Size(min = 10, max = 2000, message = "length must be between {min} and {max}")
    val description: String,

    @field:NotBlank(message = "must not be blank")
    @field:Size(min = 2, max = 64, message = "length must be between {min} and {max}")
    val primaryMuscleGroup: String,

    @field:Size(min = 0, max = 10, message = "size must be between {min} and {max}")
    val secondaryMuscleGroups: List<@NotBlank(message = "must not be blank") @Size(
        min = 2,
        max = 64,
        message = "length must be between {min} and {max}"
    ) String>? = null,

    @field:NotBlank(message = "must not be blank")
    @field:Size(min = 2, max = 64, message = "length must be between {min} and {max}")
    val equipment: String,

    val createdByUserId: String
)

data class ExerciseResponse(
    val id: String,
    val name: String,
    val description: String,
    val primaryMuscleGroup: String,
    val secondaryMuscleGroups: List<String>?,
    val equipment: String,
    val createdByUserId: String?,
    val createdAt: String,
    val updatedAt: String
)
