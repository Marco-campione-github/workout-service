package org.workout.ws.plan

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface WorkoutPlanDao : MongoRepository<WorkoutPlan, String>
