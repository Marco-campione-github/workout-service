package org.workout.ws.plan

import org.springframework.stereotype.Service
import org.workout.ws.api.model.CreateWorkoutPlanRequest
import org.workout.ws.api.model.UpdateWorkoutPlanRequest
import org.workout.ws.user.User
import java.time.Instant
import kotlin.jvm.optionals.getOrNull

@Service
class WorkoutPlanService(
    private val workoutPlanDao: WorkoutPlanDao,
) {

    fun createWorkoutPlan(req: CreateWorkoutPlanRequest): WorkoutPlan {
        val plan = WorkoutPlan(
            name = req.name,
            description = req.description,
            workoutPlanItems = req.workoutPlanItems.map {
                WorkoutPlanItem(
                    exerciseId = it.exerciseId,
                    sets = it.sets,
                    reps = it.reps,
                    restTimeSeconds = it.restTimeSeconds,
                    notes = it.notes
                )
            },
            createdByUserId = req.createdByUserId
        )
        return workoutPlanDao.save(plan)
    }

    fun getWorkoutPlanById(id: String): WorkoutPlan? =
        workoutPlanDao.findById(id).getOrNull()

    fun getAllWorkoutPlans(limit: Int?): List<WorkoutPlan> {
        val all = workoutPlanDao.findAll()
        return limit?.let { all.take(it.coerceIn(1, 200)) } ?: all
    }

    fun updateWorkoutPlan(id: String, req: UpdateWorkoutPlanRequest): WorkoutPlan {
        val existing = workoutPlanDao.findById(id).orElseThrow {
            WorkoutPlanNotFoundException("Workout plan '$id' not found")
        }

        val updated = existing.copy(
            name = req.name ?: existing.name,
            description = req.description ?: existing.description,
            workoutPlanItems = req.workoutPlanItems?.map {
                WorkoutPlanItem(
                    exerciseId = it.exerciseId,
                    sets = it.sets,
                    reps = it.reps,
                    restTimeSeconds = it.restTimeSeconds,
                    notes = it.notes
                )
            } ?: existing.workoutPlanItems,
            updatedAt = Instant.now()
        )
        return workoutPlanDao.save(updated)
    }

    fun deleteWorkoutPlan(id: String) {
        if (!workoutPlanDao.existsById(id)) {
            throw WorkoutPlanNotFoundException("Workout plan '$id' not found")
        }
        workoutPlanDao.deleteById(id)
    }
}

class WorkoutPlanNotFoundException(message: String) : RuntimeException(message)
