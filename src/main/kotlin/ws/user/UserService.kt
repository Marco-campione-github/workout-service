package org.workout.ws.user

import org.springframework.dao.DuplicateKeyException
import org.springframework.stereotype.Service
import org.workout.ws.api.model.CreateUserRequest
import org.workout.ws.api.model.UpdateUserRequest
import org.workout.ws.mongo.SequenceService
import java.time.Instant
import kotlin.jvm.optionals.getOrNull

@Service
class UserService(
    private val userDao: UserDao,
    private val sequenceService: SequenceService,
) {
    fun createUser(req: CreateUserRequest): User {
        val normalizedEmail = req.email.trim().lowercase()

        val user = User(
            userNumber = sequenceService.nextUserNumber(),
            email = normalizedEmail,
            username = req.username.trim(),
            firstName = req.firstName?.trim(),
            lastName = req.lastName?.trim(),
            birthDate = req.birthDate,
            units = req.units,
            heightCm = req.heightCm,
            weightKg = req.weightKg,
            weeklyWorkoutGoal = req.weeklyWorkoutGoal,
            experienceLevel = req.experienceLevel,
        )

        return try {
            userDao.save(user)
        } catch (_: DuplicateKeyException) {
            throw UserAlreadyExistsException("User with email '$normalizedEmail' already exists")
        }
    }

    fun getUserById(id: String): User =
        userDao.findById(id).getOrNull()
            ?: throw UserNotFoundException("User '$id' not found")

    fun listUsers(limit: Int?): List<User> {
        val all = userDao.findAll()
        return limit?.let { all.take(it.coerceIn(1, 200)) } ?: all
    }

    fun updateUser(id: String, req: UpdateUserRequest): User {
        val existing = userDao.findById(id).getOrNull()
            ?: throw UserNotFoundException("User '$id' not found")

        val updated = existing.copy(
            username = req.username?.trim() ?: existing.username,
            firstName = req.firstName?.trim() ?: existing.firstName,
            lastName = req.lastName?.trim() ?: existing.lastName,
            birthDate = req.birthDate ?: existing.birthDate,
            units = req.units ?: existing.units,
            heightCm = req.heightCm ?: existing.heightCm,
            weightKg = req.weightKg ?: existing.weightKg,
            weeklyWorkoutGoal = req.weeklyWorkoutGoal ?: existing.weeklyWorkoutGoal,
            experienceLevel = req.experienceLevel ?: existing.experienceLevel,
            updatedAt = Instant.now()
        )

        return userDao.save(updated)
    }

    fun deleteUser(id: String) {
        if (!userDao.existsById(id)) {
            throw UserNotFoundException("User '$id' not found")
        }
        userDao.deleteById(id)
    }
}

class UserNotFoundException(message: String) : RuntimeException(message)
class UserAlreadyExistsException(message: String) : RuntimeException(message)