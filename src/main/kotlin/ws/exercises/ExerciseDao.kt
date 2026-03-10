package org.workout.ws.exercises

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ExerciseDao : MongoRepository<Exercise, String> {

    fun findByName(name: String): Exercise?

    fun findByPrimaryMuscleGroup(muscleGroup: String): List<Exercise>

    fun findByCreatedByUserId(userId: String): List<Exercise>
}
