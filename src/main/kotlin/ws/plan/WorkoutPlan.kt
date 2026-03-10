package org.workout.ws.plan

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(collection = "plans")
data class WorkoutPlan(
    @Id
    val id: String? = null,
    val name: String,
    val description: String,
    val workoutPlanItems: List<WorkoutPlanItem>,
    val createdByUserId: String? = null,

    val createdAt: Instant = Instant.now(),
    val updatedAt: Instant = Instant.now()
)

data class WorkoutPlanItem(
    val exerciseId: String,
    val sets: Int,
    val reps: Int,
    val restTimeSeconds: Int,
    val notes: String? = null
)