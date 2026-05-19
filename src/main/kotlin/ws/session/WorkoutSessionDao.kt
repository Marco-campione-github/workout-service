package org.workout.ws.session

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface WorkoutSessionDao : MongoRepository<WorkoutSession, String> {
    fun findByUserId(userId: String): List<WorkoutSession>
    fun findByWorkoutPlanId(workoutPlanId: String): List<WorkoutSession>
}
