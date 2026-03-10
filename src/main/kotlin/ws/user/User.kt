package org.workout.ws.user

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant
import java.time.LocalDate

@Document(collection = "users")
data class User(
    @Id
    val id: String? = null,

    @Indexed(unique = true, sparse = true)
    val userNumber: Long? = null,

    @Indexed(unique = true)
    val email: String,
    val username: String,
    val firstName: String? = null,
    val lastName: String? = null,
    val birthDate: LocalDate? = null,

    // Workout app domain fields
    val units: Units = Units.METRIC,
    val heightCm: Int? = null,
    val weightKg: Double? = null,
    val weeklyWorkoutGoal: Int = 3,
    val experienceLevel: ExperienceLevel = ExperienceLevel.BEGINNER,

    val createdAt: Instant = Instant.now(),
    val updatedAt: Instant = Instant.now()
)

enum class Units { METRIC, IMPERIAL }
enum class ExperienceLevel { BEGINNER, INTERMEDIATE, ADVANCED }