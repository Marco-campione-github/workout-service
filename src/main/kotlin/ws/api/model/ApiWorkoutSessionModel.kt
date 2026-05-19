package org.workout.ws.api.model

import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import org.workout.ws.session.Difficulty
import java.time.Instant

data class CreateWorkoutSessionRequest(
    @field:NotBlank(message = "must not be blank")
    val userId: String,

    @field:NotBlank(message = "must not be blank")
    val workoutPlanId: String,

    val startedAt: Instant,

    @field:Valid
    val sessionExercises: List<ApiSessionExercise>,

    @field:Size(max = 2000, message = "length must be <= {max}")
    val notes: String? = null
)

data class UpdateWorkoutSessionRequest(
    val completedAt: Instant? = null,

    @field:Valid
    val sessionExercises: List<ApiSessionExercise>? = null,

    @field:Size(max = 2000, message = "length must be <= {max}")
    val notes: String? = null,

    val completed: Boolean? = null
)

data class ApiSessionExercise(
    @field:NotBlank(message = "must not be blank")
    val exerciseId: String,

    val plannedSets: Int,

    val plannedReps: Int,

    val actualSets: Int? = null,

    val actualReps: Int? = null,

    val weightUsed: Double? = null,

    val difficulty: Difficulty? = null,

    @field:Size(max = 1000, message = "length must be <= {max}")
    val notes: String? = null,
)

data class WorkoutSessionResponse(
    val id: String,
    val userId: String,
    val workoutPlanId: String,
    val startedAt: String,
    val completedAt: String?,
    val sessionExercises: List<SessionExerciseResponse>,
    val notes: String?,
    val completed: Boolean,
    val createdAt: String,
    val updatedAt: String
)

data class SessionExerciseResponse(
    val exerciseId: String,
    val plannedSets: Int,
    val plannedReps: Int,
    val actualSets: Int?,
    val actualReps: Int?,
    val weightUsed: Double?,
    val difficulty: Difficulty?,
    val notes: String?,
)
