package org.workout.ws.session

import org.springframework.stereotype.Service
import org.workout.ws.api.model.CreateWorkoutSessionRequest
import org.workout.ws.api.model.UpdateWorkoutSessionRequest
import java.time.Instant
import kotlin.jvm.optionals.getOrNull

@Service
class WorkoutSessionService(
    private val workoutSessionDao: WorkoutSessionDao
) {

    fun createWorkoutSession(req: CreateWorkoutSessionRequest): WorkoutSession {
        val session = WorkoutSession(
            userId = req.userId,
            workoutPlanId = req.workoutPlanId,
            startedAt = req.startedAt,
            sessionExercises = req.sessionExercises.map {
                SessionExercise(
                    exerciseId = it.exerciseId,
                    plannedSets = it.plannedSets,
                    plannedReps = it.plannedReps,
                    difficulty = it.difficulty,
                    notes = it.notes,
                )
            },
            notes = req.notes,
        )
        return workoutSessionDao.save(session)
    }

    fun getWorkoutSessionById(id: String): WorkoutSession? =
        workoutSessionDao.findById(id).getOrNull()

    fun getAllWorkoutSessions(limit: Int?): List<WorkoutSession> {
        val all = workoutSessionDao.findAll()
        return limit?.let { all.take(it.coerceIn(1, 200)) } ?: all
    }

    fun getWorkoutSessionsByUserId(userId: String, limit: Int?): List<WorkoutSession> {
        val all = workoutSessionDao.findByUserId(userId)
        return limit?.let { all.take(it.coerceIn(1, 200)) } ?: all
    }

    fun getWorkoutSessionsByWorkoutPlanId(workoutPlanId: String, limit: Int?): List<WorkoutSession> {
        val all = workoutSessionDao.findByWorkoutPlanId(workoutPlanId)
        return limit?.let { all.take(it.coerceIn(1, 200)) } ?: all
    }

    fun updateWorkoutSession(id: String, req: UpdateWorkoutSessionRequest): WorkoutSession {
        val existing = workoutSessionDao.findById(id).getOrNull()
            ?: throw WorkoutSessionNotFoundException("Workout session '$id' not found")

        val updated = existing.copy(
            completedAt = req.completedAt ?: existing.completedAt,
            sessionExercises = req.sessionExercises?.map {
                SessionExercise(
                    exerciseId = it.exerciseId,
                    plannedSets = it.plannedSets,
                    plannedReps = it.plannedReps,
                    actualSets = it.actualSets,
                    actualReps = it.actualReps,
                    weightUsed = it.weightUsed,
                    difficulty = it.difficulty,
                    notes = it.notes,
                )
            } ?: existing.sessionExercises,
            notes = req.notes ?: existing.notes,
            completed = req.completed ?: existing.completed,
            updatedAt = Instant.now()
        )

        return workoutSessionDao.save(updated)
    }

    fun deleteWorkoutSession(id: String) {
        if (!workoutSessionDao.existsById(id)) {
            throw WorkoutSessionNotFoundException("Workout session '$id' not found")
        }
        workoutSessionDao.deleteById(id)
    }
}

class WorkoutSessionNotFoundException(message: String) : RuntimeException(message)
