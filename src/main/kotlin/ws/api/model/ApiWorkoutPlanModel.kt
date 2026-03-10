package org.workout.ws.api.model

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

data class CreateWorkoutPlanRequest(
    @field:NotBlank(message = "must not be blank")
    @field:Size(min = 2, max = 80, message = "length must be between {min} and {max}")
    val name: String,

    @field:NotBlank(message = "must not be blank")
    @field:Size(min = 10, max = 2000, message = "length must be between {min} and {max}")
    val description: String,

    @field:NotEmpty(message = "must not be empty")
    val workoutPlanItems: List<WorkoutPlanItemRequest>,

    val createdByUserId: String
)

data class UpdateWorkoutPlanRequest(
    @field:Size(min = 2, max = 80, message = "length must be between {min} and {max}")
    val name: String? = null,

    @field:Size(min = 10, max = 2000, message = "length must be between {min} and {max}")
    val description: String? = null,

    val workoutPlanItems: List<WorkoutPlanItemRequest>? = null,
)

data class WorkoutPlanItemRequest(
    val exerciseId: String,
    val sets: Int,
    val reps: Int,
    val restTimeSeconds: Int,
    val notes: String? = null
)

data class WorkoutPlanResponse(
    val id: String,
    val name: String,
    val description: String,
    val workoutPlanItems: List<WorkoutPlanItemResponse>,
    val createdByUserId: String?,
    val createdAt: String,
    val updatedAt: String
)

data class WorkoutPlanItemResponse(
    val exerciseId: String,
    val sets: Int,
    val reps: Int,
    val restTimeSeconds: Int,
    val notes: String? = null,
)
