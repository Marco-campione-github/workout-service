package org.workout.ws.api.model

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import org.workout.ws.user.ExperienceLevel
import org.workout.ws.user.Units
import java.time.LocalDate

data class CreateUserRequest(
    @field:Email(message = "must be a well-formed email address")
    @field:NotBlank(message = "must not be blank")
    val email: String,

    @field:NotBlank(message = "must not be blank")
    @field:Size(min = 3, max = 32, message = "length must be between {min} and {max}")
    val username: String,

    // Optional strings: validate length only if you want (kept permissive here)
    @field:Size(max = 64, message = "length must be <= {max}")
    val firstName: String? = null,

    @field:Size(max = 64, message = "length must be <= {max}")
    val lastName: String? = null,

    val birthDate: LocalDate? = null,

    val units: Units = Units.METRIC,

    @field:Min(value = 50, message = "must be >= {value}")
    @field:Max(value = 260, message = "must be <= {value}")
    val heightCm: Int? = null,

    @field:Min(value = 20, message = "must be >= {value}")
    @field:Max(value = 400, message = "must be <= {value}")
    val weightKg: Double? = null,

    @field:Min(value = 0, message = "must be >= {value}")
    @field:Max(value = 14, message = "must be <= {value}")
    val weeklyWorkoutGoal: Int = 3,

    val experienceLevel: ExperienceLevel = ExperienceLevel.BEGINNER
)

data class UpdateUserRequest(
    @field:Size(min = 3, max = 32, message = "length must be between {min} and {max}")
    val username: String? = null,

    @field:Size(max = 64, message = "length must be <= {max}")
    val firstName: String? = null,

    @field:Size(max = 64, message = "length must be <= {max}")
    val lastName: String? = null,

    val birthDate: LocalDate? = null,

    val units: Units? = null,

    @field:Min(value = 50, message = "must be >= {value}")
    @field:Max(value = 260, message = "must be <= {value}")
    val heightCm: Int? = null,

    @field:Min(value = 20, message = "must be >= {value}")
    @field:Max(value = 400, message = "must be <= {value}")
    val weightKg: Double? = null,

    @field:Min(value = 0, message = "must be >= {value}")
    @field:Max(value = 14, message = "must be <= {value}")
    val weeklyWorkoutGoal: Int? = null,

    val experienceLevel: ExperienceLevel? = null
)

data class UserResponse(
    val id: String,
    val userNumber: Long,
    val email: String,
    val username: String,
    val firstName: String?,
    val lastName: String?,
    val birthDate: LocalDate?,
    val units: Units,
    val heightCm: Int?,
    val weightKg: Double?,
    val weeklyWorkoutGoal: Int,
    val experienceLevel: ExperienceLevel,
    val createdAt: String,
    val updatedAt: String
)
