package org.workout.ws.session

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(collection = "sessions")
data class WorkoutSession(
    @Id val id: String? = null,
    val userId: String,
    val workoutPlanId: String,
    val startedAt: Instant,
    val completedAt: Instant? = null,
    val sessionExercises: List<SessionExercise>,
    val notes: String? = null,
    val completed: Boolean = false,
    val createdAt: Instant = Instant.now(),
    val updatedAt: Instant = Instant.now(),
)

data class SessionExercise(
    val exerciseId: String,
    val plannedSets: Int,
    val plannedReps: Int,
    val actualSets: Int? = null,
    val actualReps: Int? = null,
    val weightUsed: Double? = null,
    val difficulty: Difficulty? = null,
    val notes: String? = null,
)

enum class Difficulty {
    EASY,
    MEDIUM,
    HARD,
}