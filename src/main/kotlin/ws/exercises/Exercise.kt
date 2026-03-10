package org.workout.ws.exercises

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(collection = "exercises")
class Exercise(
    @Id
    val id: String? = null,
    val name: String,
    val description: String,
    val primaryMuscleGroup: String,
    val secondaryMuscleGroups: List<String>? = null,
    val equipment: String,
    val createdByUserId: String? = null,

    val createdAt: Instant = Instant.now(),
    val updatedAt: Instant = Instant.now()
)