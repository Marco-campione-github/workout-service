package org.workout.ws.exercises

import org.springframework.stereotype.Service
import org.workout.ws.api.model.ApiExerciseRequest
import kotlin.jvm.optionals.getOrNull

@Service
class ExerciseService(
    private val exerciseDao: ExerciseDao,
) {
    fun getExerciseById(id: String): Exercise? =
        exerciseDao.findById(id).getOrNull()

    fun getAllExercises(): List<Exercise> =
        exerciseDao.findAll().toList()

    fun createExercise(exerciseRequest: ApiExerciseRequest): Exercise {
        val exercise = Exercise(
            name = exerciseRequest.name,
            description = exerciseRequest.description,
            primaryMuscleGroup = exerciseRequest.primaryMuscleGroup,
            secondaryMuscleGroups = exerciseRequest.secondaryMuscleGroups,
            equipment = exerciseRequest.equipment,
            createdByUserId = exerciseRequest.createdByUserId,
        )
        return exerciseDao.save(exercise)
    }
}
